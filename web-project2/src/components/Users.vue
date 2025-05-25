<template>
    <div>
        <table>
            <tr>
                <td>Kullanıcı: </td>
            </tr>
            <tr v-for="item in notifications"> 
                <td>{{item.mail}}</td>
            </tr>
        </table>
    </div>

</template>

<script>
import axios from 'axios';
export default{
    data(){
        return{
            notifications: [],
        }
    },
    mounted(){
        this.fetchNotification;
    },
    methods:{
        async fetchNotification(){
            const response = await axios.get();
        }
    }
}

</script>

<style>

</style>