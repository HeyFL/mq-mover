package org.chris.common.mq.mover.service;

import org.chris.common.mq.mover.domain.MoveInfo;


public interface MoveService {
    void move(MoveInfo moveInfo) throws Exception;
}
