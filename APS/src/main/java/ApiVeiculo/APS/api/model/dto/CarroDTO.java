package ApiVeiculo.APS.api.model.dto;

import lombok.Data;

@Data
public class CarroDTO {

    private Long id;
    private String marca;
    private String placa;
    private Long usuarioId;

}
