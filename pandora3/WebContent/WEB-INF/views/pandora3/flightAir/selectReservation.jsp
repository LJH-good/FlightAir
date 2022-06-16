<%-- 
   1. 파일명   : selectReservation
   2. 파일설명 : 예약 관리
   3. 작성일   : 2022-05-25
   4. 작성자   : LJH
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 헤더파일 include -->
<%@ include file="/WEB-INF/views/pandora3/common/include/header.jsp" %>
<script type="text/javascript">
var reservation_grid;

$(document).ready(function(){
	var grid_config = {
		gridid		: 'reservation_grid',
		pagerid		: '',
		gridBtnYn   : 'Y',						// 상단 그리드 버튼 여부 ( 그리드 1개 일때 필수 'Y', 상/하단 그리드 일 경우 상단 그리드만 필수'Y' )
		columns		: [
		                {name : 'RESERVE_NUM', width:8, label :'예약 번호', align : 'center', sortable : false, editable : false},
		                {name : 'USER_NAME', width:10, label : '이름', align : 'center', sortable : false, editable : false},
		                {name : 'SURNAME', width:10, label : '성(영문)', align : 'center', sortable : false, editable : false},
		                {name : 'NAME', width:20, label : '이름(영문)', align : 'center', sortable : false, editable : false},
		                {name : 'PASS_NUM', width:16, label : '여권 번호', align : 'center', sortable : false, editable : false},
		                {name : 'BIRTHDAY', width:16, label : '생년월일', align : 'center', sortable : false, editable : false},
		                {name : 'DATE', width:16, label : '예약 날짜', align : 'center', sortable : false, editable : false, formatter : setDate},
		                {name : 'GO_SCHEDULE', width:10, label : '출국 스케줄', align : 'center', sortable : false, editable : false},
		                {name : 'GO_SEAT_CLASS', width:18, label : '출국 좌석 등급', align : 'center', sortable : false, editable : false},
		                {name : 'SPARE', width:14, label : '잔여석', align : 'center', sortable : false, editable : false, formatter : setSpare},
		                {name : 'BACK_SCHEDULE', width:10, label : '귀국 스케줄', align : 'center', sortable : false, editable : false, formatter:fn_dash},
		                {name : 'BACK_SEAT_CLASS', width:18, label : '귀국 좌석 등급', align : 'center', sortable : false, editable : false, formatter:fn_dash},
		                {name : 'SPARE', width:14, label : '잔여석', align : 'center', sortable : false, editable : false, formatter : setSpare2},
		                {name : 'STATE', width:30, label : '예약 상태', align : 'center', sortable : false, editable : true, edittype : 'select', editoptions : {value:'승인 대기:승인 대기;승인 완료:승인 완료;스케쥴 취소로 인한 취소:스케쥴 취소로 인한 취소;예약정보상이로 인한 취소:예약정보상이로 인한 취소'}}
        			],
        			
		editmode	: true,										// 그리드 editable 여부
		gridtitle	: '예약 목록',									// 그리드명
		multiselect	: true,										// checkbox 여부
		formid		: 'search',									// 조회조건 form id
		height		: 300,										// 그리드 높이
        shrinkToFit	: true,										// true인경우 column의 width 자동조정, false인경우 정해진 width대로 구현(가로스크롤바필요시 false)
        selecturl	: '/reservation/reservationList',			// 그리드 조회 URL
	};
    
    reservation_grid = new UxGrid(grid_config);
    reservation_grid.init();
    reservation_grid.setGridWidth($('.tblType1').width());	
    
    // 자동 조회
    fn_Search();
});

//조회: 내부 로직 사용자 정의
function fn_Search(){
	reservation_grid.retreive();
}

//날짜 줄바꿈
function setDate(value, options, rowJson) {      
     return value.substring(0,10) + "<br>" + value.substring(10, value.length);﻿﻿﻿﻿﻿
}

