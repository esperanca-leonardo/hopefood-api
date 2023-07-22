package com.esperanca.hopefood.api.errors;

import lombok.Builder;

@Builder
public record Field(String name, String message) { }
