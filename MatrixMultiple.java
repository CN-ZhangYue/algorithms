import java.util.ArrayList;

public class MatrixMultiple {
	public static final int n = 3;//define the number of matrices
	public static ArrayList<Pair> matrixs = new ArrayList<Pair>();//stored the matrixs
	public static int sum = 0;
	/*keep track of the order in which you multiply in the function devideConquer*/
	public static int[][] path = new int[n][n];
	public static int[][] result = new int[n][n]; 
	public static final int infty = Integer.MAX_VALUE;

	public static void main(String[] args) {
		init();
		devideConquer();
		System.out.println("the least number of multiplications:");
		System.out.println("devideConquer:"+ result[0][n - 1]);
		greedy();
	}

	/**
	 * initial the data
	 * automatically generate a certain number of matrices
	 * 
	 * @author ZhangYue
	 */
	static void init() {
		sum = 0;
		int x = 0;
		ArrayList<Integer> nums = new ArrayList<Integer>();
		for (int i = 0; i < n + 1; i++) {
			x = (int) (Math.random() * 13 + 3);
			nums.add(x);
		}
		for (int i = 0; i < n; i++) {
			Pair temPair = new Pair(nums.get(i), nums.get(i + 1));
			matrixs.add(temPair);
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				path[i][j] = 0;
				result[i][j] = infty;
			}
		}
	}

	/**
	 * devideAndConquer
	 * 
	 * @author ZhangYue
	 */
	static void devideConquer() {
		sum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				path[i][j] = 0;
				result[i][j] = infty;
				if (i == j) {
					result[i][j] = 0;
				}
			}
		}
		for (int l = 1; l < n; l++ ){
			for (int i = 0; i <= n - l - 1; i++) {
				int j = i + l;
				result[i][j] = infty;
				for (int k = i; k < j; k++) {
					int temp = (matrixs.get(i).getX()) * (matrixs.get(k).getY()) * (matrixs.get(j).getY());
					int num = result[i][k] + result[k + 1][j] + temp;
					if (num < result[i][j]) {
						result[i][j] = num;
						path[i][j] = k;
					}
				}
			}
		}
	}
	
	/**
	 * greedy algorithm
	 * the least number of multiplications
	 *
	 * @author ZhangYue
	 */
	static void greedy() {
		sum = 0;
		ArrayList<Pair> tempPairs = matrixs;
		int nn = tempPairs.size();
		int min = infty;
		int tempNum = 0;
		int index = 0;
		
		while(nn > 1) {
			min = infty;
			index = 0;
			for(int i = 0; i< nn-1; i++) {
				tempNum = tempPairs.get(i).getX()*tempPairs.get(i).getY()*tempPairs.get(i+1).getY();
				if(min > tempNum) {
					min = tempNum;
					index = i;
				}
			}
			Pair pair = new Pair(tempPairs.get(index).getX(), tempPairs.get(index+1).getY());
			tempPairs.remove(index+1);
			tempPairs.remove(index);
			tempPairs.add(index, pair);
			nn = tempPairs.size();
			sum += min;
		}
		System.out.println("greedy:"+sum);
	}
}

class Pair {
	private int x;
	private int y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}