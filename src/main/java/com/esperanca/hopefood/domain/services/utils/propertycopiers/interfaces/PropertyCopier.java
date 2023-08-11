package com.esperanca.hopefood.domain.services.utils.propertycopiers.interfaces;

public interface PropertyCopier<Source, Target> {
	void copyProperties(Source source, Target target);
}
