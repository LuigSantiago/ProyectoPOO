/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "problematecnico", catalog = "poobd", schema = "")
@NamedQueries({
    @NamedQuery(name = "Problematecnico.findAll", query = "SELECT p FROM Problematecnico p"),
    @NamedQuery(name = "Problematecnico.findByIdProblemaTecnico", query = "SELECT p FROM Problematecnico p WHERE p.idProblemaTecnico = :idProblemaTecnico"),
    @NamedQuery(name = "Problematecnico.findByDescripcion", query = "SELECT p FROM Problematecnico p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Problematecnico.findByFechaReporte", query = "SELECT p FROM Problematecnico p WHERE p.fechaReporte = :fechaReporte"),
    @NamedQuery(name = "Problematecnico.findByEstado", query = "SELECT p FROM Problematecnico p WHERE p.estado = :estado")})
public class Problematecnico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idProblemaTecnico", nullable = false)
    private Integer idProblemaTecnico;
    @Basic(optional = false)
    @Column(name = "descripcion", nullable = false, length = 60)
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "fechaReporte", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaReporte;
    @Column(name = "estado")
    private Short estado;
    @JoinColumn(name = "Usuario_idUsuario", referencedColumnName = "idUsuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario usuarioidUsuario;

    public Problematecnico() {
    }

    public Problematecnico(Integer idProblemaTecnico) {
        this.idProblemaTecnico = idProblemaTecnico;
    }

    public Problematecnico(Integer idProblemaTecnico, String descripcion, Date fechaReporte) {
        this.idProblemaTecnico = idProblemaTecnico;
        this.descripcion = descripcion;
        this.fechaReporte = fechaReporte;
    }

    public Integer getIdProblemaTecnico() {
        return idProblemaTecnico;
    }

    public void setIdProblemaTecnico(Integer idProblemaTecnico) {
        this.idProblemaTecnico = idProblemaTecnico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
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
        hash += (idProblemaTecnico != null ? idProblemaTecnico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Problematecnico)) {
            return false;
        }
        Problematecnico other = (Problematecnico) object;
        if ((this.idProblemaTecnico == null && other.idProblemaTecnico != null) || (this.idProblemaTecnico != null && !this.idProblemaTecnico.equals(other.idProblemaTecnico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Problematecnico[ idProblemaTecnico=" + idProblemaTecnico + " ]";
    }
    
}
