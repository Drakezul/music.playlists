package playlist.m3u.components;

import java.io.File;

import playlist.m3u.M3UPlaylist;

public class M3UTrack {

	public int lengthInSeconds;
	public String displayName;
	public String filepath;

	public M3UTrack(int lengthInSeconds, String displayName, String filepath) {
		this.lengthInSeconds = lengthInSeconds;
		if (displayName == null || displayName.trim().isEmpty()) {
			this.displayName = getDisplayNameFrom(filepath);
		} else {
			this.displayName = displayName;
		}
		this.filepath = filepath;
	}

	public M3UTrack(String filepath) {
		this.filepath = filepath;
		this.displayName = getDisplayNameFrom(filepath);
		this.lengthInSeconds = -1;
	}

	public static String getDisplayNameFrom(String filepath) {
		int index = filepath.lastIndexOf(File.separator);
		if (index == -1) {
			return filepath;
		} else {
			return filepath.substring(index + 1, filepath.lastIndexOf("."));
		}
	}

	@Override
	public String toString() {
		return new StringBuilder(M3UPlaylist.EXTENSION_INF).append(lengthInSeconds)
				.append(",")
				.append(displayName)
				.append("\n")
				.append(filepath)
				.toString();
	}
}