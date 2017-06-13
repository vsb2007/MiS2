<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>

<main>
    <div class="container">
        <%-- <sec:authorize access="!hasRole('ROLE_LOGIN')"> --%>
        <sec:authorize access="!hasRole('ROLE_USER')">
            <div class="row">
                <c:url var="loginUrl" value="/login"/>
                <form action="${loginUrl}" method="post" class="">
                    <c:if test="${param.error != null}">
                        <div class="col s6 offset-s2">
                            <p>Invalid username and password.</p>
                        </div>
                    </c:if>
                    <c:if test="${error != null}">
                        <div class="col s6 offset-s2">
                            <p>${error}</p>
                        </div>
                    </c:if>
                    <c:if test="${param.logout != null}">
                        <div class="col s6 offset-s2">
                            <p>You have been logged out successfully.</p>
                        </div>
                    </c:if>

                    <div class="row">
                        <div class="input-field col s6 offset-s2">
                            <input type="text" class="validate" id="lastName" name="lastName" required autofocus/>
                            <label for="lastName">Фамилия</label>
                            <input type="hidden" name="username" value="Test22"/>
                            <input type="hidden" name="password" value="Test22"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s6 offset-s2">
                            <input type="text" class="validate" id="firstName" name="firstName" required autofocus/>
                            <label for="firstName">Имя</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s6 offset-s2">
                            <input type="text" class="validate" id="secondName" name="secondName" required autofocus/>
                            <label for="secondName">Отчество</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s6 offset-s2">
                            <input type="text" class="validate" id="birthDate" name="birthDate" required autofocus/>
                            <label for="birthDate">Дата рождения</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s6 offset-s2">
                            <input type="text" class="validate" id="phone" name="phone" required autofocus/>
                            <label for="phone">Номер сотового телефона</label>
                        </div>
                    </div>


                        <%--
                        <div class="row">
                            <div class="col s6 offset-s2">
                                <p>
                                    <input type="checkbox" class="filled-in" id="rememberme" checked="checked"
                                           name="remember-me"/>
                                    <label for="rememberme">Remember Me</label>
                                </p>
                            </div>
                        </div>
                        --%>
                    <div class="row">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <div class="col s6 offset-s2">
                            <button class="btn waves-effect waves-light">Вход
                                <i class="material-icons right">send</i>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_USER')">
            <div class="row">
                <div class="input-field col s6 offset-s2">
                    Вы уже вошли
                </div>
            </div>
        </sec:authorize>
    </div>
</main>
<%@ include file="footer.jsp" %>