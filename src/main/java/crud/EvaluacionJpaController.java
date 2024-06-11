/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud;

import Clases.Evaluacion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Clases.Usuario;
import crud.exceptions.NonexistentEntityException;
import crud.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author HP
 */
public class EvaluacionJpaController implements Serializable {

    public EvaluacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Evaluacion evaluacion) throws PreexistingEntityException, Exception {
        if (evaluacion.getUsuarioList() == null) {
            evaluacion.setUsuarioList(new ArrayList<Usuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Usuario> attachedUsuarioList = new ArrayList<Usuario>();
            for (Usuario usuarioListUsuarioToAttach : evaluacion.getUsuarioList()) {
                usuarioListUsuarioToAttach = em.getReference(usuarioListUsuarioToAttach.getClass(), usuarioListUsuarioToAttach.getIdUsuario());
                attachedUsuarioList.add(usuarioListUsuarioToAttach);
            }
            evaluacion.setUsuarioList(attachedUsuarioList);
            em.persist(evaluacion);
            for (Usuario usuarioListUsuario : evaluacion.getUsuarioList()) {
                usuarioListUsuario.getEvaluacionList().add(evaluacion);
                usuarioListUsuario = em.merge(usuarioListUsuario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEvaluacion(evaluacion.getIdEvaluacion()) != null) {
                throw new PreexistingEntityException("Evaluacion " + evaluacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Evaluacion evaluacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Evaluacion persistentEvaluacion = em.find(Evaluacion.class, evaluacion.getIdEvaluacion());
            List<Usuario> usuarioListOld = persistentEvaluacion.getUsuarioList();
            List<Usuario> usuarioListNew = evaluacion.getUsuarioList();
            List<Usuario> attachedUsuarioListNew = new ArrayList<Usuario>();
            for (Usuario usuarioListNewUsuarioToAttach : usuarioListNew) {
                usuarioListNewUsuarioToAttach = em.getReference(usuarioListNewUsuarioToAttach.getClass(), usuarioListNewUsuarioToAttach.getIdUsuario());
                attachedUsuarioListNew.add(usuarioListNewUsuarioToAttach);
            }
            usuarioListNew = attachedUsuarioListNew;
            evaluacion.setUsuarioList(usuarioListNew);
            evaluacion = em.merge(evaluacion);
            for (Usuario usuarioListOldUsuario : usuarioListOld) {
                if (!usuarioListNew.contains(usuarioListOldUsuario)) {
                    usuarioListOldUsuario.getEvaluacionList().remove(evaluacion);
                    usuarioListOldUsuario = em.merge(usuarioListOldUsuario);
                }
            }
            for (Usuario usuarioListNewUsuario : usuarioListNew) {
                if (!usuarioListOld.contains(usuarioListNewUsuario)) {
                    usuarioListNewUsuario.getEvaluacionList().add(evaluacion);
                    usuarioListNewUsuario = em.merge(usuarioListNewUsuario);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = evaluacion.getIdEvaluacion();
                if (findEvaluacion(id) == null) {
                    throw new NonexistentEntityException("The evaluacion with id " + id + " no longer exists.");
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
            Evaluacion evaluacion;
            try {
                evaluacion = em.getReference(Evaluacion.class, id);
                evaluacion.getIdEvaluacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The evaluacion with id " + id + " no longer exists.", enfe);
            }
            List<Usuario> usuarioList = evaluacion.getUsuarioList();
            for (Usuario usuarioListUsuario : usuarioList) {
                usuarioListUsuario.getEvaluacionList().remove(evaluacion);
                usuarioListUsuario = em.merge(usuarioListUsuario);
            }
            em.remove(evaluacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Evaluacion> findEvaluacionEntities() {
        return findEvaluacionEntities(true, -1, -1);
    }

    public List<Evaluacion> findEvaluacionEntities(int maxResults, int firstResult) {
        return findEvaluacionEntities(false, maxResults, firstResult);
    }

    private List<Evaluacion> findEvaluacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Evaluacion.class));
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

    public Evaluacion findEvaluacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Evaluacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getEvaluacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Evaluacion> rt = cq.from(Evaluacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
