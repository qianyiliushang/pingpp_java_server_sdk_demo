<%--
  Created by IntelliJ IDEA.
  User: sunkai
  Date: 7/22/15
  Time: 11:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv=”Access-Control-Allow-Origin” content=”*”>
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
</head>
<body>
<div id="title">壹收款 wap 支付结果展示页面
</div>
<div id="button" onclick="goback()">返回</div>
<script type="text/javascript">
    function goback() {
        location.href = "<%=request.getScheme()+"://"+request.getLocalAddr()+":"+request.getLocalPort()%>";
    }
</script>
</body>
</html>
