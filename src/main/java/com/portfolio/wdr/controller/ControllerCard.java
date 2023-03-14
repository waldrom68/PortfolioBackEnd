// Orden de creacion 5.-
package com.portfolio.wdr.controller;

import com.portfolio.wdr.Security.Controller.Mensaje;
import com.portfolio.wdr.model.Card;
import com.portfolio.wdr.service.ICardService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Recibe las peticiones y delega el negocio (es el pivot de la aplicacion)
@RequestMapping("/card")
@RestController
public class ControllerCard {

    //    Creamos la dependencia
    @Autowired
    private ICardService cardServ;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list/all")
    public List<Card> verCards() {
        return cardServ.verCards();

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/edit")
    public ResponseEntity<?> crearCard(@RequestBody Card card) {
        // Si se tiene mas de 8 elementos solo se permite la edicion
        // El layout solo permite administrar 8 elementos.
        // Esta restriccion la coloco en el backend.
        if (cardServ.buscarCard(card.getId()) != null || cardServ.verCards().size() < 8) {

            try {
                cardServ.editarCard(card);
                return new ResponseEntity(new Mensaje("Informacion guardada correctamente"), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity(new Mensaje("No pudo guardarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity(new Mensaje(" Se ha llegado al maximo de etiquetas que puede manejar el Portfolio, sólo se permite modificar los nombres y/o status de las existentes"), HttpStatus.CONFLICT);

        }

    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/reorder")
    public ResponseEntity<?> reorderEntity(@RequestBody List<Card> data) {

        try {
            for (Card elemento : data) {
                this.crearCard(elemento);
                System.out.println("Hice el proceso para" + elemento);
            }
            return new ResponseEntity(new Mensaje("Orden de las tarjetas actualizadas"), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("No pudo guardarse la informacion suministrada"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

