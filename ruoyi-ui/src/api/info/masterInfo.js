import request from '@/utils/request'

const BASEPATH = '/info/master'

export function getAllMasterInfo () {
    return request({
        url: BASEPATH + '/queryAll',
        method: 'get'
    })
}

export function deleteMasterInfo (userId) {
    return request({
        url: BASEPATH + '/delete/' + userId,
        method: 'delete'
    })
}