<template>
    <div id='bar' v-loading="loading" class="app-container ml-10"
       style="width: 800px;height:400px;" ></div>
</template>

<style>
</style>

<script>
import { getPerson } from '@/api/person';

const defaultForm = () => ({
  name: '',
  age: '',
});
export default {
  name: 'AgeChart',

  data() {
    return {
      rawList: [],
      form: defaultForm(),
      agecount: [],
      loading: false,
    };
  },
  mounted() {
    this.init();
  },
  beforeDestroy() {
    if (!this.chart) {
      return;
    }
    this.chart.dispose();
    this.chart = null;
  },
  methods: {
    async init() {
      await this.query();
      this.getBarList();
      this.myechart();
    },

    async query() {
      try {
        this.loading = true;
        const { code, list } = await getPerson(this.form);
        if (code === 200) {
          this.rawList = Array.isArray(list) ? list : [];
        }
      } finally {
        this.loading = false;
      }
    },

    getBarList() {
      // 获取到数据后显示数据
      this.agecount = new Array(4).fill(0);
      this.rawList.forEach((v) => {
        // 统计年龄个数
        if (v.age > 0 && v.age < 20) {
          this.agecount[0] += 1;
        } else if (v.age >= 20 && v.age < 40) {
          this.agecount[1] += 1;
        } else if (v.age >= 40 && v.age < 60) {
          this.agecount[2] += 1;
        } else if (v.age >= 60) {
          this.agecount[3] += 1;
        }
      });
    },

    myechart() {
      const myChart = this.$echarts.init(document.getElementById('bar'));

      const option = {
        title: { text: 'Age Analysis' },
        legend: { data: ['age'] },
        xAxis: {
          data: ['0-20', '20-40', '40-60', '60-100'],
        },
        yAxis: { minInterval: 1 },
        series: [{
          data: this.agecount,
          type: 'bar',
          barWidth: '30%',
          showBackground: true,
          backgroundStyle: {
            color: 'rgba(180, 180, 180, 0.2)',
          },
          itemStyle: {
            normal: {
              label: {
                show: true, // 开启显示
                position: 'top', // 在上方显示
                textStyle: { // 数值样式
                  color: 'black',
                  // fontSize: 16
                },
              },
            },
          },

        }],
      };
      myChart.setOption(option);
    },
  },
};
</script>
