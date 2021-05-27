package galerie.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString

@Entity
public class Transaction {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @ManyToOne
    private Personne client;

    @NonNull
    @OneToOne
    private Tableau oeuvre;

    @NonNull
    @ManyToOne
    private Exposition lieuDeVente;

    @NonNull
    private Float prixVente;

    private LocalDate venduLe = LocalDate.now();

    public float getPrixVente() {
        return 0;
    }
}
