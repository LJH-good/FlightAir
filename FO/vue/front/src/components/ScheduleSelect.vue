<!--eslint-disable-->
<template>
  <form @submit.prevent="onSubmit">
    <div class="container">
      <div v-if="isVisible1 && isVisible2">
        <div class="sm_inner trv_first" style="text-align:center;">
          <h3 style="padding-top: 5%;">
            <v-icon>mdi-airplane</v-icon>첫 번째 여정
          </h3>

          <div
            class="travel_info"
            id="depTravelInfo"
            style="padding-bottom: 5%;"
            v-for="(base, i) in GoAir.slice(0, 1)"
            :key="i"
          >
            <div>
              <span class="area">{{ base.de_country }}</span>
              <b-icon
                icon="arrow-right"
                style="width: 20px; height: 20px;"
              ></b-icon>
              <span class="area">{{ base.arr_country }}</span>
            </div>
          </div>
          <table
            class="table_list airline_ticketing"
            id="tb1_area1"
            v-for="(base2, i) in GoAir"
            :key="i"
          >
            <thead>
              <tr style="background: #BBDEFB;">
                <th scope="col" rowspan="2" class="gray_bg col-sm-6">
                  출도착시간(비행시간)
                </th>
                <th scope="col" rowspan="2" class="unleftline gray_bg col-sm-2">
                  스케쥴명/기종
                </th>
                <th scope="col" rowspan="2" class="business_bg col-sm-2">
                  좌석등급/가격
                </th>
                <th scope="col" rowspan="2" class="spare_bg col-sm-2">
                  잔여석
                </th>
              </tr>
            </thead>
            <tbody>
              <tr class="flight">
                <td scope="row" class="th_row chk_th_row">
                  <div class="flight_time">
                    <div>
                      <p class="time">{{ base2.de_time }}</p>

                      <p
                        class="lead_time"
                        style="padding-left: 90px;"
                      >
                        <b-icon
                          icon="arrow-down"
                          style="width: 20px; height: 20px;"
                        ></b-icon
                        >{{ base2.flight_time }}
                      </p>

                      <p class="time">{{ base2.arr_time }}</p>
                    </div>
                  </div>
                </td>
                <td scope="row">
                  <span>{{ base2.schedule_num }}</span>
                  <p>{{ base2.plane_num }}</p>
                </td>
                <td class="business_area">
                  <input
                    type="radio"
                    name="cost"
                    :id="'cost' + i"
                    :value="'cost' + i"
                    @click="seatChecked(base2.schedule_num, base2.seat_class)"
                  />
                  <label :for="'cost' + i">KRW {{ base2.price | comma }}</label>
                  <br />
                  <label :for="'cost' + i">{{ base2.seat_class }}</label>
                </td>
                <td scope="row">
                  <span :id="base2.schedule_num + base2.plane_num + base2.seat_class">
                    {{
                      spare(
                        base2.schedule_num,
                        base2.plane_num,
                        base2.seat_class
                      )
                    }}
                  </span>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="sm_inner trv_second" style="text-align:center;">
          <h3 style="padding-top: 5%;">
            <v-icon>mdi-airplane</v-icon>두 번째 여정
          </h3>
          <div
            class="travel_info"
            id="arrTravelInfo"
            style="padding-bottom: 5%;"
            v-for="(base3, i) in BackAir.slice(0, 1)"
            :key="i"
          >
            <span class="area">{{ base3.de_country }}</span>
            <b-icon
              icon="arrow-right"
              style="width: 20px; height: 20px;"
            ></b-icon>
            <span class="area">{{ base3.arr_country }}</span>
          </div>
          <table
            class="table_list airline_ticketing"
            id="tb1_area1"
            v-for="(base4, i) in BackAir"
            :key="i"
          >
            <thead>
              <tr style="background: #BBDEFB;">
                <th scope="col" rowspan="2" class="gray_bg col-sm-6">
                  출도착시간(비행시간)
                </th>
                <th scope="col" rowspan="2" class="unleftline gray_bg col-sm-2">
                  편명/기종
                </th>
                <th scope="col" rowspan="2" class="business_bg col-sm-2">
                  좌석등급/가격
                </th>
                <th scope="col" rowspan="2" class="spare_bg col-sm-2">
                  잔여석
                </th>
              </tr>
            </thead>
            <tbody>
              <tr class="flight">
                <td scope="row" class="th_row chk_th_row">
                  <div class="flight_time">
                    <div class="row">
                      <p class="time">{{ base4.de_time }}</p>

                      <p
                        class="lead_time"
                        style="padding-left: 90px;"
                      >
                        <b-icon
                          icon="arrow-down"
                          style="width: 20px; height: 20px;"
                        ></b-icon
                        >{{ base4.flight_time }}
                      </p>

                      <p class="time">{{ base4.arr_time }}</p>
                    </div>
                  </div>
                </td>
                <td scope="row">
                  <span>{{ base4.schedule_num }}</span>
                  <p>{{ base4.plane_num }}</p>
                </td>
                <td class="business_area">
                  <input
                    type="radio"
                    name="cost2"
                    :id="'cost2' + i"
                    :value="'cost2' + i"
                    @click="seatChecked2(base4.schedule_num, base4.seat_class)"
                  />
                  <label :for="'cost2' + i"
                    >KRW {{ base4.price | comma }}</label
                  >
                  <br />
                  <label :for="'cost2' + i">{{ base4.seat_class }}</label>
                </td>
                <td scope="row">
                  <span :id=" base4.schedule_num + base4.plane_num + base4.seat_class">
                    {{spare(
                        base4.schedule_num,
                        base4.plane_num,
                        base4.seat_class
                      )}}
                  </span>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
      <div v-if="!(isVisible1 && isVisible2)">
        <p style="text-align: center;">
          선택하신 조건에 맞는 예약 가능 항공편이 없습니다.
        </p>
      </div>
      <br />
      <div v-if="isVisible1 && isVisible2" style="padding-bottom: 50px;">
        <v-btn
          type="submit"
          outlined
          color="info"
          style="float: right; background: blue;"
        >
          <span class="plane-search_content" style="color:white;">다음</span>
        </v-btn>
      </div>
      <div v-if="!(isVisible1 && isVisible2)" style="padding-bottom: 50px;">
        <v-btn
          href="/"
          outlined
          color="info"
          style="float: right; background: blue;"
        >
          <span class="plane-search_content" style="color:white;"
            >이전으로 돌아가기</span
          >
        </v-btn>
      </div>
    </div>
  </form>
