import request from '@/utils/request'

const BASEPATH = '/level/test'

//    根据学号查询学生等级考试成绩(暂未使用)
export function getByStuId(query) {
    return request({
        url: BASEPATH + '/queryById',
        method: 'get',
        params: query
    })
}

//    根据等级考试信息查询学生成绩，含班级信息
export function getByLT(query) {
    return request({
        url: BASEPATH + '/queryByLT',
        method: 'get',
        params: query
    })
}


//    根据等级考试信息查询学生成绩，含班级信息
export function getPassRateByLT(query) {
    return request({
        url: BASEPATH + '/queryRate',
        method: 'post',
        data: query
    })
}