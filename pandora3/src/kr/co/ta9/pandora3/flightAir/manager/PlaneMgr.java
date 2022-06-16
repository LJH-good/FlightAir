package kr.co.ta9.pandora3.flightAir.manager;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ta9.pandora3.app.servlet.ParameterMap;
import kr.co.ta9.pandora3.flightAir.dao.PlaneDao;
import kr.co.ta9.pandora3.flightAir.dto.PlaneDto;

/**
* <pre>
* 1. 클래스명 : PlaneMgr
* 2. 설명 : 항공기 관리 서비스
* 3. 작성일 : 2022-05-25
* 4. 작성자 : LJH
* </pre>
*/
@Service
public class PlaneMgr {

	@Autowired
	private PlaneDao planeDao;

	/**
	 * 항공기 목록 조회
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject planeList(ParameterMap parameterMap) throws Exception {
		return planeDao.planeList(parameterMap);
	}

	/**
	 * 항공기 행 추가 / 수정
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public void planeInsert(ParameterMap parameterMap) throws Exception {
		List<PlaneDto> insertList = new ArrayList<PlaneDto>();
		List<PlaneDto> updateList = new ArrayList<PlaneDto>();
		List<PlaneDto> deleteList = new ArrayList<PlaneDto>();
		parameterMap.populates(PlaneDto.class, insertList, updateList, deleteList, "planeData");

		PlaneDto[] insert = insertList.toArray(new PlaneDto[0]);
		PlaneDto[] update = updateList.toArray(new PlaneDto[0]);

		planeDao.insertMany("Plane.insertPlane", insert);
		planeDao.updateMany("Plane.updatePlane", update);
	}

	/**
	 * 항공기 행 삭제
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public void planeDelete(ParameterMap parameterMap) throws Exception {
		List<PlaneDto> insertList = new ArrayList<PlaneDto>();
		List<PlaneDto> updateList = new ArrayList<PlaneDto>();
		List<PlaneDto> deleteList = new ArrayList<PlaneDto>();
		parameterMap.populates(PlaneDto.class, insertList, updateList, deleteList, "planeData");

		PlaneDto[] delete = deleteList.toArray(new PlaneDto[0]);

		planeDao.deleteMany("Plane.planeDelete", delete);
		
	}

	/**
	 * 항공기 번호 가져오기 - 중복검사
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject getPlaneNum(ParameterMap parameterMap) throws Exception {
		return planeDao.getPlaneNum(parameterMap);
	}
}
