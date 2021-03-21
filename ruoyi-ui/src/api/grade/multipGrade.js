import request from '@/utils/request'

const BASEPATH = '/multip/grade'

//    根据课程、学期信息批量查询学生成绩，含班级信息
export function getAllGrade(query) {
    return request({
        url: BASEPATH + '/queryAll',
        method: 'post',
        data: query
    })
}
