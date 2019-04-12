/*insert into LATAZZASCHEMA.PERSONALE (nome,cognome,attivo) VALUES ('andrea','straforini',true);
insert into LATAZZASCHEMA.PERSONALE (nome,cognome,attivo) VALUES ('gabriele','armanino',true);
insert into LATAZZASCHEMA.PERSONALE (nome,cognome,attivo) VALUES ('jacopo','dapueto',true);
insert into LATAZZASCHEMA.PERSONALE (nome,cognome,attivo) VALUES ('simone','campisi',true);
insert into LATAZZASCHEMA.PERSONALE (nome,cognome,attivo) VALUES ('alessandro','caroti',false );
insert into LATAZZASCHEMA.PERSONALE (nome,cognome,attivo) VALUES ('andrea','storace',false );
insert into LATAZZASCHEMA.PERSONALE (nome,cognome,attivo) VALUES ('elisa','zazzera',false);
insert into LATAZZASCHEMA.PERSONALE (nome,cognome,attivo) VALUES ('andrea','oneto',true);
insert into LATAZZASCHEMA.PERSONALE (nome,cognome,attivo) VALUES ('gabriele','gianluigino',true);
insert into LATAZZASCHEMA.PERSONALE (nome,cognome,attivo) VALUES ('jacopo','sivori',true);
insert into LATAZZASCHEMA.PERSONALE (nome,cognome,attivo) VALUES ('simone','mirto',true);
insert into LATAZZASCHEMA.PERSONALE (nome,cognome,attivo) VALUES ('alessandro','sabatini',false );
insert into LATAZZASCHEMA.PERSONALE (nome,cognome,attivo) VALUES ('andrea','squillante',false );
insert into LATAZZASCHEMA.PERSONALE (nome,cognome,attivo) VALUES ('elisa','montezemolo',true);
insert into LATAZZASCHEMA.CIALDE (TIPO,PREZZO_EURO,PREZZO_CENTESIMI) VALUES ('caffè',0,5);
insert into LATAZZASCHEMA.CIALDE (TIPO,PREZZO_EURO,PREZZO_CENTESIMI) VALUES ('thè',0,5);
insert into LATAZZASCHEMA.CIALDE (TIPO,PREZZO_EURO,PREZZO_CENTESIMI) VALUES ('cioccolata',0,5);
insert into LATAZZASCHEMA.CIALDE (TIPO,PREZZO_EURO,PREZZO_CENTESIMI) VALUES ('tortaalgustoditorta',0,5);
insert into LATAZZASCHEMA.VISITATORE (NOME,COGNOME) VALUES ('pino','scotto');
insert into LATAZZASCHEMA.VISITATORE (NOME,COGNOME) VALUES ('luciano','ligabue');
insert into LATAZZASCHEMA.VISITATORE (NOME,COGNOME) VALUES ('salmo','lebon');
insert into LATAZZASCHEMA.VISITATORE (NOME,COGNOME) VALUES ('fabri','fibra');
insert into LATAZZASCHEMA.RIFORNIMENTO(DATAR,TIPO_CIALDA,NUMERO_CIALDE) VALUES ( '2000-12-31 00:00:00','caffè',2);
insert into LATAZZASCHEMA.RIFORNIMENTO(DATAR,TIPO_CIALDA,NUMERO_CIALDE) VALUES ( '2012-12-1 00:00:00','cioccolata',3);
insert into LATAZZASCHEMA.RIFORNIMENTO(DATAR,TIPO_CIALDA,NUMERO_CIALDE) VALUES ( '2012-12-10 00:00:00','thè',3);
insert into LATAZZASCHEMA.RIFORNIMENTO(DATAR,TIPO_CIALDA,NUMERO_CIALDE) VALUES ( '2012-11-1 00:00:00','thè',3);
COMMIT ;
insert into LATAZZASCHEMA.COMPRA_DIPENDENTE("NOME", "COGNOME", "CONTANTI", "NUMERO_CIALDE", "TIPO_CIALDA") VALUES ('andrea','straforini',false,10,'caffè');
insert into LATAZZASCHEMA.COMPRA_DIPENDENTE("DATA","NOME", "COGNOME", "CONTANTI", "NUMERO_CIALDE", "TIPO_CIALDA") VALUES ('2019-01-01 00:00:00','andrea','oneto',false,9,'caffè');
insert into LATAZZASCHEMA.COMPRA_VISITATORE("NOME", "COGNOME", "TIPO_CIALDA", "NUMERO_CIALDE") VALUES ('pino','scotto','caffè',6);
insert into LATAZZASCHEMA.COMPRA_VISITATORE("NOME", "COGNOME", "TIPO_CIALDA", "NUMERO_CIALDE") VALUES ('luciano','ligabue','thè',6);
insert into LATAZZASCHEMA.COMPRA_VISITATORE("DATA","NOME", "COGNOME", "TIPO_CIALDA", "NUMERO_CIALDE") VALUES ('2019-01-01 00:00:00','salmo','lebon','cioccolata',6);
*/

