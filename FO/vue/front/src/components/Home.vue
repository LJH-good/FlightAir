<!-- eslint-disable -->
<template>
  <div>
    <div class="col-md-12 col-sm-12 col-xs-12">
      <v-carousel hide-delimiters :cycle="true" :interval="3000">
        <v-carousel-item :src="require('@/assets/img/homeimg.jpg')" />
        <v-carousel-item :src="require('@/assets/img/homeimg2.jpg')" />
        <v-carousel-item :src="require('@/assets/img/homeimg3.jpg')" />
      </v-carousel>
    </div>
    <!-- 왕복 -->
    <div class="container">
      <b-tabs
        active-nav-item-class="font-weight-bold text-uppercase text-danger"
        active-tab-class="font-weight-bold text-success"
        content-class="mt-3"
      >
        <b-tab title="왕복" active>
          <form @submit.prevent="onSubmit">
            <div class="row">
              <div class="col-12 col-md-3 col-sm-6 col-xs-6 text-center">
                <section class="dropdown-wrapper">
                  <div
                    @click.prevent="isVisible = !isVisible"
                    class="selected-item"
                  >
                    <span v-if="selectedItem" style="overflow: auto;">{{
                      selectedItem.de_country
                    }}</span>
                    <span v-else>출발지 선택</span>
                    <v-icon
                      class="drop-down-icon"
                      :class="isVisible ? 'dropdown' : ''"
                      >mdi-arrow-down-thick</v-icon
                    >
                  </div>
                  <div v-if="isVisible" class="dropdown-popover">
                    <input
                      class="search"
                      id="search"
                      v-model="term"
                      type="text"
                      placeholder="출발지 검색"
                      @keyup.prevent="search(term)"
                    />

                    <div class="options">
                      <ul style="padding-left: 0px;">
                        <li
                          @click.prevent="selectItem(post)"
                          v-for="post in Base"
                          :key="post.id"
                        >
                          {{ post.de_country }}
                        </li>
                        <span v-if="Base.length === 0"
                          >검색 정보가 없습니다</span
                        >
                      </ul>
                    </div>
                  </div>
                </section>
              </div>

              <div class="col-12 col-md-3 col-sm-6 col-xs-6 text-center">
                <section class="dropdown-wrapper">
                  <div @click.prevent="selectarrival" class="selected-item">
                    <span v-if="selectedItem2" style="overflow: auto;">{{
                      selectedItem2.arr_country
                    }}</span>
                    <span v-else>도착지 선택</span>
                    <v-icon
                      class="drop-down-icon"
                      :class="isVisible2 ? 'dropdown' : ''"
                      >mdi-arrow-down-thick</v-icon
                    >
                  </div>
                  <div v-if="isVisible2" class="dropdown-popover">
                    <input
                      class="search"
                      id="search2"
                      v-model="term2"
                      type="text"
                      placeholder="도착지 검색"
                      @keyup.prevent="search2(term2)"
                    />

                    <div class="options">
                      <ul style="padding-left: 0px;">
                        <li
                          @click.prevent="selectItem2(post2)"
                          v-for="post2 in Base2"
                          :key="post2.id"
                        >
                          {{ post2.arr_country }}
                        </li>
                        <span v-if="Base2.length === 0"
                          >검색 정보가 없습니다</span
                        >
                      </ul>
                    </div>
                  </div>
                </section>
              </div>
              <div class="col-12 col-md-2 col-sm-6 col-xs-6 text-center coming">
                <input
                  type="date"
                  class="form-control"
                  v-bind:min="date"
                  v-bind:max="maxdate"
                  v-model="STARTDATE"
                  id="STARTDATE"
                  @change="changeDate"
                />
              </div>
              <div class="col-12 col-md-2 col-sm-6 col-xs-6 text-center coming">
                <input
                  v-bind:disabled="STARTDATE == ''"
                  type="date"
                  class="form-control"
                  v-bind:min="endLimit"
                  v-bind:max="maxdate"
                  v-model="ENDDATE"
                  id="ENDDATE"
                />
              </div>
              <div class="col-12 col-md-1 col-sm-6 col-xs-6 text-center coming">
                <v-btn type="submit" class="plane-search" outlined color="info">
                  <span class="plane-search_content">항공권 검색</span>
                </v-btn>
              </div>
            </div>
          </form>
        </b-tab>

        <b-tab title="편도">
          <form @submit.prevent="onSubmit2">
            <div class="row">
              <div class="col-12 col-md-3 col-sm-6 col-xs-6 text-center">
                <section class="dropdown-wrapper">
                  <div
                    @click.prevent="isVisible3 = !isVisible3"
                    class="selected-item"
                  >
                    <span v-if="selectedItem3" style="overflow: auto;">{{
                      selectedItem3.de_country
                    }}</span>
                    <span v-else>출발지 선택</span>
                    <v-icon
                      class="drop-down-icon"
                      :class="isVisible3 ? 'dropdown' : ''"
                      >mdi-arrow-down-thick</v-icon
                    >
                  </div>
                  <div v-if="isVisible3" class="dropdown-popover">
                    <input
                      class="search"
                      id="search3"
                      v-model="term3"
                      type="text"
                      placeholder="출발지 검색"
                      @keyup.prevent="search3(term3)"
                    />

                    <div class="options">
                      <ul style="padding-left: 0px;">
                        <li
                          @click.prevent="selectItem3(post3)"
                          v-for="post3 in Base3"
                          :key="post3.id"
                        >
                          {{ post3.de_country }}
                        </li>
                        <span v-if="Base3.length === 0"
                          >검색 정보가 없습니다</span
                        >
                      </ul>
                    </div>
                  </div>
                </section>
              </div>
              <div class="col-12 col-md-3 col-sm-6 col-xs-6 text-center">
                <section class="dropdown-wrapper">
                  <div @click.prevent="selectarrival2" class="selected-item">
                    <span v-if="selectedItem4" style="overflow: auto;">{{
                      selectedItem4.arr_country
                    }}</span>
                    <span v-else>도착지 선택</span>
                    <v-icon
                      class="drop-down-icon"
                      :class="isVisible4 ? 'dropdown' : ''"
                      >mdi-arrow-down-thick</v-icon
                    >
                  </div>
                  <div v-if="isVisible4" class="dropdown-popover">
                    <input
                      class="search"
                      id="search4"
                      v-model="term4"
                      type="text"
                      placeholder="출발지 검색"
                      @keyup.prevent="search4(term4)"
                    />

                    <div class="options">
                      <ul style="padding-left: 0px;">
                        <li
                          @click.prevent="selectItem4(post4)"
                          v-for="post4 in Base4"
                          :key="post4.id"
                        >
                          {{ post4.arr_country }}
                        </li>
                        <span v-if="Base4.length === 0"
                          >검색 정보가 없습니다</span
                        >
                      </ul>
                    </div>
                  </div>
                </section>
              </div>
              <div class="col-12 col-md-2 col-sm-6 col-xs-6 text-center coming">
                <input
                  type="date"
                  class="form-control"
                  v-bind:min="date"
                  v-bind:max="maxdate"
                  v-model="STARTDATE2"
                  id="STARTDATE2"
                  @change="changeDate"
                />
              </div>
              <div class="col-12 col-md-2 col-sm-6 col-xs-6 text-center coming">
                <v-btn type="submit" class="plane-search" outlined color="info">
                  <span class="plane-search_content">항공권 검색</span>
                </v-btn>
              </div>
            </div>
          </form>
        </b-tab>
      </b-tabs>
      <p style="padding:1%;">
        * 오늘부터 12개월 내 출발하는 항공편의 최저가와 출발일을 간편하게
        조회하실 수 있습니다.
      </p>
    </div>

    <br />
    <form @submit.prevent="reservation">
      <div class="col-md-12 col-sm-12 col-xs-12">
        <v-card>
          <v-img
            :src="require('@/assets/main.jpg')"
            class="white--text align-center"
            gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
            height="400px"
          >
            <h1 class="text-center font-size">예약 조회</h1>
            <div class="text-center">
              <v-btn type="submit" class="white--text " outlined
                >바로가기</v-btn
              >
            </div>
          </v-img>
        </v-card>
      </div>
    </form>
    <br />
  </div>
