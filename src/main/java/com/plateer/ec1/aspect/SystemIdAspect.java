package com.plateer.ec1.aspect;

import com.plateer.ec1.common.model.BaseModel;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
@Slf4j
public class SystemIdAspect {

    @Before("execution(* com.plateer.ec1..*Trx*..*(..))")
    private void beforeCUD(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if(args != null && args.length > 0) {
            for(Object arg : args) {
                if(arg instanceof BaseModel) {
                    ((BaseModel) arg).setSysModrId("admin");
                    ((BaseModel) arg).setSysRegrId("admin");
                }
            }
        }
    }
}
