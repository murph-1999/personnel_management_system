<template>
<el-container style="height: 600px; border: 1px solid #eee">
     <el-header style="height: 80px;text-align:right; font-size: 15px">
       <h2 style="float:left; font-size: 30px;line-height:40px;color:rgb(238, 241, 246)">
         <i class="el-icon-user"></i>
          Personnel</h2>
      <el-dropdown>
        <i class="el-icon-setting" style="margin-right: 15px"></i>
        <el-dropdown-menu slot="dropdown">
          <div id="logout" @click="goTo('/')"><el-dropdown-item>退出</el-dropdown-item></div>
          <el-dropdown-item>设置</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <span >Hello！Jack</span>
    </el-header>
<el-container>
  <el-aside width="220px" style="background-color: rgb(238, 241, 246);
  text-align:left; font-size:15px">
    <el-menu
     :default-active="$route.path"
        class="el-menu-vertical-demo"
       >
      <el-menu-item index="1">
        <template slot="title">
        <div style="font-size:15px;font-weight:bold;" @click="goTo('/showall')">
        <i class="el-icon-s-order"></i>show all
      </div>
          </template>
      </el-menu-item>
      <el-menu-item index="2" >
        <template slot="title">
          <div style="font-size:15px;font-weight:bold" @click="goTo('/agechart')">
           <i class="el-icon-picture-outline-round"></i>age chart</div>
          </template>
      </el-menu-item>
      <!-- <el-menu-item index="3">
        <template slot="title">
          <div style="font-size:15px;font-weight:bold"  @click="goTo('/add')">
          <i class="el-icon-plus"></i>add personnel</div>
          </template>
      </el-menu-item>
      <el-menu-item index="4">
        <template slot="title">
          <div style="font-size:15px;font-weight:bold"  @click="goTo('/find')">
          <i class="el-icon-search"></i>find personnel</div></template>
      </el-menu-item> -->
    </el-menu>
  </el-aside>

    <el-main>
      <router-view></router-view>
    </el-main>
  </el-container>
</el-container>

</template>

<style>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }

  .el-aside {
    color: #333;
  }
</style>

<script>
export default {
  data() {
    return { };
  },
  mounted() {
    // this.$router.replace('/showall');
  },

  methods: {
    goTo(path) {
      if (this.$route.path !== path) {
        this.$router.push({ path });
      }
    },
  },
};
</script>
