<?php include"../conexao/config.php";?>

<div class="imagefile">
    <div class="preview"></div>
    
    <span class="fileinput_button">
        <img src="img/botao_input_file.png" alt="" />
        <form id="imageform" method="post" enctype="multipart/form-data" action="ajax/imageform.php">
            <input type="file" id="photoimg" name="photoimg" />
        </form>
    </span>
    
    <div class="fileinput_load"></div>
</div><!--imagefile-->