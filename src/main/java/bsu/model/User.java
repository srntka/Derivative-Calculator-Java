package bsu.model;
import jakarta.persistence.*;

@Entity @Table(name="users")
public class User {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true,nullable=false) private String username;
    @Column(nullable=false) private String passwordHash;
    public User() {}
    public User(String u,String h){username=u;passwordHash=h;}
    public Long getId(){return id;}
    public String getUsername(){return username;}
    public String getPasswordHash(){return passwordHash;}
}