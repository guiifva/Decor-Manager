CREATE TABLE categories
(
    id          UUID    NOT NULL,
    name        VARCHAR(255),
    description VARCHAR(255),
    active      BOOLEAN NOT NULL,
    CONSTRAINT pk_categories PRIMARY KEY (id)
);

CREATE TABLE product_category
(
    category_id UUID NOT NULL,
    product_id  UUID NOT NULL
);

CREATE TABLE product_theme
(
    product_id UUID NOT NULL,
    theme_id   UUID NOT NULL
);

CREATE TABLE products
(
    id                UUID             NOT NULL,
    name              VARCHAR(255)     NOT NULL,
    quantity          INTEGER          NOT NULL,
    reserved_quantity INTEGER          NOT NULL,
    price_per_day     DOUBLE PRECISION NOT NULL,
    description       VARCHAR(255),
    active            BOOLEAN          NOT NULL,
    market_value      DOUBLE PRECISION,
    CONSTRAINT pk_products PRIMARY KEY (id)
);

CREATE TABLE theme
(
    id          UUID         NOT NULL,
    name        VARCHAR(255) NOT NULL,
    size        VARCHAR(255),
    description VARCHAR(255),
    active      BOOLEAN      NOT NULL,
    price       DOUBLE PRECISION,
    CONSTRAINT pk_theme PRIMARY KEY (id)
);

ALTER TABLE products
    ADD CONSTRAINT uc_products_name UNIQUE (name);

ALTER TABLE theme
    ADD CONSTRAINT uc_theme_name UNIQUE (name);

ALTER TABLE product_category
    ADD CONSTRAINT fk_procat_on_category FOREIGN KEY (category_id) REFERENCES categories (id);

ALTER TABLE product_category
    ADD CONSTRAINT fk_procat_on_product FOREIGN KEY (product_id) REFERENCES products (id);

ALTER TABLE product_theme
    ADD CONSTRAINT fk_prothe_on_product FOREIGN KEY (product_id) REFERENCES products (id);

ALTER TABLE product_theme
    ADD CONSTRAINT fk_prothe_on_theme FOREIGN KEY (theme_id) REFERENCES theme (id);