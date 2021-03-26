import Vue from 'vue';
import ElementUI from 'element-ui';
import locale from 'element-ui/lib/locale/lang/en';
import echarts from 'echarts';
import App from './App.vue';
import router from './router';
import store from './store';
import 'default-passive-events';
import '@/styles/index.scss'; // global css
import 'element-ui/lib/theme-chalk/index.css';
// eslint-disable-next-line import/order
import Layer from '@/utils/layer';
// 通用弹出层
Vue.prototype.$echarts = echarts;
Vue.prototype.$layer = Layer;
Vue.use(ElementUI, { locale });

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount('#app');
