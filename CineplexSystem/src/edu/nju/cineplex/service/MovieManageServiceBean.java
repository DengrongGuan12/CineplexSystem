package edu.nju.cineplex.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.nju.cineplex.action.business.MoviePlanBean;
import edu.nju.cineplex.action.business.SimpleMovieBean;
import edu.nju.cineplex.dao.MovieDao;
import edu.nju.cineplex.dao.PlanDao;
import edu.nju.cineplex.factory.DaoFactory;
import edu.nju.cineplex.model.Movie;
import edu.nju.cineplex.model.Plan;

public class MovieManageServiceBean implements MovieManageService{

	private MovieDao movieDao=DaoFactory.getMovieDao();
	private PlanDao planDao=DaoFactory.getPlanDao();
	private static MovieManageServiceBean movieManageServiceBean=new MovieManageServiceBean();
	private MovieManageServiceBean() {
		// TODO Auto-generated constructor stub
	}
	public static MovieManageServiceBean getInstance(){
		return movieManageServiceBean;
	}
	@Override
	public boolean addMovie(String title, String description, String endTime) {
		// TODO Auto-generated method stub
		Movie movie=new Movie();
		movie.setDescription(description);
		movie.setName(title);
		endTime+=" 00:00:00";
		Timestamp ts=Timestamp.valueOf(endTime);
		movie.setEndTime(ts);
		return movieDao.insert(movie);
		
	}
	@Override
	public ArrayList<MoviePlanBean> getMoviePlanBeans() {
		// TODO Auto-generated method stub
		List<Movie> movies=movieDao.getAllMovies();
		ArrayList<MoviePlanBean> moviePlanBeans=new ArrayList<MoviePlanBean>();
		for (Movie movie:movies) {
			int id=movie.getId();
			List<Plan> plans=planDao.getPlansByMovieId(id);
			MoviePlanBean moviePlanBean=new MoviePlanBean();
			moviePlanBean.setMovie(movie);
			moviePlanBean.setPlans(plans);
			moviePlanBean.setHasEnd();
			moviePlanBeans.add(moviePlanBean);
		}
		return moviePlanBeans;
	}
	
	public static boolean isOffLine(Timestamp offLineTime){
		boolean offLine=false;
		Date date=new Date();
		if(offLineTime.compareTo(date)<0){
			offLine=true;
		}
		return offLine;
	}
	@Override
	public ArrayList<SimpleMovieBean> getSimpleMovieBeans() {
		// TODO Auto-generated method stub
		ArrayList<SimpleMovieBean> simpleMovieBeans=new ArrayList<SimpleMovieBean>();
		List<Movie> movies=movieDao.getAllMovies();
		for(Movie movie:movies){
			SimpleMovieBean simpleMovieBean=new SimpleMovieBean();
			simpleMovieBean.setId(movie.getId()+"");
			simpleMovieBean.setName(movie.getName());
			if(isOffLine(movie.getEndTime())){
				simpleMovieBean.setOffLine("已下线");
			}else{
				simpleMovieBean.setOffLine("未下线");
			}
			simpleMovieBeans.add(simpleMovieBean);
			
		}
		return simpleMovieBeans;
	}
	@Override
	public MoviePlanBean getMoviePlanBean(String movieId) {
		// TODO Auto-generated method stub
		MoviePlanBean moviePlanBean=new MoviePlanBean();
		int id=Integer.parseInt(movieId);
		Movie movie=movieDao.getMovieById(id);
		List<Plan> plans=planDao.getPlansByMovieId(id);
		moviePlanBean.setMovie(movie);
		moviePlanBean.setPlans(plans);
		moviePlanBean.setHasEnd();
		return moviePlanBean;
	}
	
	

}
