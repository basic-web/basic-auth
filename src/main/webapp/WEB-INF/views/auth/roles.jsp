<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pages" uri="http://ququzone.github.com/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../include/head.jsp"/>
    <title>角色管理</title>
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
                            <h2>角色列表</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <pages:a pattern="/role" method="POST">
                                <div class="pull-right">
                                    <button id="btn-add" class="btn btn-success"><span
                                            class="fa fa-plus-circle"></span> 新增
                                    </button>
                                </div>
                            </pages:a>
                            <table class="table table-striped table-bordered dataTable no-footer">
                                <thead>
                                <tr>
                                    <th>角色名称</th>
                                    <th>创建时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="role" items="${roles.data}">
                                    <tr>
                                        <td>${role.name}</td>
                                        <td><fmt:formatDate value="${role.createdTime}"
                                                            pattern="yyyy-MM-dd HH:mm"/></td>
                                        <td>
                                            <c:if test="${role.id != 'role_admin' and role.id != 'role_user'}">
                                                <pages:a pattern="/role/${role.id}" method="PUT">
                                                    <button class="btn btn-sm btn-primary btn-edit"
                                                            data-id="${role.id}"><span
                                                            class="fa fa-edit"></span> 编辑
                                                    </button>
                                                </pages:a>
                                                <pages:a pattern="/role/${role.id}" method="DELETE">
                                                    <button class="btn btn-sm btn-danger btn-delete"
                                                            data-id="${role.id}"><span
                                                            class="fa fa-trash"></span> 删除
                                                    </button>
                                                </pages:a>
                                            </c:if>
                                            <pages:a pattern="/role/${role.id}/resources" method="GET">
                                                <button class="btn btn-sm btn-info btn-view_resource"
                                                        data-id="${role.id}"
                                                        data-name="${role.name}"><span
                                                        class="fa fa-unlock-alt"></span> 查看资源
                                                </button>
                                            </pages:a>
                                            <pages:a pattern="/role/${role.id}/users" method="GET">
                                                <button class="btn btn-sm btn-info btn-view_user"
                                                        data-id="${role.id}"
                                                        data-name="${role.name}"><span
                                                        class="fa fa-users"></span> 查看用户
                                                </button>
                                            </pages:a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <pages:p current="${roles.current}" totalPage="${roles.totalPage}"
                                     baseUrl="/roles"/>
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
                <h4 class="modal-title">新增角色</h4>
            </div>
            <div class="modal-body">
                <form id="form-add" class="form-horizontal form-label-left">
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">角色名称
                            <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input name="name" type="text" required="required"
                                   class="form-control col-md-7 col-xs-12">
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
                <h4 class="modal-title">编辑角色</h4>
            </div>
            <div class="modal-body">
                <form id="form-edit" class="form-horizontal form-label-left">
                    <div class="form-group">
                        <input type="hidden" name="_method" value="PUT">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">角色名称
                            <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input name="name" type="text" required="required" id="edit-name"
                                   class="form-control col-md-7 col-xs-12">
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
<div class="modal fade" id="modal-resource" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">资源列表:<span id="resource-role-name"></span></h4>
            </div>
            <div class="modal-body" id="resources-area">
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="modal-user" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">用户列表:<span id="user-role-name"></span></h4>
            </div>
            <div class="modal-body" id="users-area">
            </div>
        </div>
    </div>
</div>
<jsp:include page="../include/script.jsp"/>
<script type="text/javascript" src="/static/vendors/parsleyjs/parsley.min.js"></script>
<script type="text/javascript" src="/static/vendors/parsleyjs/i18n/zh_cn.js"></script>
<script type="text/javascript" src="/static/vendors/bootstrap-confirmation/bootstrap-confirmation.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#btn-add').click(function () {
            $('#modal-add').modal('toggle');
        });
        $('#btn-add-submit').click(function (e) {
            e.preventDefault();
            $('#form-add').parsley().validate();
            if ($('#form-add').parsley().isValid()) {
                $.ajax({
                    url: '/role',
                    method: 'POST',
                    dataType: 'json',
                    data: $('#form-add').serialize(),
                    success: function () {
                        window.location.reload();
                    },
                    error: function (data) {
                        notie.alert(3, data.responseJSON.error, 2.5);
                    }
                });
            }
        });
        $('.btn-edit').click(function () {
            var id = $(this).attr('data-id');
            $.ajax({
                url: '/role/' + id,
                method: 'GET',
                dataType: 'json',
                success: function (data) {
                    $('#edit-name').val(data.name);
                    $('#btn-edit-submit').attr('data-id', id);
                    $('#modal-edit').modal('toggle');
                }
            });
        });
        $('#btn-edit-submit').click(function () {
            var id = $(this).attr('data-id');
            $('#form-edit').parsley().validate();
            if ($('#form-edit').parsley().isValid()) {
                $.ajax({
                    url: '/role/' + id,
                    method: 'POST',
                    dataType: 'json',
                    data: $('#form-edit').serialize(),
                    success: function () {
                        window.location.reload();
                    },
                    error: function (data) {
                        notie.alert(3, data.responseJSON.error, 2.5);
                    }
                });
            }
        });
        $('.btn-delete').confirmation({
            btnOkLabel: '删除',
            btnCancelLabel: '取消',
            onConfirm: function (event, element) {
                var id = $(element).attr('data-id');
                $.ajax({
                    url: '/role/' + id,
                    method: 'POST',
                    data: '_method=DELETE',
                    success: function () {
                        window.location.reload();
                    }
                });
            }
        });
        $('.btn-view_resource').click(function () {
            var id = $(this).attr('data-id');
            var name = $(this).attr('data-name');
            $.ajax({
                url: '/role/' + id + '/resources',
                method: 'GET',
                dataType: 'json',
                success: function (data) {
                    var html = '<table class="table table-striped table-bordered dataTable no-footer">';
                    for (var i = 0; i < parseInt(data.length / 2); i++) {
                        html += '<tr><td>' + data[i * 2].name + '</td><td>' + data[i * 2 + 1].name + '</td></tr>';
                    }
                    if (data.length % 2 !== 0) {
                        html += '<tr><td>' + data[data.length - 1].name + '</td><td>&nbsp;</td></tr>';
                    }
                    html += '</table>';
                    $('#resources-area').html(html);
                    $('#resource-role-name').html(name);
                    $('#modal-resource').modal('toggle');
                }
            });
        });
        $('.btn-view_user').click(function () {
            var id = $(this).attr('data-id');
            var name = $(this).attr('data-name');
            $.ajax({
                url: '/role/' + id + '/users',
                method: 'GET',
                dataType: 'json',
                success: function (data) {
                    var html = '<table class="table table-striped table-bordered dataTable no-footer">';
                    for (var i = 0; i < parseInt(data.length / 2); i++) {
                        html += '<tr><td>' + data[i * 2].username + '</td><td>' + data[i * 2 + 1].username + '</td></tr>';
                    }
                    if (data.length % 2 !== 0) {
                        html += '<tr><td>' + data[data.length - 1].username + '</td><td>&nbsp;</td></tr>';
                    }
                    html += '</table>';
                    $('#users-area').html(html);
                    $('#user-role-name').html(name);
                    $('#modal-user').modal('toggle');
                }
            });
        });
    });
</script>
</body>
</html>
