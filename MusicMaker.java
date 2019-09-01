
import java.util.*;


/**
 * Represent the main class of Music Maker
 * @author Ruijie Cao
 *
 */
public class MusicMaker implements IUser{


	private String song;
	private String reference;
	private String total;
	private Set<String> referencelst;
	private Classifier c;

//	private String title;

	/**
	 * Constructor. 
	 */
	public MusicMaker(String filename) {

//		title = "";
		song = "";
		reference = "";
		total = "";
		referencelst = new HashSet<String>();
		c = new Classifier();
		c.scan(filename);
		c.runTFIDF();
		c.generateGenre();
//		HashMap<String, GenreBase> genreBases = c.getGenreBases();
//		for(GenreBase gb: genreBases.values()) {
//			System.out.println(gb.getGenre()+":"+gb.getSongbase().size());
//		}
	}

	public ArrayList<String> referencelstReset() {

		ArrayList<String> old = new ArrayList<String>();

		for (String s : referencelst) {

			old.add(s);

		}

		referencelst = new HashSet<String>();

		return old;

	}

	/**
	 * Generate the song according to the genre.
	 * @param genre that is the genre of the song, which will be generated.
	 */
    public String generateSong(String genre){

    	ArrayList<Song> songbase = new ArrayList<Song>();

    	HashMap<String, GenreBase> genreBases = new HashMap<>();
    	
    	genreBases = c.getGenreBases();
    	
    	songbase = genreBases.get(genre).getSongbase();

    	int baseSize = songbase.size();

    	
    	String verses1 = "";
    	ArrayList<String> verseslst1 = new ArrayList<String>();
		int versesNum1 = 4;
		
    	String verses2 = "";
    	ArrayList<String> verseslst2 = new ArrayList<String>();
		int versesNum2 = 4;
		
		String choruses = "";
		ArrayList<String> choruseslst = new ArrayList<String>();
		int chorusesNum = 3;
		
		String bridge  = "";
		ArrayList<String> bridgelst = new ArrayList<String>();
		int bridgeNum = 2;
		
		
		int count0 = 0;
		
		while (count0 < versesNum1) {
			
			Random rand = new Random();
			
			int songIndex = rand.nextInt((int)Math.ceil(baseSize/10.0));
			
			ArrayList<String> lyrics = songbase.get(songIndex).getLyrics();
			
			int lyricsSize = lyrics.size();
			
			Random rand2 = new Random();
			
    		int lyricIndex = rand2.nextInt(lyricsSize);
    		
    		verseslst1.add(lyrics.get(lyricIndex));

    		referencelst.add(songbase.get(songIndex).getTitle());

    		count0 = count0 + 1;
			
		}
		
		int count1 = 0;
		
		while (count1 < versesNum2) {
			
			Random rand = new Random();
			
			int songIndex = rand.nextInt(baseSize); 
			
			ArrayList<String> lyrics = songbase.get(songIndex).getLyrics();
			
			int lyricsSize = lyrics.size();
			
			Random rand2 = new Random();
    		
    		int lyricIndex = rand2.nextInt(lyricsSize);
    		
    		verseslst2.add(lyrics.get(lyricIndex));

    		referencelst.add(songbase.get(songIndex).getTitle());

    		count1 = count1 + 1;
			
		}

		int count2 = 0;
		
		while (count2 < chorusesNum) {
			
			Random rand = new Random();
			
			int songIndex = rand.nextInt(baseSize);
			
			ArrayList<String> lyrics = songbase.get(songIndex).getLyrics();
			
			int lyricsSize = lyrics.size();
			
			Random rand2 = new Random();
   		
    		int lyricIndex = rand2.nextInt(lyricsSize);
    		
    		choruseslst.add(lyrics.get(lyricIndex));

    		referencelst.add(songbase.get(songIndex).getTitle());

    		count2 = count2 + 1;
			
		}
		
		int count3 = 0;
		
		while (count3 < bridgeNum) {
			
			Random rand = new Random();
			
			int songIndex = rand.nextInt(baseSize);
			
			ArrayList<String> lyrics = songbase.get(songIndex).getLyrics();
			
			int lyricsSize = lyrics.size();
			
			Random rand2 = new Random();		
    		int lyricIndex = rand2.nextInt(lyricsSize);

    		
    		bridgelst.add(lyrics.get(lyricIndex));

    		referencelst.add(songbase.get(songIndex).getTitle());

    		count3 = count3 + 1; 
			
		}

		verses1 = String.join(".\n", verseslst1);
		
		verses2 = String.join(".\n", verseslst2);
		
		choruses = String.join(".\n", choruseslst); 
    	
		bridge = String.join(".\n", bridgelst);

//    	song = String.join(",", songlst);
		
//		System.out.println("verses1: "+verses1);
//		System.out.println("verses2: "+verses2);
//		System.out.println("choruses: "+choruses);
//		System.out.println("bridge: "+bridge);
		 
		song = verses1 + "\n" + "\n" + choruses  + "\n" + "\n" + verses2  + "\n" + "\n" + choruses  + "\n" + "\n" + bridge  + "\n" + "\n" + choruses + "\n" + "\n" + choruses; 
				
		return song;

    }

