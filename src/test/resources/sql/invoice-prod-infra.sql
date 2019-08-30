select fa.trasmittente_id_paese, fa.c_ca_cognome 
from  "public".fa_header fa
where fa.c_ca_cognome is not null;

select count(*) from "public".fa_header;

select column_name, data_type, character_maximum_length
from INFORMATION_SCHEMA.COLUMNS where table_name = 'fa_body';
