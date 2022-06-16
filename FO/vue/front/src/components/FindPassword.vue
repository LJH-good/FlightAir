<!--eslint-disable-->
<template>
  <v-form>
    <v-container>
      <form @submit.prevent="onSubmit">
        <div class="container col-sm-6">
          <h3 style="font-weight:bold">비밀번호 찾기</h3>
          <br />
          <div v-if="!isVisible">
            <div class="row col-sm-12">
              <div class="col-sm-4">
                <i class="fa fa-user icon"></i>
                <label for="USERNAME"
                  >이름<span class="text-danger">*</span></label
                >
              </div>
              <div class="col-sm-8">
                <input
                  type="text"
                  class="form-control"
                  placeholder="이름"
                  id="USERNAME"
                  name="USERNAME"
                  maxlength="20"
                />
              </div>
            </div>
            <div class="row col-sm-12">
              <div class="col-sm-4">
                <i class="fa fa-envelope icon"></i>
                <label for="EMAIL"
                  >E-mail(ID)<span class="text-danger">*</span></label
                >
              </div>
              <div class="col-sm-8">
                <input
                  type="text"
                  class="form-control"
                  placeholder="E-mail"
                  id="EMAIL"
                  name="EMAIL"
                />
              </div>
            </div>
            <div class="row col-sm-12">
              <div class="col-sm-4">
                <i class="fa fa-phone icon"></i>
                <label for="PHONE"
                  >전화번호<span class="text-danger">*</span></label
                >
              </div>
              <div class="col-sm-8">
                <input
                  type="text"
                  class="form-control"
                  placeholder="-를 제외한 번호만 입력해주세요(공백불가)"
                  id="PHONE"
                  name="PHONE"
                  maxlength="11"
                />
              </div>
            </div>
          </div>
          <div v-if="isVisible" class="row col-sm-12">
            <div class="col-sm-4">
              <i class="fa fa-key icon"></i>
              <label for="PIN">인증번호</label>
            </div>
            <div class="col-sm-8">
              <input
                type="text"
                class="form-control"
                placeholder="인증번호를 입력해주세요"
                id="PIN"
                name="PIN"
              />
            </div>
          </div>
          <div v-if="isVisible2">
            <div class="row col-sm-12">
              <div class="col-sm-4">
                <i class="fa fa-key icon"></i>
                <label for="PASSWORD"
                  >비밀번호<span class="text-danger">*</span></label
                >
              </div>
              <div class="col-sm-8">
                <input
                  type="password"
                  class="form-control"
                  placeholder="비밀번호"
                  id="PASSWORD"
                  name="PASSWORD"
                />
              </div>
            </div>
            <div class="row col-sm-12">
              <div class="col-sm-4">
                <i class="fa fa-key icon"></i>
                <label for="PASSWORDCHECK"
                  >비밀번호 확인<span class="text-danger">*</span></label
                >
              </div>
              <div class="col-sm-8">
                <input
                  type="password"
                  class="form-control"
                  placeholder="비밀번호 확인"
                  id="PASSWORDCHECK"
                  name="PASSWORDCHECK"
                />
              </div>
            </div>
          </div>
          <br />
          <button type="submit" class="btn btn-info form-control">확인</button>
        </div>
      </form>
    </v-container>
  </v-form>
</template>

<script>
/* eslint-disable */
import axios from "axios";

export default {
  data() {
    return {
      isVisible: false,
      isVisible2: false,
      code: "",
      EMAIL: ""
    };
  },
  methods: {
    onSubmit() {
      if (!this.isVisible) {
        var USERNAME = document.getElementById("USERNAME").value;
        var PHONE = document.getElementById("PHONE").value;
        this.EMAIL = document.getElementById("EMAIL").value;

        var nameTest = /^[가-힣]{2,20}$/;
        var phoneTest =  /^01[0-9]{9}$/g;
        var idTest = /^[\w!#$%&'*+/=?^_{|}~-]+(?:\.[\w!#$%&'*+/=?^_{|}~-]+)*@(?:\w+\.)+\w+$/g;

        if (USERNAME == "") {
          alert("이름을 입력해주세요");
          document.getElementById("USERNAME").focus();
          return false;
        } else if (!nameTest.test(USERNAME)) {
          alert("이름을 확인해주세요!(공백불가)\n한글만 입력 가능\n최대 20자");
          document.getElementById("USERNAME").focus();
          return false;
        }
        if (this.EMAIL == "") {
          alert("이메일을 입력해주세요");
          document.getElementById("EMAIL").focus();
          return false;
        } else if (!idTest.test(this.EMAIL)) {
          alert("이메일 형식을 확인해주세요");
          document.getElementById("EMAIL").focus();
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
        }

        const params = new URLSearchParams();
        params.append("user_name", USERNAME);
        params.append("email", this.EMAIL);
        params.append("phone", PHONE);

        axios.post("/user/findPass", params).then(res => {
          if (res.data) {
            alert("인증번호가 전송되었습니다");
            this.isVisible = true;
            this.code = res.data;
          } else {
            alert("비밀번호 조회 실패 \n입력 정보를 확인해주세요");
          }
        });
      } else if (!this.isVisible2) {
        var a = document.getElementById("PIN").value;
        if (this.code != a) {
          alert("인증번호가 일치하지 않습니다");
        } else {
          document.getElementById("PIN").readOnly = true;
          this.isVisible2 = true;
        }
      } else {
        var PASSWORD = document.getElementById("PASSWORD").value;
        var PASSWORDCHECK = document.getElementById("PASSWORDCHECK").value;
        var pwTest = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,20}$/;

        if (PASSWORD == "") {
          alert("비밀번호를 입력해주세요");
          document.getElementById("PASSWORD").focus();
          return false;
        } else if (PASSWORDCHECK == "") {
          alert("비밀번호 확인을 입력해주세요");
          document.getElementById("PASSWORDCHECK").focus();
          return false;
        } else if (!pwTest.test(PASSWORD)) {
          alert(
            "비밀번호를 확인해주세요!(공백불가)\n최소 8 자, 최대 20 자, 최소 하나의 문자, 하나의 숫자 및 하나의 특수 문자"
          );
          document.getElementById("PASSWORD").focus();
          return false;
        } else if (PASSWORD != PASSWORDCHECK) {
          alert("비밀번호가 일치하지 않습니다");
          document.getElementById("PASSWORDCHECK").focus();
          return false;
        }
        const params = new URLSearchParams();
        params.append("email", this.EMAIL);
        params.append("password", PASSWORD);

        axios.post("/user/changepassword", params).then(res => {
          if (res.data == "success") {
            alert("비밀번호 변경 완료");
          } else {
            alert("비밀번호 변경 실패 \n관리자에게 문의하세요");
          }
          window.location.href = "/login";
        });
      }
    }
  }
};
</script>

<style scoped lang="css">
.input-container {
  display: -ms-flexbox; /* IE10 */
  display: flex;
  width: 100%;
  margin-bottom: 15px;
}

.icon {
  padding: 10px;
  background: skyblue;
  color: white;
  min-width: 50px;
  text-align: center;
}

.input-field {
  width: 100%;
  padding: 10px;
  outline: none;
}

.input-field:focus {
  border: 2px solid skyblue;
}

/* Set a style for the submit button */
.btn {
  background-color: dodgerblue;
  color: white;
  padding: 10px 10px;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
}

.btn:hover {
  opacity: 1;
}
</style>
