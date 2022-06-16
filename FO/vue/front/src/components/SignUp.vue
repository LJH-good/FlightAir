<!--eslint-disable-->
<template>
    <v-form>
        <v-container>
            <form @submit.prevent="onSubmit">
                <div class="container col-sm-6">
                    <h3 style="font-weight:bold">회원가입</h3>
                    <br />
                    <div class="row col-sm-12">
                        <div class="col-sm-4">
                            <i class="fa fa-envelope icon"></i>
                            <label for="email">E-mail(ID)<span class="text-danger">*</span></label>
                        </div>
                        <div class="col-sm-8">
                            <div class="row">
                                <div class="col-sm-12">
                                    <input type="text" class="form-control" placeholder="E-mail" id="email" name="email" maxlength="90" v-model="email" @blur="emailFormatter(email)" @focus="availableEmail = false"/>
                                    <span class="badge badge-danger mt-1" v-show="availableEmail" id="emailCheck">{{emailMsg}}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row col-sm-12">
                        <div class="col-sm-4">
                            <i class="fa fa-user icon"></i>
                            <label for="username">이름<span class="text-danger">*</span></label>
                        </div>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="이름" id="username" name="username" maxlength="20" />
                        </div>
                    </div>

                    <div class="row col-sm-12">
                        <div class="col-sm-4">
                            <i class="fa fa-user icon"></i>
                            <label for="surname">성(영문)<span class="text-danger">*</span></label>
                        </div>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="예)HONG" id="surname" name="surname" maxlength="15" />
                        </div>
                    </div>

                    <div class="row col-sm-12">
                        <div class="col-sm-4">
                            <i class="fa fa-user icon"></i>
                            <label for="name">이름(영문)<span class="text-danger">*</span></label>
                        </div>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="예)GILDONG" id="name" name="name" maxlength="30" />
                        </div>
                    </div>

                    <div class="row col-sm-12">
                        <div class="col-sm-4">
                            <i class="fa fa-user icon"></i>
                            <label for="birthday">생년월일<span class="text-danger">*</span></label>
                        </div>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="birthday" v-model="birthday" placeholder="숫자 8자리 입력(1900-00-00)" maxlength="8" @blur="birthdayFormatter(birthday)" @focus="birthday = ''" />
                        </div>
                    </div>

                    <div class="row col-sm-12">
                        <div class="col-sm-4">
                            <i class="fa fa-plane icon"></i>
                            <label for="pass_num">여권번호<span class="text-danger">*</span></label>
                        </div>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="여권번호" id="pass_num" name="pass_num" maxlength="9" />
                        </div>
                    </div>

                    <div class="row col-sm-12">
                        <div class="col-sm-4">
                            <i class="fa fa-phone icon"></i>
                            <label for="phone">전화번호<span class="text-danger">*</span></label>
                        </div>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="-를 제외한 번호만 입력" id="phone" name="phone" maxlength="11" />
                        </div>
                    </div>

                    <div class="row col-sm-12">
                        <div class="col-sm-4">
                            <i class="fa fa-key icon"></i>
                            <label for="password">비밀번호<span class="text-danger">*</span></label>
                        </div>
                        <div class="col-sm-8">
                            <input type="password" class="form-control" placeholder="비밀번호" id="password" name="password" maxlength="20" />
                        </div>
                    </div>
                    <div class="row col-sm-12">
                        <div class="col-sm-4">
                            <i class="fa fa-key icon"></i>
                            <label for="passwordcheck">비밀번호 확인<span class="text-danger">*</span></label>
                        </div>
                        <div class="col-sm-8">
                            <input type="password" class="form-control" placeholder="비밀번호 확인" id="passwordcheck" name="passwordcheck" maxlength="20" />
                        </div>
                    </div>

                    <div class="row col-sm-12">
                        <div class="col-sm-4">
                            <i class="fa fa-book icon"></i>
                            <label for="addr">주소<span class="text-danger">*</span></label>
                        </div>
                        <div class="col-sm-7">
                            <div class="row">
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" placeholder="우편 번호" id="post" name="post" maxlength="5" />
                                </div>
                                <div class="col-sm-5">
                                    <button id="addr" class="btn btn-warning form-control" @click.prevent="postSearch">
                                        주소 검색
                                    </button>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-12">
                                    <input type="text" placeholder="주소" id="addr1" name="addr1" class="form-control" maxlength="40" />
                                </div>
                                <div class="col-sm-12">
                                    <input type="text" placeholder="상세주소" id="addr2" name="addr2" class="form-control" maxlength="40" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <br />
                    <button type="submit" class="btn btn-info form-control">
                        회원가입
                    </button>
                </div>
            </form>
        </v-container>
    </v-form>
