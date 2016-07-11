<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pages" uri="http://ququzone.github.com/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../include/head.jsp"/>
    <title>资源管理</title>
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
                            <h2>资源列表</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <div class="pull-right">
                                <pages:a pattern="/resource" method="POST">
                                    <button id="btn-add" class="btn btn-success"><span
                                            class="fa fa-plus-circle"></span> 新增
                                    </button>
                                </pages:a>
                                <pages:a pattern="/resource/discover" method="POST">
                                    <button id="btn-discover" class="btn btn-primary"><span
                                            class="fa fa-search-plus"></span> 发现资源
                                    </button>
                                </pages:a>
                            </div>
                            <table class="table table-striped table-bordered dataTable no-footer">
                                <thead>
                                <tr>
                                    <th>名称</th>
                                    <th>模式</th>
                                    <th>方法</th>
                                    <th>创建时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="resource" items="${resources.data}">
                                    <tr>
                                        <td>${resource.name}</td>
                                        <td>${resource.pattern}</td>
                                        <td>${resource.method}</td>
                                        <td><fmt:formatDate value="${resource.createdTime}"
                                                            pattern="yyyy-MM-dd HH:mm"/></td>
                                        <td>
                                            <pages:a pattern="/resource/${resource.id}" method="PUT">
                                                <button class="btn btn-sm btn-primary btn-edit"
                                                        data-id="${resource.id}"><span
                                                        class="fa fa-edit"></span> 编辑
                                                </button>
                                            </pages:a>
                                            <pages:a pattern="/resource/${resource.id}" method="DELETE">
                                                <button class="btn btn-sm btn-danger btn-delete"
                                                        data-id="${resource.id}"><span
                                                        class="fa fa-trash"></span> 删除
                                                </button>
                                            </pages:a>
                                            <pages:a pattern="/resource/${resource.id}/roles" method="POST">
                                                <c:if test="${resource.id != 'dashboard'}">
                                                    <button class="btn btn-sm btn-info btn-assign_role"
                                                            data-id="${resource.id}"
                                                            data-name="${resource.name}"><span
                                                            class="fa fa-users"></span>
                                                        分配角色
                                                    </button>
                                                </c:if>
                                            </pages:a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <pages:p current="${resources.current}" totalPage="${resources.totalPage}"
                                     baseUrl="/resources"/>
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
                <h4 class="modal-title">新增资源</h4>
            </div>
            <div class="modal-body">
                <form id="form-add" class="form-horizontal form-label-left">
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">名称
                            <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input name="name" type="text" required="required"
                                   class="form-control col-md-7 col-xs-12">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">模式
                            <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input name="pattern" type="text" required="required"
                                   class="form-control col-md-7 col-xs-12">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">方法
                            <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <div class="radio">
                                <label>
                                    <input type="radio" value="GET" name="method" required="required"> GET
                                </label>
                                <label>
                                    <input type="radio" value="POST" name="method" required="required"> POST
                                </label>
                                <label>
                                    <input type="radio" value="PUT" name="method" required="required"> PUT
                                </label>
                                <label>
                                    <input type="radio" value="DELETE" name="method" required="required"> DELETE
                                </label>
                            </div>
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
                <h4 class="modal-title">编辑资源</h4>
            </div>
            <div class="modal-body">
                <form id="form-edit" class="form-horizontal form-label-left">
                    <div class="form-group">
                        <input type="hidden" name="_method" value="PUT">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">名称
                            <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input name="name" type="text" required="required" id="edit-name"
                                   class="form-control col-md-7 col-xs-12">
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="hidden" name="_method" value="PUT">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">模式
                            <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input name="pattern" type="text" required="required" id="edit-pattern"
                                   class="form-control col-md-7 col-xs-12">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">方法
                            <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <div class="radio">
                                <label>
                                    <input type="radio" class="edit-method" value="GET" name="method"
                                           required="required"> GET
                                </label>
                                <label>
                                    <input type="radio" class="edit-method" value="POST" name="method"
                                           required="required"> POST
                                </label>
                                <label>
                                    <input type="radio" class="edit-method" value="PUT" name="method"
                                           required="required"> PUT
                                </label>
                                <label>
                                    <input type="radio" class="edit-method" value="DELETE" name="method"
                                           required="required"> DELETE
                                </label>
                            </div>
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
                <h4 class="modal-title">分配角色 - <span id="role-name"></span></h4>
            </div>
            <div class="modal-body">
                <form id="form-resource-roles">
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
<script type="text/javascript" src="/static/js/bootstrap-confirmation.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#btn-add').click(function () {
            $('#modal-add').modal('toggle');
        });
        $('#btn-discover').click(function () {
            $.ajax({
                url: '/resource/discover',
                method: 'POST',
                success: function () {
                    window.location.reload();
                }
            })
        });
        $('#btn-add-submit').click(function (e) {
            e.preventDefault();
            $('#form-add').parsley().validate();
            if ($('#form-add').parsley().isValid()) {
                $.ajax({
                    url: '/resource',
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
                url: '/resource/' + id,
                method: 'GET',
                dataType: 'json',
                success: function (data) {
                    $('#edit-name').val(data.name);
                    $('#edit-pattern').val(data.pattern);
                    $('.edit-method').each(function (i, e) {
                        if ($(e).val() === data.method) {
                            $(e).attr('checked', 'checked');
                        }
                    });
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
                    url: '/resource/' + id,
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
                    url: '/resource/' + id,
                    method: 'POST',
                    data: '_method=DELETE',
                    success: function () {
                        window.location.reload();
                    }
                });
            }
        });
        $('.btn-assign_role').click(function () {
            var id = $(this).attr('data-id');
            var name = $(this).attr('data-name');
            $.ajax({
                url: '/resource/' + id + '/roles',
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
                    $('#role-name').html(name);
                    $('#form-resource-roles').html(html);
                    $('#modal-roles').modal('toggle');
                }
            });
        });
        $('#btn-roles-submit').click(function () {
            var id = $(this).attr('data-id');
            $.ajax({
                url: '/resource/' + id + '/roles',
                data: $('#form-resource-roles').serialize(),
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
