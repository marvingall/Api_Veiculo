package ApiVeiculo.APS.domain.repository;

import ApiVeiculo.APS.domain.model.Carros;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carros,Long> {
}
