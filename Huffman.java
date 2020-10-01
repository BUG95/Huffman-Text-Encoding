import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;  
import javax.swing.text.BadLocationException;  
import javax.swing.text.Document;  
import javax.swing.text.SimpleAttributeSet;  
import javax.swing.text.StyleConstants;  
import java.util.Random;

class QuickSort{
	
	private int partition(Node[] nodes, int inf, int sup){
		int pivot = nodes[inf].getFreq();
		int start = inf;
		int end = sup;

		while(start < end){
			while(nodes[start].getFreq() <= pivot && start < end) start++;
			while(nodes[end].getFreq() > pivot) end--;
			if(start < end)
			{
				Node node = nodes[start];
				nodes[start] = nodes[end];
				nodes[end] = node;
			}
		}
		
		Node node = nodes[inf];
		nodes[inf] = nodes[end];
		nodes[end] = node;
		return end;
	}
	
	public void sortAscending(Node[] nodes, int inf, int sup){
		if(inf < sup){
			int location = partition(nodes, inf, sup);
			sortAscending(nodes, inf, location - 1);
			sortAscending(nodes, location + 1, sup);
		}
	}
}

class Node{
	private char c;  		 //caracter
	private int freq;		 //frecventa
	private Node left;       //copil stanga
	private Node right;		 //copil dreapta
	private Node next;       //nodul urmator
	
	public Node(char c, int freq){
		this.c = c;
		this.freq = freq;
	}
	
	public Node(){}
	
	public void setC(char c){
		this.c = c;
	}
	
	public void setFreq(int freq){
		this.freq = freq;
	}
	
	public void setLeft(Node node){
		left = node;
	}
	
	public void setRight(Node node){
		right = node;
	}
	
	public void setNext(Node node){
		next = node;
	}
	
	public char getC(){
		return c;
	}
	
	public int getFreq(){
		return freq;
	}
	
	public Node getLeft(){
		return left;
	}
	
	public Node getRight(){
		return right;
	}
	
	public Node getNext(){
		return next;
	}
	
	public void unsetLeft(){left = null;}
	public void unsetRight(){right = null;}
	public void unsetNext(){next = null;}
	
	public void display(){
		//if(c != '\0')
			System.out.println("Character: " + c);
		//else 
		//	System.out.println("Character: NULL");
		//if(freq != null)
			System.out.println("Frequency: " + freq);
		//else System.out.println("Frequency: NULL");
		if(left != null){
			System.out.println("Left Node Character: " + left.c);
			System.out.println("Left Node Frequency: " + left.freq);
		} else System.out.println("Left Node: NULL");
		if(right != null){
			System.out.println("Right Node Character: " + right.c);
			System.out.println("Right Node Frequency: " + right.freq);
		} else System.out.println("Right Node: NULL");
		if(next != null){
			System.out.println("Next Node Character: " + next.c);
			System.out.println("Next Node Frequency: " + next.freq);
		} else System.out.println("Next Node: NULL");
		System.out.println();
	}
}

class Queue{ 
	private ArrayList<Node> nodeList;
	
	public Queue(){
		nodeList = new ArrayList<Node>();
	}	
	
	public ArrayList<Node> getQueue() {
		return nodeList;
	}
	
	public void push(Node node){
		nodeList.add(node);
	}
	
	public Node popFirstElement(){
		Node node = nodeList.get(0);
		nodeList.remove(node);
		return node;
	}
	
	public Node popLastElement(){
		Node node = nodeList.get(getSize() - 1);
		nodeList.remove(node);
		return node;
	}
	
	public Node getLastElement(){
		Node node = nodeList.get(getSize() - 1);
		return node;
	}
	
	public void clear(){
		nodeList.clear();
	}
	
	public int getSize(){
		return nodeList.size();
	}
	
