INSERT INTO person (name) VALUES ('João Silva');
INSERT INTO natural_person (person_id, cpf) VALUES (1, '12345678901');

INSERT INTO person (name) VALUES ('Indústria XYZ S.A.');
INSERT INTO juridical_person (person_id, cnpj) VALUES (2, '23456789000176');

-- Insert products
INSERT INTO Product (id, description, price) VALUES (1, 'The Old Man and the Sea', 10.00);
INSERT INTO Product (id, description, price) VALUES (2, 'Of Mice and Men', 15.50);
INSERT INTO Product (id, description, price) VALUES (3, 'Moby Dick', 7.25);

-- Insert sales
INSERT INTO sale (id, client_id, date, total) VALUES (1, 1, '2024-10-01', 41);
INSERT INTO sale (id, client_id, date, total) VALUES (2, 2, '2024-10-05', 52.75);

-- Insert sale items (linking sales to products)
INSERT INTO sale_item (id, sale_id, product_id, quantity) VALUES (1, 1, 1, 1); -- sale 1, Product A
INSERT INTO sale_item (id, sale_id, product_id, quantity) VALUES (2, 1, 2, 2); -- sale 1, Product B
INSERT INTO sale_item (id, sale_id, product_id, quantity) VALUES (3, 2, 2, 2); -- sale 2, Product B
INSERT INTO sale_item (id, sale_id, product_id, quantity) VALUES (4, 2, 3, 3); -- sale 2, Product C