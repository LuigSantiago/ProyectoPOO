/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Clases.Articulo;
import Clases.Oferta;
import Clases.Usuario;
import crud.exceptions.NonexistentEntityException;
import crud.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author HP
 */
public class OfertaJpaController implements Serializable {

    public OfertaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Oferta oferta) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Articulo articuloidArticulo = oferta.getArticuloidArticulo();
            if (articuloidArticulo != null) {
                articuloidArticulo = em.getReference(articuloidArticulo.getClass(), articuloidArticulo.getIdArticulo());
                oferta.setArticuloidArticulo(articuloidArticulo);
            }
            Usuario usuarioidUsuario = oferta.getUsuarioidUsuario();
            if (usuarioidUsuario != null) {
                usuarioidUsuario = em.getReference(usuarioidUsuario.getClass(), usuarioidUsuario.getIdUsuario());
                oferta.setUsuarioidUsuario(usuarioidUsuario);
            }
            em.persist(oferta);
            if (articuloidArticulo != null) {
                articuloidArticulo.getOfertaList().add(oferta);
                articuloidArticulo = em.merge(articuloidArticulo);
            }
            if (usuarioidUsuario != null) {
                usuarioidUsuario.getOfertaList().add(oferta);
                usuarioidUsuario = em.merge(usuarioidUsuario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOferta(oferta.getIdOferta()) != null) {
                throw new PreexistingEntityException("Oferta " + oferta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Oferta oferta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Oferta persistentOferta = em.find(Oferta.class, oferta.getIdOferta());
            Articulo articuloidArticuloOld = persistentOferta.getArticuloidArticulo();
            Articulo articuloidArticuloNew = oferta.getArticuloidArticulo();
            Usuario usuarioidUsuarioOld = persistentOferta.getUsuarioidUsuario();
            Usuario usuarioidUsuarioNew = oferta.getUsuarioidUsuario();
            if (articuloidArticuloNew != null) {
                articuloidArticuloNew = em.getReference(articuloidArticuloNew.getClass(), articuloidArticuloNew.getIdArticulo());
                oferta.setArticuloidArticulo(articuloidArticuloNew);
            }
            if (usuarioidUsuarioNew != null) {
                usuarioidUsuarioNew = em.getReference(usuarioidUsuarioNew.getClass(), usuarioidUsuarioNew.getIdUsuario());
                oferta.setUsuarioidUsuario(usuarioidUsuarioNew);
            }
            oferta = em.merge(oferta);
            if (articuloidArticuloOld != null && !articuloidArticuloOld.equals(articuloidArticuloNew)) {
                articuloidArticuloOld.getOfertaList().remove(oferta);
                articuloidArticuloOld = em.merge(articuloidArticuloOld);
            }
            if (articuloidArticuloNew != null && !articuloidArticuloNew.equals(articuloidArticuloOld)) {
                articuloidArticuloNew.getOfertaList().add(oferta);
                articuloidArticuloNew = em.merge(articuloidArticuloNew);
            }
            if (usuarioidUsuarioOld != null && !usuarioidUsuarioOld.equals(usuarioidUsuarioNew)) {
                usuarioidUsuarioOld.getOfertaList().remove(oferta);
                usuarioidUsuarioOld = em.merge(usuarioidUsuarioOld);
            }
            if (usuarioidUsuarioNew != null && !usuarioidUsuarioNew.equals(usuarioidUsuarioOld)) {
                usuarioidUsuarioNew.getOfertaList().add(oferta);
                usuarioidUsuarioNew = em.merge(usuarioidUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = oferta.getIdOferta();
                if (findOferta(id) == null) {
                    throw new NonexistentEntityException("The oferta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Oferta oferta;
            try {
                oferta = em.getReference(Oferta.class, id);
                oferta.getIdOferta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The oferta with id " + id + " no longer exists.", enfe);
            }
            Articulo articuloidArticulo = oferta.getArticuloidArticulo();
            if (articuloidArticulo != null) {
                articuloidArticulo.getOfertaList().remove(oferta);
                articuloidArticulo = em.merge(articuloidArticulo);
            }
            Usuario usuarioidUsuario = oferta.getUsuarioidUsuario();
            if (usuarioidUsuario != null) {
                usuarioidUsuario.getOfertaList().remove(oferta);
                usuarioidUsuario = em.merge(usuarioidUsuario);
            }
            em.remove(oferta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Oferta> findOfertaEntities() {
        return findOfertaEntities(true, -1, -1);
    }

    public List<Oferta> findOfertaEntities(int maxResults, int firstResult) {
        return findOfertaEntities(false, maxResults, firstResult);
    }

    private List<Oferta> findOfertaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Oferta.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Oferta findOferta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Oferta.class, id);
        } finally {
            em.close();
        }
    }

    public int getOfertaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Oferta> rt = cq.from(Oferta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
