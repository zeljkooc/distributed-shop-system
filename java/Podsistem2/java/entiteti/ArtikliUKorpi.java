package entiteti;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "artikli_u_korpi")
@NamedQueries({
    @NamedQuery(name = "ArtikliUKorpi.findAll", query = "SELECT a FROM ArtikliUKorpi a"),
    @NamedQuery(name = "ArtikliUKorpi.findByIdStavke", query = "SELECT a FROM ArtikliUKorpi a WHERE a.idStavke = :idStavke"),
    @NamedQuery(name = "ArtikliUKorpi.findByKolicina", query = "SELECT a FROM ArtikliUKorpi a WHERE a.kolicina = :kolicina")})
public class ArtikliUKorpi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idStavke")
    private Integer idStavke;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kolicina")
    private int kolicina;
    @JoinColumn(name = "idArtiklaFK", referencedColumnName = "idArtikla")
    @ManyToOne(optional = false)
    private Artikal idArtiklaFK;
    @JoinColumn(name = "idKorpeFK", referencedColumnName = "idKorpe")
    @ManyToOne(optional = false)
    private Korpa idKorpeFK;

    public ArtikliUKorpi() {
    }

    public ArtikliUKorpi(Integer idStavke) {
        this.idStavke = idStavke;
    }

    public ArtikliUKorpi(Integer idStavke, int kolicina) {
        this.idStavke = idStavke;
        this.kolicina = kolicina;
    }

    public Integer getIdStavke() {
        return idStavke;
    }

    public void setIdStavke(Integer idStavke) {
        this.idStavke = idStavke;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public Artikal getIdArtiklaFK() {
        return idArtiklaFK;
    }

    public void setIdArtiklaFK(Artikal idArtiklaFK) {
        this.idArtiklaFK = idArtiklaFK;
    }

    public Korpa getIdKorpeFK() {
        return idKorpeFK;
    }

    public void setIdKorpeFK(Korpa idKorpeFK) {
        this.idKorpeFK = idKorpeFK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStavke != null ? idStavke.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArtikliUKorpi)) {
            return false;
        }
        ArtikliUKorpi other = (ArtikliUKorpi) object;
        if ((this.idStavke == null && other.idStavke != null) || (this.idStavke != null && !this.idStavke.equals(other.idStavke))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.ArtikliUKorpi[ idStavke=" + idStavke + " ]";
    }
    
}
