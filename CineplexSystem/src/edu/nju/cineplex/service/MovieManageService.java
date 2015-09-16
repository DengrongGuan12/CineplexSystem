package edu.nju.cineplex.service;

import java.util.ArrayList;

import edu.nju.cineplex.action.business.MoviePlanBean;
import edu.nju.cineplex.action.business.SimpleMovieBean;


public interface MovieManageService {
	public boolean addMovie(String title,String description,String endTime);
	public ArrayList<MoviePlanBean> getMoviePlanBeans();
	public ArrayList<SimpleMovieBean> getSimpleMovieBeans();
	public MoviePlanBean getMoviePlanBean(String movieId);

}
