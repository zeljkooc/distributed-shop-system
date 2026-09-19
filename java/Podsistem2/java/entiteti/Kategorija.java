package entiteti;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "kategorija")
@NamedQueries({
    @NamedQuery(name = "Kategorija.findAll", query = "SELECT k FROM Kategorija k"),
    @NamedQuery(name = "Kategorija.findByIdKategorije", query = "SELECT k FROM Kategorija k WHERE k.idKategorije = :idKategorije"),
    @NamedQuery(name = "Kategorija.findByNazivKategorije", query = "SELECT k FROM Kategorija k WHERE k.nazivKategorije = :nazivKategorije")})
public class Kategorija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idKategorije")
    private Integer idKategorije;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nazivKategorije")
    private String nazivKategorije;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idKategorije")
    private Collection<Artikal> artikalCollection;
    @OneToMany(mappedBy = "idNadkategorije")
    private Collection<Kategorija> kategorijaCollection;
    @JoinColumn(name = "idNadkategorije", referencedColumnName = "idKategorije")
    @ManyToOne
    private Kategorija idNadkategorije;

    public Kategorija() {
    }

    public Kategorija(Integer idKategorije) {
        this.idKategorije = idKategorije;
    }

    public Kategorija(Integer idKategorije, String nazivKategorije) {
        this.idKategorije = idKategorije;
        this.nazivKategorije = nazivKategorije;
    }

    public Integer getIdKategorije() {
        return idKategorije;
    }

    public void setIdKategorije(Integer idKategorije) {
        this.idKategorije = idKategorije;
    }

    public String getNazivKategorije() {
        return nazivKategorije;
    }

    public void setNazivKategorije(String nazivKategorije) {
        this.nazivKategorije = nazivKategorije;
    }

    public Collection<Artikal> getArtikalCollection() {
        return artikalCollection;
    }

    public void setArtikalCollection(Collection<Artikal> artikalCollection) {
        this.artikalCollection = artikalCollection;
    }

    public Collection<Kategorija> getKategorijaCollection() {
        return kategorijaCollection;
    }

    public void setKategorijaCollection(Collection<Kategorija> kategorijaCollection) {
        this.kategorijaCollection = kategorijaCollection;
    }

    public Kategorija getIdNadkategorije() {
        return idNadkategorije;
    }

    public void setIdNadkategorije(Kategorija idNadkategorije) {
        this.idNadkategorije = idNadkategorije;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKategorije != null ? idKategorije.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kategorija)) {
            return false;
        }
        Kategorija other = (Kategorija) object;
        if ((this.idKategorije == null && other.idKategorije != null) || (this.idKategorije != null && !this.idKategorije.equals(other.idKategorije))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.Kategorija[ idKategorije=" + idKategorije + " ]";
    }
    
}
