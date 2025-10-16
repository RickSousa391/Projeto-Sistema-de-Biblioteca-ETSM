<%@page import="misc.JWTManager"%>
<%@page import="model.BibliotecariaDAO"%>
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
        <link  rel="stylesheet" href="css/menu.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
        <title>Lista</title>


        <style>
            #Body2{
                background-color: seagreen;
            }
            #Tabela1{
                border: 13px;
                container-type: unset;
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

            <div id="couteudo" class="bg-background" style="align-items: center">
                <div class="coutainer">
                    <div class="row">
                        <div class="col-12">
                            <h2 class="text-lg-center">Usuarios</h2>
                            <table class="table-dark table table-hover" id="Tabela1">
                                <thead>
                                    <tr>
                                        <th>Codigo</th>
                                        <th>Nome</th>
                                        <th>Contato</th>
                                        <th>E-mail</th>
                                        <th>CPF</th>
                                        <th>Alterar</th>
                                        <th>Excluir</th>
                                    </tr>
                                </thead>
                                <tbody class="table-group-divider">
                                <jsp:useBean class="model.UsuarioDAO" id="udao"/>
                                <c:forEach var="u" items="${udao.lista}">
                                    <tr>
                                        <td>${u.idUsuario}</td>
                                        <td>${u.nome}</td>
                                        <td>${u.contato}</td>
                                        <td>${u.email}</td>
                                        <td>${u.cpf}</td>
                                        <td>
                                            <a href="gerenciarUsuario?acao=alterar&idUsuario=${u.idUsuario}"
                                               class="btn btn-sm btn-primary" role="button">
                                                Alterar
                                            </a>
                                        </td>

                                        <td>
                                            <script type="text/javascript">
                                                function confirmarExclusao(id, nome) {
                                                    if (confirm('Deseja excluir o usuario ' + nome + ' ?')) {
                                                        location.href = "gerenciarUsuario?acao=excluir&idUsuario=" + id;
                                                    }
                                                }
                                            </script>
                                            <button class="btn btn-sm btn-danger" onclick="confirmarExclusao(${u.idUsuario}, '${u.nome}')"> Excluir</button>
                                        </td>

                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <%        }
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
