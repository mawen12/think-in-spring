package com.mawen.think.in.spring.condition.condition;

import com.mawen.think.in.spring.condition.annotation.MetaConditional;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2023/2/11
 */
public class MetaConditionalFilter implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(MetaConditional.class.getName()));
        return attributes != null && attributes.getString("value").equals("test");
    }
}
