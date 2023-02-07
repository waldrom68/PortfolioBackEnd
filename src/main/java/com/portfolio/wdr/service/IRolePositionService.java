// Orden de creacion 2.-

package com.portfolio.wdr.service;

import com.portfolio.wdr.model.RolePosition;
import java.util.List;

/**
 *
 * @author waldr
 */
public interface IRolePositionService {
    
    public RolePosition crearRolePosition(RolePosition position);
    public void borrarRolePosition(Long id);
    public RolePosition buscarRolePosition(Long id);
  
    public List<RolePosition> verRolePosition();
    public List<RolePosition> verByPersonId(Long id);
    
}
