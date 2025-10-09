package gm.zona_fit.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

// Entity para marcarlo como entidad de nuesta DB usando JPA y JAKARTA
@Entity
@Data // Genera automaticamente los metodos GET Y SET mediante lombok
@NoArgsConstructor // Genera un constructor vacio
@AllArgsConstructor // Genera un constructor con todos los argumentos
@ToString // Genera el metodo ToString
@EqualsAndHashCode // Genera los metodos equals y hash code
public class Cliente {
    // Para marcar que es un ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Marcar que se genera automaticamente
    private Integer id; // Integer en vez de int ya que debemos trabajar con null en la BD, si fuera un int su default fuera 0, como es un objeto, su default es null
    private String nombre;
    private String apellido;
    private Integer membresia;

}
