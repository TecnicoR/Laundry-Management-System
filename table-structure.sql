create database laundrydb;
use laundrydb;
create table user (id int primary key auto_increment, name varchar(256), email varchar(100), phoneNumber varchar(15), password varchar(50));
create table admin (id int primary key auto_increment, name varchar(256), email varchar(100), password varchar(50));
create table laundryorder ( id int primary key auto_increment, userName varchar(256), numberOfCloths int, clothItems varchar(512), orderDate date, finishDate date);
insert into admin (name, email, password) values ('Admin', 'admin@gmail.com', 'system');