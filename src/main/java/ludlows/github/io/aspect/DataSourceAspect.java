package ludlows.github.io.aspect;

import ludlows.github.io.common.DataSourceContextHolder;
import ludlows.github.io.common.DataSourceEnum;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import ludlows.github.io.annotation.DataSourceTypeAnnotation;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Component
@Aspect
public class DataSourceAspect {
    @Pointcut("@annotation(ludlows.github.io.annotation.DataSourceTypeAnnotation)")
    public void dataSourcePointcut() {
    }

    @Around("dataSourcePointcut()")
    public Object doAround(ProceedingJoinPoint pjp) {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        DataSourceTypeAnnotation typeAnno = method.getAnnotation(DataSourceTypeAnnotation.class);
        DataSourceEnum sourceEnum = typeAnno.value();

        if (sourceEnum == DataSourceEnum.master) {
            DataSourceContextHolder.setDataSourceType(DataSourceEnum.master);
        } else if (sourceEnum == DataSourceEnum.slaver) {
            DataSourceContextHolder.setDataSourceType(DataSourceEnum.slaver);
        }else{
            DataSourceContextHolder.setDataSourceType(DataSourceEnum.master);
        }

        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            DataSourceContextHolder.resetDataSourceType();
        }

        return result;
    }
}