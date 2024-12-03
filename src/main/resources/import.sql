INSERT INTO person (name) VALUES ('João Silva');
INSERT INTO natural_person (person_id, cpf) VALUES (1, '12345678901');

INSERT INTO person (name) VALUES ('Indústria XYZ S.A.');
INSERT INTO juridical_person (person_id, cnpj) VALUES (2, '23456789000176');

INSERT INTO person (name) VALUES ('Maria Oliveira');
INSERT INTO natural_person (person_id, cpf) VALUES (3, '98765432100');

INSERT INTO person (name) VALUES ('Tech Solutions Ltda.');
INSERT INTO juridical_person (person_id, cnpj) VALUES (4, '34567890001234');

INSERT INTO person (name) VALUES ('Carlos Mendes');
INSERT INTO natural_person (person_id, cpf) VALUES (5, '87654321098');

INSERT INTO person (name) VALUES ('Supermarket ABC Ltda.');
INSERT INTO juridical_person (person_id, cnpj) VALUES (6, '45678901234567');

INSERT INTO person (name) VALUES ('Ana Costa');
INSERT INTO natural_person (person_id, cpf) VALUES (7, '65432109876');

INSERT INTO person (name) VALUES ('Construction Corp.');
INSERT INTO juridical_person (person_id, cnpj) VALUES (8, '56789012345678');

INSERT INTO person (name) VALUES ('Bruno Lima');
INSERT INTO natural_person (person_id, cpf) VALUES (9, '54321098765');

INSERT INTO person (name) VALUES ('Fashion Brand Ltda.');
INSERT INTO juridical_person (person_id, cnpj) VALUES (10, '67890123456789');

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

INSERT INTO sale (client_id, date) VALUES (1, '2024-10-01');      
INSERT INTO sale (client_id, date) VALUES (2, '2024-10-05');    
INSERT INTO sale (client_id, date) VALUES (3, '2024-10-10');   
INSERT INTO sale (client_id, date) VALUES (1, '2024-10-12');  
INSERT INTO sale (client_id, date) VALUES (1, '2024-10-15');   
INSERT INTO sale (client_id, date) VALUES (6, '2024-10-18');   
INSERT INTO sale (client_id, date) VALUES (2, '2024-10-20');    
INSERT INTO sale (client_id, date) VALUES (1, '2024-10-22');   
INSERT INTO sale (client_id, date) VALUES (3, '2024-10-25')    
INSERT INTO sale (client_id, date) VALUES (10, '2024-10-28');   


INSERT INTO sale_item (sale_id, product_id, quantity) VALUES (1, 1, 1); 
INSERT INTO sale_item (sale_id, product_id, quantity) VALUES (1, 2, 2);  
INSERT INTO sale_item (sale_id, product_id, quantity) VALUES (2, 3, 3); 
INSERT INTO sale_item (sale_id, product_id, quantity) VALUES (2, 4, 1); 
INSERT INTO sale_item (sale_id, product_id, quantity) VALUES (3, 5, 2); 
INSERT INTO sale_item (sale_id, product_id, quantity) VALUES (3, 6, 1);  
INSERT INTO sale_item (sale_id, product_id, quantity) VALUES (4, 7, 1);  
INSERT INTO sale_item (sale_id, product_id, quantity) VALUES (5, 8, 2);  
INSERT INTO sale_item (sale_id, product_id, quantity) VALUES (6, 9, 3);  
INSERT INTO sale_item (sale_id, product_id, quantity) VALUES (7, 10, 1);
INSERT INTO sale_item (sale_id, product_id, quantity) VALUES (8, 3, 5);  
INSERT INTO sale_item (sale_id, product_id, quantity) VALUES (9, 1, 2);  
INSERT INTO sale_item (sale_id, product_id, quantity) VALUES (10, 2, 1); 
