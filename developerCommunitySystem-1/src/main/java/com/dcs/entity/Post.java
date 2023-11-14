package com.dcs.entity;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="postId")
	private Integer postId;
	private String query;
	private LocalDateTime postDateTime;
	private String topic;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private Developer developer;
	
	//Bidirectional Mapping
	@OneToMany(mappedBy="post") // Many posts are associated with one developer
	private List<Response> listOfResponse;
	private Integer noOfViews;
	public Post() {
		super();
	}
public Post(Integer postId, String query, LocalDateTime postDateTime, String topic, Developer developer,
		List<Response> listOfResponse, Integer noOfViews) {
	super();
	this.postId = postId;
	this.query = query;
	this.postDateTime = postDateTime;
	this.topic = topic;
	this.developer = developer;
	this.listOfResponse = listOfResponse;
	this.noOfViews = noOfViews;
}
public Integer getPostId() {
	return postId;
}
public void setPostId(Integer postId) {
	this.postId = postId;
}
public String getQuery() {
	return query;
}
public void setQuery(String query) {
	this.query = query;
}
public LocalDateTime getPostDateTime() {
	return postDateTime;
}
public void setPostDateTime(LocalDateTime postDateTime) {
	this.postDateTime = postDateTime;
}
public String getTopic() {
	return topic;
}
public void setTopic(String topic) {
	this.topic = topic;
}
public Developer getDeveloper() {
	return developer;
}
public void setDeveloper(Developer developer) {
	this.developer = developer;
}
public List<Response> getListOfResponse() {
	return listOfResponse;
}
public void setListOfResponse(List<Response> listOfResponse) {
	this.listOfResponse = listOfResponse;
}
public Integer getNoOfViews() {
	return noOfViews;
}
public void setNoOfViews(Integer noOfViews) {
	this.noOfViews = noOfViews;
}
@Override
public String toString() {
	return "Post [postId=" + postId + ", query=" + query + ", postDateTime=" + postDateTime + ", topic=" + topic
			+ ", developer=" + developer + ", listOfResponse=" + listOfResponse + ", noOfViews=" + noOfViews + "]";
}

	
}