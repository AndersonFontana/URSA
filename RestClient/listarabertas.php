<!DOCTYPE html>
<html lang="pt-br">




<?php
// define variables and set to empty lcg_value()

if (  $_SERVER["REQUEST_METHOD"] == "POST")
    $url = "http://localhost:8080/WsServidorRest/webresources/oportunidadesws/";
    $url .= "listaaberta/";
    $url .=  $_POST['listarabertas'];
    $jsonobj = json_decode( file_get_contents($url));
    if(isset($jsonobj)){
 
    foreach($jsonobj as $op) {
     echo  "Código : ".$op->{"codigo"} ;
     echo "<br>";
     echo  "Cargo : " .$op->{"codcargo"}; 
     echo "<br>";
     echo "Descricao : " .$op->{"descricao"};
     echo "<br>";
     echo "Acesso : ".$op->{"acesso"} ;
     echo "<br>";
     echo "Ingresso em : ".$op->{"ingresso"};
     echo "<br>";
     echo "Fechado em : ".$op->{"fechada"};
     echo "<br>";

       }
    }  
    else 
        echo 'null';

  ?>
  
  
