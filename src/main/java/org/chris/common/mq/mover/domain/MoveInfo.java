
package org.chris.common.mq.mover.domain;

import lombok.Data;

/**
 * @author chris-cai
 */
@Data
public class MoveInfo {
    private String queue;
    private String toExchange;
    private String toRoutingKey;
    private int moveNum;
}
