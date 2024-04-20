<template>
    <main>
        <!-- Barra de navegação do Bootstrap -->
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">   
                <!-- Logo e título da aplicação -->
                <a class="navbar-brand" href="/">
                    <img src="../assets/logo.png" alt="Logo" style="max-height: 30px; margin-right: 10px;">
                    To Do List
                </a>
                <!-- Botão de menu responsivo para celular -->
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <!-- Links de navegação -->
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <!-- Link para a lista de tarefas -->
                        <li class="nav-item">
                            <a class="nav-link" href="/">Lista de Tarefas</a>
                        </li>
                        <!-- Link para adicionar uma nova tarefa -->
                        <li class="nav-item">
                            <a class="nav-link" href="/add">Adicionar Tarefa</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </main>
</template>
