<?php
	function getNews($time) {
		$host =     "ec2-184-73-189-190.compute-1.amazonaws.com";
		$database = "df4dv32d0ha20c";
		$user =     "jgnctpthhqngqk";
		$port =     "5432";
		$password = "6a4f0f2a99e6d6109658af60edced243a6fa8c2ff9eac54d474be2cc584303bd";

		$str = "host=$host port=$port dbname=$database user=$user password=$password";
		$db = pg_connect($str) or die('connection failed');

		$time = date('Y-m-d H:i:s', $time);

		$sql = "SELECT o.codigo, o.descricao, o.acesso, o.codcargo, c.descricao as cdescricao, o.fechada FROM Oportunidade o, Cargo c WHERE o.ingresso > '$time' AND o.codcargo = c.codcargo;";

		$result = pg_query($db, $sql);

		pg_close($db);

		return pg_fetch_all($result);
	}

	// echo "OK\n";

	if (isset($_GET['time'])) {
		$time = $_GET['time'];
	}
	elseif (isset($_GET['delay'])) {
		$time = time() - $_GET['delay'];
	}
	else {
		$time = time() - 5;
	}

	$data = getNews($time);

	foreach ($data as $key => $arr) {
		echo "Oportunidade '". $arr['descricao'] ."' {codigo=".$arr['codigo'] .", acesso=". $arr['acesso'] .", codigo cargo=". $arr['codcargo'] .", cargo='". $arr['cdescricao'] ."', data de fechamento='". $arr['fechada'] ."'}\n";
	}
?>
