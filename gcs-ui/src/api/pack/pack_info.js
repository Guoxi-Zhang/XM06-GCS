import request from '@/utils/request'

// 查询礼包信息列表
export function listPack_info(query) {
  return request({
    url: '/pack/pack_info/list',
    method: 'get',
    params: query
  })
}

// 查询礼包信息详细
export function getPack_info(giftId) {
  return request({
    url: '/pack/pack_info/' + giftId,
    method: 'get'
  })
}

// 新增礼包信息
export function addPack_info(data) {
  return request({
    url: '/pack/pack_info',
    method: 'post',
    data: data
  })
}

// 修改礼包信息
export function updatePack_info(data) {
  return request({
    url: '/pack/pack_info',
    method: 'put',
    data: data
  })
}

// 删除礼包信息
export function delPack_info(giftId) {
  return request({
    url: '/pack/pack_info/' + giftId,
    method: 'delete'
  })
}
