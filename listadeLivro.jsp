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
                            <h2 class="text-lg-center">Livros</h2>
                            <table class="table-dark table table-hover" id="listar" id="Tabela1">
                                <thead>
                                    <tr>
                                        <th>Codigo</th>
                                        <th>Titulo</th>
                                        <th>Editoria</th>
                                        <th>Autor</th>
                                        <th>Ano De Publicacao</th>
                                        <th>Status</th>
                                        <th>Quantidade</th>
                                        <th>Alterar</th>
                                    </tr>
                                </thead>
                                <tbody class="table-group-divider">
                                <jsp:useBean class="model.LivroDAO" id="ldao"/>
                                <c:forEach var="livro" items="${ldao.lista}">
                                    <tr>
                                        <td>${livro.idLivro}</td>
                                        <td>${livro.titulo}</td>
                                        <td>${livro.editoria}</td>
                                        <td>${livro.autor}</td>
                                        <td><fmt:formatDate pattern="dd/MM/yyyy" value="${livro.anoPublicacao}"/></td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${livro.status == 0}">
                                                    Disponivel
                                                </c:when>
                                                <c:otherwise>
                                                    Indisponivel
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>${livro.quantidade}</td>
                                        <td>
                                               <script type="text/javascript">
                                                   function confirmIndisponivel(id, titulo){
                                                       if(confirm('O Livro de ' + titulo + ' Sera Emprestado ?')){
                                                           location.href = "gerenciarLivro?acao=indisponivel&idLivro="+id;
                                                       }
                                                       
                                                   }
                                                   
                                                   function confirmDisponivel(id, titulo){
                                                       if(confirm('O Livro de ' + titulo + ' Voltou do Emprestado ?')){
                                                           location.href = "gerenciarLivro?acao=disponivel&idLivro="+id;
                                                       }
                                                   }
                                                </script>
                                            <c:choose>
                                                <c:when test="${livro.status == 1}">
                                                    <button class="btn btn-sm btn-danger" onclick="confirmIndisponivel('${livro.idLivro}', '${livro.titulo}')">
                                                        Indisponivel</button>
                                                </c:when>
                                                <c:otherwise>
                                                    <button class="btn btn-sm btm" style="background-color: green;" onclick="confirmDisponivel('${livro.idLivro}', '${livro.titulo}')">
                                                        Disponivel</button>
                                                </c:otherwise>
                                            </c:choose>
                                               
                                               
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
