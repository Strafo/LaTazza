--file che contiene la struttura del DB

create schema LATAZZASCHEMA;

create table LATAZZASCHEMA.cialde(
  tipo varchar(64) not null primary key,
  prezzo_euro int not null default (0) check ( prezzo_euro >=0),
  prezzo_centesimi tinyint not null default (50) check( prezzo_centesimi >= 0 and prezzo_centesimi <100)
);


create table LATAZZASCHEMA.visitatore(
  nome varchar(64) not null,
  cognome varchar(64) not null,
  primary key(nome, cognome)
);

create table LATAZZASCHEMA.rifornimento(

  dataR TIMESTAMP default CURRENT_TIMESTAMP not null,
  numero_cialde integer not null, -- NUMERO DI cialde COMPRATE
  tipo_cialda varchar(64) not null references LATAZZASCHEMA.cialde(tipo) on update cascade on delete restrict,
  primary key (dataR,tipo_cialda)
);

create table LATAZZASCHEMA.personale(
  nome varchar(64) not null,
  cognome varchar(64) not null,
  attivo boolean not null,
  primary key (nome, cognome)
);

create table LATAZZASCHEMA.pagamento_debito(

  nome varchar(64) not null,
  cognome varchar(64) not null,
  data TIMESTAMP default CURRENT_TIMESTAMP not null,
  euro bigint not null check( euro > 0),
  centesimi tinyint not null check(centesimi>=0 and centesimi < 100),
  primary key (nome, cognome, data),
  foreign key(nome, cognome) references LATAZZASCHEMA.personale(nome,cognome) on update cascade on delete restrict
);

create table LATAZZASCHEMA.compra_visitatore(

  nome varchar(64) not null,
  cognome varchar(64) not null,
  tipo_cialda varchar(64) not null references LATAZZASCHEMA.cialde(tipo) on update cascade on delete restrict,
  numero_cialde integer not null check (numero_cialde > 0),
  data TIMESTAMP default CURRENT_TIMESTAMP not null,
  primary key(nome,cognome, data),
  foreign key(nome, cognome) references LATAZZASCHEMA.visitatore(nome, cognome) on update cascade on delete restrict
);

create table LATAZZASCHEMA.compra_dipendente(

  nome varchar(64) not null,
  cognome varchar(64) not null,
  tipo_cialda varchar(64) not null references LATAZZASCHEMA.cialde(tipo) on update cascade on delete restrict,
  contanti boolean not null, -- se � false paga con credito, altrimenti in contanti
  numero_cialde integer not null check (numero_cialde > 0),
  data TIMESTAMP default CURRENT_TIMESTAMP not null,
  primary key (data, nome, cognome),
  foreign key (nome, cognome) references LATAZZASCHEMA.personale(nome, cognome)on update cascade on delete restrict
);


create table LATAZZASCHEMA.Magazzino
(
  tipo varchar(64) not null primary key,
  qta integer not null default(0)
);

create table LATAZZASCHEMA.Debito(

  nome varchar(64) not null,
  cognome varchar(64) not null,
  euro bigint not null check( euro > 0),
  centesimi tinyint not null check(centesimi>=0 and centesimi < 100),
  primary key (nome,cognome),
  foreign key (nome, cognome) references LATAZZASCHEMA.personale(nome, cognome)on update cascade on delete restrict

);

create table LATAZZASCHEMA.Cassa(
  euro bigint not null default (500)check( euro >= 0),
  centesimi tinyint not null default (0) check(centesimi>=0 and centesimi < 100)
);
SET LOCK_MODE 1;
insert into LATAZZASCHEMA.Cassa values ();--insert con valore di default




