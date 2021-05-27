package galerie.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.util.LinkedList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@ToString


@Entity
public class Artiste extends Personne {
    @NotEmpty
    private String biographie;

    @OneToMany(mappedBy="auteur")
    @ToString.Exclude
    private List<Tableau> oeuvres = new LinkedList<>();
}
