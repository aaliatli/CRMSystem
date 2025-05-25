<script setup>
import Menu from './components/Header/Menu.vue';
import HomePage from './components/HomePage.vue';
</script>

<template>
  <header>
    <Menu></Menu>
  </header>
  <main>
    <router-view></router-view>
  </main>
</template>

<script>

export default {
  components:{
    Menu,
    HomePage
  },
  server: {
    open: true 
  },
  data() {
    return {
      mesaj: ''
    };
  },
};
</script>

<style scoped>
header {
  line-height: 1.5;
}

menu{
  display: flex;
  list-style: none;
}
</style>
