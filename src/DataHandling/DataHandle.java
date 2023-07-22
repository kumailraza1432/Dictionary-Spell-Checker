package DataHandling;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import Structures.DictAVL;
import Structures.Dictionary;
import Structures.dictComp;

public class DataHandle {
		public static void addLine(String Line, String path)
		{
			try
			{
				File file = new File(path);
				FileWriter fr = new FileWriter(file, true);
				fr.write("\n" + Line + "\n");
				fr.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
			System.out.println("Data has been saved");
		}
		public static void overWrite(Dictionary c, String path)
		{
			try
			{
				DictAVL[] words = c.getHashTable();
				File file = new File(path);
				FileWriter fr = new FileWriter(file);
				for(int i = 0; i<27; i++)
				{
					words[i].getRoot();
					overWriteRe(words[i].getRoot(), fr);
				}
				fr.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
			System.out.println("Data has been saved");
		}
		private static void overWriteRe(dictComp current, FileWriter writeData)
		{
			if(current == null)
				return;
			//Left sub Tree
			overWriteRe(current.right, writeData);
			//Writing Data
			try {
				writeData.write(current.toString() + "\n\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Right sub Tree
			overWriteRe(current.left, writeData);
			
		}
}
