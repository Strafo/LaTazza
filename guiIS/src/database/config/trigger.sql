
set schema LATAZZASCHEMA;

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




CREATE TRIGGER check_numero_aquisto_scatole
AFTER UPDATE OR INSERT ON LATAZZASCHEMA.COMPRA_VISITATORE
FOR EACH ROW
EXECUTE PROCEDURE checkNumCialde();

CREATE TRIGGER check_numero_aquisto_scatole
AFTER UPDATE OR INSERT ON LATAZZASCHEMA.COMPRA_DIPENDENTE
FOR EACH ROW
EXECUTE PROCEDURE checkNumCialde();