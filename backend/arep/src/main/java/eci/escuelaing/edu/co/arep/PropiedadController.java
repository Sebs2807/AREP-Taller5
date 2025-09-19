package eci.escuelaing.edu.co.arep;

import eci.escuelaing.edu.co.arep.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/propiedades")
@CrossOrigin(origins = "*")
public class PropiedadController {
    private final PropiedadService service;

    public PropiedadController(PropiedadService service){ 
        this.service = service; 
    }

    @PostMapping
    public ResponseEntity<Propiedad> crearPropiedad(@RequestBody Propiedad p) {
        if (p.getDireccion() == null || p.getPrecio() == null) {
            return ResponseEntity.badRequest().build();
        }
        Propiedad saved = service.crearPropiedad(p);
        return ResponseEntity.created(URI.create("/api/properties/" + saved.getId())).body(saved);
    }

    @GetMapping
    public List<Propiedad> obtenerTodasLasPropiedades(){ 
        return service.obtenerTodasLasPropiedades(); 
    }

    //GET propiedades/id
    @GetMapping("/{id}")
    public ResponseEntity<Propiedad> obtenerPropiedadPorId(@PathVariable Integer id) {
        Optional<Propiedad> opcional = service.obtenerPropiedadPorId(id);
        if (opcional.isPresent()) {
            return ResponseEntity.ok(opcional.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    //PUT propiedades/id
    @PutMapping("/{id}")
    public ResponseEntity<Propiedad> actualizar(@PathVariable Integer id, @RequestBody Propiedad p) {
        try {
            Propiedad actualizada = service.actualizarPropiedad(id, p);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //DELETE propiedades/id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.eliminarPropiedad(id);
        return ResponseEntity.noContent().build();
    }
}
