package Structures;

import java.util.ArrayList;

//AVL for insertion and Retrieval of Information in O(log n).

public class  DictAVL {
	private dictComp root = null;
	private int balance(dictComp node)
	{
		int balance = height(node.left) - height(node.right);
		return balance;
	}
	private dictComp rotateLeft(dictComp node)
	{
		dictComp RNode = node.right;
		dictComp RLNode = RNode.left;

		RNode.left = node;
		node.right = RLNode;

		//In This Case Only Height of RNode / node changes//
		updateHeight(node);
		updateHeight(RNode);

		return RNode;
	}
	private dictComp rotateRight(dictComp node)
	{
		dictComp LNode = node.left;
		dictComp LRNode = LNode.right;

		LNode.right = node;
		node.left = LRNode;

		//In This Case Only Height of RNode / node changes//
		updateHeight(node);
		updateHeight(LNode);

		return LNode;
	}
	private dictComp insertionHelp(dictComp node, dictComp newNode)
	{
		/*********************************INSERTION OF BST********************************************/
		// if there is no value//
		if (node == null)
		{
			return newNode;
		}
		//Same Value//
		if (node.word.compareToIgnoreCase(newNode.word) == 0)
		{
			if(!newNode.Meaning.equalsIgnoreCase(node.Meaning))
				node.Meaning = node.Meaning + "\n-" + newNode.Meaning;
			return node;
		}
		//Insertion//

		if (node.word.compareToIgnoreCase(newNode.word) < 0)
			node.left = insertionHelp(node.left, newNode);
		else if (node.word.compareToIgnoreCase(newNode.word) > 0)
			node.right = insertionHelp(node.right, newNode);
		/*********************************AVL SELF BALANCING********************************************/
		//Height Update//
		updateHeight(node);
		//Rotation//
		//LEFT LEFT CASE//
		if (balance(node) >= 2 && balance(node.left) >= 1)
		{
			return rotateRight(node);
		}
		//LEFT RIGHT CASE//
		else if (balance(node) >= 2 && balance(node.left) <= -1)
		{
			node.left = rotateLeft(node.left);
			return rotateRight(node);
		}
		//RIGHT RIGHT CASE//
		else if (balance(node) <= -2 && balance(node.right) <= -1)
		{
			return rotateLeft(node);
		}
		//RIGHT LEFT CASE//
		else if (balance(node) <= -2 && balance(node.right) >= 1)
		{
			node.right = rotateRight(node.right);
			return rotateLeft(node);
		}
		// No Rotation needed //
		return node;
	}
	dictComp deletionHelp(dictComp node, String word)
	{
		/*********************************DELETION OF BST********************************************/
		//Value Not Found
		if (node == null)
		{
			System.out.println("Word is not Avaliable");
			return node;
		}
		//Greater Value //
		if (node.word.compareToIgnoreCase(word) < 0)
		{
			node.left = deletionHelp(node.left, word);
		}
		else if (node.word.compareToIgnoreCase(word) > 0)
		{
			node.right = deletionHelp(node.right, word);
		}
		else if (node.word.compareToIgnoreCase(word) == 0)
		{
			//Make 3 Conditions here//
			//Left and Right of the Node is NULL//
			if (node.left == null && node.right == null)
			{
				return null;
			}
			//Node has a Right or a Left Node//
			else if (node.left == null)
			{
				dictComp subRight = node.right;
				return subRight;
			}
			else if (node.right == null)
			{
				dictComp subLeft = node.right;
				return subLeft;
			}
			else
			{
				//Goto the Right of this Node
				dictComp temp = node.right;
				//then Completely go towards the Left Node
				while (temp.left != null)
					temp = temp.left;
				//Assigning Current Node temp's Value
				node.word = temp.word;
				node.Meaning = temp.Meaning;
				//Delete the Node that we Accessed in temp
				node.right = deletionHelp(node.right, temp.word);
			}
		}
		/*********************************AVL SELF BALANCING********************************************/
		//Height Update//
		updateHeight(node);
		//Rotation//
		//LEFT LEFT CASE//
		if (balance(node) >= 2 && balance(node.left) >= 1)
		{
			return rotateRight(node);
		}
		//LEFT RIGHT CASE//
		else if (balance(node) >= 2 && balance(node.left) <= -1)
		{
			node.left = rotateLeft(node.left);
			return rotateRight(node);
		}
		//RIGHT RIGHT CASE//
		else if (balance(node) <= -2 && balance(node.right) <= -1)
		{
			return rotateLeft(node);
		}
		//RIGHT LEFT CASE//
		else if (balance(node) <= -2 && balance(node.right) >= 1)
		{
			node.right = rotateRight(node.right);
			return rotateLeft(node);
		}
		//No Rotation Needed//
		return node;
	}
	private int height(dictComp node)
	{
		if (node == null)
			return 0;
		return node.height;
	}
	private void updateHeight(dictComp node)
	{
		node.height = ((height(node.left) > height(node.right)) ? height(node.left) : height(node.right)) + 1;
	}
	private void InOrderTraversal(dictComp node)
	{
		if (node == null)
			return;
		InOrderTraversal(node.left);
		System.out.print(node.word + " ");
		InOrderTraversal(node.right);
	}
	public dictComp getRoot()
	{
		return root;
	}
	public void insert(String Word, String Meaning)
	{
		dictComp newNode = new dictComp(Word, Meaning);
		if (root == null)
		{
			root = newNode;
			return;
		}
		root = insertionHelp(root, newNode);
	}
	public void deleteElement(String word)
	{
		if (root == null)
		{
			return;
		}
		root = deletionHelp(root, word);
	}
	public dictComp search(String word)
	{
		dictComp curr = root;
		while (curr != null)
		{
			if (curr.word.compareToIgnoreCase(word) == 0)
				break;
			else if (curr.word.compareToIgnoreCase(word) > 0)
				curr = curr.right;
			else if (curr.word.compareToIgnoreCase(word) < 0)
				curr = curr.left;
		}
		return curr;
	}
	public ArrayList<String> similarWord(String word)
	{
		ArrayList<String> similarList = new ArrayList<String>();
		dictComp curr = root;
		while (curr != null)
		{
			if(curr.word.length() - word.length() >= -1 && curr.word.length() - word.length() <= 1)
			{
				similarList.add(curr.word);
			}
			if (curr.word.compareToIgnoreCase(word) > 0)
				curr = curr.right;
			else if (curr.word.compareToIgnoreCase(word) < 0)
				curr = curr.left;
		}
		return similarList;
	}
	void print()
	{
		InOrderTraversal(root);
		System.out.println();
	}
};
