import request from '@/utils/request'

const BASEPATH = '/notify'

export function getNotify(param) {
    return request({
        url: BASEPATH + '/query',
        method: 'post',
        data: param
    })
}