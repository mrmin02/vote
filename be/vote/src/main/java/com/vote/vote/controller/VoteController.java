package com.vote.vote.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.vote.vote.db.dto.Candidate;
import com.vote.vote.db.dto.Member;
import com.vote.vote.db.dto.Program;
import com.vote.vote.db.dto.Vote;
import com.vote.vote.db.dto.Voter;
import com.vote.vote.klaytn.Klaytn;
import com.vote.vote.repository.CandidateJpaRepository;
import com.vote.vote.repository.CustomVoteRepository;
import com.vote.vote.repository.MemberJpaRepository;
import com.vote.vote.repository.ProgramJpaRepository;
import com.vote.vote.repository.VoteJpaRepository;
import com.vote.vote.repository.VoterJpaRepository;
import com.vote.vote.service.StorageService;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/vote")
public class VoteController {

	@Autowired  
	private StorageService storageService; 

	@Autowired
	private MemberJpaRepository MemberRepository;

	@Autowired
	private VoteJpaRepository voteRepository;

	@Autowired
	private CandidateJpaRepository candidateRepository;

	@Autowired 
	private VoterJpaRepository voterRepository;

	@Autowired
	private CustomVoteRepository customVoteRepository;

	@Autowired
	private ProgramJpaRepository programJpaRepository;


	public Klaytn klaytn = new Klaytn();


	//  투표 메인
	@RequestMapping(value={"","/"}, method=RequestMethod.GET)
	public String index(Model model, Principal user) { 

		return "vote/index";
	}
	
	// 투표 메인 페이지 axios
	@RequestMapping(value={"/axios","/axios/"})
	@ResponseBody
	public JSONArray indexMainAxios(Principal user,Pageable page,
	 int state,
	 int program){

		System.out.println("투표 메인페이지");
		String nowTime = getNowTime();
		int type = state;
		System.out.println("state:"+state);
		System.out.println("현재시각"+nowTime);
		System.out.println("프로그램 id"+program);
		// if(state != null)
		// 	type = state;
		
		
		// List<Vote> votes = customVoteRepositoy.customFindVotes(nowTime,type,page);
		List<Vote> votes = customVoteRepository.customFindVotes(nowTime,page,type, program);
		long count = customVoteRepository.getFindVotesCount();

		JSONArray json = createVoteList(votes);
		
		json.add(json.size(),count);

		return json;
	}

	public String getNowTime(){
		Date time = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
		String nowTime = format.format(time);
		return nowTime;
	}


	public JSONArray createVoteList(List<Vote> votes){
		JSONArray json = new JSONArray();

		for( Vote vote : votes){
			JSONObject voteData = new JSONObject();
			voteData.put("title", vote.getTitle());
			voteData.put("id",vote.getId());
			voteData.put("thumbnail",vote.getThumbnail());
			// data.add(voteData);
			json.add(voteData);
		}

		return json;
	}



    @RequestMapping(value={"/create","/create/"})
	public String create(Model model) {
		return "vote/create";
	}

	@ResponseBody
	@RequestMapping(value={"/program/axios","/program/axios/"})
	public JSONArray createAxios() {
		
		JSONArray result = new JSONArray();

		List<Program> programList = programJpaRepository.findAll();

		for(Program program : programList){
			JSONObject json = new JSONObject();
			json.put("id", program.getId());
			json.put("name",program.getName());
			result.add(json);
		}

		return result;
	}
	
