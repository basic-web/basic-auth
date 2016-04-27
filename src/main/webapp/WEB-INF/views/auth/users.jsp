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
                            <table class="table">
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
                                        <td>&nbsp;</td>
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
<jsp:include page="../include/script.jsp"/>
</body>
</html>
