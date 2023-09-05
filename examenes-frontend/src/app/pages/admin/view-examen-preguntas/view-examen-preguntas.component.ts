import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { PreguntaService } from 'src/app/services/pregunta.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-examen-preguntas',
  templateUrl: './view-examen-preguntas.component.html',
  styleUrls: ['./view-examen-preguntas.component.css']
})
export class ViewExamenPreguntasComponent implements OnInit { 

  examenId:any; 
  titulo:any; 
  preguntas:any=[];//array


  constructor(
               private route:ActivatedRoute,
               private preguntaService: PreguntaService,
               private  snack:MatSnackBar)  { }

  ngOnInit(): void {  
    this.examenId=this.route.snapshot.params['examenId'];
    this.titulo=this.route.snapshot.params['titulo'];  
    //console.log(this.examenId);
    //console.log(this.titulo);
    this.preguntaService.listarPreguntasDelExamen(this.examenId).subscribe(
      (data:any)=>{
        console.log(data);
        this.preguntas=data;
      }, 
      (error)=>{ 
        console.log(error);

      }
    )
 
  } 

  public eliminarPregunta(preguntaId:any){
    Swal.fire({
      title:'EliminarPregunta',
      text:'¿Estás seguro, quieres eliminar ésta pregunta?',
      icon: 'warning',
      showCancelButton:true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor:'#d33',
      confirmButtonText: 'Eliminar',
      cancelButtonText:'Cancelar'
    }).then((resultado)=>{
       if(resultado.isConfirmed){
        this.preguntaService.eliminarPregunta(preguntaId).subscribe(
          (data)=>{
            this.snack.open('Pregunta eliminada','',{
            duration:3000 
          }) 
          this.preguntas= this.preguntas.filter((pregunta:any)=>pregunta.preguntaId!=preguntaId);  
       }, 
       (error)=>{
         this.snack.open('Error al eliminar la pregunta', '',{
           duration:3000
         })
       }
    )             
  }
    
  })
 } 
}
