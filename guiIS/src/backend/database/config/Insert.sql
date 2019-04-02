
insert into LATAZZASCHEMA.CIALDE values ('the', 0.50 );
insert into LATAZZASCHEMA.CIALDE values('caffe',0.50);
insert into LATAZZASCHEMA.CIALDE values('camomilla',0.50);



insert into LATAZZASCHEMA.VISITATORE values ( 'Andrea', 'Manzi' );
insert into LATAZZASCHEMA.VISITATORE values ( 'Edoardo', 'Vignola' );
insert into LATAZZASCHEMA.VISITATORE values ( 'Marco', 'Rigardo' );
insert into LATAZZASCHEMA.VISITATORE values ( 'Filippo', 'Carrubba' );

insert into LATAZZASCHEMA.PERSONALE values ( 'Jacopo', 'Dapueto', true );
insert into LATAZZASCHEMA.PERSONALE values ( 'Andrea', 'Straforini', true );
insert into LATAZZASCHEMA.PERSONALE values ( 'Simone', 'Campisi', true );
insert into LATAZZASCHEMA.PERSONALE values ( 'Gabriele', 'Armanino', true );

--yyyy-MM-dd hh:mm:ss
insert into LATAZZASCHEMA.RIFORNIMENTO values ('2019-01-12 12:00:00', 500,'caffe');
insert into LATAZZASCHEMA.RIFORNIMENTO values ('2018-05-11 12:00:00', 350,'the');
insert into LATAZZASCHEMA.RIFORNIMENTO values ('2018-05-11 12:00:00', 500,'camomilla');

insert into LATAZZASCHEMA.COMPRA_DIPENDENTE values ('Simone','Campisi', 'caffe',true,13,'2018-06-11 13:00:00');
insert into LATAZZASCHEMA.COMPRA_DIPENDENTE values ('Jacopo','Dapueto', 'the',false,8,'2018-06-11 14:00:00');
insert into LATAZZASCHEMA.COMPRA_DIPENDENTE values ('Jacopo','Dapueto', 'caffe',false,15,'2018-08-11 16:30:00');
--insert into LATAZZASCHEMA.COMPRA_DIPENDENTE values ('Jacopo','Dapueto', 'caffe',false,2000,'2018-07-10 9:00:00');--per trigger
insert into LATAZZASCHEMA.COMPRA_DIPENDENTE values ('Gabriele','Armanino', 'camomilla',false,30,'2018-09-11 15:30:00');


insert into LATAZZASCHEMA.COMPRA_VISITATORE values ('Andrea','Manzi', 'caffe',10,'2018-07-10 8:00:00' );
insert into LATAZZASCHEMA.COMPRA_VISITATORE values ('Edoardo','Vignola','the',12,'2019-02-15 17:00:00');
insert into LATAZZASCHEMA.COMPRA_VISITATORE values ('Andrea','Manzi', 'caffe',2000,'2018-07-10 9:00:00');


insert into LATAZZASCHEMA.PAGAMENTO_DEBITO values ('Jacopo','Dapueto', '2019-01-11 14:00:00',2);
insert into LATAZZASCHEMA.PAGAMENTO_DEBITO values ('Jacopo','Dapueto', '2019-02-11 14:00:00',5);








