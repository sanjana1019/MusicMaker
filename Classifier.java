import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
/**
 * Classifier organizes songs by genre and categorizes most important lyrics
 *
 * @author Ruijie Cao, Ningke Hu, Tamara Prabhakar, Sanjana Prakash, Lingyi You
 * @version April 23, 2019
 */

/**
 * Word encapsulate word with its TF-IDF score
 */
class Word{
	String label;
	double score;
	public Word(String l, double s){
		label = l;
		score = s;
	}
}

public class Classifier implements IClassifier{
	
	private ArrayList<Song> base;
	private Set<String> trainingSet; 
	private HashMap<String, GenreBase> genreBases;
	/**
     * constructor 
     */
	public Classifier(){
		trainingSet = loadWords("love-words"); 
		base = new ArrayList<Song>();
		genreBases = new HashMap<>();
		String[] genres = {"love","breakup","pain","yearning","happy","melancholy", "rage","angst"};
		for(String g: genres) {
			GenreBase gb = new GenreBase(g);
			genreBases.put(g, gb);
		}
		
	}
	
	/**
     * Gets the base where all the songs are stored 
     * @return the list of songs in the base 
     */
	public ArrayList<Song> getBase() {
		return base;
	}

	/**
     * Sets the base where all the songs are stored 
     * @param base song base
     */
	public void setBase(ArrayList<Song> base) {
		this.base = base;
	}
	
	/**
     * Gets the training set of all of the love words 
     * @return the list of words
     */
	public Set<String> getTrainingSet() {
		return trainingSet;
	}
	/**
     * Sets the list of love words for the training set
     * @param trainingSet the set of word related to love
     */
	public void setTrainingSet(Set<String> trainingSet) {
		this.trainingSet = trainingSet;
	}

	/**
     * Gets the base where all the songs are stored 
     */
	public void scan(String filename) {
	        String line = "";
	        String cvsSplitBy = ",";
//	        int k = 0; // line # indicator
	        Set<String> title = new HashSet<>();
	        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

	            while ((line = br.readLine()) != null) {
	                String[] str = line.split(cvsSplitBy);//title: str[0], lyrics: rest
	                //System.out.println(k+": "+line);
	                
	                StringBuffer sb = new StringBuffer();//store lyrics
	                for(int i = 1; i < str.length; i++) {
	                	sb.append(str[i]);
	                }
	                if(title.contains(str[0])) continue;
	                //System.out.println(k+"ti: "+str[0]+"\nly\n");
	                
	                String[] lys = sb.toString().split("\\.");//split lyrics by line
	                ArrayList<String> lyrics = new ArrayList<>();
	                for(int i = 0; i < lys.length; i++) {
	                	lys[i] = lys[i].replace("\"", "");
	                	lys[i] = lys[i].replace("\n", "");
	                	lys[i] = lys[i].replace("\r", "");
	                	lys[i] = lys[i].trim();	
	                	if(lys[i].length() == 0) continue;
	                	
	                	lyrics.add(lys[i]);
	                	//System.out.println(lys[i]);
	                }
	                
	                title.add(str[0]);
	                Song s = new Song(str[0], lyrics);
	                base.add(s);	                	                
//	                k++;
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	/**
     * Generates a score for every word in each song
     * and ranks the words based on meaningfulness   
     */
	public void runTFIDF() {
		IVisitor v1 = new TFVisitor(); 
		IVisitor v2 = new IDFVisitor(); 
		for(Song s: base) {
			s.accept(v1);
			s.accept(v2);
		}
		
		HashMap<String, Integer> idf = Song.getWords();
		
		for(Song s: base) {
			ArrayList<String> tw = s.getTopwords();
			HashMap<String, Integer> tf = s.getTermFreq();
			int totalNumOfWords = s.getTotalNumOfWords();
			
			int size = 50;
			PriorityQueue<Word> minheap = new PriorityQueue<>(size, new Comparator<Word>() {
				public int compare(Word w1, Word w2) {
					int ret = (int) (10000*(w1.score - w2.score));
					return ret;
				}
			});
			
			for(String w: tf.keySet()) {
				double score = (tf.get(w)/(1.0*totalNumOfWords)) * Math.log((base.size()/(1.0*idf.get(w))));
				Word word = new Word(w,score);
				
				if(minheap.size() < size) {
					minheap.offer(word);
				}else {
					minheap.offer(word);
					minheap.poll();
				}
			}
			//System.out.println(s.getTitle()+"\n");
			while(!minheap.isEmpty()) {
				tw.add(minheap.poll().label);
			}
			Collections.reverse(tw);
		}
	}

	/**
	 * will load all the love-words from our document into a hash set 
	 * @return the hash set of strings called 'words'that contains all love related words 
	 */
	public Set<String> loadWords(String filename){
		Scanner in = null; 
		Set<String> loveWordsSet = new HashSet<>(); 
		try{
			//----------------make sure the name matches the document ----------------//
			in = new Scanner(new File(filename)); 
			//keep reading the next line 
			while (in.hasNextLine()){
				String word = in.nextLine(); 
				//trim any whitespaces/spaces
				if(!word.trim().equals("")){
					//add love word to set 
					loveWordsSet.add(word); 
				}
			}
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		} finally {
			try{
				//close file 
				in.close();
			}
			catch (Exception e){				
			}
		}
		return loveWordsSet; 
	}
	/**
     * Determines the genre for each song and adds it to 
     * the appropriate genre base that matches its genre
     */
	@Override
	public void generateGenre() {
		for(Song s: base) {
//			System.out.println(s.getTitle());
			s.determineGenre();
			genreBases.get(s.getGenre()).addSong(s);
		}
		for(GenreBase gb: genreBases.values()) {
			gb.getSongbase().sort(new Comparator<Song>() {
				public int compare(Song s1, Song s2) {
					int ret = (int) (10000*(s1.getEmotionScore() - s2.getEmotionScore()));
					return ret;
				}
			});
		}
		
	}
	/**
     * Gets all eight genre bases 
     * @return the list of words
     */
	public HashMap<String, GenreBase> getGenreBases() {
		return genreBases;
	}

	/**
     * sets genre base categories
     * @param genreBases genre as key, the corresponding base as value
     */
	public void setGenreBases(HashMap<String, GenreBase> genreBases) {
		this.genreBases = genreBases;
	}


	
}
