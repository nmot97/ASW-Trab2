package wwwordz.game;

import wwwordz.shared.Puzzle;
import wwwordz.shared.Rank;
import wwwordz.shared.WWWordzException;
import wwwordz.game.Players;

import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;

/**
 * A round has 4 sequential stages
 * 	1.join - client join the round
 * 	2.play - client retrieves puzzle and player solve puzzle
 * 	3.report - clients report points back to server
 * 	4.ranking - clients ask for rankings
 * Each stage has a specific duration and the round method can only be executed within a limited time frame. The duration of each stage can be checked or changed with static setters and getters. The following method should be executed in the associated stages.
 * 	1.register() - join
 * 	2.getPuzzle() - play
 * 	3.setPoints() - register
 *  4.getRanking() - ranking
 * When executed outside their stages these methods raise a WWWordzException.
 * @author José Paulo Leal
 *
 */
public class Round extends java.lang.Object {
	java.util.Date join;
	java.util.Date play;
	java.util.Date report;
	java.util.Date ranking;
	java.util.Date end;
	java.util.Date current;
	java.util.Map<java.lang.String,java.lang.String> roundPlayers;
	Puzzle puzzle;
	
	static long joinStageDuration = -1;
	static long playStageDuration = -1;
	static long reportStageDuration = -1;
	static long rankingStageDuration = -1;
	
	Round.Stage status = Stage.join;
		
	static enum Stage {
		join,
		play, 
		report,
		ranking
	}
	
	/**
	 * Constructor to initialize the dates.
	 */
	public Round() {
		Calendar calendar = Calendar.getInstance();
		join = new Date();
		calendar.setTime(join);
		calendar.add(Calendar.MILLISECOND,(int) getJoinStageDuration());
		play = calendar.getTime();
		calendar.add(Calendar.MILLISECOND,(int) getPlayStageDuration());
		report = calendar.getTime();
		calendar.add(Calendar.MILLISECOND,(int) getReportStageDuration());
		ranking = calendar.getTime();
		calendar.add(Calendar.MILLISECOND,(int) getRankingStageDuration());
		end = calendar.getTime();
	}
	
	/**
	 * Returns status of instanced round.
	 * @return status - of instance
	 */
	public Round.Stage getStatus() {
		return this.status;
	}
	
	/**
	 * Gets duration of join stage in milliseconds.
	 * @return join duration in milliseconds
	 */
	public static long getJoinStageDuration() {
		return Round.joinStageDuration;
	}
	
	/**
	 * Change join stage.
	 * @param joinStageDuration in milliseconds
	 */
	public static void setJoinStageDuration(long joinStageDuration) {
		Round.joinStageDuration = joinStageDuration;
	}
	
	/**
	 * Gets duration of play stage in milliseconds.
	 * @return play duration in milliseconds
	 */
	public static long getPlayStageDuration() {
		return Round.playStageDuration;
	}
	
	/**
	 * Change play stage.
	 * @param playStageDuration in milliseconds
	 */
	public static void setPlayStageDuration(long playStageDuration) {
		Round.playStageDuration = playStageDuration;
	}
	
	/**
	 * Gets duration of report stage in milliseconds.
	 * @return stage duration in milliseconds
	 */
	public static long getReportStageDuration() {
		return Round.reportStageDuration;
	}
	
	/**
	 * Change report stage.
	 * @param reportStageDuration in milliseconds
	 */
	public static void setReportStageDuration(long reportStageDuration) {
		Round.reportStageDuration = reportStageDuration;
	}
	
	/**
	 * Gets duration of ranking stage in milliseconds.
	 * @return ranking duration in milliseconds
	 */
	public static long getRankingStageDuration() {
		return Round.rankingStageDuration;
	}
	
	/**
	 * Change ranking stage.
	 * @param rankingStageDuration in milliseconds
	 */
	public static void setRankingStageDuration(long rankingStageDuration) {
		Round.rankingStageDuration = rankingStageDuration;
	}
	
	/**
	 * Gets complete duration of a round (all stages).
	 * @return duration of a round
	 */
	public static long getRoundDuration() {
		return Round.joinStageDuration + Round.playStageDuration + Round.reportStageDuration + Round.rankingStageDuration;
	}
	
	/**
	 * Time in milliseconds to the next play stage. If the play stage of this round has already started then the time returned refers to the next round.
	 * @return time in milliseconds
	 */
	public long getTimetoNextPlay() {
		//INCOMPLETE
		return 0;
	}
	
	/**
	 * Register user with nick and password for this round.
	 * @param nick - of registered user
	 * @param password - of registered user
	 * @return time in milliseconds - for next round
	 * @throws WWWordzException if not it join stage or user is invalid
	 */
	public long register(java.lang.String nick, java.lang.String password) throws WWWordzException {
		//INCOMPLETE
		int index = nick.indexOf(' ');
		if ( index > -1 || nick.length() == 0 ) 
			throw new WWWordzException("Error: Invalid user.");
		else if ( !Players.getInstance().verify(nick,password) )
			throw new WWWordzException("Error: wrong password.");
		else {
			current = new Date();
			System.out.println("Current: " + current.getTime() + " Deadline: " + play.getTime() + " Result:" + (play.getTime() - current.getTime()) );
			System.out.println(current.before(play));
			if ( current.before(play) ) {
				System.out.println("what");
				roundPlayers.put(nick,password);
				System.out.println("hello3");
				return play.getTime()-current.getTime();
			}
			else {
				throw new WWWordzException("Error: Unable to join stage.");
			}
		}
	}
	
	/**
	 * Get table of this round.
	 * @return table - of this round
	 * @throws WWWordzException - if not in play stage
	 */
	public Puzzle getPuzzle() throws WWWordzException {
		//INCOMPLETE
		return puzzle;
	}
	
	/**
	 * Set number of points obtained by user in this round.
	 * @param nick - of user with changed points
	 * @param points - to set to user
	 * @throws WWWordzException - if not in report stage
	 */
	public void setPoints(java.lang.String nick, int points) throws WWWordzException {
		//INCOMPLETE
	}
	
	/**
	 * List of players in this round sorted by points.
	 * @return list or ranks
	 * @throws WWWordzException - if not in ranking stage
	 */
	public java.util.List<Rank> getRanking() throws WWWordzException {
		//INCOMPLETE
		java.util.List<Rank> rank = new ArrayList<>();
		return rank;
	}
}