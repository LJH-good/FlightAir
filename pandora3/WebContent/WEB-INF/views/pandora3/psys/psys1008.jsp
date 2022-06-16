<%-- 
   1. 파일명 : psys1008
   2. 파일설명 : 시스템사용자권한관리
   3. 작성일 : 2018-03-28
   4. 작성자 : TANINE
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 헤더파일 include -->
<%@ include file="/WEB-INF/views/pandora3/common/include/header.jsp"%>

<script type="text/javascript">
var role_grid;
var user_grid;
var target_row;
// 한글 입력 방지
function fn_onkeyup(e) {
	$(e).keyup(function() {
		e.value = e.value.replace(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/g, '');
	});
}

function fn_dateFormatter(cellValue, options, rowObject){
	
	return cellValue.substr(0, 4) + "-" + cellValue.substr(4, 2) + "-" + cellValue.substr(6, 2);
}

$(document).ready(function(){
	var role_config = {
		gridid	  : 'role_grid',
		pagerid	  : 'role_grid_pager',
		gridBtnYn : 'Y',						// 상단 그리드 버튼 여부 ( 그리드 1개 일때 필수 'Y', 상/하단 그리드 일 경우 상단 그리드만 필수'Y' )
		columns   : [
			{name : 'ROL_ID', label : '권한ID', editable:true, align:'center',  sorttype:'int', width:60, editoptions:{dataInit:fn_onkeyup}},
			{name : 'ROL_NM', label : '권한명', editable:true, edittype:'text', width:200},
			{name : 'US_YN', label : '사용여부', align:'center', editable:true, edittype:'select', formatter:'select', editoptions:{value:'Y:사용;N:미사용'}, width:80},
			{name : 'APL_ST_DT', label : '적용시작일자', align:'center', editable:true, width:120, formatter : fn_dateFormatter},
			{name : 'APL_ED_DT', label : '적용종료일자', align:'center', editable:true, width:120, formatter : fn_dateFormatter},
			{name : 'F_UPD_DTTM', label : '변경일자', align:'center', width:100}
		],
		initval		: {US_YN:'Y', APLY_END_DATE:'9999-12-31 23:59'}, // 컬럼 add 시 초기값
		editmode	: false,						// 그리드 editable 여부
		gridtitle	: '권한 목록',						// 그리드명
		multiselect	: false,						// checkbox 여부
		formid		: 'search',						// 조회조건 form id
		height		: 200,							// 그리드 높이
		selecturl	: '/psys/getPsys1006List',	// 그리드 조회 URL
		saveurl		: '/psys/savePsys1006',		// 그리드 입력/수정/삭제 URL
		events		: {    
			ondblClickRow : function(event, rowIdx, irow, icol) {
				var row = role_grid.getRowData(rowIdx);
				if(isNotEmpty(row.ROL_ID)) {
					target_row = row;
					$("#usr_nm").val("");
					user_grid.retreive({data:{rol_id:row.ROL_ID}});
				}
			}
		}
	};
	
	var user_config = {
		gridid	: 'user_grid',
		pagerid	: 'user_grid_pager',
		columns	: [
			{name:'STATUS', label:'상태', align:'center', editable:false, width:20},
			{name : 'LGN_ID', label : '로그인ID',  sorttype : 'int', width : 100, hidden : true},
			{name : 'ROL_ID', label : '권한ID', align : 'center',  sorttype : 'int', width : 60, hidden : true},           
			{name : 'ROL_NM', label : '권한명', hidden : true},
			{name : 'APL_ST_DT', label : '적용시작일자',  hidden : true},
			{name : 'APL_ED_DT', label : '적용종료일자', hidden : true},
			{name : 'USR_ID', label : '사번', width : 100},
			{name : 'CSTR_NM', label : '소속', width : 100},
			{name : 'USR_NM', label : '사원명', width : 100},
// 			{name : 'USR_EML_ADDR', label : '이메일', width : 150},
			{name : 'F_CRT_DTTM', label : '등록일자', align : 'center', width : 100},
			{name : 'F_UPD_DTTM', label : '변경일자', align : 'center', width : 100}
		],
		initval		: {},							// 컬럼 add 시 초기값
		editmode	: true,							// 그리드 editable 여부
		gridtitle	: '사원 목록',					// 그리드명
		multiselect	: true,							// checkbox 여부
		height		: 200,							// 그리드 높이
		shrinkToFit	: true,							// true인경우 column의 width 자동조정, false인경우 정해진 width데로 구현(가로스크롤바필요시 false)
		formid        : 'searchUsr',                     // 조회조건 form id
		selecturl	: '/psys/getPsys1008List',	// 그리드 조회 URL
		saveurl		: '/psys/savePsys1008',		// 그리드 입력/수정/삭제 URL
		events		: {}
	};
	    
	role_grid = new UxGrid(role_config);
	role_grid.init();
	
	user_grid = new UxGrid(user_config);
	user_grid.init();
	    
	role_grid.setGridWidth($('.tblType1').width());
	user_grid.setGridWidth($('.tblType1').width());
	
	// 사용자권한 저장 버튼 클릭 시
	$("#btn_user_save").click(function() {
		user_grid.save();	// {success:function(){alert('save success');}}
	});
	
	// 사용자권한 행추가 버튼 클릭 시    
	$("#btn_user_add").click(function(){
		var row = role_grid.getSelectRows();
		
		if(row.length == 0) {
			alert('권한을 선택하세요');
			return;
		} else if (row.length > 1) {
			alert('권한을 하나만 선택하세요');
			return;
		}
		
		bpopup({
			  url		: "/psys/forward.psys1008p001.adm"
			, params	: {callback : "fn_getUserList", target_id : _menu_id}
			, title		: "시스템 사용자 목록"                          
			, type		: "m"
			, height	: "450px"
			, id        : "psys1008p1"
		});		
	});
	
	// 사용자권한 행삭제 버튼 클릭 시    
	$("#btn_user_del").click(function(){
		user_grid.remove(); // {success:function(){alert('remove success');}}
	});
	
	// 사용자권한 엑셀다운로드 버튼 클릭 시     
	$("#btn_user_excel").click(function(){
		var idx = $('#role_grid').jqGrid('getGridParam', 'selrow');
		
		if(isEmpty(idx))
		{
			alert("권한을 선택해주세요.");
			return;
		}

		var grid_id	= "user_grid";
		var rows	= $('#user_grid').jqGrid('getGridParam', 'rowNum');
		var page	= $('#user_grid').jqGrid('getGridParam', 'page');
		var total	= $('#user_grid').jqGrid('getGridParam', 'total');
		var title	= $('#user_grid').jqGrid('getGridParam', 'gridtitle');
		var url		= "/psys/getPsys1008XlsxDwld";  //페이징 존재
		
		var param	= {};
		param.rol_id= role_grid.getRowData(idx).ROL_ID
		
		param.page	= page;
		param.rows	= rows;
		param.fileName = "사용자권한 목록";

		AdvencedExcelDownload(grid_id, url, param);
	});
	
	//사이트 리스트 취득
	getSystemList();
	
	$("#sys_info").on("change", function () {
		fn_Search();
	});
	
	
	$("#btn_user_search").on("click", function() {
		
		//권한 선택 시 .
		if(isEmpty(target_row)) {
			alert("권한을 선택해주세요.");
			return false;
		} else {
			user_grid.retreive({data:{rol_id:target_row.ROL_ID}});
		}
		
	});
	
});

