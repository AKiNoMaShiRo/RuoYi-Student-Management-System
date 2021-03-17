import request from '@/utils/request'

const BASEPATH = '/info/stu'

//    根据学号查询学生信息
export function getStuInfo(stuId){
    return request({
        url: '/info/stu/queryById/' + stuId,
        method: 'get',
    })
}

//    根据学号查询学生基本信息
export function getStuBaseInfo(stuId){
    return request({
        url: '/info/stu/queryBaseById/' + stuId,
        method: 'get',
    })
}

//    批量查询学生基本信息
export function getAllStuUser(query){
    return request({
        url: '/info/stu/queryAll/',
        method: 'get',
        params: query
    })
}

//    修改学生信息
export function editStuInfo(params){
    return request({
        url: '/info/stu/edit',
        method: 'post',
        data: params
    })
}

//    添加学生信息
export function addStuInfo(params){
    return request({
        url: BASEPATH + '/add',
        method: 'post',
        data: params
    })
}

//    添加学生角色信息
export function addStuRoleInfo(params){
    return request({
        url: BASEPATH + '/add/role',
        method: 'post',
        data: params
    })
}