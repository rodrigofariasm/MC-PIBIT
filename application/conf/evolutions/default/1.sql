# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table usuario (
  id                        bigint auto_increment not null,
  email                     varchar(255),
  password                  varchar(255),
  sessao_ativa              boolean,
  constraint pk_usuario primary key (id))
;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists usuario;

SET REFERENTIAL_INTEGRITY TRUE;

