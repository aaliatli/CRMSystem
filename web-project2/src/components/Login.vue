<template>
    <div class="login-container">
        <table>
            <tr>
                <td>
                    <label for="mail">Kullanıcı: </label>
                    <input type="mail" placeholder="e-mail" v-model="userMail" required >
                </td>
            </tr>
            <tr>
                <td>
                    <label for="password">Şifre: </label>
                    <input type="password" v-model="userPassword" required >
                </td>
            </tr>
            <tr>
                <td><button @click="submitUsers">Giriş</button></td>
            </tr>
        </table>
        <p v-if="loginSuccess === false">Giriş bilgilerini kontrol edin!</p>
    </div>

</template>

<script>
import axios from 'axios';
export default{
    data(){
        return{
            userMail: '',
            userPassword: '',
            loginSuccess: null,
        }
    },
    methods:{
        async submitUsers() {
        try {
            const response = await axios.post('http://localhost:8081/api/login', {
            userMail: this.userMail,
            userPassword: this.userPassword
            });

            if (response.data !== "Hatalı kullanıcı adı ya da şifre") {
            localStorage.setItem("isLoggedIn", "true");
            localStorage.setItem("loggedInUser", response.data); 
            this.loginSuccess = true;
            this.$router.replace('/analizler').then(() => {
                location.reload(); 
            });
            } else {
            this.loginSuccess = false;
            }
        } catch (error) {
            this.loginSuccess = false;
            console.error("Login hatası:", error);
        }
        }


    }
}
</script>

<style scoped>
body, html {
  height: 100%;
  margin: 0;
  background-color: #f2f2f2;
}

.login-container {
  padding: 2% 6%;
  height: 65vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

table {
  background-color: #fff;
  padding: 30px;
  width: 100%;
  max-width: 400px;
  border-radius: 10px;
  box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1);
}

td {
  padding: 10px 0;
  display: flex;
  flex-direction: column;
}

input {
  padding: 10px;
  width: 100%;
  box-sizing: border-box;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 14px;
}

button {
  padding: 10px 20px;
  margin-top: 10px;
  background-color: #3498db;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-weight: bold;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #2980b9;
}
</style>
