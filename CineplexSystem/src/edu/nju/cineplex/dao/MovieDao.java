package edu.nju.cineplex.dao;

import java.util.List;

import edu.nju.cineplex.model.Movie;

public interface MovieDao {
	public boolean insert(Movie movie);
	public List getAllMovies();
	public Movie getMovieById(int id);

}
