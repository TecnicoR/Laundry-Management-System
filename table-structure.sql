create database laundrydb;
use laundrydb;
create table user (id int primary key auto_increment, name varchar(256), email varchar(100), phoneNumber varchar(15), password varchar(50));
create table admin (id int primary key auto_increment, name varchar(256), email varchar(100), password varchar(50));
create table laundryOrder ( id int primary key auto_increment, userName varchar(256), userId int, numberOfCloths int, clothItems varchar(512), status varchar(128), orderDate varchar(128), finishDate varchar(128), amount int);
create table priceChart(id int primary key auto_increment, typeOfCloth varchar(256), price int);
insert into priceChart(typeOfCloth, price) values('others', 15);
insert into admin (name, email, password) values ('Admin', 'admin@gmail.com', 'system');
