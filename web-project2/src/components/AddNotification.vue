<template>
  <div class="notification-container">
    <form @submit.prevent="submitForm">
      <table class="notification">
        <tbody>
          <tr>
            <td class="notification-top">
              <label class="notification-label">Bildirim Seçin</label>
              <select v-model="service" class="select" required>
                <option value="">Seçiniz</option>
                <option>Mağaza Lokasyon</option>
                <option>Tekerlekli Sandalye</option>
                <option>Bebek Arabası</option>
                <option>Hayvan Pusedi</option>
                <option>Şikayet</option>
              </select>

              <div class="notification-info">
                <div class="info-row">
                  <div class="input-group">
                    <label>Ad Soyad:</label>
                    <input type="text" placeholder="Ad Soyad" :disabled="isStoreLocation" v-model="name" required />
                  </div>
                  <div class="input-group">
                    <label>Telefon Numarası:</label>
                    <input type="tel" placeholder="05xx xxx xx xx" :disabled="isStoreLocation" v-model="phone" required
                    maxlength="10" minlength="10" pattern="[0-9]{10}" inputmode="numeric" />
                  </div>
                </div>

                <div class="info-row">
                  <div class="input-group">
                    <label>e-mail:</label>
                    <input type="email" placeholder="e-mail" :disabled="isStoreLocation" v-model="mail" />
                  </div>
                  <div class="input-group" style="position: relative;">
                    <label>Mağaza Lokasyon:</label>
                    <input
                      v-model="searchQuery"
                      @focus="showSuggestions = true"
                      @input="showSuggestions = true"
                      @blur="hideSuggestions"
                      type="text"
                      placeholder="Mağaza ara"
                      :disabled="isStoreLocationDisabled"
                      :required="isStoreLocation"
                    />
                    <ul v-if="showSuggestions && filteredBrands.length" class="suggestion-list">
                      <li
                        v-for="(brand, index) in filteredBrands"
                        :key="index"
                        @mousedown.prevent="selectBrand(brand)"
                      >
                        {{ brand }}
                      </li>
                    </ul>
                  </div>
                </div>

                <div class="info-row">
                  <div class="input-group">
                    <label>Seri No:</label>
                    <input type="number" placeholder="Seri No" :disabled="isStoreLocation" v-model="serialNo" />
                  </div>
                  <div class="input-group">
                    <label>Desk Lokasyon:</label>
                    <div class="select-bar-desk">
                      <button
                        v-for="option in options"
                        :key="option.value"
                        :class="['select-option', { active: deskLocation === option.value }]"
                        type="button"
                        @click="deskLocation = option.value"
                      >
                        {{ option.label }}
                      </button>
                    </div>
                  </div>
                </div>

                <div class="info-row">
                  <div class="input-group full">
                    <label>Açıklama:</label>
                    <input placeholder="Açıklama..." :disabled="isStoreLocation" v-model="description" />
                  </div>
                </div>
              </div>
              <button type="submit" value="send request">Gönder</button>
            </td>

            <td class="notification-right">
              <label class="notification-label">Bildirim Özeti</label>
              <ul class="summary-list">
                <li v-for="(item, index) in notification.slice(-1)" :key="index">
                  <strong>{{ item.type }}</strong><br />
                  Ad Soyad: {{ item.name }} <br />
                  Telefon: {{ item.phone }} <br />
                  Mail: {{ item.mail }} <br />
                  Mağaza: {{ item.storeLocation }} <br />
                  Desk: {{ item.deskLocation }} <br />
                  Açıklama: {{ item.description }}
                </li>
              </ul>
            </td>
          </tr>
          <tr>
            <td class="notification-bottom" v-if="sendedOption">Gönderildi</td>
            <td class="notification-bottom" v-else></td>
            <td class="notification-corner">Destek: destek@softcrm.com</td>
          </tr>
        </tbody>
      </table>
    </form>
  </div>
</template>



<script>
import axios from 'axios';

