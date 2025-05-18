<template>
  <div class="all-notification-container">
    <table class="all-notificaiton">
      <tbody>
        <tr>
          <td class="all-notification-left">
            <!-- Sabit kalan butonlar -->
            <div class="select-bar">
              <button
                v-for="option in options"
                :key="option.value"
                :class="['select-option', { active: service === option.value }]"
                @click="service = option.value"
              >
                {{ option.label }}
              </button>
            </div>

            <!-- Sadece seçilen tablo görünür -->
            <div class="table-container">
              <table v-if="service === 'magaza'">
                <h5>Mağaza Lokasyon Bildirimleri</h5>
                <tr v-for="item in notifications" :key="item.id">
                  <td>{{ item.id }}</td>
                  <td>{{ item.storeLocation }}</td>
                </tr>
              </table>

              <table v-else-if="service === 'sandalye'">
                <h5>Tekerlekli Sandalye Bildirimleri</h5>
                <tr v-for="item in notifications" :key="item.id">
                  <td>{{ item.id }}</td>
                  <td>{{ item.name }}</td>
                  <td>{{ item.phone }}</td>
                  <td>{{ item.mail }}</td>
                  <td>{{ item.deskLocation }}</td>
                </tr>
              </table>

              <table v-else-if="service === 'bebek'">
                <h5>Bebek Arabası Bildirimleri</h5>
                <tr>
                  <td>Durum</td>
                  <td>İsim</td>
                  <td>Telefon</td>
                  <td>Mail</td>
                  <td>Desk</td>
                </tr>
                <tr v-for="item in notifications" >
                  <td class="stroller"> Durum</td>
                  <td class="stroller">{{ item.name }}</td>
                  <td class="stroller">{{ item.phone }}</td>
                  <td class="stroller">{{ item.mail }}</td>
                  <td class="stroller">{{ item.deskLocation }}</td>
                </tr>
              </table>

              <table class="pet-stroller" v-else-if="service === 'puset'">
                <h5>Hayvan Pusedi Bildirimleri</h5>
                <tr v-for="item in notificationList" :key="item.id">
                  <td>{{ item.id }}</td>
                  <td>{{ item.name }}</td>
                  <td>{{ item.phone }}</td>
                  <td>{{ item.mail }}</td>
                  <td>{{ item.deskLocation }}</td>
                </tr>
              </table>

            </div>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>


<script>
import axios from 'axios';
import { computed } from 'vue';

export default {
  data() {
    return {
      name: '',
      phone: '',
      mail: '',
      storeLocation: '',
      description: '',
      deskLocation: '',
      serialNo: '',
      notificationList: [],
      service: 'magaza', // Varsayılan olarak ilk tablo açık
      options: [
        { label: 'Mağaza Lokasyonu', value: 'magaza' },
        { label: 'Tekerlekli Sandalye', value: 'sandalye' },
        { label: 'Hayvan Pusedi', value: 'puset' },
        { label: 'Bebek Arabası', value: 'bebek' },
      ]
    };
  },
  mounted() {
    this.fetchNotifications();
  },
  methods: {
    async fetchNotifications() {
      try {
        const response = await axios.get("http://localhost:8081/api/notification");
        this.notifications = response.data.reverse();
      } catch (error) {
        console.error("Veri çekme hatası", error);
      }
    }
  },
};
</script>


<style>
.select-bar {
  display: inline-flex;
  border: 1px solid #ccc;
  border-radius: 5px;
  overflow: hidden;
}

.select-option {
  background-color: white;
  color: #333;
  border: none;
  padding: 8px 16px;
  cursor: pointer;
  outline: none;
  transition: background-color 0.3s;
}

.select-option:not(:last-child) {
  border-right: 1px solid #ccc;
}

.select-option.active {
  background-color: #3498db;
  color: white;
  font-weight: bold;
}


.all-notification-container {
  padding: 2% 6%;
}

.all-notification {
  width: 100%;
  border-collapse: collapse;
}

.all-notification td {
  padding: 10px;
  background-color: #fff;
  border: 1px solid #ccc;
}

.pet-stroller{
  border: 1px solid #ccc;
}
.stroller{
  border: 1px solid #ccc;
  width: 100vh;
  height: 8px;
}
</style>