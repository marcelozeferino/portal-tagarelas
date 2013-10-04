<?php include"../conexao/config.php";?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Upload de Imagens com jCrop</title>
<link rel="stylesheet" type="text/css" href="../css/imagethumb.css"/>
</head>

<body>
<?php
  /// PEGA A IMAGEM TEMP
  $sql_crop = mysql_query("SELECT temp FROM tab_imagecrop ORDER BY id DESC LIMIT 1") or die("Erro ao selecionar");
			  $res_crop = mysql_fetch_array($sql_crop);
						  $img_temp  = $res_crop['temp'];
						  
  if ($_SERVER['REQUEST_METHOD'] == 'POST'){
	  
	  /// PEGA O TIPO DE IMAGEM
	  $img_type_1  = $img_temp; $img_type_2  = explode('.', $img_type_1); $img_type_3  = $img_type_2['1'];
	  
	  /// DESTINO DA IMG RECORTADA E NOME QUE A IMAGEM VAI RECEBER
	  $pasta = "../upload/thumb/";
	  
	  /// NOME
	  $novo_name = sha1(uniqid(rand(), true)).time().".".$img_type_3;
	  
	  $targ_w_170 = $targ_h_170 = 170;
  
	  $src = "../upload/temp/".$img_temp."";
	  
	  if($img_type_3 == "png"){ $img_r = imagecreatefrompng($src);}
	  elseif($img_type_3 == "gif"){ $img_r = imagecreatefromgif($src);}
	  else{ $img_r = imagecreatefromjpeg($src);}
	  
	  $dst_r_170 = imagecreatetruecolor( $targ_w_170, $targ_h_170);
  
	  imagecopyresampled($dst_r_170,$img_r,0,0,$_POST['x'],$_POST['y'],$targ_w_170,$targ_h_170,$_POST['w'],$_POST['h']);
  
	  if($img_type_3 == "png"){ $imagetype = imagepng($dst_r_170,$pasta.$novo_name);}
	  elseif($img_type_3 == "gif"){ $imagetype = imagegif($dst_r_170,$pasta.$novo_name);}
	  else{ $imagetype = imagejpeg($dst_r_170,$pasta.$novo_name,80);}
	  
	  if($imagetype){
						
		  mysql_query("UPDATE tab_imagecrop SET img = '$novo_name' ORDER BY id DESC LIMIT 1") or die("Erro ao selecionar");
	  }else{
		  echo "Falha ao enviar!";
		  exit;
	  }
  }
?>

<div id="conteudo">
  <div class="titulo">Upload de Imagens com jCrop</div>
  <div class="bloco">
	<?php
    /// PEGA A IMAGEM THUMB
    $sql = mysql_query("SELECT img FROM tab_imagecrop ORDER BY id DESC LIMIT 1") or die("Erro ao selecionar");
           $res = mysql_fetch_array($sql);
                  $img = $res['img'];
    ?>
    <img src="<?php echo "../upload/thumb/".$img;?>" alt="" />
  </div>
</div><!--conteudo-->
</body>
</html>