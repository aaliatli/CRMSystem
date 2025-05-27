<template>
  <div class="users-container">
    <h2>Giriş Yapan Kullanıcı</h2>
    <p v-if="user">{{ user }}</p>
    <p v-else>Kullanıcı bilgisi bulunamadı.</p>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: ""
    };
  },
  created() {
    this.user = localStorage.getItem("loggedInUser");
  },
  methods: {
    logout() {
      localStorage.removeItem("loggedInUser");
      this.$router.push("/login");
    }
  }
};
</script>

<style scoped>
.users-container {
  padding: 2% 6%;
}

.users-container table {
  width: 100%;
  border-collapse: collapse;
}

.users-container td {
  padding: 8px;
  border-bottom: 1px solid #ccc;
}
</style>
