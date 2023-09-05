import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoriaService } from 'src/app/services/categoria.service';
import { ExamenService } from 'src/app/services/examen.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-actualizar-examen',
  templateUrl: './actualizar-examen.component.html',
  styleUrls: ['./actualizar-examen.component.css']
})
export class ActualizarExamenComponent implements OnInit {

  constructor(
    private route:ActivatedRoute,
    private examenService:ExamenService,
    private categoriaService:CategoriaService,
    private router:Router) { }

  examenId = 0;
  examen:any;
  categorias:any;
  

  ngOnInit(): void {
    this.examenId = this.route.snapshot.params['examenId']; 
    this.examenService.obtenerExamen(this.examenId).subscribe(
      (data)=>{
        this.examen=data;
        console.log(this.examen);
      },
      (error)=>{
        console.log(error);
      }
    ) 
     //Muestra las categorias en la lista
     this.categoriaService.listarCategorias().subscribe(
       (data:any)=>{
        this.categorias=data;
      },
      (error)=>{
        console.log(error);
      }
    ) 

  }  
  
  public actualizarDatos(){
    this.examenService.actualizarExamen(this.examen).subscribe(
      (data) => {
        Swal.fire('Examen actualizado','El examen ha sido actualizado con éxito','success').then(
          (e) => {
            this.router.navigate(['/admin/examenes']);
          }
        );
      },
      (error)=>{
        Swal.fire('Error en el sistema','No se ha podido actualizar el examen','error');
        console.log(error);
      }
      
    )
  }

  
}
