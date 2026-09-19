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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "korpa")
@NamedQueries({
    @NamedQuery(name = "Korpa.findAll", query = "SELECT k FROM Korpa k"),
    @NamedQuery(name = "Korpa.findByIdKorpe", query = "SELECT k FROM Korpa k WHERE k.idKorpe = :idKorpe"),
    @NamedQuery(name = "Korpa.findByUkupnaCena", query = "SELECT k FROM Korpa k WHERE k.ukupnaCena = :ukupnaCena"),
    @NamedQuery(name = "Korpa.findByIdKorisnika", query = "SELECT k FROM Korpa k WHERE k.idKorisnika = :idKorisnika")})
public class Korpa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idKorpe")
    private Integer idKorpe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ukupnaCena")
    private int ukupnaCena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idKorisnika")
    private int idKorisnika;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idKorpeFK")
    private Collection<ArtikliUKorpi> artikliUKorpiCollection;

    public Korpa() {
    }

    public Korpa(Integer idKorpe) {
        this.idKorpe = idKorpe;
    }

    public Korpa(Integer idKorpe, int ukupnaCena, int idKorisnika) {
        this.idKorpe = idKorpe;
        this.ukupnaCena = ukupnaCena;
        this.idKorisnika = idKorisnika;
    }

    public Integer getIdKorpe() {
        return idKorpe;
    }

    public void setIdKorpe(Integer idKorpe) {
        this.idKorpe = idKorpe;
    }

    public int getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(int ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKorpe != null ? idKorpe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Korpa)) {
            return false;
        }
        Korpa other = (Korpa) object;
        if ((this.idKorpe == null && other.idKorpe != null) || (this.idKorpe != null && !this.idKorpe.equals(other.idKorpe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.Korpa[ idKorpe=" + idKorpe + " ]";
    }
    
}
