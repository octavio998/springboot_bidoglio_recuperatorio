package ar.edu.davinci.Proyecto.model.dto;

import java.time.LocalDateTime;

public class PedidoDTO {

    private Long id;
    private LocalDateTime fecha;
    private String direccionEnvio;
    private Double total;

    public PedidoDTO() {
    }

    public PedidoDTO(Long id, LocalDateTime fecha, String direccionEnvio, Double total) {
        this.id = id;
        this.fecha = fecha;
        this.direccionEnvio = direccionEnvio;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PedidoDTO{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", direccionEnvio='" + direccionEnvio + '\'' +
                ", total=" + total +
                '}';
    }
}
