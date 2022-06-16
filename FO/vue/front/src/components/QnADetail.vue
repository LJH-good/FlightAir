<!--eslint-disable-->
<template>
  <div class="container" style="padding: 5%;">
    <h1 style="padding-top: 50px;">문의사항</h1>
    <br />
    <table class="table table-striped">
      <tr>
        <th class="col-sm-4">문의 유형</th>
        <td class="col-sm-8">{{ qna.title }}</td>
      </tr>
      <tr>
        <th class="col-sm-4">문의 제목</th>
        <td class="col-sm-8">{{ qna.title }}</td>
      </tr>
      <tr>
        <th class="col-sm-4">문의 내용</th>
        <td class="col-sm-8">{{ qna.content }}</td>
      </tr>
    </table>

    <table class="table table-striped">
      <tr>
        <th class="col-sm-4">관리자 답변</th>
        <td class="col-sm-8">{{ qna.answer }}</td>
      </tr>
    </table>

    <div style="padding-bottom: 50px;">
      <b-button id="show-btn" @click="showModal" block variant="secondary"
        >글 삭제</b-button
      >
      <b-modal ref="cancel-modal" hide-footer title="글 삭제">
        <div class="d-block text-center">
          <h6>해당 글을 삭제하시겠습니까?</h6>
          <h6>삭제된 글은 복구가 불가합니다</h6>
        </div>
        <b-button class="mt-3" variant="outline-danger" block @click="cancel"
          >글 삭제하기</b-button
        >
        <b-button class="mt-2" variant="outline-warning" block @click="close"
          >닫기</b-button
        >
      </b-modal>
    </div>
    <div style="padding-bottom: 50px;">
      <v-btn
        href="/myqna"
        outlined
        color="info"
        style="float: right; background: blue;"
      >
        <span class="plane-search_content" style="color: white;">목록</span>
      </v-btn>
    </div>
  </div>
</template>

<script>
/* eslint-disable */
export default {
  data() {
    return {
      board_num: this.$route.query.board_num,
      qna: []
    };
  },
  mounted() {
    this.qnainfo();
  },
  methods: {
    qnainfo() {
      const params = new URLSearchParams();
      params.append("board_num", this.board_num);
      this.$axios.post("/user/qnadetail", params).then(res => {
        this.qna = res.data;
      });
    },
    showModal() {
      this.$refs["cancel-modal"].show();
    },
    cancel() {
      const params = new URLSearchParams();
      params.append("board_num", this.board_num);
      this.$axios.post("/user/deleteqna", params).then(res => {
        if ((res.data = "success")) {
          alert("삭제 완료");
          window.location.href = "/myqna";
        } else {
          alert("삭제 불가 \n 관리자에게 문의하세요");
          window.location.href = "/myqna";
        }
      });
    },
    close() {
      this.$refs["cancel-modal"].hide();
    }
  }
};
</script>
