package com.otdr.otdr.Models.Respuestas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UserActual {

    private Collection<? extends GrantedAuthority> Authorities;
    private String email;
    private boolean maps;
    private boolean caracterizacion;
    private boolean fallo;
    private boolean dashboard;

}
