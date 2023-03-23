/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.wdr.Security.Controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author waldr
 */
@Getter
@Setter
public class Origins {
    @Value("${cors.local}")
    private String local;
    @Value("${cors.web}")
    private String apiweb;

    public Origins(String local, String apiweb) {
        this.local = local;
        this.apiweb = apiweb;
    }

    public Origins() {
    }
    
    
}




