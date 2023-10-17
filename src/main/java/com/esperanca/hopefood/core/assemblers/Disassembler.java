package com.esperanca.hopefood.core.assemblers;

public interface Disassembler<Input, Output> {

	Output toEntity(Input input);
}
