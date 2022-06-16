<%--
   1. 파일명 : pbbs1003
   2. 파일설명: 게시판 등록
   3. 작성일 : 2018-04-06
   4. 작성자 : TANINE
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 헤더파일 include -->
<%@ include file="/WEB-INF/views/pandora3/common/include/header.jsp" %>
<script type="text/javascript">
var menu_id = _menu_id;

$(document).ready(function() {
	// 등록 버튼 클릭 시
	$("#btn_reg").click(function() {
		if(validChk()) {
			regFrmSubmit();
		}
	});

	// 등록 서브밋
	$("#frm_regInfo").submit(function(e) {
		ajax({
			url:"/pbbs/savePbbs1001ModlInfByFlag.adm",
			data:{
				modl_tp:$("#modl_tp").val(),
				us_yn:$("#us_yn").val(),
				modl_nm:$("#modl_nm").val(),
				ctegry_mst_cd:$("#ctegry_mst_cd").val(),
				sys_cd:$("#sys_cd").val(),
				save_flag:"I"
			},
			success:function(data) {
				if(_boolean_success == data.RESULT) {
					alert('정상적으로 저장되었습니다.');
					var parentTabId = 'tab-' + menu_id;
					var tabs = $("#" + parentTabId, parent.document)[0].parentElement;
					$(tabs).find("li[aria-controls=" + parentTabId + "]").find('span.tab_close_ico').trigger('click');
				}else {
					alert('작업이 정상적으로 실행되지 않았습니다. 잠시후 다시 시도하세요.');
					return;
				}
			}
		});
		e.preventDefault();
	});


	//시스템명에 매핑되는 카테고리정보 조회
	$("#sys_cd").on('change',function(){
		if($("#sys_cd option:selected").val() == ''){
			$("#ctegry_mst_cd").empty();
			$("#ctegry_mst_cd").append("<option value=''>선택</option>");
		}else{
		$("#ctegry_mst_cd").empty();
		$("#ctegry_mst_cd").append("<option value=''>선택</option>");
			ajax({
				url:"/pbbs/getPbbs1001SitSeq",
				data:{sys_cd:$("#sys_cd").val()},
				success:function(data){
					if (data.RESULT == _boolean_success) {
						$(data.rows).each(function () {
							$("#ctegry_mst_cd").append("<option value=" + this.CTEGRY_MST_CD + ">" + this.CTEGRY_MST_NM + "</option>");
						});
					}
				}
			})
		}
	});
	getSystemList();
});


// 입력값 유효성 체크
function validChk() {
	var retVal = true;
	var modl_nm = $("#modl_nm").val();
	var sys_cd = $("#sys_cd option:selected").val();
	if(isEmpty(sys_cd)) {
		alert("시스템 명을 선택해주세요.");
		$("#sys_cd").focus();
		retVal = false;
	}
	if(isEmpty(modl_nm)) {
		alert("게시판명을 입력주세요.");
		$("#modl_nm").focus();
		retVal = false;
	}
	return retVal;
}

// 등록 서브밋
function regFrmSubmit() {
	if(!confirm("해당 내용을 저장하시겠습니까?")) return false;
	$("#frm_regInfo").submit();
}
//사이트 리스트 취득
function getSystemList() {
	var html = "";
	$("#sys_cd").append("<option value=''>선택</option>");
	ajax({
		url 	: "/pdsp/getPdsp1011ListSit",
		success : function (data) {
			if (data.RESULT == "S") {
				var sitListCnt = data.rows.length;
				$(data.rows).each(function (index) {
					// 조회 건수가 하나일 경우 사이트 하나
					if(sitListCnt == 1) {
						html += "<option value="+ this.SYS_CD +" selected='selected' >"+ this.SYS_NM +"</option>"
						return false;
					} else {
						html += "<option value="+ this.SYS_CD +">"+ this.SYS_NM +"</option>"
						$("#sys_info").closest('div').show();
					}
				});
				$("#sys_cd").append(html);
			}
		}
	});
}
</script>
</head>
<body>
	<div class="frameWrap">
		<div class="subCon">
		<h1><%=_title %></h1>
		<form name="frm_regInfo" id="frm_regInfo" onsubmit="return false">
		<div class="rightTableBtn">
			<div class="tableBtn">
				<button type="button" class="btn_common btn_default" id="btn_reg">등록</button>
			</div>
		</div>
		<table class="tblType1">
			<colgroup>
			</colgroup>
			<tr>
				<th>게시판유형</th>
				<td>
					<span class="txt_pw">
						<select class="select" name="modl_tp" id="modl_tp">
							<option value="B_TYPE2">공통 게시판</option>
							<option value="B_TYPE3">질문답변형 게시판</option>
						</select>
					</span>
				</td>
				<th><label for="dept_no" class="vv">사용여부</label></th>
				<td>
					<span class="txt_pw">
						<select class="select" name="us_yn" id="us_yn">
							<option value="Y">사용</option>
							<option value="N">미사용</option>
						</select>
					</span>
				</td>
			</tr>
			<tr>
				<th><label for="dept_no" class="vv">시스템 명</label></th>
				<td>
					<span class="txt_pw">
						<select class="select" name="sys_cd" id="sys_cd">
						</select>
					</span>
				</td>
				<th>카테고리정보</th>
				<td>
					<span class="txt_pw">
						<select class="select" name="ctegry_mst_cd" id="ctegry_mst_cd">
							<option value=''>선택</option>
						</select>
					</span>
				</td>
			</tr>
			<tr>
				<th><label for="modl_nm" class="vv">게시판명</label></th>
				<td colspan="3">
					<span class="txt_pw">
						<input type="text" name="modl_nm" id="modl_nm" onkeyup="javascript:f_chk_byte(this, 100);" placeholder="게시판명을 입력하세요." class="w100"/>
					</span>
				</td>
			</tr>
		</table>
		</form>
		</div>
	</div>
</body>
<%@ include file="/WEB-INF/views/pandora3/common/include/footer.jsp" %>