package kr.co.ta9.pandora3.flightAir.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.ta9.pandora3.app.servlet.ParameterMap;
import kr.co.ta9.pandora3.app.servlet.controller.CommonActionController;
import kr.co.ta9.pandora3.common.conf.Const;
import kr.co.ta9.pandora3.common.util.ResponseUtil;
import kr.co.ta9.pandora3.flightAir.manager.FlightMgr;

/**
* <pre>
* 1. 클래스명 : FlightController
* 2. 설명 : 스케줄 관리 컨트롤러
* 3. 작성일 : 2022-05-23
* 4. 작성자 : LJH
* </pre>
*/
@Controller
public class FlightController extends CommonActionController{
	@Autowired
	private FlightMgr flightmgr;
	
	/**
	 * 스케줄 목록 조회
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/flight/selectScheduleList", method = RequestMethod.POST)
	public void selectUserList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// parameterMap 선언
		ParameterMap parameterMap = getParameterGridMap(request, response);
		// result 선언
		String result = Const.BOOLEAN_SUCCESS;
		// json 선언
		JSONObject json = new JSONObject();
		try {
			// 스케줄 목록 조회
			json = flightmgr.selectScheduleList(parameterMap);

		} catch (Exception e) {
			// Exception 일 경우
			result = Const.BOOLEAN_FAIL;
		}
		// json에 결과 담기
		json.put("RESULT", result);
		ResponseUtil.write(response, json.toJSONString());
	}
	
	/**
	 * 스케줄 예약 조회
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/flight/scheduleReserveList", method = RequestMethod.POST)
	public void scheduleReserveList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// parameterMap 선언
		ParameterMap parameterMap = getParameterGridMap(request, response);
		// result 선언
		String result = Const.BOOLEAN_SUCCESS;
		// json 선언
		JSONObject json = new JSONObject();
		try {
			// 스케줄 예약 조회
			json = flightmgr.scheduleReserveList(parameterMap);

		} catch (Exception e) {
			// Exception 일 경우
			result = Const.BOOLEAN_FAIL;
		}
		// json에 결과 담기
		json.put("RESULT", result);
		ResponseUtil.write(response, json.toJSONString());
	}
	
	/**
	 * 가격 목록 조회
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/flight/priceList", method = RequestMethod.POST)
	public void priceList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// parameterMap 선언
		ParameterMap parameterMap = getParameterGridMap(request, response);
		// result 선언
		String result = Const.BOOLEAN_SUCCESS;
		// json 선언
		JSONObject json = new JSONObject();
		try {
			// 가격 목록 조회
			json = flightmgr.priceList(parameterMap);

		} catch (Exception e) {
			// Exception 일 경우
			result = Const.BOOLEAN_FAIL;
		}
		// json에 결과 담기
		json.put("RESULT", result);
		ResponseUtil.write(response, json.toJSONString());
	}
	
	/**
	 * 가격 행 추가
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/flight/priceInsert", method = RequestMethod.POST)
	public void priceInsert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// parameterMap 선언
		ParameterMap parameterMap = getParameterMap(request, response);
		// result 선언
		String result = Const.BOOLEAN_SUCCESS;
		// json 선언
		JSONObject json = new JSONObject();
		try {
			// 가격 행 추가
			flightmgr.priceInsert(parameterMap);

		} catch (Exception e) {
			// Exception 일 경우
			result = Const.BOOLEAN_FAIL;
			// json에 MSG 담기
			json.put("MSG", parameterMap.getValue("MSG"));
		}
		// json에 결과 담기
		json.put("RESULT", result);
		ResponseUtil.write(response, json.toJSONString());
	}
	
	/**
	 * 가격 번호 가져오기 - 입력 시 자동생성
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/flight/pricenumInsert", method = RequestMethod.POST)
	public void pricenumInsert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// parameterMap 선언
		ParameterMap parameterMap = getParameterGridMap(request, response);
		// result 선언
		String result = Const.BOOLEAN_SUCCESS;
		// json 선언
		JSONObject json = new JSONObject();
		try {
			// 가격 번호 가져오기
			json = flightmgr.pricenumInsert(parameterMap);

		} catch (Exception e) {
			// Exception 일 경우
			result = Const.BOOLEAN_FAIL;
		}
		// json에 결과 담기
		json.put("RESULT", result);
		ResponseUtil.write(response, json.toJSONString());
	}

	/**
	 * 가격 행 삭제
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/flight/priceDelete", method = RequestMethod.POST)
	public void priceDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// parameterMap 선언
		ParameterMap parameterMap = getParameterMap(request, response);
		// result 선언
		String result = Const.BOOLEAN_SUCCESS;
		// json 선언
		JSONObject json = new JSONObject();
		try {
			// 가격 행 삭제
			flightmgr.priceDelete(parameterMap);
		} catch (Exception e) {
			// Exception 일 경우
			result = Const.BOOLEAN_FAIL;
			// json에 MSG 담기
			json.put("MSG", parameterMap.getValue("MSG"));
		}
		// json에 결과 담기
		json.put("RESULT", result);
		ResponseUtil.write(response, json.toJSONString());
	}
	
	/**
	 * 스케줄 번호 가져오기 - select option
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/flight/getScheduleNum", method = RequestMethod.POST)
	public void getScheduleNum(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// parameterMap 선언
		ParameterMap parameterMap = getParameterGridMap(request, response);
		// result 선언
		String result = Const.BOOLEAN_SUCCESS;
		// json 선언
		JSONObject json = new JSONObject();
		ArrayList<HashMap<String, String>> list = null;
		try {
			// 스케줄 번호 가져오기
			json = flightmgr.getScheduleNum(parameterMap);
		} catch (Exception e) {
			// Exception 일 경우
			result = Const.BOOLEAN_FAIL;
		}
		
		list = (ArrayList<HashMap<String, String>>) json.get("rows");
		StringBuilder buf1 = new StringBuilder("");
		
		// schedule_num 파싱
		for (int i = 0; i < list.size(); i++) {
			if (i > 0) {
				buf1.append(";");
			}
			buf1.append(list.get(i).get("SCHEDULE_NUM")).append(":").append(list.get(i).get("SCHEDULE_NUM"));
		}
		
		// json에 결과 담기
		json.put("RESULT", result);
		json.put("SCHEDULE_NUMS", buf1.toString());
		ResponseUtil.write(response, json.toJSONString());
	}
	
	/**
	 * 항공기 번호 가져오기 - select option
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/flight/getSelectPlane", method = RequestMethod.POST)
	public void getSelectPlane(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// parameterMap 선언
		ParameterMap parameterMap = getParameterGridMap(request, response);
		// result 선언
		String result = Const.BOOLEAN_SUCCESS;
		// json 선언
		JSONObject json = new JSONObject();
		ArrayList<HashMap<String, String>> list = null;
		try {
			// 항공기 번호 가져오기
			json = flightmgr.getSelectPlane(parameterMap);
		} catch (Exception e) {
			// Exception 일 경우
			result = Const.BOOLEAN_FAIL;
		}
		
		list = (ArrayList<HashMap<String, String>>) json.get("rows");
		StringBuilder buf = new StringBuilder("");
		
		// plane_num 중복 거르기
		TreeSet<String> set = new TreeSet<String>();
		for (int i = 0; i < list.size(); i++) {
			set.add(list.get(i).get("PLANE_NUM"));
		}
		
		// plane_num 파싱
		int cnt = 0;
		Iterator<String> iter = set.iterator();
		while(iter.hasNext()) {
			String plane_num = iter.next();
			if (cnt > 0) {
				buf.append(";");
			}
			buf.append(plane_num).append(":").append(plane_num);
			cnt ++;
		}
		
		// json에 결과 담기
		json.put("RESULT", result);
		json.put("PLANE_NUMS", buf.toString());
		ResponseUtil.write(response, json.toJSONString());
	}
	
	/**
	 * 스케줄 행 추가/ 수정
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/flight/scheduleInsert", method = RequestMethod.POST)
	public void scheduleInsert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// parameterMap 선언
		ParameterMap parameterMap = getParameterMap(request, response);
		// result 선언
		String result = Const.BOOLEAN_SUCCESS;
		// json 선언
		JSONObject json = new JSONObject();
		try {
			flightmgr.scheduleInsert(parameterMap);
		} catch (Exception e) {
			// Exception 일 경우
			result = Const.BOOLEAN_FAIL;
			// json에 MSG 담기
			json.put("MSG", parameterMap.getValue("MSG"));
		}
		// json에 결과 담기
		json.put("RESULT", result);
		ResponseUtil.write(response, json.toJSONString());
	}
	
	/**
	 * 스케줄 행 삭제
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/flight/scheduleDelete", method = RequestMethod.POST)
	public void scheduleDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// parameterMap 선언
		ParameterMap parameterMap = getParameterMap(request, response);
		// result 선언
		String result = Const.BOOLEAN_SUCCESS;
		// json 선언
		JSONObject json = new JSONObject();
		try {
			// 스케줄 행 삭제
			flightmgr.scheduleDelete(parameterMap);
		} catch (Exception e) {
			// Exception 일 경우
			result = Const.BOOLEAN_FAIL;
			// json에 MSG 담기
			json.put("MSG", parameterMap.getValue("MSG"));
		}
		// json에 결과 담기
		json.put("RESULT", result);
		ResponseUtil.write(response, json.toJSONString());
	}
	
	/**
	 * 스케줄 번호 가져오기 - 행 추가 시 자동생성
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/flight/schedulenumInsert", method = RequestMethod.POST)
	public void schedulenumInsert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// parameterMap 선언
		ParameterMap parameterMap = getParameterGridMap(request, response);
		// result 선언
		String result = Const.BOOLEAN_SUCCESS;
		// json 선언
		JSONObject json = new JSONObject();
		try {
			// 스케줄 번호 가져오기 
			json = flightmgr.schedulenumInsert(parameterMap);

		} catch (Exception e) {
			// Exception 일 경우
			result = Const.BOOLEAN_FAIL;
		}
		// json에 결과 담기
		json.put("RESULT", result);
		ResponseUtil.write(response, json.toJSONString());
	}
	
	/**
	 * 가격 스케줄/좌석 중복확인 
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/flight/overlapPrice", method = RequestMethod.POST)
	public void overlapPrice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// parameterMap 선언
		ParameterMap parameterMap = getParameterGridMap(request, response);
		// result 선언
		String result = Const.BOOLEAN_SUCCESS;
		// json 선언
		JSONObject json = new JSONObject();
		try {
			// 가격 스케줄/좌석 중복확인 
			json = flightmgr.overlapPrice(parameterMap);

		} catch (Exception e) {
			// Exception 일 경우
			result = Const.BOOLEAN_FAIL;
		}
		// json에 결과 담기
		json.put("RESULT", result);
		if(json.get("rows").toString().equals("[]")) {
			json.put("OVERLAP", "N");
		}else {
			json.put("OVERLAP", "Y");
		}
		ResponseUtil.write(response, json.toJSONString());
	}
}
