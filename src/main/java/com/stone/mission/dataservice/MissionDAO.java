/* 작성자 : 조유진
 미션창 : 미션 인증을 할 수 있는 기능
*/
package com.stone.mission.dataservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.cj.Session;
import com.mysql.cj.x.protobuf.MysqlxCrud.ViewSqlSecurity;
import com.stone.board.common.Board;
import com.stone.board.common.Paging;
import com.stone.mission.common.Mission;
import com.stone.qnaboard.common.Qna;
import com.stone.qnaboard.dataservice.IQnaMapper;

@Repository
public class MissionDAO implements IMissionDAO {
	
	@Autowired IMissionMapper missionMapper;

	//저장
	@Transactional
	@Override
	public void save(Mission newMission) {
		missionMapper.save(newMission);
		
	}
	//수집
	@Transactional
	@Override
	public List<Mission> collectAllNotices() {
		return missionMapper.collectAllNotices();
	}
	//조회
	@Transactional
	@Override
	public Mission view(int missionNo) {
		return missionMapper.view(missionNo);
	}
	//조회수증가
	@Transactional
	@Override
	public void increaseInViews(int missionNo) {
		 missionMapper.increaseInViews(missionNo);
	}

	//번호를찾다
	@Transactional
	@Override
	public Mission findAnumber(int missionNo) {
		return missionMapper.findAnumber(missionNo);
	}

	//수정
	@Transactional
	@Override
	public void permute(Mission changeMiision) {
		missionMapper.permute(changeMiision);
		
	}

	//삭제
	@Transactional
	@Override
	public void delete(int missionNo) {
		missionMapper.delete(missionNo);
		
	}






}