	public void sortAfterFreq(){
		Node nodes[] = new Node[getSize()];
		int i = 0;
		for(Node node : nodeList)
			nodes[i++] = node;
		
		QuickSort qs = new QuickSort();
		qs.sortAscending(nodes, 0, i - 1);

		nodeList.clear();

		for(int n = 0; n < i; n++)
			nodeList.add(nodes[n]);

	}
	
	public void display(){
		System.out.println("----------------------------");
		System.out.println("Queue");
		System.out.println("----------------------------");
		for(Node node : nodeList){
			node.display();
		}
		System.out.println("----------------------------");
	}
	
}

class Text{
	private String text;
	private char[] characters;
	private int[] freqs;
	private char[] uniqueCharacters;
	private int[] uniqueFreqs;
	private HashMap<Character, Integer> charactersFreqsMap;
	
	public Text(String text){
		this.text = text;
		setCharacters();
		setFreqs();
		setUniqueCharactersAndFreqs();
		setCharactersFreqsMap();
	}
	
	public char getCharAt(int position){
		return text.charAt(position);
	}
	
	public int getSize(){
		return text.length();
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public String getText(){
		return text;
	}
	
	
	//stabileste caracterele unice si frecventele acestora
	private void setCharactersFreqsMap(){
		charactersFreqsMap = new HashMap<Character, Integer>();
		int dim = uniqueCharacters.length;
		for(int i = 0; i < dim; i++)
			charactersFreqsMap.put(uniqueCharacters[i], uniqueFreqs[i]);
	}
	
	//returneaza mapa cu caracterele unice cu frecventele acestora
	public HashMap<Character, Integer> getCharactersFreqsMap(){
		return charactersFreqsMap;
	}
	
	//afiseaza caracterele unice si frecventa acestora
	public void displayCharactersFreqsMap(){
		for(Character c : charactersFreqsMap.keySet()){
			 System.out.println("key: " + c + " frequence: " + charactersFreqsMap.get(c));
		}
	}
	
	//incarca vectorul characters cu caracterele din text
	private void setCharacters(){
		int dim = getSize();
		characters = new char[dim];
		for(int i = 0; i < dim; i++)
			characters[i] = text.charAt(i);
	}
	
	//seteaza frecventele tuturor caracterelor din text
	private void setFreqs(){
		int dim = getSize();
		freqs = new int[dim];
		for(int i = 0; i < dim; i++) 
			freqs[i] = 0;
		for(int i = 0; i < dim; i++){
			for(int j = 0; j < dim; j++){
				if(characters[i] == characters[j])
					freqs[i]++;
			}
		}
	}
	
	//returneaza caracterele unice
	public char[] getUniqueCharacters(){
		return uniqueCharacters;
	}
	
	//stabileste care sunt caracterele unice si frecventele acestora
	private void setUniqueCharactersAndFreqs(){
		int dim = getTotalOfUniqueCharacters();
		int size = getSize();
		uniqueCharacters = new char[dim];
		uniqueFreqs = new int[dim];
		int k = 0;
		boolean found;
		for(int i = 0; i < size; i++){
			found = false;
			for(int j = 0; j < size && i != j; j++){
				if(characters[i] == characters[j]) {
					found = true;
					break;
				}
			}
			if(!found) {
				uniqueCharacters[k] = characters[i];
				uniqueFreqs[k] = freqs[i];
				k++;
			}
		}
	}
	
	//retrneaza numarul total de caractere unice din text
	public int getTotalOfUniqueCharacters(){
		int total = 0;
		int size = getSize();
		boolean found;
		for(int i = 0; i < size; i++){
			found = false;
			for(int j = 0; j < size && i != j; j++){
				if(characters[i] == characters[j]) {
					found = true;
					break;
				}
			}
			if(!found) total++;
		}
		return total;
	}
	
	//retrneaza toate frecventele din text
	public int[] getFreqs(){
		return freqs;
	}
	
	//retrneaza toata caracterele din text
	public char[] getCharacters(){
		return characters;
	}
	
	//afiseaza frecventele tututor caracterelor din text
	public void displayCharactersFrequencies(){
		int dim = getSize();
		int v[] = getFreqs();
		for(int i = 0; i < dim; i++)
			System.out.print(v[i]);
	}
	
	//afiseaza caracterele unice 
	public void displayUniqueCharacters(){
		int dim = uniqueCharacters.length;
		for(int i = 0; i < dim; i++)
			System.out.print(uniqueCharacters[i]);
	}
	
}

class HuffmanTree{
	
