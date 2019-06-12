<template>
  <div class="wrap">
    <Header></Header>
    <div class="personal-msg">
      <div class="per-pic">
        <el-upload
          class="avatar-uploader"
          :action="API.UPLOAD.UPIMG"
          name="UserImg"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
        >
          <img v-if="userInfo.userImg" :src="userInfo.userImg" class="avatar">
          <i v-else class="el-icon-camera-solid avatar-uploader-icon"></i>
        </el-upload>
        <div class="exchange-pwd">
          <div class="user-name">
            <span class="sty-title">当前账号：</span>
            <span class="sty-name">{{userInfo.user_Name}}</span>
          </div>
          <div @click="changePwd" class="re-pwd">
            <i class="el-icon-edit">修改密码</i>
          </div>
          <div v-show="onChangePwd" class="input-pwd">
            <div class="pwd-line">
              <div class="pwd-name">当前密码</div>
              <div>
                <el-input placeholder="当前密码" show-password></el-input>
              </div>
            </div>
            <div class="pwd-line">
              <div class="pwd-name">新密码</div>
              <div>
                <el-input placeholder="新密码" show-password></el-input>
              </div>
            </div>
            <div class="pwd-line">
              <div class="pwd-name">确认密码</div>
              <div>
                <el-input placeholder="确认密码" show-password></el-input>
              </div>
            </div>
            <div class="pwd-line">
              <div class="save-bnt">
                <el-button type="success" plain>确认修改</el-button>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="other-msg">
        <el-alert
          :closable="false"
          v-if="userInfoErr"
          show-icon
          :title="userInfoErrMsg"
          type="error"
        ></el-alert>
        <div class="show-title">用户信息</div>
        <div class="show-msg">
          <div class="msg-items">
            <div class="item-name">姓名</div>
            <div class="item-input">
              <el-input v-model="userInfo.user_RealName" placeholder="请输入内容"></el-input>
            </div>
          </div>
        </div>
        <div class="show-msg">
          <div class="msg-items">
            <div class="item-name">性别</div>
            <div class="item-input">
              <el-select v-model="userInfo.user_Sex" placeholder="选择性别">
                <el-option label="男" value="male"></el-option>
                <el-option label="女" value="female"></el-option>
              </el-select>
            </div>
          </div>
        </div>
        <div class="show-msg">
          <div class="msg-items">
            <div class="item-name">所在城市</div>
            <div class="item-input">
              <el-select
                v-model="userInfo.user_Province"
                @change="changeProvince"
                placeholder="请选择省"
              >
                <el-option
                  v-for="item in allCity"
                  :key="item.province"
                  :label="item.province"
                  :value="item.province"
                ></el-option>
              </el-select>
              <el-select v-model="userInfo.user_City" placeholder="请选择市">
                <el-option v-for="item in curCity" :key="item" :label="item" :value="item"></el-option>
              </el-select>
            </div>
          </div>
        </div>
        <div class="show-msg">
          <div class="msg-items">
            <div class="item-name">所在学校</div>
            <div class="item-input">
              <el-input v-model="userInfo.user_School" placeholder="请输入内容"></el-input>
            </div>
          </div>
        </div>
        <div class="show-msg">
          <div class="msg-items">
            <div class="item-name">所学专业</div>
            <div class="item-input">
              <el-input v-model="userInfo.user_Major" placeholder="请输入内容"></el-input>
            </div>
          </div>
        </div>
        <div class="show-msg">
          <div class="msg-items">
            <div class="item-name">求职意向</div>
            <div class="item-input">
              <el-input v-model="userInfo.user_IntentionalPost" placeholder="请输入内容"></el-input>
            </div>
          </div>
        </div>
        <div class="show-msg">
          <div class="msg-items">
            <div class="item-name">常用邮箱</div>
            <div class="item-input">
              <el-input v-model="userInfo.user_Email" placeholder="请输入内容"></el-input>
            </div>
          </div>
          <el-checkbox
            v-if="userInfo.user_Email"
            style="margin-left:120px;"
            v-model="userInfo.user_Status"
            :checked="userInfo.user_Status"
          >是否接受推送</el-checkbox>
        </div>

        <div class="save-bot">
          <el-button @click="updateUser" type="primary">确认修改</el-button>
        </div>
      </div>
    </div>
    <Footsy></Footsy>
  </div>
