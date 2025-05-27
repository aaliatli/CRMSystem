import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '@/components/HomePage.vue'
import AddNotification from '@/components/LoggedInMenu/AddNotification.vue'
import Users from '@/components/RightDropMenu/Users.vue'
import Analysis from '@/components/LoggedInMenu/Analysis.vue'
import Reports from '@/components/LeftDropMenu/Reports.vue'
import StoreNotifications from '@/components/LeftDropMenu/StoreNotifications.vue'
import ItemDepositNotifications from '@/components/LeftDropMenu/ItemDepositNotifications.vue'
import Login from '@/components/Login.vue'
import AboutUs from '@/components/LoggedOutMenu/AboutUs.vue'
import Solutions from '@/components/LoggedOutMenu/Solutions.vue'
import ContactUs from '@/components/LoggedOutMenu/ContactUs.vue'

const routes = [
  { path: '/', component: HomePage },
  { path: '/giris', component: Login },
  { path: '/hakkimizda', component: AboutUs },
  { path: '/cozumler', component: Solutions },
  { path: '/iletisim', component: ContactUs },

  { path: '/bildirim-ekle', component: AddNotification, meta: { requiresAuth: true }},
  { path: '/sikayetler', component: Reports, meta: { requiresAuth: true }},
  { path: '/kullanicilar', component: Users, meta: { requiresAuth: true }},
  { path: '/analizler', component: Analysis, meta: { requiresAuth: true }},
  { path: '/bildirimler', component: ItemDepositNotifications, meta: { requiresAuth: true }},
  { path: '/magaza-bildirimleri', component: StoreNotifications, meta: { requiresAuth: true }},
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  const isLoggedIn = localStorage.getItem("isLoggedIn") === "true";

  if (to.meta.requiresAuth && !isLoggedIn) {
    next('/giris');
  } else {
    next();
  }
});

export default router;
