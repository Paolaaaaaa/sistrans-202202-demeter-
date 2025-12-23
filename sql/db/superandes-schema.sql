    -- SuperAndes Domain
-- supermarket
CREATE TABLE
    supermarket (
        id NUMBER(19) GENERATED ALWAYS AS IDENTITY,
        name VARCHAR2(32 CHAR) NOT NULL,
        PRIMARY KEY (id)
    );

-- branch office
CREATE TABLE
    branch_office (
        id NUMBER(19) GENERATED ALWAYS AS IDENTITY,
        name VARCHAR2(64 CHAR) NOT NULL,
        country VARCHAR2(64 CHAR) NOT NULL,
        city VARCHAR2(64 CHAR) NOT NULL,
        address VARCHAR2(64 CHAR) NOT NULL,
        supermarket_id NUMBER(19) NOT NULL,
        PRIMARY KEY (id),
        FOREIGN KEY (supermarket_id) REFERENCES supermarket (id)
    );

CREATE TABLE
    branch_office_authentication (
        activation_code VARCHAR2(36 CHAR),
        activation_token VARCHAR2(36 CHAR),
        branch_office_id NUMBER(19) NOT NULL,
        PRIMARY KEY (activation_code, activation_token),
        FOREIGN KEY (branch_office_id) REFERENCES branch_office (id) ON DELETE CASCADE
    );

-- user
CREATE TABLE
    user_role (
        id NUMBER(2) GENERATED ALWAYS AS IDENTITY,
        role VARCHAR2(32 CHAR) NOT NULL UNIQUE,
        PRIMARY KEY (id)
    );

CREATE TABLE
    user_ (
        id NUMBER(19)  GENERATED ALWAYS AS IDENTITY,
        email VARCHAR2(32 CHAR),
        password VARCHAR2(32 CHAR) NOT NULL,
        role_id NUMBER(3) NOT NULL,
        FOREIGN KEY (role_id) REFERENCES user_role (id),
        PRIMARY KEY(id)
    );

CREATE TABLE
    employee(
        id NUMBER(19) PRIMARY KEY,
        branch_office_id NUMBER(19),
        FOREIGN KEY (id) REFERENCES user_(id),
        FOREIGN KEY (branch_office_id) REFERENCES branch_office (id)
);

CREATE TABLE
    user_session (
        id NUMBER(19)  GENERATED ALWAYS AS IDENTITY,
        user_id NUMBER(19) NOT NULL,
        session_start TIMESTAMP NOT NULL,
        session_end TIMESTAMP,
        login_token VARCHAR2(36 CHAR),
        FOREIGN KEY (user_id) REFERENCES user_ (id),
        PRIMARY KEY (id)
    );

-- product
CREATE TABLE
    product_category (
        id NUMBER(5)  GENERATED ALWAYS AS IDENTITY,
        category VARCHAR2(16 CHAR) NOT NULL,
        hierarchy_level NUMBER(2) NOT NULL,
        father_category NUMBER(5),
        FOREIGN KEY (father_category) REFERENCES product_category(id),
        PRIMARY KEY (id)
    );

CREATE TABLE
    product (
        id NUMBER(19)  GENERATED ALWAYS AS IDENTITY,
        branch_office_id NUMBER(19) NOT NULL ,
        brand VARCHAR2(16 CHAR) NOT NULL,
        price NUMBER(10) NOT NULL,
        category_id NUMBER(5),
        FOREIGN KEY (branch_office_id) REFERENCES branch_office(id),
        FOREIGN KEY (category_id) REFERENCES product_category(id),
        PRIMARY KEY(id)
    );

-- TODO

Create table 
    sell
-- storage
CREATE TABLE
    product_storage (
        id NUMBER(1) GENERATED ALWAYS AS IDENTITY,
        type VARCHAR2(16 CHAR) CHECK (type IN ('WAREHOUSE', 'SELLING_POINT')),
        capacity NUMBER(19),
        PRIMARY KEY(id)
    );

CREATE TABLE
    product_storage_constraint (
        product_category_id NUMBER(5) PRIMARY KEY,
        description VARCHAR2(32 CHAR) NOT NULL,
        FOREIGN KEY (product_category_id) REFERENCES product_category (id)
    );

-- TODO
CREATE TABLE
    product_supplier (
        nit_supplier NUMBER,
        quality_of_service NUMBER(1) CHECK 
            (quality_of_service BETWEEN 0 AND 5),
        product_id NUMBER,
        expected_due_date DATE NOT NULL,
        PRIMARY KEY ( product_id),
        FOREIGN KEY (product_id) REFERENCES product (id)
        );

