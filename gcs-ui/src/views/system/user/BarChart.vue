<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
  import * as echarts from 'echarts';
  require('echarts/theme/macarons')// echarts theme
  import resize from '@/views/dashboard/mixins/resize'
  import {getStatistics} from "@/api/system/user"
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
        dept : [],
        num : [],
      }
    },
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

        getStatistics().then(response => {
          console.log(response);
          this.dept = JSON.parse(JSON.stringify(response.dept));
          this.num = JSON.parse(JSON.stringify(response.num));

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
              data: this.dept,
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
              name: '总人数',
              type: 'bar',
              stack: 'vistors',
              barWidth: '60%',
              data: this.num,
              // animationDuration
            }],
            legend: {
              orient: 'vertical',
              x:'left',      //可设定图例在左、右、居中
              y:'top',     //可设定图例在上、下、居中
              padding:[0,0,0,50],   //可设定图例[距上方距离，距右方距离，距下方距离，距左方距离]
              data: ['总人数']
            },
          });
        });
      }
    }
  }
</script>
