import request from '@/utils/request'

const BASEPATH = '/info/classInfo'

export function getClassInfo(param){
    return request({
        url: BASEPATH + '/query',
        method: 'post',
        data: param
    })
}

export function getAllClassInfo(param){
    return request({
        url: BASEPATH + '/queryAll',
        method: 'post',
        data: param
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