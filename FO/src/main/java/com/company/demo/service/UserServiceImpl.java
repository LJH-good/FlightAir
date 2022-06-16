package com.company.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.demo.dao.UserDao;
import com.company.dto.QnADto;
import com.company.dto.UserDto;
/**
* <pre>
* 1. 클래스명 : UserServiceImpl
* 2. 설명 : 사용자 서비스
* 3. 작성일 : 2022-04-30
* 4. 작성자 : LJH
* </pre>
*/
@Service
public class UserServiceImpl implements UserService{
	@Autowired UserDao dao;
	
	/**
	 * 아이디 중복 검사 
	 * 
	 * @param email 
	 * @return UserDto
	 */
	@Override
	public UserDto idCheck(String email) {
		return dao.idCheck(email);
	}
	
	/**
	 * 회원 가입 
	 * 
	 * @param dto 
	 * @return int
	 */
	@Override
	public int insertUser(UserDto dto) {
		return dao.insertUser(dto);
	}
	
	/**
	 * 로그인 
	 * 
	 * @param dto 
	 * @return UserDto
	 */
	@Override
	public UserDto login(UserDto dto) {
		return dao.login(dto);
	}
	
	/**
	 * 아이디 찾기 
	 * 
	 * @param dto 
	 * @return List<UserDto>
	 */
	@Override
	public List<UserDto> FindId(UserDto dto) {
		return dao.FindId(dto);
	}
	
	/**
	 * 비밀번호 찾기 
	 * 
	 * @param dto 
	 * @return UserDto
	 */
	@Override
	public UserDto FindPassword(UserDto dto) {
		return dao.FindPassword(dto);
	}
	
	/**
	 * 비밀번호 변경
	 *  
	 * @param dto 
	 * @return int
	 */
	@Override
	public int changepassword(UserDto dto) {
		return dao.changepassword(dto);
	}
	
	/**
	 * 회원 정보 수정
	 *  
	 * @param dto 
	 * @return int
	 */
	@Override
	public int updateinfo(UserDto dto) {
		return dao.updateinfo(dto);
	}

	/**
	 * 마이페이지 로그인
	 *  
	 * @param email 
	 * @return UserDto
	 */
	@Override
	public UserDto mypagelogin(String email) {
		return dao.mypagelogin(email);
	}

	/**
	 * 회원 정보 조회 
	 * 
	 * @param email 
	 * @return UserDto
	 */
	@Override
	public UserDto userinfo(String email) {
		return dao.userinfo(email);
	}

	/**
	 * 회원 탈퇴 
	 * 
	 * @param String 
	 * @return UserDto
	 */
	@Override
	public int deleteuser(UserDto dto) {
		return dao.deleteuser(dto);
	}

	/**
	 * 기존 비밀번호 확인
	 *  
	 * @param email 
	 * @return UserDto
	 */
	@Override
	public UserDto oripass(String email) {
		return dao.oripass(email);
	}

	/**
	 * 비밀번호 변경 
	 * 
	 * @param dto 
	 * @return int
	 */
	@Override
	public int updatepassword(UserDto dto) {
		return dao.updatepassword(dto);
	}

	/**
	 * 회원 삭제 시 진행 중인 예약 확인
	 * 
	 * @param dto 
	 * @return int
	 */
	@Override
	public int deletereservation(UserDto dto) {
		return dao.deletereservation(dto);
	}

	/**
	 * 상담 글 등록
	 *  
	 * @param dto 
	 * @return int
	 */
	@Override
	public int insertqna(QnADto dto) {
		return dao.insertqna(dto);
	}

	/**
	 * 상담 글 목록
	 *  
	 * @param dto 
	 * @return List<QnADto>
	 */
	@Override
	public List<QnADto> qnanumber(QnADto dto) {
		return dao.qnanumber(dto);
	}

	/**
	 * 상담 글 상세
	 *  
	 * @param dto 
	 * @return QnADto
	 */
	@Override
	public QnADto qnadetail(QnADto dto) {
		return dao.qnadetail(dto);
	}

	/**
	 * 상담 글 삭제 
	 * @param dto 
	 * @return int
	 */
	@Override
	public int deleteqna(QnADto dto) {
		return dao.deleteqna(dto);
	}
}
