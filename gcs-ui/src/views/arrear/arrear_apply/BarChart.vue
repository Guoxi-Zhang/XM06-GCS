<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import * as echarts from 'echarts';
require('echarts/theme/macarons')// echarts theme
import resize from '@/views/dashboard/mixins/resize'
import {getStatistics} from "@/api/arrear/arrear_apply"
const animationDuration = 6000

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '200px'
    },

  },
  data() {
    return {
      chart: null,
      dept : [],
      num : [],
      dicts: {
        0: '家庭困难，已申请生源地助学贷款',
        1: '家庭困难，准备申请国家助学贷款'
      }
    }
  },
  // created(){
  //   this.$nextTick(() => {
  //     this.initChart()
  //   })
  // },
  mounted() {
    this.$nextTick(() => {
      this.initChart()
    })
  },

  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },

  methods: {
    initChart() {
      this.chart = echarts.init(this.$el, 'macarons');
      this.reason = [];
      this.number = [];
      getStatistics().then(response => {
        console.log(response);
        this.reason = response.reason.map(value => this.dicts[value]);;
        this.number = JSON.parse(JSON.stringify(response.num));
        this.chart.setOption({
          tooltip: {
            trigger: 'axis',
            axisPointer: { // 坐标轴指示器，坐标轴触发有效
              type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
            }
          },
          grid: {
            top: 10,
            left: '2%',
            right: '2%',
            bottom: '3%',
            containLabel: true
          },
          xAxis: [{
            type: 'category',
            data: this.reason,
            axisTick: {
              alignWithLabel: true
            }
          }],
          yAxis: [{
            type: 'value',
            axisTick: {
              show: false
            }
          }],
          series: [{
            name: '人数',
            type: 'bar',
            stack: 'vistors',
            barWidth: '60%',
            data: this.number,
            animationDuration: animationDuration// animationDuration
          }],
        })
        console.log(this.reason);
        console.log(this.number);
      });

    }
  }
}
</script>
