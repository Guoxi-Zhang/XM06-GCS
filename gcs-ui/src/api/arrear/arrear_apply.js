import request from '@/utils/request'

// 查询欠缴费申请列表
export function listArrear_apply(query) {
  return request({
    url: '/arrear/arrear_apply/list',
    method: 'get',
    params: query
  })
}

// 统计信息
export function getStatistics() {
  return request({
    url: '/arrear/arrear_apply/chart',
    method: 'get'
  })
}

export function getRoleInfo() {
  return request({
    url: '/arrear/arrear_apply/user_info',
    method: 'get'
  })
}

// 查询欠缴费申请详细
export function getArrear_apply(tableId) {
  return request({
    url: '/arrear/arrear_apply/' + tableId,
    method: 'get'
  })
}

// 新增欠缴费申请
export function addArrear_apply(data) {
  return request({
    url: '/arrear/arrear_apply',
    method: 'post',
    data: data
  })
}

// 修改欠缴费申请
export function updateArrear_apply(data) {
  return request({
    url: '/arrear/arrear_apply',
    method: 'put',
    data: data
  })
}

// 删除欠缴费申请
export function delArrear_apply(tableId) {
  return request({
    url: '/arrear/arrear_apply/' + tableId,
    method: 'delete'
  })
}
