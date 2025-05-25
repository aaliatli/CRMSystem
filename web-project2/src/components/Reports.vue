<template>
  <div class="reports-container ">
    <table class="reports">
      <tbody>
        <tr>
          <td>
            <div>
              <table v-if="service === 'Şikayet'">
                <h5>Şikayet Bildirimleri</h5>
                <tr>
                  <td>Durum</td>
                  <td>İsim</td>
                  <td>Telefon</td>
                  <td>Mail</td>
                  <td>Desk</td>
                  <td>Açıklama</td>
                  <td>Form Seri No</td>
                </tr>
                <tr v-for="item in notifications"  :key="item.id">
                  <td class="status" > 
                    <select @change="handleStatusChange($event, item.id)">
                      <option value="Açık">Açık</option>
                      <option value="Kapalı">Kapalı</option>
                    </select>
                  </td>
                  <td class="reports-notificiation">{{ item.name }}</td>
                  <td class="reports-notificiation">{{ item.phone }}</td>
                  <td class="reports-notificiation">{{ item.mail }}</td>
                  <td class="reports-notificiation">{{ item.deskLocation }}</td>
                  <td class="reports-notificiation">{{item.description}}</td>
                  <td class="reports-notificiation"> {{item.serialNo}}</td>
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

export default{
    data(){
        return{
            name: '',
            phone: '',
            mail: '',
            storeLocation: '',
            description: '',
            deskLocation: '',
            serialNo: '',
            notifications: [],
            service: 'Şikayet',
        };
    },
    mounted(){
        this.fetchNotifications();
    },
    methods:{
      async handleStatusChange(event, id){
      const selectedStatus = event.target.value;
      if(selectedStatus === 'Kapalı'){
        await this.deleteNotification(id);
        await this.fetchNotifications();
      }
      },
      async deleteNotification(id) {
        try {
          setTimeout(async () => {
            await axios.delete(`http://localhost:8081/api/notifications/${id}`);
            await this.fetchNotifications(); 
          }, 2500);
        } catch (error) {
          console.error("Silme hatası: ", error);
        }
      },
      async selectService(service){
        this.service = service;
        await this.fetchNotifications();
      },
      async fetchNotifications(){
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
.reports-container {
  padding: 2% 6%;
}
.reports{
  width: 100%;
  border-collapse: collapse;
  background-color: #fff;
  table-layout: fixed;
}
.reports-notificiation{
    border: 1px solid #ccc;
    padding: 10px;
    width: 100vh;
    height: 8px;
}
select{
  width: 80px;
}

</style>