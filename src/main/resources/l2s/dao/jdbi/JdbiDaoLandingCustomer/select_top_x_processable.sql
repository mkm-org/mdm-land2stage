select
  c.landing_id  c_landing_id,
  c.source_system_id c_source_system_id,
  c.source_customer_id c_source_customer_id,
  c.first_name c_first_name,
  c.last_name c_last_name,
  c.phone1 c_phone1,
  c.phone2 c_phone2,
  c.email1 c_email1
from
  LANDING_ALL_CUSTOMER_RECORD c
where
  c.LND_2_STG_REC_PROCESSD='N'
  and c.source_system_id is not null
order by c.landing_id
fetch first 100 rows only