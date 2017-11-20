<!DOCTYPE html>
<html lang="pt-br">


<?php
// define variables and set to empty lcg_value()
	
	$url = "http://localhost:8080/WsServidorRest/webresources/oportunidadesws/";
	$url1 = $url . "incluir/adicionar";

	$url2 = $url . "cargo/";
	$idcargo = $_POST['incluir-codigo-cargo'];
    $url2 .= $idcargo; 
    $cargo = file_get_contents($url2);     

  	$ingresso = strtotime($_POST['incluir-ingresso']);
    $dti = date("M d, Y h:i:00 A",$ingresso);
    $fechada = strtotime($_POST['incluir-fechada']);
    $dtf = date("M d, Y h:i:00 A",$fechada); 

    $json ="{\"codigo\":". $_POST['incluir-codigo'];
    $json.=",\"codcargo\":".$idcargo;
	$json.=",\"cargo\":". $cargo;
	$json.=",\"descricao\":\"".$_POST['incluir-descricao'] . "\"";
	$json.=",\"acesso\":".$_POST['incluir-acesso'];
	$json.=",\"ingresso\":\"".$dti . "\"" ;
	$json.=",\"fechada\":\"". $dtf . "\"";
	$json.="}";

 		$ch = curl_init();

       curl_setopt($ch, CURLOPT_URL, $url1);
       curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json','Content-Length: ' . strlen($json)));
       curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'POST');
       curl_setopt($ch, CURLOPT_POSTFIELDS,$json);
       curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
       $jsonRet = curl_exec($ch);
       echo  $jsonRet ;
	
 
  ?>
  