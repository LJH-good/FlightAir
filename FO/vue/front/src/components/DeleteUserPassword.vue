<!-- eslint-disable -->

<template>
  <v-app id="app">
    <v-main style="margin:0px auto">
      <h1 style="text-align:center;margin-top:20%">
        회원정보 보호를 위해 비밀번호를 입력해주세요
      </h1>
      <br />
      <v-container style="position: relative;" class="text-xs-center">
        <v-layout row wrap class="text-xs-center">
          <v-flex>
            <v-card flat class="mx-auto" max-width="800">
              <v-row>
                <v-col>
                  <v-form
                    @submit.prevent="mypage()"
                    style="width: 400px; height: 300px; margin-top:20%; margin-left: 200px;"
                  >
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
                    <v-card-actions>
                      <v-btn type="submit" class="login" outlined color="info">
                        <span class="login_content">입력</span>
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
  data() {
    return {
      password: "",
      showPassword: false
    };
  },
  methods: {
    mypage() {
      const params = new URLSearchParams();
      params.append("password", this.password);
      params.append("email", this.$session.get("email"));

      this.$axios.post("/user/mypagelogin", params).then(res => {
        if (res.data.includes("success")) {
          window.location.href = "/deleteuser";
        } else {
          alert(res.data);
        }
      });
    }
  }
};
</script>
<style>
.login {
  background-color: #0077a3;
}
.login_content {
  color: white;
}
</style>
