package edu.skidmore.cs326.game.sudoku.persistence.users;

import java.util.Date;

import org.apache.log4j.Logger;

import edu.skidmore.cs326.game.sudoku.frontend.home.RandomDisplayNameGenerator;
import edu.skidmore.cs326.game.sudoku.persistence.exception.PersistenceException;
import edu.skidmore.cs326.game.sudoku.persistence.security.PasswordSecurity;
import edu.skidmore.cs326.game.sudoku.persistence.security.SHA512;
import edu.skidmore.cs326.game.utility.EmailUtility;

/**
 * Represents User object.
 * 
 * Used for create account, log in, and account modification.
 * 
 * @author zlindewirth & Cheyenne Tanner
 */
public class Users implements User {
	/**
	 * User email.
	 */
	private String email;

	/**
	 * User code.
	 */
	private String code;

	/**
	 * User username.
	 */
	private String username;

	/**
	 * User inGame variable.
	 */
	private int inGame;

	/**
	 * User hashed password.
	 */
	private byte[] hashedPassword;

	/**
	 * User salt.
	 */
	private byte[] salt;

	/**
	 * SHA512 password hashing class object.
	 */
	private PasswordSecurity security = new SHA512();

	/**
	 * User wins.
	 */
	private int wins;

	/**
	 * User losses.
	 */
	private int losses;

	/**
	 * User draws.
	 */
	private int draws;

	/**
	 * User level.
	 */
	private int level;

	/**
	 * User last time logging in.
	 */
	private Date lastLogIn;

	/**
	 * Users avatar directory string.
	 */
	private String avatar;
	
	/**
	 * The logger.
	 */
	private static final Logger LOG;

	static {
		LOG = Logger.getLogger(Users.class);
	}

	/**
	 * Generate user object with only email parameter.
	 * 
	 * @param e String email
	 */
	public Users(String e) {
		this.email = e;
		LOG.info("Created user object with email " + e);
	}

	/**
	 * Generate user object with multiple parameters.
	 * 
	 * @param e    String email
	 * @param u    String username
	 * @param c    String code
	 * @param salt byte[] salt
	 * @param hp   byte[] hashedPassword
	 */
	public Users(String e, String u, String c, byte[] salt, byte[] hp) {
		this.email = e;
		this.username = u;
		this.code = c;
		this.salt = salt;
		this.hashedPassword = hp;
		LOG.info("Created user object using multiple parameters");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void userCreateAccount(String e) 
	    throws PersistenceException {
		// Probably should be made into a singleton
		this.email = e;
		this.username = RandomDisplayNameGenerator.
				generateRandomDisplayName();
		this.code = EmailUtility.generateOTP();
		this.salt = this.security.salt();
		LOG.info("Initialized new user with email " + e);
		EmailUtility.sendOTP(e, this.code);
        LOG.info("Sent code to " + e);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getEmail() {
		LOG.info("Getting email");
		return this.email;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCode() {
		LOG.info("Getting code");
		return this.code;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getUsername() {
		LOG.info("Getting username");
		return this.username;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getInGame() {
		LOG.info("Getting inGame");
		return this.inGame;
	}

	@Override
	public byte[] getHashedPassword() {
		LOG.info("Getting hashedPassword");
		return this.hashedPassword;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public byte[] getSalt() {
		LOG.info("Getting salt");
		return this.salt;
	}

	/**
	 * {@inheritDoc} 
	 */
	@Override
	public void setPassword(String password) 
	    throws PersistenceException {
		this.hashedPassword = security.createHash(
        		password, this.salt);
        LOG.info("Creating new hashedPassword "
        		+ "and adding to user object");
	}

	/**
	 * {@inheritDoc} 
	 */
	@Override
	public void setCode(String code) {
	    this.code = code;
	}

	/**
	 * User Level getter method.
	 * 
	 * @return level
	 */
	public int getLevel() {
		return this.level;
	}

	/**
	 * User Level setter method.
	 * 
	 * @param level
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * User wins getter method.
	 * 
	 * @return wins
	 */
	public int getWins() {
		return this.wins;
	}

	/**
	 * User Level setter method.
	 * 
	 * @param wins
	 */
	public void setWins(int wins) {
		this.wins = wins;
	}

	/**
	 * User Level getter method.
	 * 
	 * @return losses
	 */
	public int getLosses() {
		return this.losses;
	}

	/**
	 * User Level setter method.
	 * 
	 * @param losses
	 */
	public void setLosses(int losses) {
		this.losses = losses;
	}

	/**
	 * User draws getter method.
	 * 
	 * @return draws
	 */
	public int getDraws() {
		return this.draws;
	}

	/**
	 * User draws setter method.
	 * 
	 * @param draws
	 */
	public void setDraws(int draws) {
		this.draws = draws;
	}

	/**
	 * User last log in getter method.
	 * 
	 * @return last log in
	 */
	public Date isLastLogIn() {
		return this.lastLogIn;
	}

	/**
	 * User last log in setter method.
	 * 
	 * @param lastLogIn
	 */
	public void setLastLogIn(Date lastLogIn) {
		this.lastLogIn = lastLogIn;
	}

	/**
	 * User avatar directory getter method.
	 * 
	 * @return avatar
	 */
	public String getAvatar() {
		return this.avatar;
	}

	/**
	 * User avatar setter method.
	 * 
	 * @param avatar
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
		
	}
}
