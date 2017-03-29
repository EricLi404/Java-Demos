package rip;

import java.util.Scanner;

public class Index {
	public static void main(String[] args){
		Network network = new Network();
		network.initNetwork();
		network.setnearRouter();
		System.out.print("请输入循环轮次:");
		Scanner in = new Scanner(System.in);
		int i = in.nextInt();
		in.close();
		for(int j = 0; j < i; j++){
			System.out.println("=========================循环轮次"+(j+1)+"===========================");
			network.changeAllRouter();
		}
	}

}
