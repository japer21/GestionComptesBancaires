/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import tp3.CompteBancaire;

/**
 *
 * @author jperk
 */
@Stateless
@LocalBean
public class GestionnaireDeCompteBancaire {

    @PersistenceContext(unitName = "GCBPerkova-ejbPU")
    private EntityManager em;

  public void persist(Object object) {
        em.persist(object);
    }

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void creerCompte(CompteBancaire compte) {
        persist(compte);
    }

    public List<CompteBancaire> getAllComptes() {
        Query query = em.createNamedQuery("CompteBancaire.findAll");  
        return query.getResultList();
    }
    public void creerComptesTest() {  
   creerCompte(new CompteBancaire("John Lennon", 150000));  
   creerCompte(new CompteBancaire("Paul McCartney", 950000));  
   creerCompte(new CompteBancaire("Ringo Starr", 20000));  
   creerCompte(new CompteBancaire("Georges Harrisson", 100000));  
}
    
}
