insert into genre (id, `name`)
values (1, 'Fantasy'),
       (2, 'Educational');

insert into author (id, first_name, last_name)
values (1, 'Andrzej', 'Sapkowski'),
       (2, 'Paul', 'Berry');

insert into book (id, `name`, author_id, genre_id)
values (1, 'The Witcher Saga', 1, 2),
       (2, 'Learning Python programming', 2, 1);

insert into comment (id, text, book_id)
values (1, 'Good!', 1),
       (2, 'Very good!', 1);