package taskManager.model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class Credentials {
	public static final String DefaultRole="USER";
	public static final String AdminRole="ADMIN";
	
	//variabili
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, name="id")
	private Long id;
	@Column(nullable = false, unique = true)
	private String username;
	@Column(nullable = false)
	private String password;
	@OneToOne(cascade=CascadeType.ALL)
	//@JoinColumn(name = "utente_id", referencedColumnName = "id")
	private User user;
	@Column(nullable = false, updatable = false)
	private LocalDateTime creationTimeStamp;
	@Column(nullable = false)
	private LocalDateTime lastUpdate;
	private String role;
	
	//costruttore
	public Credentials() {
		
	}
	
	public Credentials(String username,String password) {
		this.username=username;
		this.password=password;
		
	}
	@PrePersist
	protected void onPersist() {
		this.creationTimeStamp = LocalDateTime.now();
		this.lastUpdate = LocalDateTime.now();
	}
	@PreUpdate
	protected void onUpdate() {
		this.lastUpdate=LocalDateTime.now();
	}
	
	//metodi setter e getter
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id=id;
	}
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username=username;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public User getUser() {
		return this.user;
	}
	public void setUser(User user) {
		this.user=user;
	}
	public LocalDateTime getCreationTimeStamp() {
		return this.creationTimeStamp;
	}
	public LocalDateTime getUpdateCreationTime() {
		return this.lastUpdate;
	}
	public void setUpdateCreationTime(LocalDateTime time) {
		this.lastUpdate=time;
	}
	public String getRole() {
		return this.role;
	}
	public void setRole(String role) {
		this.role=role;
	}
	
	//equals e hashCode
	@Override
	public boolean equals(Object o) {
		if(this==o)
			return true;
		if(!(o instanceof Credentials))
			return false;
		Credentials credentials=(Credentials)o;
		return this.username.equals(credentials.username);
	}
	@Override
	public int hashCode() {
		return username.hashCode();
	}
	

}
