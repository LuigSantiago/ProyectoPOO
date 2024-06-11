/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud;

import Clases.Problematecnico;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
public class ProblematecnicoJpaController implements Serializable {

    public ProblematecnicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Problematecnico problematecnico) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuarioidUsuario = problematecnico.getUsuarioidUsuario();
            if (usuarioidUsuario != null) {
                usuarioidUsuario = em.getReference(usuarioidUsuario.getClass(), usuarioidUsuario.getIdUsuario());
                problematecnico.setUsuarioidUsuario(usuarioidUsuario);
            }
            em.persist(problematecnico);
            if (usuarioidUsuario != null) {
                usuarioidUsuario.getProblematecnicoList().add(problematecnico);
                usuarioidUsuario = em.merge(usuarioidUsuario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProblematecnico(problematecnico.getIdProblemaTecnico()) != null) {
                throw new PreexistingEntityException("Problematecnico " + problematecnico + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Problematecnico problematecnico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Problematecnico persistentProblematecnico = em.find(Problematecnico.class, problematecnico.getIdProblemaTecnico());
            Usuario usuarioidUsuarioOld = persistentProblematecnico.getUsuarioidUsuario();
            Usuario usuarioidUsuarioNew = problematecnico.getUsuarioidUsuario();
            if (usuarioidUsuarioNew != null) {
                usuarioidUsuarioNew = em.getReference(usuarioidUsuarioNew.getClass(), usuarioidUsuarioNew.getIdUsuario());
                problematecnico.setUsuarioidUsuario(usuarioidUsuarioNew);
            }
            problematecnico = em.merge(problematecnico);
            if (usuarioidUsuarioOld != null && !usuarioidUsuarioOld.equals(usuarioidUsuarioNew)) {
                usuarioidUsuarioOld.getProblematecnicoList().remove(problematecnico);
                usuarioidUsuarioOld = em.merge(usuarioidUsuarioOld);
            }
            if (usuarioidUsuarioNew != null && !usuarioidUsuarioNew.equals(usuarioidUsuarioOld)) {
                usuarioidUsuarioNew.getProblematecnicoList().add(problematecnico);
                usuarioidUsuarioNew = em.merge(usuarioidUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = problematecnico.getIdProblemaTecnico();
                if (findProblematecnico(id) == null) {
                    throw new NonexistentEntityException("The problematecnico with id " + id + " no longer exists.");
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
            Problematecnico problematecnico;
            try {
                problematecnico = em.getReference(Problematecnico.class, id);
                problematecnico.getIdProblemaTecnico();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The problematecnico with id " + id + " no longer exists.", enfe);
            }
            Usuario usuarioidUsuario = problematecnico.getUsuarioidUsuario();
            if (usuarioidUsuario != null) {
                usuarioidUsuario.getProblematecnicoList().remove(problematecnico);
                usuarioidUsuario = em.merge(usuarioidUsuario);
            }
            em.remove(problematecnico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Problematecnico> findProblematecnicoEntities() {
        return findProblematecnicoEntities(true, -1, -1);
    }

    public List<Problematecnico> findProblematecnicoEntities(int maxResults, int firstResult) {
        return findProblematecnicoEntities(false, maxResults, firstResult);
    }

    private List<Problematecnico> findProblematecnicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Problematecnico.class));
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

    public Problematecnico findProblematecnico(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Problematecnico.class, id);
        } finally {
            em.close();
        }
    }

    public int getProblematecnicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Problematecnico> rt = cq.from(Problematecnico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
