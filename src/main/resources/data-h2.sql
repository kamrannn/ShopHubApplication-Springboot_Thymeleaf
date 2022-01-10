INSERT INTO business (name, description, email)
VALUES ('Candy Cats', 'The place for all things sugary and sweet.', 'candyheaven@sky.com');
INSERT INTO business (name, description, email)
VALUES ('Atomic Comics', 'Action packed graphic novels delivered to your door.', 'atomiccomics@gmail.com');
INSERT INTO business (name, description, email)
VALUES ('The Vinyl House', 'The Vinyl House offers the largest collection of vinyl records in wales.',
        'thevinylhouse@outlook.com');
INSERT INTO business (name, description, email)
VALUES ('Greys workshop', 'I sell construction tools', 'greysworkshop@outlook.com');

INSERT INTO users (username, password, enabled, email, role)
VALUES ('test1', '$2a$10$yAvBWzkF7Mqt0xZfXNono.VdCIBYFF0RW39pwYSVYwITV1yFb2fn2', TRUE, 'jakethesnake@gmail.com',
        'ADMIN');
-- password = password
INSERT INTO users (username, password, enabled, email)
VALUES ('test2', '$2a$12$AxyDvdDrJWGqVpYNJbfrA.9PiaDLuQqJKt9x3ykInVt2SUWBVn1Ai', TRUE, 'test@gmail.com');
-- password = Secret12?
INSERT INTO users (username, password, enabled, email)
VALUES ('test3', '$2a$12$7Qheb9GJ1C.0IkNRHx5JRecbdxo.nMnrPcD7TPbpbm2x7zZex5cJm', TRUE, 'johndoe@outlook.com');
-- password = pa55word£
INSERT INTO users (username, password, enabled, email)
VALUES ('test4', '$2a$12$A4ypFeNsZzY8Wmxwyy5bf.NMvZapllGAlCurqu8w4OF8nxeMmowV.', TRUE, 'jackmasters88@gmail.com');
-- password = Il0veyou!

INSERT INTO usersubscriptions (username, businessid)
VALUES ('test1', 1);
INSERT INTO usersubscriptions (username, businessid)
VALUES ('test1', 2);
INSERT INTO usersubscriptions (username, businessid)
VALUES ('test1', 4);
INSERT INTO usersubscriptions (username, businessid)
VALUES ('test2', 1);
INSERT INTO usersubscriptions (username, businessid)
VALUES ('test2', 3);