import request from '@/utils/request'

let BASEPATH = '/holiday'

//    获取节假日去向信息
export function getHoliday(query){
    return request({
        url: BASEPATH + '/queryById',
        method: 'get',
        params: query
    })
}

//    新增节假日去向报备
export function addHoliday(params){
    return request({
        url: BASEPATH + '/add',
        method: 'post',
        data: params
    })
}

//    更新节假日去向报备状态
export function setStatus(params){
    return request({
        url: BASEPATH + '/setStatus',
        method: 'post',
        data: params
    })
}

//    修改节假日去向报备信息
export function editHoliday(params){
    return request({
        url: BASEPATH + '/edit',
        method: 'post',
        data: params
    })
}

//    撤销报备信息
export function deleteHoliday(goId){
    return request({
        url: BASEPATH + '/delete/' + goId,
        method: 'delete'
    })
}