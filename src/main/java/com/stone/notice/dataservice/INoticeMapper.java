/* 
   공지사항 Mapper
   특징 : 관리자만 접근할 수 있다.*/

package com.stone.notice.dataservice;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.stone.notice.common.Notice;


@Mapper
public interface INoticeMapper {
	
	//저장
	@Insert("insert into notice(title,contents) values(#{title},#{contents})")
	//("insert into board(title,contents,writer) values(?,?,?)");
	 void save(Notice newNotice); 
	
	//수집
	@Select("select * from notice ORDER BY date DESC")
	@Results(value= {
			@Result(property="no",column="no"),
			@Result(property="title", column="title"),
			@Result(property="contents", column ="contents"),
//			@Result(property="writer", column="writer",
//			one=@One(select="com.stone.member.dataservice.IMemberMapper.찾다회원번호")),
			@Result(property="views", column="views"),
			@Result(property="date", column="date")})
	List<Notice> collectAllNotices();
	
	//조회
	@Select("select * from notice where no=#{no} ")
	//("select no,title,contents,writer,views from board where no=?");
	@Results(value= {
			@Result(property="no",column="no"),
			@Result(property="title",column="title"),
			@Result(property="contents",column="contents"),
//			@Result(property="writer", column="writer",
//			one=@One(select="com.stone.member.dataservice.IMemberMapper.찾다회원번호")),
//			@Result(property="views",column="views"),
			@Result(property="date", column="date")})
	Notice view(int noticeNo);
	
	//조회수증가
	@Update("update notice set views=views+1 where no=#{no}")
	//("update board set views=views+1 where no=?");
	Notice increaseInViews(int noticeNo);
	
	//글번호찾기
	@Select("select * from notice where no=#{no}")
	@Results(value= {
			@Result(property="no",column="no"),
			@Result(property="title",column="title"),
			@Result(property="contents",column="contents"),
//            @Result(property="writer", column="writer",
//            one=@One(select="com.stone.member.dataservice.IMemberMapper.찾다회원번호")),
//			@Result(property="views",column="views"),
			@Result(property="date", column="date")})
	Notice findAnumber(int noticeNo);
	
	//글변경
	@Update("update notice set title=#{title},contents=#{contents} where no=#{no}")
	//("update board set title=?,contents=?,where no=?");
	void permute(Notice changeNotice);

	//삭제
	@Delete("delete from notice where no=#{no}")
	//("delete from board where no=?")
    void delete(int noticeNo);
	


}
