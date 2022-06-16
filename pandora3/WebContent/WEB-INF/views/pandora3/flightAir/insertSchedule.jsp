<%-- 
   1. 파일명   : insertSchedule
   2. 파일설명 : 스케줄 입력 / 수정
   3. 작성일   : 2022-05-24
   4. 작성자   : LJH
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 헤더파일 include -->
<%@ include file="/WEB-INF/views/pandora3/common/include/header.jsp" %>
<script type="text/javascript">
var schedule_grid;
var plane_nums;

//행추가 여러개 방지(1개씩 추가)
var rowState = true;

$(document).ready(function(){
	getSelectPlane();
	
	var grid_config = {
		gridid		: 'schedule_grid',
		pagerid		: '',
		gridBtnYn : 'Y',				// 상단 그리드 버튼 여부 ( 그리드 1개 일때 필수 'Y', 상/하단 그리드 일 경우 상단 그리드만 필수'Y' )
		columns		: [
 			{name : 'SCHEDULE_NUM', width:10,label :'스케줄 번호', align : 'center', sortable : false, editable : false, required : true, edittype : 'text',editoptions : {maxlength:5}},
			{name : 'DE_COUNTRY', width:39, label : '출발 나라', align : 'center', sortable : false, editable : true, required : true, edittype : 'text',editoptions : {maxlength:20}},
			{name : 'ARR_COUNTRY', width:39, label : '도착 나라', align : 'center', sortable : false, editable : true, required : true, edittype : 'text', editoptions : {maxlength:20}},
			{name : 'DE_TIME', width:10, label : '출발 시간', align : 'center', sortable : false, editable : true, required : true, edittype : 'text', editoptions : {maxlength:5}},
			{name : 'ARR_TIME', width:10, label : '도착 시간', align : 'center', sortable : false, editable : true, required : true, edittype : 'text', editoptions : {maxlength:5}},
			{name : 'FLIGHT_TIME', width:15, label : '비행 시간', align : 'center', sortable : false, editable : true, required : true, edittype : 'text', editoptions : {maxlength:10}},
			{name : 'DE_DATE', width:15, label : '출발 날짜', align : 'center', sortable : false, editable : true, required : true, edittype : 'text', editoptions : {maxlength:10}},
			{name : 'PLANE_NUM', width:10, label : '항공기 번호', align : 'center', sortable : false, editable : true, required : true, edittype : 'select', editoptions : {maxlength:5, value:plane_nums}},
			{name : 'STATE', width:10, label : '상태', align : 'center', sortable : false, editable : true, required : true, edittype : 'select', editoptions : {value:'정상:정상;비활성:비활성'}},
			{name : 'ADD_DATE', width:16, label : '등록일', align : 'center', sortable : false, editable : false, formatter: fn_DateForm },
			{name : 'RECENT_UPDATE', width:16, label : '마지막 수정일', align : 'center', sortable : false, editable : false, formatter: fn_DateForm}
			
        ],
		editmode	: true,										// 그리드 editable 여부
		gridtitle	: '스케줄 목록',								// 그리드명
		multiselect	: true,										// checkbox 여부
		formid		: 'search',									// 조회조건 form id
		height		: 400,										// 그리드 높이
        shrinkToFit	: true,										// true인경우 column의 width 자동조정, false인경우 정해진 width대로 구현(가로스크롤바필요시 false)
        selecturl	: '/flight/selectScheduleList',				// 그리드 조회 URL
	};
    
    schedule_grid = new UxGrid(grid_config);
    schedule_grid.init();
    schedule_grid.setGridWidth($('.tblType1').width());
    
    fn_Search();
     
});

// grid resizing
$(window).resize(function() {
	schedule_grid.setGridWidth($('.tblType1').width());
});


//조회: 내부 로직 사용자 정의
function fn_Search(){
	rowState = true;
	schedule_grid.retreive(); 
}

//날짜 파싱
function fn_DateForm(value, options, rowJson) {
	 if(value == null){ return ""; }
     return value.substring(0,10);﻿﻿﻿﻿﻿
} 

//데이터 가져오기: plane_nums
function getSelectPlane() {
	ajax({
       url : '/flight/getSelectPlane',
       async : false,
       success : function(data) {
          if (data.RESULT == "S"){
        	  plane_nums = data.PLANE_NUMS;
          }
          else {
             if (isNotEmpty(data.MSG)){ alert("적용 실패!") }
          }
       },
    });
}

//추가: 내부 로직 사용자 정의
function fn_AddRow(){
 	if(rowState){
 		var newScheduleNum;
 		ajax({
 		       url : '/flight/schedulenumInsert',
 		       async : false,
 		       success : function(data) {
 		    	   newScheduleNum = data.rows[0].SCHEDULE_NUM;
 		    	   newScheduleNum = newScheduleNum.substring(0, 2) + (Number(newScheduleNum.substring(1, newScheduleNum.length)) + 1);
 		       },
 		    });
 		schedule_grid.add({APLY_START_DATE:$.timestampToString(new Date()), SCHEDULE_NUM : newScheduleNum});	
 		rowState = false;
 	} else{
 		alert("스케줄 추가는 1개씩 가능합니다.\n기존 작업을 마무리해주세요.");
 	}
}

