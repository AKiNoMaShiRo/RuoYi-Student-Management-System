import request from '@/utils/request'

//    根据学号查询所有家庭成员信息
export function getFamilyInfo(stuId){
    return request({
        url: '/info/family/list/queryById/' + stuId,
        method: 'get'
    })
}

//    修改家庭成员信息
export function editFamilyInfo(params){
    return request({
        url: '/info/family/edit',
        method: 'post',
        data: params
    })
}

//    修改家庭成员信息
export function deleteFamilyInfo(memberId){
    return request({
        url: '/info/family/delete/' + memberId,
        method: 'delete'
    })
}