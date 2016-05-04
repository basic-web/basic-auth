<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../include/head.jsp"/>
    <link href="/static/css/jquery.treegrid.css" rel="stylesheet" type="text/css"/>
    <title>菜单管理</title>
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
                            <h2>菜单管理</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <div class="pull-right">
                                <button id="btn-add" class="btn btn-success"><span
                                        class="fa fa-plus-circle"></span> 新增菜单
                                </button>
                            </div>
                            <table class="table table-striped table-bordered dataTable no-footer tree">
                                <thead>
                                <tr>
                                    <th>名称</th>
                                    <th>模式</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach varStatus="ms" var="menu" items="${menus}">
                                    <tr class="treegrid-${ms.index}">
                                        <td><span class="${menu.icon}"></span> ${menu.name}</td>
                                        <td>&nbsp;</td>
                                        <td>
                                            <button class="btn btn-sm btn-success btn-add"
                                                    data-id="${menu.id}"><span
                                                    class="fa fa-edit"></span> 添加资源
                                            </button>
                                            <c:if test="${menu.id != 'home'}">
                                                <button class="btn btn-sm btn-primary btn-edit"
                                                        data-id="${menu.id}"><span
                                                        class="fa fa-edit"></span> 编辑
                                                </button>
                                                <button class="btn btn-sm btn-danger btn-menu-delete"
                                                        data-id="${menu.id}"><span
                                                        class="fa fa-trash"></span> 删除
                                                </button>
                                            </c:if>
                                            <c:if test="${not ms.first}">
                                                <button class="btn btn-sm btn-info btn-menu-up"
                                                        data-id="${menu.id}"><span
                                                        class="fa fa-chevron-up"></span> 上移
                                                </button>
                                            </c:if>
                                            <c:if test="${not ms.last}">
                                                <button class="btn btn-sm btn-info btn-menu-down"
                                                        data-id="${menu.id}"><span
                                                        class="fa fa-chevron-down"></span> 下移
                                                </button>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <c:forEach varStatus="rs" var="resource" items="${menu.resources}">
                                        <tr class="treegrid-parent-${ms.index}">
                                            <td>${resource.name}</td>
                                            <td>${resource.pattern}</td>
                                            <td>
                                                <c:if test="${resource.id != 'dashboard'}">
                                                    <button class="btn btn-sm btn-danger btn-resource-delete"
                                                            data-id="${resource.id}"><span
                                                            class="fa fa-trash"></span> 删除
                                                    </button>
                                                </c:if>
                                                <c:if test="${not rs.first}">
                                                    <button class="btn btn-sm btn-info btn-resource-up"
                                                            data-id="${resource.id}"><span
                                                            class="fa fa-chevron-up"></span> 上移
                                                    </button>
                                                </c:if>
                                                <c:if test="${not rs.last}">
                                                    <button class="btn btn-sm btn-info btn-resource-down"
                                                            data-id="${resource.id}"><span
                                                            class="fa fa-chevron-down"></span> 下移
                                                    </button>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="../include/footer.jsp"/>
    </div>
</div>

<div class="modal fade" id="modal-add_menu" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">新增菜单</h4>
            </div>
            <div class="modal-body">
                <form id="form-add_menu" class="form-horizontal form-label-left">
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
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">图标
                            <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input name="icon" value="fa fa-table" type="text" required="required"
                                   class="form-control col-md-7 col-xs-12">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="btn-add_menu-submit" type="button" class="btn btn-primary"><span
                        class="fa fa-plus-circle"></span> 新增
                </button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="modal-edit_menu" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑菜单</h4>
            </div>
            <div class="modal-body">
                <form id="form-edit_menu" class="form-horizontal form-label-left">
                    <input type="hidden" name="_method" value="PUT">
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">名称
                            <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input name="name" id="edit-name" type="text" required="required"
                                   class="form-control col-md-7 col-xs-12">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">图标
                            <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input name="icon" id="edit-icon" type="text" required="required"
                                   class="form-control col-md-7 col-xs-12">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="btn-edit_menu-submit" type="button" class="btn btn-primary"><span
                        class="fa fa-edit"></span> 编辑
                </button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../include/script.jsp"/>
<script type="text/javascript" src="/static/js/parsley/parsley.min.js"></script>
<script type="text/javascript" src="/static/js/parsley/zh_cn.js"></script>
<script type="text/javascript" src="/static/js/bootstrap-confirmation.min.js"></script>
<script type="text/javascript" src="/static/js/jquery.treegrid.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('.tree').treegrid({
            expanderExpandedClass: 'glyphicon glyphicon-minus',
            expanderCollapsedClass: 'glyphicon glyphicon-plus'
        });
        $('#btn-add').click(function () {
            $('#modal-add_menu').modal('toggle');
        });
        $('#btn-add_menu-submit').click(function () {
            $('#form-add_menu').parsley().validate();
            if ($('#form-add_menu').parsley().isValid()) {
                $.ajax({
                    url: '/menu',
                    method: 'POST',
                    dataType: 'json',
                    data: $('#form-add_menu').serialize(),
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
                url: '/menu/' + id,
                method: 'GET',
                dataType: 'json',
                success: function (data) {
                    $('#edit-name').val(data.name);
                    $('#edit-icon').val(data.icon);
                    $('#btn-edit_menu-submit').attr('data-id', id);
                    $('#modal-edit_menu').modal('toggle');
                }
            });
        });
        $('#btn-edit_menu-submit').click(function () {
            var id = $(this).attr('data-id');
            $('#form-edit_menu').parsley().validate();
            if ($('#form-edit_menu').parsley().isValid()) {
                $.ajax({
                    url: '/menu/' + id,
                    method: 'POST',
                    dataType: 'json',
                    data: $('#form-edit_menu').serialize(),
                    success: function () {
                        window.location.reload();
                    },
                    error: function (data) {
                        notie.alert(3, data.responseJSON.error, 2.5);
                    }
                });
            }
        });
    });
</script>
</body>
</html>
