package com.esperanca.hopefood.core.interfaces.operations;

public interface Update<ID, Input, Output> {

	Output update(ID ID, Input input);
}
