-- SuperAndes Domain
-- supermarket
INSERT INTO
    supermarket(name)
VALUES
    ('Quien invento esta vaina');

INSERT INTO
    supermarket(name)
VALUES
    ('Vendido por el compa');

-- branch office
INSERT INTO
    branch_office(name, city, address, supermarket_id)
VALUES
    (
        'El mercadito de Escobar',
        'Bogotá',
        'Cra. 1 #18a-12',
        1
    );

INSERT INTO
    branch_office(name, city, address, supermarket_id)
VALUES
    ('La vieja confiable', 'Montería', 'El uberrimo', 1);

INSERT INTO
    branch_office_authentication(
        activation_code,
        activation_token,
        branch_office_id
    )
VALUES
    (
        'a0748142-cdb1-47d3-a636-6699abdf9e65',
        '1087eb09-35b4-4241-98a2-477d16f9e48e',
        1
    );

INSERT INTO
    branch_office_authentication(
        activation_code,
        activation_token,
        branch_office_id
    )
VALUES
    (
        'd147485f-32c3-4af3-9f66-e5d8114efc14',
        '2b30930a-c8ac-4a1c-9b49-e8f3f44714b3',
        1
    );

-- user
INSERT INTO
    user_role(role)
VALUES
    ('DBA');

INSERT INTO
    user_role(role)
VALUES
    ('GENERAL_MANAGER');

INSERT INTO
    user_role(role)
VALUES
    ('BRANCH_OFFICE_MANAGER');

INSERT INTO
    user_role(role)
VALUES
    ('BRANCH_OFFICE_OPERATOR');

INSERT INTO
    user_role(role)
VALUES
    ('BRANCH_OFFICE_CASHIER');

INSERT INTO
    user_role(role)
VALUES
    ('CLIENT');

INSERT INTO
    user_(email, role_id, password)
VALUES
    ('test@uniandes.edu.co', 5, '123456');

-- product
INSERT INTO
    product_category(category, hierarchy_level, father_category)
VALUES
    ('some cat', 0, NULL);

INSERT INTO
    product_category(category, hierarchy_level, father_category)
VALUES
    ('child cat', 1, 1);

INSERT INTO
    product(name, brand, price, branch_office_id, category_id)
VALUES
    ('pasar SISTRANS', 'JAVALIMOS', 9999, 1, 2);

-- storage
INSERT INTO
    product_storage(type, capacity)
VALUES
    ('WAREHOUSE', 9999);

INSERT INTO
    product_storage_constraint(product_category_id, description)
VALUES
    (1, 'some description');

-- shopping cart
INSERT INTO
    shopping_cart(branch_office_id, state)
VALUES
    ('ACTIVE');

INSERT INTO
    shopping_cart_product(product_id, units, shopping_cart_id)
VALUES
    (1, 10, 1);

-- client
INSERT INTO
    client(user_id, shopping_cart_id, supermarket_id)
VALUES
    (1, NULL, 1);

-- sell registry
INSERT INTO
    sell_registry()
VALUES
    (1, 'A DATE', 1, 10);branch_office_id, sell_date, product_id, units

-- bill
INSERT INTO
    bill(user_id, issue_date, shopping_cart_id)
VALUES
    (1, 'A DATE', 1);

-- Log Domain
INSERT INTO
    system_info_log(
        branch_office_id,
        logged_at,
        java_version,
        java_vendor,
        user_name,
        os_name,
        os_arch
    )
VALUES
    (
        0,
        CURRENT_TIMESTAMP,
        '17',
        'Eclipse Adoptium',
        'idbl6',
        'Windows 11',
        'amd64'
    );
INSERt into shelf (branch_office_id)values (2);



INSERT INTO stock_shelf (id_shelf,product_id, quantity) values (1,1,20);
INSERt into sell_registry (branch_office_id,sell_date,product_id,units)values (1,SYSDATE,4, 20-10);
