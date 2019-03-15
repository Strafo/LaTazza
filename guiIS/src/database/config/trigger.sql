
create  view  Magazzino(tipoCialda, qta) as
  select tipoCialda, sum(qta*50)
  from  LATAZZASCHEMA.RIFORNIMENTO
  group by tipoCialda

    except

  select tipo_cialda, sum(numero_cialde)
  from LATAZZASCHEMA.COMPRA_VISITATORE
  group by tipo_cialda

    except

  select tipo_cialda, sum(numero_cialde)
  from LATAZZASCHEMA.COMPRA_DIPENDENTE
  group by tipo_cialda;






CREATE OR REPLACE FUNCTION checkNumCialde() RETURNS trigger AS
$$
declare
num1 integer ;
num2 integer;
num_magaz integer;
BEGIN

num1:=(select sum(numero_cialde) from LATAZZASCHEMA.COMPRA_VISITATORE
        where tipo_cialda = new.tipo_cialda);
num2:=(select sum(numero_cialde) from LATAZZASCHEMA.COMPRA_DIPENDENTE
        where tipo_cialda = new.tipo_cialda);
num_magaz:=(select sum(qta*50) from LATAZZASCHEMA.MAGAZZINO
	  	where tipoCialda=new.TipoCialda);
if(num1+num2 > num_magaz) then  RAISE EXCEPTION 'Cialde di tipo % nel magazzino insufficienti', new.TipoCialda;
end if;

END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER check_numero_aquisto_scatole
AFTER UPDATE OR INSERT ON LATAZZASCHEMA.COMPRA_VISITATORE
FOR EACH ROW
EXECUTE PROCEDURE checkNumCialde();

CREATE TRIGGER check_numero_aquisto_scatole
AFTER UPDATE OR INSERT ON LATAZZASCHEMA.COMPRA_DIPENDENTE
FOR EACH ROW
EXECUTE PROCEDURE checkNumCialde();