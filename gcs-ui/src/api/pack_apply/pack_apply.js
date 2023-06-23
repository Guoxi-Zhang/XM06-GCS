import request from '@/utils/request'

export function getRoleInfo() {
  return request({
    url: '/pack_apply/pack_apply/user_info',
    method: 'get'
  })
}

// 查询爱心大礼包申请列表
export function listPack_apply(query) {
  return request({
    url: '/pack_apply/pack_apply/list',
    method: 'get',
    params: query
  })
}

// 查询爱心大礼包申请详细
export function getPack_apply(tableId) {
  return request({
    url: '/pack_apply/pack_apply/' + tableId,
    method: 'get'
  })
}

// 新增爱心大礼包申请
export function addPack_apply(data) {
  return request({
    url: '/pack_apply/pack_apply',
    method: 'post',
    data: data
  })
}

// 修改爱心大礼包申请
export function updatePack_apply(data) {
  return request({
    url: '/pack_apply/pack_apply',
    method: 'put',
    data: data
  })
}

// 删除爱心大礼包申请
export function delPack_apply(tableId) {
  return request({
    url: '/pack_apply/pack_apply/' + tableId,
    method: 'delete'
  })
}
