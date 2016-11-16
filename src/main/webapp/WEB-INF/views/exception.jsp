<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>错误</title>

    <link href="/static/vendors/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="/static/vendors/animate/animate.min.css" rel="stylesheet">
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
                    <p>
                    <h2>${exception.message} - <a href="javascript:history.go(-1);">返回</a> - <a class="more"
                                                                                              href="javascript:void(0)">查看更多</a>
                    </h2>
                    <div class="hidden">
                        ${exception.stackTrace}
                    </div>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="/static/vendors/jquery/jquery.min.js"></script>
<script type="application/javascript">
    $(document).ready(function () {
        $('.more').click(function () {
            $('.hidden').show();
        })
    });
</script>
</body>
</html>

