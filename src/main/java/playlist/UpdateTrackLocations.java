package playlist;

import java.io.File;
import java.io.FilenameFilter;

import playlist.wpl.WindowsMediaPlayerPlaylist;

public class UpdateTrackLocations {

	public static void main(String[] args) {
		File[] playlists = new File(args[0]).listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".wpl");
			}
		});
		for (File playlist : playlists) {
			WindowsMediaPlayerPlaylist current = WindowsMediaPlayerPlaylist.getPlaylist(playlist.getAbsolutePath());
			current.replaceAllMediaSrcs(args[1], args[2]);
			current.save();
		}
	}
}
