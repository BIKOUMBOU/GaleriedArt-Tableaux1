package galerie.entity;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString

@Entity

public class Personne {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String nom;

    @Column(unique=true)
    private String adresse;

    @OneToMany(mappedBy = "client")
    @ToString.Exclude
    private List<Transaction> achats = new LinkedList<>();

    public float budgetArt(int annee) {
        float result=0.00f;
        for (Transaction achat : achats)
            if (achat.getVenduLe().getYear() == annee)
                result += achat.getPrixVente();
        return result;

    }
}
