
package org.chris.common.mq.mover.web;

import org.chris.common.mq.mover.domain.MoveInfo;
import org.chris.common.mq.mover.service.impl.CommonMoverServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author caizq
 * @date 2018/4/20
 * @since v1.0.0
 */
@Scope("prototype")
@RestController
public class MoverController {
    @Autowired
    private CommonMoverServiceImpl commonMoverService;
    @RequestMapping("/move")
    public void move(@RequestBody MoveInfo moveInfo) throws Exception {
        commonMoverService.moveMsg(moveInfo);
    }
}
