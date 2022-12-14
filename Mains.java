package jobs;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Mains {
	

	

	
		public static int findLastNonConflictingJob(List<Job> jobs, int n)
	    {
	        int low = 0, high = n;
	 
	        while (low <= high)
	        {
	            int mid = (low + high) / 2;
	            if (jobs.get(mid).finish <= jobs.get(n).start)
	            {
	                if (jobs.get(mid + 1).finish <= jobs.get(n).start) {
	                    low = mid + 1;
	                }
	                else {
	                    return mid;
	                }
	            }
	            else {
	                high = mid - 1;
	            }
	        }
	 
	        return -1;
	    }
	 
	    public static int findMaxProfit(List<Job> jobs)
	    {
	        Collections.sort(jobs, Comparator.comparingInt(x -> x.finish));
	 
	        int n = jobs.size();
	 
	        if (n == 0) {
	            return 0;
	        }
	 
	        int[] maxProfit = new int[n];
	 
	        maxProfit[0] = jobs.get(0).profit;
	 
	        for (int i = 1; i < n; i++)
	        {
	            int index = findLastNonConflictingJob(jobs, i);
	 
	            int incl = jobs.get(i).profit;
	            if (index != -1) {
	                incl += maxProfit[index];
	            }
	 
	            maxProfit[i] = Math.max(incl, maxProfit[i - 1]);
	        }
	 
	       
	        return maxProfit[n - 1];
	    }
	 
	    public static void main(String[] args)
	    {
	        List<Job> jobs = Arrays.asList(
	                new Job( 9000, 1030, 100 ),
	                new Job( 1100, 1200, 300 )
	               
	        );


	        System.out.print("The maximum profit is " + findMaxProfit(jobs));
	        
	    }
	


	   

}
