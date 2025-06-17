package com.MovieProject.MovieProject.Movie;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.swing.text.View;

import com.MovieProject.MovieProject.Comments.Comment;
import com.MovieProject.MovieProject.Views.Views;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "movies_tb")
public class Movie implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = false, nullable = false)
    private String rate;

    @Column(unique = false, nullable = false)
    private String description;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "movieImage", cascade = CascadeType.ALL)
    private final Set<Image> image = new HashSet<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "movieComment", cascade = CascadeType.ALL)
    private final Set<Comment> comment = new HashSet<>();

    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "movieViews", cascade = CascadeType.ALL)
    private final Set<Views> views = new HashSet<>();

}