import request from '@/utils/request'

const BASEPATH = '/scholarship/national'

export function getNational(query) {
    return request({
        url: BASEPATH + '/query',
        method: 'get',
        params: query
    })
}

export function getRecheck(query) {
    return request({
        url: BASEPATH + '/queryRecheck',
        method: 'get',
        params: query
    })
}

export function addNational(param) {
    return request({
        url: BASEPATH + '/add',
        method: 'post',
        data: param
    })
}

export function updateNationalStatus(param) {
    return request({
        url: BASEPATH + '/updateStatus',
        method: 'post',
        data: param
    })
}

export function deleteNational(param) {
    return request({
        url: BASEPATH + '/delete',
        method: 'post',
        data: param
    })
}