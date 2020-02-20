package com.vote.vote.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.vote.vote.db.dto.Member;
import com.vote.vote.db.dto.Vote;
import com.vote.vote.db.dto.Vote_img;
import com.vote.vote.db.dto.Vote_name;
import com.vote.vote.db.dto.Voter;
import com.vote.vote.klaytn.Klaytn;
import com.vote.vote.repository.MemberJpaRepository;
import com.vote.vote.repository.VoteJpaRepository;
import com.vote.vote.repository.Vote_imgJpaRepository;
import com.vote.vote.repository.Vote_nameJpaRepository;
import com.vote.vote.repository.VoterJpaRepository;
import com.vote.vote.service.StorageService;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
	private Vote_imgJpaRepository vote_imgRepository;

	@Autowired
	private Vote_nameJpaRepository vote_nameRepository;

	@Autowired 
	private VoterJpaRepository voterRepository;


	public Klaytn klaytn = new Klaytn();

	@RequestMapping(value={"","/"}, method=RequestMethod.GET)
	public String index(Model model) {
		// model.addAttribute("votes",voteRepository.findAll());
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
			storageService.store(file[i]);
			fileName.add(StringUtils.cleanPath(file[i].getOriginalFilename()));		
		}

		while(fileName.size() != 6){
			fileName.add("0");
		}
		while(names.size() != 6){
			names.add("0");
		}
		Vote_img img_data = new Vote_img(fileName);
		
		vote_imgRepository.saveAndFlush(img_data);

		Vote_name name_data =  new Vote_name(names);
		
		vote_nameRepository.saveAndFlush(name_data);

		
		Vote data = new Vote();

		data.setTitle(title);
		data.setWriter(user.getName());
		data.setImg(img_data.getId());
		data.setName(name_data.getId());
		data.setCount(count);

		// data.setAddress("testAddress");

		voteRepository.saveAndFlush(data);
		
		ExecutorService es = Executors.newCachedThreadPool();
        
        es.execute(() -> {
            try {
                JSONObject json = klaytn.klaytnDeploy();
				System.out.println(json);
				data.setAddress(json.get("address").toString());
                voteRepository.saveAndFlush(data);
            } catch (Exception e) {
                System.out.println("클레이튼 오류 발생 : 투표 생성");
            }
        });

		

		
		// 모든 회원들에게 투표 권한 줌 
		ArrayList<Member> members = MemberRepository.findAll();
		for (Member member : members) {


			Voter voter = new Voter();
			voter.setVoteId(data.getId());
			voter.setMemberId(member.getMemberId());
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
		
		Vote vote = voteRepository.findById(voteId);
		Vote_img img = vote_imgRepository.findById(vote.getImg());
		Vote_name name = vote_nameRepository.findById(vote.getName());
		
		JSONArray array = new JSONArray();
		
		ArrayList<String> imgs = img.getAllImg();
		ArrayList<String> names = name.getAllName();

		for(int i=0; i<6;i++){
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

		Voter voter = voterRepository.findByVoteIdAndMemberId(voteId, user.getName());
		Vote vote = voteRepository.findById(voteId);

		if(voter != null){// 유권자일 경우
			if (voter.getState() !=1){// 처음 투표한 경우.
				// 여기에 Klaytn 소스 넣기.
				// int id  = Integer.parseInt(axiosData.get("select"));
				
				// klaytn.klaytnSend(vote.getAddress(), 1);
				ExecutorService es = Executors.newCachedThreadPool();
        
				es.execute(() -> {
					try {
						JSONObject message = klaytn.klaytnSend(vote.getAddress(), Integer.parseInt(axiosData.get("select").toString()));							
						voter.setState(1);
						voterRepository.saveAndFlush(voter);//투표 완료.
						result.put("hash",message.get("message").toString());
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
}