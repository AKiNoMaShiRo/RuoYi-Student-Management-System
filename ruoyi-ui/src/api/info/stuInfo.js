import request from '@/utils/request'

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

//    修改学生信息
export function editStuInfo(params){
    return request({
        url: '/info/stu/edit',
        method: 'post',
        data: params
    })
}