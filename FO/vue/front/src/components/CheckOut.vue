<!--eslint-disable-->
<template>
  <form @submit.prevent="onSubmit">
    <div class="container" style="padding:5%">
      <h2>여정 정보</h2>
      <b-card>
        <div class="row">
          <div class="col-md-3 col-sm-12 col-xs-12">
            <h4>가는 편</h4>
          </div>
          <div class="col-md-3 col-sm-12 col-xs-12">
            <span class="area">{{ Go.de_country }}</span>
            <b-icon
              icon="arrow-right"
              style="width: 20px; height: 20px;"
            ></b-icon>
            <span class="area">{{ Go.arr_country }}</span>
          </div>
          <div
            class="col-md-3 col-sm-12 col-xs-12"
            style="padding-top: inherit;"
          >
            <span class="area">{{ Go.de_date }}</span>
            <br />
            <span class="area">출발시각</span>
            <span class="area">{{ Go.de_time }}</span>
            <span class="area">– 도착시각</span>
            <span class="area">{{ Go.arr_time }}</span>
          </div>

          <div class="col-md-3 col-sm-12 col-xs-12">
            <span class="area">{{ Go.plane_num }}</span>
            <span class="area">{{ Go.seat_class }}</span>
          </div>
        </div>
      </b-card>
      <br />
      <b-card>
        <div class="row">
          <div class="col-md-3 col-sm-12 col-xs-12">
            <h4>오는 편</h4>
          </div>
          <div class="col-md-3 col-sm-12 col-xs-12">
            <span class="area">{{ Back.de_country }}</span>
            <b-icon
              icon="arrow-right"
              style="width: 20px; height: 20px;"
            ></b-icon>
            <span class="area">{{ Back.arr_country }}</span>
          </div>
          <div
            class="col-md-3 col-sm-12 col-xs-12"
            style="padding-top: inherit;"
          >
            <span class="area">{{ Back.de_date }}</span>
            <br />
            <span class="area">출발시각</span>
            <span class="area">{{ Back.de_time }}</span>
            <span class="area">– 도착시각</span>
            <span class="area">{{ Back.arr_time }}</span>
          </div>

          <div class="col-md-3 col-sm-12 col-xs-12">
            <span class="area">{{ Back.plane_num }}</span>
            <span class="area">{{ Back.seat_class }}</span>
          </div>
        </div>
      </b-card>
      <br />
      <b-card>
        <div class="row">
          <div class="col-md-3 col-sm-12 col-xs-12">
            <h4>항공 운송료</h4>
          </div>
          <div class="col-md-3 col-sm-12 col-xs-12">
            <span class="area">총액</span>
          </div>
          <div class="col-md-3 col-sm-12 col-xs-12">
            <span class="area">KRW {{ (Go.price + Back.price) | comma }}</span>
          </div>
        </div>
      </b-card>
      <br />
      <b-card
        border-variant="primary"
        header="승객정보"
        header-bg-variant="primary"
        header-text-variant="white"
        align="center"
      >
        <div class="row">
          <div class="col-md-6 col-sm-12 col-xs-12">
            <p>승객 성</p>
            <p class="form-control">{{ ui.surname }}</p>
          </div>
          <div class="col-md-6 col-sm-12 col-xs-12">
            <p>승객 이름</p>
            <p class="form-control">{{ ui.name }}</p>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6 col-sm-12 col-xs-12">
            <p>여권번호</p>
            <p class="form-control">{{ ui.pass_num }}</p>
          </div>
          <div class="col-md-6 col-sm-12 col-xs-12">
            <p>생년월일</p>
            <p class="form-control">{{ ui.birthday }}</p>
          </div>
        </div>
        <br />
        <div class="row">
          <div class="col-md-6 col-sm-12 col-xs-12">
            <p>전화번호</p>
            <p class="form-control">{{ ui.phone }}</p>
          </div>
          <div class="col-md-6 col-sm-12 col-xs-12">
            <p>이메일</p>
            <p class="form-control">{{ ui.email }}</p>
          </div>
        </div>
      </b-card>
      <br />
      <div style="padding-bottom: 50px;">
        <v-btn
          type="submit"
          outlined
          color="info"
          style="float: right; background: blue;"
        >
          <span class="plane-search_content" style="color: white;"
            >예약하기</span
          >
          <p></p>
        </v-btn>
      </div>
    </div>
  </form>
</template>

<script>
/* eslint-disable */
export default {
  data() {
    return {
      date: new Date(new Date().getTime()).toISOString().slice(0, -14),
      Go: [],
      Back: [],
      schedule_num: this.$route.query.go_schedule,
      schedule_num2: this.$route.query.back_schedule,
      seat_class: this.$route.query.go_seat_class,
      seat_class2: this.$route.query.back_seat_class,
      ui: []
    };
  },
  filters: {
    comma(val) {
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
  },
  mounted() {
    this.userinfo();
    this.Reservation();
  },
  methods: {
    userinfo() {
      const params = new URLSearchParams();
      params.append("email", this.$session.get("email"));
      this.$axios.post("/user/userinfo", params).then(res => {
        this.ui = res.data;
      });
    },
    Reservation() {
      const params = new URLSearchParams();
      params.append("schedule_num", this.schedule_num);
      params.append("seat_class", this.seat_class);
      this.$axios.post("/flight/getschedule", params).then(res => {
        this.Go = res.data;
      });

      const params2 = new URLSearchParams();
      params2.append("schedule_num", this.schedule_num2);
      params2.append("seat_class", this.seat_class2);
      this.$axios.post("/flight/getschedule", params2).then(res => {
        this.Back = res.data;
      });
    },

    onSubmit() {
      const params = new URLSearchParams();
      params.append("email", this.$session.get("email"));
      params.append("go_schedule", this.schedule_num);
      params.append("back_schedule", this.schedule_num2);
      params.append("go_seat_class", this.Go.seat_class);
      params.append("back_seat_class", this.Back.seat_class);

      this.$axios.post("/flight/beforereservation", params).then(res => {
      if (res.data == "success") {
        alert("해당 날짜에 예약 내역이 존재하여 예약 불가합니다.");
        return false;
      } else {
        this.$axios.post("/flight/insertreservation", params).then(res => {
          if (res.data == "success") {
            alert("예약 완료");
            window.location.href = "/myreservation";
          } else {
            alert("예약 실패 \n 관리자에게 문의하세요");
            window.location.href = "/";
          }
        });
      }
    });
    }
  }
};
</script>
