insert into genre (id, `name`)
values (1, 'Fantasy');
insert into genre (id, `name`)
values (2, 'Educational');

insert into author (id, first_name, last_name)
values (1, 'Andrzej', 'Sapkowski');
insert into author (id, first_name, last_name)
values (2, 'Paul', 'Berry');

insert into book (id, `name`, author_id, genre_id)
values (1, 'The Witcher Saga', 1, 2);
insert into book (id, `name`, author_id, genre_id)
values (2, 'Learning Python programming', 2, 1);