export default {
  name: 'AddNotification',
  data() {
    return {
      service: '',
      sendedOption: false,
      name: '',
      phone: '',
      mail: '',
      storeLocation: '',
      description: '',
      serialNo: '',
      deskLocation: '',
      searchQuery: '',
      showSuggestions: false,
      brand: [
        'Adidas', 'Bershka', 'Boyner', 'Decathlon',
        'DeFacto', 'English Home', 'FLO', 'Gratis',
        'H&M', 'Koton', 'LC Waikiki', 'Madame Coco',
        'Mavi', 'Mugo Jewellery', 'Nike', 'Sephora',
        'Stradivarius', 'Teknosa', 'Vatan Bilgisayar', 'Watsons', 'Zara',
      ],
      notification: [], 
      options: [
        { label: 'CS', value: 'CS' },
        { label: 'Desk 1', value: '1' },
        { label: 'Desk 2', value: '2' },
        { label: 'Desk 3', value: '3' },
      ]
    };
  },
  computed: {
    isStoreLocation() {
      return this.service === 'Mağaza Lokasyon';
    },
    filteredBrands() {
      return this.brand.filter(item =>
        item.toLowerCase().includes(this.searchQuery.toLowerCase())
      );
    },
    isStoreLocationDisabled() {
      const disabledtypes = ['Tekerlekli Sandalye', 'Bebek Arabası', 'Hayvan Pusedi'];
      return disabledtypes.includes(this.service);
    }
  },
  methods: {
    selectBrand(brand) {
      this.searchQuery = brand;
      this.storeLocation = brand;
      this.showSuggestions = false;
    },
    hideSuggestions() {
      setTimeout(() => {
        this.showSuggestions = false;
      }, 200);
    },
    async submitForm() {
      const payload = {
        service: this.service,
        name: this.name,
        phone: this.phone,
        mail: this.mail,
        storeLocation: this.storeLocation,
        description: this.description,
        serialNo: parseInt(this.serialNo),
        deskLocation: parseInt(this.deskLocation),
      };

      try {
        const response = await axios.post('http://localhost:8081/api/notifications', payload);
        if (response.status === 200) { 
          this.notification.push({
            type: this.service, 
            name: this.name,
            phone: this.phone,
            mail: this.mail,
            description: this.description,
            deskLocation: this.deskLocation,
            storeLocation: this.storeLocation, 
          });
          this.sendedOption = true;
          setTimeout(() => {
            this.sendedOption = false;
          }, 2500);
        }
      } catch (error) {
        console.error("Bildirim gönderme hatası:", error);
      } finally {
        this.name = '';
        this.phone = '';
        this.mail = '';
        this.storeLocation = '';
        this.description = '';
        this.serialNo = '';
        this.deskLocation = '';
        this.searchQuery = '';
        this.service = '';
      }
    }
  }
};
</script>




<style>
.notification-container {
  padding: 2% 6%;
}

table.notification {
  width: 100%;
  border-collapse: collapse;
  background-color: #fff;
  table-layout: fixed;
}

.notification-top {
  border: 1px solid #ccc;
  padding: 20px;
  width: 70%;
  vertical-align: top;
}

.notification-right{
  border: 1px solid #ccc;
  padding: 20px;
  vertical-align: top;
  width: 30%;
}

.notification-bottom,
.notification-corner {
  padding: 16px;
  vertical-align: top;
  width: 30%;
}

.notification-label {
  display: block;
  font-weight: bold;
  margin-bottom: 8px;
  background-color: #fff3cd;
  padding: 6px;
  border-radius: 4px;
}

select {
  width: 100%;
  padding: 8px;
  margin-bottom: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.notification-info {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.notification-info label {
  font-weight: 500;
  margin-bottom: 4px;
}

.notification-info input {
  padding: 6px;
  border: 1px solid #ccc;
  border-radius: 4px;
  width: 100%;
}

.info-row {
  display: flex;
  gap: 20px;
  margin-bottom: 12px;
}

.input-group {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.input-group.full {
  flex: 1 1 100%;
}

.suggestion-list {
  position: absolute;
  top: 100%;
  left: 0;
  width: 100%;
  background: white;
  border: 1px solid #ccc;
  border-top: none;
  z-index: 1000;
  max-height: 150px;
  overflow-y: auto;
  padding: 0;
  margin: 0;
  list-style: none;
}

.suggestion-list li {
  padding: 8px 12px;
  cursor: pointer;
}

.suggestion-list li:hover {
  background-color: #f0f0f0;
}

li{
  list-style-type: none;
}
.select-bar-desk{
  display: flex;
  height: 29px;
}
.select-bar-desk button{
  width: 100%;
  align-items: center;
  display: flex;
  background: white;
  border: 1px solid #ccc;
  padding: 6px;
  border-radius: 4px;
}
.select{
  width: 100%;
}
</style>