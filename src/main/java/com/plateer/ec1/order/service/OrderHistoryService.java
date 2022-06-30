package com.plateer.ec1.order.service;

import com.plateer.ec1.common.model.order.OpOrdClmMntLog;

public interface OrderHistoryService {
    OpOrdClmMntLog insertOrderHistory(Object log);

    void updateOrderHistory(OpOrdClmMntLog logModel, Object result, Exception exceptionHolder);
}
