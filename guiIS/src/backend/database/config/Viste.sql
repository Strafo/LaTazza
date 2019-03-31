/*
CREATE OR REPLACE  FUNCTION CialdeVendute(tipo varchar(64)) RETURNS Integer AS
$$
BEGIN
if(tipo) is null then raise exception 'TIPO CIALDA NULLO!';
end if;
return 	(select sum(numero_cialde)
          from compra_dipendente join compra_visitatore using(tipo_cialda)
          where tipo_cialda=tipo
          );
END;
$$
LANGUAGE plpgsql ;

CREATE OR REPLACE  FUNCTION CialdeRifornite ( tipo varchar(64)) RETURNS Integer AS
$$
BEGIN
if(tipo) is null then raise exception 'TIPO CIALDA NULLO!';
end if;
	return (select sum(qta * 50)
	          from rifornimento
	          where tipo=tipo_cialda);

END;
$$
LANGUAGE plpgsql ;




create view StatoMagazzino (TipoCialda, qta) as
select tipo, stato.rifornite
 from cialda join (select rifornite
    from CialdeVendute(cialda.tipo)
    except
    select vendute
    from CialdeRifornite(cialda.tipo)) as stato;





CREATE OR REPLACE  FUNCTION pagamenti_a_credito(nomeP varchar(64), cognomeP varchar(64)) RETURNS double precision AS
$$
BEGIN
if( nome ) is null then raise exception 'NOME NULLO!';
end if;

if( cognome ) is null then raise exception 'COGNOME NULLO!';
end if;

return 	(select sum(numero_cialde * 0.5)
          from compra_dipendente
          where nome=nomeP and cognome=cognomeP and contanti=false
          );
END;
$$
LANGUAGE plpgsql ;

CREATE OR REPLACE  FUNCTION pagamentoDebito ( nomeP varchar(64), cognomeP varchar(64)) RETURNS double precision AS
$$
BEGIN
if( nome ) is null then raise exception 'NOME NULLO!';
end if;

if( cognome ) is null then raise exception 'COGNOME NULLO!';
end if;

	return (select sum(importo)
	          from pagamento_debito
	          where nome=nomeP and cognome=cognomeP);

END;
$$
LANGUAGE plpgsql ;


create view StatoDebiti (nome, cognome, importo) as
select tipo, stato.importoDebito
 from Personale join (select importoDebito
    from pagamentoDebito(nome,cognome)
    except
    select importoPagato
    from pagamenti_a_credito(nome,cognome)) as stato;





CREATE OR REPLACE  FUNCTION ImportoVendite() RETURNS double precision AS
$$
BEGIN

return 	((select sum(numero_cialde * 0.5)
          from compra_visitatore)
           +
           (select sum(numero_cialde * 0.5)
          from compra_dipendente
          where contanti=true)
          );
END;
$$
LANGUAGE plpgsql ;

CREATE OR REPLACE  FUNCTION ImportoRifornimenti() RETURNS double precision AS
$$
BEGIN

	return (select sum(qta * 0.4)
	          from rifornimento
	         );

END;
$$
LANGUAGE plpgsql ;


CREATE OR REPLACE  FUNCTION ImportoPagamentiDebiti() RETURNS double precision AS
$$
BEGIN

	return (select sum(importo)
	          from pagamento_debito
	         );

END;
$$
LANGUAGE plpgsql ;




create view statoCassa (importo) as
select stato.importoV + 250
from (select importoV
      from ImportoVendite()

      +

      select importoPagamenti
      from ImportoPagamentiDebiti()

      except

      select importoRif
      from ImportoRifornimenti());
*/
/*
create  view  Magazzino(tipoCialda, qta) as
  select TIPO_CIALDA, sum(qta*50)
  from  LATAZZASCHEMA.RIFORNIMENTO
  group by TIPO_CIALDA

    except

  select tipo_cialda, sum(numero_cialde)
  from LATAZZASCHEMA.COMPRA_VISITATORE
  group by tipo_cialda

    except

  select tipo_cialda, sum(numero_cialde)
  from LATAZZASCHEMA.COMPRA_DIPENDENTE
  group by tipo_cialda;






*/






