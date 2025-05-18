import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '@/components/HomePage.vue'
import AddNotification from '@/components/AddNotification.vue'
import Settings from '@/components/Settings.vue'
import Users from '@/components/Users.vue'
import Analysis from '@/components/Analysis.vue'
import AllNotifications from '@/components/AllNotifications.vue'
import Reports from '@/components/Reports.vue'
import StarNotifications from '@/components/StarNotifications.vue'


const routes = [
  { path: '/api', component: HomePage },
  { path: '/bildirim-ekle', component: AddNotification },
  { path: '/ayarlar', component: Settings},
  { path: '/sikayetler', component: Reports},
  { path: '/kullanicilar', component: Users},
  { path: '/analizler', component: Analysis},
  { path: '/bildirimler', component: AllNotifications},
  { path: '/yildizli-bildirimler', component: StarNotifications}

]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
