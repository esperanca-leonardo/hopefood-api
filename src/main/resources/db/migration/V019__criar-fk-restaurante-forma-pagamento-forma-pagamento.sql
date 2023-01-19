alter table if exists restaurante_forma_pagamento
  add constraint fk_restaurante_forma_pagamento_forma_pagamento
  foreign key (forma_pagamento_id)
  references forma_pagamento;