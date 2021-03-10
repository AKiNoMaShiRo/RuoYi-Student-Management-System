import request from '@/utils/request'

let BASEPATH = '/no/board'

//    获取外宿信息
export function getBoard(query){
    return request({
        url: BASEPATH + '/queryById',
        method: 'get',
        params: query
    })
}

//    新增外宿报备
export function addBoard(params){
    return request({
        url: BASEPATH + '/add',
        method: 'post',
        data: params
    })
}

//    更新外宿报备状态
export function setStatus(params){
    return request({
        url: BASEPATH + '/setStatus',
        method: 'post',
        data: params
    })
}

//    修改外宿报备信息
export function editBoard(params){
    return request({
        url: BASEPATH + '/edit',
        method: 'post',
        data: params
    })
}

//    撤销报备信息
export function deleteBoard(boardId){
    return request({
        url: BASEPATH + '/delete/' + boardId,
        method: 'delete'
    })
}