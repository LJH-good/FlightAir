<!-- eslint-disable -->
<template>
  <v-app id="inspire">
    <v-app-bar
      :clipped-left="$vuetify.breakpoint.lgAndUp"
      app
      color="#BBDEFB"
      dark
    >
      <v-toolbar-title style="width: 350px">
        <a href="/" class="white--text" style="text-decoration: none"
          ><v-icon>mdi-airplane</v-icon>&nbsp;FLIGHT AIR</a
        >
      </v-toolbar-title>
      <v-spacer />
      <v-btn depressed v-if="!(this.$session.has('email'))" class="v-btn" color="#BBDEFB" href="/signup">
        <a style="text-decoration:none; font-weight:bold">회원가입</a>
      </v-btn>
      <v-btn v-if="this.$session.has('email')" @click="logout" icon>
        <v-icon>mdi-logout</v-icon>
      </v-btn>
      <v-btn v-if="this.$session.has('email')" href="/mypagepassword" icon>
        <v-icon>mdi-account-circle</v-icon>
      </v-btn>
      <v-btn v-if="!this.$session.has('email')" href="/login" icon>
        <v-icon>mdi-account-circle</v-icon>
      </v-btn>
    </v-app-bar>

    <v-main>
      <v-bottom-navigation color="#2d3753" horizontal>
        <v-btn
          v-if="this.$session.has('email')"
          class="v-btn"
        >
        <b-button-group>
          <b-dropdown text="마이페이지" variant="light">
          <b-dropdown-item  href="/myreservation">예약 내역</b-dropdown-item>
          <b-dropdown-item href="/mypagepassword">회원 정보 수정</b-dropdown-item>
          <b-dropdown-item href="/deleteuserpassword">회원 탈퇴</b-dropdown-item>
        </b-dropdown>
        </b-button-group>
        </v-btn>

        <v-btn v-if="this.$session.has('email')" class="v-btn">
           <b-button-group>
          <b-dropdown text="고객 상담" variant="light">
          <b-dropdown-item  href="/myqna" >상담글 조회</b-dropdown-item>
          <b-dropdown-item href="/qna">상담글 작성</b-dropdown-item>
        </b-dropdown>
        </b-button-group>
        </v-btn>
      </v-bottom-navigation>
    </v-main>

    <router-view />

    <v-footer :padless="true">
      <v-card flat tile width="100%" class="secondary white--text text-center">
        <v-card-text class="white--text pt-10">
          <p>NAME: 이주희</p>
          <p>E-MAIL: joohee.lee@ta9.co.kr</p>
        </v-card-text>

        <v-divider></v-divider>

        <v-card-text class="white--text">
          {{ new Date().getFullYear() }}
        </v-card-text>
      </v-card>
    </v-footer>
  </v-app>
</template>

<script>
/* eslint-disable */
export default {
  methods: {
    logout() {
      // this.$session.remove('USERNO');
      this.$session.destroy();
      window.location.href = "/";
    }
  }
};
</script>
