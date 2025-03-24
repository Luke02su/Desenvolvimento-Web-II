<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Document</title>
</head>
<?php
session_start();
if (!isset($_SESSION['logado'])){
    header('Location: login.php');
} 
require("Conexao.php");
require("FundoImobiliario.php");

// Verifica se o formulário foi submetido
if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    // Coleta os dados do formulário
    $nome = isset($_POST['nome']) ? $_POST['nome'] : '';
    $ticker = isset($_POST['ticker']) ? $_POST['ticker'] : '';
    $valor = isset($_POST['valor']) ? $_POST['valor'] : '';
    $quantidade = isset($_POST['quantidade']) ? $_POST['quantidade'] : '';
    $data = isset($_POST['data']) ? $_POST['data'] : '';

    // Instanciar uma nova conexão
    $conexao = new Conexao('127.0.0.1', '3306', 'investimentos', 'root', 'vertrigo');

    // Conectar à base de dados
    if ($conexao->conectar()) {

        // Preparar a consulta SQL de inserção
        $query = "INSERT INTO fundos (nome, ticker, valor, quantidade, data) 
                  VALUES ('$nome', '$ticker', '$valor', '$quantidade', '$data')";

        // Executar a inserção de um fundo no banco de dados
        if ($conexao->executar($query)) {
            echo "<p style='color: green;'>Fundo cadastrado com sucesso!</p>";
        } else {
            echo "<p style='color: red;'>Erro ao cadastrar fundo. Tente novamente.</p>";
        }

        // Desconectar da base de dados
        $conexao->desconectar();
    } else {
        echo "<p style='color: red;'>Erro de conexão com o banco de dados.</p>";
    }
}
?>


<body>
<div class="container">
    <form role="form" class="mt-5" method="post" action="cadastrarForm.php">
        <!-- Nome -->
        <div class="form-group row">
            <label for="nome" class="col-sm-2 col-form-label">Nome:</label>
            <div class="col-sm-10">
                <input type="text" id="nome" name="nome" class="form-control" required>
            </div>
        </div>

        <!-- Ticker -->
        <div class="form-group row">
            <label for="ticker" class="col-sm-2 col-form-label">Ticker:</label>
            <div class="col-sm-10">
                <input type="text" id="ticker" name="ticker" class="form-control" required>
            </div>
        </div>

        <!-- Valor -->
        <div class="form-group row">
            <label for="valor" class="col-sm-2 col-form-label">Valor:</label>
            <div class="col-sm-10">
                <input type="number" id="valor" name="valor" class="form-control" required>
            </div>
        </div>

        <!-- Quantidade -->
        <div class="form-group row">
            <label for="quantidade" class="col-sm-2 col-form-label">Quantidade:</label>
            <div class="col-sm-10">
                <input type="number" id="quantidade" name="quantidade" class="form-control" step="1" required>
            </div>
        </div>

        <!-- Data -->
        <div class="form-group row">
            <label for="data" class="col-sm-2 col-form-label">Data:</label>
            <div class="col-sm-10">
                <input type="text" id="data" name="data" class="form-control" required>
            </div>
        </div>

        <!-- Botão de envio -->
        <div class="form-group row">
            <div class="offset-sm-2 col-sm-10">
                <input type="submit" value="Cadastrar Fundo" class="btn btn-primary" />
            </div>
        </div>
    </form>
</div>
</body>