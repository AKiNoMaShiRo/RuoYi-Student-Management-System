import request from '@/utils/request'

const BASEPATH = '/info/classInfo'

export function getClassInfo(param){
    return request({
        url: BASEPATH + '/query/',
        method: 'get',
        params: param
    })
}

export function getProfessionType(){
    return request({
        url: BASEPATH + '/profession/type',
        method: 'get'
    })
}

export function addClassInfo(param){
    return request({
        url: BASEPATH + '/add',
        method: 'post',
        data: param
    })
}

export function editClassInfo(param){
    return request({
        url: BASEPATH + '/edit',
        method: 'post',
        data: param
    })
}

export function deleteClassInfo(param){
    return request({
        url: BASEPATH + '/delete/' + param,
        method: 'delete'
    })
}