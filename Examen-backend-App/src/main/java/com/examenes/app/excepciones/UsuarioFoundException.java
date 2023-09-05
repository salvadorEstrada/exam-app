package com.examenes.app.excepciones;

public class UsuarioFoundException extends Exception{
    //Mensaje por defecto
    public UsuarioFoundException(){
        super("El usuario con ese nombre ya existe en la Bd, vuelva a intentar");
    }
   //POr si le quiero pasar un mensaje personalizado
    public UsuarioFoundException(String mensaje){
       super(mensaje);
    }
}
