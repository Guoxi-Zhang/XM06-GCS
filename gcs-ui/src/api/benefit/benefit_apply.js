import request from '@/utils/request'

export function getRoleInfo() {
  return request({
    url: '/benefit/benefit_apply/user_info',
    method: 'get'
  })
}

// 查询补助申请列表
export function listBenefit_apply(query) {
  return request({
    url: '/benefit/benefit_apply/list',
    method: 'get',
    params: query
  })
}

// 查询补助申请详细
export function getBenefit_apply(tableId) {
  return request({
    url: '/benefit/benefit_apply/' + tableId,
    method: 'get'
  })
}

// 新增补助申请
export function addBenefit_apply(data) {
  return request({
    url: '/benefit/benefit_apply',
    method: 'post',
    data: data
  })
}

// 修改补助申请
export function updateBenefit_apply(data) {
  return request({
    url: '/benefit/benefit_apply',
    method: 'put',
    data: data
  })
}

// 删除补助申请
export function delBenefit_apply(tableId) {
  return request({
    url: '/benefit/benefit_apply/' + tableId,
    method: 'delete'
  })
}
