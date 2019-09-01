import java.util.*;
import com.ibm.cloud.sdk.core.service.security.IamOptions;
import com.ibm.watson.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.natural_language_understanding.v1.model.EmotionOptions;
import com.ibm.watson.natural_language_understanding.v1.model.Features;
/**
 *Song class contains information about title and lyrics
 *and determines its genre based on its meaningful and frequent words
 *evaluated by TFIDF and training set of love words 
 *
 * @author Ruijie Cao, Ningke Hu, Tamara Prabhakar, Sanjana Prakash, Lingyi You
 * @version April 23, 2019
 */

public class Song {
	private String genre;
	private String title;
	private ArrayList<String> lyrics;
	private ArrayList<String> topwords;
	private double emotionScore;
	private HashMap<String, Integer> termFreq;
	private int totalNumOfWords;
	private boolean isLove;
	static public HashMap<String, Integer> words = new HashMap<>();
	
	/**
     * constructor
     * @param title song's title
     */
	public Song(String title){
		this.title = title; 
	}
	
	/**
     * constructor
     * @param t title
     * @param l lyrics
     */
	public Song(String t,ArrayList<String> l) {
		title = t;
		lyrics = l;
		termFreq = new HashMap<>();
		topwords = new ArrayList<>();
	}
	
	/**
     * gets song lyrics
     * @return a list of words in the song
     */
	public ArrayList<String> getLyrics() {
		return lyrics;
	}
	
	/**
     * gets word frequency
     * @return a map of words and the number of times they appear in song
     */
	public HashMap<String, Integer> getTermFreq(){
		return termFreq;
	}
	/**
     * gets total number of words in song
     * @return number of words
     */
	public int getTotalNumOfWords() {
		return totalNumOfWords;
	}
	/**
     * sets total number of words in song
     * @param num number of words
     * @return num number of words
     */
	public int setTotalNumOfWords(int num) {
		totalNumOfWords = num;
		return totalNumOfWords;
	}
	/**
     * gets all the words in song 
     * @return map of words and their score
     */
	public static HashMap<String, Integer> getWords(){
		return words;
	}
	
	/**
     * accepts the visitor so TFIDF can run on all
     * song objects in collection 
     * @param v visitor
     */
	public void accept(IVisitor v) {
		v.visit(this);
	}
	/**
     * gets top words in song based on frequency and meaningfulness
     * @return list of words
     */
	public ArrayList<String> getTopwords(){
		return topwords;
	}
	
	//song is an aggregation of classifier 
	//training set doesn't change so we only need a single classifier
	private static Classifier c = new Classifier(); 
	
	
	/**
     * gets the song genre
     * @return name of genre
     */
	public String getGenre() {
		return genre;
	}
	/**
     * sets the genre of a song
     * @param genre genre of song
     */
	public void setGenre(String genre) {
		this.genre = genre;
	}
	/**
     * gets title of song
     * @return name of song
     */
	public String getTitle() {
		return title;
	}
	/**
     * sets title of song
     * @param title title of song
     */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
     * sets the lyrics of song
     * @param lyrics lyrics of song
     */
	public void setLyrics(ArrayList<String> lyrics) {
		this.lyrics = lyrics;
	}

	
	/**
     * sets top words in song based on frequency and meaningfulness
     * @param topWords top words of song
     */
	public void setTopWords(ArrayList<String> topWords) {
		this.topwords = topWords;
	}
	/**
     * gets emotional score of song based on word sentiments
     * @return score value
     */
	public double getEmotionScore() {
		return emotionScore;
	}
	/**
     * sets emotional score of song based on word sentiments
     * @param emotionScore emotion score of song
     */
	public void setEmotionScore(double emotionScore) {
		this.emotionScore = emotionScore;
	}
	/**
     * gets word frequency
     * @return a map of words and the number of times they appear in song
     */
	public Map<String, Integer> getTermFreq1() {
		return termFreq;
	}

	/**
     * determines if song is a love song or not
     * @return true if the song is a long song
     */
	public boolean isLove() {
		return isLove;
	}

	
	/**
	 * determines whether a song (depending on its topWords)
	 * is a love song or not 
	 */
	public void setLove() {
		int countLove = 0; 
		//iterate through the topWords array list 
		for(int i = 0; i < topwords.size()/2; i++){
			//look at each word 
			String topWord = topwords.get(i); 
			//get the training set from classifier 
			if (c.getTrainingSet().contains(topWord)){
				//if training set contains the top word, then increase love count 
				countLove += 2; 
				System.out.println(topWord + " is a love word");
			}
		}
		for(int i = topwords.size()/2; i < topwords.size(); i++){
			//look at each word 
			String topWord = topwords.get(i); 
			//get the training set from classifier 
			if (c.getTrainingSet().contains(topWord)){
				//if training set contains the top word, then increase love count 
				countLove++; 
				System.out.println(topWord + " is a love word");
			}
		}
//		//get count of how many words are not love words
//		int countNotLove = topwords.size() - countLove; 
		//set the isLove (boolean) field 
		 
		if (countLove/(1.0*topwords.size()) >= 0.2){
			isLove = true; 
		} else {
			isLove = false; 
		}
	}
	
