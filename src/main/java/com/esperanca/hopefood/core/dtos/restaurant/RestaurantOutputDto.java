package com.esperanca.hopefood.core.dtos.restaurant;

import com.esperanca.hopefood.core.dtos.address.AddressOutputDto;
import com.esperanca.hopefood.core.dtos.formofpayments.FormOfPaymentOutputDto;
import com.esperanca.hopefood.core.dtos.kitchens.KitchenOutputDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class RestaurantOutputDto {
	private Long id;
	private String name;
	private BigDecimal freightValue;
	private OffsetDateTime createdAt;
	private OffsetDateTime updatedAt;
	private AddressOutputDto address;
	private KitchenOutputDto kitchen;
	private List<FormOfPaymentOutputDto> formOfPayments;
}
