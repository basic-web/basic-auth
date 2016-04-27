<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-3 left_col">
    <div class="left_col scroll-view">
        <div class="navbar nav_title" style="border: 0;">
            <a href="/" class="site_title"><i class="fa fa-paw"></i> <span>Basic Auth Web!</span></a>
        </div>
        <div class="clearfix"></div>

        <div class="profile">
            <div class="profile_pic">
                <img src="/resources/images/user.png" alt="..." class="img-circle profile_img">
            </div>
            <div class="profile_info">
                <span>欢迎您,</span>
                <h2>${user.displayName}</h2>
            </div>
        </div>

        <br/>

        <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
            <h2>&nbsp;</h2>
            <div class="menu_section">
                <ul class="nav side-menu">
                    <c:forEach var="menu" items="${menus}">
                        <li><a><i class="${menu.icon}"></i> ${menu.name} <span class="fa fa-chevron-down"></span></a>
                            <ul class="nav child_menu" style="display: none">
                                <c:forEach var="resource" items="${menu.resources}">
                                    <li><a class="link_menu" href="${resource.pattern}">${resource.name}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="top_nav">
    <div class="nav_menu">
        <nav class="" role="navigation">
            <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
            </div>

            <ul class="nav navbar-nav navbar-right">
                <li class="">
                    <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown"
                       aria-expanded="false">
                        <img src="/resources/images/user.png" alt=""> ${user.displayName}
                        <span class="fa fa-angle-down"></span>
                    </a>
                    <ul class="dropdown-menu dropdown-usermenu pull-right">
                        <li><a href="/user/settings"> 设置</a>
                        </li>
                        <li><a href="/logout"><i class="fa fa-sign-out pull-right"></i> 退出</a>
                        </li>
                    </ul>
                </li>

                <li role="presentation" class="dropdown">
                    <a href="javascript:;" class="dropdown-toggle info-number" data-toggle="dropdown"
                       aria-expanded="false">
                        <i class="fa fa-envelope-o"></i>
                        <span class="badge bg-green">1</span>
                    </a>
                    <ul id="menu1" class="dropdown-menu list-unstyled msg_list" role="menu">
                        <li>
                            <a>
                      <span class="image">
                                        <img src="/resources/images/user.png" alt="Profile Image"/>
                                    </span>
                      <span>
                                        <span>John Smith</span>
                      <span class="time">3 mins ago</span>
                      </span>
                      <span class="message">
                                        Film festivals used to be do-or-die moments for movie makers. They were where...
                                    </span>
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </nav>
    </div>
</div>
