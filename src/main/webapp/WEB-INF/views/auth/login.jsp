<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登录</title>
    <jsp:include page="../include/head.jsp"/>
</head>

<body class="login">
<div>
    <div class="login_wrapper">
        <div class="animate form login_form">
            <section class="login_content">
                <form id="form-login">
                    <h1>登录系统</h1>
                    <div>
                        <input name="next" type="hidden" value="${param.next}">
                        <input name="username" type="text" class="form-control" placeholder="Username" required=""/>
                    </div>
                    <div>
                        <input name="password" type="password" class="form-control" placeholder="Password" required=""/>
                    </div>
                    <div>
                        <button class="btn btn-default submit" id="btn-login">登录</button>
                    </div>
                    <div class="clearfix"></div>
                    <div class="separator">
                        <div>
                            <h1><i class="fa fa-paw" style="font-size: 26px;"></i> Basic Auth!</h1>
                            <p>©2016 All Rights Reserved.</p>
                        </div>
                    </div>
                </form>
            </section>
        </div>
    </div>
</div>
<jsp:include page="../include/script.jsp"/>

<script type="application/javascript">
    $(document).ready(function () {
        $('#btn-login').click(function (e) {
            e.preventDefault();
            $.ajax({
                method: 'POST',
                url: '/login',
                data: $('#form-login').serialize(),
                dataType: 'json',
                success: function (data) {
                    window.location.href = data.next;
                },
                error: function (data) {
                    notie.alert(3, data.responseJSON.message, 2.5);
                }
            });
        });
    });
</script>
</body>
</html>
