<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../include/head.jsp"/>
    <title>个人信息设置</title>
</head>
<body class="nav-md">

<div class="container body">
    <div class="main_container">
        <jsp:include page="/nav"/>

        <div class="right_col" role="main">
            <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                    <div class="x_panel">
                        <div class="x_title">
                            <h2>设置个人信息</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <form id="form-settings" action="/user/settings" method="POST" data-parsley-validate
                                  class="form-horizontal form-label-left">
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="username">用户名
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input name="display_name" type="text" id="username" disabled="disabled"
                                               class="form-control col-md-7 col-xs-12" value="${user.username}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="display-name">姓名
                                        <span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input name="display_name" type="text" id="display-name" required="required"
                                               class="form-control col-md-7 col-xs-12" value="${user.displayName}">
                                    </div>
                                </div>
                                <div class="divider-dashed"></div>
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12">修改密码
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <div class="checkbox">
                                            <label>
                                                <input id="check-change-password" name="change_password" type="checkbox"
                                                       class="flat" value="true">
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group password-area hidden">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="origin-password">原密码
                                        <span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input type="password" id="origin-password" name="origin_password"
                                               class="form-control col-md-7 col-xs-12 input-password">
                                    </div>
                                </div>
                                <div class="form-group password-area hidden">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="password">密码
                                        <span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input type="password" id="password" name="password"
                                               class="form-control col-md-7 col-xs-12 input-password">
                                    </div>
                                </div>
                                <div class="form-group password-area hidden">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="repassword">重复密码
                                        <span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input type="password" id="repassword" name="repassword"
                                               class="form-control col-md-7 col-xs-12 input-password">
                                    </div>
                                </div>
                                <div class="ln_solid"></div>
                                <div class="form-group">
                                    <div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
                                        <button id="btn-settings" type="submit" class="btn btn-success">修改</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="../include/footer.jsp"/>
    </div>
</div>
<jsp:include page="../include/script.jsp"/>
<script type="text/javascript" src="/resources/js/icheck/icheck.min.js"></script>
<script type="text/javascript" src="/resources/js/parsley/parsley.min.js"></script>
<script type="text/javascript" src="/resources/js/parsley/zh_cn.js"></script>
<script type="application/javascript">
    $(document).ready(function () {
        $('#check-change-password').on('ifChecked', function () {
            $('.password-area').removeClass('hidden');
            $('.input-password').attr('required', 'required');
        });
        $('#check-change-password').on('ifUnchecked', function () {
            $('.password-area').addClass('hidden');
            $('.input-password').removeAttr('required');
        });
        $('#btn-settings').click(function (e) {
            e.preventDefault();
            console.log($('#form-settings').serialize());
            $('#form-settings').parsley().validate();
            if ($('#form-settings').parsley().isValid()) {
                $('#form-settings').submit();
            }
        });
    });
</script>
</body>
</html>
