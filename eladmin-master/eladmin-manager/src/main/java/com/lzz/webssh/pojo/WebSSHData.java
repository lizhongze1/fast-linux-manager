package com.lzz.webssh.pojo;

import lombok.Data;

/**
 *
 * 　  * @className: WebSSHData
 * 　　* @description:TODO
 * 　　* @param:
 * 　　* @return:
 * 　　* @throws:
 * 　　* @author: lizz
 * 　　* @date: 2020/06/28 14:54
 *
 */
@Data
public class WebSSHData {
    private String operate;
    private String host;
    //端口号默认为22
    private Integer port = 22;
    private String username;
    private String password;
    private String command = "";
}
