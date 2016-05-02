<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>错误 - 没有权限</title>

    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/fonts/css/font-awesome.min.css" rel="stylesheet">
    <link href="/static/css/animate.min.css" rel="stylesheet">
    <link href="/static/css/custom.css" rel="stylesheet">

    <!--[if lt IE 9]>
    <script src="/static/js/html5shiv.min.js"></script>
    <script src="/static/js/respond.min.js"></script>
    <![endif]-->
</head>
<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <div class="col-md-12">
            <div class="col-middle">
                <div class="text-center text-center">
                    <h1 class="error-number">错误</h1>
                    <h2>对不起，你没有访问页面 <c:if test="${not empty param.next}"><a href="${param.next}">${param.next}</a></c:if> 的权限</h2>
                    <p>请重新登录或者联系管理员确认权限！ <a href="/login">重新登录</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

