// Orden de creacion 2.-

package com.portfolio.wdr.service;

import com.portfolio.wdr.model.DisplayData;
import java.util.List;

/**
 *
 * @author waldr
 */
public interface IDisplayDataService {
        
    public List<DisplayData> verDisplay();
    public void crearDisplay(DisplayData display);
    public void borrarDisplay(Long id);
    public DisplayData buscarDisplay(Long id);
    public DisplayData crearForceDisplay(DisplayData display);  // Necesito el ID del elemento creado
    
    
}
