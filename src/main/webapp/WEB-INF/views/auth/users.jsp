<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pages" uri="http://ququzone.github.com/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../include/head.jsp"/>
    <title>用户管理</title>
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
                            <h2>用户列表</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <pages:a pattern="/user" method="POST">
                                <div class="pull-right">
                                    <button id="btn-add" class="btn btn-success"><span
                                            class="fa fa-plus-circle"></span> 新增
                                    </button>
                                </div>
                            </pages:a>
                            <table class="table table-striped table-bordered dataTable no-footer">
                                <thead>
                                <tr>
                                    <th>用户名</th>
                                    <th>姓名</th>
                                    <th>状态</th>
                                    <th>加入时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="user" items="${users.data}">
                                    <tr>
                                        <td>${user.username}</td>
                                        <td>${user.displayName}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${user.status == 'NORMAL'}">正常</c:when>
                                                <c:when test="${user.status == 'DISABLE'}">冻结</c:when>
                                                <c:otherwise>未知</c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td><fmt:formatDate value="${user.createdTime}"
                                                            pattern="yyyy-MM-dd HH:mm"/></td>
                                        <td>
                                            <button class="btn btn-sm btn-primary btn-edit" data-id="${user.id}"><span
                                                    class="fa fa-edit"></span> 编辑
                                            </button>
                                            <c:if test="${user.status == 'NORMAL'}">
                                                <pages:a pattern="/user/{user.id}/disable" method="POST">
                                                    <button class="btn btn-sm btn-danger btn-disable"
                                                            data-id="${user.id}"><span
                                                            class="fa fa-lock"></span>
                                                        禁用
                                                    </button>
                                                </pages:a>
                                                <pages:a pattern="/user/{user.id}/roles" method="POST">
                                                    <button class="btn btn-sm btn-info btn-assign_role"
                                                            data-id="${user.id}"
                                                            data-name="${user.displayName}:${user.username}"><span
                                                            class="fa fa-users"></span>
                                                        分配角色
                                                    </button>
                                                </pages:a>
                                            </c:if>
                                            <c:if test="${user.status == 'DISABLE'}">
                                                <pages:a pattern="/user/{user.id}/enable" method="POST">
                                                    <button class="btn btn-sm btn-info btn-enable"
                                                            data-id="${user.id}"><span
                                                            class="fa fa-unlock"></span>
                                                        激活
                                                    </button>
                                                </pages:a>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <pages:p current="${users.current}" totalPage="${users.totalPage}"
                                     baseUrl="/users"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="../include/footer.jsp"/>
    </div>
</div>
<div class="modal fade" id="modal-add" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">新增用户</h4>
            </div>
            <div class="modal-body">
                <form id="form-add" class="form-horizontal form-label-left">
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="username">用户名
                            <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input name="username" type="text" id="username" required="required"
                                   class="form-control col-md-7 col-xs-12">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="display-name">姓名
                            <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input name="display_name" type="text" id="display-name" required="required"
                                   class="form-control col-md-7 col-xs-12">
                        </div>
                    </div>
                    <div class="form-group password-area">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="password">密码
                            <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input type="password" id="password" name="password" required="required"
                                   class="form-control col-md-7 col-xs-12 input-password">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="btn-add-submit" type="button" class="btn btn-primary"><span
                        class="fa fa-plus-circle"></span> 新增
                </button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="modal-edit" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑用户</h4>
            </div>
            <div class="modal-body">
                <form id="form-edit" class="form-horizontal form-label-left">
                    <input type="hidden" name="_method" value="PUT">
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">用户名
                            <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input name="username" type="text" id="edit-username" required="required"
                                   class="form-control col-md-7 col-xs-12">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">姓名
                            <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input name="display_name" type="text" id="edit-display_name" required="required"
                                   class="form-control col-md-7 col-xs-12">
                        </div>
                    </div>
                    <div class="form-group password-area">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">密码
                            <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input type="password" name="password" id="edit-password" required="required"
                                   class="form-control col-md-7 col-xs-12 input-password">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="btn-edit-submit" type="button" class="btn btn-primary"><span
                        class="fa fa-edit"></span> 编辑
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-roles" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">分配角色 - <span id="role-username"></span></h4>
            </div>
            <div class="modal-body">
                <form id="form-user-roles">
                </form>
            </div>
            <div class="modal-footer">
                <button id="btn-roles-submit" type="button" class="btn btn-primary"><span
                        class="fa fa-users"></span> 分配
                </button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../include/script.jsp"/>
