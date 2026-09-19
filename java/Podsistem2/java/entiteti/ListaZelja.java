package entiteti;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "lista_zelja")
@NamedQueries({
    @NamedQuery(name = "ListaZelja.findAll", query = "SELECT l FROM ListaZelja l"),
    @NamedQuery(name = "ListaZelja.findByIdListaZelja", query = "SELECT l FROM ListaZelja l WHERE l.idListaZelja = :idListaZelja"),
    @NamedQuery(name = "ListaZelja.findByDatumKreiranja", query = "SELECT l FROM ListaZelja l WHERE l.datumKreiranja = :datumKreiranja"),
    @NamedQuery(name = "ListaZelja.findByIdKorisnika", query = "SELECT l FROM ListaZelja l WHERE l.idKorisnika = :idKorisnika")})
public class ListaZelja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idListaZelja")
    private Integer idListaZelja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datumKreiranja")
    @Temporal(TemporalType.DATE)
    private Date datumKreiranja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idKorisnika")
    private int idKorisnika;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idListeZelja")
    private Collection<ArtikliUListiZelja> artikliUListiZeljaCollection;

    public ListaZelja() {
    }

    public ListaZelja(Integer idListaZelja) {
        this.idListaZelja = idListaZelja;
    }

    public ListaZelja(Integer idListaZelja, Date datumKreiranja, int idKorisnika) {
        this.idListaZelja = idListaZelja;
        this.datumKreiranja = datumKreiranja;
        this.idKorisnika = idKorisnika;
    }

    public Integer getIdListaZelja() {
        return idListaZelja;
    }

    public void setIdListaZelja(Integer idListaZelja) {
        this.idListaZelja = idListaZelja;
    }

    public Date getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(Date datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public int getIdKorisnika() {
        return idKorisnika;
    }

    public void setIdKorisnika(int idKorisnika) {
        this.idKorisnika = idKorisnika;
    }

    public Collection<ArtikliUListiZelja> getArtikliUListiZeljaCollection() {
        return artikliUListiZeljaCollection;
    }

    public void setArtikliUListiZeljaCollection(Collection<ArtikliUListiZelja> artikliUListiZeljaCollection) {
        this.artikliUListiZeljaCollection = artikliUListiZeljaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idListaZelja != null ? idListaZelja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListaZelja)) {
            return false;
        }
        ListaZelja other = (ListaZelja) object;
        if ((this.idListaZelja == null && other.idListaZelja != null) || (this.idListaZelja != null && !this.idListaZelja.equals(other.idListaZelja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.ListaZelja[ idListaZelja=" + idListaZelja + " ]";
    }
    
}
