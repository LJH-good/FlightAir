<%-- 
   1. 파일명   : insertPrice
   2. 파일설명 : 가격 입력 / 수정
   3. 작성일   : 2022-05-25
   4. 작성자   : LJH
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 헤더파일 include -->
<%@ include file="/WEB-INF/views/pandora3/common/include/header.jsp" %>
<script type="text/javascript">
var price_grid;
var schedule_nums;
//행추가 여러개 방지(1개씩 추가)
var rowState = true;

$(document).ready(function(){
	getSelectData();
	
	var grid_config = {
		gridid		: 'price_grid',
		pagerid		: '',
		gridBtnYn : 'Y',				// 상단 그리드 버튼 여부 ( 그리드 1개 일때 필수 'Y', 상/하단 그리드 일 경우 상단 그리드만 필수'Y' )
		columns		: [
 			{name : 'PRICE_NUM', width : 10, label : '가격 번호', align : 'center', editable : false , required : true, edittype : 'text',  editoptions : {maxlength:5}},        
			{name : 'SCHEDULE_NUM', width : 10, label : '스케줄 번호', editable : true, align : 'center', required : true, edittype :  'select', editoptions : { value : schedule_nums },
 				cellattr : function(rowId, tv, rowObject, cm, rdata) {
                    if (tv != "&#160;") {
                       return "class='not-editable-cell'";
                    }
                }
            },
 			{name : 'SEAT_CLASS', width : 20, label : '좌석 등급', align : 'center', editable : true, edittype : 'select', required : true, editoptions : {value:'비즈니스:비즈니스;이코노미:이코노미'},
            	cellattr : function(rowId, tv, rowObject, cm, rdata) {
                    if (tv != "&#160;") {
                       return "class='not-editable-cell'";
                    }
                }
 			},
			{name : 'PRICE', width : 20, label : '가격', align : 'center', editable : true,  required : true, editoptions : {maxlength : 10}},
			{name : 'ADD_DATE', width:20, label : '등록일', align : 'center', sortable : false, editable : false, formatter: fn_DateForm },
			{name : 'RECENT_UPDATE', width:20, label : '마지막 수정일', align : 'center', sortable : false, editable : false, formatter: fn_DateForm}
        ],
		editmode	: true,										// 그리드 editable 여부
		gridtitle	: '가격 목록',									// 그리드명
		multiselect	: true,										// checkbox 여부
		formid		: 'search',									// 조회조건 form id
		height		: 300,										// 그리드 높이
        shrinkToFit	: true,										// true인경우 column의 width 자동조정, false인경우 정해진 width대로 구현(가로스크롤바필요시 false)
        selecturl	: '/flight/priceList',						// 그리드 조회 URL
	};
    
    price_grid = new UxGrid(grid_config);
    price_grid.init();
    price_grid.setGridWidth($('.tblType1').width());
    
    fn_Search();
     
});

// grid resizing
$(window).resize(function() {
	price_grid.setGridWidth($('.tblType1').width());
});


//조회: 내부 로직 사용자 정의
function fn_Search(){
	rowState = true;
	price_grid.retreive();
}

//데이터 가져오기: schedule_nums
function getSelectData() {
	ajax({
       url : '/flight/getScheduleNum',
       async : false,
       success : function(data) {
          if (data.RESULT == "S"){
        	  schedule_nums = data.SCHEDULE_NUMS;
          }
          else {
             if (isNotEmpty(data.MSG)){ alert("적용 실패!") }
          }
       },
    });
}

//날짜 파싱
function fn_DateForm(value, options, rowJson) {    
	 if(value == null){ return ""; }
     return value.substring(0,10);﻿﻿﻿﻿﻿
}

//추가: 내부 로직 사용자 정의
function fn_AddRow() {
	if (rowState) {
		var newPriceNum;
		ajax({
			url : '/flight/pricenumInsert',
			async : false,
			success : function(data) {
				newPriceNum = data.rows[0].PRICE_NUM;

				var cnt = 0;
				for (var i = 1; i < newPriceNum.length; i++) { 
					if (newPriceNum.charAt(i) != '0') {
						break;
					}
					cnt++;
				}
				newPriceNum = newPriceNum.substring(0, cnt + 1)
						+ (Number(newPriceNum.substring(cnt,
								newPriceNum.length)) + 1);
			},
		});
		price_grid.add({
			APLY_START_DATE : $.timestampToString(new Date()),
			PRICE_NUM : newPriceNum,
		});
		rowState = false;
	} else {
		alert("가격 추가는 1개씩 가능합니다. \n 기존 작업을 마무리해주세요.");
	}
}

