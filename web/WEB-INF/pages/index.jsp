<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>
<main>
    <div class="container">
        <sec:authorize access="hasRole('ROLE_USER')">
            <div class="row">
                <div class="input-field col s6 offset-s2">
                    <h4>Здравствуйте <%-- =userName--%></h4>

                </div>
            </div>
        </sec:authorize>
    </div>
</main>
<%@ include file="footer.jsp" %>
