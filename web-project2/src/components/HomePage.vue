<template>
  <div>
    <template v-if="loading">
      <transition name="fade">
        <div class="splash-screen">
          <h1 class="app-name">AACRM</h1>
        </div>
      </transition>
    </template>

    <template v-else>
      <div class="main-content">
        <h2>Hoş geldiniz!</h2>
        <p>AACRM sistemine hoşgeldiniz. Devam etmek için menüleri kullanabilirsiniz.</p>
      </div>
    </template>
  </div>
</template>


<script>
export default {
  data() {
    return {
      loading: true
    };
  },
  mounted() {
    setTimeout(() => {
      this.loading = false;
    }, 2500); 
  }
};
</script>

<style scoped>
.splash-screen {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 80vh;
  background: linear-gradient(135deg, #e3f2fd, #ffffff);
  overflow: hidden;
}

.logo-animate {
  width: 100px;
  height: 100px;
  opacity: 0;
  animation: dropIn 1s ease forwards;
}

.app-name {
  font-size: 28px;
  font-weight: bold;
  color: #2c3e50;
  opacity: 0;
  animation: fadeInText 1.5s ease 1s forwards;
}

@keyframes dropIn {
  from {
    transform: translateY(-50px);
    opacity: 0;
  }
  to {
    transform: translateY(0);
    opacity: 1;
  }
}

@keyframes fadeInText {
  to {
    opacity: 1;
  }
}

.fade-enter-active, .fade-leave-active {
  transition: opacity 0.5s ease;
}
.fade-enter-from, .fade-leave-to {
  opacity: 0;
}

.main-content {
  min-height: 65vh;
}

.main-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
}

.main-content h2 {
  font-size: 32px;
  color: #2c3e50;
  margin-bottom: 1rem;
}

.main-content p {
  font-size: 18px;
  color: #555;
  max-width: 600px;
  line-height: 1.6;
}

</style>
