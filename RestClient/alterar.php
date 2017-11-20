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
        $url1 = $url . "altera";
        $url2 = $url . "cargo/";

        $idcargo = $_POST['alterar-codigo-cargo'];
        $url2 .= $idcargo; 
        $cargo = file_get_contents($url2); 
         
        $ingresso = strtotime($_POST['alterar-ingresso']);
        $dti = date("M d, Y h:i:00 A",$ingresso);
        $fechada = strtotime($_POST['alterar-fechada']);
        $dtf = date("M d, Y h:i:00 A",$fechada);

  
        $json ="{\"codigo\":". $_POST['alterar-codigo'];
        $json.=",\"codcargo\":".$idcargo;
        $json.=",\"cargo\":". $cargo;
        $json.=",\"descricao\":\"".$_POST['alterar-descricao']."\"";
        $json.=",\"acesso\":".$_POST['alterar-acesso'];
        $json.=",\"ingresso\":\"".$dti . "\"" ;
        $json.=",\"fechada\":\"". $dtf . "\"";
        $json.="}";

        
       $ch = curl_init();

       curl_setopt($ch, CURLOPT_URL, $url1);
       curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json','Content-Length: ' . strlen($json)));
       curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'PUT');
       curl_setopt($ch, CURLOPT_POSTFIELDS,$json);
       curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
       $jsonRet = curl_exec($ch);
       echo  $jsonRet ;

     
        
       
        ?>
    </body>
</html>
