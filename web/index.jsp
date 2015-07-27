<%--
  Created by IntelliJ IDEA.
  User: sunkai
  Date: 7/8/15
  Time: 5:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0"/>
    <title>壹收款演示版</title>
    <style type="text/css">
        #title {
            margin-left: auto;
            margin-right: auto;
            font-size: 21px;
            text-align: center;
        }

        #button {
            margin-left: auto;
            margin-right: auto;
            margin-top: 300px;
            text-align: center;
            background-color: #999999;
            width: 200px;
            height: 50px;
            line-height: 50px;
        }

        #button:hover {
            background-color: #bbbbbb;
        }
    </style>
    <script type="text/javascript">
        if (0< location.href.indexOf("localhost")) {
            location.href = "<%=request.getScheme()+"://"+request.getLocalAddr()+":"+request.getLocalPort()%>";
        }
    </script>
</head>
<body>
<div id="title">壹收款演示
</div>
<div id="button" onclick="dopay()">你丫点我啊~</div>
<script type="text/javascript" src="https://one.pingxx.com/lib/pingpp_one.js"></script>
<script type="text/javascript">
    function dopay() {
        pingpp_one.init({
            app_id: 'app_1Gqj58ynP0mHeX1q',               // 该应用在 Ping++ 的应用ID
            order_no: 'no1234567890',                     // 订单在商户系统中的订单号
            amount: 1,                                   // 订单价格，单位：人民币 分
            channel: ['alipay_wap', 'wx_pub', 'upacp_wap', 'yeepay_wap', 'jdpay_wap', 'bfb_wap'],  // 壹收款页面上需要展示的渠道，数组，数组顺序即页面展示出的渠道的顺序
            charge_url: ' <%=request.getScheme()+"://"+request.getLocalAddr()+":"+request.getLocalPort()+"/pay"%>',  // 商户服务端创建订单的url
            charge_param: {a: 1, b: 2},                      //(可选，用户自定义参数，若存在自定义参数则壹收款会通过 POST 方法透传给 charge_url)
            open_id: 'OPEN_ID'       // (可选，使用微信公众号支付时必须传入)
        }, function (res) {
            if (!res.status) {
                alert(res.msg); // 处理错误
            }
        });
    }
</script>
</body>
</html>
