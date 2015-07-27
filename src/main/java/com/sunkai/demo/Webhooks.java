package com.sunkai.demo;

import com.opensymphony.xwork2.ActionSupport;
import com.pingplusplus.model.Event;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by sunkai on 7/23/15.
 * <p/>
 * 该示例为 webhooks 获取异步通知并进行签名验证
 */
public class Webhooks extends ActionSupport {
    private static final Logger logger = Logger.getLogger(Webhooks.class);

    public String webhook() {

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        String signature = request.getHeader("x-pingplusplus-signature");
        logger.debug("receiver x-pingplusplus-signature: " + signature);
        System.out.println("receiver x-pingplusplus-signature: " + signature);
        StringBuffer stringBuffer = new StringBuffer();
        try {
            BufferedReader bufferedReader = request.getReader();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.debug("receive event: " + stringBuffer.toString());
        System.out.println("receive event: " + stringBuffer.toString());
        ServletContext servletContext = request.getServletContext();
        String pubKeyPath = servletContext.getRealPath("/") + "/WEB-INF/";

        byte[] bytes1= stringBuffer.toString().getBytes();
        byte[] bytes2 =Base64.decodeBase64(signature.getBytes());

        logger.debug("pubKeyPath: " + pubKeyPath);
        System.out.println("pubKeyPath: " + pubKeyPath);

        boolean signatureResult;
        try {
            signatureResult = WebHooksVerifyUtil.verifyData(bytes1, bytes2, WebHooksVerifyUtil.getPubKey(pubKeyPath + "my-server.pub"));
        } catch (Exception e) {
            signatureResult = false;
        }

        logger.debug("signatureResult: " + signatureResult);
        System.out.println("signatureResult: " + signatureResult);
        // 解析异步通知数据
        Event event = com.pingplusplus.model.Webhooks.eventParse(stringBuffer.toString());
        logger.debug("event.type: " + event.getType());
        System.out.println("event.type: " + event.getType());
        if ("charge.succeeded".equals(event.getType())) {
            response.setStatus(200);
        } else if ("refund.succeeded".equals(event.getType())) {
            response.setStatus(200);
        } else {
            response.setStatus(200);
        }

        return "success";
    }
}
