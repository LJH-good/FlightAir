<!-- eslint-disable -->

<template>
  <form @submit.prevent="onSubmit">
    <div class="MyPage">
      <div class="container fluid">
        <div class="row align-tems-center">
          <div class="col">
            <h2 style="text-align:center;margin-top:20%">기본정보</h2>
            <h6 style="font-size: x-small; text-align:center;}">마지막 수정일 {{Base.recent_update.substring(0, 10) }}</h6>
          </div>
          <div class="col">
            <div class="row" style="margin-top: 10%;">
              <h6 class="mt-3 col-sm-6">
                이름<span class="text-danger">*</span>
              </h6>
              <div class="mt-3 col-sm-6">{{ Base.user_name }}</div>
            </div>
            <v-divider></v-divider>
            <div class="row">
              <h6 class="mt-4 col-sm-6" style="margin-bottom: 0px;">
                아이디(이메일)<span class="text-danger">*</span>
              </h6>
              <div class="mt-4 col-sm-6">{{ Base.email }}</div>
            </div>
            <v-divider></v-divider>
            <div class="row">
              <h6 class="mt-4 col-sm-6" style="margin-bottom: 0px;">
                성(영문)<span class="text-danger">*</span>
              </h6>
              <div class="mt-4 col-sm-6">{{ Base.surname }}</div>
            </div>
            <v-divider></v-divider>
            <div class="row">
              <h6 class="mt-4 col-sm-6" style="margin-bottom: 0px;">
                이름(영문)<span class="text-danger">*</span>
              </h6>
              <div class="mt-4 col-sm-6">{{ Base.name }}</div>
            </div>
            <v-divider></v-divider>
            <div class="row">
              <h6 class="mt-4 col-sm-6" style="margin-bottom: 0px;">
                생년월일<span class="text-danger">*</span>
              </h6>
              <div class="mt-4 col-sm-6">{{ Base.birthday }}</div>
            </div>
            <v-divider></v-divider>
            <h6 class="mt-3">여권번호<span class="text-danger">*</span></h6>
            <input
              type="text"
              class="form-control mt-2"
              id="pass_num"
              placeholder="여권번호"
              v-model="Base.pass_num"
              maxlength="9"
            />
            <v-divider></v-divider>
            <h6 class="mt-3">핸드폰번호<span class="text-danger">*</span></h6>
            <input
              type="text"
              class="form-control mt-2"
              id="PHONE"
              placeholder="핸드폰번호(-를 제외한 번호만 입력)"
              v-model="Base.phone"
              maxlength="11"
            />
            <v-divider></v-divider>
            <h6 class="mt-3">주소<span class="text-danger">*</span></h6>
            <input
              type="text"
              class="form-control mt-2"
              id="POST"
              placeholder="우편번호"
              v-model="Base.post"
              maxlength="5"
            />
            <input
              type="text"
              class="form-control mt-2"
              id="ADDR1"
              placeholder="주소"
              v-model="Base.addr1"
              maxlength="40"
            />
            <input
              type="text"
              class="form-control mt-2"
              id="ADDR2"
              placeholder="상세주소"
              v-model="Base.addr2"
              maxlength="40"
            />
            <input
              type="button"
              @click="this.DaumPost"
              class="btn btn-primary btn-default btn-sm mt-2"
              value="우편번호 검색"
            />
            <v-divider></v-divider>
            <div class="extra row">
              <a href="/deleteuser" class="extra2">회원탈퇴</a>
              <a href="/changepassword">비밀번호 변경</a>
            </div>
            <button type="submit" class="btn btn-info form-control">
              변경사항 저장하기
            </button>
          </div>
          <div class="col"></div>
        </div>
      </div>
    </div>
  </form>
</template>

<script>
/* eslint-disable */

export default {
  data() {
    return {
      Base: []
    };
  },
  mounted() {
    this.mydata();
  },
  methods: {
    mydata() {
      const params = new URLSearchParams();
      params.append("email", this.$session.get("email"));

      this.$axios.post("/user/userinfo", params).then(res => {
        this.Base = res.data;
      });
    },
    DaumPost() {
      new window.daum.Postcode({
        oncomplete: data => {
          if (data.jibunAddress == "") {
            document.getElementById("ADDR1").value = data.roadAddress;
          } else {
            document.getElementById("ADDR1").value = data.jibunAddress;
          }
          document.getElementById("POST").value = data.zonecode;
          document.getElementById("ADDR2").focus();
        }
      }).open();
    },
    onSubmit() {
      var pass_num = document.getElementById("pass_num").value;
      var PHONE = document.getElementById("PHONE").value;
      var POST = document.getElementById("POST").value;
      var ADDR1 = document.getElementById("ADDR1").value;
      var ADDR2 = document.getElementById("ADDR2").value;

      var passnumtest = /^(.*)[a-zA-Z]{1}[0-9a-zA-Z]{1}[0-9]{7}(.*)$/;
      var postTest = /^[0-9]{5}$/g;
      var phoneTest = /^01[0-9]{9}$/g;

      if (pass_num == "") {
        alert("여권 번호를 입력해주세요");
        document.getElementById("pass_num").focus();
        return false;
      } else if (!passnumtest.test(pass_num)) {
        alert(
          "여권번호는 영문 1자리 또는 2자리 + 숫자의 형식으로만 입력이 가능합니다. 공백불가"
        );
        document.getElementById("pass_num").focus();
        return false;
      } else if (PHONE == "") {
        alert("핸드폰 번호를 입력해주세요");
        document.getElementById("PHONE").focus();
        return false;
      } else if (!phoneTest.test(PHONE)) {
        alert(
          "핸드폰 번호를 확인 해주세요\n -를 제외한 번호만 입력해주세요(공백불가)"
        );
        document.getElementById("PHONE").focus();
        return false;
      } else if (POST == "" || ADDR1 == "" || ADDR2 == "") {
        alert("주소를 입력해주세요");
        document.getElementById("POST").focus();
        return false;
      } else if (!postTest.test(POST)) {
        alert("우편번호를 확인해주세요");
        document.getElementById("POST").focus();
        return false;
      }

      const params = new URLSearchParams();
      params.append("pass_num", pass_num);
      params.append("phone", PHONE);
      params.append("post", POST);
      params.append("addr1", ADDR1);
      params.append("addr2", ADDR2);
      params.append("email", this.$session.get("email"));

      this.$axios.post("/user/updateinfo", params).then(res => {
        if (res.data == "success") {
          alert("회원정보 수정 완료");
          window.location.href = "/";
        } else {
          alert("회원정보 수정 실패 \n 관리자에게 문의하세요");
        }
      });
    }
  }
};
</script>

<style scoped>
.btn-primary {
  width: 30%;
}
.v-application .mt-3 {
  margin-top: 30px !important;
}
.row + .row {
  margin-top: 0px;
}
.extra {
  margin-bottom: 20px;
}
.extra2 {
  padding-left: 12px;
  padding-right: 205px;
}
</style>
