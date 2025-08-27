package taskManager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",columnDefinition = "serial", nullable = false)
    private Long id;
    @Column(nullable=false)
    
    private String nome;
    @Column(nullable = false)
    private String cognome;

    // Getters and setters
    public User() {
    	
    }
    public User(String nome,String cognome) {
    	this.nome=nome;
    	this.cognome=cognome;
    }
    public Long getId(){ 
    	return id;
	}
    public void setId(Long id) { 
    	this.id = id; 
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

    
}

