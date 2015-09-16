package edu.nju.cineplex.action.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class PlansOfDaysBean {
	private HashSet<String> movieIdSet=new HashSet<String>();
	public HashSet<String> getMovieIdSet() {
		return movieIdSet;
	}

	public void setMovieIdSet(HashSet<String> movieIdSet) {
		this.movieIdSet = movieIdSet;
	}
	private Hashtable<String, ArrayList<SimplePlanBean>> day_simplePlanBeans=new Hashtable<String, ArrayList<SimplePlanBean>>();
	public Hashtable<String, ArrayList<SimplePlanBean>> getDay_simplePlanBeans() {
		return day_simplePlanBeans;
	}

	public void setDay_simplePlanBeans(
			Hashtable<String, ArrayList<SimplePlanBean>> day_simplePlanBeans) {
		this.day_simplePlanBeans = day_simplePlanBeans;
	}
	public static Map.Entry[] getSortedHashtableByKey(Hashtable h) {

	    Set set = h.entrySet();

	    Map.Entry[] entries = (Map.Entry[]) set.toArray(new Map.Entry[set.size()]);

	    Arrays.sort(entries, new Comparator() {
	      public int compare(Object arg0, Object arg1) {
	        Object key1 = ((Map.Entry) arg0).getKey();
	        Object key2 = ((Map.Entry) arg1).getKey();
	        return ((Comparable) key2).compareTo(key1);
	      }

	    });

	    return entries;
	  }

	

}
