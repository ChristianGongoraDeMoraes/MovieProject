package com.MovieProject.MovieProject.Movie.RepReqUtils.Requests;

import jakarta.validation.constraints.NotBlank;

public record MovieSaveRequest(
                                @NotBlank
                                String name,
                                @NotBlank
                                String rate,
                                @NotBlank
                                String description
) {

}
