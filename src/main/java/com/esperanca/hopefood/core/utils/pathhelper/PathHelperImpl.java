package com.esperanca.hopefood.core.utils.pathhelper;

import org.springframework.stereotype.Component;

@Component
public class PathHelperImpl implements PathHelper {

	public static final String DOMAIN_PACKAGE = "com.esperanca.hopefood.domain";

	@Override
	public String getEntityPath(String className) {
		return String.format("%s.%s.entities.%s",
				DOMAIN_PACKAGE, className.toLowerCase(), className
		);
	}

	@Override
	public String getExceptionPath(String className, String suffix) {
		return String.format("%s.%s.exceptions.%s", DOMAIN_PACKAGE,
				className.toLowerCase(), className.concat(suffix)
		);
	}
}
