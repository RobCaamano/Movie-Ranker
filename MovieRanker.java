import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Collections;

/* Starter code for PS8.
 */

public class MovieRanker {

	public static void main(String[] args) {
        File file = new File("D:/My Programs/Java/CS114/PS/PS8/ratings.tsv");

		ArrayList<MovieRating> rl = new ArrayList<MovieRating>();

		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
                String[] tkns = line.split("\\t"); // tabs separate tokens
                MovieRating nr = new MovieRating(tkns[0], tkns[1], tkns[2]);
				rl.add(nr);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int minVotes = 1;
		int numRecords = 1;
		Scanner input = new Scanner(System.in);
		while (true) {
			System.out.println();
			System.out.println("Enter minimum vote threshold and number of records:");
			minVotes = input.nextInt();
			numRecords = input.nextInt();
			if (minVotes * numRecords == 0)
				break;
			long startTime = System.currentTimeMillis();

/* Fill in code to determine the top numRecords movies that have at least
 * minVotes votes. For each record mr, in decreasing order of average rating,
 * execute a System.out.println(mr).
 * Do not sort the movie list!
 */

		MaxHeap hop = new MaxHeap(rl.toArray(new MovieRating[rl.size()]));
         
        
        //System.out.println(hop.heapsize());
        //System.out.println(hop.removemax()); 
         
         for(int i = 0; !hop.isEmpty() && i < numRecords;){
            MovieRating rating = (MovieRating)hop.removemax();
               if (rating.getVotes() > minVotes) {
                  System.out.println(rating);
                  i++;
               }
         }


            System.out.println();
			long readTime = System.currentTimeMillis();
			System.out.println("Time: "+(System.currentTimeMillis()-startTime)+" ms");
		}
	}
}
