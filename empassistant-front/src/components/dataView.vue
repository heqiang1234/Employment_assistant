<template>
  <div class="warp">
    <Header></Header>
    <div class="chartsBox">
      <div class="chartBox">
        <div id="myChart1" class="ty-form"></div>
        <div class="chart-title"></div>
      </div>
      <div class="chartBox">
        <div id="myChart2" class="ty-form"></div>
        <div class="chart-title"></div>
      </div>
      <div class="chartBox chart3">
        <div id="myChart3" class="ty-form"></div>
        <div class="chart-title"></div>
      </div>
    </div>
    <Footsy></Footsy>
  </div>
</template>
<script>
import Header from "./common/header.vue";
import Footsy from "./common/footsy.vue";
var echarts = require("echarts");
// 引入柱状图
require("echarts/lib/chart/bar");
// 引入提示框和标题组件
require("echarts/lib/component/tooltip");
require("echarts/lib/component/title");
export default {
  components: { Header, Footsy },
  data() {
    return {};
  },
  mounted() {
    // 绘制图表
    this.getChartsDatas(this.API.ECHARTS.GETEMPCHARTS)//宣讲会可视化数据
      .then(res=>{
        console.log(res);
        this.empDatas = res.data.extendInfo.result_list;
        this.echart();
      })
      this.getChartsDatas(this.API.ECHARTS.GETPOSCHARTS)//岗位可视化数据
      .then((res)=>{
        console.log(res)
        this.posDatas = res.data.extendInfo.result_list;
        this.echartPie();
      })
      this.getChartsDatas(this.API.ECHARTS.GETMEETCHARTS)
        .then(res=>{
          console.log(res);
          this.meetCharts = res.data.extendInfo.result_list;
          this.echart1();
        })

    
    this.echartPie1();
  },
  methods: {
    getChartsDatas(url){//获取宣讲会可视化数据
      return this.axios({
        url:url,
      })
    },
    //点击量排行表
    echart() {
      var echarts = require("echarts");

      // 基于准备好的dom，初始化echarts实例
      var myChart = echarts.init(document.getElementById("myChart1"));
      // 不能在单个容器上初始化多个 ECharts 实例。
      //实例容器，一般是一个具有高宽的div元素。
      myChart.setOption({
        title: {
          text: "宣讲会数量分布"
          //subtext: "二级标题"
        },
        tooltip: {},
        xAxis: {
          data: this.empDatas.map((item,index)=>{
            return item.value_name;
          }),
          axisLine: {
            // 设置刻度线相关的
            lineStyle: {
              color: "#0087ED",
              width: 1 //这里是为了突出显示加上的
            }
          },
          axisLabel: {
            // 设置字体相关的
            show: true,
            textStyle: {
              color: "#aaa"
            }
          }
        },
        yAxis: {
          min: 0,
          max: 1000, // y 轴最大的 值
          splitNumber: 10, // y轴刻度之间的距离 将y轴均分10
          axisLine: {
            // y轴线不显示   如果想要x轴线不显示 就把这个属性设置在xAxis里面
            show: false
          },
          axisTick: {
            //y轴刻度线  false就是去掉刻度线
            show: false
          },
          splitLine: {
            // 显示或者不显示横线
            show: true,
            lineStyle: {
              // 设置网格线的颜色跟类型
              // 使用深浅的间隔色
              color: ["#ddd"],
              type: "dotted"
            }
          }
        },
        series: [
          {
            name: "学校",
            type: "bar", // line折线  bar矩形
            //					 smooth: true, //平滑折线
            //					showSymbol: false, // 去掉折线的点
            data: this.empDatas.map((item,index)=>{
              return item.vaule;
            }),
            itemStyle: {
              // 设置bar或者线的颜色
              normal: {
                color: "#ddd"
              }
            }
          }
        ]
      });
    },
    echartPie() {
      var echarts = require("echarts");
      var myChart = echarts.init(document.getElementById("myChart2"));
      // 不能在单个容器上初始化多个 ECharts 实例。
      //实例容器，一般是一个具有高宽的div元素。
      var option = {
        title: {
          text: "岗位城市分布情况",
          subtext: "",
          x: "center"
        },
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
          orient: "vertical",
          left: "left",
          data: this.posDatas.map((item,index)=>{
            return item.value_name;
          })
        },
        series: [
          {
            name: "城市",
            type: "pie",
            radius: "55%",
            center: ["50%", "60%"],
            data:this.posDatas.map((item,index)=>{
              return {
                value:item.vaule,
                name:item.value_name
              }
            }),
            itemStyle: {
              emphasis: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)"
              }
            }
          }
        ]
      };
      myChart.setOption(option);
       function init(index) {
        myChart.dispatchAction({
          type: "highlight",
          seriesIndex: 0,
          dataIndex: index
        });
      }
      init(0);

     
      //记录上次高亮的索引
      var lastMouseOverIndex = null;
      // mouseover事件，记录当前数据索引并取消其他高亮，over在out之后
      myChart.on("mouseover", function(params) {
        var dataLen = option.series[0].data.length;
        lastMouseOverIndex = params.dataIndex;
        for (var i = 0; i < dataLen; i++) {
          if (i != params.dataIndex) {
            myChart.dispatchAction({
              type: "downplay",
              seriesIndex: 0,
              dataIndex: i
            });
          }
        }
      });
      // mouseout事件，将上次的高亮
      myChart.on("mouseout", function(params) {
        myChart.dispatchAction({
          type: "highlight",
          seriesIndex: 0,
          dataIndex: lastMouseOverIndex
        });
      });
    },
    //宣讲会日程表
    echart1() {
      var echarts = require("echarts");

      // 基于准备好的dom，初始化echarts实例
      var myChart = echarts.init(document.getElementById("myChart3"));
      // 不能在单个容器上初始化多个 ECharts 实例。
      //实例容器，一般是一个具有高宽的div元素。
      var option = {
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "cross",
            crossStyle: {
              color: "#999"
            }
          }
        },
        toolbox: {
          feature: {
            dataView: { show: true, readOnly: false },
            magicType: { show: true, type: ["line", "bar"] },
            restore: { show: true },
            saveAsImage: { show: true }
          }
        },
        legend: {
         
        },
        xAxis: [
          {
            type: "category",
            data: this.meetCharts.map(item=>{
              return item.value_name;
            }),
            axisPointer: {
              type: "shadow"
            }
          }
        ],
        yAxis: [
          {
            type: "value",
            name: "公司宣讲会日期分布",
            min: 0,
            max: 40,
            interval: 5,
            axisLabel: {
              formatter: "{value} 个"
            }
          },
        ],
        series: [
          {
            name: "公司宣讲会数量",
            type: "bar",
            data: this.meetCharts.map(item=>{
              // console.log(item.vaule)
              return item.vaule;
            })
          },
        ]
      };
      myChart.setOption(option);
      init(0);
    },
    //全国人才需求状况
    echartPie1() {
      var echarts = require("echarts");
      var myChart = echarts.init(document.getElementById("myChart4"));
      // 不能在单个容器上初始化多个 ECharts 实例。
      //实例容器，一般是一个具有高宽的div元素。
    }
  },
  data(){
    return{
      empDatas:[],
      posDatas:[],
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
.chartsBox {
  margin: 20px auto;
  padding: 0px 20px;
  width: 90%;
  min-width: 1080px;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}
.chartBox {
  width: 48%;
  margin-top: 20px;
  padding: 10px;
  height: 500px;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}
.ty-form {
  width: 100%;
  height: 400px;
}
.chart-title {
  font-family: "Courier New", Courier, monospace;
  font-size: 30px;
  color: chocolate;
  text-align: center;
  line-height: 100px;
  width: 100%;
}
#myChart3{
  width:96%;
}
.chart3{
  width:100%;
}
</style>