	private boolean valid; //valid cand un drum este complet
	private Queue q;
	private HashMap<String, Character> codeTable;
	
	public HuffmanTree(){
		q = new Queue();
		codeTable = new HashMap<String, Character>();
	}
	
	public HashMap<String, Character> getCodeTable(){
		return codeTable;
	}
	
	public void displayCodeTable(){
		for(String s : codeTable.keySet()){
			System.out.println("Code: " + s + " Character: " + codeTable.get(s));
		}
	}

	//Creeaza un nod parinte folosind nodurile n1 si n2
	//nodul cu frecventa caracterului mai mica trece in partea stanga
	//nodul cu frecventa caracterului mai mare trece in partea dreapta
	//returneaza nodul parinte
	public Node createParentNode(Node n1, Node n2){
		Node parent = new Node();
		int n1_freq = n1.getFreq();
		int n2_freq = n2.getFreq();
		parent.setFreq(n1_freq + n2_freq);
		if(n1_freq <= n2_freq){
			parent.setLeft(n1);
			parent.setRight(n2);
		}
		else {
			parent.setLeft(n2);
			parent.setRight(n1);
		}
		return parent;
	}
	
	//Creeaza arborele binar Huffman si returneaza radacina acestuia
	public Node getHuffmanTree(Queue q){
		int sizeOfQueue = q.getSize();
		int _sizeOfQueue = sizeOfQueue;
		Node roots[] = new Node[sizeOfQueue - 1];
		int index = 0;
		Node n1, n2;
		q.sortAfterFreq(); //sorteaza elementele din coada in ordine crescatoare
		while(sizeOfQueue - 1 > 0){
			n1 = q.popFirstElement();
			n2 = q.popFirstElement();
			roots[index] = createParentNode(n1, n2);
			q.push(roots[index]);
			q.sortAfterFreq();
			index ++;
			sizeOfQueue --;
		}
		return roots[_sizeOfQueue - 2]; // radacina arborelui
	}
	
	public void displayTree(Node treeRoot){
		if(treeRoot != null){
			displayTree(treeRoot.getLeft());
			System.out.print(treeRoot.getFreq() + " ");
			displayTree(treeRoot.getRight());
		}
	}
	
	//creeaza codurile Huffman de lungime variabila
	//asociaza fiecarui cod caracterul corespunzator
	//codul si caracterul sau se salveaza in mapa codeTable
	public void createHuffmanCode(Node treeRoot){
		if(treeRoot != null){
			valid = true;
			if(treeRoot.getLeft() != null){
				q.push(new Node(treeRoot.getLeft().getC(), 0));
				createHuffmanCode(treeRoot.getLeft());
			}
			if(treeRoot.getRight() != null){
				q.push(new Node(treeRoot.getRight().getC(), 1));
				createHuffmanCode(treeRoot.getRight());
			}
			if(valid){
				ArrayList<Node> nodeList = q.getQueue();
				String s = "";
				for(Node node : nodeList){
					s += node.getFreq();
					if(node == q.getLastElement())
						codeTable.put(s, node.getC());
				}
			}
			if(q.getSize() > 0){
				q.popLastElement();
				valid = false;
			}
		}	
	}
	
	
}

class HuffmanDecoding{
	private String huffmanCode;
	private HashMap<String, Character> codeTable;
	
	public HuffmanDecoding(String huffmanCode, HashMap<String, Character> codeTable){
		this.huffmanCode = huffmanCode;
		this.codeTable = codeTable;
	}
	
