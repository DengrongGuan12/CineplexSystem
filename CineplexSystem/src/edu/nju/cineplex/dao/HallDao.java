package edu.nju.cineplex.dao;

import java.util.List;

import edu.nju.cineplex.model.Hall;


public interface HallDao {
	public List<Hall> getAllHalls();
	public Hall getHallById(int id);

}
