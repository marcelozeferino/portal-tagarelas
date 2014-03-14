function exibeMensagemUsuario(tipo, mensagem, idDiv) {
	var html = "";
	var css = "";

	if (tipo == "success") {
		css = "formee-msg-success";
	} else if (tipo == "notice") {
		css = "formee-msg-info";
	} else if (tipo == "warning") {
		css = "formee-msg-warning";
	} else {
		css = "formee-msg-error";
	}

	html += "<div class=\"" + css + "\" id=\"divMensagemInterna\">";
	html += "	<ul>";
	html += "		<li>" + mensagem + "</li>";
	html += "	</ul>";
	html += "	<p style=\"display: none\">Este banner sumir√° em <span id=\"contador\">4</span> segundos.</p>";
	html += "</div>";

	$("#" + idDiv).html(html);
}

function showToast(tipo, mensagem, fixa) {
	// success, notice, warning, error

	$().toastmessage('showToast', {
		text : mensagem,
		sticky : fixa,
		position : 'middle-center',
		type : tipo,
		closeText : '',
		close : function() {
			console.log("toast is closed ...");
		}
	});
}

function previewImagem(input) {
	var tipos = new Array("image/bmp","image/png","image/jpeg","image/gif");
	
	if (input.files && input.files[0]) {
		var arquivo = input.files[0];
		var tipo = arquivo.type;
		
		if($.inArray(tipo, tipos) == -1){
			input.value = "";
        	alert("Formato de arquivo inv·lido. Por favor, utilize somente imagens nos formatos JPG, JPEG, GIF, BMP ou PNG!");
		} else if (arquivo.size > 4194304){
			input.value = "";
        	alert("A imagem n„o pode ter mais que 4 MB (4096 KB)!");
		} else {
			var reader = new FileReader();

            reader.onload = function (e) {
                //$('#preview').attr('src', e.target.result);
            	$("#upload").removeAttr('rel');
            	$("#upload").attr('rel', e.target.result);
            };
            
            reader.readAsDataURL(input.files[0]);
		}
    }
}