package OptimalNodeCoverage;

	import java.util.Comparator;

	public class CompareInt implements Comparator<Integer> {
		int[] freedom;
		@Override
		public int compare(Integer arg0, Integer arg1) {
			if(freedom[arg0]>freedom[arg1]){
			return 1;
			}
			if(freedom[arg0]<freedom[arg1]){
				return -1;
				}
			
			return 0;
		}

		public CompareInt(int[] freedom){
			this.freedom=freedom;
		}
	
}
