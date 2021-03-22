INSERT INTO stu_info VALUES (2, '20171344054', '王圣滋', 1, 'payed', '', '', '', '', '', NULL, NULL, 'gqty', NULL, '', '', '', '', '', '', '', '', '', '', '', '', '', '', 520, 455, '', 'no');
INSERT INTO stu_info VALUES (2, '20171344049', '勾怡璇', 1, 'payed', '', '', '', '', '', NULL, NULL, 'gqty', NULL, '', '', '', '', '', '', '', '', '', '', '', '', '', '', 503, NULL, '', 'no');
INSERT INTO stu_info VALUES (1, '20171344042', '寇雅楠', 1, 'payed', '', '', '', '', '', NULL, NULL, 'gqty', NULL, '', '', '', '', '', '', '', '', '', '', '', '', '', '', 503, NULL, '', 'no');

INSERT INTO class_info VALUES (1, '计算机与软件学院', '软件工程', 17, 1, 1001);
INSERT INTO class_info VALUES (2, '计算机与软件学院', '软件工程', 17, 2, 1001);
INSERT INTO class_info VALUES (3, '计算机与软件学院', '软件工程', 17, 3, 1001);
INSERT INTO class_info VALUES (4, '计算机与软件学院', '计算机科学与技术', 17, 1, 1002);
INSERT INTO class_info VALUES (5, '计算机与软件学院', '计算机科学与技术', 17, 2, 1002);
INSERT INTO class_info VALUES (6, '计算机与软件学院', '计算机科学与技术', 17, 3, 1002);
INSERT INTO class_info VALUES (7, '计算机与软件学院', '计算机科学与技术', 17, 4, 1002);
INSERT INTO class_info VALUES (8, '计算机与软件学院', '计算机科学与技术', 17, 5, 1003);
INSERT INTO class_info VALUES (9, '计算机与软件学院', '计算机科学与技术', 17, 6, 1003);
INSERT INTO class_info VALUES (10, '计算机与软件学院', '物联网工程', 17, 1, 1003);
INSERT INTO class_info VALUES (11, '计算机与软件学院', '物联网工程', 17, 2, 1003);
INSERT INTO class_info VALUES (12, '计算机与软件学院', '信息安全', 17, 1, 1001);
INSERT INTO class_info VALUES (13, '计算机与软件学院', '信息安全', 17, 2, 1004);
INSERT INTO class_info VALUES (14, '计算机与软件学院', '网络工程', 17, 1, 1004);
INSERT INTO class_info VALUES (15, '计算机与软件学院', '网络工程', 17, 2, 1004);
INSERT INTO class_info VALUES (16, '计算机与软件学院', '软件工程中外合作办学', 17, 1, 1004);
INSERT INTO class_info VALUES (17, '计算机与软件学院', '软件工程嵌入式', 17, 1, 1001);

INSERT INTO temp_leave (student_id, reason, start_time, end_time, teacher_id, status) VALUES ('20171344054', '打酱油', '2021-3-6', '2021-3-7', '1001', 1);
INSERT INTO temp_leave (student_id, reason, start_time, end_time, teacher_id, status) VALUES ('20171344054', '摸鱼', '2021-3-7', '2021-3-8', '1001', 1);

INSERT INTO instructor_info VALUES ('1001', '张学会');

INSERT INTO holiday_go ( student_id, term, holiday_type, destination, status ) VALUES ('20171344054', '2020-2021-2', 'qingMing', 'school', 1);

INSERT INTO no_borad ( student_id, term, address, reason, connect_method, status ) VALUES ('20171344054', '2020-2021-2', 'M87星云', 'aaa', '10086', 1);

