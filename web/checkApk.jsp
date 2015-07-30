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
<h1>上传 apk 文件</h1>

<s:form action="upload" namespace="/"
        method="POST" enctype="multipart/form-data">

    <s:file name="fileUpload" label="上传 apk "/>

    <s:submit value="submit" name="submit"/>

</s:form>

</body>
</html>
