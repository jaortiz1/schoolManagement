$(document).ready(function(){
		//libreria formateo fechas
	/*$('[data-toggle="datepicker"]').datepicker();
	$("#guardar").click(function() {
		var regExp = /^\d{2}\/\d{2}\/\d{4}$/;
		var fecha = $("#anioEdicion");
		if (!regExp.test(fecha)) {
			alert("formato de fecha invalido");
			return false;
		}*/
	
	
	
	changePageAndSize();
	//




    //$('#myTable').DataTable();
	
    //pagina crear cuenta, al darle al boton crear se abre el formulario de creacion de cuenta
    $("#botonCrear").click(function(){
      //mostramos lentamente el formulario creacion cuenta
        $("#creacionCuenta").show(600);
        //se quita la publiciad para que no haya sonido de por medio

        $("#publicidad2").hide();
       
    });


    //mostrar formulario al darle al boton seguridad
    $("#direccion").click(function(){
      //ocultamos el formulario datos personales y de seguridad
      $("#datosPersonales").hide();
      $("#datosSeguridad").hide();
      //mostramos lentamente el formulario de la direccion
      $("#datosDireccion").show(600);
        

        
       
    });
        //mostrar formulario al darle al boton seguridad

    $("#personal").click(function(){

      //se quita el formulario de
      $("#datosDireccion").hide();
      $("#datosSeguridad").hide();
      //mostramos lentamente el formulario de datos personal
      $("#datosPersonales").show(600);
      

        
       
    });
    //mostrar formulario al darle al boton seguridad
    $("#seguridad").click(function(){

      //se quita el formulario de
      $("#datosDireccion").hide();
      $("#datosPersonales").hide();
      //mostramos lentamente el formulario de datos personal
      $("#datosSeguridad").show(600);
      

        
       
    });

    //hover de las filas
    $("tr").hover(function() {
      $(this).css("background-color", "#96DBF7FF");},
      function() {

      $(this).css("background-color", "white");
    });


    //pagina editar usuario, para que se quite el fieldset disabled y los botones roten en disabled y enabled

    $("#editar").click(function() {
      $(this).prop("disabled", true);
      $("#guardar").prop("disabled", false);
      $(".formularioEditar").prop("disabled", false);
    });

    /*Pagina de mis pedidos ocultar detalles o mostrarlos.*/
    $("a.botonMasInformacion").click(function() {
      var $padreTr = $(this).parents("div.posicion");
      $padreTr.toggle();
     
     // $(this).next().toggle();
    });
    
    $("a.botoncitoMasInformacion").click(function() {
       
       
       $(this).next().toggle();
      });
    
    
   
    //se comprueba que el segundo correo coincide con el primero
    $("input#correo2").blur(function() {
      if ($("input#correo1").val()==$(this).val()) {
          $(this).addClass("valid");
          $(this).removeClass("invalid");
          $(".error").remove();
          $(".error2").remove();
      }else{
        $(".error2").remove();
         $(this).addClass("invalid");
         $(this).removeClass("valid");
         $(this).after("<span class='error2' style='color:red;'>Los correos deben coincidir</span>");
      }
    });

   
     //se comprueba que las password coinciden
     $("input#pwd2").blur(function() {
      if ($("input#pwd1").val()==$(this).val()) {
          $(this).addClass("valid");
          $(this).removeClass("invalid");
          $(".error").remove();
          $(".error2").remove();
      }else{
        $(".error2").remove();
         $(this).addClass("invalid");
         $(this).removeClass("valid");
         $(this).after("<span class='error2' style='color:red;'>Las contraseñas deben coincidir</span>");
      }
      
      
    });

     $("input#telefono").blur(function() {
      var resultado =/^([0-9]+){9}$/;
      var $telefono = $("input#telefono").val();
      if(resultado.test($telefono)){
        $(this).addClass("valid");
        $(this).removeClass("invalid");
        $(".error1").remove();
      }else{
        $(".error1").remove();
        $(this).addClass("invalid");
        $(this).removeClass("valid");
        $("input#telefono").after("<span class='error1' style='color:red;''>Teléfono incorrecto.</span>");
      }
      
      
    });
     
     
     
     
     ///////////////////////////FORMATEO CAMPOS////////////////////

     //////USUARIOS//////
     //correo 1
     $("input#correo1").blur(function() {
      var $correo1 = $(this).val();
      var minuscula = cambiarAMinuscula($correo1);
      $(this).val(minuscula);
     });

     //correo2
     $("input#correo2").blur(function() {
      var $correo2 = $(this).val();
      var minuscula = cambiarAMinuscula($correo2);
      $(this).val(minuscula);
     });
     //nombre
      $("input#nombre").blur(function() {
      var $nombre = $(this).val();
      var minuscula = cambiarAMayuscula($nombre);
      $(this).val(minuscula);
     });
     //apellido1
     $("input#primerApellido").blur(function() {
      var $primerApellido = $(this).val();
      var minuscula = cambiarAMayuscula($primerApellido);
      $(this).val(minuscula);
     });
     //apellido2
     
     $("input#segundoApellido").blur(function() {
      var $segundoApellido = $(this).val();
      var minuscula = cambiarAMayuscula($segundoApellido);
      $(this).val(minuscula);
     });

     ////////LIBROS////////
     /* var $editorial = $("input#editorial").val();
    var $titulo = $("input#titulo").val();
    var $isbn = $("input#isbn").val();
    var $autor = $("input#autor").val();
    var $editorial = $("input#editorial").val();
    var $genero = $("input.genero").val();
    var $numeroPagina = $("input#numeroPagina").val();
    var $precioBase = $("input#precioBase").val();
    var $descuento = $("input#descuento").val();
     */
     //editorial
     $("input#editorial").blur(function() {
      var $editorial = $(this).val();
      var minuscula = cambiarAMayuscula($editorial);
      $(this).val(minuscula);
     });
     //titulo
      $("input#titulo").blur(function() {
      var $titulo = $(this).val();
      var minuscula = cambiarAMayuscula($titulo);
      $(this).val(minuscula);
     });
      //autor
      $("input#autor").blur(function() {
      var $autor = $(this).val();
      var minuscula = cambiarAMayuscula($autor);
      $(this).val(minuscula);
     });


    /////////////////////////////////VALIDACIONES/////////////////////////////
    $("form#creacionUsuarioFormulario").submit(function (e) {
    var validacionFallida = false;
   //Aquí haces tu validación de los campos que sean, si alguno es incorrecto, pones validacionFallida a true
    
    //VALIDACION CORREO
    var resultado =checkInput(".correo", "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,4}$");
    

    var $correo1 = $("input#correo1").val();
   
    
    var $correo2 = $("input#correo2").val();
    
    if($correo1!=$correo2){
      
      validacionFallida=true;
    }
   
    //FIN VALIDACION CORREO

    //VALIDACION PASSWORD

    var $pass1 = $("input#pwd1").val();
    var $pass2 = $("input#pwd2").val();
    
    if($pass1!=$pass2){
      
      validacionFallida=true;
    }

    //FIN VALIDACION PASSWORD



    //VALIDACION NOMBRE
    var expresionRegular3 = /^[a-zA-Z]+(\s*[a-zA-Z]*)*[a-zA-Z]+$/;
    var $nombre = $("input#nombre").val();
    
    if(!expresionRegular3.test($nombre)){
      //alert("nombre con la primera en mayusculas")
     validacionFallida=true;
     $(".errorNombre").remove();
     $("input#nombre").after("<span class='errorNombre' style='color:red;'>El nombre solo puede contener letras</span>");
     
    }
    


    //FIN VALIDACION NOMBRE

    //validacion apellido
    var $apellido1 = $("input#apellidos").val();
   
    if(!expresionRegular3.test($apellido1)){
      
      validacionFallida=true;
      $(".errorApellidos").remove();
       $("input#apellidos").after("<span class='errorApellidos' style='color:red;'>Los apellidos solo puede contener letras</span>");
    }
   

    //fin validacion apellido

    //validacion dni

    var expresionRegular4 = /^\d{8}[a-zA-Z]$/;
    var $dni = $("input#dni").val();
    if(!expresionRegular4.test($dni)){
      validacionFallida=true;
    }
    //fin validacion dni
   


    //si alguna validacion fallase no se deja enviar el formulario
    if (validacionFallida) {
      e.preventDefault();
      return false;
   }
    

    /*$("input#correo2").blur(function() {
      if ($("input#correo1").val()==$(this).val()) {
          $(this).addClass("valid");
          $(this).removeClass("invalid");
          $(".error").remove();
          $(".error2").remove();
      }else{
        $(".error2").remove();
         $(this).addClass("invalid");
         $(this).removeClass("valid");
         $(this).after("<span class='error2' style='color:red;'>Los correos deben coincidir</span>");
      }
    });*/













   
    
   


  });
    //validar editar perfill parte password
    $("form#editarPassword").submit(function (e) {
    	alert("holi")
        var validacionFallida = false;
        var $pass1 = $("input#pwd3").val();
        var $pass2 = $("input#pwd4").val();
        
        if($pass1!=$pass2){
          
          validacionFallida=true;
        }
        if (validacionFallida) {
            
            e.preventDefault();
            return false;
         }
       


      });

    //formulacioCreacionLibro
    
    
    //validar login
    $("form#formularioLogin").submit(function (e) {
    	
    	 //VALIDACION CORREO
    	
    	var resultado =checkInput(".correo", "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,4}$");
        var validacionFallida = false;
        if(!resultado){
        	validacionFallida=true;
        }
    	
    	

       
        if (validacionFallida) {
            
            e.preventDefault();
            return false;
         }
       


      });
    
  //validar login creacioncuenta
    $("form#formularioLogin2").submit(function (e) {
    	
    	 //VALIDACION CORREO y password
    	//correo
    	var resultado =checkInput(".correo2", "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,4}$");
        var validacionFallida = false;
        if(!resultado){
        	validacionFallida=true;
        }
        var $correo1 = $("input#correo10").val();
        var $correo2 = $("input#correo11").val();
        if($correo1!=$correo2){
        	validacionFallida=true;
        	
        }
        
        //password
        var pass1 = $("input#pwd10").val();
        var pass2 = $("input#pwd11").val();
    	if(pass1!=pass2){
    		validacionFallida=true;
    		
    	}
    	

       
        if (validacionFallida) {
            
            e.preventDefault();
            return false;
         }
       


      });

  $("form#formulacioCreacionLibro").submit(function (e) {
    var validacionFallida=false;
    
    //recogida de valores
    var $editorial = $("input#editorial").val();
    var $titulo = $("input#titulo").val();
    var $isbn = $("input#isbn").val();
    var $autor = $("input#autor").val();
    var $editorial = $("input#editorial").val();
    var $genero = $("input.genero").val();
    var $numeroPagina = $("input#numeroPagina").val();
    var $precioBase = $("input#precioBase").val();
    var $descuento = $("input#descuento").val();
   
    //validacion del titulo
    var expresionRegular3 = /^([A-ZÑÁÉÍÓÚ]+[^\s]?(,?\s?)?)*$/;
    if(!expresionRegular3.test($titulo)){
      alert("titulo");
      validacionFallida=true;
    }
    //fin validacion del titulo


    //validacion isbn
    var expresionRegular4 = /^(97(8|9))?\d{9}(\d|X)$/;
    if(!expresionRegular4.test($isbn)){
      alert("isbn");
      validacionFallida=true;
    }
    //fin validacion isbn


    //validacion autor
    
     if(!expresionRegular3.test($autor)){
      alert("autor");
      validacionFallida=true;
    }

    //fin validacion autor
     
     //validacion editorial
     if(!expresionRegular3.test($editorial)){
         alert("editorial");
         validacionFallida=true;
       }
     //fin validacion editorial




    //validacion genero

    //fin validacion genero


    //validacion numeroPagina
    if ($numeroPagina<=0 || $numeroPagina >10000) {
      alert("numero pagina");
      validacionFallida=true;
    }

    //fin validacion numeroPagina

    //validacion checkbox

    var $checked = $("input[type=checkbox]:checked").length;

      if(!$checked) {
        alert("no se eligio un checkbox")
        validacionFallida=true;
        
      }
    //fin validacion checkbox


    //validacion precioBase
     if ($precioBase<=0 || $precioBase >10000) {
      alert("precio base");
      validacionFallida=true;
    }


    //fin validacion precioBase


    //validacion descuento
     if ($descuento<=0 || $descuento >200) {
      alert("descuento");
      validacionFallida=true;
    }
    //fin validacion descuento
    
    if (validacionFallida) {
      
      e.preventDefault();
      return false;
   }
   
    

  });

  
  //FILTRAR POR GENERO LITERARIO

  
  $("#select" ).change(function() {
  var $valor = $(this).val();
  $valor = String($valor);
  $valor = $valor.toLocaleLowerCase();
  var $divs = $("div.librito");
  var $span;
  var $texto;
  var $genero;
  var $resultado;
  var $padreDiv;
  var $padresDiv;
  //var $generos = $("span.genero");
  
  if($valor=="ninguno"){
	  $padresDiv = $divs.parent();
	  $padresDiv.removeClass("oculto");
  }else{
	  for (var i = $divs.length - 1; i >= 0; i--) {
		    $span = $divs.eq(i).find("span.genero");
		    $span = $span.text();
		    $span = $span.toLocaleLowerCase();
		    $span = $span.replace("[", "");
		    $span = $span.replace("]", "");
		    
		    if($span.includes($valor)){
		    	$padreDiv = $divs.eq(i).parent();
		    	$padreDiv.removeClass("oculto");
		    }else{
		    	$padreDiv = $divs.eq(i).parent();
		    	$padreDiv.addClass("oculto");
		    }
		  }
  }
  
});
  
  


    


});

/*funcion que devuelve true si se cumple con la expresion regular*/
function checkInput(idInput, pattern) {
return $(idInput).val().match(pattern) ? true : false;
}
function cambiarAMinuscula(texto) {
  return texto.toLowerCase();
}
function cambiarAMayuscula(texto) {
  return texto.toUpperCase();
}

function changePageAndSize() {
	$('#pageSizeSelect').change(function(evt) {
		window.location.replace("/ebook/?pageSize=" + this.value + "&page=1");
	});
}

