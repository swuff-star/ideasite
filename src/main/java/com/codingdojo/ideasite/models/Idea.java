package com.codingdojo.ideasite.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="ideas")

public class Idea {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@NotEmpty(message="Concept name is required!")
		@Size(min=3, max=30, message="Product name must be between 3 and 30 characters.")
		private String pName;
		
		@NotEmpty(message="Concept description is required!")
		@Size(min=3, max=255, message="Product description must be between 3 and 255 characters.")
		private String pDesc;
		
		private String pImage;
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="user_id")
		private User user;
		
	    @Lob
	    @Column(name = "image_data", columnDefinition = "BLOB")
	    private byte[] imageData;
	    
	    @OneToMany(mappedBy="idea", fetch = FetchType.LAZY)
	    private List<Comment> comments;
	    
		public Idea() { }
		
		public Idea(Long id, String name, String desc, String imageref, User user){
			this.id = id;
			this.pName = name;
			this.pDesc = desc;
			this.pImage = imageref;
			this.user = user;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getpName() {
			return pName;
		}

		public void setpName(String pName) {
			this.pName = pName;
		}

		public String getpDesc() {
			return pDesc;
		}

		public void setpDesc(String pDesc) {
			this.pDesc = pDesc;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public byte[] getImageData() {
			return imageData;
		}

		public void setImageData(byte[] imageData) {
			this.imageData = imageData;
		}

		public String getpImage() {
			return pImage;
		}

		public void setpImage(String pImage) {
			this.pImage = pImage;
		}
		
		public List<Comment> getComments() {
			return comments;
		}
		
		public void setComments(List<Comment> comments) {
			this.comments = comments;
		}
}

