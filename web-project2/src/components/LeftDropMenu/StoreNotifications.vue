<template>
  <div class="store-container ">
    <table class="stores">
      <tbody>
        <tr>
          <td>
            <div>
              <table>
                <h5>Mağaza Bildirimleri</h5>
                <tr class="width">
                  <td>Desk</td>
                  <td>Mağaza</td>
                </tr>
                <tr v-for="item in storeNotifications" :key="item.id">
                  <td class="store-notificiation">{{item.deskLocation }}</td>
                  <td class="store-notificiation">{{item.storeLocation}}</td>
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
            storeLocation: '',
            deskLocation: '',
            storeNotifications: [],
        }
    },
    mounted(){
        this.fetchNotifications();
    },
    methods:{
        async fetchNotifications(){
            const response = await axios.get(`http://localhost:8081/api/store`);
            this.storeNotifications = response.data.reverse();
        },
    },
}


</script>

<style>

.store-container{
  padding: 2% 6%;
}

.stores{
  width: 100%;
  border-collapse: collapse;
  background-color: #fff;
  table-layout: fixed;
}

.store-notificiation{
    border: 1px solid #ccc;
    padding: 1% 2%;
}

</style>