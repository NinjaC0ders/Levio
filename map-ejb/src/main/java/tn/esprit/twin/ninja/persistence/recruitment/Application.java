package tn.esprit.twin.ninja.persistence.recruitment;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table

public class Application implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Temporal(TemporalType.TIMESTAMP )
	private Date date_app;
	@Enumerated(EnumType.STRING)
	private State state;
	@OneToOne
	private Folder folder;
	@ManyToMany
	private List<Test> listTest;
	@OneToMany(mappedBy="application")
	private List<Interview> listInterview;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate_app() {
		return date_app;
	}
	public void setDate_app(Date date_app) {
		this.date_app = date_app;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public Folder getFolder() {
		return folder;
	}
	public void setFolder(Folder folder) {
		this.folder = folder;
	}
	public List<Test> getListTest() {
		return listTest;
	}
	public void setListTest(List<Test> listTest) {
		this.listTest = listTest;
	}
	public List<Interview> getListInterview() {
		return listInterview;
	}
	public void setListInterview(List<Interview> listInterview) {
		this.listInterview = listInterview;
	}
	public Application(Date date_app, State state) {
		super();
		this.date_app = date_app;
		this.state = state;
	}
	public Application() {
		super();
	}
	
	
	

}