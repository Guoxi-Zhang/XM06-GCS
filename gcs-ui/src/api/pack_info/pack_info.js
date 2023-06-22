import request from '@/utils/request'

// 查询礼包设置列表
export function listPack_info(query) {
  return request({
    url: '/pack_info/pack_info/list',
    method: 'get',
    params: query
  })
}

// 查询礼包设置详细
export function getPack_info(packId) {
  return request({
    url: '/pack_info/pack_info/' + packId,
    method: 'get'
  })
}

// 新增礼包设置
export function addPack_info(data) {
  return request({
    url: '/pack_info/pack_info',
    method: 'post',
    data: data
  })
}

// 修改礼包设置
export function updatePack_info(data) {
  return request({
    url: '/pack_info/pack_info',
    method: 'put',
    data: data
  })
}

// 删除礼包设置
export function delPack_info(packId) {
  return request({
    url: '/pack_info/pack_info/' + packId,
    method: 'delete'
  })
}
