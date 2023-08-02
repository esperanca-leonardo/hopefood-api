package com.esperanca.hopefood.core.dtos.formofpayments;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class FormOfPaymentOutputDto {
	private Long id;
	private String description;
	private OffsetDateTime createdAt;
	private OffsetDateTime updatedAt;
}
