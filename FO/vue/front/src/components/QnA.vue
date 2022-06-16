<!--eslint-disable-->
<template>
  <v-form>
    <v-container>
      <form @submit.prevent="onSubmit">
        <div>
          <h1 style="padding-top: 50px;">문의사항</h1>
          <hr />
          <b-input-group prepend="문의 유형" class="mt-10">
            <b-form-select
              v-model="selected"
              :options="options"
              class="form-control"
              required
              id="category"
              name="category"
            ></b-form-select>
          </b-input-group>
          <b-input-group prepend="문의 제목" class="mt-10">
            <b-form-input
              required
              id="title"
              name="title"
              v-model="title"
              placeholder="최대 15자까지 입력 가능합니다(공백 포함)"
            ></b-form-input>
          </b-input-group>
          <b-input-group prepend="문의 내용" class="mt-10">
            <span style="position:absolute; right:0px; top:10px;">
              <span :style="[text.length > 100 ? { color: 'red' } : {}]">{{
                text.length
              }}</span>
              / 100
            </span>
          </b-input-group>
          <b-form-textarea
            id="content"
            name="content"
            placeholder="최대 100자까지 입력 가능합니다(공백 포함)"
            rows="5"
            max-rows="5"
            required
            v-model="text"
            :state="text.length < 101"
            no-resize
          ></b-form-textarea>
        </div>
        <br />
        <div style="padding-bottom: 50px;">
          <v-btn
            type="submit"
            outlined
            color="info"
            style="float: right; background: blue;"
          >
            <span class="plane-search_content" style="color: white;"
              >문의하기</span
            >
            <p></p>
          </v-btn>
        </div>
      </form>
    </v-container>
  </v-form>
</template>

<script>
/* eslint-disable */
export default {
  data() {
    return {
      selected: null,
      options: [
        { value: null, text: "문의 유형 선택" },
        { value: "예약 및 이용 문의", text: "예약 및 이용 문의" },
        { value: "계정 문의", text: "계정 문의" },
        { value: "건의 사항", text: "건의 사항" },
        { value: "기타", text: "기타" }
      ],
      text: "",
      category: "",
      title: "",
      content: ""
    };
  },
  methods: {
    onSubmit() {
      //제목 정규식 - 모든 글자 1글자 이상 15글자 이하
      var titletest = /^.{1,15}$/;
      //내용 정규식 - 모든 글자 1글자 이상 100글자 이하
      var contenttest = /^.{1,100}$/;

      this.category = document.getElementById("category").value;
      this.title = document.getElementById("title").value;
      this.content = document.getElementById("content").value;

      if (!titletest.test(this.title)) {
        alert("제목은 공백포함 15자까지 입력이 가능합니다");
        this.title = this.title.substring(0,15);
        document.getElementById("title").focus();
        return false;
      } else if (!contenttest.test(this.content)) {
        alert("내용은 공백포함 100자까지 입력이 가능합니다");
        this.text = this.text.substring(0,100);
        document.getElementById("content").focus();
        return false;
      }

      const params = new URLSearchParams();
      params.append("email", this.$session.get("email"));
      params.append("category", this.category);
      params.append("title", this.title);
      params.append("content", this.content);

      this.$axios.post("/user/insertqna", params).then(res => {
        if (res.data == "success") {
          alert("등록 완료");
          window.location.href = "/myqna";
        } else {
          alert("등록실패 \n 관리자에게 문의하세요");
          window.location.href = "/myqna";
        }
      });
    }
  }
};
</script>
