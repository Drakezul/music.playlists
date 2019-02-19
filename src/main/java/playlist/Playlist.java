package playlist;

public interface Playlist {

	public void replaceAllMediaSrcs(String old, String replacement);

	/**
	 * Save at original path
	 */
	public void save();

	/**
	 * Save at (new) path
	 * 
	 * @param filepath
	 */
	public void saveAs(String filepath);
}