	@RequestMapping(value={"","/"}, method=RequestMethod.POST)
	public String store(
		@RequestParam(name="title") String title,
		@RequestParam(name="file") MultipartFile[] file,
		@RequestParam(name="name") ArrayList<String> names,
		@RequestParam(name="count") int count,
		@RequestParam(name="startTime") String startTime,
		@RequestParam(name="endTime") String endTime,
		@RequestParam(name="thumbnail") MultipartFile thumbnail,
		@RequestParam(name="program_id") int programId,
		Principal user
	){
		
		storageService.store(thumbnail);
		String thumbnailPath = StringUtils.cleanPath(thumbnail.getOriginalFilename());


		ArrayList<String> fileName = new ArrayList<String>();
		
		for(int i=0;i<file.length;i++){
			storageService.store(file[i]);   // 파일 저장
			fileName.add(StringUtils.cleanPath(file[i].getOriginalFilename()));		// 파일 이름을 배열에 저장
		}

		Vote data = new Vote();

		// string 에서 숫자만 추출   기본 값: 년-월-일T시:분
		String startTime_set = startTime.replaceAll("[^0-9]","");
		String endTime_set = endTime.replaceAll("[^0-9]","");


		data.setTitle(title);
		data.setWriter(user.getName());
		data.setCount(count);
		data.setStartTime(startTime_set);
		data.setEndTIme(endTime_set);
		data.setThumbnail(thumbnailPath);
		data.setProgram_id(programId);
        voteRepository.saveAndFlush(data);
        
        
        
        for(int i=0; i<count;i++){
            Candidate candidate =  new Candidate();
            candidate.setVoteId(data.getId());
            candidate.setImg(fileName.get(i));
            candidate.setName(names.get(i));
            candidateRepository.saveAndFlush(candidate);
        }
        
		
		
		ExecutorService es = Executors.newCachedThreadPool();
        
        es.execute(() -> {
            try {
				// JSONObject json = klaytn.klaytnDeploy();
				JSONObject json = klaytn.klaytnDeploy2();// 스마트 컨트렉트 배포
				
				System.out.println(json);
				data.setAddress(json.get("address").toString());

				voteRepository.saveAndFlush(data);
				
				//스마트 컨트랙트 배포후에 투표 시작시간, 끝 시간 세팅
				JSONObject json2 = klaytn.klaytnSetOptions(json.get("address").toString(), Long.parseLong(startTime_set), Long.parseLong(endTime_set), count);
				System.out.println(json2);

            } catch (Exception e) {
                System.out.println("클레이튼 오류 발생 : 투표 생성");
            }
        });

		

		
		// 모든 회원들에게 투표 권한 줌  // 나중에 로직 변경 가능성 있음.
		ArrayList<Member> members = MemberRepository.findAll();
		for (Member member : members) {


			Voter voter = new Voter();
			voter.setVoteId(data.getId());
			voter.setUserid(member.getUserid());
			voter.setState(0);

			voterRepository.saveAndFlush(voter);

		}
		
		
		return "redirect:/vote";
	}

	@RequestMapping(value={"/{voteId}","/{voteId}/"})
	public String show(Model model, @PathVariable("voteId") int voteId){
		Vote vote = voteRepository.findById(voteId);
		Long time = Long.parseLong(getNowTime());
		
		model.addAttribute("type", 1);
		if(time < vote.getLongStartTime()){//시작전
			System.out.println("시작전 투표");
			model.addAttribute("type", 0);
		}else if(time >= vote.getLongEndTime()){// 마감
			System.out.println("마감된 투표");
			model.addAttribute("type", 2);
		}
		// 진행중인 투표
		return "vote/show";
	}

