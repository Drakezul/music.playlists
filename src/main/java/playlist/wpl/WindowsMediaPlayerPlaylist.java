package playlist.wpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import playlist.Playlist;
import playlist.m3u.M3UPlaylist;
import playlist.m3u.components.M3UTrack;
import playlist.wpl.components.xml.Body;
import playlist.wpl.components.xml.Head;
import playlist.wpl.components.xml.Media;

@JacksonXmlRootElement(localName = "smil")
public class WindowsMediaPlayerPlaylist implements Playlist {

	private static final String charsetName = "Windows-1252";

	private Head head;
	private Body body;

	@JsonIgnore
	public String path;

	protected WindowsMediaPlayerPlaylist() {}

	public static WindowsMediaPlayerPlaylist getPlaylist(String path) {
		XmlMapper mapper = new XmlMapper();
		WindowsMediaPlayerPlaylist list;
		try {
			list = mapper.readValue(new String(Files.readAllBytes(Paths.get(path)), Charset.forName(charsetName)),
					WindowsMediaPlayerPlaylist.class);
			list.path = path;
		} catch (IOException e) {
			return null;
		}
		return list;
	}

	@Override
	public void replaceAllMediaSrcs(String old, String replacement) {
		for (Media media : this.body.media) {
			media.src = media.src.replace(old, replacement);
			System.out.println(media.src);
		}
	}

	@Override
	public void save() {
		saveAs(this.path);
	}

	@Override
	public void saveAs(String path) {
		try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(new File(path)),
				Charset.forName(charsetName))) {
			XmlMapper mapper = new XmlMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			String res = "<?wpl version=\"1.0\"?>\n" + mapper.writeValueAsString(this);
			writer.write(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public M3UPlaylist toM3U() {
		M3UPlaylist m3u = new M3UPlaylist(this.path.replace(".wpl", ".m3u"));
		List<Media> media = this.body.media;
		for (Media medi : media) {
			m3u.addTrack(new M3UTrack(medi.src));
		}
		return m3u;
	}
}