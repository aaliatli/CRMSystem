<template>
  <div class="deposit-container">
    <table>
      <tbody>
        <tr>
          <td class="deposit-left">
            <div class="select-bar">
              <button
                v-for="option in options"
                :key="option.value"
                :class="['select-option', { active: service === option.value }]"
                @click="selectService(option.value)"
              >
                {{ option.label }}
              </button>
            </div>

            <div class="table-container">
              <table v-if="service === 'Tekerlekli Sandalye'">
                <h5>Tekerlekli Sandalye Bildirimleri</h5>
                <tr class="width">
                  <td>Durum</td>
                  <td>İsim</td>
                  <td>Telefon</td>
                  <td>Mail</td>
                  <td>Desk</td>
                  <td>Form Seri No</td>
                </tr>
                <tr v-for="item in notifications"  :key="item.id">
                  <td class="status" > 
                    <select name="" id="" @change="handleStatusChange($event, item.id)">
                      <option value="Açık">Açık</option>
                      <option value="Kapalı">Kapalı</option>
                    </select>
                  </td>
                  <td class="wheelchair">{{ item.name }}</td>
                  <td class="wheelchair">{{ item.phone }}</td>
                  <td class="wheelchair">{{ item.mail }}</td>
                  <td class="wheelchair">{{ item.deskLocation }}</td>
                  <td class="wheelchair"> {{item.serialNo}}</td>
                </tr>
              </table>

              <table v-else-if="service === 'Bebek Arabası'" >
                <h5>Bebek Arabası Bildirimleri</h5>
                <tr class="width">
                  <td>Durum</td>
                  <td>İsim</td>
                  <td>Telefon</td>
                  <td>Mail</td>
                  <td>Desk</td>
                  <td>Form Seri No</td>
                </tr>
                <tr v-for="item in notifications"  :key="item.id">
                  <td class="status" > 
                    <select @change="handleStatusChange($event, item.id)">
                      <option value="Açık">Açık</option>
                      <option value="Kapalı">Kapalı</option>
                    </select>
                  </td>
                  <td class="baby-stroller">{{ item.name }}</td>
                  <td class="baby-stroller">{{ item.phone }}</td>
                  <td class="baby-stroller">{{ item.mail }}</td>
                  <td class="baby-stroller">{{ item.deskLocation }}</td>
                  <td class="baby-stroller"> {{item.serialNo}}</td>
                </tr>
              </table>

              <table v-else-if="service === 'Hayvan Pusedi'" >
                <h5>Hayvan Pusedi Bildirimleri</h5>
                <tr class="width">
                  <td>Durum</td>
                  <td>İsim</td>
                  <td>Telefon</td>
                  <td>Mail</td>
                  <td>Desk</td>
                  <td>Form Seri No</td>
                </tr>
                <tr v-for="item in notifications"  :key="item.id">
                  <td class="status" > 
                    <select @change="handleStatusChange($event, item.id)">
                      <option value="Açık">Açık</option>
                      <option value="Kapalı">Kapalı</option>
                    </select>
                  </td>
                  <td  class="pet-stroller">{{ item.name }}</td>
                  <td  class="pet-stroller">{{ item.phone }}</td>
                  <td  class="pet-stroller">{{ item.mail }}</td>
                  <td  class="pet-stroller">{{ item.deskLocation }}</td>
                  <td class="pet-stroller"> {{item.serialNo}}</td>
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
      notifications: [],
      service: 'Tekerlekli Sandalye', 
      options: [
        { label: 'Tekerlekli Sandalye', value: 'Tekerlekli Sandalye' },
        { label: 'Hayvan Pusedi', value: 'Hayvan Pusedi' },
        { label: 'Bebek Arabası', value: 'Bebek Arabası' },
      ]

    };
  },
  mounted() {
    this.fetchNotifications();
  },
  methods: {
    async handleStatusChange(event, id){
      const selectedStatus = event.target.value;
      if(selectedStatus === 'Kapalı'){
        await this.deleteNotification(id);
        await this.fetchNotifications();
      }
    },
    async deleteNotification(id){
      try{
        setTimeout(async () => {
          await axios.delete(`http://localhost:8081/api/notifications/${id}`);      
          await this.fetchNotifications(); 
        }, 1000)
      }catch(error){
        console.error("Silme hatası: ", error)
      }
    },
    async selectService(service){
      this.service = service;
      await this.fetchNotifications();
    },
    async fetchNotifications() {
      try {
        const response = await axios.get(`http://localhost:8081/api/notification?service=${this.service}`);
        this.notifications = response.data.reverse();
      } catch (error) {
        console.error("Veri çekme hatası", error);
      }
    },

  },
};
</script>


<style>

.select-situation{
  width: 100px;
  padding-top: 10px;
}
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


.deposit-container {
  padding: 2% 6%;
}

.deposit {
  width: 100%;
  border-collapse: collapse;
}

.width{
  background-color: #f2f2f2;
  font-weight: bold;
}

.deposit td {

  background-color: #fff;
  
}
.pet-stroller{
  border: 1px solid #ccc;
  width: 100vh;
  padding: 10px;
}
.baby-stroller{
  border: 1px solid #ccc;
  width: 100vh;
  padding: 10px;
}
.wheelchair{
  border: 1px solid #ccc;
  width: 100vh;
  padding: 10px;
}
table{
  width: 100%;
}
select{
  width: 80px;
}

</style>