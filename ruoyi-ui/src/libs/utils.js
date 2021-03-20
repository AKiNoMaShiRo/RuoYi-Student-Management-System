export const DEPARTMENTS = 
[ '大气科学学院', '应用气象学院', '大气物理学院', '地理科学学院', '遥感与测绘工程学院', '水温与水资源工程学院', '海洋科学学院', '环境科学与工程学院', '自动化学院', '电子与信息工程学院', '计算机与软件学院' , '数学与统计学院', '物理与光电学院', '法政学院', '马克思主义学院', '管理工程学院', '商学院', '文学院', '艺术学院', '大学体育部', '继续教育学院', '应用技术学院', '雷丁学院', '长望学院', '教师教育学院', '化学与材料学院', '人工智能学院', '龙山书院', '人文与艺术教育中心' ]

export const HOLIDAYTYPEOPTS = [
    { label: '元旦', value: 'newYearDay' },
    { label: '清明节', value: 'qingMing' },
    { label: '劳动节', value: 'laborDay' },
    { label: '国庆节', value: 'nationalDay' },
    { label: '寒假', value: 'winter' },
    { label: '暑假', value: 'summer' }
]

export const DESTINATIONOPTS = [
    { label: '回家', value: 'home' },
    { label: '外出', value: 'other' },
    { label: '留校', value: 'school' }
]

export const COURSETYPE = [
    { label: '学科(必)', value: '学科(必)' },
    { label: '专业(必)', value: '专业(必)' },
    { label: '专业(选)', value: '专业(选)' },
    { label: '实践(必)', value: '实践(必)' },
    { label: '实践(选)', value: '实践(选)' },
    { label: '公共(必)', value: '公共(必)' },
    { label: '方向(选)', value: '方向(选)' },
    { label: '通修课', value: '通修课' }
]

export function termOptions(stuId) {
    let res = [] 
    let startYear = parseInt( stuId.substring(0, 4) )    //入学年份
    for (let i = 0; i < 4; i ++) {
        let temp = (startYear + i).toString() + '-' + (startYear + i + 1).toString() + '-1'
        res.push({ label: temp, value: temp })
        temp = (startYear + i).toString() + '-' + (startYear + i + 1).toString() + '-2'
        res.push({ label: temp, value: temp })
    }
    return res
}

export function learnYearOptions(stuId) {
    let res = [] 
    let startYear = parseInt( stuId.substring(0, 4) )    //入学年份
    for (let i = 0; i < 4; i ++) {
        let temp = (startYear + i).toString() + '-' + (startYear + i + 1).toString()
        res.push({ label: temp, value: temp })
    }
    return res
}