package com.vote.vote.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.vote.vote.config.CustomUserDetails;
import com.vote.vote.db.dto.Candidate;
import com.vote.vote.db.dto.Program;
import com.vote.vote.db.dto.Vote;
import com.vote.vote.db.dto.Voter;
import com.vote.vote.db.dto.VoterHash;
import com.vote.vote.klaytn.Klaytn;
import com.vote.vote.repository.CandidateJpaRepository;
import com.vote.vote.repository.CustomVoteRepository;
import com.vote.vote.repository.MemberJpaRepository;
import com.vote.vote.repository.ProgramJpaRepository;
import com.vote.vote.repository.VoteJpaRepository;
import com.vote.vote.repository.VoterHashJpaRepository;
import com.vote.vote.repository.VoterJpaRepository;
import com.vote.vote.service.StorageService;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
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

	@Autowired
	private VoterHashJpaRepository voterHashRepository;


	public Klaytn klaytn = new Klaytn();


	//  투표 메인
	@RequestMapping(value={"","/"}, method=RequestMethod.GET)
	public String index(Model model, Principal user) { 
		// System.out.println("user.getName:"+user.getName());

		// @Nullable Authentication authentication
		// CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		// System.out.println(userDetails.getR_ID());

		return "vote/index";
	}
	
	// 투표 메인 페이지 axios
	@RequestMapping(value={"/axios","/axios/"})
	@ResponseBody
	public JSONArray indexMainAxios(Principal user,Pageable page,
	 int state,
	 int program, @Nullable String text){

		System.out.println("투표 메인페이지");
		String nowTime = getNowTime();
		int type = state;
		System.out.println("state:"+state);
		System.out.println("현재시각"+nowTime);
		System.out.println("프로그램 id"+program);
		// if(state != null)
		// 	type = state;
		String searchText = " ";
		if(text != null){// text 가 있으면..
			searchText = text;
		}
		
		
		// List<Vote> votes = customVoteRepositoy.customFindVotes(nowTime,type,page);
		List<Vote> votes = customVoteRepository.customFindVotes(nowTime,page,type, program,searchText);
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
		@RequestParam(name="title") String title, // 투표 이름
		@RequestParam(name="file") MultipartFile[] file, // 후보자 사진
		@RequestParam(name="name") ArrayList<String> names, // 후보자 이름
		@RequestParam(name="count") int count, // 후보자 수
		@RequestParam(name="startTime") String startTime, // 시작시간
		@RequestParam(name="endTime") String endTime, // 마감 시간
		@RequestParam(name="thumbnail") MultipartFile thumbnail, // 섬네일
		@RequestParam(name="program_id") int programId, // 프로그램 id 
		@RequestParam(name="info") ArrayList<String> infos, // 후보자 설명,
		@RequestParam(name="selectNum") int selectNum,// 선발인원
		@RequestParam(name="voteCanNum") int voteCanNum,// 다중투표 수
		@RequestParam(name="resultShowTime") String resultShowTime, // 결과 공개시간
		@RequestParam(name="show") int showState, //공개 여부 ( 0 : 공개, 1: 비공개)
		Principal user,@Nullable Authentication authentication
	){
		// @Nullable Authentication authentication
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		System.out.println(userDetails);

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
		// String resultShowTime_set = resultShowTime.replaceAll("[^0-9]","");

		data.setTitle(title);
		data.setMemberId(userDetails.getR_ID());
		data.setCount(count);
		data.setStartTime(startTime_set);
		data.setEndTIme(endTime_set);
		data.setThumbnail(thumbnailPath);
		data.setProgramId(programId);
		data.setSelectNum(selectNum);
		data.setVoteCanNum(voteCanNum);
		data.setShowState(showState);
		data.setResultShowTime(resultShowTime);
        voteRepository.saveAndFlush(data);
        
        
        
        for(int i=0; i<count;i++){
            Candidate candidate =  new Candidate();
            candidate.setVoteId(data.getId());
            candidate.setImg(fileName.get(i));
			candidate.setName(names.get(i));
			candidate.setInfo(infos.get(i));
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
				

					//resultShowTime  결과 공개 시간 and showState 를 포함시킨걸로 새로 만들어야 함.

				//스마트 컨트랙트 배포후에 투표 시작시간, 끝 시간 세팅
				JSONObject json2 = klaytn.klaytnSetOptions(json.get("address").toString(), Long.parseLong(startTime_set), Long.parseLong(endTime_set), count);
				System.out.println(json2);

            } catch (Exception e) {
                System.out.println("클레이튼 오류 발생 : 투표 생성");
            }
        });

		

		
		// 모든 회원들에게 투표 권한 줌  // 나중에 로직 변경 가능성 있음.
		// ArrayList<Member> members = MemberRepository.findAll();
		// for (Member member : members) {


		// 	Voter voter = new Voter();
		// 	voter.setVoteId(data.getId());
		// 	voter.setUserid(member.getUserid());
		// 	voter.setState(0);

		// 	voterRepository.saveAndFlush(voter);

		// }
		
		
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
			item.put("info",candidateList.get(i).getInfo());
			array.add(item);
		}
		JSONObject voteInfo = new JSONObject();
		Vote vote = voteRepository.findById(voteId);
		voteInfo.put("title",vote.getTitle());

		Program program = programJpaRepository.findById(vote.getProgramId());

		JSONObject date = new JSONObject();
		date.put("startTime", vote.getLongStartTime());
		date.put("endTime", vote.getLongEndTime());
		date.put("resultShowTime", vote.getLongResultShowTime());

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
		Principal user,@Nullable Authentication authentication
	){

		// @Nullable Authentication authentication
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		

		System.out.println(axiosData.get("select"));  // 사용자가 뽑은 사람의 번호
		JSONObject result = new JSONObject();

		// 처음 투표하는 사람인지 확인하기 위한 voter
		Voter voter = voterRepository.findByVoteIdAndMemberId(voteId, userDetails.getR_ID());

		if(voter== null){// 처음 투표한 경우.
			Voter voter2 = new Voter();
			voter2.setMemberId(userDetails.getR_ID());
			voter2.setState(0);
			voter2.setVoteId(voteId);
			voterRepository.saveAndFlush(voter2);
		}

		// 밑에서 조건문에 사용하고 업데이트 하는 변수 voter3
		Voter voter3 = voterRepository.findByVoteIdAndMemberId(voteId, userDetails.getR_ID());
		

		Vote vote = voteRepository.findById(voteId);
		String nowTime = getNowTime();
		if(!(Long.parseLong(nowTime) >= vote.getLongStartTime() && Long.parseLong(nowTime)<vote.getLongEndTime())){
			result.put("message","해당 투표는 현재 진행중이지 않습니다.");
		}
		else if(vote.getVoteCanNum() > voter3.getState()){// 투표가 가능하면
			
			System.out.println(voter3.getState());
			ExecutorService es = Executors.newCachedThreadPool();
	
			es.execute(() -> {
				try {

					JSONObject message = klaytn.klaytnSend2(vote.getAddress(), Integer.parseInt(axiosData.get("select").toString()),Long.parseLong(nowTime));							
					
					VoterHash voterHash = new VoterHash();
					voterHash.setMemberId(userDetails.getR_ID());
					voterHash.setVoteId(voteId);
					voterHash.setVoterId(voter3.getId());
					voterHash.setHash(message.get("hash").toString());

					voter3.setState(voter.getState()+1);
					
					voterRepository.saveAndFlush(voter3);//투표 완료.
					voterHashRepository.saveAndFlush(voterHash);
					
				} catch (Exception e) {
					System.out.println("클레이튼 오류 발생: 클레이튼으로 선택 사항 전달&처리에서 문제발생.");						
				}
			});				

			result.put("message","투표 참여에 성공하였습니다.");
			// result.put("hash",message.get("hash"));
		}else{// 이미 투표에 참여한 경우
			result.put("errorMessage","이미 투표를 완료했습니다.");	
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
		Long nowTime = Long.parseLong(getNowTime());

		if(vote.getLongEndTime() > nowTime){// 진행중일 때,
			if(vote.getShowState() != 0){ 		// 0 or 1   0 이면 실시간 결과 보여주고, 1 이면 안보여줌
				json.add(0,"");
				json.add(1,"");
				json.add(2,"");
				json.add(3,"");
				json.add(4,"1");
				return json;
			}
				
		}else if(vote.getLongResultShowTime()> nowTime){// 투표 결과 공개시간이 아직 되지 않은 경우.
				json.add(0,"");
				json.add(1,"");
				json.add(2,"");
				json.add(3,"");
				json.add(4,"1");
				return json;
		}

		try {
			JSONObject result = klaytn.load2(vote.getAddress());   // 블록체인 소스 추가해서, 투표 결과 시간 에 맞게.
			System.out.println("result: " +result);
			json.add(0, result.get("result"));
			json.add(1,vote.getCount());
			json.add(2,names);
			json.add(3,result.get("count"));
			// json.add(4,vote.getShowState());
			System.out.println("result json -------:"+json);
			
		} catch (Exception e) {
			System.out.println("클레이튼 오류 발생: 결과 출력 오류");
		}

		return json;
	}
	
}