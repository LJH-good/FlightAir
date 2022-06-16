<!--eslint-disable-->
<template>
  <v-form>
    <v-container>
      <form @submit.prevent="onSubmit">
        <div class="container col-sm-6">
          <h3 style="font-weight:bold">아이디 찾기</h3>
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
            <br />
            <button type="submit" class="btn btn-info form-control">
              아이디 조회
            </button>
          </div>
          <div v-if="isVisible" class="search">
            <div class="row col-sm-12" v-for="emails in EMAIL" :key="emails.id">
              <div class="col-sm-4">
                <i class="fa fa-user icon"></i>
                <label for="EMAIL">EMAIL(ID)</label>
              </div>
              <div class="result col-sm-8">
                <p>{{ emails.email }}</p>
              </div>
            </div>
            <button @click="redirectlogin()" class="btn btn-info form-control">
              로그인 바로가기
            </button>
          </div>
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
      EMAIL: []
    };
  },
  methods: {
    redirectlogin() {
      window.location.href = "/login";
    },
    onSubmit() {
      if (!this.isVisible) {
        var USERNAME = document.getElementById("USERNAME").value;
        var PHONE = document.getElementById("PHONE").value;

        var nameTest = /^[가-힣]{2,20}$/;
        var phoneTest = /^01[0-9]{9}$/g;

        if (USERNAME == "") {
          alert("이름을 입력해주세요");
          document.getElementById("USERNAME").focus();
          return false;
        } else if (!nameTest.test(USERNAME)) {
          alert("이름을 확인해주세요!(공백불가)\n한글만 입력 가능\n최대 20자");
          document.getElementById("USERNAME").focus();
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
        params.append("phone", PHONE);

        axios.post("/user/findId", params).then(res => {
          if (res.data) {
            this.isVisible = true;
            this.EMAIL = res.data;
          } else {
            alert("아이디 조회 실패 \n입력 정보를 확인해주세요");
          }
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
.search {
  padding-bottom: 100px;
}
.result {
  padding-top: 18px;
}
</style>
