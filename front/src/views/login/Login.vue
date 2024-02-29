<template>

    <div id="loginForm">
        <v-form @submit.prevent = "login">
            <label>
                <v-text-field placeholder = "username" v-model = "user_id"></v-text-field>
            </label>
            <label>
                <v-text-field type ="password" placeholder = "password" v-model = "user_pw"></v-text-field>
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
import axios from 'axios'
import router from '@/router';

export default {
    data() {
        return {
            user_id : '',
            user_pw : ''
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
            axios.get("/api/csrf-token")
                .then(response => {
                    console.log(response.data.token)
                    const headers = {
                        'X-CSRF-TOKEN': response.data.token
                    }
                    const params = {
                        userId: this.user_id,
                        password: this.user_pw
                    }

                    axios.post("/api/login",params,headers)
                        .then(res => {
                            console.log(res.data)
                            if(res.data.userId != null) {
                                router.push('/')
                            }
                        }).catch();
                })
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
