<%--
  Created by IntelliJ IDEA.
  User: patryk
  Date: 13.07.2021
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Language" content="en">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Shopping organiser</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no" />
    <meta name="description" content="This is Shopping organiser webpage.">
    <meta name="msapplication-tap-highlight" content="no">
    <!--
    =========================================================
    * ArchitectUI HTML Theme Dashboard - v1.0.0
    =========================================================
    * Product Page: https://dashboardpack.com
    * Copyright 2019 DashboardPack (https://dashboardpack.com)
    * Licensed under MIT (https://github.com/DashboardPack/architectui-html-theme-free/blob/master/LICENSE)
    =========================================================
    * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
    -->
    <link href='<c:url value="../../resources/css/main.css"/>' rel="stylesheet"></head>
<body>
<div class="app-container app-theme-white body-tabs-shadow fixed-sidebar fixed-header">
    <div class="app-header header-shadow bg-dark">
        <div class="app-header__logo">
            <div class="header__pane ml-auto">
                <div>
                    <button type="button" class="hamburger close-sidebar-btn hamburger--elastic" data-class="closed-sidebar">
                            <span class="hamburger-box">
                                <span class="hamburger-inner"></span>
                            </span>
                    </button>
                </div>
            </div>
        </div>
        <div class="app-header__mobile-menu">
            <div>
                <button type="button" class="hamburger hamburger--elastic mobile-toggle-nav">
                        <span class="hamburger-box">
                            <span class="hamburger-inner"></span>
                        </span>
                </button>
            </div>
        </div>
        <div class="app-header__menu">
                <span>
                    <button type="button" class="btn-icon btn-icon-only btn btn-primary btn-sm mobile-toggle-header-nav">
                        <span class="btn-icon-wrapper">
                            <i class="fa fa-ellipsis-v fa-w-6"></i>
                        </span>
                    </button>
                </span>
        </div>    <div class="app-header__content">

        <div class="app-header-left">
            <sec:authorize access="isAuthenticated()">
                <ul class="header-menu nav">
                    <li class="dropdown nav-item">
                        <a href='<c:url value="/home"/>' class="nav-link text-dark badge badge-light">
                            <i class="nav-link-icon fa pe-7s-home"></i>
                            Go to home page
                        </a>
                    </li>
                </ul>
            </sec:authorize>
     </div>
        <div class="app-header-right">
            <sec:authorize access="isAuthenticated()">
                <ul class="header-menu nav">
                    <li class="nav-item">
                        <div class="header-btn-lg pr-0">
                            <div class="widget-content p-0">
                                <div class="widget-content-wrapper">
                                    <div class="widget-content-left  ml-3 header-user-info text-light">
                                        <div class="widget-subheading">
                                            Hello again,
                                        </div>
                                        <div class="widget-heading">
                                            <sec:authentication property="principal.user.userName"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li class="dropdown nav-item">
                        <a href='<c:url value="/home/settings"/>' class="nav-link text-light">
                            <i class="nav-link-icon fa fa-cog"></i>
                            Settings
                        </a>
                    </li>
                    <li class="btn-group nav-item">
                        <form action="<c:url value="/logout"/>" method="post">
                            <input class="btn-shadow mr-3 btn btn-primary" type="submit" value="Logout">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                    </li>
                </ul>  </sec:authorize>
        </div>
    </div>
    </div>
    <div class="app-main">

    <div class="app-sidebar sidebar-shadow bg-dark sidebar-text-light">
        <sec:authorize access="!isAuthenticated()">
            <div class="scrollbar-sidebar">
                <div class="app-sidebar__inner">
                    <ul class="vertical-nav-menu">
                        <li class="app-sidebar__heading">
                        <h5>Shopping organiser</h5>
                        </li>
                    </ul>
            </div>
            </div>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
        <div class="app-header__logo">
            <div class="header__pane ml-auto">
                <div>
                    <button type="button" class="hamburger close-sidebar-btn hamburger--elastic" data-class="closed-sidebar">
                                    <span class="hamburger-box">
                                        <span class="hamburger-inner"></span>
                                    </span>
                    </button>
                </div>
            </div>
        </div>
        <div class="app-header__mobile-menu">
            <div>
                <button type="button" class="hamburger hamburger--elastic mobile-toggle-nav">
                                <span class="hamburger-box">
                                    <span class="hamburger-inner"></span>
                                </span>
                </button>
            </div>
        </div>
        <div class="app-header__menu">
                        <span>
                            <button type="button" class="btn-icon btn-icon-only btn btn-primary btn-sm mobile-toggle-header-nav">
                                <span class="btn-icon-wrapper">
                                    <i class="fa fa-ellipsis-v fa-w-6"></i>
                                </span>
                            </button>
                        </span>
        </div>    <div class="scrollbar-sidebar">
        <div class="app-sidebar__inner">
            <ul class="vertical-nav-menu">
                <li class="app-sidebar__heading">Shopping lists</li>
                <li>
                    <a href='<c:url value="/home"/>'>
                        <i class="metismenu-icon pe-7s-menu"></i>
                        Your lists
                    </a>
                </li>
                <li>
                    <a href="index.html">
                        <i class="metismenu-icon pe-7s-users"></i>
                        Shared lists
                    </a>
                </li>
                <li class="app-sidebar__heading">History</li>
                <li>
                    <a href='<c:url value="/home/history"/>'>
                        <i class="metismenu-icon pe-7s-note2"></i>
                        Your history
                    </a>

                </li>
                <li class="app-sidebar__heading">Supplies</li>
                <li>
                    <a href="dashboard-boxes.html">
                        <i class="metismenu-icon pe-7s-box2"></i>
                        Your supplies
                    </a>
                </li>
            </ul>
        </div>
    </div>
        </sec:authorize>
    </div>

    <div class="app-main__outer">
        <div class="app-main__inner">