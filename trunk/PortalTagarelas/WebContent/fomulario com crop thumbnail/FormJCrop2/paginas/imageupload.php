<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Upload de Imagens com jCrop</title>
</head>

<body>
<?php 
  include"../conexao/config.php";

  /// POST
  $img = $_FILES['photoimg'];

  if(!empty($img)){

  /// VALIDAÇÃO DA IMAGEM
  $_Up['pasta'] = '../upload/temp/';
  $_Up['tamanho'] = 512 * 512 * 2;
  $_Up['extensoes'] = array ('jpg', 'jpeg', 'png', 'gif', 'JPG', 'JPEG', 'PNG', 'GIF');
  $_Up['renomeia'] = true;
  
  $extensao = strtolower(end(explode('.', $_FILES['photoimg']['name'])));
	  
  $tam_name = getimagesize($_FILES['photoimg']['tmp_name']);

  if(array_search($extensao, $_Up['extensoes']) === false){
	  echo "<script>javascript:alert('Formato da imagem não e válido!')</script>";
      echo "<script>javascript:open('../index.php', '_top')</script>";
  }elseif($_Up['tamanho'] < $_FILES['photoimg']['size']) {
	  echo "<script>javascript:alert('Imagem muito grande!')</script>";
      echo "<script>javascript:open('../index.php', '_top')</script>";
  }elseif($tam_name[0] < '100') {
	  echo "<script>javascript:alert('A imagem deve ter largura maior que 100 px!')</script>";
      echo "<script>javascript:open('../index.php', '_top')</script>";
  }elseif($tam_name[1] < '100') {
	  echo "<script>javascript:alert('A imagem deve ter altura maior que 100 px!')</script>";
      echo "<script>javascript:open('../index.php', '_top')</script>";
  }else{
	  
  /// RENOMEIA A IMAGEM
  $novo_nome = md5(time()).".".$extensao;

  /// MOVE A IMAGEM PARA PASTA
  if(move_uploaded_file($_FILES['photoimg']['tmp_name'], $_Up['pasta'] . $novo_nome)){
  
  /// CADASTRA A IMAGEM	
  $cad = mysql_query("INSERT into tab_imagecrop (temp) values ('$novo_nome')") or die("Erro ao consultar");

					
  if($cad >= '1'){
	  	echo "<script>javascript:open('imagecrop.php', '_top')</script>";
  }else{
	  echo "<script>javascript:alert('Erro ao enviar!')</script>";
      echo "<script>javascript:open('../index.php', '_top')</script>";
		}
	  }
	}  
  }else{
	  echo "<script>javascript:alert('Informe a imagem!')</script>";
      echo "<script>javascript:open('../index.php', '_top')</script>";	  
  }
?>
</body>
</html>