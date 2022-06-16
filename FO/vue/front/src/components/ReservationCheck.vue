<!--eslint-disable-->
<template>
  <div class="container" style="padding: 5%;">
    <h2>여정 정보</h2>
    <b-card>
      <div class="row">
        <div class="col-md-2 col-sm-12 col-xs-12">
          <h4>가는 편</h4>
        </div>
        <div class="col-md-4 col-sm-12 col-xs-12">
           <p>출발지 / 도착지</p>
          <span class="area">{{ ri.de_country }}</span>
          <b-icon
            icon="arrow-right"
            style="width: 20px; height: 20px;"
          ></b-icon>
          <span class="area">{{ ri.arr_country }}</span>
        </div>
        <div class="col-md-3 col-sm-12 col-xs-12">
          <p>날짜 / 시간</p>
          <span class="area">{{ ri.de_date }}</span>
          <br />
          <span class="area">출발시각</span>
          <span class="area">{{ ri.de_time }}</span>
          <span class="area">–도착시각</span>
          <span class="area">{{ ri.arr_time }}</span>
        </div>

        <div class="col-md-3 col-sm-12 col-xs-12">
           <p>스케줄 번호 / 항공기</p>
           <span class="area">{{ ri.schedule_num }} / {{ ri.plane_num }}</span>
          <p class="area">{{ ri.seat_class }}</p>
        </div>
      </div>
    </b-card>
    <br />
    <b-card v-if="isVisible">
      <div class="row">
        <div class="col-md-2 col-sm-12 col-xs-12">
          <h4>오는 편</h4>
        </div>
        <div class="col-md-4 col-sm-12 col-xs-12">
          <p>출발지 / 도착지</p>
          <span class="area">{{ ri2.de_country }}</span>
          <b-icon
            icon="arrow-right"
            style="width: 20px; height: 20px;"
          ></b-icon>
          <span class="area">{{ ri2.arr_country }}</span>
        </div>
        <div class="col-md-3 col-sm-12 col-xs-12">
          <p>날짜 / 시간</p>
          <span class="area">{{ ri2.de_date }}</span>
          <br />
          <span class="area">출발시각</span>
          <span class="area">{{ ri2.de_time }}</span>
          <span class="area">–도착시각</span>
          <span class="area">{{ ri2.arr_time }}</span>
        </div>

        <div class="col-md-3 col-sm-12 col-xs-12">
          <p>스케줄 번호 / 항공기</p>
          <span class="area">{{ ri2.schedule_num }} / {{ ri2.plane_num }}</span>
          <p class="area">{{ ri2.seat_class }}</p>
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
           <span v-if="isVisible" class="area">KRW {{ ri.price | comma }} + KRW {{ ri2.price | comma }}</span>
          <span v-if="!isVisible" class="area">KRW {{ ri.price | comma }}</span>
        </div>
        <div class="col-md-3 col-sm-12 col-xs-12">
          <span v-if="isVisible" class="area"
            >KRW {{ (ri.price + ri2.price) | comma }}</span
          >
          <span v-if="!isVisible" class="area">KRW {{ ri.price | comma }}</span>
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
          <label for="surname">승객 성</label>
          <span class="area">{{ ri3.surname }}</span>
        </div>
        <div class="col-md-6 col-sm-12 col-xs-12">
          <label for="firstname">승객 이름</label>
          <span class="area">{{ ri3.name }}</span>
        </div>
      </div>
      <div class="row">
        <div class="col-md-6 col-sm-12 col-xs-12">
          <label for="passnum">여권번호</label>
          <span class="area">{{ ri3.pass_num }}</span>
        </div>
        <div class="col-md-6 col-sm-12 col-xs-12">
          <label for="name">생년월일</label>
          <span class="area">{{ ri3.birthday }}</span>
        </div>
      </div>
    </b-card>
    <br />
    <div v-if="isVisible2">
      <b-button id="show-btn" @click="showModal" block variant="secondary"
        >예약 취소</b-button
      >
      <b-modal ref="cancel-modal" hide-footer title="예약 취소">
        <div class="d-block text-center">
          <h6>예약을 취소하시겠습니까?</h6>
          <h6>취소된 예약은 복구가 불가합니다</h6>
        </div>
        <b-button class="mt-3" variant="outline-danger" block @click="cancel"
          >예약 취소하기</b-button
        >
        <b-button class="mt-2" variant="outline-warning" block @click="close"
          >닫기</b-button
        >
      </b-modal>
    </div>
    <p>* 예약 취소는 출발날짜 기준 3일 전에만 가능합니다</p>
  </div>
</template>

<script scoped>
/* eslint-disable */
export default {
  data() {
    var today = new Date();
    today.setDate(today.getDate() + 3);
    return {
      go_schedule: this.$route.query.go_schedule,
      back_schedule: this.$route.query.back_schedule,
      go_seat_class: this.$route.query.go_seat_class,
      back_seat_class: this.$route.query.back_seat_class,
      reserve_num: this.$route.query.reserve_num,
      ri: [],
      ri2: [],
      ri3: [],
      isVisible: false,
      isVisible2: true,
      date: today.toISOString().slice(0, -14)
    };
  },
  mounted() {
    this.reservationinfo();
  },
  filters: {
    comma(val) {
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
  },
  methods: {
    reservationinfo() {
      const params = new URLSearchParams();
      params.append("schedule_num", this.go_schedule);
      params.append("seat_class", this.go_seat_class);
      this.$axios.post("/flight/reservationinfo", params).then(res => {
        this.ri = res.data;
        if (res.data.de_date < this.date) {
          this.isVisible2 = false;
        }
      });

      const params2 = new URLSearchParams();
      params2.append("schedule_num", this.back_schedule);
      params2.append("seat_class", this.back_seat_class);
      this.$axios.post("/flight/reservationinfo", params2).then(res => {
        this.ri2 = res.data;
        if (res.data) {
          this.isVisible = true;
        } else {
          this.isVisible = false;
        }
      });

      const params3 = new URLSearchParams();
      params3.append("reserve_num", this.reserve_num);
      params3.append("email", this.$session.get("email"));
      this.$axios.post("/flight/reservationinfo2", params3).then(res => {
        this.ri3 = res.data;

        if (
          res.data.state == "예약정보상이로 인한 취소" ||
          res.data.state == "스케쥴 취소로 인한 취소"
        ) {
          this.isVisible2 = false;
        }
      });
    },
    showModal() {
      this.$refs["cancel-modal"].show();
    },
    cancel() {
      const params = new URLSearchParams();
      params.append("reserve_num", this.reserve_num);
      this.$axios.post("/flight/deletereservation", params).then(res => {
        if ((res.data = "success")) {
          alert("취소 완료");
          window.location.href = "/myreservation";
        } else {
          alert("취소 불가 \n 관리자에게 문의하세요");
          window.location.href = "/myreservation";
        }
      });
    },
    close() {
      this.$refs["cancel-modal"].hide();
    }
  }
};
</script>
