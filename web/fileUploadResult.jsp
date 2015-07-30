<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0"/>
</head>
<body>
<h1>查看 apk 签名、包名</h1>

<h2>
    MD5 : <s:property value="md5"/>
</h2>

<h2>
    Manifest 声明的包名 : <s:property value="manifestPackage"/>
</h2>

<h2>
    启动 activity 的包名 : <s:property value="launchActivityPackage"/>
</h2>

</body>
</html>