package org.chris.common.mq.mover.service;

import org.chris.common.mq.mover.domain.MoveInfo;

/**
 * @author caizq
 * @date 2018/4/8
 * @since v1.0.0
 */
public interface IMoveService {
    /**
     * 转移消息
     *
     * @param moveInfo
     * @throws Exception
     */
    void moveMsg(MoveInfo moveInfo) throws Exception;
}
