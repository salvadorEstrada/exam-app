package com.examenes.app.excepciones;

public class UsuarioNotFoundException extends Exception {

    //Mensaje por defecto
    public UsuarioNotFoundException(){
        super("El usuario con ese username no existe en la Bd, vuelva a intentar");
    }
    //POr si le quiero pasar un mensaje personalizado
    public UsuarioNotFoundException(String mensaje){
        super(mensaje);
    }
}
