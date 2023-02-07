// Orden de creacion 2.-

package com.portfolio.wdr.service;

import com.portfolio.wdr.DTO.DTOSocialNetwork;
import com.portfolio.wdr.model.SocialNetwork;
import java.util.List;

/**
 *
 * @author waldr
 */
public interface ISocialnetworkService {
    
    public SocialNetwork crearSocial(SocialNetwork social);
    public void borrarSocial(Long id);
    public SocialNetwork buscarSocial(Long id);
    
    public List<SocialNetwork> verSocial();
    public List<DTOSocialNetwork> verByPersonId(Long id);
    
}
