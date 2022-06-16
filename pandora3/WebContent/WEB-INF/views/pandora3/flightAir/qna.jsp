<%-- 
   1. 파일명   : qna
   2. 파일설명 : 고객 상담
   3. 작성일   : 2022-06-07
   4. 작성자   : LJH
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 헤더파일 include -->
<%@ include file="/WEB-INF/views/pandora3/common/include/header.jsp"%>
<script type="text/javascript">
var qna_grid;
var qna_detail_grid;
var target_row ="";
$(document).ready(function(){
    var up_config = { 
        gridid    : 'qna_grid',	    	// 그리드 id
        pagerid   : '',					// 그리드 페이지 id
        gridBtnYn : 'Y',				// 상단 그리드 버튼 여부 ( 그리드 1개 일때 필수 'Y', 상/하단 그리드 일 경우 상단 그리드만 필수'Y' )
        columns	    : [
						{name : 'BOARD_NUM', hidden:true},
						{name : 'CATEGORY', width:30, label : '문의 유형', align : 'center', sortable : false, editable : false},
						{name : 'TITLE', width:30, label : '제목', align : 'center', sortable : false, editable : false},
						{name : 'EMAIL', width:30, label : '이메일', align : 'center', sortable : false, editable : false},
						{name : 'USER_NAME', width:10, label : '작성자', align : 'center', sortable : false, editable : false},
						{name : 'DATE', width:20, label : '작성일', align : 'center', sortable : false, editable : false, formatter: fn_DateForm},
						{name : 'ANSWER', width:20, label : '답변여부', align : 'center', sortable : false, editable : false, formatter: fn_Answer},
			         ],
        editmode       : false,                                 	// 그리드 editable 여부
        gridtitle      : '상담글 목록',                           		// 그리드명
        multiselect    : false,                             		// checkbox 여부
        formid         : 'search',                                  // 조회조건 form id
        height         : 200,                                       // 그리드 높이
        selecturl      : '/qna/selectQnAList',  		    		// 그리드 조회 URL
        events         : {
	                          ondblClickRow : function (event, rowid, irow, icol) {
	                        	  var row = qna_grid.getRowData(rowid);
	                        	  target_row = row;
	                        	  if (isNotEmpty(row.BOARD_NUM)) {
									qna_detail_grid.retreive({data:{board_num:row.BOARD_NUM}});
	                              }
	                          },
                          }
    };
    var down_config = { 
            gridid    : 'qna_detail_grid',	    	// 그리드 id
            pagerid   : '',							// 그리드 페이지 id
            columns	    : [
            				{name : 'BOARD_NUM', hidden:true},
            				{name : 'CATEGORY', width:30, label : '문의 유형', align : 'center', sortable : false, editable : false},
    						{name : 'TITLE', width:30, label : '제목', align : 'center', sortable : false, editable : false},
    						{name : 'CONTENT', width:50, label : '내용', align : 'center', sortable : false, editable : false},
    						{name : 'ANSWER', width:50, label : '답변', align : 'center', sortable : false, editable : true, required : true, editoptions : {maxlength:100}}
				         ],
            editmode    : true, 									// 그리드 editable 여부
            gridtitle   : '상담글 답변',								// 그리드명
            multiselect : false,                               		// checkbox 여부
            height      : 200,                                		// 그리드 높이
            shrinkToFit : true,                               		// true인경우 column의 width 자동조정, false인경우 정해진 width대로 구현(가로스크롤바필요시 false)
            selecturl   : '/qna/answerQnAList',       				// 그리드 조회 URL
        };
    
    qna_grid = new UxGrid(up_config);
    qna_grid.init();
    
    qna_detail_grid = new UxGrid(down_config);
    qna_detail_grid.init();
    fn_Search();
});
//조회: 내부 로직 사용자 정의
function fn_Search(){
	qna_grid.retreive();
	qna_detail_grid.clearGridData();
}

//날짜 파싱
function fn_DateForm(value, options, rowJson) {      
     return value.substring(0,10);﻿﻿﻿﻿﻿
}

//답변이 없는 부분
function fn_Answer(value, options, rowJson) {
	if (value == '-') {
		return '답변 대기';
	} else {
		return '답변 완료';
	}
}

//저장: 내부 로직 사용자 재정의
function fn_Save(){
	
	var qnaData = getSaveData("qna_detail_grid");
    //입력폼데이터 파라미터형태로 변경
    var data = "qnaData=" + qnaData + "&_pre_url="
          + parent.preUrl.getPreUrl();
    ajax({
   		url: '/qna/qnaAnswer',
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
						<th>문의 유형</th>
						<td><span class="txt_pw">
							<select name="category" id="category">
								<option value="">전체</option>
								<option value="예약 및 이용 문의">예약 및 이용 문의</option>
								<option value="계정 문의">계정 문의</option>
								<option value="건의 사항">건의 사항</option>
								<option value="기타">기타</option>
							</select>
						</span></td>
						<th>이메일</th>
							<td><span class="txt_pw">
							<input type="text" name="email" id="email" class="text" />
							</span></td>
					</tr>
				</table>
			</form>
			<br/>
			<!-- search // -->
			<!-- Master Grid -->
			<table id="qna_grid"></table>
			<!-- Master Grid // -->
			
			<!-- Detail Grid -->
			<table id="qna_detail_grid"></table>
			<!-- Detail Grid // -->
			<br />
		</div>
	</div>
</body>
<%@ include file="/WEB-INF/views/pandora3/common/include/footer.jsp"%>