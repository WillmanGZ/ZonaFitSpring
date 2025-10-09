package gm.zona_fit.repositorio;

import gm.zona_fit.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

// Debe extender de JPA Repository para poder usarlo con JPA, se especifica como parámetros la clase modelo y luego el tipo de dato que es su llave primaria
// Spring Data JPA usa interfaces porque Spring genera automáticamente la implementación por ti en tiempo de ejecución
// Spring crea internamente una clase que implementa esa interfaz y le pone toda la lógica CRUD (guardar, actualizar, borrar, buscar, etc.).
// En Java moderno (y especialmente en Spring), ya no se acostumbra usar el prefijo “I” para interfaces.
public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {
}
