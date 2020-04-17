package wwwordz.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import wwwordz.shared.WWWordzException;

/**
 * Persistent collection of players indexed by nick.
 * Each player has nick, password, points and accumulated points. Data is persisted using serialization and backup each time a new user is created or points are changed.
 * This class is a singleton
 * @author José Paulo Leal
 *
 */
public class Players extends java.lang.Object implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	static Players players = null;
	public static File home = new File(System.getProperty("user.dir"));
	Map <java.lang.String, Player> playersMap = new HashMap<java.lang.String, Player>();
	
	/**
	 * Constructor for a new instance of Players.
	 */
	Players() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cobaia.tmp",false));
			oos.writeObject("");
			oos.close();
		}
		catch (Exception ex) {
	         ex.printStackTrace();
		}	
	}
	
	/**
	 * Get single instance of this class.
	 * @return singleton instance
	 */
	public static Players getInstance() {
		File duck = new File("cobaia.tmp");
		if ( players == null ) {
			if ( duck.exists() || !(duck.length() == 0) ) { 
				try {
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream("cobaia.tmp"));
					players = (Players)ois.readObject();
					ois.close();
				}
				catch (Exception ex) {
				    ex.printStackTrace();
				    return players = new Players();
				}
				return players;
			}
			else
				return players = new Players();
		}
		else {
			return players;
		}
	}
	
	/**
	 * Current home directory, where the data file is stored.
	 * @return home - directory
	 */
	public static File getHome() {
		return Players.home;
	}
	
	/**
	 * Update home directory, where the data file is stored.
	 * @param home directory
	 */
	public static void setHome(File home) {
		Players.home = home;
	}
	
	/**
	 * Verify player's password. If player doesn't exist yet then it is created with given password.
	 * @param nick of player
	 * @param password of player
	 * @return true if passwords match and false otherwise
	 */
	public boolean verify(java.lang.String nick, java.lang.String password) {
		int index = nick.indexOf(' ');
		if ( index > -1 || nick.length() == 0 ) 
			return false;
		else if ( this.playersMap.containsKey(nick) && this.playersMap.get(nick).password.compareTo(password) == 0 )
			return true;
		else if ( this.playersMap.containsKey(nick) && this.playersMap.get(nick).password.compareTo(password) != 0 )
			return false;
		else {
			Player player = new Player(nick,password);
			this.playersMap.put(nick,player);
			try {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cobaia.tmp",false));
				oos.writeObject(players);
				oos.close();
			}
			catch (Exception ex) {
		         ex.printStackTrace();
			}
			return true;			
		}
	}
	
	/**
	 * Reset points of current round while keeping accumulated points.
	 * @param nick - of player
	 * @throws WWWordzException if player is unknown
	 */
	public void resetPoints(java.lang.String nick) throws WWWordzException {
		int index = nick.indexOf(' ');
		if ( index > -1 || nick.length() == 0 ) 
			return;
		else if ( !this.playersMap.containsKey(nick) )
			throw new WWWordzException("Error: Unknown player.");
		else {
			this.playersMap.get(nick).setPoints(0);
			try {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cobaia.tmp",false));
				oos.writeObject(players);
				oos.close();
			}
			catch (Exception ex) {
		         ex.printStackTrace();
			}
		}
	}
	
	/**
	 * Add points to player.
	 * @param nick - of player
	 * @param points - to add
	 * @throws WWWordzException if player in unknown
	 */
	public void addPoints(java.lang.String nick, int points) throws WWWordzException {
		int index = nick.indexOf(' ');
		if ( index > -1 || nick.length() == 0 ) 
			return;
		else if ( !this.playersMap.containsKey(nick) )
			throw new WWWordzException("Error: Unknown player.");
		else {
			this.playersMap.get(nick).setPoints(points);	
			try {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cobaia.tmp",false));
				oos.writeObject(players);
				oos.close();
			}
			catch (Exception ex) {
		         ex.printStackTrace();
			}
		}
	}
	
	/**
	 * Obtains a player from the playersMap.
	 * @param nick - of player
	 * @return player
	 */
	public Player getPlayer(java.lang.String nick) {
		Player cobaia = this.playersMap.get(nick);
		return cobaia;
	}
	
	/**
	 * Auxiliary function for debugging purposes to clean the playersMap and the output file.
	 */
	public void cleanup() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cobaia.tmp",false));
			oos.writeObject("");
			oos.close();
		}
		catch (Exception ex) {
	         ex.printStackTrace();
		}
		playersMap.clear();
	}
}