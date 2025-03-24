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

// Instanciar uma nova conexão
$conexao = new Conexao('127.0.0.1', '3306', 'investimentos', 'root', 'vertrigo');

// Conectar à base de dados
if($conexao->conectar()) {
    $query = "SELECT * FROM fundos";
    $fundos = $conexao->executar($query);
}
?>

<body>
    <div class="container">
        <div class="row mt-4 mb-4">
            <a href="cadastrarForm.php" class="btn btn-info mr-5" role="button">Cadastrar</a>
            <a href="logout.php" class="btn btn-info" role="button">Logout</a>
        </div>

        <?php
        if ($fundos) {
            echo "<table class='table table-striped'>";
            echo "<thead>
                    <tr>
                        <th scope='col'>Nome</th>
                        <th scope='col'>Ticker</th>
                        <th scope='col'>Valor</th>
                        <th scope='col'>Quantidade</th>
                        <th scope='col'>Data</th>
                    </tr>
                </thead>
                <tbody>";
            // Lógica para exibir os fundos recuperados no banco da tela
            foreach ($fundos as $fundo) {
                echo "<tr>
                        <td>" . $fundo['nome'] . "</td>
                        <td>" . $fundo['ticker'] . "</td>
                        <td>" . $fundo['valor'] . "</td>
                        <td>" . $fundo['quantidade'] . "</td>
                        <td>" . $fundo['data'] . "</td>
                    </tr>";
            }
            // Desconectar da base de dados
            $conexao->desconectar();

            echo "</tbody></table>";
        }
        ?>
    </div>
</body>
</html>
