package playlist;

import java.io.File;
import java.io.FilenameFilter;

import playlist.wpl.WindowsMediaPlayerPlaylist;

public class WplToM3U {

	public static void main(String[] args) {
		File[] playlists = new File(args[0]).listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".wpl");
			}
		});
		for (File wpl : playlists) {
			WindowsMediaPlayerPlaylist current = WindowsMediaPlayerPlaylist.getPlaylist(wpl.getAbsolutePath());
			current.toM3U().save();
		}
	}
}