// 유효성 검사
function fn_validationCheck(selectRow) {
	for(var i = 0; i < selectRow.length; i++){
		var price = Number(selectRow[i].PRICE);
		var pricetest = /^[0-9]{4,10}$/;
		
		var schedule_num = selectRow[i].SCHEDULE_NUM;
		var seat_class = selectRow[i].SEAT_CLASS;
		var overlap = false;
		
		if(selectRow[i].JQGRIDCRUD == "C"){
			ajax({
				url : '/flight/overlapPrice',
				data : { schedule_num : schedule_num, seat_class : seat_class },
				async : false,
				success : function(data) {
					if (data.RESULT == "S") {
						if(data.OVERLAP == "Y"){
							overlap = true;
						}
					} else {
						if (isNotEmpty(data.MSG)) {
							alert("적용 실패!")
						}
					}
				},
			});
		}
		
		if(!pricetest.test(price)){
			alert("가격은 숫자만 입력 가능합니다 \n 최소 4자리, 최대 10자리 입력 가능합니다 ");
			return false;
		} else if(price == 0){
			alert("가격은 0이 될 수 없습니다");
			return false;
		} else if(overlap){
			alert(schedule_num + "의 " + seat_class + "좌석 등급의 가격은 존재합니다.");
			return false;
		}
	}
	return true;
}

//저장: 내부 로직 사용자 재정의
function fn_Save(rowId) {
	if (isEmpty(getSaveData("price_grid"))) {
		return false;
	} else if (getSaveData("price_grid") == '[]') {
		alert("데이터를 선택해주세요");
		return false;
	}
	var selectRow = price_grid.getSelectRows();
	
	if (fn_validationCheck(selectRow)) {
		var priceData = getSaveData("price_grid");
		//입력폼데이터 파라미터형태로 변경
		var data = "priceData=" + priceData + "&_pre_url="
				+ parent.preUrl.getPreUrl();

		ajax({
			url : '/flight/priceInsert',
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
	}else{
		fn_Search();
	}
}

//삭제: 내부 로직 사용자 삭제
function fn_Delete() {

//jqgrid grid 데이터 json 형태로 생성
	var priceData;
	if (getSaveData("price_grid") == '[]') {
		alert("삭제할 가격 번호를 선택해주세요.");
		return false;
	} else {
		if (!confirm("해당 가격을 삭제하시겠습니까?\n삭제 후 복구가 불가능 합니다.")) {
			return false;
		} else {
			price_grid.remove(); // {success:function(){alert('remove success');}}
			priceData = getSaveData("price_grid"); //grid_id
		}
	}

	//입력폼데이터 파라미터형태로 변경
	var data = "priceData=" + priceData + "&_pre_url="
			+ parent.preUrl.getPreUrl();
	ajax({
		url : '/flight/priceDelete',
		data : data,
		async : false,
		success : function(data) {
			if (data.RESULT == "S") {
				alert("삭제되었습니다.");
				fn_Search();
				return;
			} else {
				alert("가격 삭제 실패!")
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
			<form name="search" id="search" name="search" onsubmit="return false">
	            <table class="tblType1 mB60" >
	            	<colgroup>
						<col width="15%" />
						<col width="35%" />
						<col width="15%" />
						<col width="35%" />
					</colgroup>   
		            <tr>
						<th>스케줄 번호</th>
						<td><span class="txt_pw"><input type="text" name="schedule_num" id="schedule_num" class="text" /></span></td>
						<th>좌석 등급</th>
						<td><span class="txt_pw">
							<select name="seat_class" id="seat_class">
								<option value="">전체</option>
								<option value="비즈니스">비즈니스</option>
								<option value="이코노미">이코노미</option>
							</select>
						</span></td>
					</tr>
	            </table>
			</form>
			<br/>
	        <!-- search // -->
	        
	        <!-- Grid -->
	        <table id="price_grid"></table> 
	        <!-- Grid // -->    
	    </div>
    </div>
</body>
<%@ include file="/WEB-INF/views/pandora3/common/include/footer.jsp" %>
