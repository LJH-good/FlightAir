<!-- eslint-disable -->

<template>
  <v-app id="app">
    <v-main style="margin:0px auto">
      <h1 style="text-align:center;">로그인</h1>
      <br />
      <v-container style="position: relative;" class="text-xs-center">
        <v-layout row wrap class="text-xs-center">
          <v-flex>
            <v-card flat class="mx-auto" max-width="800">
              <v-row>
                <v-col>
                  <v-form
                    @submit.prevent="login()"
                    style="width: 600px; height: 300px; margin-top:20%;"
                  >
                    <div class="mx-3">
                      <div class="mx-1">
                        <v-text-field
                          label="Username (E-mail)"
                          prepend-icon="mdi-account-circle"
                          v-model="email"
                          required
                        />
                      </div>
                    </div>
                    <div class="mx-3">
                      <div class="mx-1">
                        <v-text-field
                          label="Password"
                          :type="showPassword ? 'text' : 'password'"
                          prepend-icon="mdi-lock"
                          :append-icon="
                            showPassword ? 'mdi-eye' : 'mdi-eye-off'
                          "
                          @click:append="showPassword = !showPassword"
                          v-model="password"
                          required
                        />
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-sm-3"></div>
                      <div class="col-sm-3"></div>
                      <div class="findid col-sm-3 text-right">
                        <a href="/FindId" style="text-decoration: none"
                          >아이디찾기</a
                        >
                      </div>
                      <div class="findpassword col-sm-3 text-left">
                        <a href="/FindPassword" style="text-decoration: none"
                          >비밀번호 찾기</a
                        >
                      </div>
                    </div>
                    <v-divider></v-divider>
                    <v-card-actions>
                      <v-btn
                        href="/signup"
                        class="register"
                        outlined
                        color="info"
                      >
                        <span class="register_content">회원가입</span>
                      </v-btn>
                      <v-btn type="submit" class="login" outlined color="info">
                        <span class="login_content">로그인</span>
                      </v-btn>
                    </v-card-actions>
                  </v-form>
                </v-col>
              </v-row>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
/* eslint-disable */

export default {
  name: "login",
  data() {
    return {
      email: "",
      password: "",
      showPassword: false
    };
  },
  methods: {
    login() {
      const params = new URLSearchParams();
      params.append("email", this.email);
      params.append("password", this.password);

      this.$axios.post("/user/login", params).then(res => {
        if (res.data.includes("success")) {
          var inUser = res.data.split("success")[1];
          this.$session.set("email", inUser);
          window.location.href = "/";
        } else {
          alert(res.data);
        }
      });
    }
  }
};
</script>
<style>
.register {
  background-color: #3f3c67;
}

.register_content {
  color: white;
}

.login {
  background-color: #0077a3;
}

.login_content {
  color: white;
}
.findid {
  padding-left: 400px;
}
.findpassword {
  padding-left: 20px;
}
</style>
