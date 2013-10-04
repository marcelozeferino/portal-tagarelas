<?php include"../conexao/config.php";?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Upload de Imagens com jCrop</title>
<link rel="stylesheet" type="text/css" href="../css/imagecrop.css"/>
<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../js/jquery.Jcrop.js"></script>

<script language="Javascript">
  /// PEGA AS DISMENSÕES DA IMAGEM PARA RECORTE
  $(function(){
	  $('#cropbox').Jcrop({
		  aspectRatio: 1,
		  setSelect: [ 0, 0, 170, 170 ],
		  bgOpacity: 0.7,
		  bgColor: '#000',
		  addClass: 'jcrop-light',
		  onSelect: updateCoords
	  });
  });
  function updateCoords(c){
	  $('#x').val(c.x);
	  $('#y').val(c.y);
	  $('#w').val(c.w);
	  $('#h').val(c.h);
  };
</script>
</head>

<body>
<div id="conteudo">
  <div class="titulo">Upload de Imagens com jCrop</div>
  <div class="bloco">
	<?php
    /// PEGA A IMAGEM
    $sql_crop = mysql_query("SELECT temp FROM tab_imagecrop ORDER BY id DESC LIMIT 1") or die(mysql_error());
                $res_crop = mysql_fetch_array($sql_crop);
                            $img_temp  = $res_crop['temp'];
    ?>
    <img id="cropbox" src="<?php echo "../upload/temp/".$img_temp;?>" alt="" />
  </div>
  <form id="cropform" method="post" enctype="multipart/form-data" action="imagethumb.php">
      <input type="hidden" id="x" name="x" />
      <input type="hidden" id="y" name="y" />
      <input type="hidden" id="w" name="w" />
      <input type="hidden" id="h" name="h" />
      <input type="submit" class="cropform_btn" id="botao" value="Definir fotografia" />
  </form>
</div><!--conteudo-->
</body>
</html>