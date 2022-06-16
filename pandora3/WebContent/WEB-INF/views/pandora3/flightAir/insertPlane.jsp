<%-- 
   1. 파일명   : insertPlane
   2. 파일설명 : 항공기 입력 / 수정
   3. 작성일   : 2022-05-26
   4. 작성자   : LJH
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 헤더파일 include -->
<%@ include file="/WEB-INF/views/pandora3/common/include/header.jsp" %>
<script type="text/javascript">
var plane_grid;
var plane_nums;

//행추가 여러개 방지(1개씩 추가)
var rowState = true;

$(document).ready(function(){
	
	var grid_config = {
		gridid		: 'plane_grid',
		pagerid		: '',
		gridBtnYn : 'Y',				// 상단 그리드 버튼 여부 ( 그리드 1개 일때 필수 'Y', 상/하단 그리드 일 경우 상단 그리드만 필수'Y' )
		columns		: [
			 			{name : 'PLANE_NUM', label : '항공기 번호', align : 'center', editable : false , required : true, edittype : 'text', width : 20, editoptions : {maxlength:5}},
						{name : 'FIRST_CAPACITY', label : '비즈니스 수용인원', align : 'center', editable : true,  required : true, edittype : 'text', width : 20, editoptions : {maxlength : 4}},
						{name : 'SECOND_CAPACITY', label : '이코노미 수용인원', align : 'center', editable : true, required : true, edittype : 'text', width : 20, editoptions : {maxlength : 4}},
						{name : 'ADD_DATE', width:20, label : '등록일', align : 'center', sortable : false, editable : false, formatter: fn_DateForm},
						{name : 'RECENT_UPDATE', width:20, label : '마지막 수정일', align : 'center', sortable : false, editable : false, formatter: fn_DateForm}
					],
		editmode	: true,										// 그리드 editable 여부
		gridtitle	: '항공기 목록',								// 그리드명
		multiselect	: true,										// checkbox 여부
		formid		: 'search',									// 조회조건 form id
		height		: 300,										// 그리드 높이
        shrinkToFit	: true,										// true인경우 column의 width 자동조정, false인경우 정해진 width대로 구현(가로스크롤바필요시 false)
        selecturl	: '/plane/planeList',						// 그리드 조회 URL
	};
    
    plane_grid = new UxGrid(grid_config);
    plane_grid.init();
    plane_grid.setGridWidth($('.tblType1').width());
    
    fn_Search();
     
});

// grid resizing
$(window).resize(function() {
	plane_grid.setGridWidth($('.tblType1').width());
});


//조회: 내부 로직 사용자 정의
function fn_Search(){
	rowState = true;
	plane_grid.retreive(); 
}

//날짜 파싱
function fn_DateForm(value, options, rowJson) {
	 if(value == null){ return ""; }
     return value.substring(0,10);﻿﻿﻿﻿﻿
}

//추가: 내부 로직 사용자 정의
function fn_AddRow() {
getData();
	var planeState = true;

	if (rowState) {
		var planeNum = prompt('항공기 번호를 입력해주세요.');
		for (var i = 0; i < plane_nums.length; i++) {
			if (plane_nums[i].PLANE_NUM == planeNum) {
				planeState = false;
				break;
			}
		}
		if (planeNum == '' || planeNum == null) {
			alert("항공기 번호를 입력하지 않았습니다.");
		} else if (!planeState) {
			alert("중복되는 항공기 번호입니다.");
		} else {
			plane_grid.add({
				APLY_START_DATE : $.timestampToString(new Date()),
				PLANE_NUM : planeNum
			});
		}
		rowState = false;
	} else {
		alert("항공기 추가는 1개씩 가능합니다. \n 기존 작업을 마무리해주세요.");
	}

}

//데이터 가져오기: plane_nums
function getData() {
	ajax({
			url : '/plane/getPlaneNum',
			async : false,
			success : function(data) {
				if (data.RESULT == "S") {
					plane_nums = data.PLANE_NUMS;
				} else {
					if (isNotEmpty(data.MSG)) {
						alert("적용 실패!")
					}
				}
			},
		});
	}
	function fn_validationCheck() {
		return true;
}

// 유효성 검사
function fn_validationCheck(selectRow) {
	for(var i = 0; i < selectRow.length; i++){
		var first_capacity = Number(selectRow[i].FIRST_CAPACITY);
		var second_capacity = Number(selectRow[i].SECOND_CAPACITY);
		
		var capacitytest = /^[0-9]{1,3}$/;
		
		if(!capacitytest.test(first_capacity)){
			alert("수용인원은 숫자만 입력이 가능합니다\n 1~999");
			return false;
		} else if(!capacitytest.test(second_capacity)){
			alert("수용인원은 숫자만 입력이 가능합니다\n 1~999");
			return false;
		} else if(first_capacity == 0){
			alert("수용인원은 0이 될 수 없습니다");
			return false;
		} else if(second_capacity == 0){
			alert("수용인원은 0이 될 수 없습니다");
			return false;
		}
		else{
			return true;
		}
	}
	
}
	
//저장: 내부 로직 사용자 재정의
function fn_Save() {
	if (isEmpty(getSaveData("plane_grid"))) {
		return false;
	} else if (getSaveData("plane_grid") == '[]') {
		alert("데이터를 선택해주세요");
		return false;
	}
	
	var selectRow = plane_grid.getSelectRows();
	if (fn_validationCheck(selectRow)) {
		
		var planeData = getSaveData("plane_grid");
		//입력폼데이터 파라미터형태로 변경
		var data = "planeData=" + planeData + "&_pre_url="
				+ parent.preUrl.getPreUrl();
	
		ajax({
			url : '/plane/planeInsert',
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
	} else{
		alert("데이터를 선택해주세요");
		return false;
	}
}

//삭제: 내부 로직 사용자 삭제
function fn_Delete() {

//jqgrid grid 데이터 json 형태로 생성
	var planeData;
	if (getSaveData("plane_grid") == '[]') {
		alert("삭제할 항공기 번호를 선택해주세요.");
		return false;
	} else {
		if (!confirm("해당 항공기 데이터를 삭제하시겠습니까?\n삭제 후 복구가 불가능 합니다.")) {
			return false;
		} else {
			plane_grid.remove(); 
			planeData = getSaveData("plane_grid"); //grid_id
		}
	}

	//입력폼데이터 파라미터형태로 변경
	var data = "planeData=" + planeData + "&_pre_url="
			+ parent.preUrl.getPreUrl();
	ajax({
		url : '/plane/planeDelete',
		data : data,
		async : false,
		success : function(data) {
			if (data.RESULT == "S") {
				alert("삭제되었습니다.");
				fn_Search();
				return;
			} else {
				alert("데이터 삭제 실패!")
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
				<input type="hidden" name="sys_cd" id="sys_cd" value="" />
	            <table class="tblType1 mB60" >
	            	<colgroup>
						<col width="50%" />
					</colgroup>   
		           <tr>
		            <th>항공기 번호</th>
						<td><span class="txt_pw"><input type="text" name="plane_num" id="plane_num" class="text" /></span></td>
	            	</tr>
	            </table>
			</form>
			<br/>
	        <!-- search // -->
	        
	        <!-- Grid -->
	        <table id="plane_grid"></table> 
	        <!-- Grid // -->    
	    </div>
    </div>
</body>
<%@ include file="/WEB-INF/views/pandora3/common/include/footer.jsp" %>
