package ApiVeiculo.APS.domain.service;

import ApiVeiculo.APS.domain.repository.CarroRepository;
import ApiVeiculo.APS.domain.repository.UsuarioRepository; // Importando o repositório de usuário
import ApiVeiculo.APS.api.model.dto.CarroDTO;
import ApiVeiculo.APS.domain.model.Carros;
import ApiVeiculo.APS.domain.model.Usuario; // Importando a entidade Usuario
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<CarroDTO> listarTodos() {
        return carroRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    public CarroDTO buscarPorId(Long id) {
        Optional<Carros> carro = carroRepository.findById(id);
        return carro.map(this::convertToDTO).orElseThrow(() -> new RuntimeException("Carro não encontrado"));
    }

    @Transactional
    public CarroDTO criar(CarroDTO carroDTO) {
        Carros carro = convertToEntity(carroDTO);
        carro = carroRepository.save(carro);
        return convertToDTO(carro);
    }

    @Transactional
    public CarroDTO atualizar(Long id, CarroDTO carroDTO) {
        Carros carro = carroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado"));


        carro.setMarca(carroDTO.getMarca());
        carro.setPlaca(carroDTO.getPlaca());


        Optional<Usuario> usuario = usuarioRepository.findById(carroDTO.getUsuarioId());
        if (usuario.isPresent()) {
            carro.setUsuario(usuario.get()); // Associa o usuário ao carro
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }

        return convertToDTO(carroRepository.save(carro));
    }

    @Transactional
    public void deletar(Long id) {
        carroRepository.deleteById(id);
    }

    private CarroDTO convertToDTO(Carros carro) {
        CarroDTO dto = new CarroDTO();
        dto.setId(carro.getId());
        dto.setMarca(carro.getMarca());
        dto.setPlaca(carro.getPlaca());
        dto.setUsuarioId(carro.getUsuario().getId());
        return dto;
    }

    private Carros convertToEntity(CarroDTO dto) {
        Carros carro = new Carros();
        carro.setMarca(dto.getMarca());
        carro.setPlaca(dto.getPlaca());


        Optional<Usuario> usuario = usuarioRepository.findById(dto.getUsuarioId());
        if (usuario.isPresent()) {
            carro.setUsuario(usuario.get());
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }

        return carro;
    }
}
