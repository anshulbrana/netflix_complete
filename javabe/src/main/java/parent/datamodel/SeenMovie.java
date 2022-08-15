package parent.datamodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "seen_movie")
public class SeenMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date")
    private ZonedDateTime date;

    @ManyToOne
    @JsonIgnoreProperties("seenMovies")
    private Movie movie;

    @ManyToOne
    @JsonIgnoreProperties("seenMovies")
    private MovieUser movieUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public SeenMovie date(ZonedDateTime date) {
        this.date = date;
        return this;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public Movie getMovie() {
        return movie;
    }

    public SeenMovie movie(Movie movie) {
        this.movie = movie;
        return this;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public MovieUser getMovieUser() {
        return movieUser;
    }

    public SeenMovie movieUser(MovieUser movieUser) {
        this.movieUser = movieUser;
        return this;
    }

    public void setMovieUser(MovieUser movieUser) {
        this.movieUser = movieUser;
    }
}
