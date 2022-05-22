insert into genre (id, `name`)
values (1, 'Educational'), (2, 'Scientific');

insert into author (id, first_name, last_name)
values (1, 'Ivan', 'Pupcin'), (2, 'Sergey', 'Gubcin');

insert into post (id, title, text, image, author_id, genre_id)
values (1, 'Learning java', 'text', 'image.img', 1, 1);

insert into post (id, title, text, author_id, genre_id)
values (1, 'Learning java', 'text', 1, 1);