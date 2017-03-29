package rip;

import java.util.HashMap;

public class RouterTable {
	private HashMap<Integer, String[] > list;
	private int routerID;
	
	public HashMap<Integer, String[]> getList() {
		return list;
	}

	public void setList(HashMap<Integer, String[]> list) {
		this.list = list;
	}

	public int getRouterID() {
		return routerID;
	}

	public void setRouterID(int routerID) {
		this.routerID = routerID;
	}
	
	

	public RouterTable(HashMap<Integer, String[]> list, int routerID) {
		super();
		this.list = list;
		this.routerID = routerID;
	}
}
