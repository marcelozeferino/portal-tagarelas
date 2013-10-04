<?php
  session_start();
  sleep(1);
  include"../conexao/config.php";
    
  /// ID SESSÃO
  $id_session = 1;
  
  /// POST
  $largura = $_POST['largura'];

  /// PEGA A IMAGEM ATUAL
  $sql_crop = mysql_query("SELECT temp FROM tab_upload WHERE id = '$id_session'") or die(mysql_error());
			  $res_crop = mysql_fetch_array($sql_crop);
						  $img_temp  = $res_crop['temp'];
						  
  if ($_SERVER['REQUEST_METHOD'] == 'POST'){
	  
	  /// PEGA O TIPO DE IMAGEM
	  $img_type_1  = $img_temp; $img_type_2  = explode('.', $img_type_1); $img_type_3  = $img_type_2['1'];
	  
	  /// DESTINO DA IMG RECORTADA E NOME QUE A IMAGEM VAI RECEBER
	  $pasta = "../upload/thumb/";
	  
	  /// NOME
	  $novo_name = $id_session."-".sha1(uniqid(rand(), true)).time().".".$img_type_3;
	  
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
		  
		  $del_img = mysql_query("SELECT img FROM tab_upload WHERE id = '$id_session'") or die(mysql_query());
			 $res_del = mysql_fetch_array($del_img);
						$delet = $res_del['img'];
						if($delet != "avatar.jpg"){
						$img_170 = "../upload/thumb/".$delet."";
						$del = unlink($img_170);
		 				}
						
		  mysql_query("UPDATE tab_upload SET img = '$novo_name' WHERE id = '$id_session'") or die(mysql_query());
		  
		  echo "<div style=\"float:left;color:#090;\">Foto editada com sucesso!</div>";
		  ?>
		  <script type="text/javascript"> 
			$(document).ready(function(){ 
		  		$(".cropform_load").hide(0);
				$(".img").html('');
				$.ajax({
					type: "POST",
					url: "ajax/imagefinal.php",
					data: '',
					cache: false,
					success: function(html){
						$(".img").append(html);						  
					}
				});
				$("#mask , #box_popup").fadeOut(300 , function(){
					$("#mask").remove();
					$("#box_popup").remove();
					$("body").append('<div id="box_popup" class="box_popup"></div>');
					$("#box_popup").append('<div class="bp_titulo"><div class="bp_titulo_1">Alterar Foto</div><span class="bp_fechar"><img src="img/fechar.png" alt="" /></span></div>');
					$("#box_popup").append('<div class="bp_load"><div class="bp_load_1"><img src="img/loader.gif" alt="" /> Carregando...</div></div>');
				}); 
			});
          </script>
		  <?php
		  exit;
	  }else{
		  echo "Falha ao enviar!";
		  ?>
		  <script type="text/javascript"> $(document).ready(function(){ $(".cropform_load").hide(0);});</script>
		  <?php
		  exit;
	  }
  }
?>