</template>

<script scoped>
/* eslint-disable */
export default {
  data() {
    return {
      GoAir: [],
      BackAir: [],
      de_country: this.$route.query.de_country,
      arr_country: this.$route.query.arr_country,
      STARTDATE: this.$route.query.STARTDATE,
      ENDDATE: this.$route.query.ENDDATE,
      cost: "",
      cost2: "",
      isVisible1: true,
      isVisible2: true,
      seat_class: "",
      seat_class2: ""
    };
  },
  mounted() {
    this.Base();
  },
  filters: {
    comma(val) {
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
  },
  methods: {
    seatChecked(schedule_num, seat_class) {
      this.cost = schedule_num;
      this.seat_class = seat_class;
    },
    seatChecked2(schedule_num, seat_class) {
      this.cost2 = schedule_num;
      this.seat_class2 = seat_class;
    },
    onSubmit() {
      // console.log(this.seat_class);
      if (this.cost == "") {
        alert("첫번째 여정을 선택해주세요");
        return false;
      } else if (this.cost2 == "") {
        alert("두번째 여정을 선택해주세요");
        return false;
      }
      window.location.href =
        "/checkout?go_schedule=" +
        this.cost +
        "&back_schedule=" +
        this.cost2 +
        "&go_seat_class=" +
        this.seat_class +
        "&back_seat_class=" +
        this.seat_class2;
    },
    Base() {
      const params = new URLSearchParams();
      params.append("de_country", this.de_country);
      params.append("arr_country", this.arr_country);
      params.append("de_date", this.STARTDATE);
      this.$axios.post("/flight/searchdeparture", params).then(async res => {
        // console.log(res.data)
        if (res.data) {
          this.GoAir = await res.data;
        } else {
          this.isVisible1 = false;
        }
      });

      const params2 = new URLSearchParams();
      params2.append("de_country", this.arr_country);
      params2.append("arr_country", this.de_country);
      params2.append("de_date", this.ENDDATE);
      this.$axios.post("/flight/searchdeparture2", params2).then(async res => {
        // console.log(res.data)
        if (res.data) {
          this.BackAir = await res.data;
        } else {
          this.isVisible2 = false;
        }
      });

      // console.log(this.GoAir);
      // console.log(this.BackAir);
    },
    spare(schedule_num, plane_num, seat_class) {
      const params = new URLSearchParams();
      params.append("schedule_num", schedule_num);
      params.append("plane_num", plane_num);
      params.append("seat_class", seat_class);

      this.$axios.post("/flight/spareseat", params).then(res => {
        document.getElementById(schedule_num + plane_num + seat_class).innerText = res.data;
      });
    },
  }
};
</script>

<style scoped>
table {
  width: 100%;
  border-collapse: collapse;
}
th,
td {
  padding: 10px;
  border-bottom: 1px solid #bbdefb;
}
tr:hover {
  background-color: #f5f5f5;
}
</style>
