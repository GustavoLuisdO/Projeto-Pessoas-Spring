<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <title>Cadastrar Telefone</title>
</head>
<body>
<div class="container">
    <h1>Cadastrar Telefone</h1>
    <hr>
    <div>

        <spring:url value="/telefone/all" var="list"/>
        <a class="btn btn-default" href="${list}">Telefones Cadastrados</a>
    </div>
    <hr>
    <div>
        <spring:url value="/telefone/save" var="save"/>
        <%--@elvariable id="telefone" type="com.ot.pessoa.domain.Telefone"--%>
        <form:form modelAttribute="telefone" action="${save}" method="post">
<%--            <form:hidden path="id"/>--%>

            <div class="form-group">
                <label for="numero">Número</label>
                <form:input path="numero" class="form-control"/>
                <form:errors path="numero" cssClass="text-danger"/>
            </div>

            <div class="form-group form-check">
               <form:checkbox path="celular" value="1" class="form-check-input" />
                <%--@declare id="celular"--%><label class="form-check-label" for="celular">Celular</label>
            </div>

            <div class="form-group">
                <label for="dono">Dono</label>
                <form:select path="dono" class="form-control">
                    <form:options items="${pessoas}" itemLabel="id" />
                </form:select>
            </div>

            <div class="form-group">
                <button type="submit" class="btn btn-primary">Confirmar</button>
            </div>
        </form:form>
    </div>
</div>

</body>
</html>