</template>

<script scoped>
/* eslint-disable */
export default {
  data() {
    var today = new Date();
    today.setMonth(today.getMonth() + 12);
    return {
      term: "",
      term2: "",
      term3: "",
      term4: "",
      selectedItem: null,
      selectedItem2: null,
      selectedItem3: null,
      selectedItem4: null,
      isVisible: false,
      isVisible2: false,
      isVisible3: false,
      isVisible4: false,
      STARTDATE: "",
      STARTDATE2: "",
      endLimit: "",
      ENDDATE: "",
      date: new Date(new Date().getTime()).toISOString().slice(0, -14),
      maxdate: today.toISOString().slice(0, -14),
      slide: 0,
      sliding: null,
      Base: [],
      OriBase: [],
      OriBase2: [],
      Base2: [],
      Base3: [],
      Base4: [],
      state: "정상"
    };
  },
  mounted() {
    this.mydata();
    this.mydata2();
  },
  methods: {
    selectarrival() {
      if (this.selectedItem == null) {
        alert("출발지를 선택해주세요");
      } else {
        this.isVisible2 = !this.isVisible2;
      }
    },
    selectarrival2() {
      if (this.selectedItem3 == null) {
        alert("출발지를 선택해주세요");
      } else {
        this.isVisible4 = !this.isVisible4;
      }
    },
    mydata() {
      this.$axios.post("/flight/schedule").then(res => {
        this.OriBase = res.data;
        this.Base = this.OriBase;
        this.Base3 = this.OriBase;
      });
    },
    mydata2() {
      this.$axios.post("/flight/schedule2").then(res => {
        this.OriBase2 = res.data;
        this.Base2 = this.OriBase2;
        this.Base4 = this.OriBase2;
      });
    },
    search(term) {
      this.Base = this.Base.filter(post => {
        return post.de_country.toLowerCase().match(term.toLowerCase());
      });
      if (term == "") {
        this.Base = this.OriBase;
      }
    },
    search2(term2) {
      this.Base2 = this.Base2.filter(post2 => {
        return post2.arr_country.toLowerCase().match(term2.toLowerCase());
      });
      if (term2 == "") {
        this.Base2 = this.OriBase2;
      }
    },
    search3(term3) {
      this.Base3 = this.Base3.filter(post3 => {
        return post3.de_country.toLowerCase().match(term3.toLowerCase());
      });
      if (term3 == "") {
        this.Base3 = this.OriBase;
      }
    },
    search4(term4) {
      this.Base4 = this.Base4.filter(post4 => {
        return post4.arr_country.toLowerCase().match(term4.toLowerCase());
      });
      if (term4 == "") {
        this.Base4 = this.OriBase2;
      }
    },
    selectItem(post) {
      this.selectedItem = post;
      this.isVisible = false;
    },
    selectItem2(post2) {
      this.selectedItem2 = post2;
      this.isVisible2 = false;
    },
    selectItem3(post3) {
      this.selectedItem3 = post3;
      this.isVisible3 = false;
    },
    selectItem4(post4) {
      this.selectedItem4 = post4;
      this.isVisible4 = false;
    },
    changeDate() {
      var limitdate = new Date(this.STARTDATE);
      limitdate.setDate(limitdate.getDate());
      this.endLimit = limitdate.toISOString().slice(0, -14);
      this.ENDDATE = "";
    },
    onSubmit() {
      if (this.$session.get("email") == null) {
        alert("로그인이 필요한 서비스입니다");
        return (window.location.href = "/login");
      } else if (this.selectedItem == null) {
        alert("출발지를 선택해주세요");
        return false;
      } else if (this.selectedItem2 == null) {
        alert("도착지를 선택해주세요");
        return false;
      } else if (this.STARTDATE == "") {
        alert("가는 날을 선택해주세요");
        return false;
      } else if (this.ENDDATE == "") {
        alert("오는 날을 선택해주세요");
        return false;
      } else if (this.ENDDATE == "") {
        alert("오는 날을 선택해주세요");
        return false;
      } else if (
        this.selectedItem.de_country == this.selectedItem2.arr_country
      ) {
        alert("출발지와 도착지가 같으면 검색이 불가능합니다");
        return false;
      }

      window.location.href =
        "/scheduleselect?de_country=" +
        this.selectedItem.de_country +
        "&arr_country=" +
        this.selectedItem2.arr_country +
        "&STARTDATE=" +
        this.STARTDATE +
        "&ENDDATE=" +
        this.ENDDATE;
    },
    onSubmit2() {
      if (this.$session.get("email") == null) {
        alert("로그인이 필요한 서비스입니다");
        return (window.location.href = "/login");
      } else if (this.selectedItem3 == null) {
        alert("출발지를 선택해주세요");
        return false;
      } else if (this.selectedItem4 == null) {
        alert("도착지를 선택해주세요");
        return false;
      } else if (this.STARTDATE2 == "") {
        alert("가는 날을 선택해주세요");
        return false;
      } else if (
        this.selectedItem3.de_country == this.selectedItem4.arr_country
      ) {
        alert("출발지와 도착지가 같으면 검색이 불가능합니다");
        return false;
      }

      window.location.href =
        "/scheduleselect2?de_country=" +
        this.selectedItem3.de_country +
        "&arr_country=" +
        this.selectedItem4.arr_country +
        "&STARTDATE=" +
        this.STARTDATE2;
    },
    reservation() {
      if (this.$session.get("email") == null) {
        alert("로그인이 필요한 서비스입니다");
        return (window.location.href = "/login");
      }
      window.location.href = "/myreservation";
    }
  }
};
</script>

