package com.esperanca.hopefood.core.assemblers;

import java.util.List;

public interface Assembler<Input, Output> {

	Output toModel(Input input);

	List<Output> toCollectionModel(List<Input> inputs);
}