//grid resizing
$(window).resize(function() {
	role_grid.setGridWidth($('.tblType1').width());
	user_grid.setGridWidth($('.tblType1').width());
});

/**************************************************
 * 공통 버튼 
 **************************************************/

// 조회: 내부 로직 사용자 정의
function fn_Search() {
	
	var sys_cd = $("#sys_info").val();
	$("#sys_cd").val(sys_cd);
	
	role_grid.retreive(); //{success:function(){alert('retreive success');}}
	user_grid.clearGridData();
}
	
// 추가: 내부 로직 사용자 정의
function fn_AddRow() {
}

// 저장: 내부 로직 사용자 정의
function fn_Save() {
}

// 삭제: 내부 로직 사용자 정의
function fn_DelRow() {
}

// 프린트: 내부 로직 사용자 정의
function fn_Print(){
}

// 엑셀다운로드: 내부 로직 사용자 정의
function fn_ExcelDownload(){
}

// 사용자권한 권한사용자 추가 후 콜백
function fn_getUserList(user_list)
{
	var role = role_grid.getSelectRows();
	var role_user = user_grid.getRowData();
	
	if(role.length == 0)
	{
		alert('권한을 선택하세요');
		return;
	}
	else if(role.length > 1)
	{
		alert('권한을 하나만 선택하세요');
		return;
	}
	
	var user_list = user_list || {}
	
	for(var i = 0; i < user_list.length; i++)
	{
		var isExist = false;
		
		// 기존에 추가되어있는지 확인                   
		for(var k = 0; k < role_user.length; k++)
		{
			if(user_list[i].USR_ID == role_user[k].USR_ID)
				isExist = true;
		}

		if(!isExist)
		{
			user_grid.add({
				ROL_ID		: role[0].ROL_ID,
				ROL_NM		: role[0].ROL_NM,
				APL_ST_DT	: role[0].APL_ST_DT.replace(/-/gi, ''),
				APL_ED_DT	: role[0].APL_ED_DT.replace(/-/gi, ''),
				USR_ID		: user_list[i].USR_ID,
				LGN_ID		: user_list[i].LGN_ID,
				USR_NM		: user_list[i].USR_NM,
				USR_EML_ADDR	: user_list[i].USR_EML_ADDR,
				F_CRT_DTTM	: formatDate(new Date()),
				F_UPD_DTTM	: formatDate(new Date())
			});
		}
	}
}