    /**
	 * Get the reference.
	 */
    public String getReference(){

	    	reference = String.join(".\n", referencelst);
	
	    	return reference;

    }

    /**
	 * Print the song to user.
	 */
    public String printSong(){

	    	total = "\nThe song is: " + "\n" + "\n" + song + "\n" + "\n" + "\n" + "The reference is: " + "\n" + reference;
	    	return total;

    }


    /**
	 * Main method. Play the Music Maker.
	 * @param args that is the arguments for the main method
	 */
    public static void main(String args[]) {

    	
    	MusicMaker maker = new MusicMaker("src/newlyrics.csv");
    	
//    	Scanner scan = new Scanner(System.in); //create a new scanner for the user input

    	
    	System.out.println("----------------------------------");
    	System.out.println("Welcome to the Music Maker program");
        

//        String input = scan.nextLine();

        boolean flag = true;
        Scanner scan = new Scanner(System.in); //create a new scanner for the user input
        
        while (flag) {
        	
        		maker.referencelstReset();

	        System.out.println("Hello! What song do you want to listen to?");
	        System.out.println("If you want to listen to Love song, please enter: l");
	        System.out.println("If you want to listen to Happy song, please enter: h");
	        System.out.println("If you want to listen to Breakup song, please enter: b");
	        System.out.println("If you want to listen to Melancholy song, please enter: m");
	        System.out.println("If you want to listen to Pain song, please enter: p");
	        System.out.println("If you want to listen to Rage song, please enter: r");
	        System.out.println("If you want to listen to Yearning song, please enter: y");
	        System.out.println("If you want to listen to Angst song, please enter: a");
	        System.out.println("If you want to quit, please enter: quit");
	        	
	        	String input = scan.nextLine();
	        	
	        	if ("quit".equals(input)) {
	        		flag = false;
	        		System.out.println("program exits");
	        		break;
	        	}
	        	
	        	else if ("l".equals(input)) {
		        	maker.generateSong("love");
		        	maker.getReference();
		        	System.out.println(maker.printSong());	
			}
		
			else if ("h".equals(input)) {
				maker.generateSong("happy");
				maker.getReference();
				System.out.println(maker.printSong());
	
			}
		
			else if ("b".equals(input)) {
				maker.generateSong("breakup");
	        		maker.getReference();
				System.out.println(maker.printSong());
	
			}
		
			else if ("m".equals(input)) {
				maker.generateSong("melancholy");
				maker.getReference();
				System.out.println(maker.printSong());
					
	
			}
	
			else if ("p".equals(input)) {
	
				maker.generateSong("pain");
				maker.getReference();
				System.out.println(maker.printSong());
	
			}
		
			else if ("r".equals(input)) {
				maker.generateSong("rage");
				maker.getReference();
				System.out.println(maker.printSong());
	
			}
	
			else if ("y".equals(input)) {
				maker.generateSong("yearning");
				maker.getReference();
				System.out.println(maker.printSong());
	
			}
		
			else if ("a".equals(input)) {
				maker.generateSong("angst");
				maker.getReference();
				System.out.println(maker.printSong());
			}	
			else {
				System.out.println("please check your input.");
			}
        }
		scan.close();
		
    }
}
