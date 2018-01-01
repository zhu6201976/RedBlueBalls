/**
һ��˫ɫ���Ʊ�淨
�淨˵����
˫ɫ��Ͷע����Ϊ����������������������������뷶ΧΪ01��33��
������뷶ΧΪ01��16��˫ɫ��ÿ�ڴ�33�������п���6�����룬
��16�������п���1��������Ϊ�н����룬
˫ɫ���淨���Ǿ��¿��������6����������1��������룬˳���ޡ�

��������������
1����β�������ͺ���
2����ν����û�ѡ�ţ�
3�������֤�Ƿ��н���
4�����������н����룿

����ʵ�ֲ��裺
1������ʵ��˼·
2�����ȡֵ���ظ��㷨��ϵͳ���û���
3���ж��Ƿ��н����߼�
4��������
*/

import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
public class Test{
	public static void main(String[] args){
		
		// 1.������ر���
		int[] redBalls = new int[33];// ����أ�1-33
		int[] sysRedBalls = new int[6];// ϵͳ��������ĺ��򼯺�6��
		int sysBlueBall = 0;// ϵͳ�������������1��(1-16)
		int[] userRedBalls = new int[6];// �û�ѡ��ĺ��򼯺�6��
		int userBlueBall = 0;// �û�ѡ�������1��
		int redBallsCount = 0;// ��ȷ�ĺ�����
		int blueBallCount = 0;// ��ȷ��������
		Random random = new Random();
		Scanner input = new Scanner(System.in);
		
		// 2.������ؼ���Ԫ��
		for(int i=0;i<redBalls.length;i++){
			redBalls[i] = i+1;
		}
		
		// 3.�������ϵͳ���򼯺Ϻ��������
		computerSelection(redBalls,sysRedBalls);
		sysBlueBall = random.nextInt(16)+1;
		
		// 4.ѯ���û��ǻ�ѡ������ѡ����
		System.out.println("��Ϸ��ʼ��ף����ˣ�");
		System.out.println("�������ǻ�ѡ������ѡ��1.��ѡ2.��ѡ");
		int isAuto = input.nextInt();
		boolean flag = true;
		while(flag){
			switch(isAuto){
				case 1:
					// ��ѡ
					computerSelection(redBalls,userRedBalls);
					userBlueBall = random.nextInt(16)+1;
					flag = false;
					break;
				case 2:
					// ��ѡ
					System.out.println("������6��������룺");
					for(int i=0;i<userRedBalls.length;i++){
						userRedBalls[i] = input.nextInt();
					}
					System.out.println("������1��������룺");
					userBlueBall = input.nextInt();
					flag = false;
					break;
				default:
					flag = true;
					break;
			}
		}
		
		// ͳ�ƺ���������н�����
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
		// ���������
		sort(sysRedBalls);
		sort(userRedBalls);
		System.out.println("�����н��������Ϊ��"+Arrays.toString(sysRedBalls));
		System.out.println("�����н��������Ϊ��"+sysBlueBall);
		System.out.println("��ѡ��ĺ������Ϊ��"+Arrays.toString(userRedBalls));
		System.out.println("��ѡ����������Ϊ��"+userBlueBall);
		if(blueBallCount == 0 && redBallsCount <= 3){
			System.out.println("���ź���ɶҲû�̵���");
		}else if(blueBallCount == 1 && redBallsCount < 3){
			System.out.println("��ϲ���������Ƚ���5��Ǯ��");
		}else if(blueBallCount == 1 && redBallsCount == 3||blueBallCount == 0 && redBallsCount == 4){
			System.out.println("��ϲ��������Ƚ���10��Ǯ��");
		}else if(blueBallCount == 1 && redBallsCount == 4||blueBallCount == 0 && redBallsCount == 5){
			System.out.println("��ϲ�������ĵȽ���200��Ǯ��");
		}else if(blueBallCount == 1 && redBallsCount == 5){
			System.out.println("��ϲ���������Ƚ���3000��Ǯ��");
		}else if(blueBallCount == 0 && redBallsCount == 6){
			System.out.println("��ϲ�����˶��Ƚ���150w��");
		}else if(blueBallCount == 1 && redBallsCount == 6){
			System.out.println("��ϲ������һ�Ƚ���500w��");
		}else{
			System.out.println("ϵͳ�����н���Ч��");
		}
		
	}
	// �Ӻ�������������6�����ظ��ĺ������
	public static void computerSelection(int[] redBalls,int[] balls){
		Random random = new Random();
		int index = 0;
		int temp = 0;
		for(int i=0;i<balls.length;i++){
			// �������1���±�
			index = random.nextInt(redBalls.length-i);
			// ���±��ֵ����������
			balls[i] = redBalls[index];
			// ���±��ϵ�����Ҫ���������һ�������н���
			temp = redBalls[index];
			redBalls[index] = redBalls[redBalls.length-1-i];
			redBalls[redBalls.length-1-i] = temp;
		}
	}
	
	// ʹ��ð�ݷ���С�������������
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




