/* 작성자 : 조유진
 미션창 : 미션 인증을 할 수 있는 기능
*/
package com.stone.mission.dataservice;

import java.util.List; 

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.stone.mission.common.Mission;

@Mapper
public interface IMissionMapper {
						
	//저장
	@Insert("insert into mission(title, contents,profile, writer) values(#{title},#{contents},#{profile},#{writer.no})")
	void save(Mission newMission);

	
	//수집
	@Select("select * from mission ORDER BY date DESC")
	@Results(value= {
			@Result(property="no",column="no"),
			@Result(property="title", column="title"),
			@Result(property="contents", column ="contents"),
			@Result(property="writer", column="writer",	one=@One(select="com.stone.member.dataservice.IMemberMapper.회원번호를찾다")),
			@Result(property="views", column="views"),
			@Result(property="date", column="date")})
	List<Mission> collectAllNotices();
	
	
	
	//조회
	@Select("select * from mission where no=#{no}")
	
	//("select no,title,contents,writer,views from board where no=?");
	@Results(value= {
			@Result(property="no",column="no"),
			@Result(property="title",column="title"),
			@Result(property="contents",column="contents"),
			@Result(property="views",column="views"),
			@Result(property="writer", column="writer", one=@One(select="com.stone.member.dataservice.IMemberMapper.회원번호를찾다")),
			@Result(property="profile",column="profile"),
			@Result(property="date", column="date"),
		})
	Mission view(int missionNo);
	
	//조회수증가
	@Update("update mission set views=views+1 where no=#{no}")
	void increaseInViews(@Param("no") int missionNo);
	
	//글번호찾기
	@Select("select * from mission where no=#{no}")
	@Results(value= {
			@Result(property="no",column="no"),
			@Result(property="title",column="title"),
			@Result(property="contents",column="contents"),
			@Result(property="views",column="views"),
            @Result(property="writer", column="writer",
            one=@One(select="com.stone.member.dataservice.IMemberMapper.회원번호를찾다")),
			@Result(property="date", column="date"),
	})
	Mission findAnumber(int missionNo);
	
	//글변경
	@Update("update mission set title=#{title},contents=#{contents},profile=#{profile} where no=#{no}")
	//("update board set title=?,contents=? where no=?");
	 void permute(Mission changeMission);

	//삭제
	@Delete("delete from mission where no=#{no}")
	//("delete from board where no=?")
	 void delete(int missionNo);
}