	/**
	 * will get the 4 emotions (joy, anger, fear, sad) for each list of top words 
	 * @return a hashmap mapping the emotion (String) to score (double)
	 */
	public Map<String, Double> getWatsonSentiment(){
			IamOptions options = new IamOptions.Builder()
				//---------------set API key here---------------------//
			    .apiKey("lnDDRc588Zowp2dMdk391B4ON7UD045l3NmsUPZWHEHN") 
			    .build();

			NaturalLanguageUnderstanding naturalLanguageUnderstanding = new NaturalLanguageUnderstanding("2018-11-16", options);
			//---------------set url here---------------------//
			naturalLanguageUnderstanding.setEndPoint("https://gateway-wdc.watsonplatform.net/natural-language-understanding/api");
			
			EmotionOptions emotion = new EmotionOptions.Builder()
			  .targets(topwords)
			  .build();

			Features features = new Features.Builder()
			  .emotion(emotion)
			  .build();
			
			//make a stringbuilder with all the topWords so we can pass it to Watson 
		    StringBuilder sb = new StringBuilder(); 
		    for(int i = 0; i < topwords.size(); i++){
		    	sb.append(topwords.get(i)); 
		    	//add space between words 
		    	sb.append(" "); 
		    }
			AnalyzeOptions parameters = new AnalyzeOptions.Builder()
			  .text(sb.toString())
			  .features(features)
			  .build();

			AnalysisResults response = naturalLanguageUnderstanding
			  .analyze(parameters)
			  .execute()
			  .getResult();
			
			//make a hashMap with the emotion and scores 
			Map<String, Double> emotionScores = new HashMap<>(); 
			emotionScores.put("Joy", response.getEmotion().getDocument().getEmotion().getJoy());
			emotionScores.put("Sad", response.getEmotion().getDocument().getEmotion().getSadness()); 
			emotionScores.put("Anger", response.getEmotion().getDocument().getEmotion().getAnger()); 
			emotionScores.put("Fear", response.getEmotion().getDocument().getEmotion().getFear()); 
			//return the map
			return emotionScores; 			
		
	}
		
		
		/**
		 * determines the overall genre of the song  
		 * love + joy = love
		 * love + sad = breakup
		 * love + anger = pain
		 * love + fear = yearning 
		 * no love + joy = happy
		 * no love  + sad = melancholy
		 * no love  + anger = rage
		 * no love  + fear = angst
		 * @return a String (the genre)
		 */
		public String determineGenre(){
			System.out.println("title"+this.getTitle());
			//call this method to set isLove field
			setLove(); 
			
			String songGenre = null; 
			Map<String, String> loveGenreMap = new HashMap<>(); 
			Map<String, String> noLoveGenreMap = new HashMap<>(); 
		    //maps the emotion with the correct genre (for the love category)
			loveGenreMap.put("Joy", "love"); 
			loveGenreMap.put("Sad", "breakup"); 
			loveGenreMap.put("Anger", "pain"); 
			loveGenreMap.put("Fear", "yearning"); 
			
			//maps the emotion with the correct genre (for the no love category)
			noLoveGenreMap.put("Joy", "happy");
			noLoveGenreMap.put("Sad", "melancholy");
			noLoveGenreMap.put("Anger", "rage");
			noLoveGenreMap.put("Fear", "angst");
			
			System.out.println("topwords"+this.getTopwords());
			
			//get the emotion and scores map from this method 
			Map<String, Double> emotionScores = getWatsonSentiment(); 
			
			//get highest emotion score 
			String highestEmotion = null; 
			double scoreOfHighestEmotion = 0.0; 
			
			//iterate through each entry in the entry set from the map
			for (Map.Entry<String, Double> entry : emotionScores.entrySet()){
				//get the key (emotion) and value (score)
				String emotion = entry.getKey(); 
				double score = entry.getValue(); 
				System.out.println("emotion "+emotion+":"+score);
				//if you find a higher score than the current score 
				if (score > scoreOfHighestEmotion){
					//set the highest emotion 
					highestEmotion = emotion; 
					//set the highest score 
					scoreOfHighestEmotion = score; 
				}
			}
			
			emotionScore = scoreOfHighestEmotion;
			//look at instance variable isLove. 
			//If the word is a love word 
			if(isLove == true){
				//then go the to LoveGenreMap and get the genre that matches the emotion
				songGenre = loveGenreMap.get(highestEmotion); 
			} else {
				//if the word is not a love word 
				songGenre = noLoveGenreMap.get(highestEmotion); 
			}	
			//set the genre and return it 
			setGenre(songGenre);
			
			System.out.println("Genre:"+this.getGenre());
			return songGenre; 
		}
}
 