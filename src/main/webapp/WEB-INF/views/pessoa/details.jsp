<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

    <title>Detalhes</title>
</head>
<body>
<div class="container">

    <header>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <spring:url value="/pessoa/all" var="all"/>
            <a class="navbar-brand" href="${all}">Pessoas</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
                    aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
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
        <c:set var="pessoa" scope="session" value="${pessoa}"/>
        <div class="jumbotron jumbotron-fluid text-center">
            <div class="container">
                <h1 class="display-4">${pessoa.nome}</h1>

                <div class="row text-center">
                    <div class="col-6">
                        <h4>CPF</h4>
                        <h5>${pessoa.cpf}</h5>
                    </div>
                    <div class="col-6">
                        <h4>Gênero</h4>
                        <h5>${pessoa.genero.desc}</h5>
                    </div>
                </div>

                <hr class="my-4">
                <div>
                    <spring:url value="/pessoa/update/${pessoa.id}" var="update"/>
                    <a class="btn btn-outline-info" href="${update}">Editar</a>

                    <spring:url value="/pessoa/delete/${pessoa.id}" var="delete"/>
                    <a class="btn btn-outline-danger" href="${delete}">Excluir</a>
                </div>
            </div>
        </div>
    </div>

    <div>
        <div class="jumbotron jumbotron-fluid">
            <div class="container">
                <div class="card-header ${message == null ? 'bg-default' : 'alert-success'}">
                    <span>${message == null ? '&nbsp;' : message}</span>
                </div>

                <h1 class="display-4">Adicionar Telefone</h1>

                <div>
                    <spring:url value="/pessoa/details/${pessoa.id}" var="save"/>
                    <%--@elvariable id="telefone" type="com.ot.pessoa.domain.Telefone"--%>
                    <form:form modelAttribute="telefone" action="${save}" method="post">
                        <div class="row">
                            <div class="col-5">
                                <div class="form-group">
                                    <label for="numero">Número</label>
                                    <form:input path="numero" class="form-control" maxlength="13" placeholder="00 90000-0000" required="required" />
                                    <form:errors path="numero" cssClass="text-danger"/>
                                </div>
                            </div>

                            <div class="col-7">
                                <div class="form-group">
                                    <label for="descricao">Descrição</label>
                                    <form:input path="descricao" class="form-control" placeholder="Ex: Ativo" required="required" />
                                    <form:errors path="descricao" cssClass="text-danger"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary">Confirmar</button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>

    <div>
        <div class="jumbotron jumbotron-fluid text-center">
            <div class="container">
                <div class="">
                    <table class="table table-striped table-condensed">
                        <thead>
                        <tr>
                            <th>Número</th>
                            <th>Descrição</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="telefones" items="${telefones}">
                            <tr>
                                <td>${telefones.numero}</td>
                                <td>${telefones.descricao}</td>
                                <td>
                                    <spring:url value="/telefone/updateTel/${telefones.id}" var="update"/>
                                    <a class="btn btn-outline-info" href="${update}">Editar</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>