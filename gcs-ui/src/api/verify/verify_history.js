import request from '@/utils/request'

// 查询审核历史列表
export function listVerify_history(query) {
  return request({
    url: '/verify/verify_history/list',
    method: 'get',
    params: query
  })
}

// 查询审核历史详细
export function getVerify_history(verifyId) {
  return request({
    url: '/verify/verify_history/' + verifyId,
    method: 'get'
  })
}

// 新增审核历史
export function addVerify_history(data) {
  return request({
    url: '/verify/verify_history',
    method: 'post',
    data: data
  })
}

// 修改审核历史
export function updateVerify_history(data) {
  return request({
    url: '/verify/verify_history',
    method: 'put',
    data: data
  })
}

// 删除审核历史
export function delVerify_history(verifyId) {
  return request({
    url: '/verify/verify_history/' + verifyId,
    method: 'delete'
  })
}
