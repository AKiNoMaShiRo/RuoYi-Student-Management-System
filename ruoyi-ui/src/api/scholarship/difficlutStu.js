import request from '@/utils/request'

const BASEPATH = '/difficult/student'

export function getIsDifficult(query){
    return request({
        url: BASEPATH + '/query',
        method: 'get',
        params: query
    })
}