create table customer (
    id bigint AUTO_INCREMENT NOT NULL ,
    name VARCHAR(100) NOT NULL ,
    address VARCHAR(100) NOT NULL,
    PRIMARY KEY(id)
) ENGINE InnoDB;

drop table customer;

create table product (
    id bigint AUTO_INCREMENT NOT NULL ,
    name VARCHAR(100) NOT NULL ,
    type VARCHAR(100) NOT NULL,
    price integer NOT NULL,
    PRIMARY KEY(id)
) ENGINE InnoDB;

create table cart (
    id bigint AUTO_INCREMENT NOT NULL ,
    customer_id bigint NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(customer_id) REFERENCES customer(id)
) ENGINE InnoDB;