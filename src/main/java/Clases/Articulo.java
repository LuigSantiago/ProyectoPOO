/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "articulo", catalog = "poobd", schema = "")
@NamedQueries({
    @NamedQuery(name = "Articulo.findAll", query = "SELECT a FROM Articulo a"),
    @NamedQuery(name = "Articulo.findByIdArticulo", query = "SELECT a FROM Articulo a WHERE a.idArticulo = :idArticulo"),
    @NamedQuery(name = "Articulo.findByNombre", query = "SELECT a FROM Articulo a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Articulo.findByDescripcion", query = "SELECT a FROM Articulo a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "Articulo.findByEstado", query = "SELECT a FROM Articulo a WHERE a.estado = :estado"),
    @NamedQuery(name = "Articulo.findByPrecioInicial", query = "SELECT a FROM Articulo a WHERE a.precioInicial = :precioInicial"),
    @NamedQuery(name = "Articulo.findByFechaLimite", query = "SELECT a FROM Articulo a WHERE a.fechaLimite = :fechaLimite"),
    @NamedQuery(name = "Articulo.findByVendido", query = "SELECT a FROM Articulo a WHERE a.vendido = :vendido")})
public class Articulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idArticulo", nullable = false)
    private Integer idArticulo;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "descripcion", nullable = false, length = 70)
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "estado", nullable = false, length = 20)
    private String estado;
    @Basic(optional = false)
    @Column(name = "precioInicial", nullable = false)
    private int precioInicial;
    @Basic(optional = false)
    @Column(name = "fechaLimite", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaLimite;
    @Column(name = "vendido")
    private Short vendido;
    @JoinTable(name = "articulo_has_categoria", joinColumns = {
        @JoinColumn(name = "Articulo_idArticulo", referencedColumnName = "idArticulo", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "Categoria_idCategoria", referencedColumnName = "idCategoria", nullable = false)})
    @ManyToMany
    private List<Categoria> categoriaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articuloidArticulo")
    private List<Oferta> ofertaList;
    @JoinColumn(name = "Usuario_idUsuario", referencedColumnName = "idUsuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario usuarioidUsuario;

    public Articulo() {
    }

    public Articulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Articulo(Integer idArticulo, String nombre, String descripcion, String estado, int precioInicial, Date fechaLimite) {
        this.idArticulo = idArticulo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.precioInicial = precioInicial;
        this.fechaLimite = fechaLimite;
    }

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getPrecioInicial() {
        return precioInicial;
    }

    public void setPrecioInicial(int precioInicial) {
        this.precioInicial = precioInicial;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public Short getVendido() {
        return vendido;
    }

    public void setVendido(Short vendido) {
        this.vendido = vendido;
    }

    public List<Categoria> getCategoriaList() {
        return categoriaList;
    }

    public void setCategoriaList(List<Categoria> categoriaList) {
        this.categoriaList = categoriaList;
    }

    public List<Oferta> getOfertaList() {
        return ofertaList;
    }

    public void setOfertaList(List<Oferta> ofertaList) {
        this.ofertaList = ofertaList;
    }

    public Usuario getUsuarioidUsuario() {
        return usuarioidUsuario;
    }

    public void setUsuarioidUsuario(Usuario usuarioidUsuario) {
        this.usuarioidUsuario = usuarioidUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArticulo != null ? idArticulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Articulo)) {
            return false;
        }
        Articulo other = (Articulo) object;
        if ((this.idArticulo == null && other.idArticulo != null) || (this.idArticulo != null && !this.idArticulo.equals(other.idArticulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Articulo[ idArticulo=" + idArticulo + " ]";
    }
    
}
