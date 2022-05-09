package entities;

import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;
  @Basic(optional = false)
  @NotNull
  @Column(name = "user_name", length = 25)
  private String userName;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "user_pass")
  private String userPass;
  @JoinTable(name = "user_roles", joinColumns = {
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")}, inverseJoinColumns = {
    @JoinColumn(name = "role_name", referencedColumnName = "role_name")})
  @ManyToMany
  private List<Role> roleList = new ArrayList<>();


  @OneToMany(mappedBy = "user")
  private List<UserMovie> userMovieList = new ArrayList<>();

//  @ManyToMany(fetch = FetchType.EAGER)
//  @JoinTable(
//          name ="user_movie",
//          joinColumns = @JoinColumn(name="user_id"),
//          inverseJoinColumns = @JoinColumn(name="movie_id"))
//  private List<Movie> movieList = new ArrayList<>();
//
//  @ManyToMany
//  private List <Dislike> dislikedListUser = new ArrayList<>();


//  public void addMovie(Movie movie){
//    this.movieList.add(movie);
//    movie.addUser(this);
//  }


  public List<String> getRolesAsStrings() {
    if (roleList.isEmpty()) {
      return null;
    }
    List<String> rolesAsStrings = new ArrayList<>();
    roleList.forEach((role) -> {
        rolesAsStrings.add(role.getRoleName());
      });
    return rolesAsStrings;
  }

  public User() {}

  //TODO Change when password is hashed
   public boolean verifyPassword(String pw){
        return(BCrypt.checkpw(pw,userPass));
    }

  public User(String userName, String userPass) {
    this.userName = userName;

    this.userPass = BCrypt.hashpw(userPass,BCrypt.gensalt());
  }

  public void addToUserMovieList(UserMovie userMovie){
    userMovie.setUser(this);
    this.userMovieList.add(userMovie);
  }

  public List<UserMovie> getUserMovieList() {
    return userMovieList;
  }

  public void setUserMovieList(List<UserMovie> userMovieList) {
    this.userMovieList = userMovieList;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserPass() {
    return this.userPass;
  }

  public void setUserPass(String userPass) {
    this.userPass = userPass;
  }

  public List<Role> getRoleList() {
    return roleList;
  }

  public void setRoleList(List<Role> roleList) {
    this.roleList = roleList;
  }

  public void addRole(Role userRole) {
    roleList.add(userRole);
  }

//  public List<Movie> getMovieList() {
//    return movieList;
//  }
//
//  public void setMovieList(List<Movie> movieList) {
//    this.movieList = movieList;
//  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

//  public void addDislike(UserMovie userMovie) {
//    this.dislikedListUser.add(userMovie);
//  }
}
