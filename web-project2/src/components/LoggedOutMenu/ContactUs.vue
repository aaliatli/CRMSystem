<template>
  <div class="contact-container">
    <div>
      <h3>İletişim</h3>
    </div>
    <div>
      <p>
        Bizimle iletişime geçmek için aşağıdaki kanallardan faydalanabilirsiniz:
      </p>
      <ul>
        <li><strong>E-Posta:</strong> info@aacrm.com</li>
        <li><strong>Telefon:</strong> +90 (212) 123 45 67</li>
        <li><strong>Adres:</strong> İstanbul</li>
        <li><strong>Destek Saatleri:</strong> Hafta içi her gün 09:00 - 18:00</li>
      </ul>
      <p>İletişim formumuz üzerinden de bize ulaşabilirsiniz. Size en kısa sürede geri dönüş yapılacaktır.</p>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.contact-container {
  padding: 2% 6%;
  font-family: Arial, sans-serif;
  line-height: 1.6;
  height: 65vh;
  background-color: #e9e2cc;
  position: relative;
  overflow: hidden;
}

.contact-container::after {
  content: "";
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 100px;
  background: linear-gradient(to bottom, #e9e2cc 40%, rgba(255, 255, 255, 1) 100%);
  pointer-events: none;
}


.contact-container h3 {
  margin-bottom: 1rem;
  font-size: 1.5rem;
  color: #e9e2cc;
}

.contact-container ul {
  padding-left: 1.5rem;
  list-style-type: disc;
}

.contact-container li {
  margin-bottom: 0.5rem;
}
</style>
