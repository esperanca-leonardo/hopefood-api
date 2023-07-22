package com.esperanca.hopefood.api.errors;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record Error (Integer status, String type, String title, String detail) {}