package edu.nju.cineplex.action.business;

import java.util.ArrayList;
import java.util.HashSet;

public class SimplePlanBeans {
	private ArrayList<SimplePlanBean> simplePlanBeans=new ArrayList<SimplePlanBean>();
	public ArrayList<SimplePlanBean> getSimplePlanBeans() {
		return simplePlanBeans;
	}
	public void setSimplePlanBeans(ArrayList<SimplePlanBean> simplePlanBeans) {
		this.simplePlanBeans = simplePlanBeans;
	}
	private HashSet<String> movieIdSet=new HashSet<String>();
	public HashSet<String> getMovieIdSet() {
		return movieIdSet;
	}
	public void setMovieIdSet(HashSet<String> movieIdSet) {
		this.movieIdSet = movieIdSet;
	}

}
