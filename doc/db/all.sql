drop table if exists `chapter`;
create table `chapter` (
    `id` char(8) not null comment 'ID',
    `course_id` char(8) comment 'Course ID',
    `name` varchar(50) comment 'Course Name',
    primary key (`id`)
) engine = innodb default charset=utf8mb4 comment='Chapter';

insert into `chapter` (id, course_id, name) values ('00000000', '00000000', 'Ch00');
insert into `chapter` (id, course_id, name) values ('00000001', '00000001', 'Ch01');
insert into `chapter` (id, course_id, name) values ('00000003', '00000000', 'Ch03');
insert into `chapter` (id, course_id, name) values ('00000004', '00000001', 'Ch04');
insert into `chapter` (id, course_id, name) values ('00000005', '00000000', 'Ch05');
insert into `chapter` (id, course_id, name) values ('00000006', '00000001', 'Ch06');
insert into `chapter` (id, course_id, name) values ('00000007', '00000000', 'Ch07');
insert into `chapter` (id, course_id, name) values ('00000008', '00000001', 'Ch08');
insert into `chapter` (id, course_id, name) values ('00000009', '00000000', 'Ch09');
insert into `chapter` (id, course_id, name) values ('00000010', '00000001', 'Ch10');
insert into `chapter` (id, course_id, name) values ('00000011', '00000000', 'Ch11');
insert into `chapter` (id, course_id, name) values ('00000012', '00000001', 'Ch12');


--  Test
drop table if exists `Test`;
create table `Test` (
                        `id` varchar(256) not null default '' comment 'id',
                        `name` varchar(256) comment 'name',
                        primary key (`id`)
) engine = innodb default charset=utf8mb4 comment='Test';

insert into `Test` (id, name) values (1, 'Test1');
insert into `Test` (id, name) values (2, 'Test2');