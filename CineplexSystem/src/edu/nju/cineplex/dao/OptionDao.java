package edu.nju.cineplex.dao;

import java.util.List;

import edu.nju.cineplex.model.Option;

public interface OptionDao {
	public boolean insert(Option option);
	public Option getOptionById(int id);
	public List<Option> getOptionsByActivityId(int activityId);

}
