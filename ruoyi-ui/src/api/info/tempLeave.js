import request from '@/utils/request'

export function getTempLeave(stuId) {
    return request({
        url: '/temp/leave/queryById' + stuId,
        method: 'get'
    })
}
export function addTempLeave(params) {
    return request({
        url: '/temp/leave/add',
        method: 'post',
        data: params
    })
}