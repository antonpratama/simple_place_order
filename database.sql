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

create table cart_item (
    id VARCHAR(266) NOT NULL ,
    cart_id bigint NOT NULL,
    product_id bigint NOT NULL,
    quantity integer NOT NULL,
    price double NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(cart_id) REFERENCES cart(id),
    FOREIGN KEY(product_id) REFERENCES product(id)
) ENGINE InnoDB;

create table orders(
    id VARCHAR(266) NOT NULL,
    customer_id bigint NOT NULL,
    status VARCHAR(266) NOT NULL,
    total_price double NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(customer_id) REFERENCES customer(id)
) ENGINE InnoDB;

create table orders_item(
    id VARCHAR(266) NOT NULL,
    orders_id VARCHAR(266) NOT NULL,
    product_id bigint NOT NULL,
    quantity integer NOT NULL,
    price double NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(orders_id) REFERENCES orders(id),
    FOREIGN KEY(product_id) REFERENCES product(id)
) ENGINE InnoDB;