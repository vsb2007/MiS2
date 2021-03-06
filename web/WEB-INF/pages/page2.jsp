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
                        <input type="text" class="validate" id="inn" name="inn" autofocus/>
                        <label for="inn">ИНН</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6 offset-s2">
                        <p>
                            <input type="checkbox" id="year0" name="year0"/>
                            <label for="year0">${years[0]}</label>
                            <br/>
                            <input type="checkbox" id="year1" name="year1"/>
                            <label for="year1">${years[1]}</label>
                            <br/>
                            <input type="checkbox" id="year2" name="year2"/>
                            <label for="year2">${years[2]}</label>
                        </p>
                    </div>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="token"/>
                <div class="row">
                    <div class="col s6 offset-s2">
                        <button class="btn waves-effect waves-light"
                                onClick="javascript:getAmount('print-content',null);">Печать справки и договора
                            за указанные года.
                            <i class="material-icons right">send</i>
                        </button>
                    </div>
                </div>
            </div>
        </sec:authorize>
    </div>
    <div id="print-content">
    </div>
    <script type="text/javascript">
        //window.print();
    </script>
    <script type="text/javascript">
        //window.print();
    </script>
    <script language="javascript">
        function CallPrint(idSpan, contract) {
            var prtContent = document.getElementById(idSpan);
            //var prtCSS = '<link rel="stylesheet" href="/templates/css/template.css" type="text/css" />';
            var WinPrint = window.open('', '', 'left=50,top=50,width=800,height=640,toolbar=0,scrollbars=1,status=0');
            WinPrint.document.write('<div id="print" class="contentpane">');
            //WinPrint.document.write(prtCSS);
            WinPrint.document.write(contract);
            WinPrint.document.write('</div>');
            WinPrint.document.close();
            WinPrint.focus();
            WinPrint.print();
            WinPrint.close();
            //prtContent.innerHTML = strOldOne;
            prtContent.innerHTML = "";
        }

        function getContract(idSpan, year) {

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
                    CallPrint(idSpan, xmlhttp.responseText);
                }
            }
            var token = document.getElementById("token");
            xmlhttp.open("POST", "getContract", true);
            xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlhttp.send("year=" + year + "&" + token.name + "=" + token.value);
        }

        function getAmount(idSpan, year) {

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
                    //CallPrint(idSpan);
                    CallPrint(idSpan, xmlhttp.responseText);
                }
            }
            var token = document.getElementById("token");
            var inn = document.getElementById("inn");
            /*
            if (inn == null || inn.value.length == 0) {
                document.getElementById(idSpan).innerHTML = "loading...<br> Введите ИНН";
                return;
            }*/
            xmlhttp.open("POST", "getAmount", true);
            xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlhttp.send("year=" + year + "&" + inn.name + "=" + inn.value + "&" + token.name + "=" + token.value);
        }
    </script>

</main>
<%@ include file="footer.jsp" %>