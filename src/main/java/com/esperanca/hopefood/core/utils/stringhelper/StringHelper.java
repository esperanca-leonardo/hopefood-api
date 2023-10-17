package com.esperanca.hopefood.core.utils.stringhelper;

import java.util.Optional;

public interface StringHelper {

	Optional<String> extractRegex(String text, String regex);

	String removeSuffix(String text, String suffix) throws IllegalArgumentException;
}
