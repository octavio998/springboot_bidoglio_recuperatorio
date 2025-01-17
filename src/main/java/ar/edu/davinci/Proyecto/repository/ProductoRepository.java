package ar.edu.davinci.Proyecto.repository;

import ar.edu.davinci.Proyecto.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
