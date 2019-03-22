

insert into LATAZZASCHEMA.CIALDE values('caffè',0.50);
insert into LATAZZASCHEMA.CIALDE values ('thè', 0.25 );


insert into LATAZZASCHEMA.VISITATORE values ( 'Andrea', 'Manzi' );
insert into LATAZZASCHEMA.VISITATORE values ( 'Edoardo', 'Vignola' );
insert into LATAZZASCHEMA.VISITATORE values ( 'Marco', 'Rigardo' );
insert into LATAZZASCHEMA.VISITATORE values ( 'Filippo', 'Carrubba' );

insert into LATAZZASCHEMA.PERSONALE values ( 'Jacopo', 'Dapueto', true );
insert into LATAZZASCHEMA.PERSONALE values ( 'Andrea', 'Straforini', true );
insert into LATAZZASCHEMA.PERSONALE values ( 'Simone', 'Campisi', true );
insert into LATAZZASCHEMA.PERSONALE values ( 'Gabriele', 'Armanino', true );
--yyyy-MM-dd hh:mm:ss
insert into LATAZZASCHEMA.RIFORNIMENTO values ('2019-01-12 12:00:00','caffè',30);
insert into LATAZZASCHEMA.RIFORNIMENTO values ('2018-05-11 12:00:00','thè',30);

insert into LATAZZASCHEMA.COMPRA_DIPENDENTE values ('Simone','Campisi',true,13,'2018-06-11 13:00:00', 'caffè');
