package com.company.demo.service;

import java.util.List;

import com.company.dto.QnADto;
import com.company.dto.UserDto;
/**
* <pre>
* 1. 클래스명 : UserService
* 2. 설명 : 사용자 서비스
* 3. 작성일 : 2022-04-30
* 4. 작성자 : LJH
* </pre>
*/
public interface UserService {

	/**
	 * 아이디 중복 검사 
	 * 
	 * @param email 
	 * @return UserDto
	 */
	public UserDto idCheck(String email);
	
	/**
	 * 회원 가입 
	 * 
	 * @param dto 
	 * @return int
	 */
	public int insertUser(UserDto dto);
	
	/**
	 * 로그인 
	 * 
	 * @param dto 
	 * @return UserDto
	 */
	public UserDto login(UserDto dto);
	
	/**
	 * 아이디 찾기 
	 * 
	 * @param dto 
	 * @return List<UserDto>
	 */
	public List<UserDto> FindId(UserDto dto);
	
	/**
	 * 비밀번호 찾기 
	 * 
	 * @param dto 
	 * @return UserDto
	 */
	public UserDto FindPassword(UserDto dto);
	
	/**
	 * 비밀번호 변경
	 *  
	 * @param dto 
	 * @return int
	 */
	public int changepassword(UserDto dto);
	
	/**
	 * 회원 정보 수정
	 *  
	 * @param dto 
	 * @return int
	 */
	public int updateinfo(UserDto dto);

	/**
	 * 마이페이지 로그인
	 *  
	 * @param email 
	 * @return UserDto
	 */
	public UserDto mypagelogin(String email);

	/**
	 * 회원 정보 조회 
	 * 
	 * @param email 
	 * @return UserDto
	 */
	public UserDto userinfo(String email);

	/**
	 * 회원 탈퇴 
	 * 
	 * @param String 
	 * @return UserDto
	 */
	public int deleteuser(UserDto dto);

	/**
	 * 기존 비밀번호 확인
	 *  
	 * @param email 
	 * @return UserDto
	 */
	public UserDto oripass(String email);

	/**
	 * 비밀번호 변경 
	 * 
	 * @param dto 
	 * @return int
	 */
	public int updatepassword(UserDto dto);

	/**
	 * 회원 삭제 시 진행 중인 예약 확인
	 * 
	 * @param dto 
	 * @return int
	 */
	public int deletereservation(UserDto dto);

	/**
	 * 상담 글 등록
	 *  
	 * @param dto 
	 * @return int
	 */
	public int insertqna(QnADto dto);

	/**
	 * 상담 글 목록
	 *  
	 * @param dto 
	 * @return List<QnADto>
	 */
	public List<QnADto> qnanumber(QnADto dto);

	/**
	 * 상담 글 상세
	 *  
	 * @param dto 
	 * @return QnADto
	 */
	public QnADto qnadetail(QnADto dto);

	/**
	 * 상담 글 삭제 
	 * @param dto 
	 * @return int
	 */
	public int deleteqna(QnADto dto);

}