//잔여석 이벤트 - 가는 스케줄
function setSpare(value, options, rowJson) {      
	var total_seat;
	var spare_seat;
	var schedule_num = rowJson.GO_SCHEDULE;
	var seat_class = rowJson.GO_SEAT_CLASS;
	
	ajax({
		url : '/reservation/getSpareseat',
		data : {schedule_num : schedule_num, seat_class : seat_class },
		async : false,
		success : function(data){
			if(data.RESULT == "S"){
				total_seat = data.TOTAL_SEAT.rows[0].VALUE;
				spare_seat = data.SPARE_SEAT.rows[0].VALUE;
			}
			else {
				if (isNotEmpty(data.MSG)){ alert("적용 실패!") }
			}
		}
	})
	return (total_seat - spare_seat) + " / " + total_seat;﻿﻿﻿﻿﻿
}

//잔여석 이벤트 - 오는 스케줄
function setSpare2(value, options, rowJson) {      
	var total_seat;
	var spare_seat;
	var schedule_num = rowJson.BACK_SCHEDULE;
	var seat_class = rowJson.BACK_SEAT_CLASS;
	
	if(schedule_num != '-'){
		ajax({
			url : '/reservation/getSpareseat',
			data : {schedule_num : schedule_num, seat_class : seat_class },
			async : false,
			success : function(data){
				if(data.RESULT == "S"){
					total_seat = data.TOTAL_SEAT.rows[0].VALUE;
					spare_seat = data.SPARE_SEAT.rows[0].VALUE;
				}
				else {
					if (isNotEmpty(data.MSG)){ alert("적용 실패!") }
				}
			}
		})	
	} else {
		return '-';
	}
	return (total_seat - spare_seat) + " / " + total_seat;﻿﻿﻿﻿﻿
}

// grid resizing
$(window).resize(function() {
	reservation_grid.setGridWidth($('.tblType1').width());
});

//스케줄이 없는 부분
function fn_dash(value, options, rowJson) {
	if (value == null) {
		return '-';
	} else {
		return value;
	}
}

//저장: 내부 로직 사용자 재정의
function fn_Save(){

	if(getSaveData("reservation_grid") == '[]'){
		alert("데이터를 선택해주세요");
		return false;
	}
	var reservationData = getSaveData("reservation_grid");
    //입력폼데이터 파라미터형태로 변경
    var data = "reservationData=" + reservationData + "&_pre_url="
          + parent.preUrl.getPreUrl();
    ajax({
   		url: '/reservation/reservationState',
   		data : data ,
   		async : false,
   		success: function(data) {
   			if(data.RESULT == "S") {
   				alert('저장되었습니다');	
   				fn_Search();
   				return;
   			}else {
   				alert("일시적 오류입니다\n잠시후 다시 시도하세요.")
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
	        <!-- search -->
			<form id="search" name="search" onsubmit="return false">
				<table class="tblType1 mB60">
					<colgroup>
						<col width="15%" />
						<col width="35%" />
						<col width="15%" />
						<col width="35%" />
					</colgroup>
					<tr>
						<th>이름</th>
						<td><span class="txt_pw"><input type="text" name="user_name" id="user_name" class="text" /></span></td>
						<th>예약 상태</th>
						<td><span class="txt_pw">
							<select name="state" id="state">
								<option value="">전체</option>
								<option value="승인 대기">승인 대기</option>
								<option value="승인 완료">승인 완료</option>
								<option value="스케쥴 취소로 인한 취소">스케쥴 취소로 인한 취소</option>
								<option value="예약정보상이로 인한 취소">예약정보상이로 인한 취소</option>
							</select>
						</span></td>
					</tr>
				</table>
			</form>
			<br/>
			<!-- search // -->
	        <!-- Grid -->
	        <table id="reservation_grid"></table> 
	        <!-- Grid // -->    
	    </div>
    </div>
</body>
<%@ include file="/WEB-INF/views/pandora3/common/include/footer.jsp" %>
