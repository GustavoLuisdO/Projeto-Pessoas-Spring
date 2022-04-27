<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <title>Editar Pessoa</title>
</head>
<body>
<div class="container">
    <h1>Editar Pessoa</h1>
    <hr>
    <div>
        <spring:url value="/pessoa/update" var="update"/>
        <%--@elvariable id="pessoa" type="com.ot.pessoa.domain.Pessoa"--%>
        <form:form modelAttribute="pessoa" action="${update}" method="post">
            <form:hidden path="id"/>

            <div class="form-group">
                <%--@declare id="nome"--%><label for="nome">Nome</label>
                <form:input path="nome" class="form-control"/>
                <form:errors path="nome" cssClass="text-danger"/>
            </div>

            <div class="form-group">
                 <%--@declare id="cpf"--%><label for="cpf">CPF</label>
                <form:input path="cpf" class="form-control"/>
                <form:errors path="cpf" cssClass="text-danger"/>
            </div>

            <div class="form-group">
                <button type="submit" class="btn btn-primary">Confirmar</button>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
