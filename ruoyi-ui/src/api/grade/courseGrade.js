import request from '@/utils/request'

const BASEPATH = '/course/grade'

//    根据课程、学期信息批量查询学生成绩，含班级信息
export function getAllGrade(query) {
    return request({
        url: BASEPATH + '/queryAll',
        method: 'get',
        params: query
    })
}

//    查询课程名称
export function getGradeName(query) {
    return request({
        url: BASEPATH + '/course/type',
        method: 'get',
        params: query
    })
}

//    查询课程平均分
export function getGradeAvg(query) {
    return request({
        url: BASEPATH + '/course/avg',
        method: 'get',
        params: query
    })
}
