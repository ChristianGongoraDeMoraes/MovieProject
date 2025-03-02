package com.MovieProject.MovieProject.Movie;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    @OneToMany(mappedBy = "movieImage")
    private final Set<Image> image = new HashSet<>();

}