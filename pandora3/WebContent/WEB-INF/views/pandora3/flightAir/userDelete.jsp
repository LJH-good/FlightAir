<%--
   1. 파일명   : userDelete
   2. 파일설명 : 회원 삭제
   3. 작성일   : 2022-05-20
   4. 작성자   : LJH
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 헤더파일 include -->
<%@ include file="/WEB-INF/views/pandora3/common/include/header.jsp" %>
<script type="text/javascript">
var user_grid;

$(document).ready(function(){
	var grid_config = {
		gridid    : 'user_grid',	    	// 그리드 id
        pagerid   : '',						// 그리드 페이지 id
		gridBtnYn : 'Y',					// 상단 그리드 버튼 여부 ( 그리드 1개 일때 필수 'Y', 상/하단 그리드 일 경우 상단 그리드만 필수'Y' )
		columns	  : [
						{name : 'EMAIL', width:140, label : '아이디(이메일)', align : 'center', sortable : false, editable : false},
			            {name : 'USER_NAME', width:80, label : '이름', align : 'center', sortable : false, editable : false},
			            {name : 'SURNAME', width:70, label : '성(영문)', align : 'center', sortable : false, editable : false},
			            {name : 'NAME', width:70, label : '이름(영문)', align : 'center', sortable : false, editable : false},
			            {name : 'BIRTHDAY', width:80, label : '생년월일', align : 'center', sortable : false, editable : false},
			            {name : 'PASS_NUM', width:80, label : '여권번호', align : 'center', sortable : false, editable : false},
			            {name : 'POST', width:70, label : '우편번호', align : 'center', sortable : false, editable : false},
			            {name : 'ADDR1', width:200, label : '주소', align : 'center', sortable : false, editable : false},
			            {name : 'ADDR2', width:160, label : '상세 주소', align : 'center', sortable : false, editable : false},
			            {name : 'PHONE', width:90, label : '전화번호', align : 'center', sortable : false, editable : false},
			            {name : 'DATE', width:80, label : '가입 날짜', align : 'center', sortable : false, editable : false, formatter: fn_DateForm},
			            {name : 'RECENT_UPDATE', width:80, label : '최근 수정일', align : 'center', sortable : false, editable : false, formatter: fn_DateForm}
                    ],
		editmode	: false,
		gridtitle	: '회원 삭제',									// 그리드명
		multiselect	: true,										// checkbox 여부
		formid		: 'delete',									// 조회조건 form id
		height		: 480,										// 그리드 높이
        shrinkToFit	: true,										// true인경우 column의 width 자동조정, false인경우 정해진 width대로 구현(가로스크롤바필요시 false)
        selecturl	: '/user/selectUserList',					// 그리드 조회 URL
	};
    
    user_grid = new UxGrid(grid_config);
    user_grid.init();
    
    fn_Search();
    
});

//조회: 내부 로직 사용자 정의
function fn_Search(){
	user_grid.retreive();
}

//날짜 파싱
function fn_DateForm(value, options, rowJson) {      
     return value.substring(0,10);﻿﻿﻿﻿﻿
}

//삭제: 내부 로직 사용자 삭제
function fn_Delete() {
//jqgrid grid 데이터 json 형태로 생성
	var userData;
	if (getSaveData("user_grid") == '[]') {
		alert("삭제할 회원 번호를 선택해주세요.");
		return false;
	} else {
		if (!confirm("해당 회원을 삭제하시겠습니까?\n삭제 후 복구가 불가능 합니다.")) {
			return false;
		} else {
			user_grid.remove();
			userData = getSaveData("user_grid"); 
		}
	}
	if(fn_reservationCheck()) {
		//입력폼데이터 파라미터형태로 변경
		var data = "userData=" + userData + "&_pre_url="
				+ parent.preUrl.getPreUrl();
		ajax({
			url : '/user/deleteUser',
			data : data,
			async : false,
			success : function(data) {
				if (data.RESULT == "S") {
					alert("삭제되었습니다.");
					fn_Search();
					return;
				} else {
					alert("사용자 삭제 실패!")
					e.preventDefault();
					return;
				}
			}
		});
	}
}

// 회원 삭제 시 예약 내역 확인
function fn_reservationCheck(){
	var email = user_grid.getRowData(user_grid.getSelectRowIDs()).EMAIL;
	if(email == null){
		alert("삭제는 한번에 하나만 가능합니다.");
		return false;
	}
	var result;
	ajax({
	   url : '/user/reservationCheck',
	   data : {
	      email : email,
	   },
	   async : false,
	   success : function(data) {
	      if (data.RESULT == "S"){
	    	  if(data.rows[0].VALUE > 0){
		         alert("만료되지 않은 예약 내역이 존재하여 삭제가 불가합니다");
		         result = false;
	    	  }
	    	  else{
	    		  result = true; 
	    	  }
	      }
	      else {
	         if (isNotEmpty(data.MSG)){ alert("잠시 후 다시 시도해주세요") }
	      }
	   },
	});
	return result;
}

</script>
</head>
<body id="app">
	<div class="frameWrap">
	    <div class="subCon">
			<%@ include file="/WEB-INF/views/pandora3/common/include/btnList.jsp" %>
	       
			<div class="bgBorder"></div>
	        <!-- search -->
			<form id="delete" name="delete" onsubmit="return false">
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
						<th>아이디(이메일)</th>
						<td><span class="txt_pw"><input type="text" name="email" id="email" class="text" /></span></td>
					</tr>
				</table>
			</form>
			<br/>
			<!-- search // -->
	        <!-- Grid -->
	        <table id="user_grid"></table> 
	        <!-- Grid // -->        
	    </div>
    </div>
</body>
<%@ include file="/WEB-INF/views/pandora3/common/include/footer.jsp" %>