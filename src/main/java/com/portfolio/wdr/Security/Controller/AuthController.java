package com.portfolio.wdr.Security.Controller;

import com.portfolio.wdr.Security.DTO.JwtDTO;
import com.portfolio.wdr.Security.DTO.LoginUsuario;
import com.portfolio.wdr.Security.DTO.NuevoUsuario;
import com.portfolio.wdr.Security.Entity.Rol;
import com.portfolio.wdr.Security.Entity.Usuario;
import com.portfolio.wdr.Security.Enums.RolNombre;
import com.portfolio.wdr.Security.Service.RolService;
import com.portfolio.wdr.Security.Service.UsuarioService;
import com.portfolio.wdr.Security.jwt.JwtProvider;
import java.util.HashSet;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
//@RequestMapping("/auth")
//@CrossOrigin(origins = { "http://localhost:4200", "https://portfolio-frontend-wdr.web.app"})
// @CrossOrigin(origins = "http://localhost:4200")

public class AuthController {

    @Autowired PasswordEncoder passwordEncoder;
    @Autowired AuthenticationManager authenticationManager;
    @Autowired UsuarioService usuarioService;
    @Autowired RolService rolService;
    @Autowired JwtProvider jwtProvider;

    
    @RequestMapping("/")
    public String index() {
        return "<center><h1>El servicio API está funcionando,<br>pero su acceso tiene restricciones</h1></center>";
    }
    
    @GetMapping("/**")
    public String indexLogin() {
        return "<center><h1>El servicio API está funcionando,<br>pero su acceso tiene restricciones</h1></center>";
    }
    
    @CrossOrigin(origins = {"https://portfolio-frontend-wdr.web.app", "http://localhost:4200"})
    @PostMapping("/auth/new")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> nuevo(@Validated @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("Campos mal puestos o email invalido"), HttpStatus.BAD_REQUEST);
        }

        if (usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario())) {
            return new ResponseEntity(new Mensaje("El nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);
        }

        if (usuarioService.existsByEmail(nuevoUsuario.getEmail())) {
            return new ResponseEntity(new Mensaje("El email del usuario ya existe"), HttpStatus.BAD_REQUEST);
        }

        Usuario usuario = new Usuario(
                nuevoUsuario.getNombre(),
                nuevoUsuario.getNombreUsuario(),
                nuevoUsuario.getEmail(),
                passwordEncoder.encode(nuevoUsuario.getPassword()));
        
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        
        if (nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        
        return new ResponseEntity(new Mensaje("Usuario guardado"), HttpStatus.CREATED);
        
    }
    
    @CrossOrigin(origins = {"https://portfolio-frontend-wdr.web.app", "http://localhost:4200"})
    @PostMapping("/auth/login")
    public ResponseEntity<JwtDTO> login(@Validated @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos mal puestos"), HttpStatus.BAD_REQUEST);
        
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario() , loginUsuario.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        System.out.println("###### AuthController.java class ######");
        System.out.println("USERNAME: " + userDetails.getUsername() + "\nROLES: " + userDetails.getAuthorities().toString());
        
        String jwt = jwtProvider.generateToken(authentication);
        
        JwtDTO jwtDto = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}

