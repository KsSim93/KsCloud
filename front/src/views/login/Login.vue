<template>

    <div id="loginForm">
        <v-form @submit.prevent = "login">
            <label>
                <v-text-field placeholder = "username" v-model = "username"></v-text-field>
            </label>
            <label>
                <v-text-field type ="password" placeholder = "password" v-model = "password"></v-text-field>
            </label>
            <v-btn
                :loading="loading"
                type="submit"
                block
                class="mt-2"
                text="Submit"
            ></v-btn>
        </v-form>
    </div>

</template>
<script>
export default {
    data() {
        return {
            username : '',
            password : ''
        }
    },
    methods: {
        login() {
            if (this.user_id === '') {
        alert('ID를 입력하세요.')
        return
      }

      if (this.user_pw === '') {
        alert('비밀번호를 입력하세요.')
        return
      }

      alert('로그인 되었습니다.')
        }
    }
}
</script>

<style scoped>
#loginForm {
  width: 500px;
  margin: auto;
}
</style>
