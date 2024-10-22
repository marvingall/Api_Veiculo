package ApiVeiculo.APS.api.controller;

import ApiVeiculo.APS.domain.service.CarroService;
import ApiVeiculo.APS.api.model.dto.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    private CarroService carroService;


    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<CarroDTO>> listarTodos() {
        return ResponseEntity.ok(carroService.listarTodos());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<CarroDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(carroService.buscarPorId(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CarroDTO> criar(@RequestBody CarroDTO carroDTO) {
        return ResponseEntity.ok(carroService.criar(carroDTO));
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CarroDTO> atualizar(@PathVariable Long id, @RequestBody CarroDTO carroDTO) {
        return ResponseEntity.ok(carroService.atualizar(id, carroDTO));
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        carroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

