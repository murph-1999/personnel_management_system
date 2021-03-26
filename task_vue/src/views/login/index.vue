<template>
  <div class="login-container" >
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form"
     autocomplete="on" label-position="left">

      <div class="title-container">
        <h3 class="title">Personnel Management</h3>
      </div>

      <el-form-item prop="username">
        <span style="font-size:20px" class="svg-container">
        <i  class="el-icon-user-solid"></i>
        </span>
        <el-input
          ref="username"
          v-model="loginForm.username"
          placeholder="Username"
          name="username"
          type="text"
          tabindex="1"
          clearable
          autocomplete="on"
        />
      </el-form-item>

      <el-form-item prop="password">
        <span style="font-size:20px" class="svg-container">
            <i class="el-icon-lock"></i>
        </span>
        <el-input
          :key="passwordType"
          ref="password"
          v-model="loginForm.password"
          :type="passwordType"
          placeholder="password"
          name="password"
          tabindex="2"
          auto-complete="on"
          clearable
          @keyup.enter.native="handleLogin"
        />
      </el-form-item>
      <el-button :loading="loading" type="primary" class="login-btn"
       @click.native.prevent="handleLogin">Login</el-button>
    </el-form>
  </div>
</template>

<script>
import { login } from '@/api/user';

export default {
  name: 'Login',
  data() {
    const validatePassword = (rule, value, callback) => {
      if (value.length < 4) {
        callback(new Error('The password can not be less than 4 digits'));
      } else {
        callback();
      }
    };
    return {
      loginForm: {
        username: 'Jack',
        password: '1234',
      },
      loginRules: {
        username: [{ required: true, trigger: 'blur' }],
        password: [{ required: true, trigger: 'blur', validator: validatePassword }],
      },
      passwordType: 'password',
      loading: false,
      redirect: undefined,
    };
  },
  watch: {
    $route: {
      handler(route) {
        const { query } = route;
        if (query) {
          this.redirect = query.redirect;
          // this.otherQuery = this.getOtherQuery(query);
        }
      },
      immediate: true,
    },
  },
  mounted() {
    if (this.loginForm.username === '') {
      this.$refs.username.focus();
    } else if (this.loginForm.password === '') {
      this.$refs.password.focus();
    }
  },
  methods: {
    checkCapslock(e) {
      const { key } = e;
      this.capsTooltip = key && key.length === 1 && (key >= 'A' && key <= 'Z');
    },
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = '';
      } else {
        this.passwordType = 'password';
      }
      this.$nextTick(() => {
        this.$refs.password.focus();
      });
    },

    handleLogin() {
      // eslint-disable-next-line consistent-return
      this.$refs.loginForm.validate(async (valid) => {
        if (valid) {
          this.loading = true;
          // eslint-disable-next-line no-unused-vars
          let res = [];
          try {
            res = await login(this.loginForm);
            this.$router.push({ path: this.redirect || '/home', query: this.otherQuery });
            this.loading = false;
          } finally {
            this.loading = false;
          }
        }
      });
    },

    getOtherQuery(query) {
      return Object.keys(query).reduce((acc, cur) => {
        if (cur !== 'redirect') {
          acc[cur] = query[cur];
        }
        return acc;
      }, {});
    },

  },
};
</script>

<style lang="scss">

$light_gray:#fff;
$cursor: #fff;
$bg:#d1e3eb;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

.login-container {
  height: 100%;
  width: 100%;
  background-image: url('D:\\GitHub\\task\\task_vue\\src\\assets\\bk.jpeg');
  background-repeat: no-repeat;
  background-size:100% 100%;
  overflow: auto;

  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 2px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>

$bg:#577f92;
$dark_gray:#889aa4;
$light_gray:#eee;
$light_blue: #66b1ff;

.login-container {

  .login-form {
    position: relative;
    width: 400px;
    max-width: 100%;
    padding: 35px;
    margin: 160px auto;
    overflow: hidden;
    background: rgba(57, 75, 85, 0.8);
    border-radius: 5px;
  }

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }

  .login-btn {
    background: #2c3a65;
    text-align: center;
    border: 0;
    width:100%;
    margin-bottom:30px;
    &.is-loading {
      border: 0;
    }
  }

  .thirdparty-button {
    position: absolute;
    right: 0;
    bottom: 6px;
  }

  @media only screen and (max-width: 470px) {
    .thirdparty-button {
      display: none;
    }
  }
}
</style>
