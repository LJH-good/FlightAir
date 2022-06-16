<!--eslint-disable-->
<template>
  <div class="container" style="padding:5%">
    <h2>예약 정보</h2>
    <br />
    <div v-if="rn.length != 0"><MyPaging :listArray="rn" /></div>
    <div v-if="rn.length == 0"><NoReservation /></div>
  </div>
</template>

<script>
/* eslint-disable */
import NoReservation from "@/components/NoReservation.vue";
import MyPaging from "@/components/MyPaging.vue";

export default {
  components: {
    MyPaging,
    NoReservation
  },
  data() {
    return {
      rn: []
    };
  },
  mounted() {
    this.preventBack();
  },
  methods: {
    preventBack: function() {
      history.pushState(null, null, location.href);
      window.onbeforeunload = null;

      window.onpopstate = function() {
        history.go(1);
      };
    }
  },
  created() {
    const params = new URLSearchParams();
    params.append("email", this.$session.get("email"));
    this.$axios.post("/flight/reservationnumber", params).then(res => {
      this.rn = res.data;
    });
  }
};
</script>
