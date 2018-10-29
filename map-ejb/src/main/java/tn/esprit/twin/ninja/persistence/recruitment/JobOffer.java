package tn.esprit.twin.ninja.persistence.recruitment;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import tn.esprit.twin.ninja.persistence.Skill;
@Entity
@Table
public class JobOffer implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String mission;
	private String required_profile;
	private Date beginning;
	private String experience;
	private String function;
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Skill> listSkills;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMission() {
		return mission;
	}
	public void setMission(String mission) {
		this.mission = mission;
	}
	public String getRequired_profile() {
		return required_profile;
	}
	public void setRequired_profile(String required_profile) {
		this.required_profile = required_profile;
	}
	public Date getBeginning() {
		return beginning;
	}
	public void setBeginning(Date beginning) {
		this.beginning = beginning;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	public List<Skill> getListSkills() {
		return listSkills;
	}
	public void setListSkills(List<Skill> listSkills) {
		this.listSkills = listSkills;
	}
	public JobOffer() {
		super();
	}
	

}
