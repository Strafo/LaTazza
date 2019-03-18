--file che contiene la struttura del DB

create schema LATAZZASCHEMA;

create table LATAZZASCHEMA.cialde
(
  tipo varchar(64) not null primary key,
  prezzo double precision not null check (prezzo >= (0)::double precision)
);


create table LATAZZASCHEMA.visitatore(
  nome varchar(64) not null,
  cognome varchar(64) not null,
  primary key(nome, cognome)
);

create table LATAZZASCHEMA.rifornimento(

  dataR date default CURRENT_TIMESTAMP not null,
  tipoCialda varchar(64) not null references cialde(tipo),
  qta integer not null, -- NUMERO DI SCATOLE COMPRATE
  primary key (dataR,tipoCialda)
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
  data date default CURRENT_TIMESTAMP not null,
  importo double not null check( importo > 0),
  primary key (nome, cognome, data),
  foreign key(nome, cognome) references personale(nome,cognome)
  on update cascade on delete restrict
);

create table LATAZZASCHEMA.compra_visitatore(

  nome varchar(64) not null,
  cognome varchar(64) not null,
  tipo_cialda varchar(64) not null references cialde(tipo),
  numero_cialde integer not null check (numero_cialde > 0),
  data date default CURRENT_TIMESTAMP not null,
  primary key(nome,cognome, data),
  foreign key(nome, cognome) references visitatore(nome, cognome)
  on update cascade on delete restrict
);

create table LATAZZASCHEMA.compra_dipendente(

  nome varchar(64) not null,
  cognome varchar(64) not null,
  contanti boolean not null, -- se è false paga con credito, altrimenti in contanti
  numero_cialde integer not null check (numero_cialde > 0),
  data date default CURRENT_TIMESTAMP not null,
  tipo_cialda varchar(64) not null references cialde(tipo),
  primary key (data, nome, cognome),
  foreign key (nome, cognome) references personale(nome, cognome)
  on update cascade on delete restrict
);






