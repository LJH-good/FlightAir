<!--eslint-disable-->
<template>
  <div>
    <b-card>
      <div class="row" style="text-align: center;">
        <div class="col-md-3 col-sm-12 col-xs-12">
          <h5>예약 번호</h5>
        </div>
        <div class="col-md-3 col-sm-12 col-xs-12">
          <h5>예약 날짜</h5>
        </div>
        <div class="col-md-3 col-sm-12 col-xs-12">
          <h5>승인 상태</h5>
        </div>
        <div class="col-md-3 col-sm-12 col-xs-12">
          <h5>여정 정보</h5>
        </div>
      </div>
      <div>
        <div
          @click="
            go(
              base.go_schedule,
              base.back_schedule,
              base.reserve_num,
              base.go_seat_class,
              base.back_seat_class
            )
          "
          class="row reserve"
          v-for="(base, i) in paginatedData"
          :key="i"
          style="text-align: center;"
        >
          <div class="col-md-3 col-sm-12 col-xs-12">
            <span class="area">{{ base.reserve_num }}</span>
          </div>
          <div class="col-md-3 col-sm-12 col-xs-12">
            <span class="area">{{ base.date.substring(0, 10) }}</span>
          </div>
          <div class="col-md-3 col-sm-12 col-xs-12">
            <span class="area">{{ base.state }}</span>
          </div>
          <div class="col-md-3 col-sm-12 col-xs-12">
            <span class="area">{{
              base.back_schedule == "-" ? "편도" : "왕복"
            }}</span>
          </div>
        </div>
      </div>
    </b-card>
    <div class="btn-cover">
      <b-button
        pill
        :disabled="pageNum === 0"
        @click="prevPage"
        class="page-btn"
      >
        이전
      </b-button>
      <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
      <b-button
        pill
        :disabled="pageNum >= pageCount - 1"
        @click="nextPage"
        class="page-btn"
      >
        다음
      </b-button>
    </div>
  </div>
</template>

<script scoped>
/* eslint-disable */
export default {
  data() {
    return {
      pageNum: 0
    };
  },
  props: {
    listArray: { type: Array, required: true },
    pageSize: { type: Number, required: false, default: 10 }
  },
  methods: {
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
    go(
      go_schedule,
      back_schedule,
      reserve_num,
      go_seat_class,
      back_seat_class
    ) {
      window.location.href =
        "/reservationcheck?go_schedule=" +
        go_schedule +
        "&back_schedule=" +
        back_schedule +
        "&reserve_num=" +
        reserve_num +
        "&go_seat_class=" +
        go_seat_class +
        "&back_seat_class=" +
        back_seat_class;
    }
  },
  computed: {
    pageCount() {
      let listLeng = this.listArray.length;
      let listSize = this.pageSize;
      let page = Math.floor((listLeng - 1) / listSize) + 1;
      return page;
    },
    paginatedData() {
      const start = this.pageNum * this.pageSize;
      const end = start + this.pageSize;
      return this.listArray.slice(start, end);
    }
  }
};
</script>

<style scoped>
.reserve:hover {
  background-color: #f5f5f5;
}
.btn-cover {
  text-align: center;
  padding: 4%;
}
</style>
