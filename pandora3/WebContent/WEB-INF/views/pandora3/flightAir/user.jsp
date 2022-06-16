<%-- 
   1. 파일명   : user
   2. 파일설명 : 회원 목록 조회
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
		gridid		: 'user_grid',
		pagerid		: '',
		columns		: [
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
		editmode	: false,									// 그리드 editable 여부
		gridtitle	: '회원 목록',									// 그리드명
		multiselect	: false,									// checkbox 여부
		formid		: 'search',									// 조회조건 form id
		height		: 300,										// 그리드 높이
        shrinkToFit	: true,										// true인경우 column의 width 자동조정, false인경우 정해진 width대로 구현(가로스크롤바필요시 false)
        selecturl	: '/user/selectUserList',					// 그리드 조회 URL
	};
    
    user_grid = new UxGrid(grid_config);
    user_grid.init();
    user_grid.setGridWidth($('.tblType1').width());	
    
    // 자동 조회
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

// grid resizing
$(window).resize(function() {
	user_grid.setGridWidth($('.tblType1').width());
});

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
