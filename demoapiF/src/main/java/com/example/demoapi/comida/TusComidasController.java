package com.example.demoapi.comida;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/tuscomidas")
@RequiredArgsConstructor

public class TusComidasController {

    @Autowired
    private TusComidasService comidaService;

    @PostMapping("/addOne")
    public ResponseEntity<TusComidas> addOneAlimento(@RequestBody TusComidas comida) {
        TusComidas savedComida = comidaService.addComida(comida);
        return new ResponseEntity<>(savedComida, HttpStatus.CREATED);
    }
    
    @GetMapping("/getAll/{usuario_id}")
    public ResponseEntity<List<TusComidas>> getAllMisComidas(@PathVariable("usuario_id") Long usuario_id) {
        List<TusComidas> misComidas = comidaService.getAllMisComidas(usuario_id);
        return new ResponseEntity<>(misComidas, HttpStatus.OK);
    }

    
    // @Autowired
    // private final ComidaService ComidaService;
    
    // @PostMapping("/crear")
    
    // public ResponseEntity<String> insertarComida(@RequestBody Comida person){
        
    //     ComidaService.crearComida(person);
    //     return ResponseEntity.ok("Se ha creado la ComcIda con éxito");
    // }
    // @PutMapping("borrarComcIdas/{cId}")
    // // @ApiResponses{
    // //     @ApiResponse(responseCode = "200", description = "Com borrada correctamente");
    // // }
    // public ResponseEntity<String> borrarComi(@PathVariable String cId) {
    //    if(ComidaService.borrarComida(Integer.parseInt(cId)))
    //     return ResponseEntity.ok("Se ha borrado la Comida correctamente");
    //    else
    //     return ResponseEntity.status(201).body("El registro no se ha borrado correctamente");     
    // }
    // @GetMapping("obtenerComidas/{cId}")
    // public ResponseEntity<Optional<Comida>> obtenerComcId(@PathVariable String cId) {
    //     return ResponseEntity.ok(ComidaService.obtenerComida(Integer.parseInt(cId)));
    // }
    //  @GetMapping("/comidas/{id}")
    // public ResponseEntity<List<Comida>> obtenerComcIdasPorTipoComcIda(@PathVariable Integer id) {
    //     try {
    //         List<Comida> comidas = ComidaService.obtenerComidasPorTipoComida(id);
    //         return ResponseEntity.ok(comidas);
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    //     }
    // }
    
}