	public String decode(){
		String s = "";
		String decodedText = "";
		boolean decoded = false;
		int i = 0;
		while(!decoded){
			s += huffmanCode.charAt(i);
			for(String c : codeTable.keySet()){
				if(s.equals(c)) {
					//System.out.print(codeTable.get(c));
					decodedText += codeTable.get(c);
					s = "";
					break;
				}
			}
			if(i == huffmanCode.length() - 1) decoded = true;
			i++;
		}
		return decodedText;
	}
}

class HuffmanEncoding{
	private Text text;
	private String huffmanCode;
	private HashMap<String, Character> codeTable;
	
	public HuffmanEncoding(Text text){
		this.text = text;
	}
	
	public void setCodeTable(HashMap<String, Character> codeTable){
		this.codeTable = codeTable;
	}
	
	public HashMap<String, Character> getCodeTable(){
		return codeTable;
	}
	
	public void setHuffmanCode(String huffmanCode){
		this.huffmanCode = huffmanCode;
	}
	
	public String getHuffmanCode(){
		return huffmanCode;
	}
	
	public void encode(){
		HashMap<Character, Integer> charactersFreqsMap = text.getCharactersFreqsMap();
		int numberOfNodes = text.getTotalOfUniqueCharacters();
		Node n[] = new Node[numberOfNodes];
		int i = 0;
		for(Character c : charactersFreqsMap.keySet()){
			n[i++] = new Node(c, charactersFreqsMap.get(c)); 
		}
		Queue q = new Queue();
		for(int j = 0; j < numberOfNodes; j++)
			q.push(n[j]);
		
		HuffmanTree ht = new HuffmanTree();
		Node treeRoot = ht.getHuffmanTree(q);
		ht.createHuffmanCode(treeRoot);
		setCodeTable(ht.getCodeTable());
		//ht.displayCodeTable();
		setHuffmanCode(text, ht.getCodeTable());
		String hcode = getHuffmanCode();
		//displayCode(hcode);
	}
	
	private void setHuffmanCode(Text text, HashMap<String, Character> codeTable){
		String code = "";
		char[] characters = text.getCharacters();
		for(int i = 0; i < characters.length; i++){
			for(String s : codeTable.keySet()){
				if(characters[i] == codeTable.get(s)){
					code += s + " ";
					break;
				}	
			}
		}
		this.huffmanCode = code;
	}
	
	public void displayCode(String code){
		System.out.println("--------------------------");
		System.out.println("Codificarea Huffman:");
		System.out.println(code);
		System.out.println("--------------------------");
	}
}

class GUI extends JFrame implements ActionListener, ItemListener{
	private JTabbedPane tabbedPane;
	private JPanel[] panel;
	private JScrollPane[] pane;
	private JTextArea[] textArea;
	private JLabel[] label;
	private FontMetrics fm;
	private JButton[] b;
	private JCheckBox[] cb;
	private String code;
	private HashMap<String, Character> codeTable = null;
	private String t = ""; //folosit pentru a genera codeTable
	private JTextPane[] tp;

	
	public GUI(String title){
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 600);
		setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem file = new JMenuItem("Add text file");
		menu.add(file);
		menuBar.add(menu);
		file.addActionListener(this);
		setJMenuBar(menuBar);
		
		UIManager.put("TabbedPane.selected", Color.green);
		tabbedPane = new JTabbedPane();
		add(tabbedPane);
		
		panel = new JPanel[5];
		panel[0] = new JPanel();
		panel[1] = new JPanel();
		panel[2] = new JPanel(); // contine panel[0] si panel[1]
		panel[3] = new JPanel();
		panel[4] = new JPanel();
		
		textArea = new JTextArea[1];
		textArea[0] = new JTextArea();
		
