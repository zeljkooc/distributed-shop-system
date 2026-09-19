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
import javax.validation.constraints.Size;


@Entity
@Table(name = "artikli_u_listi_zelja")
@NamedQueries({
    @NamedQuery(name = "ArtikliUListiZelja.findAll", query = "SELECT a FROM ArtikliUListiZelja a"),
    @NamedQuery(name = "ArtikliUListiZelja.findByIdArtikliulistizelja", query = "SELECT a FROM ArtikliUListiZelja a WHERE a.idArtikliulistizelja = :idArtikliulistizelja"),
    @NamedQuery(name = "ArtikliUListiZelja.findByVremeDodavanja", query = "SELECT a FROM ArtikliUListiZelja a WHERE a.vremeDodavanja = :vremeDodavanja")})
public class ArtikliUListiZelja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idArtikli_u_listi_zelja")
    private Integer idArtikliulistizelja;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "vremeDodavanja")
    private String vremeDodavanja;
    @JoinColumn(name = "idArtikla", referencedColumnName = "idArtikla")
    @ManyToOne(optional = false)
    private Artikal idArtikla;
    @JoinColumn(name = "idListeZelja", referencedColumnName = "idListaZelja")
    @ManyToOne(optional = false)
    private ListaZelja idListeZelja;

    public ArtikliUListiZelja() {
    }

    public ArtikliUListiZelja(Integer idArtikliulistizelja) {
        this.idArtikliulistizelja = idArtikliulistizelja;
    }

    public ArtikliUListiZelja(Integer idArtikliulistizelja, String vremeDodavanja) {
        this.idArtikliulistizelja = idArtikliulistizelja;
        this.vremeDodavanja = vremeDodavanja;
    }

    public Integer getIdArtikliulistizelja() {
        return idArtikliulistizelja;
    }

    public void setIdArtikliulistizelja(Integer idArtikliulistizelja) {
        this.idArtikliulistizelja = idArtikliulistizelja;
    }

    public String getVremeDodavanja() {
        return vremeDodavanja;
    }

    public void setVremeDodavanja(String vremeDodavanja) {
        this.vremeDodavanja = vremeDodavanja;
    }

    public Artikal getIdArtikla() {
        return idArtikla;
    }

    public void setIdArtikla(Artikal idArtikla) {
        this.idArtikla = idArtikla;
    }

    public ListaZelja getIdListeZelja() {
        return idListeZelja;
    }

    public void setIdListeZelja(ListaZelja idListeZelja) {
        this.idListeZelja = idListeZelja;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArtikliulistizelja != null ? idArtikliulistizelja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArtikliUListiZelja)) {
            return false;
        }
        ArtikliUListiZelja other = (ArtikliUListiZelja) object;
        if ((this.idArtikliulistizelja == null && other.idArtikliulistizelja != null) || (this.idArtikliulistizelja != null && !this.idArtikliulistizelja.equals(other.idArtikliulistizelja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.ArtikliUListiZelja[ idArtikliulistizelja=" + idArtikliulistizelja + " ]";
    }
    
}
