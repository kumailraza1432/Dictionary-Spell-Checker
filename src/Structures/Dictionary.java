package Structures;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

//Hashing for Dictionary AVLs in 27 Buckets.

public class Dictionary {
	private DictAVL Words[];
	public Dictionary() {
		Words = new DictAVL[27]; // As There are 26 Letters in English And 1 bucket for Exceptions
	}
	public void initialize()
	{
		for(int i =0 ; i<27; i++)
		{
			Words[i] = new DictAVL();
		}
		this.loadData();
	}
	public DictAVL[] getHashTable()
	{
		return Words;
	}
	public void insert(String word, String meaning)
	 {
		if(word == "" || meaning == "")
			return;
		char firstChar = word.toLowerCase().charAt(0);
		int index = (firstChar >= 'a' && firstChar <= 'z')?firstChar%97:26;
		Words[index].insert(word, meaning);
	}
	public void delete(String word)
	{
		char firstChar = word.toLowerCase().charAt(0);
		int index = (firstChar >= 'a' && firstChar <= 'z')?firstChar%97:26;
		Words[index].deleteElement(word);
	}
	public String search(String word)
	{
		char firstChar = word.toLowerCase().charAt(0);
		int index = (firstChar >= 'a' && firstChar <= 'z')?firstChar%97:26;
		dictComp wordData = Words[index].search(word);
		if(wordData == null)
		{
			String Text = "Meaning not found\n\nSimilar Words : \n";
			ArrayList<String> similarList = this.similarWord(word);
			for(int i = 0; i < similarList.size(); i++)
				Text+=similarList.get(i) + "\n";
			
			return Text;
		}
		else
			return wordData.Meaning;
	}
	private void loadData()
	{
		String line = "";
		try {
			BufferedReader in = new BufferedReader(new FileReader("src/dictionary.dat"));
		    while ((line = in.readLine()) != null) {
		    	if(line.length() <= 2)
					continue;
				String word = "";
				String wordArray[] = line.split("  ");
				if(wordArray.length >= 2)
				{
					word = wordArray[0];
					int i = 1;
					String meaning = "";
					while(i<wordArray.length)
					{
						meaning+=" ";
						meaning += wordArray[i++];
					}
					this.insert(word, meaning);
				}
		    }
		    in.close();
		}
		catch(Exception e)
		{
			System.out.print(line);
			e.printStackTrace();
		}
	}
	public ArrayList<String> similarWord(String word)
	{
		char firstChar = word.toLowerCase().charAt(0);
		int index = (firstChar >= 'a' && firstChar <= 'z')?firstChar%97:26;
		return Words[index].similarWord(word);
	}
}
