import request from '@/utils/request'

// 查询审核历史列表
export function getBenefitVerifyList(query) {
  return request({
    url: '/verify/benefit_list/list',
    method: 'get',
    params: query
  })
}

export function getVerifyListByApplyId(query) {
  return request({
    url: '/verify/benefit_list/getVerifyListByApplyId',
    method: 'get',
    params: query
  })
}
modifyBenefitApply

export function modifyBenefitApply(query) {
  return request({
    url: '/verify/benefit_list/modifyBenefitApply',
    method: 'post',
    params: query
  })
}

export function addBenefitVerify(query) {
  return request({
    url: '/verify/benefit_list/addBenefitVerify',
    method: 'post',
    params: query
  })
}


export function addBenefitVerifyByApplyIds(query) {
  return request({
    url: '/verify/benefit_list/addBenefitVerifyByApplyIds',
    method: 'post',
    params: query
  })
}
