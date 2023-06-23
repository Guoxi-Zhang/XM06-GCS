<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import * as echarts from 'echarts';
require('echarts/theme/macarons')// echarts theme
import resize from '@/views/dashboard/mixins/resize'
import {getStatistics} from "@/api/verify/verify_history"
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
      default: '130%'
    },
    height: {
      type: String,
      default: '300px'
    },

  },
  data() {
    return {
      chart: null,

      dicts: {
        0: '绿色通道',
        1: '爱心大礼包',
        2: '补助申请'
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
      this.type = [];
      this.totalNum = [];
      this.passedNum = [];
      getStatistics().then(response => {
        console.log(response);
        this.type = response.type.map(value => this.dicts[value]);
        this.totalNum = JSON.parse(JSON.stringify(response.totalNum));
        this.passedNum = JSON.parse(JSON.stringify(response.passedNum));
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
            data: this.type,
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
            name: '该类型总数',
            type: 'bar',
            stack: 'vistors',
            barWidth: '60%',
            data: this.totalNum,
            // animationDuration
          },
          {
            name: '通过数',
            type: 'bar',
            stack: 'vistors',
            barWidth: '60%',
            data: this.passedNum,
            // animationDuration
          }
          ],
          legend: {
            orient: 'vertical',
            x:'left',      //可设定图例在左、右、居中
            y:'top',     //可设定图例在上、下、居中
            padding:[0,0,0,50],   //可设定图例[距上方距离，距右方距离，距下方距离，距左方距离]
            data: ['该类型总数','通过数']
          },
        });

      });

    }
  }
}
</script>
