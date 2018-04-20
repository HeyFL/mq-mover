
package org.chris.common.mq.mover.domain;

import lombok.Data;

@Data
public class MoveInfo {
    private String queue;
    private String toExchange;
    private String toRoutingKey;
    private int moveNum;
}
