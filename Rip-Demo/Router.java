package rip;

import java.util.HashMap;

public class Router {
	private  final int routerId;
	private RouterTable routerTable;
	private int[] nearRouter;
	private int[] nearNetwork;



	public RouterTable getRouterTable() {
		return routerTable;
	}
	public void setRouterTable(RouterTable routerTable) {
		this.routerTable = routerTable;
	}
	public void changeRouterTable(RouterTable otherRouterTable){
		// 规则：
				// 1.  传入的为其临近路由器的路由表
				// 2. 解析路由表
				// 3. 如果有自己路由表里有的网络，检查跳数是否为15，不为15进行以下操作
				// 4. 如果“跳数+1” 小于 本路由表对应条目的跳数，则修改记录
				// 5. 修改记录 “网络号(不变)”，“跳数+1”,“下一跳路由(该路由条目的来源路由)” 
				// 6. else如果有本自己路由表里没有的网路，检查跳数是否为15，不为15进行以下操作
				// 7. 添加记录 “网络号”，“跳数+1”,“下一跳路由(该路由条目的来源路由)”
		
		
		//将自己的路由表里 所有的网络号建立一个String，所有的跳数建立一个String，
		//遍历外来路由表的各个网络号，判断是否存在相同的值
		//如果存在 判断跳数（1.是否不等于15，2，是否小于自己对应的跳数+1）
		//          如果满足，修改路由表对应数据
		//			 如果不满足。do nothing
		//如果不存在，判断跳数是否不等于15，
		//            如果满足，添加该路由条目
				
			HashMap<Integer, String[] > otherList = otherRouterTable.getList();
			HashMap<Integer, String[] > selfList = routerTable.getList();
			String otherNetnum = "";
			String otherTiaonum = "";
			String selfNetnum = "";
			String selfTiaonum = "";
			String selfShouldMod = "";
			String otherShouldMod = "";
			String shouldAdd = "";
			for(int i = 0 ; i < otherList.size(); i++){
				otherNetnum += otherList.get(i)[0];
				otherTiaonum += otherList.get(i)[1];
			}
			for(int i = 0; i < selfList.size(); i++){
				selfNetnum += selfList.get(i)[0];
				selfTiaonum += selfList.get(i)[1];
			}
			for(int i = 0; i < otherNetnum.length(); i++){
//				System.out.println("第"+i+"循环检验========================");
				int res = selfNetnum.indexOf(otherNetnum.substring(i,i+1));
				int p = Integer.parseInt(otherTiaonum.substring(i, i+1));
				if (res != -1) {
					int q = Integer.parseInt(selfTiaonum.substring(res, res+1));
					if (p < 15) {
						if ((p+1) < q ) {
							//TODO 修改路由表对应数据
//							System.out.println("premod======="+selfNetnum.substring(res, res+1)+"--------"+otherNetnum.substring(i,i+1));
							selfShouldMod += String.valueOf(res);
							otherShouldMod += String.valueOf(i);
						}
					}
				}else if (res == -1) {
					if (p < 15) {
						//TODO 添加该条目
//						System.out.println("preadd====="+otherNetnum.substring(i,i+1));
						shouldAdd += String.valueOf(i);
					}
				}else {
					System.err.println("core change err");
				}
			}
			if (selfShouldMod.length() > 0) {
				for(int i = 0; i < selfShouldMod.length(); i++){
//					System.out.println("mod");
					selfList.remove(selfShouldMod.substring(i,i+1));
					String newChange[] = {
								otherList.get(Integer.parseInt(otherShouldMod.substring(i, i+1)))[0],
								String.valueOf(Integer.parseInt(otherList.get(Integer.parseInt(otherShouldMod.substring(i,i+1)))[1])+1),
								String.valueOf(otherRouterTable.getRouterID())
							}; 
					selfList.put(Integer.parseInt(selfShouldMod.substring(i,i+1)), newChange);
				}
			}
			if (shouldAdd.length() > 0) {
//				System.out.println("1111111111111self.size================="+selfList.size());
				int len = selfList.size();
				for(int i = 0; i < shouldAdd.length(); i++){
//					System.out.println("add");
					String newChange[] = {
							otherList.get(Integer.parseInt(shouldAdd.substring(i, i+1)))[0],
							String.valueOf(Integer.parseInt(otherList.get(Integer.parseInt(shouldAdd.substring(i,i+1)))[1])+1),
							String.valueOf(otherRouterTable.getRouterID())
						}; 
					selfList.put(len+i, newChange);
//					System.out.println("self.size================="+selfList.size());
				}
			}
			routerTable.setList(selfList);
			setRouterTable(routerTable);
	}

	public int[] getNearRouter() {
		return nearRouter;
	}
	
	

	public void setNearRouter(int[] nearRouter) {
		this.nearRouter = nearRouter;
	}

	public int[] getNearNetwork() {
		return nearNetwork;
	}

	public void setNearNetwork(int[] nearNetwork) {
		this.nearNetwork = nearNetwork;
	}

	public int getRouterId() {
		return routerId;
	}
	
	public void echoRoutertable(){
		RouterTable rtTables = getRouterTable(); 
		HashMap<Integer, String[]> list = rtTables.getList();
		System.out.println("*******路由器 "+getRouterTable().getRouterID()+" 路由表******");
		for (int i = 0; i < list.size(); i++) {
			String[] pStrings = list.get(i);
			System.out.println("网络："+pStrings[0]+"  |   "+"跳数："+pStrings[1]+"   |   "+"下一跳路由器： "+pStrings[2]);
		}
	}


	public Router(int routerId, RouterTable routerTable) {
		super();
		this.routerId = routerId;
		this.routerTable = routerTable;
		//TODO 记录临近的网络
		int[] p = new int[routerTable.getList().size()];
		for(int i = 0; i < routerTable.getList().size(); i++){
			p[i] = Integer.parseInt(routerTable.getList().get(i)[0]);
		}
		this.nearNetwork = p;
	}
	
	

}