//사이트 리스트 취득
function getSystemList() {
	var html = "";
	ajax({
		url 	: "/pdsp/getPdsp1011ListSit",
		data 	: {sys_cd : _sys_cd}, 
		success : function (data) {
			if (data.RESULT == "S") {
				var sitListCnt = data.rows.length;
				$(data.rows).each(function (index) {
					// 조회 건수가 하나일 경우 단일 하나의 시스템
					if(sitListCnt == 1) {
						html += "<option value="+ this.SYS_CD +" selected='selected' >"+ this.SYS_NM +"</option>"
						return false;
					} else {
						html += "<option value="+ this.SYS_CD +">"+ this.SYS_NM +"</option>"
						$("#sys_info").closest('div').show();
					}
				});
				$("#sys_info").append(html);
			}
		}
	});
}

</script>
</head>
<body id="app">
	<div class="frameWrap">
		<div class="subCon">
			<%@ include file="/WEB-INF/views/pandora3/common/include/btnList.jsp"%>
			<div class="tableTopLeft gridBtn">
				<div class="selectInner" style="display:none;">
					<label for="sys_info">사이트 : </label>
					<select id="sys_info" name="sys_info" class="select" >
						<option value="">전체</option>
					</select>
				</div>
			</div>
			<!-- search -->
            <div class="frameTopTable">
				<form name="search" id="search" name="search" onsubmit="return false">
					<input type="hidden" name="sys_cd" id="sys_cd" value="" />
					<table class="tblType1 mB60">
						<colgroup>
							<col width="15%" />
							<col width="35%" />
							<col width="15%" />
							<col width="35%" />
						</colgroup>
						<tr>
							<th>권한명</th>
							<td><span class="txt_pw"><input type="text" name="rol_nm" id="rol_nm" class="text" value="" maxlength="12" maxlength="300" /></span></td>
							<th>사용여부</th>
							<td><select name="us_yn" id="us_yn" class="select">
									<option value="">전체</option>
									<option value="Y">사용</option>
									<option value="N">미사용</option>
							</select></td>
						</tr>
					</table>
				</form>
			</div>
			<!-- search // -->
			<div class="bgBorder"></div> 
			<!-- Master Grid -->
			<table id="role_grid"></table>
			<div id="role_grid_pager"></div>
			<!-- Master Grid // -->
			<div class="bgBorder"></div>  
			<!-- Detail Grid -->
			<div class="grid_right_btn">
				<div class="grid_right_btn_in">
					<div class="grid_inquiry_area">
						<form name="searchUsr" id="searchUsr" onsubmit="return false">
							<input type="hidden" name="sys_cd" id="sys_cd" value="" />
							<ul class="form_list">
								<li>
									<label for="usr_nm">사용자명 : </label>
									<span class="txt_pw"><input type="text" name="usr_nm" id="usr_nm" class="text" value="" maxlength="12" maxlength="300" /></span>
								</li>
							</ul>
						</form>
					</div>
					<button class="btn_common btn_default" id="btn_user_search">검색</button>
					<button class="btn_common btn_default" id="btn_user_save">저장</button>
					<button class="btn_common btn_default" id="btn_user_add">추가</button>
					<button class="btn_common btn_default" id="btn_user_del">삭제</button>
					<button class="btn_common btn_default" id="btn_user_excel">
					<img src="<c:out value='${pageContext.request.contextPath}' />/resources/pandora3/images/common_new/img_download.png" alt="엑셀 다운로드" />
                    </button>
				</div>
			</div>
			<table id="user_grid"></table>
			<div id="user_grid_pager"></div>
			<!-- Detail Grid // -->
			<br />
		</div>
	</div>
</body>
<%@ include file="/WEB-INF/views/pandora3/common/include/footer.jsp"%>
