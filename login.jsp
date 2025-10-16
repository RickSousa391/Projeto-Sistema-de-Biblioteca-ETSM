<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
        <title>Login</title>

        <style>
            #Body1{
                background-color: seagreen
            }
            #H2{
                text-align: center
            }
        </style>

        <link rel="icon" type="image/jpg" href="imagens/BibliotecaJR2.png">
        <link rel="shortcut icon" href="caminho/para/seu-icone.ico">

    </head>
    <body id="Body1">
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
        <form action="loginBibliotecaria" method="Post">
            <h3 class="text-center">Perfil</h3>
            <div class="form-group">
                <label for="nome" class="btn bt-primary m-auto form-control-file">Nome</label>
                <input type="text" id="nome" name="nome" class="form-control d-grid gap-0 col-md-4 mx-auto" style="background-color: #ccffcc;">
            </div>
            <div class="form-group">
                <label for="nome" class="btn bt-primary m-auto form-control-range">Senha</label>
                <input type="text" id="senha" name="senha" class="form-control d-grid gap-0 col-md-4 mx-auto" style="background-color: #ccffcc;">
            </div>
            <div class="col-lg-12">
                <a href=""><button class="btn btn-primary btn-sm form-control d-grid mb-lg-3 gap-0 col-md-2 mx-auto" tabindex="6">Entrar</button></a>
            </div>
        </form>
        <div class="col-lg-12">
            <a href="cadastrarBibliotecaria.jsp"><button class="btn btn-primary btn-sm form-control d-grid mb-lg-3 gap-0 col-md-2 mx-auto" tabindex="6">Cadastra-se</button></a>
        </div>

        <script src="js/jquery-3.6.0.min.js"></script>
        <script src="bootstrap/bootstrap.min.js"></script>

    </body>
</html>