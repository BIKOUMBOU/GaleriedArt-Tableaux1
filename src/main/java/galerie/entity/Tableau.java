package galerie.entity;
import java.util.*;
import javax.persistence.*;
import lombok.*;


@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity //
public class Tableau {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String titre;

    private String dimension;

    private String support;

    @OneToOne(mappedBy = "oeuvre")
    @ToString.Exclude
    private Transaction vendu;

    @ManyToOne
    Artiste auteur;
    @ToString.Exclude

    @ManyToMany(mappedBy= "oeuvres")
    private List<Exposition> accrochages = new LinkedList<>();

}
