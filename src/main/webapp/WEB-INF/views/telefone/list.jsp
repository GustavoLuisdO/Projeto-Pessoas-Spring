<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <title>Telefones</title>
</head>
<body>
<div class="container">
    <h1>Telefones</h1>
    <hr>
    <div>

        <spring:url value="/telefone/create" var="create"/>
        <a class="btn btn-default" href="${create}">Novo Telefone</a>
    </div>
    <hr>
    <div class="card-header ${message == null ? 'bg-default' : 'alert-success'}">
        <span>${message == null ? '&nbsp;' : message}</span>
    </div>

    <table class="table table-striped table-condensed">
        <thead>
        <tr>
            <th>Id</th>
            <th>Número</th>
            <th>Celular</th>
            <th>Dono</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="telefone" items="${telefones}">
            <tr>
                <td>${telefone.id}</td>
                <td>${telefone.numero}</td>
                <td>${telefone.celular}</td>
                <td>${telefone.dono.nome} ${telefone.dono.sobrenome}</td>
                <td>
                    <spring:url value="/pessoa/update/${telefone.id}" var="update"/>
                    <a class="btn btn-info" href="${update}">Editar</a>

                    <spring:url value="/pessoa/delete/${telefone.id}" var="delete"/>
                    <a class="btn btn-danger" href="${delete}">Excluir</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
