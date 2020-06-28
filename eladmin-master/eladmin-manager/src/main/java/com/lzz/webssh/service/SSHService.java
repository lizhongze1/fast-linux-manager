package com.lzz.webssh.service;

import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

/**
 *
 * 　  * @className: SSHService
 * 　　* @description:TODO
 * 　　* @param:
 * 　　* @return:
 * 　　* @throws:
 * 　　* @author: lizz
 * 　　* @date: 2020/06/28 14:43
 *
 */
public interface SSHService {
    /**
     * @Description: 初始化ssh连接
     * @Author: lizz
     * @Date: 2020/6/28 14:44
     * @Reviser:修改人
     * @ReviseDate:修改时间
     * @Revision:修改内容
     **/
    public void initConnection(WebSocketSession session);

    /**
     * @Description: 处理客户段发的数据
     * @Author: lizz
     * @Date: 2020/6/28 14:44
     * @Reviser:修改人
     * @ReviseDate:修改时间
     * @Revision:修改内容
     **/
    public void recvHandle(String buffer, WebSocketSession session);

    /**
     * @Description: 数据写回前端
     * @Author: lizz
     * @Date: 2020/6/28 14:44
     * @Reviser:修改人
     * @ReviseDate:修改时间
     * @Revision:修改内容
     **/
    public void sendMessage(WebSocketSession session, byte[] buffer) throws IOException;
    /**
     * @Description: 关闭连接
     * @Author: lizz
     * @Date: 2020/6/28 14:44
     * @Reviser:修改人
     * @ReviseDate:修改时间
     * @Revision:修改内容
     **/
    public void close(WebSocketSession session);
}