// 유효성 검사 
function fn_validationCheck(selectRow) {
	for(var i = 0; i < selectRow.length; i++){
		var de_time = selectRow[i].DE_TIME;
		var arr_time = selectRow[i].ARR_TIME;
		var flight_time = selectRow[i].FLIGHT_TIME;
		var de_date = selectRow[i].DE_DATE;
		
		var timetest = /^(0[1-9]|1[0-9]|2[0-4]):([0-5][0-9])$/;
		var datetest = /^(19|20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$/;
		var flighttimetest = /^(0[1-9]|[1-9][1-9])시간(0[0-9]|[1-5][0-9])분$/;
		
		if(!(timetest.test(de_time) && timetest.test(arr_time))){
			alert("시간 형식을 맞춰주세요\n HH24:mm");
			return false;
		} 
		else if(!datetest.test(de_date)){
			alert("날짜 형식을 맞춰주세요 \n YYYY-MM-DD");
			return false;
		}
		else if(!flighttimetest.test(flight_time)){
			alert("비행 시간 형식을 맞춰주세요 \n OO시간 OO분");
			return false;
		} 
		else{
			return true;
		}
	}
	
}

//저장: 내부 로직 사용자 재정의
function fn_Save(){
	if (isEmpty(getSaveData("schedule_grid"))) {
			return false;
		}
	else if (getSaveData("schedule_grid") == '[]') {
		alert("데이터를 선택해주세요");
		return false;
	}
		var selectRow = schedule_grid.getSelectRows();
		if (fn_validationCheck(selectRow)) {

			var scheduleData = getSaveData("schedule_grid");
			//입력폼데이터 파라미터형태로 변경
			var data = "scheduleData=" + scheduleData + "&_pre_url="
					+ parent.preUrl.getPreUrl();

			ajax({
				url : '/flight/scheduleInsert',
				data : data,
				async : false,
				success : function(data) {
					if (data.RESULT == "S") {
						alert('적용 완료!');
						fn_Search();
					} else {
						if (isNotEmpty(data.MSG)) {
							alert("적용 실패!")
						}
					}
				},
			});
		}
}

//삭제: 내부 로직 사용자 삭제
function fn_Delete() {
	//jqgrid grid 데이터 json 형태로 생성
	var scheduleData;
	if (getSaveData("schedule_grid") == '[]') {
		alert("삭제할 스케줄 번호를 선택해주세요.");
		return false;
	} else {
		if (!confirm("해당 스케줄을 삭제하시겠습니까?\n삭제 후 복구가 불가능 합니다.")) {
			return false;
		} else {
			schedule_grid.remove(); 
			scheduleData = getSaveData("schedule_grid"); //grid_id
		}
	}

	//입력폼데이터 파라미터형태로 변경
	var data = "scheduleData=" + scheduleData + "&_pre_url="
			+ parent.preUrl.getPreUrl();
	ajax({
		url : '/flight/scheduleDelete',
		data : data,
		async : false,
		success : function(data) {
			if (data.RESULT == "S") {
				alert("삭제되었습니다.");
				fn_Search();
				return;
			} else {
				alert("스케줄 삭제 실패!")
				e.preventDefault();
				return;
			}
		}
	});
}
</script>
</head>
<body id="app">
	<div class="frameWrap">
	    <div class="subCon">
			<%@ include file="/WEB-INF/views/pandora3/common/include/btnList.jsp" %>
			<div class="tableTopLeft gridBtn">
				<div class="selectInner" style="display:none">
					<label for="sys_info">시스템 : </label>
					<select id="sys_info" name="sys_info" class="select">
						<option value=''>선택</option>
					</select>
				</div>
			</div>		
		
			<form name="search" id="search" name="search" onsubmit="return false">
	            <table class="tblType1 mB60" >
	            	<colgroup>
						<col width="15%" />
						<col width="15%" />
						<col width="15%" />
					</colgroup>   
					<tr>
		            <th>스케줄 번호</th>
						<td><span class="txt_pw"><input type="text" name="schedule_num" id="schedule_num" class="text" /></span></td>
					<th>출발 나라 </th>
						<td><span class="txt_pw"><input type="text" name="de_country" id="de_country" class="text" /></span></td>
					<th>도착 나라 </th>
						<td><span class="txt_pw"><input type="text" name="arr_country" id="arr_country" class="text" /></span></td>	
	            	</tr>
	            </table>
			</form>
			<br/>
	        <!-- search // -->
	        <!-- Grid -->
	        <table id="schedule_grid"></table> 
	        <!-- Grid // -->    
	    </div>
    </div>
</body>
<%@ include file="/WEB-INF/views/pandora3/common/include/footer.jsp" %>
