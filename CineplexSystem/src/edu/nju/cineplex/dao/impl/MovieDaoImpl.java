package edu.nju.cineplex.dao.impl;

import java.util.List;

import edu.nju.cineplex.dao.BaseDao;
import edu.nju.cineplex.dao.MovieDao;
import edu.nju.cineplex.model.Movie;

public class MovieDaoImpl implements MovieDao{

	private static MovieDaoImpl movieDaoImpl=new MovieDaoImpl();
	private MovieDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	public static MovieDao getInstance(){
		return movieDaoImpl;
	}
	private BaseDao baseDao=BaseDaoImpl.getInstance();
	
	@Override
	public boolean insert(Movie movie) {
		// TODO Auto-generated method stub
		return baseDao.save(movie);
	}
	@Override
	public List getAllMovies() {
		// TODO Auto-generated method stub
		List movies=baseDao.getAllList(Movie.class);
		return movies;
	}
	@Override
	public Movie getMovieById(int id) {
		// TODO Auto-generated method stub
		Movie movie=(Movie)baseDao.load(Movie.class, id);
		return movie;
	}
	

}
