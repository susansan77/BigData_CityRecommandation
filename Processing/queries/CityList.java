package queries;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author zishanqin
 *
 */
public class CityList {
	List<CityWithScore> cityList;

	public CityList(List<CityWithScore> cs) {
		cityList = cs;

	}
	public CityList(String str){
		cityList=new ArrayList<CityWithScore>();
		String[] cities=str.split(":");
		for(int i=0;i<cities.length;i++){
			String[] strs=cities[i].split("/");
			CityWithScore c=new CityWithScore(strs[0],strs[1]);
			this.cityList.add(c);
		}
		
	}
	@Override
	public String toString(){
		String str="";
		for(CityWithScore city: cityList){
			str=str+city.toString()+":";
		}
		return str;
	}
	public List<CityWithScore> getCityList(){
		return this.cityList;
	}
}
