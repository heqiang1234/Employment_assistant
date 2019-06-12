<template>
  <div class="warp">
    <Header></Header>
    <div class="chartsBox">
      <div class="chartBox">
        <div id="myChart1" class="ty-form"></div>
        <div class="chart-title">点击量排行</div>
      </div>
      <div class="chartBox">
        <div id="myChart2" class="ty-form"></div>
        <div class="chart-title">城市人才需求</div>
      </div>
      <div class="chartBox">
        <div id="myChart3" class="ty-form"></div>
        <div class="chart-title">宣讲会日程</div>
      </div>
      <div class="chartBox">
        <div id="myChart4" class="ty-form"></div>
        <div class="chart-title">全国人才需求</div>
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
    this.echart();
    this.echartPie();
    this.echart1();
    this.echartPie1();
  },
  methods: {
    //点击量排行表
    echart() {
      var echarts = require("echarts");

      // 基于准备好的dom，初始化echarts实例
      var myChart = echarts.init(document.getElementById("myChart1"));
      // 不能在单个容器上初始化多个 ECharts 实例。
      //实例容器，一般是一个具有高宽的div元素。
      myChart.setOption({
        title: {
          text: "数据显示"
          //subtext: "二级标题"
        },
        tooltip: {},
        xAxis: {
          data: ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"],
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
              color: "#ddd"
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
            name: "销量",
            type: "bar", // line折线  bar矩形
            //					 smooth: true, //平滑折线
            //					showSymbol: false, // 去掉折线的点
            data: [520, 200, 306, 520, 800, 120],
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
      // 饼状图的相关配置与事件

      // 1. 默认选中了某一个
      // 2. 鼠标离开记录上次选中的作为当前选中的

      var echarts = require("echarts");

      // 基于准备好的dom，初始化echarts实例
      var myChart = echarts.init(document.getElementById("myChart2"));
      // 不能在单个容器上初始化多个 ECharts 实例。
      //实例容器，一般是一个具有高宽的div元素。

      var option = {
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
          orient: "vertical",
          x: "left",
          data: ["直接访问", "邮件营销", "联盟广告", "视频广告", "搜索引擎"]
        },
        series: [
          {
            name: "访问来源",
            type: "pie",
            radius: ["50%", "70%"],
            avoidLabelOverlap: false,
            label: {
              normal: {
                show: false,
                position: "center"
              },
              emphasis: {
                show: true,
                textStyle: {
                  fontSize: "16",
                  fontWeight: "bold"
                }
              }
            },
            labelLine: {
              normal: {
                show: false
              }
            },
            data: [
              {
                value: 335,
                name: "直接访问"
              },
              {
                value: 310,
                name: "邮件营销"
              },
              {
                value: 234,
                name: "联盟广告"
              },
              {
                value: 135,
                name: "视频广告"
              },
              {
                value: 1548,
                name: "搜索引擎"
              }
            ],
            itemStyle: {
              // 饼状图之间隔开一点的配置参数
              normal: {
                borderWidth: 4,
                borderColor: "#ffffff"
              }
            }
          }
        ]
      };
      myChart.setOption(option);
      init(0);

      function init(index) {
        myChart.dispatchAction({
          type: "highlight",
          seriesIndex: 0,
          dataIndex: index
        });
      }
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
          data: ["蒸发量", "平均温度"]
        },
        xAxis: [
          {
            type: "category",
            data: [
              "1月",
              "2月",
              "3月",
              "4月",
              "5月",
              "6月",
              "7月",
              "8月",
              "9月",
              "10月",
              "11月",
              "12月"
            ],
            axisPointer: {
              type: "shadow"
            }
          }
        ],
        yAxis: [
          {
            type: "value",
            name: "公司宣讲会",
            min: 0,
            max: 25,
            interval: 5,
            axisLabel: {
              formatter: "{value} 个"
            }
          },
          {
            type: "value",
            name: "温度",
            min: 0,
            max: 25,
            interval: 5,
            axisLabel: {
              formatter: "{value} 次"
            }
          }
        ],
        series: [
          {
            name: "蒸发量",
            type: "bar",
            data: [2.6, 5.9, 9.0, 2.4, 2.7, 7.7, 5.6, 12.2, 8.7, 8.8, 6.0, 2.3]
          },

          {
            name: "平均温度",
            type: "line",
            yAxisIndex: 1,
            data: [2.6, 5.9, 9.0, 2.4, 2.7, 7.7, 5.6, 12.2, 8.7, 8.8, 6.0, 2.3]
          }
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
</style>