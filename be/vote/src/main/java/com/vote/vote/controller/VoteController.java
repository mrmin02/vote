package com.vote.vote.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.vote.vote.db.dto.Member;
import com.vote.vote.db.dto.Vote;
import com.vote.vote.db.dto.Voter;
import com.kenai.jffi.Array;
import com.vote.vote.db.dto.Candidate;
import com.vote.vote.klaytn.Klaytn;
import com.vote.vote.repository.CandidateJpaRepository;
import com.vote.vote.repository.MemberJpaRepository;
import com.vote.vote.repository.VoteJpaRepository;
import com.vote.vote.repository.VoterJpaRepository;
import com.vote.vote.service.StorageService;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
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


	public Klaytn klaytn = new Klaytn();

	@RequestMapping(value={"","/"}, method=RequestMethod.GET)
	public String index(Model model, Principal user) {
		// model.addAttribute("votes",voteRepository.findAll());
		
		System.out.println(user.getName());
		return "vote/index";
	}
	@RequestMapping(value={"/axios","/axios/"})
	@ResponseBody
	public JSONArray indexAxios(Principal user){

		ArrayList<Vote> votes = voteRepository.findAll();

		JSONArray json = new JSONArray();

		// JSONArray data = new JSONArray();
		for( Vote vote : votes){
			JSONObject voteData = new JSONObject();
			voteData.put("title", vote.getTitle());
			voteData.put("id",vote.getId());
			// data.add(voteData);
			json.add(voteData);
		}
		

		// JSONObject userJson = new JSONObject();
		// userJson.put("user",user.getName());
		
		// json.add(userJson);
		
		return json;
	}
	

    @RequestMapping(value={"/create","/create/"})
	public String create(Model model) {
		return "vote/create";
	}
	
	
	@RequestMapping(value={"","/"}, method=RequestMethod.POST)
	public String store(
		@RequestParam(name="title") String title,
		@RequestParam(name="file") MultipartFile[] file,
		@RequestParam(name="name") ArrayList<String> names,
		// @RequestParam(name="content") String[] content,
		@RequestParam(name="count") int count,
		Principal user
	){
		
		ArrayList<String> fileName = new ArrayList<String>();

		for(int i=0;i<file.length;i++){
			storageService.store(file[i]);   // 파일 저장
			fileName.add(StringUtils.cleanPath(file[i].getOriginalFilename()));		// 파일 이름을 배열에 저장
		}

		Vote data = new Vote();

		data.setTitle(title);
		data.setWriter(user.getName());
		data.setCount(count);

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
				JSONObject json = klaytn.klaytnDeploy2();
				
				System.out.println(json);
				data.setAddress(json.get("address").toString());

				voteRepository.saveAndFlush(data);
				
				JSONObject json2 = klaytn.klaytnSetOptions(json.get("address").toString(), 202004280000L, 202004290000L, count);
				System.out.println(json2);

            } catch (Exception e) {
                System.out.println("클레이튼 오류 발생 : 투표 생성");
            }
        });

		

		
		// 모든 회원들에게 투표 권한 줌 
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
        
        ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> imgs = new ArrayList<String>();
        
        for(int i=0; i<candidateList.size();i++){
            names.add(candidateList.get(i).getName());
            imgs.add(candidateList.get(i).getImg());
        }

		for(int i=0; i<names.size();i++){
			JSONObject item = new JSONObject();
			item.put("name", names.get(i));
			item.put("img",imgs.get(i));
			array.add(item);
		}
			
		return array;
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

		if(voter != null){// 유권자일 경우
			if (voter.getState() !=1){// 처음 투표한 경우.
				// 여기에 Klaytn 소스 넣기.
				// int id  = Integer.parseInt(axiosData.get("select"));
				
				// klaytn.klaytnSend(vote.getAddress(), 1);
				ExecutorService es = Executors.newCachedThreadPool();
        
				es.execute(() -> {
					try {
						// JSONObject message = klaytn.klaytnSend(vote.getAddress(), Integer.parseInt(axiosData.get("select").toString()));							
						JSONObject message = klaytn.klaytnSend2(vote.getAddress(), Integer.parseInt(axiosData.get("select").toString()),202004280500L);							
						
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