package fudan.se.lab2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author LBW
 */
@Entity
public class Meeting {

    private static final long serialVersionUID = -5642777274465208640L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String shortName;

    private String FullName;
    private String Date;
    private String location;
    private String Deadline;
    private String DateForResults;
    private String approval;//会议是否被批准的状态,暂定为三种"Unprocessed"，"ApplyFailed","ApplyPassed"

    public Meeting(){}

    public Meeting(String shortName, String fullName, String date, String location, String deadline, String dateForResults, String approval, Set<User> users) {
        this.shortName = shortName;
        FullName = fullName;
        Date = date;
        this.location = location;
        Deadline = deadline;
        DateForResults = dateForResults;
        this.approval = approval;
        this.users = users;
    }

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<User> users = new HashSet<>();

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        shortName = shortName;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDeadline() {
        return Deadline;
    }

    public void setDeadline(String deadline) {
        Deadline = deadline;
    }

    public String getDateForResults() {
        return DateForResults;
    }

    public void setDateForResults(String dateForResults) {
        DateForResults = dateForResults;
    }

    public Collection<? extends UserDetails> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
