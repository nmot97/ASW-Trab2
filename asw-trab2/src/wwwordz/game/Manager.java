package wwwordz.game;

import wwwordz.game.Round;
import wwwordz.game.Round.Stage;
import wwwordz.shared.Puzzle;
import wwwordz.shared.Rank;
import wwwordz.shared.WWWordzException;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * This class is a singleton and acts as a facade for other classes in this package. Methods in this class are delegated in instances of these classes.
 * @author José Paulo Leal
 *
 */
public class Manager extends java.lang.Object implements Runnable {
	
	static final java.util.concurrent.ScheduledExecutorService worker = Executors.newScheduledThreadPool(1);
	static final long INITIAL_TIME = 0L;
	static Manager manager = null;
	Round round;
	
	/** 
	 * Single instance of Manager;
	 * @return isntance
	 */
	public static Manager getInstance() {
		return manager;
	}
	
	/**
	 * Runnable.
	 */
	public void run() {
		round = new Round();
	}
	
	/**
	 * Function call to round.getTimetoNextPlay();
	 * @return time in milliseconds
	 */
	public long timeToNextPlay() {
		return round.getTimetoNextPlay();
	}
	
	/**
	 * Register user with nick and password for current round.
	 * @param nick - of user to register
	 * @param password - of user to register
	 * @return time in seconds for next found
	 * @throws WWWordzException
	 */
	public long register(java.lang.String nick, java.lang.String password) throws WWWordzException {
		worker.scheduleAtFixedRate(this,100,100,TimeUnit.MILLISECONDS);
		int index = nick.indexOf(' ');
		if ( round.roundPlayers.containsKey(nick) || index > -1 || nick.length() == 0 )
			throw new WWWordzException("Error: Invalid User");
		else
			return round.register(nick,password);
	}
	
	/**
	 * Get table of current round.
	 * @return - return puzzle of current round if game has started 
	 * @throws WWWordzException - if game has not started 
	 */
	public Puzzle getPuzzle() throws WWWordzException { 
		if ( round.getStatus() == Stage.play  )
			throw new WWWordzException("Error: Game Not Started");
		else
			return round.getPuzzle();
	}
	
	/**
	 * Set number of points obtained by user in current round.
	 * @param nick - of user
	 * @param points - to set
	 * @throws WWWordzException - if game is not over or reporting has ended
	 */	
	public void setPoints(java.lang.String nick, int points) throws WWWordzException {
		if ( round.getStatus() == Stage.report )
			throw new WWWordzException("Error: Game Not Ended/Reporting Ended.");
		else
			round.setPoints(nick,points);
	}
	
	/**
	 * Set number of points obtained by user in current round.
	 * @return list of ranks
	 * @throws WWWordzException - if players can still report values
	 */
	public java.util.List<Rank> getRanking() throws WWWordzException {
		if ( round.getStatus() == Stage.ranking )
			throw new WWWordzException("Error: Players Can Still Report.");
		else
			return round.getRanking();
	}	
}
