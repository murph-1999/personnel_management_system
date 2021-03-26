/* eslint-disable no-param-reassign */
import axios from 'axios';
import { Message } from 'element-ui';
import qs from 'qs';

// create an axios instance
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 5000, // request timeout
});

// eslint-disable-next-line no-console
console.log(service.baseURL);
// request interceptor
service.interceptors.request.use(
  (config) =>
    // do something before request is sent
    // eslint-disable-next-line implicit-arrow-linebreak
    config,
  // (error) =>
  //   // do something with request error
  //   Promise.reject(error),

);

// response interceptor
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
   */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  (response) => {
    const res = response.data;

    // if the custom code is not 20000, it is judged as an error.
    if (res.code !== 200) {
      Message({
        message: res.msg || 'Error',
        type: 'error',
        duration: 5 * 1000,
      });

      return Promise.reject(new Error(res.message || 'Error'));
    }
    return res;
  },
  (error) => {
    // eslint-disable-next-line no-console
    console.log(`err${error}`); // for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000,
    });
    return Promise.reject(error);
  },
);

export default function (
  option,
  {
    // eslint-disable-next-line no-unused-vars
    loading = false, // 请求 loading，默认不开启
    dataType = 'formData', // 请求的数据格式，默认 formData , 可选 formData2, json
    mock = false, // 是否为 mock 模式
  } = {},
) {
  let defaultHeaders = {};

  if (dataType === 'formData') {
    defaultHeaders = {
      'Content-Type': 'application/x-www-form-urlencoded', // 发送 formData 数据格式
    };

    // eslint-disable-next-line no-param-reassign
    option.data = qs.stringify(option.data);
  } else if (dataType === 'formData2') {
    defaultHeaders = {
      'Content-Type': 'multipart/form-data', // 含文件
    };
  }

  option.headers = Object.assign(defaultHeaders, option.headers || {});

  if (mock && process.env.VUE_APP_MOCK_API) {
    option.url = `${process.env.VUE_APP_MOCK_API}${option.url}`;
  }

  return new Promise((resolve, reject) => {
    service(option)
      .then(resolve)
      .catch(reject);
  });
}
