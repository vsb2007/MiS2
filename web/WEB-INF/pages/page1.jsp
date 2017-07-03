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
                <div class="row">
                    <div class="input-field col s6 offset-s2">
                            ${user.getLastName()} ${user.getFirstName()}
                    </div>
                </div>
            </div>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_USER_PRE')">
            <div class="row">
                <div class="row">
                    <div class="input-field col s6 offset-s2">
                        <input type="text" class="validate" id="code" name="code" required autofocus/>
                        <label for="code">SMS Code</label>
                    </div>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="token"/>
                <div class="row">
                    <div class="col s6 offset-s2">
                        <button class="btn waves-effect waves-light"
                                onClick="javascript:checkCode('statusCheck');">Подтвердите номер
                            <i class="material-icons right">send</i>
                        </button>
                    </div>
                </div>
                <div class="row">
                    <div class="col s6 offset-s2" id="codeStatus">

                    </div>
                </div>
            </div>
        </sec:authorize>
    </div>
    <div id="statusCheck">
        Содержимое для печати
    </div>
    <script language="javascript">
        function checkCode(idSpan) {
            var xmlhttp;
            document.getElementById(idSpan).innerHTML = "loading...";
            //document.getElementById(idSpan).value = orgDogId.value;
            if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
                xmlhttp = new XMLHttpRequest();
            }
            else {// code for IE6, IE5
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
            xmlhttp.onreadystatechange = function () {
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                    //document.getElementById(idSpan).innerHTML = xmlhttp.responseText;
                    //CallPrint(idSpan, xmlhttp.responseText);
                    if (xmlhttp.responseText == 1) {
                        document.location.replace("page2");
                    }
                    else {
                        document.getElementById(idSpan).innerHTML = "Не верный код:" + xmlhttp.responseText;
                    }
                }
            }
            var token = document.getElementById("token");
            var code = document.getElementById("code");
            xmlhttp.open("POST", "checkCode", true);
            xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlhttp.send("code=" + code.value + "&" + token.name + "=" + token.value);
        }

    </script>

</main>
<%@ include file="footer.jsp" %>