/*
 * STRATEGY:
 *  - See how many key words are present within each of the sentences
 *  - The number of key words per sentence is stored into an int[], such that the 1st term represents the first sentence and so on.
 *  - Compare each of the array entries and reorganize the entries from greatest to least
 *  - Return the first 5 sentences
 */
public class Summary {
	int[][] posAndNum;
	String[] sentences;
	int numSentences;
	
	public Summary(String[] arr, String str) {
		numSentences = numSeen(".",str);
		int[] wordsPerSent = new int[numSentences];
		makeSentArr(str);
		for(int i = 0; i < numSentences; i++) {
			for(int a = 0; a < arr.length; a++) {
				wordsPerSent[i] += numSeen(arr[a], str);
			}
		}
		wordsPerSent[printLargest(wordsPerSent)] = 0;
		wordsPerSent[printLargest(wordsPerSent)] = 0;
		wordsPerSent[printLargest(wordsPerSent)] = 0;
		wordsPerSent[printLargest(wordsPerSent)] = 0;
		wordsPerSent[printLargest(wordsPerSent)] = 0;
	}
	
	private int numSeen(String small, String large) {
		String ans = large;
		int count = 0;
		while (ans.indexOf(small) != -1) {
			ans = ans.substring(0,ans.indexOf(small)) + ans.substring(ans.indexOf(small) + 1);
			count++;
		}
		return count;
	}
	
	private void makeSentArr(String str) {
		sentences = new String[numSentences];
		int start = 0;
		int end;
		for (int i = 0; i < sentences.length; i++){
			end = str.indexOf(".", start);
			if (end + 1 < str.length())
				sentences[i] = str.substring(start, end + 1);
			start = end + 2;
		}
	}
	
	private int printLargest(int[] arr) {
        int largest = Integer.MIN_VALUE;
        int largePos = 0;
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] > largest) {
                largest = arr[i];
                largePos = i;
            }
        }
		System.out.print(sentences[largePos]);
		return largePos;
	}
	
	private int[] deletePos(int[] arr, int pos) {
		int ans[] = new int[arr.length - 1];
		for (int i = 0; i < pos; i++) {
			ans[i] = arr[i];
		}
		for (int i = pos + 1; i < arr.length - 1; i++) {
			ans[i] = arr[i + 1];
		}
		return ans;
	}
}
