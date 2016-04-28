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
                            <table class="table table-striped table-bordered dataTable no-footer">
                                <thead>
                                <tr>
                                    <th>名称</th>
                                    <th>状态</th>
                                    <th>创建时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="role" items="${roles.data}">
                                    <tr>
                                        <td>${role.name}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${user.status == 'NORMAL'}">正常</c:when>
                                                <c:when test="${user.status == 'DISABLE'}">禁用</c:when>
                                                <c:otherwise>未知</c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td><fmt:formatDate value="${role.createdTime}"
                                                            pattern="yyyy-MM-dd HH:mm"/></td>
                                        <td>&nbsp;</td>
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
<jsp:include page="../include/script.jsp"/>
</body>
</html>
