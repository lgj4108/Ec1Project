package com.plateer.ec1.order.service.impl;

import com.google.gson.Gson;
import com.plateer.ec1.common.model.order.OpOrdClmMntLog;
import com.plateer.ec1.order.mapper.OrderTrxMapper;
import com.plateer.ec1.order.service.OrderHistoryService;
import com.plateer.ec1.order.vo.OrderClaimBaseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderHistoryServiceImpl implements OrderHistoryService {

    private final OrderTrxMapper orderTrxMapper;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, noRollbackFor = Throwable.class)
    public OpOrdClmMntLog insertOrderHistory(Object args) {
        OpOrdClmMntLog logModel = new OpOrdClmMntLog();
        try {
            logModel = getInsertLogModel(args);
            orderTrxMapper.insertOrderHistory(logModel);
        } catch (Exception ex) {
            log.info("Ex: {}", ex);
        }

        return logModel;
    }

    private OpOrdClmMntLog getInsertLogModel(Object args) {
        OpOrdClmMntLog model = new OpOrdClmMntLog();
        OrderClaimBaseVO base = (OrderClaimBaseVO)args;
        model.setOrdNo(base.getOrdNo());
        model.setClmNo(base.getOrdNo());
        model.setReqPram(new Gson().toJson(args));

        return model;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, noRollbackFor = Throwable.class)
    public void updateOrderHistory(OpOrdClmMntLog logModel, Object result, Exception exceptionHolder) {
        try {
            orderTrxMapper.updateOrderHistory(getUpdateLogModel(logModel, result, exceptionHolder));
        } catch(Exception ex) {
            log.info("update monitoring Ex: {}", ex);
        }
    }

    private OpOrdClmMntLog getUpdateLogModel(OpOrdClmMntLog logModel, Object result, Exception exceptionHolder) {
        logModel.setInsData(new Gson().toJson(result));
        if(exceptionHolder != null) {
            // 실패코드 exception 종류 별로 넣어주기.
        }

        return logModel;
    }
}
