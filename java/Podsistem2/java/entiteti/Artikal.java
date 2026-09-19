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
@Table(name = "artikal")
@NamedQueries({
    @NamedQuery(name = "Artikal.findAll", query = "SELECT a FROM Artikal a"),
    @NamedQuery(name = "Artikal.findByIdArtikla", query = "SELECT a FROM Artikal a WHERE a.idArtikla = :idArtikla"),
    @NamedQuery(name = "Artikal.findByNazivArtikla", query = "SELECT a FROM Artikal a WHERE a.nazivArtikla = :nazivArtikla"),
    @NamedQuery(name = "Artikal.findByOpisArtikla", query = "SELECT a FROM Artikal a WHERE a.opisArtikla = :opisArtikla"),
    @NamedQuery(name = "Artikal.findByCenaArtikla", query = "SELECT a FROM Artikal a WHERE a.cenaArtikla = :cenaArtikla"),
    @NamedQuery(name = "Artikal.findByPopust", query = "SELECT a FROM Artikal a WHERE a.popust = :popust"),
    @NamedQuery(name = "Artikal.findByIdKorisnika", query = "SELECT a FROM Artikal a WHERE a.idKorisnika = :idKorisnika")})
public class Artikal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idArtikla")
    private Integer idArtikla;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nazivArtikla")
    private String nazivArtikla;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "opisArtikla")
    private String opisArtikla;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cenaArtikla")
    private int cenaArtikla;
    @Basic(optional = false)
    @NotNull
    @Column(name = "popust")
    private int popust;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idKorisnika")
    private int idKorisnika;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArtiklaFK")
    private Collection<ArtikliUKorpi> artikliUKorpiCollection;
    @JoinColumn(name = "idKategorije", referencedColumnName = "idKategorije")
    @ManyToOne(optional = false)
    private Kategorija idKategorije;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArtikla")
    private Collection<ArtikliUListiZelja> artikliUListiZeljaCollection;

    public Artikal() {
    }

    public Artikal(Integer idArtikla) {
        this.idArtikla = idArtikla;
    }

    public Artikal(Integer idArtikla, String nazivArtikla, String opisArtikla, int cenaArtikla, int popust, int idKorisnika) {
        this.idArtikla = idArtikla;
        this.nazivArtikla = nazivArtikla;
        this.opisArtikla = opisArtikla;
        this.cenaArtikla = cenaArtikla;
        this.popust = popust;
        this.idKorisnika = idKorisnika;
    }

    public Integer getIdArtikla() {
        return idArtikla;
    }

    public void setIdArtikla(Integer idArtikla) {
        this.idArtikla = idArtikla;
    }

    public String getNazivArtikla() {
        return nazivArtikla;
    }

    public void setNazivArtikla(String nazivArtikla) {
        this.nazivArtikla = nazivArtikla;
    }

    public String getOpisArtikla() {
        return opisArtikla;
    }

    public void setOpisArtikla(String opisArtikla) {
        this.opisArtikla = opisArtikla;
    }

    public int getCenaArtikla() {
        return cenaArtikla;
    }

    public void setCenaArtikla(int cenaArtikla) {
        this.cenaArtikla = cenaArtikla;
    }

    public int getPopust() {
        return popust;
    }

    public void setPopust(int popust) {
        this.popust = popust;
    }

    public int getIdKorisnika() {
        return idKorisnika;
    }

    public void setIdKorisnika(int idKorisnika) {
        this.idKorisnika = idKorisnika;
    }

    public Collection<ArtikliUKorpi> getArtikliUKorpiCollection() {
        return artikliUKorpiCollection;
    }

    public void setArtikliUKorpiCollection(Collection<ArtikliUKorpi> artikliUKorpiCollection) {
        this.artikliUKorpiCollection = artikliUKorpiCollection;
    }

    public Kategorija getIdKategorije() {
        return idKategorije;
    }

    public void setIdKategorije(Kategorija idKategorije) {
        this.idKategorije = idKategorije;
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
        hash += (idArtikla != null ? idArtikla.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Artikal)) {
            return false;
        }
        Artikal other = (Artikal) object;
        if ((this.idArtikla == null && other.idArtikla != null) || (this.idArtikla != null && !this.idArtikla.equals(other.idArtikla))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.Artikal[ idArtikla=" + idArtikla + " ]";
    }
    
}
