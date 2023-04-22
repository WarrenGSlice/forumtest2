create table authorities(
                            id serial primary key,
                            authority varchar(50) not null unique
);

create table users(
                      id serial primary key,
                      username varchar(50) not null unique,
                      password varchar(100) not null,
                      enabled boolean default true,
                      authority_id int references authorities(id)
);

insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into users (username, password, authority_id)
values ('admin', 'admin',
        (select id from authorities where authority = 'ROLE_ADMIN')),
        ('user', 'user',
        (select id from authorities where authority = 'ROLE_USER'));
