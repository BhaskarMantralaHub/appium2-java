package config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark configuration properties.
 * Can be used on fields, methods, or classes to indicate configuration requirements.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
public @interface Config {
    /**
     * The configuration key to use.
     * @return the configuration key
     */
    String key() default "";
    
    /**
     * The default value to use if the configuration key is not found.
     * @return the default value
     */
    String defaultValue() default "";
    
    /**
     * Whether this configuration is required.
     * @return true if the configuration is required, false otherwise
     */
    boolean required() default false;
    
    /**
     * Description of the configuration property.
     * @return the description
     */
    String description() default "";
}
