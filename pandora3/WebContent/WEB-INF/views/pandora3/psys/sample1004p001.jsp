<%-- 
   1. 파일명 : psys1030p001
   2. 파일설명 : 메뉴 검색 팝업
   3. 작성일 : 2018-03-27
   4. 작성자 : TANINE
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 헤더파일 include -->
<%@ include file="/WEB-INF/views/pandora3/common/include/pop_header.jsp" %>
<script type="text/javascript">
var menu_grid;
var sys_cd = '<%= parameterMap.getValue("sys_cd") %>';
$(document).ready(function() {
	var menu_config = { 
		gridid		: 'menu_grid',
		pagerid		: 'menu_grid_pager',
        columns		: [
        	{name : 'PGM_ID', label : '프로그램ID', hidden : true},
			{name : 'MIDD_MNU_SEQ', label : '상위메뉴ID', align : 'center', width : 40},
			{name : 'MIDD_MNU_NM', label : '상위메뉴명', width : 80},
			{name : 'MNU_SEQ', label : '메뉴ID', align : 'center', width : 40},
			{name : 'MNU_NM', label : '메뉴명', width : 150},
			/* {name : 'PGM_BTN', label : '프로그램버튼목록', hidden : true} */
		],
		editmode	: false,							// 그리드 editable 여부
		gridtitle	: '메뉴 목록',							// 그리드명
		multiselect	: true,								// checkbox 여부
		formid		: 'search',							// 조회조건 form id
		height		: 201,								// 그리드 높이
		selecturl	: '/psys/getPsys1030p001List.adm',	// 그리드 조회 URL
	};
	menu_grid = new UxGrid(menu_config);
	menu_grid.init();
	menu_grid.setGridWidth($('.tblType1').width());
    
	// 조회버튼 클릭 시
	$("#btn_retreive").click(function() {
		menu_grid.retreive();
	});
    
	// 선택버튼 클릭 시
	$("#btn_select").click(function() {
		
		var rows = menu_grid.getSelectRows();
		
		if(rows.length == 0) {
			alert('메뉴를 선택해 주세요.');
			return;
		}
		
		//호출된 iframe id 획득
		var iframeId = '#tab-' + '<%= parameterMap.getValue("target_id") %>';

		//ifram 객체 획득
		var $frame = parent.$(iframeId)[0];
		
		//ifram 접근
     	var gocoderFrameGo = $frame.contentWindow || $frame.contentDocument ;
		
		//callback 함수 실행
     	gocoderFrameGo.<%= parameterMap.getValue("callback") %>(rows);
		
		parent.$('.layer_popup').bPopup().close();
				
	});
	
	// 팝업버튼 클릭 시 
	$("#btn_popup").click(function() {
		// 팝업크기 3종류로 구분 type : sl, l, xml
		bpopup({
			  url       :"/psys/forward.sample1004p002.adm"
			, params	: {callback : "fn_sample1004p002_callback", sys_cd : sys_cd}
			, title		: "메뉴 조회"                          
			, type		: "l"
			, id        : "sample1004p2" 
			, class2     : "typeTwo"
       });	
	});
	
	// 닫기버튼 클릭 시
	$(".btn_layer_close").click(function() {
		parent.$('.layer_popup').bPopup().close();
	});  
});

function fn_sample1004p002_callback(list){
	
}

$(window).resize(function() {
	menu_grid.setGridWidth($('.tblType1').width());
});

</script>
</head>
<body id="pop">
	<div class="frameWrap">
		<div class="subCon">
			<h1><%=_title %></h1>
			<div class="subConIn">
				<div class="subConScroll">
					<!-- 조회조건 -->
					<form name="search" id="search" name="search" onsubmit="return false">
						<input type="hidden" id="sys_cd" name="sys_cd" value="<%= parameterMap.getValue("sys_cd") %>" />
						<div class="frameTopTable">
							<table class="tblType1">
								<colgroup>
									<col width="85px;" />
									<col width="" />
								</colgroup>
								<tr>
									<th>메뉴명</th>
									<td>
										<span class="txt_pw"><input type="text" name="mnu_nm" id="mnu_nm" class="text w100" style="" maxlength="100" /></span>
									</td>
									<!-- <td>
										<div class="grid_btn">
											<button class="btn_common btn_default" id="btn_retreive">조회</button>
										</div>
									</td>
									<td>
										<div class="grid_btn">
											<button class="btn_common btn_default" id="btn_popup">팝업</button>
										</div>
									</td> -->
								</tr>
							</table>
						</div>
						<div class="btnWrap">
							<button type="button" class="btn_common btn_gray" id="btn_retreive">조회</button>
							<button type="button" class="btn_common btn_gray" id="btn_popup">팝업</button>
						</div>
					</form>
					<!-- 조회조건  // -->
					<div class="grid_right_btn">
						<div class="grid_right_btn_in">
							<button class="btn_common btn_default" id="btn_menu_save">저장</button>
							<button class="btn_common btn_default" id="btn_menu_add">행추가</button>
							<button class="btn_common btn_default" id="btn_menu_del">행삭제</button>
							<button class="btn_common btn_default" id="btn_menu_excel">엑셀다운로드</button>
						</div>
					</div>
					<!-- Grid -->
					<table id="menu_grid"></table>
					<div id="menu_grid_pager"></div>
					<!-- Grid // -->
				</div>
			</div>
			<div class="grid_btn">
				<button type="button" class="btn_layer_close btn_common btn_gray" name="btn_close">닫기</button>
				<button type="button" class="btn_common btn_black" id="btn_select" name="btn_select">선택</button>
			</div>
		</div>
	</div>
</body>
<%@ include file="/WEB-INF/views/pandora3/common/include/pop_footer.jsp" %>
