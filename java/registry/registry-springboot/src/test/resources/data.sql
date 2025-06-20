DROP TABLE IF EXISTS Person;

CREATE TABLE Person (
    id varchar(255) primary key,
    name varchar(255),
    email varchar(255),
    password varchar(255)
);

INSERT INTO Person (id, name, email, password) VALUES
    ('c4bbbee6-1f4b-468f-8b43-35eb0e89a417', 'lucie', 'lucie@mail.fr', 'lucielucie'),
    ('c4bbbee6-1f4b-468f-8b43-35eb0e89a418', 'pierre', 'pierre@mail.fr', 'pierrepierre'),
    ('c4bbbee6-1f4b-468f-8b43-35eb0e89a419', 'andre', 'andre@mail.fr', 'andreandre');