		tp = new JTextPane[3];
		tp[0] = new JTextPane();
		tp[1] = new JTextPane();
		tp[2] = new JTextPane();
		tp[1].setEditable(false);
		tp[2].setEditable(false);
		
		pane = new JScrollPane[4];
		pane[0] = new JScrollPane(textArea[0]); 
		pane[1] = new JScrollPane(tp[0]);
		pane[2] = new JScrollPane(tp[1]);
		pane[3] = new JScrollPane(tp[2]);
		
		cb = new JCheckBox[4];
		cb[0] = new JCheckBox("a-z");
		cb[1] = new JCheckBox("A-Z");
		cb[2] = new JCheckBox("0-9");
		cb[3] = new JCheckBox("caractere speciale: ~!#$ ...");
		cb[0].addItemListener(this);
		cb[1].addItemListener(this);
		cb[2].addItemListener(this);
		cb[3].addItemListener(this);
		
		label = new JLabel[4];
		label[0] = new JLabel("Text Decodificat");
		label[1] = new JLabel("Text Codificat");
		label[2] = new JLabel("Selectati tabelul de coduri pentru decodificare");
		label[3] = new JLabel("Nota: Selectati tabelul de coduri pentru a folosi decodificarea");
		
		Font font = new Font("Arial", Font.BOLD, 13);
		label[0].setFont(font);
		label[1].setFont(font);
		label[2].setFont(font);
		fm = getFontMetrics(font); 
		
		b = new JButton[3];
		b[0] = new JButton("Codifica");
		b[1] = new JButton("Decodifica");
		b[2] = new JButton("Confirma");
		b[0].addActionListener(this);
		b[1].addActionListener(this);
		b[2].addActionListener(this);
		
		panel[0].setLayout(null);
		label[0].setBounds(10 + 135 - fm.stringWidth(label[0].getText())/2, 5, 250, 10);
		b[0].setBounds(140 - fm.stringWidth(b[0].getText())/2 - 30, 330, 100, 30);
		textArea[0].setLineWrap(true);
		textArea[0].setWrapStyleWord(true);
		pane[0].setBounds(10, 20, 270, 300);
		panel[0].add(label[0]);
		panel[0].add(pane[0]);
		panel[0].add(b[0]);
		
		panel[1].setLayout(null);
	    label[1].setBounds(10 + 135 - fm.stringWidth(label[1].getText())/2, 5, 250, 10);
		b[1].setBounds(140 - fm.stringWidth(b[0].getText())/2 - 20, 330, 100, 30);
		pane[1].setBounds(10, 20, 270, 300);	
		panel[1].add(label[1]);
		panel[1].add(pane[1]);
		panel[1].add(b[1]);

		panel[2].setLayout(null);
		label[3].setBounds(310 - fm.stringWidth(label[3].getText())/2, 400, 350, 50);
		panel[0].setBounds(0, 0, 290, 400);
		panel[1].setBounds(300, 0, 290, 400);
		panel[2].add(panel[0]);
		panel[2].add(panel[1]);
		panel[2].add(label[3]);
		
		tabbedPane.addTab("Compresie / Decompresie", panel[2]);

		panel[3].setLayout(null);
		pane[2].setBounds(10, 20, 570, 300);
		panel[3].add(pane[2]);
		label[2].setBounds(280 - fm.stringWidth(label[2].getText())/2, 310, 300, 50);
		panel[3].add(label[2]);
		cb[0].setBounds(10, 350, 45, 20);
		cb[1].setBounds(10, 370, 45, 20);
		cb[2].setBounds(10, 390, 45, 20);
		cb[3].setBounds(10, 410, 180, 20);
		panel[3].add(cb[0]);
		panel[3].add(cb[1]);
		panel[3].add(cb[2]);
		panel[3].add(cb[3]);
		b[2].setBounds(15, 430, 90, 30);
		panel[3].add(b[2]);
		tabbedPane.addTab("Tabel coduri", panel[3]);

