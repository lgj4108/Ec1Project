package com.plateer.ec1.aspect;

import com.google.gson.Gson;
import com.plateer.ec1.common.model.order.OpOrdClmMntLog;
import com.plateer.ec1.order.service.OrderHistoryService;
import com.plateer.ec1.order.vo.OrderClaimBaseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class OrderHistoryAspect {

    private final OrderHistoryService orderHistorySerivce;

    @Around("@annotation(com.plateer.ec1.aspect.annotation.OrderHistory) && args(args,..)")
    private Object orderHistory(ProceedingJoinPoint joinPoint, Object args) throws Throwable {
        log.info("args: {}", args);

        Object result = null;
        Exception exceptionHolder = null;

        OpOrdClmMntLog logModel = orderHistorySerivce.insertOrderHistory(args);

        try {
            result = joinPoint.proceed();

            return result;
        } catch (Exception ex) {
            exceptionHolder = ex;
            throw ex;
        } finally {
            orderHistorySerivce.updateOrderHistory(logModel, result, exceptionHolder);
        }
    }
}
