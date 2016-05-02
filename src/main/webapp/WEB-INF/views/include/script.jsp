<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="/static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/static/js/progressbar/bootstrap-progressbar.min.js"></script>
<script type="text/javascript" src="/static/js/notie.min.js"></script>
<script type="text/javascript" src="/static/js/custom.js"></script>
<script>
    NProgress.done();
    <c:if test="${not empty flashMessage}">
    notie.alert(${flashMessage.level}, '${flashMessage.message}', 2.5);
    </c:if>
</script>
