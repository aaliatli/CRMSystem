<template>
  <div class="solutions-container">
    <div>
      <h3>Çözümlerimiz</h3>
    </div>
    <div>
      <p>
        AACRM olarak sunduğumuz çözümler, satış, pazarlama ve müşteri hizmetleri ekiplerinizi daha güçlü hale getirmek için özel olarak tasarlanmıştır:
      </p>
      <ul>
        <li><strong>Müşteri Takibi ve Yönetimi:</strong> Tüm müşteri verilerini tek noktada toplayarak geçmiş işlemleri takip edin ve analiz edin.</li>
        <li><strong>Satış Süreci Otomasyonu:</strong> Potansiyel müşterileri kolayca yönetin, satış fırsatlarını takip edin, teklif süreçlerini hızlandırın.</li>
        <li><strong>Görev ve Ekip Yönetimi:</strong> Ekibinizin görev dağılımını takip edin, iletişimi merkezi hale getirin.</li>
        <li><strong>Raporlama ve Analiz:</strong> Gerçek zamanlı raporlarla iş süreçlerinizi daha verimli yönetin.</li>
        <li><strong>Bildirim ve Hatırlatmalar:</strong> Önemli aktiviteleri kaçırmamak için sistemden otomatik uyarılar alın.</li>
        <li><strong>Mobil Uyumlu Arayüz:</strong> Dilediğiniz yerden erişim ile esnek çalışma imkanı.</li>
      </ul>
      <p>Her çözümümüz ihtiyaca göre özelleştirilebilir yapıdadır.</p>
    </div>
  </div>
</template>

<script>
export default {

};
</script>

<style scoped>
.solutions-container {
  padding: 2% 6%;
  font-family: Arial, sans-serif;
  line-height: 1.6;
  height: 65vh;
  background-color: #f1e9e9;
  position: relative;
  overflow: hidden;
}

.solutions-container::after {
  content: "";
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 100px; 
  background: linear-gradient(to bottom, rgba(241, 233, 233, 0) 0%, rgba(241, 233, 233, 0.7) 40%, rgba(255, 255, 255, 1) 100%);
  pointer-events: none;
}


.solutions-container h3 {
  margin-bottom: 1rem;
  font-size: 1.5rem;
  color: #f1e9e9;
}

.solutions-container ul {
  padding-left: 1.5rem;
  list-style-type: disc;
}

.solutions-container li {
  margin-bottom: 0.5rem;
}
</style>