-- shopping cart
CREATE TABLE
    shopping_cart (
        id NUMBER(19),  --GENERATED ALWAYS AS IDENTITY,
        branch_office_id NUMBER(19) NOT NULL,
        state VARCHAR2(16 CHAR) NOT NULL,
        CHECK (state IN ('ACTIVE', 'RETURNED', 'PAYED')),
        FOREIGN KEY (branch_office_id) REFERENCES branch_office (id),
        PRIMARY KEY (id)
    );

CREATE TABLE
    shopping_cart_product (
        id NUMBER(19)   GENERATED ALWAYS AS IDENTITY,
        product_id NUMBER(19) NOT NULL,
        units NUMBER(19) NOT NULL,
        shopping_cart_id NUMBER(19) NOT NULL,
        FOREIGN KEY (product_id) REFERENCES product (id),
        FOREIGN KEY (shopping_cart_id) REFERENCES shopping_cart (id),
        CHECK  (units >0 or units=0),
        PRIMARY KEY (id)
    );

-- client
CREATE TABLE
    client (
        user_id NUMBER(19) ,
        shopping_cart_id NUMBER(19),
        FOREIGN KEY (user_id) REFERENCES user_ (id),
        FOREIGN KEY (shopping_cart_id) REFERENCES shopping_cart(id),
        PRIMARY KEY (user_id)
    );

--bill
CREATE TABLE
    bill (
        id NUMBER(19) GENERATED ALWAYS AS IDENTITY,
        user_id NUMBER(19),
        issue_date DATE NOT NULL,
        shopping_cart_id NUMBER(19),
        FOREIGN KEY (user_id) REFERENCES user_ (id),
        FOREIGN KEY (shopping_cart_id) REFERENCES shopping_cart (id),
        PRIMARY KEY (id)
    );

--- order supplier
CREATE TABLE
    order_supplier (
        id NUMBER(19) GENERATED ALWAYS AS IDENTITY,
        issue_date DATE NOT NULL,
        state VARCHAR2(15 CHAR) NOT NULL,
        expecte_due_date DATE NOT NULL,
        due_date DATE,
        supplier NUMBER(19) NOT NULL,
        branch_office_id NUMBER(19) NOT NULL,
        product_id NUMBER(19) NOT NULL,
        quantity NUMBER(19) NOT NULL,
        CHECK (state IN ('TO_CONFIRM', 'DELIVERED', 'CANCELLED')),
        FOREIGN KEY (product_id) REFERENCES product (id),
        FOREIGN KEY (branch_office_id) REFERENCES branch_office (id),
        PRIMARY KEY (id));

-- Log Domain
CREATE TABLE
    system_info_log (
        id NUMBER(19) GENERATED ALWAYS AS IDENTITY,
        branch_office_id NOT NULL,
        logged_at TIMESTAMP NOT NULL,
        java_version VARCHAR2(16 CHAR) NOT NULL,
        java_vendor VARCHAR2(32 CHAR) NOT NULL,
        user_name VARCHAR2(16 CHAR) NOT NULL,
        os_name VARCHAR2(16 CHAR) NOT NULL,
        os_arch VARCHAR2(8 CHAR) NOT NULL,
        PRIMARY KEY (id),
        FOREIGN KEY (branch_office_id) REFERENCES branch_office (id)
    );

--- para req funcional 17
CREATE TABLE 
    shelf (
        id_shelf NUMBER GENERATED ALWAYS AS IDENTITY,
        branch_office_id NUMBER NOT NULL,
        PRIMARY KEY (id_shelf),
        FOREIGN KEY (branch_office_id) REFERENCES branch_office (id)
    );
    
    
CREATE TABLE 
    stock_shelf(
        id_shelf NUMBER ,
        product_id NUMBER NOT NULL,
        quantity NUMBER NOT NULL,
        PRIMARY KEY (id_shelf, product_id),
        check (quantity>1 or quantity = 0),
        FOREIGN KEY (product_id) REFERENCES product(id)
        
        
    );
--sell registry

CREATE TABLE 
    sell_registry 
    (
    id number GENERATED ALWAYS AS IDENTITY,
    branch_office_id number not null, 
    sell_date DATE not null, 
    product_id number, 
    units number,
    primary key (id ),
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (branch_office_id) REFERENCES branch_office (id)
    );
---SEQUENCIAS

 create sequence shopping_cart_sequence
  start with 1
  increment by 1
  maxvalue 999999999
  minvalue 1;


 create sequence elment_sequence
  start with 1
  increment by 1
  maxvalue 999999999
  minvalue 1;

create sequence bill_sequence
  start with 1
  increment by 1
  maxvalue 999999999
  minvalue 1;