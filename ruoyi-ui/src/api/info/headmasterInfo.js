import request from '@/utils/request'

const BASEPATH = '/info/headmaster'

//    批量查询辅导员信息
export function getAllHeadmasterInfo(query){
    return request({
        url: BASEPATH + '/queryAll',
        method: 'get',
        params: query
    })
}

//    添加辅导员基本信息
export function addHeadmasterInfo(params){
    return request({
        url: BASEPATH + '/add',
        method: 'post',
        data: params
    })
}

//    添加辅导员用户信息
export function addHeadmasterRole(params){
    return request({
        url: BASEPATH + '/add/role',
        method: 'post',
        data: params
    })
}

//    删除辅导员信息
export function deleteHeadInfo(headmasterId){
    return request({
        url: BASEPATH + '/delete/' +headmasterId ,
        method: 'delete'
    })
}

//    删除辅导员角色信息
export function deleteHeadRoleInfo(userId){
    return request({
        url: BASEPATH + '/deleteRole/' + userId,
        method: 'delete'
    })
}

//    删除辅导员用户信息
export function deleteHeadUserInfo(userId){
    return request({
        url: BASEPATH + '/deleteUser/' + userId,
        method: 'delete'
    })
}