package ApiVeiculo.APS.domain.service;

import ApiVeiculo.APS.domain.repository.UsuarioRepository;
import ApiVeiculo.APS.api.model.dto.UsuarioDTO;
import ApiVeiculo.APS.domain.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }


    public UsuarioDTO buscarPorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(this::convertToDTO).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }


    @Transactional
    public UsuarioDTO criar(UsuarioDTO usuarioDTO) {
        Usuario usuario = convertToEntity(usuarioDTO);
        String encryptedPassword = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(encryptedPassword);
        usuario = usuarioRepository.save(usuario);
        String emailSubject = "Cadastro Realizado";
        String emailBody = "Seu cadastro foi realizado com sucesso!";
        emailService.sendSimpleMessage(usuario.getEmail(), emailSubject, emailBody);

        return convertToDTO(usuario);
    }


    @Transactional
    public UsuarioDTO atualizar(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha()); // A senha deve ser criptografada antes de ser salva
        usuario.setFuncao(usuarioDTO.getFuncao());
        return convertToDTO(usuarioRepository.save(usuario));
    }

    @Transactional
    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }


    private UsuarioDTO convertToDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setFuncao(usuario.getFuncao());
        return dto;
    }

    private Usuario convertToEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setFuncao(dto.getFuncao());
        return usuario;
    }
}
