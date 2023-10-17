package com.esperanca.hopefood.core.utils.propertycopier;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PropertyCopierImpl implements PropertyCopier {

	@Override
	public void copyProperties(Object source, Object target) {
		BeanUtils.copyProperties(source, target);
	}
}
