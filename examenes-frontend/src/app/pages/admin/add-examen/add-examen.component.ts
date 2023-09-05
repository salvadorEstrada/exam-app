import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { CategoriaService } from 'src/app/services/categoria.service';
import { ExamenService } from 'src/app/services/examen.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-examen',
  templateUrl: './add-examen.component.html',
  styleUrls: ['./add-examen.component.css']
})
export class AddExamenComponent implements OnInit { 

  categorias:any=[];  
  //Manejar los campos de acuerdo al modelo en el Backend
  examenData={
    titulo: '', 
    descripcion:'',
    puntosMax:'',
    numeroDePreguntas: '',
    activo:true,
    categoria:{
      categoriaId:''
    }

  }

  constructor(
               private categoriaService:CategoriaService, 
               private snack:MatSnackBar,
               private examenService: ExamenService,
               private router: Router) { }

  ngOnInit(): void {
    this.categoriaService.listarCategorias().subscribe(
      (data:any)=>{
         this.categorias = data; 
         console.log(this.categorias);
      },(error)=>{
        console.log(error); 
        Swal.fire('Error!', 'Error al cargasr los datos','error')
      }
    )
  } 

  public guardarCuestionario(){
    console.log(this.examenData);

    if(this.examenData.titulo.trim()=='' || this.examenData.titulo.trim()==null){
      this.snack.open('El título es requerido','',{
        duration:3000
      }); 
      return;
    }



    this.examenService.agregarExamen(this.examenData).subscribe(
      (data)=>{
       console.log(data);
       Swal.fire('Examen guardado','El examen ha sido guardado exitosamente!','success');
       this.examenData ={
           titulo:'',
           descripcion:'', 
           puntosMax:'',
           numeroDePreguntas: '',
           activo: true,
           categoria:{
            categoriaId:''
           }
       } 
       this.router.navigate(['/admin/examenes'])
      },
      (error)=>{
        Swal.fire('Error','Error al guardar el examen','error')
      }
    )
  }

}
