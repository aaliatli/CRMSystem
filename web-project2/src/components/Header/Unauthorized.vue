<template>
  <div>
    <nav class="mainNav" ref="nav">
      <div class="mainNav__start">
        
        <div class="mainNav__links">
          <router-link to="/hakkimizda" class="mainNav__link" @click.native="closeMenus">Hakkımızda</router-link>
          <router-link to="/cozumler" class="mainNav__link" @click.native="closeMenus">Çözümler</router-link>
          <router-link to="/iletisim" class="mainNav__link" @click.native="closeMenus">İletişim</router-link>
        </div>

        <div class="mainNav__links">
          <router-link to="/giris" class="mainNav__link" @click.native="closeMenus">Giriş</router-link>
        </div>
      </div>

      <div class="mainNav_logo">
        <img :src="logo" alt="AACRM Logo" class="logo-img" />
      </div>
    </nav>
  </div>
</template>
<script>
import logo from '@/assets/AACRM.png';

export default {
  name: 'MainMenu',
  data() {
    return {
      logo,
      leftMenuOpen: false,
      rightMenuOpen: false,
    };
  },
  methods: {
    toggleLeftMenu() {
      this.leftMenuOpen = !this.leftMenuOpen;
      if (this.leftMenuOpen) this.rightMenuOpen = false;
    },
    toggleRightMenu() {
      this.rightMenuOpen = !this.rightMenuOpen;
      if (this.rightMenuOpen) this.leftMenuOpen = false;
    },
    closeMenus() {
      this.leftMenuOpen = false;
      this.rightMenuOpen = false;
    },
    handleOutsideClick(event) {
      const nav = this.$refs.nav;
      if (nav && !nav.contains(event.target)) {
        this.closeMenus();
      }
    }
  },
  mounted() {
    document.addEventListener('click', this.handleOutsideClick);
  },
  beforeUnmount() {
    document.removeEventListener('click', this.handleOutsideClick);
  }
};
</script>


<style> 

.mainNav_logo {
  position: relative;
  cursor: pointer;
  padding-right: 55px;
}

.mainNav_logo img {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  object-fit: cover;
  
}
html, body {
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
}

body {
  overflow-x: hidden;
}

.mainNav {
  width: 100%;
  height: 80px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #f5f7fa;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.05);
  font-family: 'Raleway', sans-serif;
  position: relative;
  padding: 0 30px;
  z-index: 999;
}

.mainNav__start {
  display: flex;
  align-items: center;
  gap: 20px;
  position: relative;
  background-size: 100vh;
}

.mainNav__hamburger {
  cursor: pointer;
}

.menu-icon {
  fill: #637498;
  transition: transform 0.3s ease;
}

.menu-icon:hover {
  transform: scale(1.1);
}

.mainNav__links {
  display: flex;
  gap: 16px;
}

.mainNav__link {
  font-weight: 600;
  font-size: 14px;
  color: #637498;
  text-decoration: none;
  transition: 0.3s;
  padding-bottom: 7px;
}

.mainNav__link:hover {
  transform: translateY(-2px);
  background: rgba(99, 116, 152, 0.1);
  border-radius: 4px;
}

.dropdownMenu {
  position: absolute;
  top: 80px;
  background-color: white;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
  border-radius: 8px;
  overflow: hidden;
  z-index: 1000;
  min-width: 160px;
}

.dropdownMenu.left {
  left: 0;
}

.dropdownMenu.right {
  right: 0;
}

.dropdownItem {
  display: block;
  padding: 10px 20px;
  font-size: 14px;
  color: #637498;
  text-decoration: none;
  transition: background 0.3s;
}

.dropdownItem:hover {
  background-color: #f0f2f5;
}

.mainNav__logo {
  position: relative;
  cursor: pointer;
  padding-right: 55px;
}

.mainNav__logo img {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  object-fit: cover;
  
}
.fade-enter-active, .fade-leave-active {
  transition: opacity 0.2s ease-in-out;
}
.fade-enter-from, .fade-leave-to {
  opacity: 0;
}
</style>
