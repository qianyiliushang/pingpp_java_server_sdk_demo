package com.sunkai.demo;


import com.opensymphony.xwork2.ActionSupport;
import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.PingppException;
import com.pingplusplus.model.Charge;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sunkai on 15/4/16.
 * 该实例程序演示如何使用 ping++ server sdk 获取 Charge
 * <p/>
 * 需要使用者填写自己的 apikey 和 appid
 */
public class ChargeAction extends ActionSupport {
    private static final Logger logger = Logger.getLogger(ChargeAction.class);
    // request parameters
    private int amount;
    private String order_no;
    private String channel;
    private Map extras;

    //response parameters
    private Charge charge;


    // your apikey and appId
    public static final String APPID = "app_1Gqj58ynP0mHeX1q";
    public static final String APIKEY = "sk_test_ibbTe5jLGCi5rzfH4OqPW9KC";

    public String pay() {
        HttpServletRequest request = ServletActionContext.getRequest();
        logger.debug("receive parameters: " + getAmount() + " , " + getChannel() + " , " + getOrder_no() + ", " + getExtras());
        logger.debug("request ip:" + request.getRemoteHost());

        System.out.println("receive parameters: " + getAmount() + " , " + getChannel() + " , " + getOrder_no() + ", " + getExtras());
        System.out.println("request ip:" + request.getRemoteHost());
        charge(request);
        return "success";
    }

    public void charge(HttpServletRequest request) {
        Pingpp.apiKey = APIKEY;

        String orderNo = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        Map<String, Object> chargeMap = new HashMap<String, Object>();
        chargeMap.put("amount", 1);
        chargeMap.put("currency", "cny");
        chargeMap.put("subject", channel + "渠道测试");
        chargeMap.put("body", "测试");
        chargeMap.put("order_no", orderNo);
        chargeMap.put("channel", channel);
        chargeMap.put("client_ip", request.getLocalAddr());
        Map<String, String> app = new HashMap<String, String>();
        app.put("id", APPID);
        chargeMap.put("app", app);

        // some channel request more parameters
        Map<String, Object> extra = new HashMap<String, Object>();
        String resultUrl = request.getScheme() + "://" + request.getLocalAddr()+":"+ request.getLocalPort() + "/result.jsp";
        switch (channel) {
            case "alipay_wap": {
                extra.put("success_url", resultUrl);
                extra.put("cancel_url", resultUrl);
                chargeMap.put("extra", extra);
            }
            break;
            case "wx_pub": {
                extra.put("open_id", "7");
                chargeMap.put("extra", extra);
            }
            break;
            case "upacp_wap": {
                extra.put("result_url", resultUrl);
                chargeMap.put("extra", extra);
            }
            break;
            case "yeepay_wap": {
                //在这里填写易宝支付的参数
//                extra.put("product_category", "");
//                extra.put("identity_id", "");
//                extra.put("identity_type", "");
//                extra.put("terminal_type", "");
//                extra.put("terminal_id", "");
//                extra.put("user_ua", "");
//                extra.put("result_url", resultUrl);
//                chargeMap.put("extra", extra);
            }
            break;
            case "jdpay_wap": {
                extra.put("success_url", resultUrl);
                extra.put("fail_url", resultUrl);
                chargeMap.put("extra", extra);
            }
            break;
            case "bfb_wap": {
                extra.put("bfb_login", false);
                extra.put("result_url", resultUrl);
                chargeMap.put("extra", extra);
            }


        }

        try {
            charge = Charge.create(chargeMap);
        } catch (PingppException e) {
            e.printStackTrace();
        }

    }


    public Charge getCharge() {
        return charge;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }


    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Map getExtras() {
        return extras;
    }

    public void setExtras(Map extras) {
        this.extras = extras;
    }

}