<style scoped lang="css">
.checkin {
  width: 60%;
}
.checkout {
  width: 40%;
}
.going {
  border: 2px solid white;
  border-radius: 5px;
}
.coming {
  border: 2px solid white;
  border-radius: 5px;
}
.dropdown-wrapper {
  max-width: 350px;
  position: margin 0 auto;
  margin-left: 25%;
}
.selected-item {
  height: 40px;
  border: 2px solid lightblue;
  border-radius: 5px;
  padding: 5px 10px;
  display: flex;
  justify-content: space-between;
  align-content: center;
  font-size: 16px;
  font-weight: 500;
}

.options {
  border: 2px solid lightblue;
  top: 100px;
  left: 0;
  right: 0;
  background-color: #fff;
  width: 100%;
  padding: 20px;
  border-radius: 5px;
}

.search {
  border: 2px solid lightblue;
  width: 100%;
  margin-top: 19px;
  border-radius: 5px;
  height: 40px;
  padding-left: 8px;
}

ul {
  list-style-type: none;
  text-align: left;
  padding-left: 8px;
  max-height: 200px;
  overflow-y: scroll;
  overflow-x: hidden;
}

li {
  width: 100%;
  border-bottom: 1px solid lightblue;
  padding: 10px;
  background-color: aliceblue;
  cursor: pointer;
  font-size: 14px;
}

li:hover {
  background-color: lightblue;
  color: #fff;
  font-weight: bold;
}

.drop-down-icon.dropdown {
  transform: rotate(180deg);
  transition: all 0.3s ease;
}
.main {
  margin-bottom: 30px;
}
.title {
  margin-left: 30px;
}
.plane-search {
  position: relative;
  border: none;
  display: inline-block;
  border-radius: 15px;
  font-family: "paybooc-Light", sans-serif;
  text-decoration: none;
  font-weight: 600;
  background-color: aliceblue;
}
</style>
