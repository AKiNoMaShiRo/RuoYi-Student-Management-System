import request from '@/utils/request'

const BASEPATH = '/info/instructor'

//    批量查询辅导员信息
export function getAllInstructorInfo(query){
    return request({
        url: BASEPATH + '/queryAll',
        method: 'get',
        params: query
    })
}

//    添加辅导员基本信息
export function addInstructorInfo(params){
    return request({
        url: BASEPATH + '/add',
        method: 'post',
        data: params
    })
}

//    添加辅导员用户信息
export function addInstructorRole(params){
    return request({
        url: BASEPATH + '/add/role',
        method: 'post',
        data: params
    })
}

//    删除辅导员信息
export function deleteInsInfo(instructorId){
    return request({
        url: BASEPATH + '/delete/' +instructorId ,
        method: 'delete'
    })
}

//    删除辅导员角色信息
export function deleteInsRoleInfo(userId){
    return request({
        url: BASEPATH + '/deleteRole/' + userId,
        method: 'delete'
    })
}

//    删除辅导员用户信息
export function deleteInsUserInfo(userId){
    return request({
        url: BASEPATH + '/deleteUser/' + userId,
        method: 'delete'
    })
}