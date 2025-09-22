package eci.escuelaing.edu.co.arep.service;

import java.util.List;
import java.util.Optional;

import eci.escuelaing.edu.co.arep.model.Propiedad;
import eci.escuelaing.edu.co.arep.repository.PropiedadRepository;
import org.springframework.stereotype.Service;  

@Service
public class PropiedadService {
    private final PropiedadRepository propiedadRepository;

    public PropiedadService(PropiedadRepository propiedadRepository) {
        this.propiedadRepository = propiedadRepository;
    }

    public Propiedad crearPropiedad(Propiedad propiedad) {
        return propiedadRepository.save(propiedad);
    }

    public List<Propiedad> obtenerTodasLasPropiedades() {
        return propiedadRepository.findAll();
    }

    public Optional<Propiedad> obtenerPropiedadPorId(Integer id) {
        return Optional.ofNullable(propiedadRepository.findById(id).orElse(null));
    }

    public void eliminarPropiedad(Integer id) {
        propiedadRepository.deleteById(id);
    }

    public Propiedad actualizarPropiedad(Integer id, Propiedad propiedadActualizada) {
        Optional<Propiedad> propiedad = propiedadRepository.findById(id);

        if (propiedad.isPresent()) {
            Propiedad nuevaPropiedad = propiedad.get();
            nuevaPropiedad.setDireccion(propiedadActualizada.getDireccion());
            nuevaPropiedad.setPrecio(propiedadActualizada.getPrecio());
            nuevaPropiedad.setTamaño(propiedadActualizada.getTamaño());
            nuevaPropiedad.setDescripcion(propiedadActualizada.getDescripcion());
            return propiedadRepository.save(nuevaPropiedad);
        } else {
            return null;
        }
    }
}
