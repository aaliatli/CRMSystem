<template>
  <div class="progress-wrapper">
    <div class="process-bar">
      <div class="progress-bar">
        <div
          class="progress-segment cs"
          :style="{ height: csPercentage + '%', backgroundColor: '#4caf50' }"
          :title="'CS - ' + csCount + ' kayıt (%' + csPercentage.toFixed(1) + ')'"
        >{{ csCount }}</div>
      </div>
      <span class="label-bottom">CS</span>
    </div>

    <div class="process-bar">
      <div class="progress-bar">
        <div
          class="progress-segment desk1"
          :style="{ height: desk1Percentage + '%', backgroundColor: '#ff9800' }"
          :title="'Desk 1 - ' + desk1Count + ' kayıt (%' + desk1Percentage.toFixed(1) + ')'"
        >{{ desk1Count }}</div>
      </div>
      <span class="label-bottom">Desk 1</span>
    </div>

    <div class="process-bar">
      <div class="progress-bar">
        <div
          class="progress-segment desk2"
          :style="{ height: desk2Percentage + '%', backgroundColor: '#2196f3' }"
          :title="'Desk 2 - ' + desk2Count + ' kayıt (%' + desk2Percentage.toFixed(1) + ')'"
        >{{ desk2Count }}</div>
      </div>
      <span class="label-bottom">Desk 2</span>
    </div>

    <div class="process-bar">
      <div class="progress-bar">
        <div
          class="progress-segment desk3"
          :style="{ height: desk3Percentage + '%', backgroundColor: '#f44336' }"
          :title="'Desk 3 - ' + desk3Count + ' kayıt (%' + desk3Percentage.toFixed(1) + ')'"
        >{{ desk3Count }}</div>
      </div>
      <span class="label-bottom">Desk 3</span>
    </div>
  </div>
</template>


<script>
import axios from 'axios';

export default {
  data() {
    return {
      notifications: [],
      csCount: 0,
      desk1Count: 0,
      desk2Count: 0,
      desk3Count: 0,
    };
  },
  mounted() {
    this.fetchNotifications();
  },
  computed: {
    totalCount() {
      return this.csCount + this.desk1Count + this.desk2Count + this.desk3Count;
    },
    csPercentage() {
      return this.totalCount === 0 ? 0 : (this.csCount / this.totalCount) * 100;
    },
    desk1Percentage() {
      return this.totalCount === 0 ? 0 : (this.desk1Count / this.totalCount) * 100;
    },
    desk2Percentage() {
      return this.totalCount === 0 ? 0 : (this.desk2Count / this.totalCount) * 100;
    },
    desk3Percentage() {
      return this.totalCount === 0 ? 0 : (this.desk3Count / this.totalCount) * 100;
    }
  },
  methods: {
    async fetchNotifications() {
      try {
        const response1 = await axios.get("http://localhost:8081/api/store");
        const response2 = await axios.get("http://localhost:8081/api/notification");
        this.notifications = [...response1.data, ...response2.data].reverse();

        this.csCount = 0;
        this.desk1Count = 0;
        this.desk2Count = 0;
        this.desk3Count = 0;

        this.notifications.forEach(item => {
          switch (item.deskLocation) {
            case 0:
              this.csCount++;
              break;
            case 1:
              this.desk1Count++;
              break;
            case 2:
              this.desk2Count++;
              break;
            case 3:
              this.desk3Count++;
              break;
          }
        });
      } catch (error) {
        console.error("Veri çekme hatası:", error);
      }
    }
  }
};
</script>


<style scoped>
.analysis-container {
  padding: 2% 6%;
}

.progress-wrapper{
  display: inline;
}

.select-bar {
  display: inline-flex;
  border: 1px solid #ccc;
  border-radius: 5px;
  overflow: hidden;
  margin-bottom: 16px;
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

.table-container {
  margin-top: 10px;
}

.analysis {
  width: 100%;
  border-collapse: collapse;
}

.analysis td {
  padding: 10px;
  background-color: #fff;
  border: 1px solid #ccc;
}
.progress-wrapper {
  display: flex;
  justify-content: center;
  align-items: flex-end;
  gap: 20px;
  margin-top: 40px;
}

.process-bar {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.progress-bar {
  width: 40px;
  height: 350px;
  margin: 0px 30px;
  background-color: #e0e0e0;
  border-radius: 6px;
  overflow: hidden;
  display: flex;
  flex-direction: column-reverse; /* Bar yukarıdan dolsun */
}

.progress-segment {
  width: 100%;
  min-height: 3px; 
  transition: height 0.5s ease;
}

.label-bottom {
  margin-top: 8px;
  font-weight: bold;
  text-align: center;
}

.cs {
  background-color: #4caf50;
}
.desk1 {
  background-color: #2196f3;
}
.desk2 {
  background-color: #ff9800;
}
.desk3 {
  background-color: #f44336;
}

</style>
