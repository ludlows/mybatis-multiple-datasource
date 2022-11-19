package ludlows.github.io.annotation;

import ludlows.github.io.common.DataSourceEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME) // Visible at runtime
@Target(ElementType.METHOD) // Notes can be used in methods
public @interface DataSourceTypeAnnotation {
    DataSourceEnum value() default DataSourceEnum.master;
}