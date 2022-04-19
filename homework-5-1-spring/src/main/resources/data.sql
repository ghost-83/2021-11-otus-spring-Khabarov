insert into genre (id, `name`)
values (1, 'Fantasy'),
       (2, 'Educational');

insert into author (id, first_name, last_name)
values (1, 'Andrzej', 'Sapkowski'),
       (2, 'Paul', 'Berry');

insert into book (id, title, text, author_id, genre_id)
values (1, 'The Witcher Saga', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec qu', 1, 2),
       (2, 'Learning Python programming', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu', 2, 1);

insert into comment (id, text, book_id)
values (1, 'Good!', 1),
       (2, 'Very good!', 1);

INSERT INTO user (id, username, password, full_name, email)
values (1, 'user', '$2a$08$uuuGT0RsSh532QIOlrS4wuTbrrUqGC2tV.WF30RVLwWmjA1Cs5yLO', 'User User', 'user@mail.com'),
       (2, 'admin', '$2a$08$uuuGT0RsSh532QIOlrS4wuTbrrUqGC2tV.WF30RVLwWmjA1Cs5yLO', 'Admin Admin', 'admin@mail.com');

INSERT INTO authority (id, user_id, authority)
values (1, 1, 'ROLE_USER'), (2, 2, 'ROLE_ADMIN');