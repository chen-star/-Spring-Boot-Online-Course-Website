drop table if exists `Test`;
create table `Test` (
    `id` varchar(256) not null default '' comment 'id',
    `name` varchar(256) comment 'name',
    primary key (`id`)
) engine = innodb default charset=utf8mb4 comment='Test';

insert into `Test` (id, name) values (1, 'Test1');
insert into `Test` (id, name) values (2, 'Test2');