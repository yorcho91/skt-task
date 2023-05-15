create table product (
    id int not null auto_increment,
    sku varchar(16) not null unique,
    name varchar(32) not null,
    description varchar(64),
    primary key (id)
);
