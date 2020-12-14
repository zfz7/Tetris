CREATE TABLE hello_world(
    id  bigserial primary key,
    visit integer default 0 not null
);

insert into hello_world (visit) values (0);