<script type="text/javascript" src="/static/js/parsley/parsley.min.js"></script>
<script type="text/javascript" src="/static/js/parsley/zh_cn.js"></script>
<script type="text/javascript" src="/static/js/lodash.min.js"></script>
<script type="application/javascript">
    $(document).ready(function () {
        $('#btn-add').click(function () {
            $('#modal-add').modal('toggle');
        });
        $('#btn-add-submit').click(function (e) {
            e.preventDefault();
            $('#form-add').parsley().validate();
            if ($('#form-add').parsley().isValid()) {
                $.ajax({
                    url: '/user',
                    method: 'POST',
                    dataType: 'json',
                    data: $('#form-add').serialize(),
                    success: function () {
                        window.location.reload();
                    },
                    error: function (data) {
                        notie.alert(3, data.responseJSON.message, 2.5);
                    }
                });
            }
        });
        $('.btn-edit').click(function (e) {
            e.preventDefault();
            var id = $(this).attr('data-id');
            $.ajax({
                url: '/user/' + id,
                dataType: 'json',
                success: function (data) {
                    $('#edit-username').val(data.username);
                    $('#edit-display_name').val(data.displayName);
                    $('#btn-edit-submit').attr('data-id', id);
                    $('#modal-edit').modal('toggle');
                },
                error: function () {
                    notie.alert(3, '获取用户信息异常', 2.5);
                }
            });
        });
        $('#btn-edit-submit').click(function (e) {
            e.preventDefault();
            var id = $(this).attr('data-id');
            $('#form-edit').parsley().validate();
            if ($('#form-edit').parsley().isValid()) {
                $.ajax({
                    url: '/user/' + id,
                    method: 'POST',
                    dataType: 'json',
                    data: $('#form-edit').serialize(),
                    success: function () {
                        window.location.reload();
                    },
                    error: function (data) {
                        notie.alert(3, data.responseJSON.message, 2.5);
                    }
                });
            }
        });
        $('.btn-disable').click(function (e) {
            e.preventDefault();
            var id = $(this).attr('data-id');
            $.ajax({
                url: '/user/' + id + '/disable',
                method: 'POST',
                success: function () {
                    window.location.reload();
                }
            });
        });
        $('.btn-enable').click(function (e) {
            e.preventDefault();
            var id = $(this).attr('data-id');
            $.ajax({
                url: '/user/' + id + '/enable',
                method: 'POST',
                success: function () {
                    window.location.reload();
                }
            });
        });
        $('.btn-assign_role').click(function () {
            var id = $(this).attr('data-id');
            var name = $(this).attr('data-name');
            $.ajax({
                url: '/user/' + id + '/roles',
                method: 'GET',
                dataType: 'json',
                success: function (data) {
                    var html = '';
                    _.each(data, function (role) {
                        html += '<div class="col-md-6 col-sm-6 col-xs-12">'
                                + '<div class="checkbox">'
                                + '<label>'
                                + '<input name="roles" type="checkbox" class="flat"';
                        if (role.checked) {
                            html += ' checked="checked"';
                        }
                        html += ' value="' + role.id + '"> ' + role.name
                                + '</label>'
                                + '</div>'
                                + '</div>';
                    });
                    $('#btn-roles-submit').attr('data-id', id);
                    $('#role-username').html(name);
                    $('#form-user-roles').html(html);
                    $('#modal-roles').modal('toggle');
                }
            });
        });
        $('#btn-roles-submit').click(function () {
            var id = $(this).attr('data-id');
            $.ajax({
                url: '/user/' + id + '/roles',
                data: $('#form-user-roles').serialize(),
                method: 'POST',
                success: function () {
                    $('#modal-roles').modal('hide');
                    notie.alert(1, '角色分配成功', 2.5);
                }
            });
        });
    });
</script>
</body>
</html>
