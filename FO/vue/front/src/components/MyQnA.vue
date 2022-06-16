<!--eslint-disable-->
<template>
  <div class="container" style="padding:5%">
    <h2>상담 내역</h2>
    <br />
    <div v-if="rn.length != 0"><MyQnAPaging :listArray="rn" /></div>
    <div v-if="rn.length == 0"><NoQnA /></div>
    <div style="padding-bottom: 50px; padding-top: 30px;">
      <v-btn
        href="/qna"
        outlined
        color="info"
        style="float: right; background: blue;"
      >
        <span class="plane-search_content" style="color: white;"
          >글 작성하기</span
        >
      </v-btn>
    </div>
  </div>
</template>

<script>
/* eslint-disable */
import NoQnA from "@/components/NoQnA.vue";
import MyQnAPaging from "@/components/MyQnAPaging.vue";

export default {
  components: {
    NoQnA,
    MyQnAPaging
  },
  data() {
    return {
      rn: []
    };
  },
  created() {
    const params = new URLSearchParams();
    params.append("email", this.$session.get("email"));
    this.$axios.post("/user/qnanumber", params).then(res => {
      this.rn = res.data;
    });
  }
};
</script>
