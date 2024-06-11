/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud;

import Clases.Articulo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Clases.Usuario;
import Clases.Categoria;
import java.util.ArrayList;
import java.util.List;
import Clases.Oferta;
import crud.exceptions.IllegalOrphanException;
import crud.exceptions.NonexistentEntityException;
import crud.exceptions.PreexistingEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author HP
 */
public class ArticuloJpaController implements Serializable {

    public ArticuloJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Articulo articulo) throws PreexistingEntityException, Exception {
        if (articulo.getCategoriaList() == null) {
            articulo.setCategoriaList(new ArrayList<Categoria>());
        }
        if (articulo.getOfertaList() == null) {
            articulo.setOfertaList(new ArrayList<Oferta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuarioidUsuario = articulo.getUsuarioidUsuario();
            if (usuarioidUsuario != null) {
                usuarioidUsuario = em.getReference(usuarioidUsuario.getClass(), usuarioidUsuario.getIdUsuario());
                articulo.setUsuarioidUsuario(usuarioidUsuario);
            }
            List<Categoria> attachedCategoriaList = new ArrayList<Categoria>();
            for (Categoria categoriaListCategoriaToAttach : articulo.getCategoriaList()) {
                categoriaListCategoriaToAttach = em.getReference(categoriaListCategoriaToAttach.getClass(), categoriaListCategoriaToAttach.getIdCategoria());
                attachedCategoriaList.add(categoriaListCategoriaToAttach);
            }
            articulo.setCategoriaList(attachedCategoriaList);
            List<Oferta> attachedOfertaList = new ArrayList<Oferta>();
            for (Oferta ofertaListOfertaToAttach : articulo.getOfertaList()) {
                ofertaListOfertaToAttach = em.getReference(ofertaListOfertaToAttach.getClass(), ofertaListOfertaToAttach.getIdOferta());
                attachedOfertaList.add(ofertaListOfertaToAttach);
            }
            articulo.setOfertaList(attachedOfertaList);
            em.persist(articulo);
            if (usuarioidUsuario != null) {
                usuarioidUsuario.getArticuloList().add(articulo);
                usuarioidUsuario = em.merge(usuarioidUsuario);
            }
            for (Categoria categoriaListCategoria : articulo.getCategoriaList()) {
                categoriaListCategoria.getArticuloList().add(articulo);
                categoriaListCategoria = em.merge(categoriaListCategoria);
            }
            for (Oferta ofertaListOferta : articulo.getOfertaList()) {
                Articulo oldArticuloidArticuloOfOfertaListOferta = ofertaListOferta.getArticuloidArticulo();
                ofertaListOferta.setArticuloidArticulo(articulo);
                ofertaListOferta = em.merge(ofertaListOferta);
                if (oldArticuloidArticuloOfOfertaListOferta != null) {
                    oldArticuloidArticuloOfOfertaListOferta.getOfertaList().remove(ofertaListOferta);
                    oldArticuloidArticuloOfOfertaListOferta = em.merge(oldArticuloidArticuloOfOfertaListOferta);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findArticulo(articulo.getIdArticulo()) != null) {
                throw new PreexistingEntityException("Articulo " + articulo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Articulo articulo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Articulo persistentArticulo = em.find(Articulo.class, articulo.getIdArticulo());
            Usuario usuarioidUsuarioOld = persistentArticulo.getUsuarioidUsuario();
            Usuario usuarioidUsuarioNew = articulo.getUsuarioidUsuario();
            List<Categoria> categoriaListOld = persistentArticulo.getCategoriaList();
            List<Categoria> categoriaListNew = articulo.getCategoriaList();
            List<Oferta> ofertaListOld = persistentArticulo.getOfertaList();
            List<Oferta> ofertaListNew = articulo.getOfertaList();
            List<String> illegalOrphanMessages = null;
            for (Oferta ofertaListOldOferta : ofertaListOld) {
                if (!ofertaListNew.contains(ofertaListOldOferta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Oferta " + ofertaListOldOferta + " since its articuloidArticulo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (usuarioidUsuarioNew != null) {
                usuarioidUsuarioNew = em.getReference(usuarioidUsuarioNew.getClass(), usuarioidUsuarioNew.getIdUsuario());
                articulo.setUsuarioidUsuario(usuarioidUsuarioNew);
            }
            List<Categoria> attachedCategoriaListNew = new ArrayList<Categoria>();
            for (Categoria categoriaListNewCategoriaToAttach : categoriaListNew) {
                categoriaListNewCategoriaToAttach = em.getReference(categoriaListNewCategoriaToAttach.getClass(), categoriaListNewCategoriaToAttach.getIdCategoria());
                attachedCategoriaListNew.add(categoriaListNewCategoriaToAttach);
            }
            categoriaListNew = attachedCategoriaListNew;
            articulo.setCategoriaList(categoriaListNew);
            List<Oferta> attachedOfertaListNew = new ArrayList<Oferta>();
            for (Oferta ofertaListNewOfertaToAttach : ofertaListNew) {
                ofertaListNewOfertaToAttach = em.getReference(ofertaListNewOfertaToAttach.getClass(), ofertaListNewOfertaToAttach.getIdOferta());
                attachedOfertaListNew.add(ofertaListNewOfertaToAttach);
            }
            ofertaListNew = attachedOfertaListNew;
            articulo.setOfertaList(ofertaListNew);
            articulo = em.merge(articulo);
            if (usuarioidUsuarioOld != null && !usuarioidUsuarioOld.equals(usuarioidUsuarioNew)) {
                usuarioidUsuarioOld.getArticuloList().remove(articulo);
                usuarioidUsuarioOld = em.merge(usuarioidUsuarioOld);
            }
            if (usuarioidUsuarioNew != null && !usuarioidUsuarioNew.equals(usuarioidUsuarioOld)) {
                usuarioidUsuarioNew.getArticuloList().add(articulo);
                usuarioidUsuarioNew = em.merge(usuarioidUsuarioNew);
            }
            for (Categoria categoriaListOldCategoria : categoriaListOld) {
                if (!categoriaListNew.contains(categoriaListOldCategoria)) {
                    categoriaListOldCategoria.getArticuloList().remove(articulo);
                    categoriaListOldCategoria = em.merge(categoriaListOldCategoria);
                }
            }
            for (Categoria categoriaListNewCategoria : categoriaListNew) {
                if (!categoriaListOld.contains(categoriaListNewCategoria)) {
                    categoriaListNewCategoria.getArticuloList().add(articulo);
                    categoriaListNewCategoria = em.merge(categoriaListNewCategoria);
                }
            }
            for (Oferta ofertaListNewOferta : ofertaListNew) {
                if (!ofertaListOld.contains(ofertaListNewOferta)) {
                    Articulo oldArticuloidArticuloOfOfertaListNewOferta = ofertaListNewOferta.getArticuloidArticulo();
                    ofertaListNewOferta.setArticuloidArticulo(articulo);
                    ofertaListNewOferta = em.merge(ofertaListNewOferta);
                    if (oldArticuloidArticuloOfOfertaListNewOferta != null && !oldArticuloidArticuloOfOfertaListNewOferta.equals(articulo)) {
                        oldArticuloidArticuloOfOfertaListNewOferta.getOfertaList().remove(ofertaListNewOferta);
                        oldArticuloidArticuloOfOfertaListNewOferta = em.merge(oldArticuloidArticuloOfOfertaListNewOferta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = articulo.getIdArticulo();
                if (findArticulo(id) == null) {
                    throw new NonexistentEntityException("The articulo with id " + id + " no longer exists.");
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
            Articulo articulo;
            try {
                articulo = em.getReference(Articulo.class, id);
                articulo.getIdArticulo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The articulo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Oferta> ofertaListOrphanCheck = articulo.getOfertaList();
            for (Oferta ofertaListOrphanCheckOferta : ofertaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Articulo (" + articulo + ") cannot be destroyed since the Oferta " + ofertaListOrphanCheckOferta + " in its ofertaList field has a non-nullable articuloidArticulo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario usuarioidUsuario = articulo.getUsuarioidUsuario();
            if (usuarioidUsuario != null) {
                usuarioidUsuario.getArticuloList().remove(articulo);
                usuarioidUsuario = em.merge(usuarioidUsuario);
            }
            List<Categoria> categoriaList = articulo.getCategoriaList();
            for (Categoria categoriaListCategoria : categoriaList) {
                categoriaListCategoria.getArticuloList().remove(articulo);
                categoriaListCategoria = em.merge(categoriaListCategoria);
            }
            em.remove(articulo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Articulo> findArticuloEntities() {
        return findArticuloEntities(true, -1, -1);
    }

    public List<Articulo> findArticuloEntities(int maxResults, int firstResult) {
        return findArticuloEntities(false, maxResults, firstResult);
    }

    private List<Articulo> findArticuloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Articulo.class));
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

    public Articulo findArticulo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Articulo.class, id);
        } finally {
            em.close();
        }
    }

    public int getArticuloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Articulo> rt = cq.from(Articulo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
