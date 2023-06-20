import request from '@/utils/request'

// 查询流程管理列表
export function listProcess_management(query) {
  return request({
    url: '/process/process_management/list',
    method: 'get',
    params: query
  })
}

// 查询流程管理详细
export function getProcess_management(id) {
  return request({
    url: '/process/process_management/' + id,
    method: 'get'
  })
}

// 新增流程管理
export function addProcess_management(data) {
  return request({
    url: '/process/process_management',
    method: 'post',
    data: data
  })
}

// 修改流程管理
export function updateProcess_management(data) {
  return request({
    url: '/process/process_management',
    method: 'put',
    data: data
  })
}

// 删除流程管理
export function delProcess_management(id) {
  return request({
    url: '/process/process_management/' + id,
    method: 'delete'
  })
}
