/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.CompteBancaire;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jperk
 */
@Stateless
@LocalBean
public class GestionnaireDeCompteBancaire {

    @PersistenceContext(unitName = "CmptBncrPerkova-ejbPU")
    private EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void persist(Object object) {
        em.persist(object);
    }

    public void creerCompte(CompteBancaire cb) {
        persist(cb);
    }
    public void majCompte(CompteBancaire cb){
        em.merge(cb);
    }
    public void supprimerCompte(CompteBancaire cb){
        if(this.chercherCompteBancaire(cb) == null){
            System.out.println("Compte bancaire "+cb.getId()+" inexistante");
        }
        else{
            em.remove(cb);
        }
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

    /***chercher un compte bancaire
     * @param cb*
     * @return result 
     **/
    public CompteBancaire chercherCompteBancaire(CompteBancaire cb){
        CompteBancaire result = em.find(CompteBancaire.class, cb.getId());
        return result;
    }
    
    /***
     * methode pour déposer de l'argent
     * @param cb 
     * @param montant 
     ***/
    public void deposerArgentBCompte(CompteBancaire cb,int montant){
        cb.deposer(montant);
        this.majCompte(cb);
    }
    
    /***
     * methode pour retirer de l'argent
     * @param cb
     * @param montant
     * @return 
     ***/
    public int retirerArgentBCompte(CompteBancaire cb, int montant){
        int res = cb.retirer(montant);
        if(res>0){
            this.majCompte(cb);
            return montant;
        }
        else{
            System.out.println("Impossible de retirer. La solde est 0.");
            return 0;
        }
    }
    
    /***
     * methode pour transferer de l'argent
     * @param cb1
     * @param cb2
     * @param montant
     ***/
    public void transfererArgent(CompteBancaire cb1, CompteBancaire cb2, int montant){
        int res=this.retirerArgentBCompte(cb1, montant);
        if(res>0){
            this.deposerArgentBCompte(cb2, montant);
        }
        else{
            System.out.println("Impossible de transferer de l'argent....");
        }
        
    }
}
