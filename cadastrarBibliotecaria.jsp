<%@page import="model.BibliotecariaDAO"%>
<%@page import="com.auth0.jwt.JWT"%>
<%@page import="com.auth0.jwt.interfaces.DecodedJWT"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="misc.JWTManager"%>
<%@page import="javax.servlet.http.Cookie"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1,shink-to-fit=no">
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/menu.css" type="text/css">
        <link rel="stylesheet" href="css/styles.css" type="text/css">
        <link rel="stylesheet" href="css/menu.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
        <title>Cadastrar</title>

        <link rel="icon" type="image/jpg" href="imagens/BibliotecaJR2.png">
        <link rel="shortcut icon" href="caminho/para/seu-icone.ico">

    </head>
    <body style="background-color: seagreen;">

        <nav class="navbar bg-body-tertiary">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">
                    <img src="imagens/BibliotecaJR2.png" width="30" height="24" class="d-inline-block align-text-top">
                    Biblioteca
                </a>
                <div id="Horas" class="ml-lg-auto">
                    <p id="segundos"></p>
                    <script src="js/Tempo.js"></script>
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <form action="gerenciarBibliotecaria" method="Post">
                        <h3 class="text-center">Nova Bibliotecaria</h3>
                        <input type="hidden" name="idBibliotecaria" value="${bibliotecaria.idBibliotecaria}">
                        <div class="form-group">
                            <label for="nome" class="btn bt-primary m-auto">Nome</label>
                            <input type="text" id="nome" name="nome" class="form-control" style="background-color: #ccffcc;" value="${bibliotecaria.nome}">   
                        </div>
                        <div class="form-group">
                            <label for="nome" class="btn bt-primary m-auto">Contato</label>
                            <input type="" id="contato" name="contato" class="form-control cod-md-6" style="background-color: #ccffcc;" valeu="${bibliotecaria.nome}">
                        </div>
                        <div class="form-group">
                            <label for="nome" class="btn bt-primary m-auto">E-Mail</label>
                            <input type="email" id="email" name="email" class="form-control cod-md-6" style="background-color: #ccffcc;" value="${bibliotecaria.email}">
                        </div>
                        <div class="form-group">
                            <label for="nome" class="btn bt-primary m-auto">Senha</label>
                            <input type="" id="senha" name="senha" class="form-control cod-md-6" style="background-color: #ccffcc;" value="${bibliotecaria.senha}">
                        </div>
                        <div class="form-group">
                            <label for="nome" class="btn bt-primary m-auto">RegistroFuncional</label>
                            <input type="" id="registrofuncional" name="registroFuncional" class="form-control cod-md-6" style="background-color: #ccffcc;" value="${bibliotecaria.registroFuncional}">
                        </div>
                        <div class="d-flex justify-content-center mb-lg-5">
                            <button type="submit" class="btn btn-primary">Salvar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <script src="js/jquery-3.6.0.min.js"></script>
        <script src="bootstrap/bootstrap.min.js"></script>
    </body>
</html>