		panel[4].setLayout(null);
		pane[3].setBounds(10, 20, 570, 490);
		panel[4].add(pane[3]);
		tabbedPane.addTab("Detalii", panel[4]);
		show();
	}
	
	
	public Color getRandomColor(){
		Random rand = new Random();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		Color randomColor = new Color(r, g, b);
		return randomColor;
	}
	public void listCodeTable(Text text, JTextPane tp, boolean extraInfo){
		
		
		SimpleAttributeSet red = new SimpleAttributeSet();  
		SimpleAttributeSet green = new SimpleAttributeSet();
		SimpleAttributeSet orange = new SimpleAttributeSet();
		SimpleAttributeSet magenta = new SimpleAttributeSet();
		SimpleAttributeSet cyan = new SimpleAttributeSet();
		SimpleAttributeSet blue = new SimpleAttributeSet();
		SimpleAttributeSet black = new SimpleAttributeSet();
		SimpleAttributeSet randomColor = new SimpleAttributeSet();
		
		StyleConstants.setForeground(red, Color.red);
		StyleConstants.setForeground(green, Color.green.darker());
		StyleConstants.setForeground(orange, Color.orange);
		StyleConstants.setForeground(magenta, Color.magenta);
		StyleConstants.setForeground(cyan, Color.cyan);
		StyleConstants.setForeground(blue, Color.blue);
		StyleConstants.setForeground(black, Color.black);
		StyleConstants.setForeground(randomColor, getRandomColor().darker());
		
		StyleConstants.setBold(red, true);
		StyleConstants.setBold(green, true);
		StyleConstants.setBold(orange, true);
		StyleConstants.setBold(magenta, true);
		StyleConstants.setBold(cyan, true);
		StyleConstants.setBold(blue, true);
		StyleConstants.setBold(black, true);
		StyleConstants.setBold(randomColor, true);
		
		int[] freqs = text.getFreqs();
		tp.setText("");
		
		int huffmanCodedLength = 0;
		for(String s : codeTable.keySet()){
			if(extraInfo){
				for(int i = 0; i < text.getSize(); i++){
					if(codeTable.get(s) == text.getCharAt(i)){
						try{
							String info1 = "Caracter: ";
							String info2;
							if(codeTable.get(s) == ' ') info2 = "[spatiu]";
							else if(codeTable.get(s) == '\n') info2 = "[linie noua]";
							else info2 = codeTable.get(s) + " ";
							String info3 = " Frecventa: ";
							String info4 = freqs[i] + " ";
							String info5 = " Cod: ";
							String info6 = s;

							tp.getStyledDocument().insertString(0, info6 + "\n", blue);
							tp.getStyledDocument().insertString(0, info5, black);
							tp.getStyledDocument().insertString(0, info4, red);
							tp.getStyledDocument().insertString(0, info3, black);
							tp.getStyledDocument().insertString(0, info2, green);
							tp.getStyledDocument().insertString(0, info1, black);
						} catch(BadLocationException e){}
						huffmanCodedLength += s.length() * freqs[i];
						break;
					}
				} 
			} else {
				try{
					String c = "";
					tp.getStyledDocument().insertString(0, s + "\n", randomColor);
					tp.getStyledDocument().insertString(0,  " Cod: ", black);
					if(codeTable.get(s) == ' ') c = "[spatiu]";
					else if(codeTable.get(s) == '\n') c = "[linie noua]";
					else c = codeTable.get(s) + "";
					tp.getStyledDocument().insertString(0, c, randomColor);
					tp.getStyledDocument().insertString(0, "Caracter: ", black);	
				}
				catch(BadLocationException e){}
			}
		}
		
		if(extraInfo){
			try{
				tp.getStyledDocument().insertString(0, " biti\n\n", black);
				tp.getStyledDocument().insertString(0, (text.getSize() * 8 - huffmanCodedLength) + "", randomColor);
				tp.getStyledDocument().insertString(0, " biti\nSpatiu salvat: ", black);
				tp.getStyledDocument().insertString(0, huffmanCodedLength + "", randomColor);
				tp.getStyledDocument().insertString(0, "Lungimea codifcarii cu compresia Huffman: ", black);
				tp.getStyledDocument().insertString(0, " biti (8 biti / caracter)" + "\n", black);
				tp.getStyledDocument().insertString(0, text.getSize() * 8 + "", randomColor);
				tp.getStyledDocument().insertString(0, "Lungimea codificarii fara compresie: ", black);
				tp.getStyledDocument().insertString(0, text.getTotalOfUniqueCharacters() + "\n", randomColor);
				tp.getStyledDocument().insertString(0, "Caractere unice: ", black);
				tp.getStyledDocument().insertString(0, text.getSize() + "\n", randomColor);
				tp.getStyledDocument().insertString(0, "Caractere totale: ", black);
				tp.getStyledDocument().insertString(0, text.getText() + "\n", randomColor);
				tp.getStyledDocument().insertString(0, "Textul introdus: ", black);
			}catch(BadLocationException e){}
		} else {
			try{
				tp.getStyledDocument().insertString(0, "Tabelul de coduri folosit pentru decompresie\n\n", black);
			} catch(BadLocationException e){}
		}
	}
	
