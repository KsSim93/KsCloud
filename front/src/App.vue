<template>
  <v-app>
    <v-container>
      <common-header />
        <Main />
      <common-footer />
    </v-container>
  </v-app>
</template>

<script>
import CommonHeader  from './components/common/CommonHeader.vue'
import CommonFooter  from './components/common/CommonFooter.vue'
import Main  from './views/Main.vue'
export default {
  name:"App",
  components:{
    CommonHeader,CommonFooter,Main
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
