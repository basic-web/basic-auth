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
                                    <th>图标</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach varStatus="ms" var="menu" items="${menus}">
                                    <tr class="treegrid-${ms.index}">
                                        <td>${menu.name}</td>
                                        <td><span class="${menu.icon}"></span></td>
                                        <td>
                                            <button class="btn btn-sm btn-success btn-add"
                                                    data-id="${menu.id}"><span
                                                    class="fa fa-edit"></span> 添加资源
                                            </button>
                                            <button class="btn btn-sm btn-primary btn-edit"
                                                    data-id="${menu.id}"><span
                                                    class="fa fa-edit"></span> 编辑
                                            </button>
                                            <c:if test="${menu.id != 'home'}">
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
                                            <td>&nbsp;</td>
                                            <td>
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
                                                <c:if test="${resource.id != 'dashboard'}">
                                                    <button class="btn btn-sm btn-danger btn-resource-delete"
                                                            data-id="${resource.id}"><span
                                                            class="fa fa-trash"></span> 删除
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
<jsp:include page="../include/script.jsp"/>
<script type="text/javascript" src="/static/js/jquery.treegrid.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('.tree').treegrid({
            expanderExpandedClass: 'glyphicon glyphicon-minus',
            expanderCollapsedClass: 'glyphicon glyphicon-plus'
        })
    });
</script>
</body>
</html>
