import request from '@/utils/request'

// 查询审核历史列表
export function getPackVerifyList(query) {
  return request({
    url: '/verify/pack_list/list',
    method: 'get',
    params: query
  })
}

export function getVerifyListByApplyId(query) {
  return request({
    url: '/verify/pack_list/getVerifyListByApplyId',
    method: 'get',
    params: query
  })
}
modifyPackApply

export function modifyPackApply(query) {
  return request({
    url: '/verify/pack_list/modifyPackApply',
    method: 'post',
    params: query
  })
}

export function addPackVerify(query) {
  return request({
    url: '/verify/pack_list/addPackVerify',
    method: 'post',
    params: query
  })
}


export function addPackVerifyByApplyIds(query) {
  return request({
    url: '/verify/pack_list/addPackVerifyByApplyIds',
    method: 'post',
    params: query
  })
}
