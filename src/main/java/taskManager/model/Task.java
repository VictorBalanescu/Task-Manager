package taskManager.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false,length = 100)
	private String name;
	@Column
	private String description;
	@Column(nullable=false)
	private boolean completed;
	@Column(updatable = false,nullable=false)
	private LocalDateTime cretionTimeStamp;
	@Column(nullable=false)
	private LocalDateTime lastUpdateTimeStamp;
	@OneToMany()
	@JoinColumn(name="task_id")
	private List<Tag> tags;
	
	public Task() {
		
	}
	public Task(String name,String description,boolean completed) {
		this.name=name;
		this.description=description;
		this.completed=completed;
		this.tags=new ArrayList<>();
	}
	
	@PrePersist
	protected void onPersist()
	{
		this.cretionTimeStamp=LocalDateTime.now();
		this.lastUpdateTimeStamp=LocalDateTime.now();
	}
	@PreUpdate
	protected void onUpdate() {
		this.lastUpdateTimeStamp=LocalDateTime.now();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public LocalDateTime getCretionTimeStamp() {
		return cretionTimeStamp;
	}
	public void setCretionTimeStamp(LocalDateTime cretionTimeStamp) {
		this.cretionTimeStamp = cretionTimeStamp;
	}
	public LocalDateTime getLastUpdateTimeStamp() {
		return lastUpdateTimeStamp;
	}
	public void setLastUpdateTimeStamp(LocalDateTime lastUpdateTimeStamp) {
		this.lastUpdateTimeStamp = lastUpdateTimeStamp;
	}
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	
}
