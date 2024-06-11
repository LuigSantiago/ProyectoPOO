/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "evaluacion", catalog = "poobd", schema = "")
@NamedQueries({
    @NamedQuery(name = "Evaluacion.findAll", query = "SELECT e FROM Evaluacion e"),
    @NamedQuery(name = "Evaluacion.findByIdEvaluacion", query = "SELECT e FROM Evaluacion e WHERE e.idEvaluacion = :idEvaluacion"),
    @NamedQuery(name = "Evaluacion.findByComentario", query = "SELECT e FROM Evaluacion e WHERE e.comentario = :comentario"),
    @NamedQuery(name = "Evaluacion.findByPuntuacion", query = "SELECT e FROM Evaluacion e WHERE e.puntuacion = :puntuacion"),
    @NamedQuery(name = "Evaluacion.findByFecha", query = "SELECT e FROM Evaluacion e WHERE e.fecha = :fecha")})
public class Evaluacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idEvaluacion", nullable = false)
    private Integer idEvaluacion;
    @Basic(optional = false)
    @Column(name = "comentario", nullable = false, length = 70)
    private String comentario;
    @Basic(optional = false)
    @Column(name = "puntuacion", nullable = false)
    private int puntuacion;
    @Basic(optional = false)
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinTable(name = "evaluacion_has_usuario", joinColumns = {
        @JoinColumn(name = "Evaluacion_idEvaluacion", referencedColumnName = "idEvaluacion", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "Usuario_idUsuario", referencedColumnName = "idUsuario", nullable = false)})
    @ManyToMany
    private List<Usuario> usuarioList;

    public Evaluacion() {
    }

    public Evaluacion(Integer idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public Evaluacion(Integer idEvaluacion, String comentario, int puntuacion, Date fecha) {
        this.idEvaluacion = idEvaluacion;
        this.comentario = comentario;
        this.puntuacion = puntuacion;
        this.fecha = fecha;
    }

    public Integer getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(Integer idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEvaluacion != null ? idEvaluacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluacion)) {
            return false;
        }
        Evaluacion other = (Evaluacion) object;
        if ((this.idEvaluacion == null && other.idEvaluacion != null) || (this.idEvaluacion != null && !this.idEvaluacion.equals(other.idEvaluacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Evaluacion[ idEvaluacion=" + idEvaluacion + " ]";
    }
    
}
