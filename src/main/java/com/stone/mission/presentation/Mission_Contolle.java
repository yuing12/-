/* 작성자 : 조유진
 미션창 : 미션 인증을 할 수 있는 기능
*/
package com.stone.mission.presentation;

import java.util.List; 

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.stone.member.common.Member;
import com.stone.mission.common.Mission;
import com.stone.mission.service.IMission_Manager;

@Controller
public class Mission_Contolle {
	
	@Autowired IMission_Manager mission_Manager;
	
	//수집 //미션창 보이게 하는 아이+로그인인지 로그아웃인지 보내기
	@GetMapping("/missions")
	 public ModelAndView listOutput(HttpSession session) {
		List<Mission> lists = mission_Manager.allList();
		
		 ModelAndView mv=new ModelAndView();
		 Boolean isLogin = false;
		  Member 회원 = (Member)session.getAttribute("user");
		  if(회원 != null) {
			  isLogin = true;
			  mv.addObject("user", 회원);
		  }

		  mv.addObject("isLogin", isLogin);
		  mv.setViewName("main3/mission/sub_mission"); 
		  mv.addObject("missions", lists);
		  return mv;
		
	}
	
	
	// 등록준비
	@GetMapping("/mission_clear")
	public String writeAnotice() {
		if(mission_Manager.RegistrationPreparation()) {
			return "main3/mission/sub_mission_clear";
		}
		return null;
		
		
	}
	
	//등록
	@PostMapping("/mission_clear")
	public ModelAndView itIsregistered(Mission newMission, HttpSession session) {
		
		int 로그인한회원의번호=(int)session.getAttribute("user_no");
		
		Member 작성한회원 = new Member();
		작성한회원.setNo(로그인한회원의번호);
		newMission.setWriter(작성한회원);
		
		//1.데이터베이스에 등록
		mission_Manager.Enrollment(newMission);
		
		 ModelAndView mv=new ModelAndView();
		 
		 mv.setViewName("main3/mission/sub_mission_clear_Completed"); 
		 
		return mv;
		
		
	}
	
	//Board 조회하다(int 게시물번호);
	  @GetMapping("/mission/{missionNo}") 
	 public ModelAndView detailedOutput(@PathVariable int missionNo, HttpSession session) {
		  Mission found = mission_Manager.View(missionNo); 
		  mission_Manager.increaseInViews(missionNo);
		  ModelAndView mv=new ModelAndView(); 
		  mv.setViewName("main3/mission/sub_mission_clear상세창"); 
		  mv.addObject("mission",found);
		  
			Boolean 로그인회원와게시물작성자가동일인물인가=false;//일단 아니다
		    if(session!=null) {
		    	Integer 회원번호=(Integer)session.getAttribute("user_no");
		    	 if(회원번호!=null) { 
		    		 if(회원번호==found.getWriter().getNo()) {
		    			 로그인회원와게시물작성자가동일인물인가=true;
		    		 }else{
		    			 로그인회원와게시물작성자가동일인물인가=false;
		    		 }
		    	 }
		    }
		    System.out.println(로그인회원와게시물작성자가동일인물인가);
			mv.addObject("isWriter", 로그인회원와게시물작성자가동일인물인가);
	      return mv;
	  
	  }
	
	  //변경준비
	  @GetMapping("/mission_update/{missionNo}")
		public ModelAndView prepareForChange(@PathVariable int missionNo,HttpSession session) {
		  Mission found=mission_Manager.prepareForChange(missionNo);
			System.out.println(missionNo);
			ModelAndView mv=new ModelAndView();
			mv.setViewName("main3/mission/sub_mission_Change");
			mv.addObject("missions",found);
			
			return mv;
		}
	
	  
	  //변경
	  @PostMapping("/mission_update/update") 
	  ModelAndView change(Mission changeMiision) {
		  mission_Manager.permute(changeMiision);
		  
		  System.out.println(changeMiision.getTitle());
		  System.out.println(changeMiision.getContents());
	  
	  ModelAndView mv =new ModelAndView();
	  mv.setViewName("main3/mission/sub_mission_Change_Completed"); 
	  return mv;
	 
	  }
	 
	  @GetMapping("/mission_delete/{missionNo}") 
	  ModelAndView delete(@PathVariable int missionNo, HttpSession session){ 
		  mission_Manager.delete(missionNo);
	  
	  ModelAndView mv =new ModelAndView(); 
	  mv.setViewName("main3/mission/sub_mission_Delete");
	 return mv; 
	 }
}

