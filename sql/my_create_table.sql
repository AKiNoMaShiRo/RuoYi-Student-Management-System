-- ----------------------------
-- 1、学生信息表
-- ----------------------------
drop table if exists stu_info;
create table stu_info (
  class_id				int(4)			not null				   comment '班级ID',
  student_id			varchar(20)		not null				   comment '学号',
  name					varchar(30)		not null				   comment '姓名',
  gradu_intention		int(4)			default null			   comment '毕业意向调查',
  registration			int(4)			default null			   comment '是否已缴费注册',
  teacher				varchar(30)		default null			   comment '导师姓名',
  phone_number			varchar(20)		default null			   comment '手机号码',
  email					varchar(50)		default null			   comment '电子邮箱',
  qq					varchar(20)		default null			   comment 'QQ',
  we_chat				varchar(30)		default null			   comment '微信',
  birthday				datetime								   comment '出生日期',
  nation				varchar(20)		default null			   comment '民族',
  politics_status		int(4)			default null			   comment '政治面貌',
  join_time				datetime								   comment '加入时间',
  student_origin		varchar(20)		default null			   comment '生源地区',
  family_address		varchar(50)		default null			   comment '家庭地址',
  family_phone_number	varchar(20)		default null			   comment '家庭电话',
  family_contacts		varchar(30)		default null			   comment '家庭联系人',
  family_postcode		varchar(10)		default null			   comment '家庭邮编',
  gradu_school			varchar(30)		default null			   comment '毕业中学',
  gradu_school_postcode	varchar(10)		default null			   comment '中学邮编',
  used_name				varchar(30)		default null			   comment '曾用名',
  native_place			varchar(20)		default null			   comment '籍贯',
  health_card			varchar(20)		default null			   comment '医保卡号',
  cet4					int(4)			default null			   comment '四级成绩',
  cet6					int(4)			default null			   comment '六级成绩',
  english_name			varchar(30)		default null			   comment '英文名',
  report_item			int(4)			default null		   	   comment '信息上报项目',
  
  primary key (student_id)
) engine=innodb comment = '学生信息表';

-- ----------------------------
-- 2、班级信息表
-- ----------------------------
drop table if exists class_info;
create table class_info (
  class_id			bigint(20)      not null auto_increment    comment '班级ID',
  department		varchar(30)		not null				   comment '所属学院',
  profession		varchar(30)		not null				   comment '所属专业',
  grade				int(4)			not null				   comment '年级',
  class_num			int(4)			not null				   comment '班级',
  instructor_id		varchar(20)		not null				   comment '辅导员工号',

  primary key (class_id)
) engine=innodb comment = '班级信息表';

-- ----------------------------
-- 3、辅导员信息表
-- ----------------------------
drop table if exists instructor_info;
create table instructor_info (
  instructor_id		varchar(20)     not null 			       comment '辅导员工号',
  name				varchar(30)		not null				   comment '姓名',

  primary key (instructor_id)
) engine=innodb comment = '辅导员信息表';

-- ----------------------------
-- 4、家庭成员信息表
-- ----------------------------
drop table if exists stu_family_info;
create table stu_family_info (
  member_id			bigint(20)      not null auto_increment    comment '成员ID',
  relative_stu		varchar(20)		not null				   comment '所属学生学号',
  appellation		varchar(30)		default null			   comment '称谓',
  name				varchar(30)		not null				   comment '姓名',
  identity_card		varchar(20)		default null			   comment '身份证号',
  health			int(4)			default null			   comment '健康状况',
  company			varchar(50)		default null			   comment '单位名称',
  duty				varchar(30)		default null			   comment '职位',
  post_code			varchar(10)		default null			   comment '邮编',
  phone_number		varchar(20)		default null			   comment '电话号码',
  politics_status	int(4)			default null			   comment '政治面貌',
  remark			varchar(255)	default null			   comment '备注',

  primary key (member_id)
) engine=innodb comment = '家庭成员信息表';

-- ----------------------------
-- 5、临时请假申请表
-- ----------------------------
drop table if exists temp_leave;
create table temp_leave (
  leave_id			varchar(20)     not null auto_increment    comment '请假单ID',
  student_id		varchar(20)		not null				   comment '学号',
  reason			varchar(500)	not null				   comment '请假原因',
  start_time		datetime		not null				   comment '请假开始时间',
  end_time			datetime		not null				   comment '请假结束时间',
  teacher			varchar(30)		not null				   comment '任课老师',

  primary key (leave_id)
) engine=innodb comment = '临时请假申请表';

-- ----------------------------
-- 6、学业预警信息表
-- ----------------------------
drop table if exists saws_info;
create table saws_info (
  student_id		varchar(20)		not null				   comment '学号',
  alarm_level		int(4)			not null				   comment '预警等级',
  term				varchar(20)		not null				   comment '学期',
  reason			varchar(200)	not null				   comment '预警原因',
  remark			varchar(500)	not null				   comment '备注',

) engine=innodb comment = '学业预警信息表';