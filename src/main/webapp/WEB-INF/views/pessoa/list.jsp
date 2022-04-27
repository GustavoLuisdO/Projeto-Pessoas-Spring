<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

    <title>Pessoas</title>
</head>
<body>
<div class="container">

    <h1>Lista de Pessoas</h1>
    <hr>
    <div>

        <spring:url value="/telefone/all" var="listTelefone"/>
        <a class="btn btn-default" href="${listTelefone}">Telefones</a>

        <spring:url value="/pessoa/create" var="create"/>
        <a class="btn btn-default" href="${create}">Novo Usuário</a>
    </div>
    <hr>
    <div class="card-header ${message == null ? 'bg-default' : 'alert-success'}">
        <span>${message == null ? '&nbsp;' : message}</span>
    </div>

    <table class="table table-striped table-condensed">
        <thead>
        <tr>
            <th>Id</th>
            <th>Nome</th>
            <th>CPF</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="pessoa" items="${pessoas}">
            <tr>
                <td>${pessoa.id}</td>
                <td>${pessoa.nome}</td>
                <td>${pessoa.cpf}</td>
                <td>
                    <spring:url value="/pessoa/update/${pessoa.id}" var="update"/>
                    <a class="btn btn-info" href="${update}">Editar</a>

                    <spring:url value="/pessoa/delete/${pessoa.id}" var="delete"/>
                    <a class="btn btn-danger" href="${delete}">Excluir</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.min.js"
        integrity="sha384-VHvPCCyXqtD5DqJeNxl2dtTyhF78xXNXdkwX1CZeRusQfRKp+tA7hAShOK/B/fQ2"
        crossorigin="anonymous"></script>
</body>
</html>