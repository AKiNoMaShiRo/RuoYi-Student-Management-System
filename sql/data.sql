INSERT INTO stu_info VALUES (2, '20171344054', '王圣滋', 1, 'payed', '', '', '', '', '', NULL, NULL, 'gqty', NULL, '', '', '', '', '', '', '', '', '', '', 520, 455, '', 'no');
INSERT INTO stu_info VALUES (2, '20171344049', '勾怡璇', 1, 'payed', '', '', '', '', '', NULL, NULL, 'qz', NULL, '', '', '', '', '', '', '', '', '', '', 503, NULL, '', 'no');
INSERT INTO stu_info VALUES (1, '20171344042', '寇雅楠', 1, 'payed', '', '', '', '', '', NULL, NULL, 'gqty', NULL, '', '', '', '', '', '', '', '', '', '', 503, NULL, '', 'no');

INSERT INTO class_info VALUES (1, '计算机与软件学院', '软件工程', 17, 1, 1);
INSERT INTO class_info VALUES (2, '计算机与软件学院', '软件工程', 17, 2, 1);
INSERT INTO class_info VALUES (3, '计算机与软件学院', '软件工程', 17, 3, 1);


SELECT stu_info.student_id, stu_info.name, stu_info.gradu_intention, stu_info.registration, class_info.department, class_info.profession, class_info.grade, class_info.class_num
FROM stu_info INNER JOIN class_info
ON stu_info.class_id = class_info.class_id
WHERE stu_info.student_id = '20171344054'