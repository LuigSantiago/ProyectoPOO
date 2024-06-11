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
import Clases.Evaluacion;
import java.util.ArrayList;
import java.util.List;
import Clases.Oferta;
import Clases.Problematecnico;
import Clases.Articulo;
import Clases.Notificacion;
import Clases.Usuario;
import crud.exceptions.IllegalOrphanException;
import crud.exceptions.NonexistentEntityException;
import crud.exceptions.PreexistingEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author HP
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, Exception {
        if (usuario.getEvaluacionList() == null) {
            usuario.setEvaluacionList(new ArrayList<Evaluacion>());
        }
        if (usuario.getOfertaList() == null) {
            usuario.setOfertaList(new ArrayList<Oferta>());
        }
        if (usuario.getProblematecnicoList() == null) {
            usuario.setProblematecnicoList(new ArrayList<Problematecnico>());
        }
        if (usuario.getArticuloList() == null) {
            usuario.setArticuloList(new ArrayList<Articulo>());
        }
        if (usuario.getNotificacionList() == null) {
            usuario.setNotificacionList(new ArrayList<Notificacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Evaluacion> attachedEvaluacionList = new ArrayList<Evaluacion>();
            for (Evaluacion evaluacionListEvaluacionToAttach : usuario.getEvaluacionList()) {
                evaluacionListEvaluacionToAttach = em.getReference(evaluacionListEvaluacionToAttach.getClass(), evaluacionListEvaluacionToAttach.getIdEvaluacion());
                attachedEvaluacionList.add(evaluacionListEvaluacionToAttach);
            }
            usuario.setEvaluacionList(attachedEvaluacionList);
            List<Oferta> attachedOfertaList = new ArrayList<Oferta>();
            for (Oferta ofertaListOfertaToAttach : usuario.getOfertaList()) {
                ofertaListOfertaToAttach = em.getReference(ofertaListOfertaToAttach.getClass(), ofertaListOfertaToAttach.getIdOferta());
                attachedOfertaList.add(ofertaListOfertaToAttach);
            }
            usuario.setOfertaList(attachedOfertaList);
            List<Problematecnico> attachedProblematecnicoList = new ArrayList<Problematecnico>();
            for (Problematecnico problematecnicoListProblematecnicoToAttach : usuario.getProblematecnicoList()) {
                problematecnicoListProblematecnicoToAttach = em.getReference(problematecnicoListProblematecnicoToAttach.getClass(), problematecnicoListProblematecnicoToAttach.getIdProblemaTecnico());
                attachedProblematecnicoList.add(problematecnicoListProblematecnicoToAttach);
            }
            usuario.setProblematecnicoList(attachedProblematecnicoList);
            List<Articulo> attachedArticuloList = new ArrayList<Articulo>();
            for (Articulo articuloListArticuloToAttach : usuario.getArticuloList()) {
                articuloListArticuloToAttach = em.getReference(articuloListArticuloToAttach.getClass(), articuloListArticuloToAttach.getIdArticulo());
                attachedArticuloList.add(articuloListArticuloToAttach);
            }
            usuario.setArticuloList(attachedArticuloList);
            List<Notificacion> attachedNotificacionList = new ArrayList<Notificacion>();
            for (Notificacion notificacionListNotificacionToAttach : usuario.getNotificacionList()) {
                notificacionListNotificacionToAttach = em.getReference(notificacionListNotificacionToAttach.getClass(), notificacionListNotificacionToAttach.getIdNotificacion());
                attachedNotificacionList.add(notificacionListNotificacionToAttach);
            }
            usuario.setNotificacionList(attachedNotificacionList);
            em.persist(usuario);
            for (Evaluacion evaluacionListEvaluacion : usuario.getEvaluacionList()) {
                evaluacionListEvaluacion.getUsuarioList().add(usuario);
                evaluacionListEvaluacion = em.merge(evaluacionListEvaluacion);
            }
            for (Oferta ofertaListOferta : usuario.getOfertaList()) {
                Usuario oldUsuarioidUsuarioOfOfertaListOferta = ofertaListOferta.getUsuarioidUsuario();
                ofertaListOferta.setUsuarioidUsuario(usuario);
                ofertaListOferta = em.merge(ofertaListOferta);
                if (oldUsuarioidUsuarioOfOfertaListOferta != null) {
                    oldUsuarioidUsuarioOfOfertaListOferta.getOfertaList().remove(ofertaListOferta);
                    oldUsuarioidUsuarioOfOfertaListOferta = em.merge(oldUsuarioidUsuarioOfOfertaListOferta);
                }
            }
            for (Problematecnico problematecnicoListProblematecnico : usuario.getProblematecnicoList()) {
                Usuario oldUsuarioidUsuarioOfProblematecnicoListProblematecnico = problematecnicoListProblematecnico.getUsuarioidUsuario();
                problematecnicoListProblematecnico.setUsuarioidUsuario(usuario);
                problematecnicoListProblematecnico = em.merge(problematecnicoListProblematecnico);
                if (oldUsuarioidUsuarioOfProblematecnicoListProblematecnico != null) {
                    oldUsuarioidUsuarioOfProblematecnicoListProblematecnico.getProblematecnicoList().remove(problematecnicoListProblematecnico);
                    oldUsuarioidUsuarioOfProblematecnicoListProblematecnico = em.merge(oldUsuarioidUsuarioOfProblematecnicoListProblematecnico);
                }
            }
            for (Articulo articuloListArticulo : usuario.getArticuloList()) {
                Usuario oldUsuarioidUsuarioOfArticuloListArticulo = articuloListArticulo.getUsuarioidUsuario();
                articuloListArticulo.setUsuarioidUsuario(usuario);
                articuloListArticulo = em.merge(articuloListArticulo);
                if (oldUsuarioidUsuarioOfArticuloListArticulo != null) {
                    oldUsuarioidUsuarioOfArticuloListArticulo.getArticuloList().remove(articuloListArticulo);
                    oldUsuarioidUsuarioOfArticuloListArticulo = em.merge(oldUsuarioidUsuarioOfArticuloListArticulo);
                }
            }
            for (Notificacion notificacionListNotificacion : usuario.getNotificacionList()) {
                Usuario oldUsuarioidUsuarioOfNotificacionListNotificacion = notificacionListNotificacion.getUsuarioidUsuario();
                notificacionListNotificacion.setUsuarioidUsuario(usuario);
                notificacionListNotificacion = em.merge(notificacionListNotificacion);
                if (oldUsuarioidUsuarioOfNotificacionListNotificacion != null) {
                    oldUsuarioidUsuarioOfNotificacionListNotificacion.getNotificacionList().remove(notificacionListNotificacion);
                    oldUsuarioidUsuarioOfNotificacionListNotificacion = em.merge(oldUsuarioidUsuarioOfNotificacionListNotificacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuario(usuario.getIdUsuario()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdUsuario());
            List<Evaluacion> evaluacionListOld = persistentUsuario.getEvaluacionList();
            List<Evaluacion> evaluacionListNew = usuario.getEvaluacionList();
            List<Oferta> ofertaListOld = persistentUsuario.getOfertaList();
            List<Oferta> ofertaListNew = usuario.getOfertaList();
            List<Problematecnico> problematecnicoListOld = persistentUsuario.getProblematecnicoList();
            List<Problematecnico> problematecnicoListNew = usuario.getProblematecnicoList();
            List<Articulo> articuloListOld = persistentUsuario.getArticuloList();
            List<Articulo> articuloListNew = usuario.getArticuloList();
            List<Notificacion> notificacionListOld = persistentUsuario.getNotificacionList();
            List<Notificacion> notificacionListNew = usuario.getNotificacionList();
            List<String> illegalOrphanMessages = null;
            for (Oferta ofertaListOldOferta : ofertaListOld) {
                if (!ofertaListNew.contains(ofertaListOldOferta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Oferta " + ofertaListOldOferta + " since its usuarioidUsuario field is not nullable.");
                }
            }
            for (Problematecnico problematecnicoListOldProblematecnico : problematecnicoListOld) {
                if (!problematecnicoListNew.contains(problematecnicoListOldProblematecnico)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Problematecnico " + problematecnicoListOldProblematecnico + " since its usuarioidUsuario field is not nullable.");
                }
            }
            for (Articulo articuloListOldArticulo : articuloListOld) {
                if (!articuloListNew.contains(articuloListOldArticulo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Articulo " + articuloListOldArticulo + " since its usuarioidUsuario field is not nullable.");
                }
            }
            for (Notificacion notificacionListOldNotificacion : notificacionListOld) {
                if (!notificacionListNew.contains(notificacionListOldNotificacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Notificacion " + notificacionListOldNotificacion + " since its usuarioidUsuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Evaluacion> attachedEvaluacionListNew = new ArrayList<Evaluacion>();
            for (Evaluacion evaluacionListNewEvaluacionToAttach : evaluacionListNew) {
                evaluacionListNewEvaluacionToAttach = em.getReference(evaluacionListNewEvaluacionToAttach.getClass(), evaluacionListNewEvaluacionToAttach.getIdEvaluacion());
                attachedEvaluacionListNew.add(evaluacionListNewEvaluacionToAttach);
            }
            evaluacionListNew = attachedEvaluacionListNew;
            usuario.setEvaluacionList(evaluacionListNew);
            List<Oferta> attachedOfertaListNew = new ArrayList<Oferta>();
            for (Oferta ofertaListNewOfertaToAttach : ofertaListNew) {
                ofertaListNewOfertaToAttach = em.getReference(ofertaListNewOfertaToAttach.getClass(), ofertaListNewOfertaToAttach.getIdOferta());
                attachedOfertaListNew.add(ofertaListNewOfertaToAttach);
            }
            ofertaListNew = attachedOfertaListNew;
            usuario.setOfertaList(ofertaListNew);
            List<Problematecnico> attachedProblematecnicoListNew = new ArrayList<Problematecnico>();
            for (Problematecnico problematecnicoListNewProblematecnicoToAttach : problematecnicoListNew) {
                problematecnicoListNewProblematecnicoToAttach = em.getReference(problematecnicoListNewProblematecnicoToAttach.getClass(), problematecnicoListNewProblematecnicoToAttach.getIdProblemaTecnico());
                attachedProblematecnicoListNew.add(problematecnicoListNewProblematecnicoToAttach);
            }
            problematecnicoListNew = attachedProblematecnicoListNew;
            usuario.setProblematecnicoList(problematecnicoListNew);
            List<Articulo> attachedArticuloListNew = new ArrayList<Articulo>();
            for (Articulo articuloListNewArticuloToAttach : articuloListNew) {
                articuloListNewArticuloToAttach = em.getReference(articuloListNewArticuloToAttach.getClass(), articuloListNewArticuloToAttach.getIdArticulo());
                attachedArticuloListNew.add(articuloListNewArticuloToAttach);
            }
            articuloListNew = attachedArticuloListNew;
            usuario.setArticuloList(articuloListNew);
            List<Notificacion> attachedNotificacionListNew = new ArrayList<Notificacion>();
            for (Notificacion notificacionListNewNotificacionToAttach : notificacionListNew) {
                notificacionListNewNotificacionToAttach = em.getReference(notificacionListNewNotificacionToAttach.getClass(), notificacionListNewNotificacionToAttach.getIdNotificacion());
                attachedNotificacionListNew.add(notificacionListNewNotificacionToAttach);
            }
            notificacionListNew = attachedNotificacionListNew;
            usuario.setNotificacionList(notificacionListNew);
            usuario = em.merge(usuario);
            for (Evaluacion evaluacionListOldEvaluacion : evaluacionListOld) {
                if (!evaluacionListNew.contains(evaluacionListOldEvaluacion)) {
                    evaluacionListOldEvaluacion.getUsuarioList().remove(usuario);
                    evaluacionListOldEvaluacion = em.merge(evaluacionListOldEvaluacion);
                }
            }
            for (Evaluacion evaluacionListNewEvaluacion : evaluacionListNew) {
                if (!evaluacionListOld.contains(evaluacionListNewEvaluacion)) {
                    evaluacionListNewEvaluacion.getUsuarioList().add(usuario);
                    evaluacionListNewEvaluacion = em.merge(evaluacionListNewEvaluacion);
                }
            }
            for (Oferta ofertaListNewOferta : ofertaListNew) {
                if (!ofertaListOld.contains(ofertaListNewOferta)) {
                    Usuario oldUsuarioidUsuarioOfOfertaListNewOferta = ofertaListNewOferta.getUsuarioidUsuario();
                    ofertaListNewOferta.setUsuarioidUsuario(usuario);
                    ofertaListNewOferta = em.merge(ofertaListNewOferta);
                    if (oldUsuarioidUsuarioOfOfertaListNewOferta != null && !oldUsuarioidUsuarioOfOfertaListNewOferta.equals(usuario)) {
                        oldUsuarioidUsuarioOfOfertaListNewOferta.getOfertaList().remove(ofertaListNewOferta);
                        oldUsuarioidUsuarioOfOfertaListNewOferta = em.merge(oldUsuarioidUsuarioOfOfertaListNewOferta);
                    }
                }
            }
            for (Problematecnico problematecnicoListNewProblematecnico : problematecnicoListNew) {
                if (!problematecnicoListOld.contains(problematecnicoListNewProblematecnico)) {
                    Usuario oldUsuarioidUsuarioOfProblematecnicoListNewProblematecnico = problematecnicoListNewProblematecnico.getUsuarioidUsuario();
                    problematecnicoListNewProblematecnico.setUsuarioidUsuario(usuario);
                    problematecnicoListNewProblematecnico = em.merge(problematecnicoListNewProblematecnico);
                    if (oldUsuarioidUsuarioOfProblematecnicoListNewProblematecnico != null && !oldUsuarioidUsuarioOfProblematecnicoListNewProblematecnico.equals(usuario)) {
                        oldUsuarioidUsuarioOfProblematecnicoListNewProblematecnico.getProblematecnicoList().remove(problematecnicoListNewProblematecnico);
                        oldUsuarioidUsuarioOfProblematecnicoListNewProblematecnico = em.merge(oldUsuarioidUsuarioOfProblematecnicoListNewProblematecnico);
                    }
                }
            }
            for (Articulo articuloListNewArticulo : articuloListNew) {
                if (!articuloListOld.contains(articuloListNewArticulo)) {
                    Usuario oldUsuarioidUsuarioOfArticuloListNewArticulo = articuloListNewArticulo.getUsuarioidUsuario();
                    articuloListNewArticulo.setUsuarioidUsuario(usuario);
                    articuloListNewArticulo = em.merge(articuloListNewArticulo);
                    if (oldUsuarioidUsuarioOfArticuloListNewArticulo != null && !oldUsuarioidUsuarioOfArticuloListNewArticulo.equals(usuario)) {
                        oldUsuarioidUsuarioOfArticuloListNewArticulo.getArticuloList().remove(articuloListNewArticulo);
                        oldUsuarioidUsuarioOfArticuloListNewArticulo = em.merge(oldUsuarioidUsuarioOfArticuloListNewArticulo);
                    }
                }
            }
            for (Notificacion notificacionListNewNotificacion : notificacionListNew) {
                if (!notificacionListOld.contains(notificacionListNewNotificacion)) {
                    Usuario oldUsuarioidUsuarioOfNotificacionListNewNotificacion = notificacionListNewNotificacion.getUsuarioidUsuario();
                    notificacionListNewNotificacion.setUsuarioidUsuario(usuario);
                    notificacionListNewNotificacion = em.merge(notificacionListNewNotificacion);
                    if (oldUsuarioidUsuarioOfNotificacionListNewNotificacion != null && !oldUsuarioidUsuarioOfNotificacionListNewNotificacion.equals(usuario)) {
                        oldUsuarioidUsuarioOfNotificacionListNewNotificacion.getNotificacionList().remove(notificacionListNewNotificacion);
                        oldUsuarioidUsuarioOfNotificacionListNewNotificacion = em.merge(oldUsuarioidUsuarioOfNotificacionListNewNotificacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getIdUsuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Oferta> ofertaListOrphanCheck = usuario.getOfertaList();
            for (Oferta ofertaListOrphanCheckOferta : ofertaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Oferta " + ofertaListOrphanCheckOferta + " in its ofertaList field has a non-nullable usuarioidUsuario field.");
            }
            List<Problematecnico> problematecnicoListOrphanCheck = usuario.getProblematecnicoList();
            for (Problematecnico problematecnicoListOrphanCheckProblematecnico : problematecnicoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Problematecnico " + problematecnicoListOrphanCheckProblematecnico + " in its problematecnicoList field has a non-nullable usuarioidUsuario field.");
            }
            List<Articulo> articuloListOrphanCheck = usuario.getArticuloList();
            for (Articulo articuloListOrphanCheckArticulo : articuloListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Articulo " + articuloListOrphanCheckArticulo + " in its articuloList field has a non-nullable usuarioidUsuario field.");
            }
            List<Notificacion> notificacionListOrphanCheck = usuario.getNotificacionList();
            for (Notificacion notificacionListOrphanCheckNotificacion : notificacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Notificacion " + notificacionListOrphanCheckNotificacion + " in its notificacionList field has a non-nullable usuarioidUsuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Evaluacion> evaluacionList = usuario.getEvaluacionList();
            for (Evaluacion evaluacionListEvaluacion : evaluacionList) {
                evaluacionListEvaluacion.getUsuarioList().remove(usuario);
                evaluacionListEvaluacion = em.merge(evaluacionListEvaluacion);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
