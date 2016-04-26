<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="include/head.jsp"/>
    <title>Template - Basic Auth.</title>
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
                            <h2>Template Page</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <p>This is template content.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="include/footer.jsp"/>
    </div>
</div>
<jsp:include page="include/script.jsp"/>
</body>
</html>
