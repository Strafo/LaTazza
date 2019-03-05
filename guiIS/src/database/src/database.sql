create schema isschema;

alter schema isschema owner to strafodb;

create table cialde
(
	tipo varchar(64) not null
		constraint cialde_pk
			primary key,
	prezzo_euro double precision not null
		constraint prezzo_positivo
			check (prezzo_euro >= (0)::double precision)
);

alter table cialde owner to postgres;

create table visitatore
(
	nome varchar(64) not null,
	cognome varchar(64) not null,
	constraint visitatore_pk
		primary key (nome, cognome)
);

alter table visitatore owner to postgres;

create table personale
(
	nome varchar(64) not null,
	cognome varchar(64) not null,
	attivo boolean not null,
	constraint personale_pk
		primary key (nome, cognome)
);

alter table personale owner to postgres;

create table rifornimento
(
	data date default CURRENT_DATE not null,
	quantita_cialde integer not null
		constraint quantita_multiplo_50
			check (mod(quantita_cialde, 50) = 0)
		constraint quantita_positiva
			check (quantita_scatole > 0),
	tipo_cialda varchar(64) not null
		constraint rifornimento_cialde_tipo_fk
			references cialde
				on update cascade on delete restrict,
	constraint rifornimento_pk
		primary key (data, tipo_cialda)
);

alter table rifornimento owner to strafodb;

create table pagamento_debito
(
	data date default CURRENT_DATE not null,
	importo double precision not null
		constraint pagamento_positivo
			check (importo >= (0)::double precision),
	nome_dipendente varchar(64) not null,
	cognome_dipendente varchar(64) not null,
	constraint pagamento_debito_pk
		primary key (data, nome_dipendente, cognome_dipendente),
	constraint pagamento_debito_personale_nome_cognome_fk
		foreign key (nome_dipendente, cognome_dipendente) references personale
			on update cascade on delete restrict
);

alter table pagamento_debito owner to postgres;

create table compra_visitatore
(
	data date default CURRENT_DATE not null,
	numero_cialde integer not null
		constraint quantita_positiva
			check (numero_cialde > 0),
	tipo_cialda varchar(64) not null,
	nome varchar(64) not null,
	cognome varchar(64) not null,
	constraint compra_visitatore_pk
		primary key (data, nome, cognome),
	constraint compra_visitatore_visitatore_nome_cognome_fk
		foreign key (nome, cognome) references visitatore
			on update cascade on delete restrict
);

alter table compra_visitatore owner to postgres;

create table compra_dipendente
(
	data date default CURRENT_DATE not null,
	numero_cialde integer not null
		constraint quantita_positiva
			check (numero_cialde > 0),
	tipo_cialda varchar(64) not null,
	nome varchar(64) not null,
	cognome varchar(64) not null,
	contanti boolean not null,
	constraint compra_dipendente_pk
		primary key (data, nome, cognome),
	constraint compra_dipendente_personale_nome_cognome_fk
		foreign key (nome, cognome) references personale
			on update cascade on delete restrict
);

alter table compra_dipendente owner to postgres;

