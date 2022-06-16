package kr.co.ta9.pandora3.flightAir.manager;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ta9.pandora3.app.servlet.ParameterMap;
import kr.co.ta9.pandora3.flightAir.dao.UserDao;
import kr.co.ta9.pandora3.flightAir.dto.UserDto;

/**
* <pre>
* 1. 클래스명 : UserMgr
* 2. 설명 : 회원 관리 서비스
* 3. 작성일 : 2022-05-20
* 4. 작성자 : LJH
* </pre>
*/
@Service
public class UserMgr {

	@Autowired
	private UserDao userDao;

	/**
	 * 회원 목록 조회
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject selectUserList(ParameterMap parameterMap) throws Exception {
		return userDao.selectUserList(parameterMap);
	}
	
	/**
	 * 회원 행 삭제 
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public void deleteUser(ParameterMap parameterMap) throws Exception {
		List<UserDto> insertList = new ArrayList<UserDto>();
		List<UserDto> updateList = new ArrayList<UserDto>();
		List<UserDto> deleteList = new ArrayList<UserDto>();
		parameterMap.populates(UserDto.class, insertList, updateList, deleteList, "userData");

		UserDto[] delete = deleteList.toArray(new UserDto[0]);

		userDao.deleteMany("User.userDelete", delete);
	}

	/**
	 * 회원 삭제 시 예약 내역 확인
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject reservationCheck(ParameterMap parameterMap) throws Exception {
		return userDao.reservationCheck(parameterMap);
	}
	
}