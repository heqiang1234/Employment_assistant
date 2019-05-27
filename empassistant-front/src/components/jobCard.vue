<template>
  <div v-for="(item) in jobList" :key="item.career_talk_id" class="post">
    <!-- 职位盒子 -->
    <div class="post-title">
      <div class="post-head">
        <div class="post-name">{{item.position_name}}</div>
        <div class="post-pay">{{item.positionWage}}</div>
      </div>
      <div class="post-body">
        <div class="post-others">
          <i class="el-icon-location-outline"></i>
          <span class="post-other">{{item.workPlace}}</span>
        </div>
        <div class="post-others">
          <i class="el-icon-time"></i>
          <span class="post-other">4天/周</span>
        </div>
        <div class="post-others">
          <i class="el-icon-date"></i>
          <span class="post-other">四个月</span>
        </div>
      </div>
    </div>
    <div class="post-sepLine"></div>
    <div class="post-company">
      <div class="company-logo">
        <img :src="item.company_logo" alt>
      </div>
      <div class="company-infor">
        <div class="company-name">
          <a href="#">{{item.companyType}}</a>
        </div>
        <div class="company-info">{{item.companyType}} || {{item.company_size}}</div>
      </div>
    </div>
  </div>
</template>
