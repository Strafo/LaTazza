--file che contiene la struttura del DB

create schema LATAZZASCHEMA;

create table LATAZZASCHEMA.cialde(
  tipo varchar(64) not null primary key,
  prezzo double not null default (0.50) check ( prezzo >=0)
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
  importo double precision not null check( importo > 0),
  primary key (nome, cognome, data),
  foreign key(nome, cognome) references LATAZZASCHEMA.personale(nome,cognome) on update cascade on delete restrict
);

create table LATAZZASCHEMA.compra_visitatore(

  nome varchar(64) not null,
  cognome varchar(64) not null,
  tipo_cialda varchar(64) not null references LATAZZASCHEMA.cialde(tipo),
  numero_cialde integer not null check (numero_cialde > 0),
  data TIMESTAMP default CURRENT_TIMESTAMP not null,
  primary key(nome,cognome, data),
  foreign key(nome, cognome) references LATAZZASCHEMA.visitatore(nome, cognome) on update cascade on delete restrict
);

create table LATAZZASCHEMA.compra_dipendente(

  nome varchar(64) not null,
  cognome varchar(64) not null,
  tipo_cialda varchar(64) not null references LATAZZASCHEMA.cialde(tipo),
  contanti boolean not null, -- se � false paga con credito, altrimenti in contanti
  numero_cialde integer not null check (numero_cialde > 0),
  data TIMESTAMP default CURRENT_TIMESTAMP not null,
  primary key (data, nome, cognome),
  foreign key (nome, cognome) references LATAZZASCHEMA.personale(nome, cognome)on update cascade on delete restrict
);


create table LATAZZASCHEMA.Magazzino
(
  tipo varchar(64) not null primary key,
  qta integer not null default(0),
  foreign key (tipo) references LATAZZASCHEMA.cialde(tipo) on update cascade on delete restrict
);

create table LATAZZASCHEMA.Debito(

  nome varchar(64) not null,
  cognome varchar(64) not null,
  importo double not null default (0),
  foreign key (nome, cognome) references LATAZZASCHEMA.personale(nome, cognome)on update cascade on delete restrict

);

create table LATAZZASCHEMA.Cassa(
    importo double not null default (1500.00) primary key
);
insert into LATAZZASCHEMA.Cassa values ();--insert con valore di default




