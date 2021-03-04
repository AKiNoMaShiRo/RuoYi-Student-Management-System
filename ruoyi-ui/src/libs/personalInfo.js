let nationOptionsStr = '汉族、满族、蒙古族、回族、藏族、维吾尔族、苗族、彝族、壮族、布依族、侗族、瑶族、白族、土家族、哈尼族、哈萨克族、傣族、黎族、傈僳族、佤族、畲族、高山族、拉祜族、水族、东乡族、纳西族、景颇族、柯尔克孜族、土族、达斡尔族、仫佬族、羌族、布朗族、撒拉族、毛南族、仡佬族、锡伯族、阿昌族、普米族、朝鲜族、塔吉克族、怒族、乌孜别克族、俄罗斯族、鄂温克族、德昂族、保安族、裕固族、京族、塔塔尔族、独龙族、鄂伦春族、赫哲族、门巴族、珞巴族、基诺族'
export const nationOptions = nationOptionsStr.split('、')

export const politicsStatusOptions = [
    { l:'中共党员', v: 'zgdy' },
    { l:'中共预备党员', v: 'zgybdy' },
    { l:'共青团员', v: 'gqty' },
    { l:'民革会员', v: 'mghy' },
    { l:'民盟盟员', v: 'mmhy' },
    { l:'民建会员', v: 'mjahy' },
    { l:'民进会员', v: 'mjihy' },
    { l:'农工党党员', v: 'ngddy' },
    { l:'致公党党员', v: 'zgddy' },
    { l:'九三学社社员', v: 'jsxssy' },
    { l:'台盟盟员', v: 'tmmy' },
    { l:'无党派民主人士', v: 'wdpmzrs' },
    { l:'群众', v: 'qz' },
]

export const healthStates = [
    { l: '健康', v: 'healthy' },
    { l: '一般', v: 'normal' },
    { l: '患病', v: 'ill' },
    { l: '缺陷', v: 'defect' },
    { l: '残疾', v: 'disability' }
]

export const graduateIntentionTypes = [
    { l: '报考硕士研究生', v: 'master' },
    { l: '出国留学', v: 'abroad' },
    { l: '实习实训', v: 'internship' },
    { l: '报考公务员', v: 'civil_servant' },
    { l: '报考事业单位', v: 'institutions' },
    { l: '大学生村官', v: 'village_official' },
    { l: '选调生', v: 'selected_students' },
    { l: '直接就业', v: 'work' },
    { l: '自主创业', v: 'business' },
    { l: '三支一扶', v: 'brace' },
    { l: '西部计划', v: 'west' },
    { l: '苏北计划', v: 'north' },
    { l: '国防生分配', v: 'defense_student' },
    { l: '其他', v: 'other' }
]

export const registrationStates = [
    { l: '已缴清学费并按规定办理注册', v: 'payed' },
    { l: '办理学费缓缴手续获批后予以注册', v: 'delay_payed' },
    { l: '办理生源地助学贷款获批后注册', v: 'loan' },
    { l: '未按学校规定缴纳学费不予以中蹙额', v: 'not_payed' },
    { l: '其他不符合条件的不予注册', v: 'other' }
]