insert into LATAZZASCHEMA.CIALDE values ('the', 0,50 );
insert into LATAZZASCHEMA.CIALDE values('caffe',0,50);
insert into LATAZZASCHEMA.CIALDE values('camomilla',0,50);



insert into LATAZZASCHEMA.VISITATORE values ( 'Andrea', 'Manzi' );
insert into LATAZZASCHEMA.VISITATORE values ( 'Edoardo', 'Vignola' );
insert into LATAZZASCHEMA.VISITATORE values ( 'Marco', 'Rigardo' );
insert into LATAZZASCHEMA.VISITATORE values ( 'Filippo', 'Carrubba' );

insert into LATAZZASCHEMA.PERSONALE values ( 'Jacopo', 'Dapueto', true );
insert into LATAZZASCHEMA.PERSONALE values ( 'Andrea', 'Straforini', true );
insert into LATAZZASCHEMA.PERSONALE values ( 'Simone', 'Campisi', true );
insert into LATAZZASCHEMA.PERSONALE values ( 'Gabriele', 'Armanino', true );

--yyyy-MM-dd hh:mm:ss
insert into LATAZZASCHEMA.RIFORNIMENTO values ('2019-01-12 12:00:00', 200,'caffe');--80euro
insert into LATAZZASCHEMA.RIFORNIMENTO values ('2018-05-11 12:00:00', 150,'the');--60
--insert into LATAZZASCHEMA.RIFORNIMENTO values ('2018-05-11 12:00:00', 5,'camomilla');

insert into LATAZZASCHEMA.COMPRA_DIPENDENTE values ('Simone','Campisi', 'caffe',13,'2018-06-11 13:00:00',true);--6.5
insert into LATAZZASCHEMA.COMPRA_DIPENDENTE values ('Jacopo','Dapueto', 'the',8,'2018-06-11 14:00:00',false);
insert into LATAZZASCHEMA.COMPRA_DIPENDENTE values ('Jacopo','Dapueto', 'caffe',15,'2018-08-11 16:30:00',false);
insert into LATAZZASCHEMA.COMPRA_DIPENDENTE values ('Jacopo','Dapueto', 'caffe',2000,'2018-07-10 9:00:00', false);--per trigger
--insert into LATAZZASCHEMA.COMPRA_DIPENDENTE values ('Gabriele','Armanino', 'camomilla',30,'2018-09-11 15:30:00',false);


insert into LATAZZASCHEMA.COMPRA_VISITATORE values ('Andrea','Manzi', 'caffe',10,'2018-07-10 8:00:00' );--5
insert into LATAZZASCHEMA.COMPRA_VISITATORE values ('Edoardo','Vignola','the',12,'2019-02-15 17:00:00');--6
insert into LATAZZASCHEMA.COMPRA_VISITATORE values ('Andrea','Manzi', 'caffe',2000,'2018-07-10 9:00:00');


insert into LATAZZASCHEMA.PAGAMENTO_DEBITO values ('Jacopo','Dapueto', '2019-01-11 14:00:00',2, 33);
insert into LATAZZASCHEMA.PAGAMENTO_DEBITO values ('Jacopo','Dapueto', '2019-02-11 14:00:00',5, 66);
