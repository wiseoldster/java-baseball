import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class NumberBaseball {
	
	static void playGame(BufferedReader br) {
		int[] defense = defenseArr();
		int[] attack;
		int[] result = new int[] {0,0};
		while(result[0] != 3) {
			attack = attackArr(br);
			result = attackResult(defense, attack);
		}
		System.out.println("\n3���� ���ڸ� ��� �����̽��ϴ�! ��������");
	}
	
	private static int[] defenseArr() {
		Set<Integer> set = new LinkedHashSet<>();
		int[] arr = new int[] {0,0,0};
		while(set.size()<3) {
			set.add((int)(Math.random()*9)+1);
		}
		Iterator<Integer> it = set.iterator();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = it.next();
		}
		return arr;
	}
	
	private static int[] attackArr(BufferedReader br) {
		int[] ret = new int[] {0,0,0};
		int tmp2 = 0;
		String tmp =null;
		while(true) {
			System.out.print("���ڸ� �Է����ּ��� : ");
			try {
				tmp = br.readLine().trim();
			} catch (IOException e) {}
			
			if(tmp.length()!=3) {
				System.out.println("�߸��� �Է��Դϴ�.");
				continue;
			}
			
			try {
				tmp2 = Integer.parseInt(tmp);
			} catch (NumberFormatException e) {
				System.out.println("�߸��� �Է��Դϴ�.");
				continue;
			}
			break;
		}
		for (int i = 2; i >= 0; i--) {
			ret[i] = tmp2 % 10;
			tmp2 /= 10;
		}
		return ret;
	}
	
	private static boolean isHitable(int[] defense, int attack) {
		for (int i = 0; i < defense.length; i++) {
			if(defense[i] == attack) return true;
		}
		return false;
	}
	
	private static int countStrike(int[] defense, int[] attack) {
		int counter = 0;
		for (int i = 0; i < 3; i++) {
			if(defense[i] == attack[i]) counter++;
		}
		return counter;
	}
	
	private static int[] attackResult(int[] defense, int[] attack) { // {# Strike, # Ball} �� ���� 
		int strike = countStrike(defense, attack);
		int hitable = 0;
		for (int i = 0; i < 3; i++) {
			if(isHitable(defense, attack[i])) hitable++;
		}
		if(strike != 0) 	System.out.printf("%d ��Ʈ����ũ",strike);
		if(hitable != strike) System.out.printf("%d ��\n",hitable-strike);
		if(hitable == 0) System.out.println("����");
		return new int[] {strike, hitable-strike};
	}
}
