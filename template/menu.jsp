<header>
    <nav class="navbar navbar-expand-lg" style="background-color: white; padding: 5px; display: flex; align-items: center;">
        <div class="container-fluid">
            <a class="navbar-brand" href="index2.jsp">
                <img src="imagens/BibliotecaJR2.png" width="30" height="24" class="d-inline-block align-text-top">
                Biblioteca
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse " id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-auto">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="index2.jsp"><button class="btn btn-primary" style="background-color: seagreen">Inicio</button></a>
                    </li>
                     <li class="nav-item dropdown">
                        <a class="nav-link dropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <button class="btn btn-primary" style="background-color: seagreen">Acervo</button>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="Livro.jsp">Barca</a></li>
                            <li><a class="dropdown-item" href="listadeLivro.jsp">Lista</a></li>
                            <li><a class="dropdown-item" href="cadastrarLivro.jsp">Cadastrar</a></li>
                            <li><a class="dropdown-item" href="CadastrarEmprestimo.jsp">Cosultar</a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <button class="btn btn-primary" style="background-color: seagreen">Usuario</button>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="cadastrarUsuario.jsp">Cadastrar</a></li>
                            <li><a class="dropdown-item" href="listadeUsuario.jsp">Lista</a></li>
                            <li><a class="dropdown-item" href="cosultarUsuario.jsp">Cosultar</a></li>
                        </ul>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <button class="btn btn-primary" style="background-color: seagreen">Conta</button>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="Conta.jsp">Perfil</a></li>
                            <li><form action="${pageContext.request.contextPath}/logout" method="post">
                                    <button type="submit" class="dropdown-item">Sair</button></form></li>
                        </ul>
                    </li>
                </ul>                
            </div>

            <div id="Horas" class="ml-lg-auto">
                <p id="segundos"></p>
                <script src="js/Tempo.js"></script>
            </div>
        </div>
    </nav>

</header>
