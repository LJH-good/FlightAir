package kr.co.ta9.pandora3.flightAir.dao;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import kr.co.ta9.pandora3.app.repository.BaseDao;
import kr.co.ta9.pandora3.app.servlet.ParameterMap;
/**
* <pre>
* 1. 클래스명 : QnADao
* 2. 설명 : 상담 글 관리 Dao
* 3. 작성일 : 2022-06-07
* 4. 작성자 : LJH
* </pre>
*/
@Repository
public class QnADao extends BaseDao{

	/**
	 * 상담글 목록 조회 
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject selectQnAList(ParameterMap parameterMap) throws Exception {
		return queryForGrid("QnA.selectQnAList", parameterMap);
	}

	/**
	 * 선택글 조회
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject answerQnAList(ParameterMap parameterMap) throws Exception {
		return queryForGrid("QnA.answerQnAList", parameterMap);
	}
}
