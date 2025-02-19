INSERT INTO client (name, type, identifier) VALUES ('Consumer', 'Individual', '0');
INSERT INTO client (name, type, identifier) VALUES ('João Silva', 'Individual', '12345678901');
INSERT INTO client (name, type, identifier) VALUES ('Indústria XYZ S.A.', 'Corporate', '23456789000176');
INSERT INTO client (name, type, identifier) VALUES ('Maria Oliveira', 'Individual', '98765432100');
INSERT INTO client (name, type, identifier) VALUES ('Tech Solutions Ltda.', 'Corporate', '34567890001234');
INSERT INTO client (name, type, identifier) VALUES ('Carlos Mendes', 'Individual', '87654321098');
INSERT INTO client (name, type, identifier) VALUES ('Supermarket ABC Ltda.', 'Corporate', '45678901234567');
INSERT INTO client (name, type, identifier) VALUES ('Ana Costa', 'Individual', '65432109876');
INSERT INTO client (name, type, identifier) VALUES ('Construction Corp.', 'Corporate', '56789012345678');
INSERT INTO client (name, type, identifier) VALUES ('Bruno Lima', 'Individual', '54321098765');
INSERT INTO client (name, type, identifier) VALUES ('Fashion Brand Ltda.', 'Corporate', '67890123456789');
INSERT INTO client (name, type, identifier) VALUES ('Luan Carvalho de Souza', 'Individual', '06468229189');

INSERT INTO e_role (description) VALUES ('ROLE_ADMIN'), ('ROLE_USER')

INSERT INTO e_user (username, password)            VALUES ('admin', '$2a$10$jT7ffrCWllvYBnVGjusEpeIHPPIUfg5E/mD/0A9I3E2JMyztolkcq')
INSERT INTO e_user (username, password, client_id) VALUES ('joao', '$2a$10$jT7ffrCWllvYBnVGjusEpeIHPPIUfg5E/mD/0A9I3E2JMyztolkcq', 2)

INSERT INTO user_role (role_id, user_id) VALUES (1, 1)
INSERT INTO user_role (role_id, user_id) VALUES (2, 1)
INSERT INTO user_role (role_id, user_id) VALUES (2, 2)

INSERT INTO Product (description, price) VALUES ('The Old Man and the Sea', 10.00);
INSERT INTO Product (description, price) VALUES ('Of Mice and Men', 15.50);
INSERT INTO Product (description, price) VALUES ('Moby Dick', 7.25);
INSERT INTO Product (description, price) VALUES ('To Kill a Mockingbird', 12.99);
INSERT INTO Product (description, price) VALUES ('1984', 9.75);
INSERT INTO Product (description, price) VALUES ('Pride and Prejudice', 8.50);
INSERT INTO Product (description, price) VALUES ('The Catcher in the Rye', 11.25);
INSERT INTO Product (description, price) VALUES ('Brave New World', 13.40);
INSERT INTO Product (description, price) VALUES ('The Great Gatsby', 10.50);
INSERT INTO Product (description, price) VALUES ('War and Peace', 20.00);

INSERT INTO e_order (client_id, date) VALUES (2, '2024-12-05');    
INSERT INTO e_order (client_id, date) VALUES (1, '2024-12-01');      
INSERT INTO e_order (client_id, date) VALUES (3, '2024-12-10');   
INSERT INTO e_order (client_id, date) VALUES (1, '2024-12-12');  
INSERT INTO e_order (client_id, date) VALUES (1, '2024-12-15');   
INSERT INTO e_order (client_id, date) VALUES (6, '2024-12-18');   
INSERT INTO e_order (client_id, date) VALUES (2, '2024-12-20');    
INSERT INTO e_order (client_id, date) VALUES (1, '2024-12-22');   
INSERT INTO e_order (client_id, date) VALUES (3, '2024-12-25');  
INSERT INTO e_order (client_id, date) VALUES (10, '2024-12-28');   

INSERT INTO e_order_item (order_id, product_id, quantity) VALUES (1, 1, 1); 
INSERT INTO e_order_item (order_id, product_id, quantity) VALUES (1, 2, 2);  
INSERT INTO e_order_item (order_id, product_id, quantity) VALUES (2, 3, 3); 
INSERT INTO e_order_item (order_id, product_id, quantity) VALUES (2, 4, 1); 
INSERT INTO e_order_item (order_id, product_id, quantity) VALUES (3, 5, 2); 
INSERT INTO e_order_item (order_id, product_id, quantity) VALUES (3, 6, 1);  
INSERT INTO e_order_item (order_id, product_id, quantity) VALUES (4, 7, 1);  
INSERT INTO e_order_item (order_id, product_id, quantity) VALUES (5, 8, 2);  
INSERT INTO e_order_item (order_id, product_id, quantity) VALUES (6, 9, 3);  
INSERT INTO e_order_item (order_id, product_id, quantity) VALUES (7, 10, 1);
INSERT INTO e_order_item (order_id, product_id, quantity) VALUES (8, 3, 5);  
INSERT INTO e_order_item (order_id, product_id, quantity) VALUES (9, 1, 2);  
INSERT INTO e_order_item (order_id, product_id, quantity) VALUES (10, 2, 1); 
