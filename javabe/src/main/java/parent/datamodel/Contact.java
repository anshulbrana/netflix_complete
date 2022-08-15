package parent.datamodel;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private ZonedDateTime birthDate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "email")
    private String email;

    @OneToOne
    @JoinColumn(unique = true)
    private MovieUser movieUser;

    @OneToMany(mappedBy = "contact")
    private Set<Address> addresses = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Contact name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZonedDateTime getBirthDate() {
        return birthDate;
    }

    public Contact birthDate(ZonedDateTime birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public void setBirthDate(ZonedDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public Contact gender(String gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public Contact email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MovieUser getMovieUser() {
        return movieUser;
    }

    public Contact movieUser(MovieUser movieUser) {
        this.movieUser = movieUser;
        return this;
    }

    public void setMovieUser(MovieUser movieUser) {
        this.movieUser = movieUser;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

}
