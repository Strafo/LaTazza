
set schema LATAZZASCHEMA;

CREATE TRIGGER check_num_Cialde_Comprate
AFTER UPDATE ON lATAZZASCHEMA.COMPRA_VISITATORE FOR EACH ROW
CALL "backend.database.config.TriggerCheckNumCialdeVisitatore";
