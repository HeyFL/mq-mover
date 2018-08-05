package org.chris.common.mq.mover.service;

import org.chris.common.mq.mover.domain.MoveInfo;


public interface MoveService {
    void moveMsg(MoveInfo moveInfo) throws Exception;
}
