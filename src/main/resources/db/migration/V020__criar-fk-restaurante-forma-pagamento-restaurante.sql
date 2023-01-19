alter table if exists restaurante_forma_pagamento
  add constraint fk_restaurante_forma_pagamento_restaurante
  foreign key (restaurante_id)
  references restaurante;