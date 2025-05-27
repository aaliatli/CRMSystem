<template>
  <header :key="isLoggedIn">
    <Unauthorized v-if="!isLoggedIn" @login="login" />
    <Menu v-else @logout="logout" />
  </header>
  <main>
    <router-view />
  </main>
</template>

<script>
import Menu from './components/Header/Menu.vue';
import Unauthorized from './components/Header/Unauthorized.vue';

export default {
  components: { Menu, Unauthorized },
  data() {
    return {
      isLoggedIn: false
    };
  },
  created() {
    this.checkLogin();
  },
  methods: {
    checkLogin() {
      this.isLoggedIn = localStorage.getItem("isLoggedIn") === "true";
    },
    login() {
      localStorage.setItem("isLoggedIn", "true");
      this.isLoggedIn = true;
    },
    logout() {
      localStorage.removeItem("isLoggedIn");
      this.isLoggedIn = false;
    }
  }
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
