<template>
  <div class="all-notifications-container">
    <h3>Tüm Bildirimler</h3>

    <div class="filter-bar">
      <div class="filter-group">
        <label>İsim: <input type="text" v-model="name" @input="applyBackendFilter" /></label>
      </div>
      <div class="filter-group">
        <label>Telefon: <input type="text" v-model="phone" @input="applyBackendFilter" /></label>
      </div>
      <div class="filter-group">
        <label>Mail: <input type="text" v-model="mail" @input="applyBackendFilter" /></label>
      </div>
    </div>

    <table class="all-notifications">
      <thead>
        <tr class="header-row">
          <td>Durum</td>
          <td>Bildirim Türü</td>
          <td>Ad Soyad</td>
          <td>Telefon</td>
          <td>Mail</td>
          <td>Mağaza Lokasyon</td>
          <td>Desk</td>
          <td>Açıklama</td>
          <td>Seri No</td>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in notifications" :key="item.id">
          <td>
            <select @change="handleStatusChange($event, item.id)">
              <option value="Açık">Açık</option>
              <option value="Kapalı">Kapalı</option>
            </select>
          </td>
          <td class="notification">{{ item.service }}</td>
          <td class="notification">{{ item.name }}</td>
          <td class="notification">{{ item.phone }}</td>
          <td class="notification">{{ item.mail }}</td>
          <td class="notification">{{ item.storeLocation }}</td>
          <td class="notification">{{ deskLabel(item.deskLocation) }}</td>
          <td class="notification">{{ item.description }}</td>
          <td class="notification">{{ item.serialNo }}</td>
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
            notifications: [],
            name: '',
            phone: '',
            mail: '',
            storeLocation: '',
            description: '',
            deskLocation: '',
            serialNo: '',
        }
    },
    mounted(){
        this.fetchNotifications();
    },
    methods:{
        async fetchNotifications(){
            try{
                const response = await axios.get('http://localhost:8081/api/notification');
                this.notifications = response.data.reverse();
            }catch(error){
                console.error("Veri çekme hatası", error);
            }
            
        },
        async handleStatusChange(event, id){
            const selectedStatus = event.target.value;
            if(selectedStatus==='Kapalı'){
                await this.deleteNotifications(id);
                await this.fetchNotifications();
            }
        },
        deskLabel(deskLocation){
            switch(deskLocation){
                case 0: return "Cs";
                case 1: return "Desk 1";
                case 2: return "Desk 2";
                case 3: return "Desk 3";
                default: return "-";
            }
        },
        async deleteNotifications(id){
            try{
                setTimeout(async () => {
                await axios.delete(`http://localhost:8081/api/notifications/${id}`);      
                await this.fetchNotifications(); 
                }, 1000)
            }catch(error){
                console.error("Silme hatası: ", error)
            }
        },
        async applyBackendFilter(){
          const params = {};
          if(this.name !== ''){
            params.name = this.name;
          } 
          if(this.phone !== ''){
            params.phone = this.phone;
          }
          if(this.mail !== ''){
            params.mail = this.mail;
          }

          try{
            const response = await axios.get('http://localhost:8081/api/notification/all', {
              params: params
            });
            if(response&&response.data){
              this.notifications = response.data.reverse();
            }
          }catch(error){
            console.error("", error)
          }


        }

        
    }
}

</script>

<style>
.all-notifications-container {
  padding: 2% 6%;
}

.filter-bar {
  display: flex;
  justify-content: center;
  gap: 100px;
  margin-bottom: 20px;
}

.filter-group {
  display: flex;
  flex-direction: column;
  display: flex;
}

.filter-group label {
  margin-bottom: 5px;
  font-weight: bold;
}

.header-row td {
  font-weight: bold;
  background-color: #f2f2f2;
}

.notification {
  border: 1px solid #ccc;
  padding: 10px;
  min-width: 100px;
}


</style>