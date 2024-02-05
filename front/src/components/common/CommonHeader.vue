<template>
    <header>
        <h1>
            <a href="#" class="logo">
                <img alt="Vue Logo" src="../../assets/logo.png" width="50" />
            </a>
        </h1>
            <div class="menuWrap">
                <ul class="menu">
                    <li><router-link to ="/">HOME</router-link></li>
                    <li><router-link to ="/user">USER</router-link></li>
                    <li><router-link to ="/">MENU3</router-link></li>
                    <li><router-link to ="/">MENU4</router-link></li>
                </ul>
            </div>
    </header>
</template>

<script>
export default{}
</script>

<style scoped>
header {
    width: 100%;
    text-align: center;
    position: relative;
    height: 80px;
    border-bottom: 1px solid #35495e;
}
header h1 {
    position: absolute;
    top: 0;
    left: 100px;
}
header ul.menu:after {
    display: block;
    clear: both;
    content: '';
}
header ul.menu {
    position: absolute;
    right: 50px;
}
header ul.menu li {
    float: left;
    padding: 10px 20px;;
    list-style: none;
}

a {
    text-decoration: none;
    color: coral;
}
</style>
