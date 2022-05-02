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

    <header>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <spring:url value="/pessoa/all" var="all"/>
            <a class="navbar-brand" href="${all}">Pessoas</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <spring:url value="/pessoa/create" var="create"/>
                        <a class="nav-link" href="${create}">Novo Registro</a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <hr>
    <div>
        <spring:url value="/pessoa/update" var="update"/>
        <%--@elvariable id="pessoa" type="com.ot.pessoa.domain.Pessoa"--%>
        <form:form modelAttribute="pessoa" action="${update}" method="post">
            <form:hidden path="id"/>

            <div class="form-group">
                <%--@declare id="nome"--%><label for="nome">Nome</label>
                <form:input path="nome" class="form-control" maxlength="100" placeholder="Nome Completo" required="required" />
                <form:errors path="nome" cssClass="text-danger"/>
            </div>

            <div class="form-group">
                 <%--@declare id="cpf"--%><label for="cpf">CPF</label>
                <form:input path="cpf" class="form-control" required="required" maxlength="14" placeholder="000.000.000-00" />
                <form:errors path="cpf" cssClass="text-danger"/>
            </div>

            <div class="form-group">
                <label for="genero">Gênero</label>
                <form:select path="genero" class="form-control">
                    <form:options items="${generos}" itemLabel="desc"/>
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