</template>
<script>
import Header from "./common/header.vue";
import Footsy from "./common/footsy.vue";
export default {
  created() {
    console.log(this.USERSTATUS.login);
    if (!this.USERSTATUS.login) {
      this.replaceTo({ name: "login" });
      this.$message({
        showClose: true,
        message: "请先登陆",
        type: "error"
      });
      return;
    }
    this.userInfo = this.USERSTATUS.userInfo;
  },
  components: { Header, Footsy },
  methods: {
    changePwd() {
      this.onChangePwd = !this.onChangePwd;
    },
    changeProvince(val) {
      let that = this;
      let province = that.allCity;
      for (let i = 0; i < province.length; i++) {
        if (province[i].province === val) {
          that.curCity = province[i].children;
          that.userInfo.user_City = province[i].children[0];
        }
      }
    },
   handleAvatarSuccess(res, file) {
      //上传头像更改
      console.log(res);
      console.log(file);
      this.userInfo.userImg = res.extendInfo.success;
    },
    beforeAvatarUpload(file){
      const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
    },
    showUserInfoErr(msg) {
      this.userInfoErrMsg = msg;
      this.userInfoErr = true;
      console.log(this.userInfoErr);
    },
    updateUser() {
      //上传个人信息
      let userInfo = this.userInfo;
      let that = this;
      var pattern = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
      console.log(userInfo);
      if (userInfo.avtUrl == "") {
        that.showUserInfoErr("请上传个人头像");
        return;
      }
      for (let item in userInfo) {
        if (userInfo[item] === "") {
          that.showUserInfoErr("请完整填写个人信息");
          return;
        }
      }
      if (!pattern.test(userInfo.user_Email)) {
        that.showUserInfoErr("请填写正确的邮箱地址");
        return;
      }
      console.log(
        userInfo.user_RealName +
          "\n" +
          userInfo.user_Sex +
          "\n" +
          userInfo.user_School +
          "\n" +
          userInfo.user_Province +
          "\n" +
          userInfo.user_Major +
          "\n" +
          userInfo.user_IntentionalPost +
          "\n" +
          userInfo.user_City +
          "\n" +
          userInfo.user_Email +
          "\n" +
          userInfo.userImg+
          "\n" +
          userInfo.user_Status
      );
      this.axios({
        url: this.API.USER.UPDATE,
        params: {
          UserRealName: userInfo.user_RealName,
          UserSex: userInfo.user_Sex,
          UserSchool: userInfo.user_School,
          UserMajor: userInfo.user_Major,
          UserIntentionalPost: userInfo.user_IntentionalPost,
          UserProvince: userInfo.user_Province,
          UserCity: userInfo.user_City,
          UserMail: userInfo.user_Email,
          UserImg: userInfo.userImg,
          UserStatus: userInfo.user_Status
        }
      }).then(res => {
        console.log(res);
        this.newUser = false;
        this.USERSTATUS.userInfo = res.data.extendInfo.person;
        console.log(this.USERSTATUS.userInfo);
        this.$message({
        showClose: true,
        message: "修改成功",
        type: "success"
      });
        this.$refs.header.syncUserStatus();
      });
    }
  },
  data() {
    return {
      onChangePwd: false,
      curCity: [],
      userInfoErrMsg: "", //用户资料报错提示信息,
      userInfoErr: false,
      userInfo: {
        userImg: "",
        user_City: "",
        user_Email: "",
        user_IntentionalPost: "",
        user_Major: "",
        user_Name: "",
        user_Province: "",
        user_RealName: "",
        user_School: "",
        user_Sex: "",
        user_Status: ""
      },
      allCity: [
        //全国省市区
        {
          province: "北京",
          children: [
            "东城区",
            "西城区",
            "崇文区",
            "宣武区",
            "朝阳区",
            "海淀区",
            "丰台区",
            "石景山区",
            "房山区",
            "通州区",
            "顺义区",
            "门头沟区",
            "昌平区",
            "大兴区",
            "怀柔区",
            "平谷区",
            "密云县",
            "延庆县"
          ]
        },
        {
          province: "上海",
          children: [
            "黄浦区",
            "卢湾区",
            "徐汇区",
            "长宁区",
            "静安区",
            "普陀区",
            "闸北区",
            "虹口区",
            "杨浦区",
            "宝山区",
            "闵行区",
            "嘉定区",
            "浦东新区",
            "金山区",
            "松江区",
            "青浦区",
            "南汇区",
            "奉贤区",
            "崇明县"
          ]
        },
        {
          province: "天津",
          children: [
            "和平区",
            "河东区",
            "河西区",
            "南开区",
            "河北区",
            "红桥区",
            "塘沽区",
            "汉沽区",
            "大港区",
            "东丽区",
            "西青区",
            "津南区",
            "北辰区",
            "武清区",
            "宝坻区",
            "宁河县",
            "静海县",
            "蓟县"
          ]
        },
        {
          province: "重庆",
          children: [
            "渝中区",
            "大渡口区",
            "江北区",
            "沙坪坝区",
            "九龙坡区",
            "南岸区",
            "北碚区",
            "万盛区",
            "双桥区",
            "渝北区",
            "巴南区",
            "万县区",
            "涪陵区",
            "永川",
            "合川",
            "江津",
            "南川",
            "长寿县",
            "綦江县",
            "潼南县",
            "荣昌县",
            "壁山县",
            "大足县",
            "铜梁县",
            "梁平县",
            "城口县",
            "垫江县",
            "武隆县",
            "丰都县",
            "忠 县",
            "开 县",
            "云阳县",
            "青龙镇青龙嘴",
            "奉节县",
            "巫山县",
            "巫溪县",
            "南宾镇",
            "中和镇",
            "钟多镇",
            "联合镇",
            "汉葭镇"
          ]
        },
        {
          province: "河北",
          children: [
            "石家庄",
            "唐山",
            "秦皇岛",
            "邯郸",
            "邢台",
            "保定",
            "张家口",
            "承德",
            "沧州",
            "廊坊",
            "衡水"
          ]
        },
        {
          province: "山西",
          children: [
            "太原",
            "大同",
            "阳泉",
            "长治",
            "晋城",
            "朔州",
            "晋中",
            "运城",
            "忻州",
            "临汾",
            "吕梁"
          ]
        },
        {
          province: "辽宁",
          children: [
            "沈阳",
            "大连",
            "鞍山",
            "抚顺",
            "本溪",
            "丹东",
            "锦州",
            "营口",
            "阜新",
            "辽阳",
            "盘锦",
            "铁岭",
            "朝阳",
            "葫芦岛"
          ]
        },
        {
          province: "吉林",
          children: [
            "长春",
            "吉林",
            "四平",
            "辽源",
            "通化",
            "白山",
            "松原",
            "白城",
            "延边朝鲜族自治州"
          ]
        },
        {
          province: "河南",
          children: [
            "郑州",
            "开封",
            "洛阳",
            "平顶山",
            "安阳",
            "鹤壁",
            "新乡",
            "焦作",
            "濮阳",
            "许昌",
            "漯河",
            "三门峡",
            "南阳",
            "商丘",
            "信阳",
            "周口",
            "驻马店",
            "济源"
          ]
        },
        {
          province: "江苏",
          children: [
            "南京",
            "无锡",
            "徐州",
            "常州",
            "苏州",
            "南通",
            "连云港",
            "淮安",
            "盐城",
            "扬州",
            "镇江",
            "泰州",
            "宿迁"
          ]
        },
        {
          province: "浙江",
          children: [
            "杭州",
            "宁波",
            "温州",
            "嘉兴",
            "湖州",
            "绍兴",
            "金华",
            "衢州",
            "舟山",
            "台州",
            "丽水"
          ]
        },
        {
          province: "安徽",
          children: [
            "合肥",
            "芜湖",
            "蚌埠",
            "淮南",
            "马鞍山",
            "淮北",
            "铜陵",
            "安庆",
            "黄山",
            "滁州",
            "阜阳",
            "宿州",
            "巢湖",
            "六安",
            "亳州",
            "池州",
            "宣城"
          ]
        },
        {
          province: "福建",
          children: [
            "福州",
            "厦门",
            "莆田",
            "三明",
            "泉州",
            "漳州",
            "南平",
            "龙岩",
            "宁德"
          ]
        },
        {
          province: "江西",
          children: [
            "南昌",
            "景德镇",
            "萍乡",
            "九江",
            "新余",
            "鹰潭",
            "赣州",
            "吉安",
            "宜春",
            "抚州",
            "上饶"
          ]
        },
        {
          province: "山东",
          children: [
            "济南",
            "青岛",
            "淄博",
            "枣庄",
            "东营",
            "烟台",
            "潍坊",
            "威海",
            "济宁",
            "泰安",
            "日照",
            "莱芜",
            "临沂",
            "德州",
            "聊城",
            "滨州",
            "菏泽"
          ]
        },
        {
          province: "湖北",
          children: [
            "武汉",
            "黄石",
            "襄樊",
            "十堰",
            "荆州",
            "宜昌",
            "荆门",
            "鄂州",
            "孝感",
            "黄冈",
            "咸宁",
            "随州",
            "恩施州",
            "仙桃",
            "潜江",
            "天门",
            "神农架林区"
          ]
        },
        {
          province: "湖南",
          children: [
            "长沙",
            "株洲",
            "湘潭",
            "衡阳",
            "邵阳",
            "岳阳",
            "常德",
            "张家界",
            "益阳",
            "郴州",
            "永州",
            "怀化",
            "娄底",
            "湘西州"
          ]
        },
        {
          province: "广东",
          children: [
            "广州",
            "深圳",
            "珠海",
            "汕头",
            "韶关",
            "佛山",
            "江门",
            "湛江",
            "茂名",
            "肇庆",
            "惠州",
            "梅州",
            "汕尾",
            "河源",
            "阳江",
            "清远",
            "东莞",
            "中山",
            "潮州",
            "揭阳",
            "云浮"
          ]
        },
        {
          province: "海南",
          children: ["海口", "龙华区", "秀英区", "琼山区", "美兰区", "三亚"]
        },
        {
          province: "四川",
          children: [
            "成都",
            "自贡",
            "攀枝花",
            "泸州",
            "德阳",
            "绵阳",
            "广元",
            "遂宁",
            "内江",
            "乐山",
            "南充",
            "宜宾",
            "广安",
            "达州",
            "眉山",
            "雅安",
            "巴中",
            "资阳",
            "阿坝州",
            "甘孜州",
            "凉山州"
          ]
        },
        {
          province: "贵州",
          children: [
            "贵阳",
            "六盘水",
            "遵义",
            "安顺",
            "铜仁地区",
            "毕节地区",
            "黔西南州",
            "黔东南州",
            "黔南州"
          ]
        },
        {
          province: "云南",
          children: [
            "昆明",
            "大理",
            "曲靖",
            "玉溪",
            "昭通",
            "楚雄",
            "红河",
            "文山",
            "思茅",
            "西双版纳",
            "保山",
            "德宏",
            "丽江",
            "怒江",
            "迪庆",
            "临沧"
          ]
        },
        {
          province: "陕西",
          children: [
            "西安",
            "铜川",
            "宝鸡",
            "咸阳",
            "渭南",
            "延安",
            "汉中",
            "榆林",
            "安康",
            "商洛"
          ]
        },
        {
          province: "甘肃",
          children: [
            "兰州",
            "嘉峪关",
            "金昌",
            "白银",
            "天水",
            "武威",
            "张掖",
            "平凉",
            "酒泉",
            "庆阳",
            "定西",
            "陇南",
            "临夏州",
            "甘南州"
          ]
        },
        {
          province: "青海",
          children: [
            "西宁",
            "海东地区",
            "海北州",
            "黄南州",
            "海南州",
            "果洛州",
            "玉树州",
            "海西州"
          ]
        },
        {
          province: "黑龙江",
          children: [
            "哈尔滨",
            "齐齐哈尔",
            "鸡西",
            "鹤岗",
            "双鸭山",
            "大庆",
            "伊春",
            "佳木斯",
            "七台河",
            "牡丹江",
            "黑河",
            "绥化",
            "大兴安岭地区"
          ]
        },
        {
          province: "内蒙古自治区",
          children: [
            "呼和浩特",
            "包头",
            "乌海",
            "赤峰",
            "通辽",
            "鄂尔多斯",
            "呼伦贝尔",
            "巴彦淖尔",
            "乌兰察布",
            "兴安盟",
            "锡林郭勒盟",
            "阿拉善盟"
          ]
        },
        {
          province: "广西",
          children: [
            "南宁",
            "柳州",
            "桂林",
            "梧州",
            "北海",
            "防城港",
            "钦州",
            "贵港",
            "玉林",
            "百色",
            "贺州",
            "河池",
            "来宾",
            "崇左"
          ]
        },
        {
          province: "西藏自治区",
          children: [
            "拉萨",
            "昌都地区",
            "山南地区",
            "日喀则地区",
            "那曲地区",
            "阿里地区",
            "林芝地区"
          ]
        },
        {
          province: "宁夏自治区",
          children: ["银川", "石嘴山", "吴忠", "固原", "中卫"]
        },
        {
          province: "新疆维吾尔自治区",
          children: [
            "乌鲁木齐",
            "克拉玛依",
            "吐鲁番地区",
            "哈密地区",
            "和田地区",
            "阿克苏地区",
            "喀什地区",
            "克孜勒苏柯尔克孜自治州",
            "巴音郭楞蒙古自治州",
            "昌吉回族自治州",
            "博尔塔拉蒙古自治州",
            "伊犁哈萨克自治州",
            "塔城地区",
            "阿勒泰地区",
            "石河子",
            "阿拉尔",
            "图木舒克",
            "五家渠"
          ]
        },
        {
          province: "台湾",
          children: [
            "台北",
            "高雄",
            "基隆",
            "台中",
            "台南",
            "新竹",
            "嘉义",
            "台北县",
            "宜兰县",
            "桃园县",
            "新竹县",
            "苗栗县",
            "台中县",
            "彰化县",
            "南投县",
            "云林县",
            "嘉义县",
            "台南县",
            "高雄县",
            "屏东县",
            "澎湖县",
            "台东县",
            "花莲县"
          ]
        },
        {
          province: "香港",
          childrem: [
            "中西区",
            "东区",
            "九龙城区",
            "观塘区",
            "南区",
            "深水埗区",
            "黄大仙区",
            "湾仔区",
            "油尖旺区",
            "离岛区",
            "葵青区",
            "北区",
            "西贡区",
            "沙田区",
            "屯门区",
            "大埔区",
            "荃湾区",
            "元朗区"
          ]
        },
        {
          province: "澳门地区",
          children: ["澳门"]
        },
        {
          province: "其它地区",
          children: ["其他地区"]
        }
      ],
      curCity: ["请选择"] //当前选中的省的市级列表
    };
  }
};
</script>
<style scoped>
.wrap {
  width: 100%;
  min-width: 1080px;
  overflow: hidden;
}
.personal-msg {
  width: 90%;
  min-width: 1080px;
  margin: 10px auto;
  padding: 5px 20px;
  display: flex;
  position: relative;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}
