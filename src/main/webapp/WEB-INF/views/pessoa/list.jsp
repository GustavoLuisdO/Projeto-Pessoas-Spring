<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/solid.css"
          integrity="sha384-Tv5i09RULyHKMwX0E8wJUqSOaXlyu3SQxORObAI08iUwIalMmN5L6AvlPX2LMoSE" crossorigin="anonymous"/>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/fontawesome.css"
          integrity="sha384-jLKHWM3JRmfMU0A5x5AkjWkw/EYfGUAGagvnfryNV3F9VqM98XiIH7VBGVoxVSc7" crossorigin="anonymous"/>

    <title>Registros</title>

    <style>
        html {
            scroll-behavior: smooth;
        }
        #btn-topo {
            float: right;
            bottom: 15px;
            right: 35px;
            position: fixed;
            color: #6c757d;
        }
    </style>
</head>
<body>
<div id="topo"></div>
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


    <section>
        <div class="jumbotron jumbotron-fluid mt-3">
            <div class="container">
                <h2 class="display-4">Tabela de Registros</h2>
                <hr class="my-4">

                <div>
                    <div class="card-header mb-2 ${messageSuccess == null ? '' : 'alert alert-success'} ${messageError != null ? 'alert alert-danger' : ''}"
                         role="alert">
                        <span>${messageSuccess == null ? '&nbsp;' : messageSuccess}</span>
                        <span>${messageError == null ? '&nbsp;' : messageError}</span>
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>Nome</th>
                            <th>CPF</th>
                            <th>G??nero</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="pessoa" items="${pessoas}">
                            <tr>
                                <td>${pessoa.nome}</td>
                                <td>${pessoa.cpf}</td>
                                <td>${pessoa.genero.desc}</td>
                                <td class="mx-auto">
                                    <spring:url value="/pessoa/details/${pessoa.id}" var="details"/>
                                    <a class="btn btn-outline-secondary" href="${details}"><i
                                            class="fas fa-info-circle"></i></a>

                                    <spring:url value="/pessoa/update/${pessoa.id}" var="update"/>
                                    <a class="btn btn-outline-info" href="${update}"><i
                                            class="fas fa-user-edit"></i></a>

                                    <spring:url value="/pessoa/delete/${pessoa.id}" var="delete"/>
                                    <a class="btn btn-outline-danger" href="${delete}"><i class="fas fa-backspace"></i></a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </section>
</div>

<!-- btn para ir ao topo da pag -->
<abbr title="Ir ao Topo"><a id="btn-topo" href="#topo"><i class="fas fa-arrow-alt-circle-up fa-2x"></i></a></abbr>

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