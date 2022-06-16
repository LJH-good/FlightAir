<%-- 
   1. 파일명   : selectSchedule
   2. 파일설명 : 스케줄 관리
   3. 작성일   : 2022-05-23
   4. 작성자   : LJH
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 헤더파일 include -->
<%@ include file="/WEB-INF/views/pandora3/common/include/header.jsp"%>
<script type="text/javascript">
var schedule_list_grid;
var schedule_detail_grid;
var target_row ="";

$(document).ready(function(){
    var up_config = { 
        gridid    : 'schedule_list_grid',	    	// 그리드 id
        pagerid   : '',								// 그리드 페이지 id
        columns	    : [
						{name : 'SCHEDULE_NUM', width:10,label :'스케줄 번호', align : 'center', sortable : false, editable : false},
						{name : 'DE_COUNTRY', width:35, label : '출발 나라', align : 'center', sortable : false, editable : false},
						{name : 'ARR_COUNTRY', width:35, label : '도착 나라', align : 'center', sortable : false, editable : false},
						{name : 'DE_DATE', width:20, label : '출발 날짜', align : 'center', sortable : false, editable : false},
						{name : 'DE_TIME', width:10, label : '출발 시간', align : 'center', sortable : false, editable : false},
						{name : 'ARR_TIME', width:10, label : '도착 시간', align : 'center', sortable : false, editable : true},
						{name : 'FLIGHT_TIME', width:20, label : '비행 시간', align : 'center', sortable : false, editable : false},
						{name : 'PLANE_NUM', width:10, label : '항공기 번호', align : 'center', sortable : false, editable : false},
						{name : 'STATE', width:10, label : '상태', align : 'center', sortable : false, editable : false},
			         ],
        editmode       : false,                                 	// 그리드 editable 여부
        gridtitle      : '스케줄 목록',                           		// 그리드명
        multiselect    : false,                             		// checkbox 여부
        formid         : 'search',                                  // 조회조건 form id
        height         : 300,                                       // 그리드 높이
        selecturl      : '/flight/selectScheduleList',  		    // 그리드 조회 URL
        events         : {
	                          ondblClickRow : function (event, rowid, irow, icol) {
	                        	  var row = schedule_list_grid.getRowData(rowid);
	                        	  target_row = row;
	                        	  if (isNotEmpty(row.SCHEDULE_NUM)) {
									schedule_detail_grid.retreive({data:{schedule_num:row.SCHEDULE_NUM}});
	                              }
	                          },
                          }
    };
    var down_config = { 
            gridid    : 'schedule_detail_grid',	    	// 그리드 id
            pagerid   : '',								// 그리드 페이지 id
            columns	    : [
							{name : 'RESERVE_NUM', width:80, label :'예약 번호', align : 'center', sortable : false, editable : false},
							{name : 'DATE', width:80, label : '예약 날짜', align : 'center', sortable : false, editable : false, formatter: fn_DateForm},
							{name : 'GO_SCHEDULE', width:80, label : '출국 스케줄', align : 'center', sortable : false, editable : false},
							{name : 'GO_SEAT_CLASS', width:80, label : '출국 좌석등급', align : 'center', sortable : false, editable : false},
							{name : 'BACK_SCHEDULE', width:80, label : '귀국 스케줄', align : 'center', sortable : false, editable : false, formatter:fn_dash},
							{name : 'BACK_SEAT_CLASS', width:80, label : '귀국 좌석등급', align : 'center', sortable : false, editable : false, formatter:fn_dash},
							{name : 'USER_NAME', width:80, label : '이름', align : 'center', sortable : false, editable : false},
							{name : 'EMAIL', width:80, label : '아이디(이메일)', align : 'center', sortable : false, editable : false},
							{name : 'STATE', width:80, label : '예약 상태', align : 'center', sortable : false, editable : false}
				         ],
            editmode    : false, 									// 그리드 editable 여부
            gridtitle   : '스케줄 예약 목록',							// 그리드명
            multiselect : false,                               		// checkbox 여부
            height      : 220,                                		// 그리드 높이
            shrinkToFit : true,                               		// true인경우 column의 width 자동조정, false인경우 정해진 width대로 구현(가로스크롤바필요시 false)
            selecturl   : '/flight/scheduleReserveList',       		// 그리드 조회 URL
        };
    
    schedule_list_grid = new UxGrid(up_config);
    schedule_list_grid.init();
    
    schedule_detail_grid = new UxGrid(down_config);
    schedule_detail_grid.init();
    fn_Search();
});
//조회: 내부 로직 사용자 정의
function fn_Search(){
	target_row = "";
	schedule_list_grid.retreive(); 
	schedule_detail_grid.clearGridData();
}

//날짜 파싱
function fn_DateForm(value, options, rowJson) {      
     return value.substring(0,10);﻿﻿﻿﻿﻿
}

//스케줄이 없는 부분
function fn_dash(value, options, rowJson) {
	if (value == null) {
		return '-';
	} else {
		return value;
	}
}

</script>
</head>
<body>
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
						<th>출국 나라</th>
						<td><span class="txt_pw"><input type="text" name="de_country" id="de_country" class="text" /></span></td>
						<th>귀국 나라</th>
						<td><span class="txt_pw"><input type="text" name="arr_country" id="arr_country" class="text" /></span></td>
					</tr>
				</table>
			</form>
			<br/>
			<!-- search // -->
			<!-- Master Grid -->
			<table id="schedule_list_grid"></table>
			<!-- Master Grid // -->
			
			<!-- Detail Grid -->
			<table id="schedule_detail_grid"></table>
			<!-- Detail Grid // -->
			<br />
		</div>
	</div>
</body>
<%@ include file="/WEB-INF/views/pandora3/common/include/footer.jsp"%>