package RankingPage;
public class SearchingWords {


	String pat; 

	// the radix
	private final int R; 

	// the bad-character skip array
	private static int[] right;

	// pattern provided as a string 
	public SearchingWords(String pat) {
		this.R = 100000;
		this.pat = pat;

		// position of rightmost occurrence of c in the pattern
		right = new int[R];
		for (int c = 0; c < R; c++)
			right[c] = -1;
		for (int j = 0; j < pat.length(); j++)
			right[pat.charAt(j)] = j;
	}

	// return offset of first match and N if no match found
	public int search(String pat, String txt) {
		int M = pat.length();
		int N = txt.length();
		int skip;
		for (int i = 0; i <= N - M; i += skip) {
			skip = 0;
			for (int j = M - 1; j >= 0; j--) {
				if (pat.charAt(j) != txt.charAt(i + j)) {
					skip = Math.max(1, j - right[txt.charAt(i + j)]);
					break;
				}
			}
			if (skip == 0)
				return i; // found
		}
		return N; // not found
	}

}
