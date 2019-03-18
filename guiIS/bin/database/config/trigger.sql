
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





--non si possono comprare più cialde di quelle del magazzino
CREATE TRIGGER check_numero_aquisto_scatole
AFTER UPDATE ON LATAZZASCHEMA.COMPRA_VISITATORE
FOR EACH ROW
EXECUTE PROCEDURE checkNumCialde();

CREATE OR REPLACE FUNCTION checkNumCialde() RETURNS trigger AS
$$


$$