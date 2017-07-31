<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>

<main>
    <div class="container">
        <%-- <sec:authorize access="!hasRole('ROLE_LOGIN')"> --%>
        <sec:authorize access="!hasRole('ROLE_USER_PRE')">
            <div class="row">
                <c:url var="loginUrl" value="/login"/>
                <form action="${loginUrl}" method="post" class=""
                    <%--      autocomplete="off" --%>
                >
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
                            <input type="text" class="validate" id="birthDate" name="birthDate" required autofocus
                                   pattern="[0-9]{2}.[0-9]{2}.[0-9]{4}" placeholder="01.01.1970"
                                   onkeyup="validateBirth(this,event.keyCode)"
                                   onchange="validateBirth(this,event.keyCode)"
                            />
                            <label for="birthDate">Дата рождения</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s6 offset-s2">
                            <input type="text" class="validate" id="phone" name="phone" required autofocus
                                   pattern="\d\d\d\d\d\d\d\d\d\d" placeholder="9223334444"
                                   onkeyup="validatePhone(this)" onchange="validatePhone(this)"/>
                            <label for="phone">Номер сотового телефона</label>
                        </div>
                    </div>
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

        <sec:authorize access="!hasRole('ROLE_USER') && hasRole('ROLE_USER_PRE')">
            <%@ include file="page1.jsp" %>
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
<script type="text/javascript">
    function validateBirth(inp, keyCode) {
        str = inp.value;
        if (keyCode == 8 && (str.length == 2 || str.length == 5)) {
            inp.value = str.substring(0, str.length - 1);
            return;
        }
        str = str.replace(/[^.0-9]/gim, '');
        if (str[0] == ".") {
            inp.value = "";
            return;
        }
        if (str.charAt(1) == '.') {
            inp.value = str[0];
            return;
        }
        if (str.charAt(3) == '.') {
            inp.value = str.substring(0, 3);
            return;
        }
        if (str.charAt(4) == '.') {
            inp.value = str.substring(0, 4);
            return;
        }
        if (str.charAt(6) == '.') {
            inp.value = str.substring(0, 6);
            return;
        }
        if (str.charAt(7) == '.') {
            inp.value = str.substring(0, 7);
            return;
        }
        if (str.charAt(8) == '.') {
            inp.value = str.substring(0, 8);
            return;
        }
        if (str.charAt(9) == '.') {
            inp.value = str.substring(0, 9);
            return;
        }
        if (str.length == 2) str = str + ".";
        if (str.length == 5) str = str + ".";
        if (str.length > 10) str = str.substring(0, 10);
        inp.value = str;
    }

    function validatePhone(inp) {
        str = inp.value;
        str = str.replace(/[^0-9]/gim, '');
        if (str.length > 10) str = str.substring(0, 10);
        inp.value = str;
    }
</script>
<%@ include file="footer.jsp" %>