-- 事务管理按钮权限
insert into sys_menu values('2001', '审批请假', '801', '1',  '', '', 1, 0, 'F', '0', '0', 'affair:templeave:approve',       '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2002', '审批节假日去向报备', '800', '1',  '', '', 1, 0, 'F', '0', '0', 'affair:holiday:approve',       '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2003', '审批外宿申请', '802', '1',  '', '', 1, 0, 'F', '0', '0', 'affair:noboard:approve',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu values('5', '用户管理', '0', '5', 'users',             null,   1, 0, 'M', '0', '0', '', '#',     'admin', sysdate(), '', null, '系统用户管理');
insert into sys_menu values('502',  '学生管理', '5',   '1', 'student', 'user/student/index', 1, 0, 'C', '0', '0', '', '#', 'admin', sysdate(), '', null, '学生账号管理菜单');
insert into sys_menu values('503',  '班级管理', '5',   '3', 'class', 'user/class/index', 1, 0, 'C', '0', '0', '', '#', 'admin', sysdate(), '', null, '班级信息管理菜单');
insert into sys_menu values('504',  '辅导员管理', '5',   '2', 'instructor', 'user/instructor/index', 1, 0, 'C', '0', '0', '', '#', 'admin', sysdate(), '', null, '辅导员信息管理菜单');
insert into sys_menu values('505',  '班主任管理', '5',   '4', 'headmaster', 'user/headmaster/index', 1, 0, 'C', '0', '0', '', '#', 'admin', sysdate(), '', null, '班主任信息管理菜单');
insert into sys_menu values('701', '等级考试', '7', '2', 'levelTest', 'study/levelTest/index',   1, 0, 'C', '0', '0', '', '#',     'admin', sysdate(), '', null, '学生等级考试管理');
insert into sys_menu values('702', '普通课程', '7', '3', 'courseGrade', 'study/courseGrade/index',   1, 0, 'C', '0', '0', '', '#',     'admin', sysdate(), '', null, '学生普通课程成绩管理');
insert into sys_menu values('703', '综合测评', '7', '4', 'multipGrade', 'study/multip/index',   1, 0, 'C', '0', '0', '', '#',     'admin', sysdate(), '', null, '综合测评成绩管理');
insert into sys_menu values('9', '评奖评优', '0', '9', 'scholarship',             null,   1, 0, 'M', '0', '0', '', '#',     'admin', sysdate(), '', null, '评奖评优事项管理');
insert into sys_menu values('901', '国家奖学金', '9', '1', 'national', 'scholarship/national/index',   1, 0, 'C', '0', '0', '', '#',     'admin', sysdate(), '', null, '国家奖学金管理');

-- insert into sys_role_menu values ('2', '1');
-- insert into sys_role_menu values ('2', '2');
-- insert into sys_role_menu values ('2', '3');

insert into sys_role values('3', '辅导员',    'instructor', 3, 2, 1, 1, '0', '0', 'admin', sysdate(), '', null, '辅导员');
insert into sys_role values('4', '班主任',    'headmaster', 4, 2, 1, 1, '0', '0', 'admin', sysdate(), '', null, '班主任');
insert into sys_role values('5', '副书记',    'master', 5, 2, 1, 1, '0', '0', 'admin', sysdate(), '', null, '副书记');

INSERT INTO course_grade VALUES ('20171344054', '2017-2018', '1', '公共(必)', '高等数学Ⅰ（1）', 85, NULL, NULL, 6);
INSERT INTO course_grade VALUES ('20171344050', '2017-2018', '1', '公共(必)', '高等数学Ⅰ（1）', 65, NULL, NULL, 6);
INSERT INTO course_grade VALUES ('20171344054', '2017-2018', '1', '公共(必)', '程序设计基础', 84, NULL, NULL, 4);
INSERT INTO course_grade VALUES ('20171344054', '2017-2018', '1', '通修课', '大学语文', 89, NULL, NULL, 2);
INSERT INTO course_grade VALUES ('20171344054', '2017-2018', '1', '公共(必)', '高等数学Ⅰ（2）', 87, NULL, NULL, 6);

SELECT stu_info.student_id, stu_info.name, stu_info.gradu_intention, stu_info.registration, class_info.department, class_info.profession, class_info.grade, class_info.class_num
FROM stu_info INNER JOIN class_info
ON stu_info.class_id = class_info.class_id
WHERE stu_info.student_id = '20171344054'

INSERT INTO headmaster_info VALUES (1, '');
INSERT INTO headmaster_info VALUES (2, '2017-2018');
