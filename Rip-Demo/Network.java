package rip;

import java.util.HashMap;

public class Network {
	private  int   routernum;
	private  int   networknum;
	public Router[] routers;
	
	
	
	public int getRouternum() {
		return routernum;
	}

	public void setRouternum(int routernum) {
		this.routernum = routernum;
	}

	public int getNetworknum() {
		return networknum;
	}

	public void setNetworknum(int networknum) {
		this.networknum = networknum;
	}

	public Router[] getRouters() {
		return routers;
	}

	public void setRouters(Router[] routers) {
		this.routers = routers;
	}

	public Network() {
		super();
	}
	
	public void initNetwork(){
		HashMap<String, String[]> netconf = Util.readNetworkConfig();
		setRouternum(Integer.parseInt(netconf.get("routernum")[0]));
		setNetworknum(Integer.parseInt(netconf.get("networknum")[0]));
		//TODO 建立各路由器以及路由表       !important
		Router routerssss[] = new Router[routernum];
		for (int i = 0; i < routernum; i++) {
			HashMap<Integer, String[]> list = new HashMap<>();
			String[] strings = netconf.get(String.valueOf(i));
			for(int j = 0; j < strings.length;j++){
				String[] temp = {strings[j],"1", String.valueOf(i)};
				list.put(j, temp);
			}
			RouterTable routerTable = new RouterTable(list, i);
			Router router = new Router(i, routerTable);
			routerssss[i] = router;
		}
		setRouters(routerssss);
	}
	
	public void setnearRouter(){
		for (int i = 0; i < routers.length; i++) {
			String str1 = Util.intArray2string(routers[i].getNearNetwork());
			String str3 = "";
			for(int j = 0; j < routers.length; j++){
				String str2 = Util.intArray2string(routers[j].getNearNetwork());
				for(int k = 0; k < str2.length(); k++){
					if (str1.contains(str2.substring(k, k+1))) {
						str3 += j;
						break;
					}
				}
			}
			if (str3.length() > 0) {
				int p[] = new int[str3.length()];
				for (int j = 0; j < str3.length(); j++) {
					p[j] = Integer.parseInt(str3.substring(j, j+1));
				}
				routers[i].setNearRouter(p);
			}
		}
	}
	
	public void changeAllRouter(){
		//TODO for 循环，每个路由器获取其临近路由器的路由表，然后更新自己的路由表
		for(int i = 0; i < routers.length; i++){
//			System.out.println("外循环********************路由器"+i+"开始获取路由表");
			for(int j = 0; j < routers[i].getNearRouter().length; j++){
//				System.out.println("内循环********************路由器了获取路由表"+j);
				routers[i].changeRouterTable(routers[routers[i].getNearRouter()[j]].getRouterTable());
			}
			routers[i].echoRoutertable();
		}
	}

}
