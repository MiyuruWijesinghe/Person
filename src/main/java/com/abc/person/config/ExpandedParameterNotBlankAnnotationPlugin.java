package com.abc.person.config;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Optional;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.bean.validators.plugins.Validators;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;

import javax.validation.constraints.NotBlank;

import static springfox.bean.validators.plugins.Validators.annotationFromBean;
import static springfox.bean.validators.plugins.Validators.annotationFromField;
@Component
@Order(Validators.BEAN_VALIDATOR_PLUGIN_ORDER)
public class ExpandedParameterNotBlankAnnotationPlugin implements ModelPropertyBuilderPlugin{

	 @Override
	    public boolean supports(DocumentationType delimiter) {
	        return true;
	    }

	    @Override
	    public void apply(ModelPropertyContext context) {
	        Optional<NotBlank> notBlank = extractAnnotation(context);
	        if (notBlank.isPresent()) {
	            context.getBuilder().required(true);
	        }
	    }

	    @VisibleForTesting
	    Optional<NotBlank> extractAnnotation(ModelPropertyContext context) {
	        return annotationFromBean(context, NotBlank.class).or(annotationFromField(context, NotBlank.class));
	    }
}
