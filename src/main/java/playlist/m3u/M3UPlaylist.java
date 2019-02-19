package playlist.m3u;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import playlist.Playlist;
import playlist.m3u.components.M3UTrack;

public class M3UPlaylist implements Playlist {

	public static final String EXTENSION_FLAG = "#EXTM3U";
	public static final String EXTENSION_INF = "#EXTINF:";

	private String playlistLocation;
	private List<M3UTrack> tracks = new ArrayList<>();

	public static M3UPlaylist getPlaylist(String path) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(path));
		M3UPlaylist playlist = new M3UPlaylist(path);

		List<M3UTrack> tracks;
		if (EXTENSION_FLAG.equals(lines.get(0))) {
			int length = -1;
			String displayName = null;
			for (int i = 1; i < lines.size(); i++) {
				String line = lines.get(i).trim();
				if (line.length() == 0) {
					continue; // skip this index
				}
				if (line.startsWith(EXTENSION_INF)) {
					String[] components = line.substring(EXTENSION_INF.length()).split(",", 1);
					length = Integer.parseInt(components[0]);
					displayName = components[1];
				} else {
					playlist.addTrack(new M3UTrack(length, displayName, line));
				}
			}
		} else {
			for (String line : lines) {
				playlist.addTrack(new M3UTrack(line));
			}
		}
		return playlist;
	}

	public M3UPlaylist(String path) {
		if (path.endsWith(File.pathSeparator)) {
			this.playlistLocation = path;
		} else {
			this.playlistLocation = path + "/";
		}
	}

	@Override
	public void replaceAllMediaSrcs(String old, String replacement) {
		for (M3UTrack track : tracks) {
			track.filepath.replace(old, replacement);
		}
	}

	@Override
	public void save() {
		this.saveAs(this.playlistLocation);
	}

	@Override
	public void saveAs(String filename) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
			writer.write(M3UPlaylist.EXTENSION_FLAG + "\n");
			for (M3UTrack track : tracks) {
				writer.write(track.toString());
				writer.write("\n\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void addTrack(M3UTrack track) {
		this.tracks.add(track);
	}

	public void removeTrack(M3UTrack track) {
		this.tracks.remove(track);
	}

	public void removeTrack(int index) {
		this.tracks.remove(index);
	}
}