.per-pic {
  width: 27%;
  min-width: 290px;
  padding: 30px 0px;
  border-right: 1px solid #ddd;
  box-sizing: border-box;
}
.avatar-uploader {
  border-radius: 6px;
  width: 200px;
  height: 200px;
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  margin: 0 auto;
}
.avatar-uploader:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 200px;
  height: 200px;
  line-height: 200px;
  text-align: center;
}
.avatar {
  width: 200px;
  height: 200px;
  display: block;
}
.exchange-pwd {
  margin-top: 30px;
  padding: 0px 10px;
  box-sizing: border-box;
  width: 100%;
}
.user-name {
  padding-left: 40px;
  width: 100%;
  font-family: "微软雅黑";
  text-align: left;
}
.sty-title {
  font-weight: 500;
}
.sty-name {
  font-size: 24px;
  font-weight: 500;
}
.re-pwd {
  padding-left: 40px;
  line-height: 50px;
  color: #666;
  cursor: pointer;
}
.input-pwd {
  margin: 30px auto 0px;
  width: 100%;
  padding: 10px 10px 10px 0px;
  box-sizing: border-box;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}
.pwd-line {
  display: flex;
  line-height: 50px;
}
.pwd-name {
  width: 100px;
  font-size: 16px;
  text-align: right;
  padding-right: 10px;
  line-height: 50px;
}
.save-bnt {
  margin: 10px auto;
}
.other-msg {
  width: 66%;
  min-width: 720px;
  padding: 10px 40px;
  box-sizing: border-box;
}
.show-title {
  width: 100%;
  padding-left: 230px;
  font-family: "微软雅黑";
  font-size: 24px;
  font-weight: 500px;
  line-height: 50px;
}
.show-msg {
  width: 100%;
  margin-top: 20px;
}
.msg-items {
  width: 100%;
  margin: 10px 0;
  height: 40px;
  line-height: 40px;
  display: flex;
}
.item-name {
  padding-right: 10px;
  text-align: right;
  width: 100px;
}
.item-input .el-input {
  width: 500px;
}
.item-input .el-select .el-input {
  width: 190px;
}
.save-bot {
  width: 100%;
  height: 40px;
  padding: 20px 230px;
}
.close-page {
  width: 50px;
  height: 20px;
  line-height: 20px;
  position: absolute;
  right: 15px;
  top: 10px;
  text-align: center;
  color: red;
}
</style>
