<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<header>
    <nav>
        <div class="nav-wrapper blue-grey">
            <a href="#" data-activates="mobile-menu" class="button-collapse"><i class="material-icons">menu</i></a>
            <a href="#" class="brand-logo center"><img src="images/logoEuromed.png" width="250"/></a>
            <ul class="left hide-on-med-and-down">
                <%@include file="menu_li.jsp" %>
            </ul>
            <ul class="side-nav" id="mobile-menu">
                <%@include file="menu_li.jsp" %>
            </ul>
        </div>
    </nav>

    <script>
        $(document).ready(function () {
            $('.button-collapse').sideNav({
                    menuWidth: 240, // Default is 240
                    edge: 'left', // Choose the horizontal origin
                    closeOnClick: true, // Closes side-nav on <a> clicks, useful for Angular/Meteor
                    draggable: true // Choose whether you can drag to open on touch screens
                }
            );
        })

    </script>

</header>