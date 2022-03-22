/* 작성자 : 조유진
 미션창 : 미션 인증을 할 수 있는 기능
*/
package com.stone.mission.service;

import java.util.List;

import com.stone.mission.common.Mission;

public interface IMission_Manager {
	
	//작성가능한가?
		public  boolean RegistrationPreparation();	
		
		//등록
		public void Enrollment(Mission newMission);
		
		//수집
		public List<Mission> allList();
		
		//Board 조회하다(int 게시물번호);
		Mission View(int missionNo);
		
		//Board 조회수증가하다(int 게시물번호);
		void increaseInViews(int missionNo);
		
		//Prepare for change : 변경준비
		public Mission prepareForChange(int missionNo);
		
		//Permute : 변경하다
		public void  permute(Mission changeMission);
		
		//삭제하다
		public void delete(int missionNo) ;

 
}
