/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import session.GestionnaireDeCompteBancaire;

/**
 *
 * @author jperk
 */
@Named(value = "compteBancaireMBeans")
@ViewScoped
public class CompteBancaireMBeans {

    @EJB
    private GestionnaireDeCompteBancaire gestionnaireDeCompteBancaire;

    /**
     * Creates a new instance of CompteBancaireMBeans
     */
    
    public CompteBancaireMBeans() {
    }
    
}
