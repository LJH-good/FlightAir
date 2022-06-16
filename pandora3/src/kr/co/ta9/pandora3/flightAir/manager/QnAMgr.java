package kr.co.ta9.pandora3.flightAir.manager;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ta9.pandora3.app.servlet.ParameterMap;
import kr.co.ta9.pandora3.flightAir.dao.QnADao;
import kr.co.ta9.pandora3.flightAir.dto.QnADto;

/**
* <pre>
* 1. 클래스명 : QnAMgr
* 2. 설명 : 상담 글 관리 서비스
* 3. 작성일 : 2022-06-07
* 4. 작성자 : LJH
* </pre>
*/
@Service
public class QnAMgr {
	
	@Autowired
	private QnADao qnaDao;
	
	/**
	 * 상담 글 조회  
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject selectQnAList(ParameterMap parameterMap) throws Exception {
		return qnaDao.selectQnAList(parameterMap);
	}

	/**
	 * 선택 글 조회  
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject answerQnAList(ParameterMap parameterMap) throws Exception {
		return qnaDao.answerQnAList(parameterMap);
	}

	/**
	 * 답변 작성  
	 * @param parameterMap
	 * @return JSONObject
	 * @throws Exception
	 */
	public void qnaAnswer(ParameterMap parameterMap) throws Exception {
		List<QnADto> insertList = new ArrayList<QnADto>();
		List<QnADto> updateList = new ArrayList<QnADto>();
		List<QnADto> deleteList = new ArrayList<QnADto>();
		parameterMap.populates(QnADto.class, insertList, updateList, deleteList, "qnaData");

		QnADto[] update = updateList.toArray(new QnADto[0]);

		qnaDao.updateMany("QnA.qnaAnswer", update);
	}

}
