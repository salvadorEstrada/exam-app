import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HTTP_INTERCEPTORS } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { LoginService } from "./login.service";

@Injectable()
export class AuthInterceptor implements  HttpInterceptor{

    constructor(private loginService: LoginService){

    }
 
    //A al cabecera se agrega el 'Bearer' ,está modificacndo la petición
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let authReq = req;
        const token = this.loginService.getToken(); 

        if(token !=null){
            authReq =authReq.clone({
                setHeaders: {Authorization: `Bearer ${token}`} //Agregarle el Bearer al token
            })
        } 
        return next.handle(authReq);
    }
    
} 

export const authInterceptorProviders=[
    {
        provide : HTTP_INTERCEPTORS,
        useClass: AuthInterceptor,
        multi: true //permite agregar la cantidad de interceptores que deseemos
    }
]