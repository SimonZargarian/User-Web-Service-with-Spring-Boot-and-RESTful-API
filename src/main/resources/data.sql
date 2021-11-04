/*
 * When the application is launched this file with the name data.sql gets called and 
 * the data in this file is used to initialize the database, this is a Spring Boot 
 * auto configuration feature.
 */

INSERT INTO USER_REST VALUES(10001, 'test@test.com', 'Bob', 'Smith', '12345678');
INSERT INTO USER_REST VALUES(10002, 'test1@test.com', 'Dave', 'Smith', '12345678' );
INSERT INTO USER_REST VALUES(10003,  'test2@test.com', 'Erik', 'Smith', '12345678');