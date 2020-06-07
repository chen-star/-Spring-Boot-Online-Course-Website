-- Chapter
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


-- Section
DROP TABLE IF EXISTS `section`;
CREATE TABLE `section` (
                           `id` CHAR(8) NOT NULL DEFAULT '' COMMENT 'ID',
                           `title` VARCHAR(50) NOT NULL COMMENT 'Title',
                           `course_id` CHAR(8) COMMENT 'Course Id',
                           `chapter_id` CHAR(8) COMMENT 'Chapter Id',
                           `video` VARCHAR(200) COMMENT 'Video',
                           `time` INT COMMENT 'Video length: s',
                           `charge` CHAR(1) COMMENT 'C: Charge | F: Free',
                           `sort` INT COMMENT 'Sequence',
                           `created_at` DATETIME(3) COMMENT 'Create Time',
                           `updated_at` DATETIME(3) COMMENT 'Modify Time',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Section';

INSERT INTO `section` (id, title, course_id, chapter_id, video, time, charge, sort, created_at, updated_at)
VALUES ('00000001', 'Sec01', '00000001', '00000000', '', 500, 'F', 1, now(), now());



--  Test
drop table if exists `Test`;
create table `Test` (
                        `id` varchar(256) not null default '' comment 'id',
                        `name` varchar(256) comment 'name',
                        primary key (`id`)
) engine = innodb default charset=utf8mb4 comment='Test';

insert into `Test` (id, name) values (1, 'Test1');
insert into `Test` (id, name) values (2, 'Test2');