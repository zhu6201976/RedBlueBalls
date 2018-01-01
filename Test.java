/**
一、双色球彩票玩法
玩法说明：
双色球投注区分为红球号码区和蓝球号码区，红球号码范围为01～33，
蓝球号码范围为01～16。双色球每期从33个红球中开出6个号码，
从16个蓝球中开出1个号码作为中奖号码，
双色球玩法即是竞猜开奖号码的6个红球号码和1个蓝球号码，顺序不限。

二、案例分析：
1、如何产生蓝球和红球？
2、如何接收用户选号？
3、如何验证是否中奖？
4、公布本期中奖号码？

三、实现步骤：
1、整体实现思路
2、随机取值不重复算法（系统和用户）
3、判断是否中奖的逻辑
4、结果输出
*/

import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
public class Test{
	public static void main(String[] args){
		
		// 1.定义相关变量
		int[] redBalls = new int[33];// 红球池，1-33
		int[] sysRedBalls = new int[6];// 系统随机产生的红球集合6个
		int sysBlueBall = 0;// 系统随机产生的篮球1个(1-16)
		int[] userRedBalls = new int[6];// 用户选择的红球集合6个
		int userBlueBall = 0;// 用户选择的篮球1个
		int redBallsCount = 0;// 正确的红球数
		int blueBallCount = 0;// 正确的篮球数
		Random random = new Random();
		Scanner input = new Scanner(System.in);
		
		// 2.填充红球池集合元素
		for(int i=0;i<redBalls.length;i++){
			redBalls[i] = i+1;
		}
		
		// 3.随机生成系统红球集合和篮球号码
		computerSelection(redBalls,sysRedBalls);
		sysBlueBall = random.nextInt(16)+1;
		
		// 4.询问用户是机选还是手选号码
		System.out.println("游戏开始，祝你好运！");
		System.out.println("请问您是机选还是手选？1.机选2.手选");
		int isAuto = input.nextInt();
		boolean flag = true;
		while(flag){
			switch(isAuto){
				case 1:
					// 机选
					computerSelection(redBalls,userRedBalls);
					userBlueBall = random.nextInt(16)+1;
					flag = false;
					break;
				case 2:
					// 手选
					System.out.println("请输入6个红球号码：");
					for(int i=0;i<userRedBalls.length;i++){
						userRedBalls[i] = input.nextInt();
					}
					System.out.println("请输入1个蓝球号码：");
					userBlueBall = input.nextInt();
					flag = false;
					break;
				default:
					flag = true;
					break;
			}
		}
		
		// 统计红球和篮球中奖个数
		for(int i=0;i<userRedBalls.length;i++){
			for(int j=0;j<sysRedBalls.length-redBallsCount;j++){
				if(userRedBalls[i] == sysRedBalls[j]){
					int temp = sysRedBalls[j];
					sysRedBalls[j] = sysRedBalls[sysRedBalls.length-1-redBallsCount];
					sysRedBalls[sysRedBalls.length-1-redBallsCount] = temp;
					redBallsCount++;
					break;
				}
			}
		}
		if(userBlueBall == sysBlueBall){
			blueBallCount++;
		}
		// 公布结果：
		sort(sysRedBalls);
		sort(userRedBalls);
		System.out.println("本期中奖红球号码为："+Arrays.toString(sysRedBalls));
		System.out.println("本期中奖蓝球号码为："+sysBlueBall);
		System.out.println("您选择的红球号码为："+Arrays.toString(userRedBalls));
		System.out.println("您选择的蓝球号码为："+userBlueBall);
		if(blueBallCount == 0 && redBallsCount <= 3){
			System.out.println("很遗憾，啥也没捞到！");
		}else if(blueBallCount == 1 && redBallsCount < 3){
			System.out.println("恭喜您中了六等奖，5块钱！");
		}else if(blueBallCount == 1 && redBallsCount == 3||blueBallCount == 0 && redBallsCount == 4){
			System.out.println("恭喜您中了五等奖，10块钱！");
		}else if(blueBallCount == 1 && redBallsCount == 4||blueBallCount == 0 && redBallsCount == 5){
			System.out.println("恭喜您中了四等奖，200块钱！");
		}else if(blueBallCount == 1 && redBallsCount == 5){
			System.out.println("恭喜您中了三等奖，3000块钱！");
		}else if(blueBallCount == 0 && redBallsCount == 6){
			System.out.println("恭喜您中了二等奖，150w！");
		}else if(blueBallCount == 1 && redBallsCount == 6){
			System.out.println("恭喜您中了一等奖，500w！");
		}else{
			System.out.println("系统出错，中奖无效！");
		}
		
	}
	// 从红球池内随机生成6个不重复的红球号码
	public static void computerSelection(int[] redBalls,int[] balls){
		Random random = new Random();
		int index = 0;
		int temp = 0;
		for(int i=0;i<balls.length;i++){
			// 随机产生1个下标
			index = random.nextInt(redBalls.length-i);
			// 该下标的值给到新数组
			balls[i] = redBalls[index];
			// 该下标上的数需要跟数组最后一个数进行交换
			temp = redBalls[index];
			redBalls[index] = redBalls[redBalls.length-1-i];
			redBalls[redBalls.length-1-i] = temp;
		}
	}
	
	// 使用冒泡法从小到大排序此数组
	public static void sort(int[] numbers){
		for(int i=0;i<numbers.length-1;i++){
			for(int j=0;j<numbers.length-1-i;j++){
				if(numbers[j]>numbers[j+1]){
					numbers[j] = numbers[j]+numbers[j+1];
					numbers[j+1] = numbers[j]-numbers[j+1];
					numbers[j] = numbers[j]-numbers[j+1];
				}
			}
		}
	}
		
}




