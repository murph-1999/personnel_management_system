import request from '@/utils/request';

export function updatePerson(data) {
  return request({
    url: '/update',
    method: 'post',
    data,
  });
}
export function deletePerson(data) {
  return request({
    url: '/delete',
    method: 'post',
    data,
  });
}

export function addPerson() {
  return request({
    url: '/add',
    method: 'post',
  });
}

export const getPerson = (params) => request({
  url: '/show',
  method: 'get',
  params,
});
