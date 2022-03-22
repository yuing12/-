
/* 작성자 : 조유진
 미션창 : 미션 인증을 할 수 있는 기능
*/
package com.stone.mission.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stone.board.common.Board;
import com.stone.board.common.Paging;
import com.stone.board.dataservice.I게시물DAO;
import com.stone.mission.common.Mission;
import com.stone.mission.dataservice.IMissionDAO;

@Service
public class Mission_Manager implements IMission_Manager {
	@Autowired IMissionDAO missionDAO;

	//작성가능한가?
	@Override
	public boolean RegistrationPreparation() {
		return true;
	}

	//등록
	@Override
	public void Enrollment(Mission newMission) {
		missionDAO.save(newMission);
		
	}


	//수집
	@Override
	public List<Mission> allList() {
		return missionDAO.collectAllNotices();
	}

	//Board 조회하다(int 게시물번호);
	@Override
	public Mission View(int missionNo) {
		return missionDAO.view(missionNo);
	}

	//Board 조회수증가하다(int 게시물번호)
	@Override
	public void increaseInViews(int missionNo) {
		 missionDAO.increaseInViews(missionNo);
	}

	//Prepare for change : 변경준비
	@Override
	public Mission prepareForChange(int missionNo) {
		return missionDAO.findAnumber(missionNo);
	}

	//Permute : 변경하다
	@Override
	public void permute(Mission changeMission) {
		missionDAO.permute(changeMission);
		
	}

	//삭제하다
	@Override
	public void delete(int missionNo) {
		missionDAO.delete(missionNo);
		
	}
	


	

	


	


}
