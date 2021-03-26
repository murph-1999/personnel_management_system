import request from '@/utils/request';

// eslint-disable-next-line import/prefer-default-export
export function login(data) {
  return request({
    url: '/login',
    method: 'post',
    data,
  });
}
