CREATE TABLE t_address
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    address_line VARCHAR(255) NULL,
    city         VARCHAR(255) NULL,
    country      VARCHAR(255) NULL,
    postal_code  VARCHAR(255) NULL,
    province     VARCHAR(255) NULL,
    user_id      BIGINT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE t_bill
(
    order_id     BIGINT NOT NULL,
    cc_number    VARCHAR(255) NULL,
    date_created datetime NULL,
    number       INT    NOT NULL,
    payed        BIT(1) NOT NULL,
    total_cost   DOUBLE NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (order_id)
);

CREATE TABLE t_cart
(
    user_id           BIGINT NOT NULL,
    delivery_included BIT(1) NOT NULL,
    name              VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (user_id)
);

CREATE TABLE t_cart_item
(
    product_id BIGINT NOT NULL,
    quantity   DOUBLE NOT NULL,
    cart_id    BIGINT NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (product_id)
);

CREATE TABLE t_global_type
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    global_type_name VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE t_order
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    date_created      datetime NULL,
    delivery_cost     INT    NOT NULL,
    delivery_included BIT(1) NOT NULL,
    executed          BIT(1) NOT NULL,
    products_cost     DOUBLE NOT NULL,
    user_id           BIGINT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE t_order_product
(
    product_id BIGINT NOT NULL,
    quantity   DOUBLE NOT NULL,
    order_id   BIGINT NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (product_id)
);

CREATE TABLE t_picture
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    lg_picture_name VARCHAR(255) NULL,
    md_picture_name VARCHAR(255) NULL,
    sm_picture_name VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE t_product
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    available          BIT(1)       NOT NULL,
    codeuuid           VARCHAR(255) NOT NULL,
    product_name       VARCHAR(255) NULL,
    picture_id         BIGINT NULL,
    product_details_id BIGINT NULL,
    type_id            BIGINT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id),
    UNIQUE (codeuuid)
);

CREATE TABLE t_product_details
(
    id                  BIGINT AUTO_INCREMENT NOT NULL,
    manufacturer        VARCHAR(255) NULL,
    product_description VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE t_role
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE t_stock
(
    id                  BIGINT AUTO_INCREMENT NOT NULL,
    purchase_date       datetime NULL,
    purchase_price      DOUBLE NOT NULL,
    quantity            DOUBLE NOT NULL,
    unit_of_measurement VARCHAR(255) NULL,
    product_id          BIGINT NULL,
    supplier_id         BIGINT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE t_sub_type
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    sub_type_name  VARCHAR(255) NULL,
    global_type_id BIGINT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE t_supplier
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    phone_number  VARCHAR(255) NULL,
    supplier_name VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE t_type
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    type_name   VARCHAR(255) NULL,
    sub_type_id BIGINT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE t_user
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    birth_date   datetime NULL,
    date_created datetime NULL,
    email        VARCHAR(255) NULL,
    enabled      BIT(1) NOT NULL,
    first_name   VARCHAR(255) NULL,
    gender       VARCHAR(255) NULL,
    last_name    VARCHAR(255) NULL,
    password     VARCHAR(255) NULL,
    phone_number VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE user_role
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (user_id, role_id)
);

ALTER TABLE t_stock
    ADD CONSTRAINT FK5ddw309ocyct4ng2qb34ngupu FOREIGN KEY (product_id) REFERENCES t_product (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

CREATE INDEX FK5ddw309ocyct4ng2qb34ngupu ON t_stock (product_id);

ALTER TABLE t_product
    ADD CONSTRAINT FK5lltrudsitabt1tobljevad4e FOREIGN KEY (product_details_id) REFERENCES t_product_details (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

CREATE INDEX FK5lltrudsitabt1tobljevad4e ON t_product (product_details_id);

ALTER TABLE t_order_product
    ADD CONSTRAINT FKboulop61lj4gynyi3b6dk36gf FOREIGN KEY (product_id) REFERENCES t_product (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE t_cart
    ADD CONSTRAINT FKciojskxef5hat4vbi6xyqu37r FOREIGN KEY (user_id) REFERENCES t_user (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE t_product
    ADD CONSTRAINT FKct6cbtxc8v8tkhjvl53pt7y1o FOREIGN KEY (type_id) REFERENCES t_type (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

CREATE INDEX FKct6cbtxc8v8tkhjvl53pt7y1o ON t_product (type_id);

ALTER TABLE t_bill
    ADD CONSTRAINT FKd5h05at0j42hrmjqj4ugt3vx5 FOREIGN KEY (order_id) REFERENCES t_order (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE t_cart_item
    ADD CONSTRAINT FKdkanjbj8pw1gf2n8664pnqlom FOREIGN KEY (product_id) REFERENCES t_product (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE user_role
    ADD CONSTRAINT FKeqon9sx5vssprq67dxm3s7ump FOREIGN KEY (user_id) REFERENCES t_user (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE t_order_product
    ADD CONSTRAINT FKgrysmxhonhe80s9xl5vo3vu8c FOREIGN KEY (order_id) REFERENCES t_order (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

CREATE INDEX FKgrysmxhonhe80s9xl5vo3vu8c ON t_order_product (order_id);

ALTER TABLE t_order
    ADD CONSTRAINT FKho2r4qgj3txpy8964fnla95ub FOREIGN KEY (user_id) REFERENCES t_user (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

CREATE INDEX FKho2r4qgj3txpy8964fnla95ub ON t_order (user_id);

ALTER TABLE t_address
    ADD CONSTRAINT FKib1tav6peo3hd297p0bw4qng9 FOREIGN KEY (user_id) REFERENCES t_user (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

CREATE INDEX FKib1tav6peo3hd297p0bw4qng9 ON t_address (user_id);

ALTER TABLE user_role
    ADD CONSTRAINT FKj88osoj2hsbpvwgraxlnh2442 FOREIGN KEY (role_id) REFERENCES t_role (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

CREATE INDEX FKj88osoj2hsbpvwgraxlnh2442 ON user_role (role_id);

ALTER TABLE t_sub_type
    ADD CONSTRAINT FKlds7tefmr1suajobt6gb45jc8 FOREIGN KEY (global_type_id) REFERENCES t_global_type (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

CREATE INDEX FKlds7tefmr1suajobt6gb45jc8 ON t_sub_type (global_type_id);

ALTER TABLE t_type
    ADD CONSTRAINT FKmqrwmkl8a4jky2rkcxogkbjdj FOREIGN KEY (sub_type_id) REFERENCES t_sub_type (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

CREATE INDEX FKmqrwmkl8a4jky2rkcxogkbjdj ON t_type (sub_type_id);

ALTER TABLE t_cart_item
    ADD CONSTRAINT FKovd7lmhrumwcfgv15wn9yqspr FOREIGN KEY (cart_id) REFERENCES t_cart (user_id) ON UPDATE RESTRICT ON DELETE RESTRICT;

CREATE INDEX FKovd7lmhrumwcfgv15wn9yqspr ON t_cart_item (cart_id);

ALTER TABLE t_product
    ADD CONSTRAINT FKqgmreqsniupw8l1s8rhuu5nub FOREIGN KEY (picture_id) REFERENCES t_picture (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

CREATE INDEX FKqgmreqsniupw8l1s8rhuu5nub ON t_product (picture_id);

ALTER TABLE t_stock
    ADD CONSTRAINT FKr8u98a9t8vsq4ske20wirapdt FOREIGN KEY (supplier_id) REFERENCES t_supplier (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

CREATE INDEX FKr8u98a9t8vsq4ske20wirapdt ON t_stock (supplier_id);