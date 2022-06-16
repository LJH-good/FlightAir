<!-- eslint-disable -->

<template>
  <v-app id="app">
    <v-main style="margin:0px auto">
      <h1 style="text-align:center;">비밀번호 변경</h1>
      <br />
      <div class="container">
        <v-form
          @submit.prevent="changepass"
          style="width: 600px; height: 300px;"
        >
          <label for="oripassword"
            >현재 비밀번호<span class="text-danger">*</span></label
          >
          <input
            type="password"
            class="form-control"
            id="oripassword"
            v-model="oripassword"
            placeholder="현재 비밀번호"
          />

          <label for="newpassword" style="margin-top: 20px;"
            >새로운 비밀번호<span class="text-danger">*</span></label
          >
          <input
            type="password"
            class="form-control"
            id="newpassword"
            v-model="newpassword"
            placeholder="새로운 비밀번호"
          />

          <label for="passwordcheck" style="margin-top: 20px;"
            >비밀번호 확인<span class="text-danger">*</span></label
          >
          <input
            type="password"
            class="form-control"
            id="passwordcheck"
            v-model="passwordcheck"
            placeholder="비밀번호 확인"
          />

          <input type="submit" value="비밀번호 변경" />
        </v-form>
      </div>
    </v-main>
  </v-app>
</template>

<script>
/* eslint-disable */

export default {
  data() {
    return {
      oripassword: "",
      newpassword: "",
      passwordcheck: ""
    };
  },
  methods: {
    changepass() {
      var pwTest = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,20}$/;

      if (this.oripassword == "") {
        alert("현재 비밀번호를 입력해주세요");
        document.getElementById("oripassword").focus();
        return false;
      } else if (this.newpassword == "") {
        alert("새로운 비밀번호를 입력해주세요");
        document.getElementById("newpassword").focus();
        return false;
      } else if (this.passwordcheck == "") {
        alert("비밀번호 확인을 입력해주세요");
        document.getElementById("passwordcheck").focus();
        return false;
      } else if (this.newpassword != this.passwordcheck) {
        alert("비밀번호가 일치하지 않습니다");
        document.getElementById("passwordcheck").focus();
        return false;
      } else if (!pwTest.test(this.newpassword)) {
        alert(
          "비밀번호를 확인해주세요!(공백불가)\n최소 8 자, 최대 20 자, 최소 하나의 문자, 하나의 숫자 및 하나의 특수 문자"
        );
        document.getElementById("newpassword").focus();
        return false;
      }
      const params = new URLSearchParams();
      params.append("password", this.oripassword);
      params.append("email", this.$session.get("email"));

      this.$axios.post("/user/oripass", params).then(res => {
        if (res.data == "success") {
          const params2 = new URLSearchParams();
          params2.append("password", this.newpassword);
          params2.append("email", this.$session.get("email"));

          this.$axios.post("/user/updatepassword", params2).then(res => {
            if (res.data == "success") {
              alert("비밀번호 변경 완료");
              window.location.href = "/mypage";
            } else {
              alert("비밀번호 변경 실패\n 관리자에게 문의하세요.");
            }
          });
        } else {
          alert(res.data);
        }
      });
    }
  }
};
</script>
<style scoped lang="css">
input[type="text"],
select {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

input[type="submit"] {
  width: 100%;
  background-color: cornflowerblue;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type="submit"]:hover {
  background-color: cornflowerblue;
}

div.container {
  border-radius: 5px;
  padding-bottom: 80px;
}
</style>
