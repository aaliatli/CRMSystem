<template>
  <div class="aboutus-container">
    <div>
      <h3>Hakkımızda</h3>
    </div>
    <div>
      <p>
        AACRM, işletmelerin müşteri ilişkilerini etkin ve verimli bir şekilde yönetmelerine yardımcı olmak amacıyla geliştirilmiş modern bir CRM platformudur.
        Kullanıcı dostu arayüzümüz ve güçlü altyapımız sayesinde müşteri memnuniyetini artırmayı, satış süreçlerini optimize etmeyi ve ekip içi iş birliğini güçlendirmeyi hedefliyoruz.
        Küçük işletmelerden büyük ölçekli firmalara kadar her sektöre özel çözümler sunarak dijital dönüşüm sürecine katkıda bulunuyoruz.
      </p>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.aboutus-container {
  padding: 2% 6%;
  font-family: Arial, sans-serif;
  line-height: 1.6;
  height: 65vh;
  background-color: #ebd4e0;
  position: relative;
  overflow: hidden;
}

.aboutus-container::after {
  content: "";
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 100px; 
  background: linear-gradient(to bottom, #ebd4e0 40%, rgba(255, 255, 255, 1) 100%);
  pointer-events: none;
}


.aboutus-container h3 {
  margin-bottom: 1rem;
  font-size: 1.5rem;
  color: #ebd4e0;
}


.aboutus-container li {
  margin-bottom: 0.5rem;
}
</style>
