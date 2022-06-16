<!-- eslint-disable -->

<template>
  <v-app id="app">
    <v-main style="margin:0px auto">
      <h1 style="text-align:center;margin-top:20%">탈퇴 전 확인하세요</h1>
      <br />
      <v-container style="position: relative;" class="text-xs-center">
        <v-layout row wrap class="text-xs-center">
          <v-flex>
            <v-card flat class="mx-auto" max-width="800">
              <v-row>
                <v-col>
                  <v-form
                    @submit.prevent="deleteuser()"
                    style="height: 300px; margin-top:20%; text-align:center;"
                  >
                    <div class="mx-3">
                      <div class="mx-1">
                        <p>탈퇴하시면 이용 중인 정보가 삭제되며</p>
                        <p>모든 데이터는 복구가 불가능합니다</p>
                        <br />
                        <p style="color:red;">
                          기간이 지나지 않은 예약 내역이 존재할 시 탈퇴가
                          불가능합니다
                        </p>
                        <br />
                      </div>
                    </div>
                    <div class="mx-3">
                      <div class="mx-1">
                        <div id="checkbox">
                          <input
                            type="checkbox"
                            value="checked"
                            v-model="checked"
                          />
                          <label
                            >안내 사항을 모두 확인하였으며, 이에
                            동의합니다</label
                          >
                        </div>
                      </div>
                    </div>
                    <v-card-actions>
                      <v-btn
                        type="submit"
                        class="form-control"
                        outlined
                        color="info"
                        style="background: cornflowerblue;"
                      >
                        <span class="login_content">회원 탈퇴</span>
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
  data() {},
  methods: {
    deleteuser() {
      if (this.checked) {
        const params = new URLSearchParams();
        params.append("email", this.$session.get("email"));
        this.$axios.post("/user/deletereservation", params).then(res => {
          if (res.data == "success") {
            alert("진행 예정인 예약 내역이 존재하여 탈퇴가 불가합니다.");
            return false;
          } else {
            this.$axios.post("/user/deleteuser", params).then(res => {
              if (res.data.includes("success")) {
                this.$session.destroy();
                alert("회원 탈퇴 완료");
                window.location.href = "/";
              } else {
                alert("회원 탈퇴 실패\n 관리자에게 문의하세요.");
              }
            });
          }
        });
      } else {
        alert("동의함에 체크해주세요");
      }
    }
  }
};
</script>
<style scoped>
#checkbox input {
  display: inline-block;
  margin-right: 10px;
}
#checkbox label {
  display: inline-block;
}
</style>
