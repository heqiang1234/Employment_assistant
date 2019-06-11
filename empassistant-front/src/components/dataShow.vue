<template>
  <div class="warp">
    <Header></Header>
    <div id="myChart"></div>
    <Footsy></Footsy>
  </div>
</template>
<script>
import Header from "./common/header.vue";
import Footsy from "./common/footsy.vue";

export default {
  components: { Header, Footsy },
  name: 'hello',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  mounted(){
    this.drawLine();
  },
  methods: {
    drawLine(){
        // 基于准备好的dom，初始化echarts实例
        let myChart = this.$echarts.init(document.getElementById('myChart'))
        // 绘制图表
        myChart.setOption({
            title: { text: '在Vue中使用echarts' },
            tooltip: {},
            xAxis: {
                data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
            },
            yAxis: {},
            series: [{
                name: '销量',
                type: 'bar',
                data: [5, 20, 36, 10, 10, 20]
            }]
        });
    }
  }
};
</script>

<style scoped>
.wrap {
  width: 100%;
  min-width: 1080px;
  overflow: hidden;
}
#myChart {
  width: 50%;
  height: 300px;
  margin: 50px auto;
  background-color: aquamarine;
}
</style>
