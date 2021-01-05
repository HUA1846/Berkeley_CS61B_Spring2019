public class Flatten {

	public static int[] flatten(int[][] x) {
		int totalLength = 0;

		for(int i = 0; i < x.length; i++) {

			totalLength += x[i].length;
		}

		int[] a = new int[totalLength];
		int aIndex = 0;

		for(int i = 0; i < x.length; i++) {
			for(int item: x[i]){
				a[aIndex] = item;
				aIndex += 1;
			}
		}

		return a;
	}

	public static void main(String[] args) {

		int[][] x = {{1, 2, 3}, {4, 5, 6}, {7, 8} };

		int[] a = flatten(x);

		for(int i : a) {
			System.out.println(i);
		}
		
	}
}