	public void putCodeOn(JTextPane tp, String code){
		tp.setText("");
		SimpleAttributeSet redForegroundAttribute = new SimpleAttributeSet();  
		SimpleAttributeSet greenForegroundAttribute = new SimpleAttributeSet();
        StyleConstants.setBold(redForegroundAttribute, true);  
		StyleConstants.setBold(greenForegroundAttribute, true);  
        StyleConstants.setForeground(redForegroundAttribute, Color.red);  
		StyleConstants.setForeground(greenForegroundAttribute, Color.green.darker());
		String _code = "";
		int i = 0;
		char c;
		boolean change = false;
		int len = code.length();
		while(len-- > 0){
			if((c = code.charAt(i++)) != ' '){
				_code += c;
			} 
			else {
				try{
					if(!change){
						tp.getStyledDocument().insertString(tp.getText().length(), _code, greenForegroundAttribute);
						change = !change;
						_code = "";
					} else{
						tp.getStyledDocument().insertString(tp.getText().length(), _code, redForegroundAttribute);
						change = !change;
						_code = "";
					}
				} catch(BadLocationException e){System.out.println(e);}
			}
		}
	}
	
	public void itemStateChanged(ItemEvent e){
		boolean cb0, cb1, cb2, cb3;
		cb0 = cb1 = cb2 = cb3 = false;
		if(cb[0].isSelected()) cb0 = true;
		if(cb[1].isSelected()) cb1 = true;
		if(cb[2].isSelected()) cb2 = true;
		if(cb[3].isSelected()) cb3 = true;
		t = "";
		if(cb0){
			//litere mici
			for(int i = 97; i <= 122; i++)
			t += (char)i + "";
		}
		if(cb1){
			//litere mari
			for(int i = 65; i <= 90; i++)
			t += (char)i + "";
		}
		if(cb2){
			//numere
			for(int i = 48; i <= 57; i++)
				t += (char)i + "";
		}
		if(cb3){
			//caractere speciale
			t += (char)10 + ""; //new line
			for(int i = 32; i <= 47; i++)
				t += (char)i + "";
			for(int i = 58; i <= 64; i++)
				t += (char)i + "";
			for(int i = 91; i <= 96; i++)
				t += (char)i + "";
			for(int i = 123; i <= 126; i++)
				t += (char)i + "";
		}
	}
	
