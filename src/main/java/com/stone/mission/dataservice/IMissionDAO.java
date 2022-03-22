/* 작성자 : 조유진
 미션창 : 미션 인증을 할 수 있는 기능
*/
package com.stone.mission.dataservice;

import java.util.List; 

import com.stone.mission.common.Mission;

public interface IMissionDAO {
	//저장
	void save(Mission newMission);
	
	//모든 미션 수집
	public List<Mission> collectAllNotices();
	
	//조회수
	Mission view(int missionNo);
	
	//조회수증가하다
	void increaseInViews(int missionNo);
	
	//미션 클리어 글 중 보고 싶은 글 찾기
	Mission findAnumber(int missionNo);
	
	//변경
	public void permute(Mission changeMission);
	//삭제
	public void delete(int missionNo);
	

	
}



