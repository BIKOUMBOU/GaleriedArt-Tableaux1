package galerie.dao;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;

import galerie.entity.Transaction;
import lombok.*;
import org.springframework.data.jpa.repository.JpaRepository;


@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString


@Entity


public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
