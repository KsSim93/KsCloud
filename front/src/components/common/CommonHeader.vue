<template>
    <v-tabs
        v-model="tab"
        bg-color="grey-darken-4"
        stacked
    >
      <h1 class="font-weight-bold text-h2 text-basil">KsCloud</h1>
        <v-tab value="HOME" to = "/">
            <v-icon>mdi-home</v-icon>
            HOME
        </v-tab>
        <v-tab value="USER" to ="/kscloud/user">
            <v-icon>mdi-account</v-icon>
            USER
            </v-tab>
        <v-tab value="MENU3" to = "/">
            <v-icon>mdi-star</v-icon>
            MENU3
            </v-tab>
        <div v-if="username === ''">
            <v-tab value="LOGIN" to ="/login" @click="login">
                    <v-icon>mdi-login</v-icon>
                    LOGIN
            </v-tab>
        </div>
        <div v-else>
                <v-tab value="LOGOUT" @click="login">
                <v-icon>mdi-logout</v-icon>
                    LOGOUT
                </v-tab>
        </div>
    </v-tabs>
</template>

<script>

export default{
    data() {
        return {
            tab: null,
            username: ''
        };
    },
    methods: {
        login() {
            if (this.username === '') {
               console.log("username is null ")
            } else {
                console.log("logout")
            }
        },
    },
}
</script>

<style scoped>
h1 {
    margin-top : auto;
    margin-bottom : auto;
    text-decoration: none;
    color: coral;
}
</style>
