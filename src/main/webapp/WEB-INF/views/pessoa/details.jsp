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

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/solid.css"
          integrity="sha384-Tv5i09RULyHKMwX0E8wJUqSOaXlyu3SQxORObAI08iUwIalMmN5L6AvlPX2LMoSE" crossorigin="anonymous"/>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/fontawesome.css"
          integrity="sha384-jLKHWM3JRmfMU0A5x5AkjWkw/EYfGUAGagvnfryNV3F9VqM98XiIH7VBGVoxVSc7" crossorigin="anonymous"/>

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

    <section>
        <c:set var="pessoa" scope="session" value="${pessoa}"/>
        <div class="jumbotron jumbotron-fluid text-center mt-3">
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
                    <a class="btn btn-outline-info" href="${update}"><i class="fas fa-user-edit"></i></a>

                    <spring:url value="/pessoa/delete/${pessoa.id}" var="delete"/>
                    <a class="btn btn-outline-danger" href="${delete}"><i class="fas fa-backspace"></i></a>
                </div>
            </div>
        </div>
    </section>

    <section>

        <nav>
            <div class="nav nav-tabs" id="nav-tab" role="tablist">
                <a class="nav-link active" id="nav-produtos-tab" data-toggle="tab" href="#nav-produtos" role="tab"
                   aria-controls="nav-produtos" aria-selected="true">Produtos</a>
                <a class="nav-link" id="nav-telefones-tab" data-toggle="tab" href="#nav-telefones" role="tab"
                   aria-controls="nav-telefones" aria-selected="false">Telefones</a>
            </div>
        </nav>
        <div class="tab-content" id="nav-tabContent">
            <%--            PRODUTOS --%>
            <div class="tab-pane fade show active" id="nav-produtos" role="tabpanel" aria-labelledby="nav-produtos-tab">
                <div class="jumbotron jumbotron-fluid">
                    <div class="container">
                        <h2 class="display-4 ml-2">Adicionar Produto</h2>
                        <hr class="my-4">

                        <div class="card-header mb-2 ${messageSuccess == null ? '' : 'alert alert-success'} ${messageError != null ? 'alert alert-danger' : ''}"
                             role="alert">
                            <span>${messageSuccess == null ? '&nbsp;' : messageSuccess}</span>
                            <span>${messageError == null ? '&nbsp;' : messageError}</span>
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>

                        <div>
                            <spring:url value="/pessoa/details/${pessoa.id}" var="save"/>
                            <%--@elvariable id="produto" type="com.ot.pessoa.domain.Produto"--%>
                            <form:form modelAttribute="produto" action="${save}" method="post">
                                <div class="row">
                                    <div class="col-5">
                                        <div class="form-group">
                                            <label for="nomeProduto">Nome do Produto</label>
                                            <form:input path="nomeProduto" class="form-control" maxlength="50"
                                                        placeholder="Ex: Notebook" required="required"/>
                                            <form:errors path="nomeProduto" cssClass="text-danger"/>
                                        </div>
                                    </div>

                                    <div class="col-2">
                                        <div class="form-group">
                                            <label for="preco">Preço</label>
                                            <form:input path="preco" class="form-control"
                                                        placeholder="Ex: 1000.00" required="required"/>
                                            <form:errors path="preco" cssClass="text-danger"/>
                                        </div>
                                    </div>

                                    <div class="col-5">
                                        <div class="form-group">
                                            <label for="descricao">Descrição</label>
                                            <form:textarea path="descricao" class="form-control" maxlength="255"
                                                        placeholder="Informação sobre o produto" required="required"/>
                                            <form:errors path="descricao" cssClass="text-danger"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary">Confirmar</button>
                                </div>
                            </form:form>
                        </div>

                        <div class="mt-5">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>Produto</th>
                                    <th>Preço</th>
                                    <th>Descrição</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="produtos" items="${produtos}">
                                    <tr>
                                        <td>${produtos.nomeProduto}</td>
                                        <td>${produtos.preco}</td>
                                        <td>${produtos.descricao}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

            <%--            TELEFONES --%>
            <div class="tab-pane fade" id="nav-telefones" role="tabpanel" aria-labelledby="nav-telefones-tab">
                <div class="jumbotron jumbotron-fluid">
                    <div class="container">
                        <h2 class="display-4 ml-2">Adicionar Telefone</h2>
                        <hr class="my-4">

                        <div class="card-header mb-2 ${messageSuccess == null ? '' : 'alert alert-success'} ${messageError != null ? 'alert alert-danger' : ''}"
                             role="alert">
                            <span>${messageSuccess == null ? '&nbsp;' : messageSuccess}</span>
                            <span>${messageError == null ? '&nbsp;' : messageError}</span>
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>

                        <div>
                            <spring:url value="/pessoa/details/${pessoa.id}" var="save"/>
                            <%--@elvariable id="telefone" type="com.ot.pessoa.domain.Telefone"--%>
                            <form:form modelAttribute="telefone" action="${save}" method="post">
                                <div class="row">
                                    <div class="col-5">
                                        <div class="form-group">
                                            <label for="numero">Número</label>
                                            <form:input path="numero" class="form-control" maxlength="13"
                                                        placeholder="00 90000-0000" required="required"/>
                                            <form:errors path="numero" cssClass="text-danger"/>
                                        </div>
                                    </div>

                                    <div class="col-7">
                                        <div class="form-group">
                                            <label for="obs">Observação</label>
                                            <form:textarea path="obs" class="form-control" placeholder="Ex: Ativo"
                                                        required="required"/>
                                            <form:errors path="obs" cssClass="text-danger"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary">Confirmar</button>
                                </div>
                            </form:form>
                        </div>

                        <div class="mt-5">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>Número</th>
                                    <th>Observação</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="telefones" items="${telefones}">
                                    <tr>
                                        <td>${telefones.numero}</td>
                                        <td>${telefones.obs}</td>
                                        <td>
                                            <spring:url value="/telefone/updateTel/${telefones.id}" var="update"/>
                                            <a class="btn btn-outline-info" href="${update}"><i
                                                    class="fas fa-user-edit"></i></a>
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
    </section>

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