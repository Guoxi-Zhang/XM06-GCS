import request from '@/utils/request'

// 查询审核历史列表
export function getArearVerifyList(query) {
  return request({
    url: '/verify/arear_list/list',
    method: 'get',
    params: query
  })
}


export function getArearVerifyByApplyId(query) {
  return request({
    url: '/verify/arear_list/getArearVerifyByApplyId',
    method: 'get',
    params: query
  })
}

export function modifyArearApply(query) {
  return request({
    url: '/verify/arear_list/modifyArearApply',
    method: 'post',
    params: query
  })
}

export function addArearVerify(query) {
  return request({
    url: '/verify/arear_list/addArearVerify',
    method: 'post',
    params: query
  })
}
