<%@page import="misc.JWTManager"%>
<%@page import="model.BibliotecariaDAO"%>
<%@page import="model.Livro"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
        <link rel="stylesheet" href="css/menu.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
        <title>Cadastrar Livro</title>

        <style>
            #Body2{
                background-color: seagreen;
            }
        </style>

        <link rel="icon" type="image/jpg" href="imagens/BibliotecaJR2.png">
        <link rel="shortcut icon" href="caminho/para/seu-icone.ico">

    </head>
    <body id="Body2">
        <%
            JWTManager jwtc = new JWTManager();
            Cookie[] cookies = request.getCookies();
            int id = 0;
            String token = "";

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("auth_token")) {
                        token = cookie.getValue();
                        break;
                    }
                }
            }

            if (!token.equals("") || !token.isBlank()) {
                id = jwtc.verifyToken(token);
            }

            // Verificar se o ID recebido pelo JWT é válido (fazendo um select no banco de dados)
            // Se for válido, renderiza a página, senão retorna um erro.
            BibliotecariaDAO bdao2 = new BibliotecariaDAO();

            if (id != 0) {
                if (bdao2.getBibliotecariaPorId(id).getIdBibliotecaria() != 0) {

        %>
        <div id="Conteúdo">
            <div id="menu">

                <jsp:include page ="template/menu.jsp"></jsp:include>
                </div><!--Fim Da Div Menu-->
            </div><!--Fim Da Div Conteúdo-->

            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <form action="gerenciarLivro" method="Post">
                            <h3 class="text-center">Registro de Livro</h3>
                            <input type="hidden" name="idLivro" value="${livro.idLivro}">
                        <div class="form-group">
                            <label for="nome" class="btn bt-primary m-auto">Titulo</label>
                            <input type="text" id="titulo" name="titulo" class="form-control" style="background-color: #ccffcc;" value="${livro.titulo}">   
                        </div>
                        <div class="form-group">
                            <label for="nome" class="btn bt-primary m-auto">Editoria</label>
                            <input type="text" id="editoria" name="editoria" class="form-control cod-md-6" style="background-color: #ccffcc;" value="${livro.editoria}">
                        </div>
                        <div class="form-group">
                            <label for="nome" class="btn bt-primary m-auto">Autor</label>
                            <input type="text" id="autor" name="autor" class="form-control cod-md-6" style="background-color: #ccffcc;" value="${livro.autor}">
                        </div>
                        <div class="form-group">
                            <label for="" class="btn bt-primary m-auto">Ano de Publicação</label>
                            <input type="date" id="anoPublicacao" name="anoPublicacao" class="form-control cod-md-6" style="background-color: #ccffcc;" value="${livro.anoPublicacao}">
                        </div>
                        <div class="form-group">
                            <label for="status" class="btn bt-primary m-auto">Status</label>
                            <select id="status" name="status"
                                    class="form-control form-control-sm" style="background-color: #ccffcc;">
                                <option value="">Selecione o Status</option>
                                <option value="1"<c:if test ="${livro.status == 1}">
                                        selected=""
                                    </c:if>>Disponivel</option>

                                <option value="0"<c:if test="${livro.status == 0}">
                                        selected="" 
                                    </c:if>>Indisponivel</option>

                            </select>

                        </div>    

                        <div class="d-flex justify-content-center mb-lg-5">
                            <button class="btn btn-primary">Salvar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <%
            }
        } else {
            // Caso inválido: encaminha para a página de erro e lança uma exceção.
            request.setAttribute("mensagem", "Dados inválidos foram fornecidos.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        %>

        <div>
            <p> Usuário não está logado ou sessão finalizada </p>
        </div>

        <%
            }
        %>

        <script src="js/jquery-3.6.0.min.js"></script>
        <script src="bootstrap/bootstrap.min.js"></script>
    </body>
</html>
