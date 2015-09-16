package edu.nju.cineplex.dao;

import java.util.List;

import edu.nju.cineplex.model.Choice;

public interface ChoiceDao {
	public List<Choice> getChoicesByMemberId(int memberId);
	public boolean insert(Choice choice);
	public List<Choice> getChoicesByOptionId(int optionId);
	public void update(Choice choice);
}
