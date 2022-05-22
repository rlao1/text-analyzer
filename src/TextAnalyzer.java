import java.util.*;
import java.io.*;

public class TextAnalyzer {
	public static void main(String[] args) throws IOException 
	{
		File file = new File("file");
		List<String> list = new ArrayList<String>();
		List<Word> wordList = new ArrayList<Word>();
		Boolean duplicate = false;
		
		// scans and stores each word from file to list
		Scanner scan = new Scanner(file); scan.useDelimiter("[^A-Za-z]+");
		while(scan.hasNextLine()) 
		{ 
			try 
			{ 
				list.add(scan.next().toUpperCase()); 
			} catch(NoSuchElementException e) {} 
		} 

		// insantiates unique words as new objects to wordList and increment count
		for(String w : list) 
		{
			duplicate = false;
			
			for(Word wo : wordList) 
			{
				if(w.equals(wo.getWord()))
				{
					wo.incrementCount();
					duplicate = true;
					break;
				}
			}
			
			if(!duplicate) wordList.add(new Word(w));
		}
		
		// sorts and prints objects in wordList by descending count order
		Collections.sort(wordList, Word.descendingCount);
		int i = 1;
		for(Word wo : wordList) 
		{
			System.out.printf("%-5d", i++);
			wo.printStat();
		}
		 
	}
}

class Word {
	private String word;
	private int count;
	
	public static Comparator<Word> descendingCount = new Comparator<Word>() {
		public int compare(Word a, Word b) 
		{
			int aCount = a.getCount();
			int bCount = b.getCount();
			return bCount - aCount;
		}
	};
	
	public Word(String w) 
	{
		word = w;
		count = 1;
	}
	
	public void incrementCount() 
	{
		count++;
	}
	
	public int getCount()
	{
		return count;
	}
	
	public String getWord()
	{
		return word;
	}
	
	public void printStat() 
	{
		System.out.printf("%s, %d\n", word, count);
	}
}
