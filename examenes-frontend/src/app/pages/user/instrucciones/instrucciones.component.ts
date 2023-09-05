import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ExamenService } from 'src/app/services/examen.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-instrucciones',
  templateUrl: './instrucciones.component.html',
  styleUrls: ['./instrucciones.component.css']
})
export class InstruccionesComponent implements OnInit { 

  examenId:any; 
  examen:any=new Object(); // para evitar error, Cannot read properties of undefined (reading 'titulo')

  constructor(
              private examenService: ExamenService,
              private route:ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void { 
    this.examenId =  this.route.snapshot.params['examenId'] // examenId de la ruta 
    //Ahora ,lo busco a través del servicio 
    this.examenService.obtenerExamen(this.examenId).subscribe(
      (data)=>{
        this.examen=data;
      },
      (error)=>{
        console.log(error);
      }
    )
  } 

  public empezarExamen(){
    Swal.fire({
      title:'¿Quieres comenzar el examen?',
      showCancelButton:true,
      cancelButtonText:'Cancelar',
      confirmButtonText:'Empezar',
      icon:'info',

    }).then((result:any)=>{
      if(result.isConfirmed){
        this.router.navigate(['/start/'+this.examenId])
      }
    })
  }

}
