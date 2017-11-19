<!DOCTYPE html>
<html lang="pt-br">


<?php
// define variables and set to empty lcg_value()
	
	$url = "http://localhost:8080/WsServidorRest/webresources/oportunidadesws/";
	$url = "incluir/adicionar";

	$url2 = $url . "cargo/";
	$idcargo = $_POST['incluir-codigo-cargo"'];
    $url2 .= $idcargo; 
    $ch = curl_init($url2);
    curl_setopt($ch, CURLOPT_HTTPGET, 1);
    curl_setopt($ch, CURLOPT_HEADER, 0);
  	$cargo =curl_exec($ch) ;      

  	$ingresso = strtotime($_POST['incluir-ingresso']);
    $dti = date("M d, Y h:i:00 A",$ingresso);
    $fechada = strtotime($_POST['incluir-fechada']);
    $dtf = date("M d, Y h:i:00 A",$fechada); 

    $json ="{\"codigo\":". $_POST['incluir-codigo'];
    $json.=",\"codcargo\":".$idcargo;
	$json.=",\"cargo\":". $cargo;
	$json.=",\"descricao\":".$_POST['incluir-descricao'];
	$json.=",\"acesso\":".$_POST['incluir-acesso'];
	$json.=",\"ingresso\":\"".$dti . "\"" ;
	$json.=",\"fechada\":\"". $dtf . "\"";
	$json.="}";

	$ch = curl_init($url1);


    curl_setopt($ch, CURLOPT_POST, 1);
    curl_setopt($ch, CURLOPT_HTTPHEADER,array('Content-Type: application/json'));
    curl_setopt($ch, CURLOPT_POSTFIELDS, $json);

    $jsonRet = curl_exec($ch);
    echo  $jsonRet ;
	
 
  ?>
  