import request from '@/utils/request'

const BASEPATH = '/scholarship/endeavor'

export function getEdv(query) {
    return request({
        url: BASEPATH + '/query',
        method: 'get',
        params: query
    })
}

export function addEdv(param) {
    return request({
        url: BASEPATH + '/add',
        method: 'post',
        data: param
    })
}

export function updateEdvStatus(param) {
    return request({
        url: BASEPATH + '/updateStatus',
        method: 'post',
        data: param
    })
}