	public void showMessage(String error, String details){
		final JOptionPane _pane = new JOptionPane(details);
		final JDialog d = _pane.createDialog((JFrame)null, error);
		d.setLocation((int)getLocation().getX() + 160, (int)getLocation().getY() + 200);
		d.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event){
		String command = event.getActionCommand();
		if(command.equals("Add text file")){
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("."));
			int result = fileChooser.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				try{
					BufferedReader br = new BufferedReader(new FileReader(selectedFile));
					textArea[0].setText("");
					String string = "";
					while((string = br.readLine()) != null){
						textArea[0].append(string + "\n");
					}
				} catch(IOException ex) {System.out.println("Eroare la citirea fisierului text");}			
			}
		}
		if(command.equals("Codifica")){
			Text text = new Text(textArea[0].getText());
			boolean valid = false;
			//verifica daca exista macar 2 caractere diferite
			for(int i = 0; i < text.getSize() && !valid; i++){
				for(int j = i + 1; j < text.getSize(); j++){
					if(text.getCharAt(i) != text.getCharAt(j)){
						valid = true;
						break;
					}
				}
			}
			if(valid){
				HuffmanEncoding he = new HuffmanEncoding(text);
				he.encode();
				code = he.getHuffmanCode();
				putCodeOn(tp[0], code);
				codeTable = he.getCodeTable();
				listCodeTable(text, tp[1], false);
				listCodeTable(text, tp[2], true);
			}
			else {
				showMessage("Eroare", "Minimul de caractere diferite: 2");
			}
		}
		if(command.equals("Decodifica")){	
			if(codeTable != null && tp[0].getText().length() > 0){
				boolean valid = true;
				code = tp[0].getText();
				//verifica daca exista caractere diferite de 0, 1, spatiu sau linie noua
				for(int i = 0; i < code.length() && valid; i++){
					if((int)code.charAt(i) != 48 && (int)code.charAt(i) != 49 && (int)code.charAt(i) != 10 && (int)code.charAt(i) != 13 && (int)code.charAt(i) != 32)
					valid = false;
				}
				
				if(valid){
					int removable = 0;
					//eliminarea spatiilor si a liniilor goale
					for(int i = 0; i < code.length(); i++){
						if((int)code.charAt(i) == 32 || (int)code.charAt(i) == 10 || (int)code.charAt(i) == 13){
							removable ++;
							for(int j = i + 1; j < code.length(); j++)
								if((int)code.charAt(j) == 32 || (int)code.charAt(j) == 10 || (int)code.charAt(j) == 13)
									removable ++;
								else break;
						}
						
						if(removable != 0){
							code = code.substring(0, i) + code.substring(i + removable);
							removable = 0;
						}
					}

					HuffmanDecoding hd = new HuffmanDecoding(code, codeTable);
					String decodedText = hd.decode();
					textArea[0].setText(decodedText);
				}
				else showMessage("Eroare", "Se accepta doar cod binar");
				
			} else if(codeTable == null){
				showMessage("Eroare", "Nu este setat tabelul de coduri");
			} else if(tp[0].getText().length() == 0){
				showMessage("Eroare", "Nimic de decodificat");
			}
		}
		if(command.equals("Confirma")){
			if(t != ""){
				Text text = new Text(t);
				HashMap<Character, Integer> charactersFreqsMap = text.getCharactersFreqsMap();
				int numberOfNodes = text.getTotalOfUniqueCharacters();
				Node n[] = new Node[numberOfNodes];
				int i = 0;
				for(Character c : charactersFreqsMap.keySet()){
					n[i++] = new Node(c, charactersFreqsMap.get(c)); 
				}
				Queue q = new Queue();
				for(int j = 0; j < numberOfNodes; j++)
					q.push(n[j]);
				
				HuffmanTree ht = new HuffmanTree();
				Node treeRoot = ht.getHuffmanTree(q);
				ht.createHuffmanCode(treeRoot);
				codeTable = ht.getCodeTable();
				listCodeTable(text, tp[1], false);
			}
			else {
				showMessage("Eroare", "Nimic bifat");
			}
		}
	}
}

public class Huffman{

	public static void main(String[] args){
		new GUI("Huffman");
	}
}