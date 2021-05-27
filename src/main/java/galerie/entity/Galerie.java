package galerie.entity;
import lombok.*;
import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class Galerie {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true)
    @NonNull
    private String nom;

    @NonNull
    private String adresse;

    @OneToMany(mappedBy = "organisateur")
    List<Exposition> evenements = new LinkedList<>();

    public float CAannuel(int annee) {
        float result = 0.00f;
        for (Exposition evenement : evenements)
            if (evenement.getDebut().getYear() == annee)
                result += evenement.CA();
        return result;
    }
}
