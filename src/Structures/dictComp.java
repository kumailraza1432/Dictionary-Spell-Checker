package Structures;
//Dictionary Component, It stores a Word, It's Meaning and It's height in AVL
public class dictComp{
	String word;
	String Meaning;
	int height;
	public dictComp left = null;
	public dictComp right = null;
	public dictComp(String word, String Meaning)
	{
		this.word = word;
		this.Meaning = Meaning;
		height = 1;
	}
	@Override
	public String toString() {
		return word + "  " + Meaning;
	}
	
}