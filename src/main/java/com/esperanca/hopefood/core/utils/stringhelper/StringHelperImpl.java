package com.esperanca.hopefood.core.utils.stringhelper;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Optional.empty;
import static java.util.Optional.of;

@Component
public class StringHelperImpl implements StringHelper {


	@Override
	public Optional<String> extractRegex(String text, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);

		if (matcher.find()) {
			return of(matcher.group(1));
		}
		return empty();
	}

	@Override
	public String removeSuffix(String text, String suffix)
			throws IllegalArgumentException {

		final var messageError = "The suffix is not present in the text";

		if (!text.endsWith(suffix)) {
			throw new IllegalArgumentException(messageError);
		}
		final int lengthWithoutSuffix = text.length() - suffix.length();
		return text.substring(0, lengthWithoutSuffix);
	}
}
