package ApiVeiculo.APS.core.security;

import ApiVeiculo.APS.domain.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

public class UserDetailsAdapter implements UserDetails {

    private final Usuario usuario;

    public UserDetailsAdapter(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Se você tiver roles, pode mapeá-las aqui. Exemplo:
        // return usuario.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        return null; // Sem roles no exemplo atual
    }

    @Override
    public String getPassword() {
        return usuario.getSenha();
    }

    @Override
    public String getUsername() {
        return usuario.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Ajuste conforme necessário
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Ajuste conforme necessário
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Ajuste conforme necessário
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
