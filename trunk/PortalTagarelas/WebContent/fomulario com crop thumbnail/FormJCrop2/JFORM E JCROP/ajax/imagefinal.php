<?php
  session_start();
  sleep(1);
  include"../conexao/config.php";
    
  /// ID SESSÃO
  $id_session = 1;

  /// PEGA A IMAGEM ATUAL
  $sql = mysql_query("SELECT img FROM tab_upload WHERE id = '$id_session'") or die(mysql_error());
		 $res = mysql_fetch_array($sql);
				$img  = $res['img'];
				
?>
<img class="img_1" src="upload/thumb/<?php echo $img;?>" alt="" />