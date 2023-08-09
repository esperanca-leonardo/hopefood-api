package com.esperanca.hopefood.core.dtos.outputs.restaurant;

import com.esperanca.hopefood.core.dtos.outputs.address.AddressOutputDto;
import com.esperanca.hopefood.core.dtos.outputs.formofpayment.FormOfPaymentOutputDto;
import com.esperanca.hopefood.core.dtos.outputs.kitchen.KitchenCompleteOutputDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class RestaurantCompleteOutputDto {
	private Long id;
	private String name;
	private BigDecimal freightValue;
	private OffsetDateTime createdAt;
	private OffsetDateTime updatedAt;
	private AddressOutputDto address;
	private KitchenCompleteOutputDto kitchen;
	private List<FormOfPaymentOutputDto> formOfPayments;
}
