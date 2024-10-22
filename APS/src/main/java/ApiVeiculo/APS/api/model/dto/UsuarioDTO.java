package ApiVeiculo.APS.api.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String funcao;
    private LocalDateTime dataCriacao;
    private List<CarroDTO> carros;


}

