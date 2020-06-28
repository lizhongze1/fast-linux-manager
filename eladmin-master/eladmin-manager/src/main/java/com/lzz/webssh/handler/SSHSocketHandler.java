package com.lzz.webssh.handler;

import com.lzz.webssh.service.SSHService;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.consts.BizConsts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

/**
 *
 * 　  * @className: WebSocketInterceptor
 * 　　* @description:TODO
 * 　　* @param:
 * 　　* @return:
 * 　　* @throws:
 * 　　* @author: lizz
 * 　　* @date: 2020/06/28 15:11
 *
 */
@Component
@Slf4j
public class SSHSocketHandler implements WebSocketHandler {

    @Autowired
    private SSHService sshService;
    /**
     * @Description: 用户连接上WebSocket的回调
     * @Author: lizz
     * @Date: 2020/6/28 15:15
     * @Reviser:修改人
     * @ReviseDate:修改时间
     * @Revision:修改内容
     **/
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        log.info("用户:{},连接WebSSH", webSocketSession.getAttributes().get(BizConsts.USER_UUID_KEY));
        //调用初始化连接
        sshService.initConnection(webSocketSession);
    }
    /**
     * @Description: 收到消息的回调
     * @Author: lizz
     * @Date: 2020/6/28 15:15
     * @Reviser:修改人
     * @ReviseDate:修改时间
     * @Revision:修改内容
     **/
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        if (webSocketMessage instanceof TextMessage) {
            log.info("用户:{},发送命令:{}", webSocketSession.getAttributes().get(BizConsts.USER_UUID_KEY), webSocketMessage.toString());
            //调用service接收消息
            sshService.recvHandle(((TextMessage) webSocketMessage).getPayload(), webSocketSession);
        } else if (webSocketMessage instanceof BinaryMessage) {

        } else if (webSocketMessage instanceof PongMessage) {

        } else {
            System.out.println("Unexpected WebSocket message type: " + webSocketMessage);
        }
    }
    /**
     * @Description: 出现错误的回调
     * @Author: lizz
     * @Date: 2020/6/28 15:15
     * @Reviser:修改人
     * @ReviseDate:修改时间
     * @Revision:修改内容
     **/
    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        log.error("数据传输错误");
    }
    /**
     * @Description: close
     * @Author: lizz
     * @Date: 2020/6/28 15:16
     * @Reviser:修改人
     * @ReviseDate:修改时间
     * @Revision:修改内容
     **/
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        log.info("用户:{}断开webssh连接", String.valueOf(webSocketSession.getAttributes().get(BizConsts.USER_UUID_KEY)));
        //调用service关闭连接
        sshService.close(webSocketSession);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
