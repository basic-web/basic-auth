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
                                <button id="btn-add" class="btn btn-success"><span
                                        class="fa fa-plus-circle"></span> 新增
                                </button>
                            </div>
                            <table class="table table-striped table-bordered dataTable no-footer">
                                <thead>
                                <tr>
                                    <th>名称</th>
                                    <th>模式</th>
                                    <th>创建时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="resource" items="${resources.data}">
                                    <tr>
                                        <td>${resource.name}</td>
                                        <td>${resource.pattern}</td>
                                        <td><fmt:formatDate value="${resource.createdTime}"
                                                            pattern="yyyy-MM-dd HH:mm"/></td>
                                        <td>
                                            <button class="btn btn-sm btn-primary btn-edit"
                                                    data-id="${resource.id}"><span
                                                    class="fa fa-edit"></span> 编辑
                                            </button>
                                            <button class="btn btn-sm btn-danger btn-delete"
                                                    data-id="${resource.id}"><span
                                                    class="fa fa-trash"></span> 删除
                                            </button>
                                            <c:if test="${resource.id != 'dashboard'}">
                                                <button class="btn btn-sm btn-info btn-assign_role"
                                                        data-id="${resource.id}"
                                                        data-name="${resource.name}"><span
                                                        class="fa fa-users"></span>
                                                    分配角色
                                                </button>
                                            </c:if>
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
<jsp:include page="../include/script.jsp"/>
</body>
</html>
