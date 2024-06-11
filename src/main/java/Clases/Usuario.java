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
import javax.persistence.ManyToMany;
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
@Table(name = "usuario", catalog = "poobd", schema = "")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuario.findByNombres", query = "SELECT u FROM Usuario u WHERE u.nombres = :nombres"),
    @NamedQuery(name = "Usuario.findByApellidos", query = "SELECT u FROM Usuario u WHERE u.apellidos = :apellidos"),
    @NamedQuery(name = "Usuario.findByCorreo", query = "SELECT u FROM Usuario u WHERE u.correo = :correo"),
    @NamedQuery(name = "Usuario.findByDireccion", query = "SELECT u FROM Usuario u WHERE u.direccion = :direccion"),
    @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email"),
    @NamedQuery(name = "Usuario.findByContrase\u00f1a", query = "SELECT u FROM Usuario u WHERE u.contrase\u00f1a = :contrase\u00f1a"),
    @NamedQuery(name = "Usuario.findByFechaRegistro", query = "SELECT u FROM Usuario u WHERE u.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "Usuario.findByVerificado", query = "SELECT u FROM Usuario u WHERE u.verificado = :verificado"),
    @NamedQuery(name = "Usuario.findBySuspendido", query = "SELECT u FROM Usuario u WHERE u.suspendido = :suspendido"),
    @NamedQuery(name = "Usuario.findByPremium", query = "SELECT u FROM Usuario u WHERE u.premium = :premium")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idUsuario", nullable = false)
    private Integer idUsuario;
    @Basic(optional = false)
    @Column(name = "nombres", nullable = false, length = 50)
    private String nombres;
    @Basic(optional = false)
    @Column(name = "apellidos", nullable = false, length = 50)
    private String apellidos;
    @Basic(optional = false)
    @Column(name = "correo", nullable = false, length = 70)
    private String correo;
    @Basic(optional = false)
    @Column(name = "direccion", nullable = false, length = 90)
    private String direccion;
    @Basic(optional = false)
    @Column(name = "email", nullable = false, length = 80)
    private String email;
    @Basic(optional = false)
    @Column(name = "contrase\u00f1a", nullable = false, length = 70)
    private String contraseña;
    @Basic(optional = false)
    @Column(name = "fechaRegistro", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Column(name = "verificado")
    private Short verificado;
    @Column(name = "suspendido")
    private Short suspendido;
    @Column(name = "premium")
    private Short premium;
    @ManyToMany(mappedBy = "usuarioList")
    private List<Evaluacion> evaluacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioidUsuario")
    private List<Oferta> ofertaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioidUsuario")
    private List<Problematecnico> problematecnicoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioidUsuario")
    private List<Articulo> articuloList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioidUsuario")
    private List<Notificacion> notificacionList;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Integer idUsuario, String nombres, String apellidos, String correo, String direccion, String email, String contraseña, Date fechaRegistro) {
        this.idUsuario = idUsuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.direccion = direccion;
        this.email = email;
        this.contraseña = contraseña;
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Short getVerificado() {
        return verificado;
    }

    public void setVerificado(Short verificado) {
        this.verificado = verificado;
    }

    public Short getSuspendido() {
        return suspendido;
    }

    public void setSuspendido(Short suspendido) {
        this.suspendido = suspendido;
    }

    public Short getPremium() {
        return premium;
    }

    public void setPremium(Short premium) {
        this.premium = premium;
    }

    public List<Evaluacion> getEvaluacionList() {
        return evaluacionList;
    }

    public void setEvaluacionList(List<Evaluacion> evaluacionList) {
        this.evaluacionList = evaluacionList;
    }

    public List<Oferta> getOfertaList() {
        return ofertaList;
    }

    public void setOfertaList(List<Oferta> ofertaList) {
        this.ofertaList = ofertaList;
    }

    public List<Problematecnico> getProblematecnicoList() {
        return problematecnicoList;
    }

    public void setProblematecnicoList(List<Problematecnico> problematecnicoList) {
        this.problematecnicoList = problematecnicoList;
    }

    public List<Articulo> getArticuloList() {
        return articuloList;
    }

    public void setArticuloList(List<Articulo> articuloList) {
        this.articuloList = articuloList;
    }

    public List<Notificacion> getNotificacionList() {
        return notificacionList;
    }

    public void setNotificacionList(List<Notificacion> notificacionList) {
        this.notificacionList = notificacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Usuario[ idUsuario=" + idUsuario + " ]";
    }
    
}
