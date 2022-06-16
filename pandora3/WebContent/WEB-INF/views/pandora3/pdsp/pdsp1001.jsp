<%--
   1. 파일명 : pdsp1001
   2. 파일설명: 통합템플릿조회
   3. 작성일 : 2018-03-28
   4. 작성자 : TANINE
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 헤더파일 include -->
<%@ include file="/WEB-INF/views/pandora3/common/include/header.jsp" %>
<script type="text/javascript">
var menu_id = '<%=parameterMap.getValue("_mnu_seq")%>';
var template_grid;
$(document).ready(function(){
	var template_config = {
		// grid id
		gridid: 'template_grid',
		pagerid: 'template_grid_pager',
		gridBtnYn : 'Y',
		// column info
		columns:[{name:'TMPL_SEQ'	, width:100, label:'메뉴ID'},
				 {name:'TMP_TYPE'	, width:100, label:'메뉴유형(CD)', hidden:true},
				 {name : 'STATUS', label:'상태', align:'center', editable:false, width:20},	
				 {name:'UP_TMPL_NM'	, width:100, label:'상위메뉴명',sortable:false, formatter:setDefaultValue},
				 {name:'TMPL_NM'	, width:100, label:'메뉴명', sortable:false,
				     cellattr: function(rowId, tv, rowObject, cm, rdata) {
				   	  if(isNotEmpty(rowObject.TMPL_NM)){
				   		  return 'style="cursor: pointer; text-decoration: underline;"'}}},
				 {name:'TMPL_TP_CD_NM', width:80, label:'메뉴유형', sortable:false},
				 {name:'MNU_NM'	, width:100, label:'적용메뉴',width:100, sortable:false, formatter:setDefaultValue},
				 {name:'US_YN' , width:30, label:'사용여부', align:'center', width:60, sortable:false, editable:false, formatter:'select', editoptions:{value:'Y:사용;N:미사용'}}
		],
		editmode: true,                   // 그리드 editable 여부
		gridtitle: '메뉴 목록',             // 그리드명
		multiselect: true,                // checkbox 여부
		formid: 'search',                 // 조회조건 form id
		shrinkToFit: true,               // 컬럼 width 자동조정
		autowidth: true,
		height: 400,                      // 그리드 높이
		cellEdit:false,
		selecturl: '/pdsp/getPdsp1001TmplList.adm',   // 그리드 조회 URL
		saveurl: '/pdsp/savePdsp1001TmplList.adm',     // 그리드 입력/수정/삭제 URL
		events: {
				onCellSelect: function(event, rowid, icol, conts) {
					var row = template_grid.getRowData(rowid);
					if (template_grid.getColName(icol) == 'TMPL_NM') {
						parent.addTab("sub"+menu_id, '메뉴상세정보', '/pdsp/forward.pdsp1002.adm', row.TMPL_SEQ);
					}
				}
		}
	};

	template_grid = new UxGrid(template_config);
	template_grid.init();
	template_grid.setGridWidth($('.tblType1').width());

	// 등록/수정 후의 목록 조회
	if(isNotEmpty(_param) && "CHG" == _param) $("#btn_search").trigger("click");

});

// set colum default value
function setDefaultValue(cellvalue, options, rowObject) {
	var defautValue = "-";
	if(!isEmpty(cellvalue)) defautValue = cellvalue;
	return defautValue;
}

// grid resizing
$(window).resize(function() {
	template_grid.setGridWidth($('.tblType1').width());
});

function fn_Search(){
	 if($("#template_grid").jqGrid('getGridParam', 'page') > 1) $("#template_grid").jqGrid('setGridParam', {'page' : 1});
	    template_grid.retreive(); //{success:function(){alert('retreive success');}}
}
function fn_Add(){
	addTabInFrame("/pdsp/forward.pdsp1003.adm");
}
function fn_DelRow(){
	 template_grid.remove(); // {success:function(){alert('remove success');}}
}
function fn_ExcelDownload(){
	var grid_id = "template_grid";
	var rows = $('#template_grid').jqGrid('getGridParam', 'rowNum');
	var page = $('#template_grid').jqGrid('getGridParam', 'page');
	var total = $('#template_grid').jqGrid('getGridParam', 'total');
	var title = $('#template_grid').jqGrid('getGridParam', 'gridtitle');
	var url = "/pdsp/getPdsp1001XlsxDwld.adm";  //페이징 존재
	var param ={};
	param.page=page;
	param.rows=rows;
	param.filename ="메뉴 목록";
	AdvencedExcelDownload(grid_id,url,param);
}

//저장: 내부 로직 사용자 정의
function fn_Save(){

	// 그리드 입력중인 경우 포커스 제거
	$("#template_grid").editCell(0, 0, false);
	
	template_grid.save();  // {success:function(){alert('save success');}}
}

</script>
</head>
<body>
	<div class="frameWrap">
		<div class="subCon">
			<%@ include file="/WEB-INF/views/pandora3/common/include/btnList.jsp" %>
			<div class="frameTopTable">
				<form name="search" id="search" onsubmit="return false">
					<table class="tblType1 mB60">
						<colgroup>
							<col width="11%" />
							<col width="39%" />
							<col width="11%" />
							<col width="*" />
						</colgroup>
						<tr>
							<th>메뉴유형</th>
							<td><%=CodeUtil.getSelectComboList("TMP_TYPE", "tmpl_tp_cd", "전체", "", "", "class=\"select\"")%></td>
							<th>메뉴명</th>
							<td><span class="txt_pw"><input type="text" name="tmpl_nm" id="tmpl_nm" class="w100"/></span></td>
						</tr>
						<tr>
							<th>사용여부</th>
							<td><select name="us_yn" id="us_yn" class="select"><option value="">전체</option><option value="Y">사용</option><option value="N">미사용</option></select></td>
							<th>적용메뉴</th>
							<td><span class="txt_pw"><input type="text" name="mnu_nm" id="mnu_nm" class="w100"/></span></td>
						</tr>
					</table>
				</form>
			</div>
			<div class="bgBorder"></div> 
			<!-- Grid -->
			<table id="template_grid"></table>
			<div id="template_grid_pager"></div>
		</div>
	</div>
</body>
<%@ include file="/WEB-INF/views/pandora3/common/include/footer.jsp" %>