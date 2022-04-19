insert into genre (id, `name`)
values (1, 'Educational'), (2, 'Scientific');

insert into author (id, first_name, last_name)
values (1, 'Ivan', 'Pupcin'), (2, 'Sergey', 'Gubcin');

insert into book (id, `name`, author_id, genre_id)
values (1, 'Learning java', 1, 1);

insert into comment (id, text, book_id)
values (1, 'Good!', 1);