</template>

<script>
/* eslint-disable */

export default {
  data() {
    return {
      availableEmail: false,
      availableEmail2: false,
      username: "",
      surname: "",
      name: "",
      pass_num: "",
      birthday: "",
      email: "",
      password: "",
      passwordcheck: "",
      post: "",
      addr1: "",
      addr2: "",
      phone: "",
      emailMsg: "",
    };
  },
  methods: {
    birthdayFormatter(birthday) {
      if (birthday.length == 8) {
        this.birthday = birthday.replace(/(\d{4})(\d{2})(\d{2})/, "$1-$2-$3");
      }
    },
    emailFormatter(email) {
      // 아이디(이메일) 정규식
      var idTest = /^[\w!#$%&'*+/=?^_{|}~-]+(?:\.[\w!#$%&'*+/=?^_{|}~-]+)*@(?:\w+\.)+\w+$/g;
      document.getElementById("emailCheck").className = "badge badge-danger mt-1";
      this.availableEmail2 = false;

      if(email == ""){
        this.emailMsg = '이메일을 작성해주세요';
        this.availableEmail = true;
      } else if(!idTest.test(email)) {
        this.emailMsg = '이메일 형식을 확인해주세요\nex) email@naver.com';
        this.availableEmail = true;
        return false;
      } else if (email.length > 91) {
        this.emailMsg = '이메일은 최대 90자리까지 입력 가능합니다';
        this.availableEmail = true;
        return false;
      }
      const params = new URLSearchParams();
      params.append("email", email);

      this.$axios.post("/user/idChecked", params).then(res => {
        if (res.data == false) {
          this.emailMsg = '이미 사용중인 이메일입니다.';
          this.availableEmail = true;
        } else {
          this.emailMsg = '유효한 이메일입니다.';
          document.getElementById("emailCheck").className = "badge badge-success mt-1";
          this.availableEmail = true;
          this.availableEmail2 = true;
          
        }
      });
    },
    postSearch() {
      new window.daum.Postcode({
        oncomplete: data => {
          if (data.jibunAddress == "") {
            document.getElementById("addr1").value = data.roadAddress;
          } else {
            document.getElementById("addr1").value = data.jibunAddress;
          }
          document.getElementById("post").value = data.zonecode;
          document.getElementById("addr2").focus();
        }
      }).open();
    },
    onSubmit() {
      this.username = document.getElementById("username").value;
      this.email = document.getElementById("email").value;
      this.surname = document.getElementById("surname").value;
      this.name = document.getElementById("name").value;
      this.pass_num = document.getElementById("pass_num").value;
      this.birthday = document.getElementById("birthday").value;
      this.password = document.getElementById("password").value;
      this.passwordcheck = document.getElementById("passwordcheck").value;
      this.post = document.getElementById("post").value;
      this.addr1 = document.getElementById("addr1").value;
      this.addr2 = document.getElementById("addr2").value;
      this.phone = document.getElementById("phone").value;

      // 이름 글자수 및 한글 정규식
      var nameTest = /^[가-힣]{2,20}$/g;
      // 영문 이름 정규식
      var nametest = /^[A-Z]+$/g;
      // 영문 성 정규식
      var surnametest = /^[A-Z]{1,15}$/g;
      // 생년월일 정규식
      var birthdaytest = /^(19[0-9][0-9]|20\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/g;
      // 여권 번호 정규식
      var passnumtest = /^(.*)[a-zA-Z]{1}[0-9a-zA-Z]{1}[0-9]{7}(.*)$/g;
      // 비밀번호 정규식
      var pwTest = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,20}$/g;
      // 우편번호 정규식
      var postTest = /^[0-9]{5}$/g;
      // 핸드폰번호 정규식
      var phoneTest = /^01[0-9]{9}$/g;
      // 주소 정규식
      var addrTest = /^.{1,40}$/;

      if (this.email == "") {
        alert("이메일을 입력해주세요");
        document.getElementById("email").focus();
        return false;
      } else if(!this.availableEmail2){
        alert("이메일을 확인해주세요");
        return false;
      } else if (this.username == "") {
        alert("이름을 입력해주세요");
        document.getElementById("username").focus();
        return false;
      } else if (!nameTest.test(this.username)) {
        alert("이름을 확인해주세요!(공백불가)\n한글만 입력 가능\n최소 2자, 최대 20자");
        document.getElementById("username").focus();
        return false;
      } else if (this.surname == "") {
        alert("성을 입력해주세요");
        document.getElementById("surname").focus();
        return false;
      } else if (!surnametest.test(this.surname)) {
        alert("대문자 영문만 입력이 가능합니다. 공백불가");
        document.getElementById("surname").focus();
        return false;
      } else if (this.name == "") {
        alert("이름을 입력해주세요");
        document.getElementById("name").focus();
        return false;
      } else if (!nametest.test(this.name)) {
        alert("대문자 영문만 입력이 가능합니다. 공백불가");
        document.getElementById("name").focus();
        return false;
      } else if (this.birthday == "") {
        alert("생년월일을 입력해주세요");
        return false;
      } else if (!birthdaytest.test(this.birthday)) {
        alert("생년월일을 정확히 입력해주세요. \n 숫자만 가능. 공백불가");
        document.getElementById("birthday").focus();
        return false;
      } else if (this.pass_num == "") {
        alert("여권번호를 입력해주세요");
        document.getElementById("pass_num").focus();
        return false;
      } else if (!passnumtest.test(this.pass_num)) {
        alert("여권번호는 영문 1자리 또는 2자리 + 숫자의 형식으로만 입력이 가능합니다. 공백불가");
        document.getElementById("pass_num").focus();
        return false;
      } else if (this.phone == "") {
        alert("핸드폰 번호를 입력해주세요");
        document.getElementById("phone").focus();
        return false;
      } else if (!phoneTest.test(this.phone)) {
        alert("핸드폰 번호를 확인 해주세요\n -를 제외한 번호만 입력해주세요(공백불가)");
        document.getElementById("phone").focus();
        return false;
      } else if (this.password == "") {
        alert("비밀번호를 입력해주세요");
        document.getElementById("password").focus();
        return false;
      } else if (!pwTest.test(this.password)) {
        alert("비밀번호를 확인해주세요!(공백불가)\n최소 8 자, 최대 20 자, 최소 하나의 문자, 하나의 숫자 및 하나의 특수 문자");
        document.getElementById("password").focus();
        return false;
      } else if (this.passwordcheck == "") {
        alert("비밀번호 확인을 입력해주세요");
        document.getElementById("passwordcheck").focus();
        return false;
      } else if (this.password != this.passwordcheck) {
        alert("비밀번호가 일치하지 않습니다");
        document.getElementById("passwordcheck").focus();
        return false;
      } else if (this.post == "") {
        alert("우편번호를 입력해주세요");
        document.getElementById("post").focus();
        return false;
      } else if (!postTest.test(this.post)) {
        alert("우편번호를 확인해주세요");
        document.getElementById("post").focus();
        return false;
      } else if (this.addr1 == "") {
        alert("주소를 입력해주세요");
        document.getElementById("addr1").focus();
        return false;
      } else if (!addrTest.test(this.addr1)) {
        alert("주소는 공백 포함 최대 40자리까지만 가능합니다");
        document.getElementById("addr1").focus();
        return false;
      } else if (this.addr2 == "") {
        alert("상세 주소를 입력해주세요");
        document.getElementById("addr2").focus();
        return false;
      } else if (!addrTest.test(this.addr2)) {
        alert("상세 주소는 공백 포함 최대 40자리까지만 가능합니다");
        document.getElementById("addr2").focus();
        return false;
      }
      const params = new URLSearchParams();
      params.append("user_name", this.username);
      params.append("surname", this.surname);
      params.append("name", this.name);
      params.append("pass_num", this.pass_num);
      params.append("birthday", this.birthday);
      params.append("email", this.email);
      params.append("password", this.password);
      params.append("post", this.post);
      params.append("addr1", this.addr1);
      params.append("addr2", this.addr2);
      params.append("phone", this.phone);

      this.$axios.post("/user/join", params).then(res => {
        if (res.data == "success") {
          alert("회원가입 완료");
        } else {
          alert("회원가입 실패 \n관리자에게 문의하세요");
        }
        window.location.href = "/login";
      });
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
