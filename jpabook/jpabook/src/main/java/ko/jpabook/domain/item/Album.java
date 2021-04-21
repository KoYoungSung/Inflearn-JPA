package ko.jpabook.domain.item;
import javax.persistence.Entity;

@Entity
@Setter @Getter
public class Album extends Item{
    private String artist;
    private String etc;
}
