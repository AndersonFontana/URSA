<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <?php
       	$url = "http://localhost:8080/WsServidorRest/webresources/oportunidadesws/";
        $url .= "excluir/";
 	    $id = $_POST['excluir-codigo'];
        $url .= $id;
        $op =  file_get_contents($url);
        echo "Status : " . $op ;
        ?>
    </body>
</html>
