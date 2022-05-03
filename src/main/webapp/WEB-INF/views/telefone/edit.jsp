<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

    <title>Editar Telefone</title>
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

    <section>

        <div class="jumbotron jumbotron-fluid mt-3">
            <div class="container">
                <c:set var="dono" scope="session" value="${pessoa}" />
                <h2 class="display-4">Editar Telefone de <strong>${pessoa.nome}</strong></h2>
                <hr class="my-4">

                <div>
                    <spring:url value="/telefone/updateTel/${telefone.id}" var="save"/>
                    <%--@elvariable id="telefone" type="com.ot.pessoa.domain.Telefone"--%>
                    <form:form modelAttribute="telefone" action="${save}" method="post">
                        <form:hidden path="id" />

                        <div class="row">
                            <div class="col-5">
                                <div class="form-group">
                                    <label for="numero">Número</label>
                                    <form:input path="numero" class="form-control" maxlength="13" placeholder="xx xxxxx-xxxx" required="required" />
                                    <form:errors path="numero" cssClass="text-danger"/>
                                </div>
                            </div>

                            <div class="col-7">
                                <div class="form-group">
                                    <label for="descricao">Descrição</label>
                                    <form:input path="descricao" class="form-control" required="required" />
                                    <form:errors path="descricao" cssClass="text-danger"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary">Confirmar Alterações</button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </section>
</div>
</body>
</html>
