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
        <link rel="stylesheet" href="css/livros.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
        <title>Acervos</title>
        
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

        <br>

        <h2 style="text-align: center;">Linguagens De Programação</h2>
        
        <form action="pesquisaLivro" method="Post">
            <label for = "nome"></label>
            <input type="text" id="titulo" name="titulo">
            <input type="submit" value="pesquisa">
        </form>
        

        <div class="carousel">
            <jsp:useBean class="model.LivroDAO" id="ldao"/>
                <c:forEach var="livro" items="${ldao.lista}">
            <div class="card">
                <img src="imagens/programacao/LivroHTML2.jpg" alt=""/><a href=""><c:choose>
                    <c:when test="${livro.status == 0}">
                        <button class="btn btn-sm btn-danger" onclick="confirmIndisponivel2('${livro.idLivro}')">
                                indisponivel</button>
                                    </c:when>
                                        <c:otherwise>
                                            <button class="btn btn-sm btm" style="background-color: green;" onclick="confirmDisponivel2('${livro.idLivro}')">
                                                disponivel</button>
                                            </c:otherwise>
                                        </c:choose></a></div>
            <div class="card"><img src="imagens/programacao/LivroJS.jpg"/><a href=""><c:choose>
                    <c:when test="${livro.status == 1}">
                        <button class="btn btn-sm btn-danger" onclick="confirmIndisponivel('${livro.idLivro}', '${livro.titulo}')">
                                </button>
                                    </c:when>
                                        <c:otherwise>
                                            <button class="btn btn-sm btm" style="background-color: green;" onclick="confirmDisponivel('${livro.idLivro}', '${livro.titulo}')">
                                                </button>
                                            </c:otherwise>
                    </c:choose></a></div>
            <div class="card"><img src="imagens/programacao/LivroPYTHON.jpg" alt=""/><a href="#"><button class="btn btn-primary btn-sm form-control d-grid mb-lg-3 gap-0 col-md-4 mx-auto"></button></a></div>
            <div class="card"><img src="imagens/programacao/LivroJAVA2.jpg" alt=""/><a href="#"><button class="btn btn-primary btn-sm form-control d-grid mb-lg-3 gap-0 col-md-4 mx-auto"></button></a></div>
            <div class="card"><img src="imagens/programacao/LivroSQL1.jpg" alt=""/><a href="#"><button class="btn btn-primary btn-sm form-control d-grid mb-lg-3 gap-0 col-md-4 mx-auto"></button></a></div>
            <div class="card"><img src="imagens/programacao/LivroC.jpg" alt=""/><a href="#"><button class="btn btn-primary btn-sm form-control d-grid mb-lg-3 gap-0 col-md-4 mx-auto"></button></a></div>
            <div class="card"><img src="imagens/programacao/LivroC++.jpg" alt=""/><a href="#"><button class="btn btn-primary btn-sm form-control d-grid mb-lg-3 gap-0 col-md-4 mx-auto"></button></a></div>
            <div class="card"><img src="imagens/programacao/LivroChar.jpg" alt=""/><a href="#"><button class="btn btn-primary btn-sm form-control d-grid mb-lg-3 gap-0 col-md-4 mx-auto"></button></a></div>
            <div class="card"><img src="imagens/programacao/LivroNode.jpg" alt=""/></div>
            <div class="card"><img src="imagens/programacao/LivroReact.jpg" alt=""/></div>
            <div class="card"><img src="imagens/programacao/LivroPython1.jpg" alt="alt"/></div>
            </c:forEach>
        </div>


        <br>

        <h2 style="text-align: center;">Radiologia</h2>

        <div class="carousel">
            <div class="card"><img src="imagens/radiologia/LivroRadiologia.jpg" alt=""/></div>
            <div class="card"><img src="imagens/radiologia/LivroRadiologia1.jpg" alt=""/></div>
            <div class="card"><img src="imagens/radiologia/LivroRadiologia2.jpg" alt=""/></div>
            <div class="card"><img src="imagens/radiologia/LivroRadiologia3.jpg" alt=""/></div>
            <div class="card"><img src="imagens/radiologia/LivroRadiologia4.jpg" alt=""/></div>
            <div class="card"><img src="imagens/radiologia/LivroRadiologia5.jpg" alt=""/></div>
            <div class="card"><img src="imagens/radiologia/LivroRadiologia6.jpg" alt=""/></div>
            <div class="card"><img src="imagens/radiologia/LivroRadiologia7.jpg" alt=""/></div>
            <div class="card"><img src="imagens/radiologia/LivroRadiologia8.jpg" alt=""/></div>
            <div class="card"><img src="imagens/radiologia/LivroRadiologia9.jpg" alt=""/></div>
            <div class="card"><img src="imagens/radiologia/LivroRadiologia10.jpg" alt=""/></div>
            <div class="card"><img src="imagens/radiologia/LivroRadiologia11.jpg" alt=""/></div>
            <div class="card"><img src="imagens/radiologia/LivroRadiologia12.jpg" alt=""/></div>   
        </div>

        <br>

        <h2 style="text-align: center;">Administração</h2>

        <div class="carousel">
            <div class="card"><img src="imagens/administracao/LivroAdministracao.jpg" alt=""/></div>
            <div class="card"><img src="imagens/administracao/LivroAdministracao1.jpg" alt=""/></div>
            <div class="card"><img src="imagens/administracao/LivroAdministracao2.jpg" alt=""/></div>
            <div class="card"><img src="imagens/administracao/LivroAdministracao3.jpg" alt=""/></div>
            <div class="card"><img src="imagens/administracao/LivroAdministracao4.jpg" alt=""/></div>
            <div class="card"><img src="imagens/administracao/LivroAdministracao5.jpg" alt=""/></div>
            <div class="card"><img src="imagens/administracao/LivroAdministracao6.jpg" alt=""/></div>
            <div class="card"><img src="imagens/administracao/LivroAdministracao7.jpg" alt=""/></div>
            <div class="card"><img src="imagens/administracao/LivroAdministracao8.jpg" alt=""/></div>
            <div class="card"><img src="imagens/administracao/LivroAdministracao9.jpg" alt=""/></div>
            <div class="card"><img src="imagens/administracao/LivroAdministracao10.jpg" alt=""/></div>
            <div class="card"><img src="imagens/administracao/LivroAdministracao11.jpg" alt=""/></div>
            <div class="card"><img src="imagens/administracao/LivroAdministracao12.jpg" alt=""/></div>   
        </div>

        <br>

        <h2 style="text-align: center;">Rede</h2>

        <div class="carousel">
            <div class="card"><img src="imagens/rede/LivroRede.jpg" alt=""/></div>
            <div class="card"><img src="imagens/rede/LivroRede1.jpg" alt=""/></div>
            <div class="card"><img src="imagens/rede/LivroRede2.jpg" alt=""/></div>
            <div class="card"><img src="imagens/rede/LivroRede3.jpg" alt=""/></div>
            <div class="card"><img src="imagens/rede/LivroRede4.jpg" alt=""/></div>
            <div class="card"><img src="imagens/rede/LIvroRede5.jpg" alt=""/></div>
            <div class="card"><img src="imagens/rede/LivroRede6.jpg" alt=""/></div>
            <div class="card"><img src="imagens/rede/LivroRede7.jpg" alt=""/></div>
            <div class="card"><img src="imagens/rede/LivroRede8.jpg" alt=""/></div>
            <div class="card"><img src="imagens/rede/LivroRede9.jpg" alt=""/></div>
            <div class="card"><img src="imagens/rede/LivroRede10.jpg" alt=""/></div>
            <div class="card"><img src="imagens/rede/LivroRede11.jpg" alt=""/></div>
            <div class="card"><img src="imagens/rede/LivroRede12.jpg" alt=""/></div>   
        </div>

        <br>

        <h2 style="text-align: center;">Robotica</h2>

        <div class="carousel">
            
            <div class="card"><img src="imagens/robotica/LivroRobotica.jpg" alt=""/></div>
            <div class="card"><img src="imagens/robotica/LivroRobotica1.jpg" alt=""/></div>
            <div class="card"><img src="imagens/robotica/LivroRobotica2.jpg" alt=""/></div>
            <div class="card"><img src="imagens/robotica/LivroRobotica3.jpg" alt=""/></div>
            <div class="card"><img src="imagens/robotica/LivroRobotica4.jpg" alt=""/></div>
            <div class="card"><img src="imagens/robotica/LivroRobotica5.jpg" alt=""/></div>
            <div class="card"><img src="imagens/robotica/LivroRobotica6.jpg" alt=""/></div>
            <div class="card"><img src="imagens/robotica/LivroRobotica7.jpg" alt=""/></div>
            <div class="card"><img src="imagens/robotica/LivroRobotica8.jpg" alt=""/></div>
            <div class="card"><img src="imagens/robotica/LivroRobotica9.jpg" alt=""/></div>
            <div class="card"><img src="imagens/robotica/LivroRobotica10.jpg" alt=""/></div>
            <div class="card"><img src="imagens/robotica/LivroRobotica11.jpg" alt=""/></div>
            <div class="card"><img src="imagens/robotica/LivroRobotica12.jpg" alt=""/></div>   
        </div>

    <%
           }
        }
        else{
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
        <script src="js/Ativo.js"></script>
        <script src="js/jquery-3.6.0.min.js"></script>
        <script src="bootstrap/bootstrap.min.js"></script>
    </body>
</html>
