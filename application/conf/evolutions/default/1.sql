# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table carona (
  id                        bigint not null,
  origem                    varchar(255),
  destino                   varchar(255),
  vagas                     varchar(255),
  data                      timestamp,
  motorista_email           varchar(255),
  ponto_de_encontro         varchar(255),
  constraint pk_carona primary key (id))
;

create table solicitacao_carona (
  id                        bigint not null,
  origem                    varchar(255),
  destino                   varchar(255),
  data                      timestamp,
  ponto_de_encontro         varchar(255),
  user_email                varchar(255),
  constraint pk_solicitacao_carona primary key (id))
;

create table usuario (
  email                     varchar(255) not null,
  password                  varchar(255),
  nome                      varchar(255),
  constraint pk_usuario primary key (email))
;

create sequence carona_seq;

create sequence solicitacao_carona_seq;

create sequence usuario_seq;

alter table carona add constraint fk_carona_motorista_1 foreign key (motorista_email) references usuario (email) on delete restrict on update restrict;
create index ix_carona_motorista_1 on carona (motorista_email);
alter table solicitacao_carona add constraint fk_solicitacao_carona_user_2 foreign key (user_email) references usuario (email) on delete restrict on update restrict;
create index ix_solicitacao_carona_user_2 on solicitacao_carona (user_email);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists carona;

drop table if exists solicitacao_carona;

drop table if exists usuario;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists carona_seq;

drop sequence if exists solicitacao_carona_seq;

drop sequence if exists usuario_seq;

