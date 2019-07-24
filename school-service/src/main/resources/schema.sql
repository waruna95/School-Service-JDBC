  drop table if exists book_seq;
  drop table if exists school;

  create table book_seq(
    next_val bigint
  );

  create table school (
     id VARCHAR(255) NOT NULL,
     city VARCHAR(255),
     name VARCHAR(255),
     PRIMARY KEY(id)
   );