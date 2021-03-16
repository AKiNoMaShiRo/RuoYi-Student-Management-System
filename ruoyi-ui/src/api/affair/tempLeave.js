import request from '@/utils/request'

let BASEPATH = '/temp/leave'

//    获取请假申请信息
export function getTempLeave(query) {
    return request({
        url: BASEPATH + '/queryById',
        method: 'get',
        params: query
    })
}

//    获取辅导员对应的请假申请信息
export function getInsTempLeave(query) {
    return request({
        url: BASEPATH + '/instructor/queryById',
        method: 'get',
        params: query
    })
}

//    提交请假申请
export function addTempLeave(params) {
    return request({
        url: BASEPATH + '/add',
        method: 'post',
        data: params
    })
}

//    撤销请假申请
export function deleteTempLeave(leaveId) {
    return request({
        url: BASEPATH + '/delete/' + leaveId,
        method: 'delete'
    })
}

//    更新请假申请状态
export function changeStatus(params) {
    return request({
        url: BASEPATH + '/setStatus',
        method: 'post',
        data: params
    })
}

//    修改请假申请
export function editTempLeave(params) {
    return request({
        url: BASEPATH + '/edit',
        method: 'post',
        data: params
    })
}