	@RequestMapping(value={"/axios/{voteId}","/axios/{voteId}/"},
	method=RequestMethod.GET,
	produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JSONArray showVoteAxios(@PathVariable("voteId") int voteId){
		
		// Vote vote = voteRepository.findById(voteId);
		// Vote_img img = vote_imgRepository.findById(vote.getImg());
		// Vote_name name = vote_nameRepository.findById(vote.getName());
		ArrayList<Candidate> candidateList = candidateRepository.findByVoteId(voteId);
		
        
		JSONArray array = new JSONArray();
        
        // ArrayList<String> names = new ArrayList<String>();
		// ArrayList<String> imgs = new ArrayList<String>();
        
        // for(int i=0; i<candidateList.size();i++){
        //     names.add(candidateList.get(i).getName());
        //     imgs.add(candidateList.get(i).getImg());
		// }
		


		for(int i=0; i<candidateList.size();i++){
			JSONObject item = new JSONObject();
			item.put("name", candidateList.get(i).getName());
			item.put("img",candidateList.get(i).getImg());
			array.add(item);
		}
		JSONObject voteInfo = new JSONObject();
		Vote vote = voteRepository.findById(voteId);
		voteInfo.put("title",vote.getTitle());

		Program program = programJpaRepository.findById(vote.getProgram_id());

		JSONObject date = new JSONObject();
		date.put("startTime", vote.getStartTime());
		date.put("endTime", vote.getEndTime());

		JSONArray result = new JSONArray();
		result.add(0, array);
		result.add(1, voteInfo);
		result.add(2, program);
		result.add(3, date);
			
		return result;
	}

	@RequestMapping(value={"/axios/{voteId}","/axios/{voteId}/"},method=RequestMethod.POST,
	produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JSONObject selectVote(@PathVariable("voteId") int voteId,
		@RequestBody JSONObject axiosData,
		Principal user
	){
		// System.out.println(axiosData.get("select"));  // 사용자가 뽑은 사람의 번호
		JSONObject result = new JSONObject();

		Voter voter = voterRepository.findByVoteIdAndUserId(voteId, user.getName());
		Vote vote = voteRepository.findById(voteId);
		String nowTime = getNowTime();
		if(!(Long.parseLong(nowTime) >= vote.getLongStartTime() && Long.parseLong(nowTime)<vote.getLongEndTime())){
			result.put("message","해당 투표는 현재 진행중이지 않습니다.");
		}
		else if(voter != null){// 유권자일 경우
			if (voter.getState() !=1){// 처음 투표한 경우.
				// 여기에 Klaytn 소스 넣기.
				// int id  = Integer.parseInt(axiosData.get("select"));
				
				// klaytn.klaytnSend(vote.getAddress(), 1);
				
				
				ExecutorService es = Executors.newCachedThreadPool();
        
				es.execute(() -> {
					try {
						// JSONObject message = klaytn.klaytnSend(vote.getAddress(), Integer.parseInt(axiosData.get("select").toString()));							
						JSONObject message = klaytn.klaytnSend2(vote.getAddress(), Integer.parseInt(axiosData.get("select").toString()),Long.parseLong(nowTime));							
						
						voter.setState(1);
						voter.setHash(message.get("hash").toString());
						voterRepository.saveAndFlush(voter);//투표 완료.
						// result.put("hash",message.get("message").toString());
					} catch (Exception e) {
						System.out.println("클레이튼 오류 발생: 클레이튼으로 선택 사항 전달&처리에서 문제발생.");						
					}
				});				

				result.put("message","투표 참여에 성공하였습니다.");
				// result.put("hash",message.get("hash"));
			}else{// 이미 투표에 참여한 경우
				result.put("errorMessage","이미 참여한 투표입니다.");	
			}
		}else{// 유권자가 아닐 경우	
			result.put("errorMessage","투표할 권한이 없습니다.");
		}

		

		return result;
	}


	@RequestMapping(value={"/result/{voteId}","/result/{voteId}/"}, 
	method=RequestMethod.GET)
	public String showResult(@PathVariable("voteId") int voteId) {

		return "/vote/result";
	}
	@RequestMapping(value={"/result/axios/{voteId}","/result/axios/{voteId}/"}, 
	method=RequestMethod.GET,
	produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	 // 동기 처리      // 반대 개념 : 비동기 처리
	public synchronized JSONArray showResultAxios(@PathVariable("voteId") int voteId) {

		Vote vote = voteRepository.findById(voteId);
		ArrayList<Candidate> candidateList = candidateRepository.findByVoteId(voteId);

        ArrayList names= new ArrayList();
        
        for(Candidate candidate: candidateList){
            names.add(candidate.getName());
        }

		JSONArray json = new JSONArray();
		
		try {
			// JSONObject result = klaytn.load(vote.getAddress());
			JSONObject result = klaytn.load2(vote.getAddress());
			System.out.println("result: " +result);
			json.add(0, result);

			json.add(1,vote.getCount());
			json.add(2,names);
			System.out.println("result json -------:"+json);
			
		} catch (Exception e) {
			System.out.println("클레이튼 오류 발생: 결과 출력 오류");
		}

		return json;
	}
	
}