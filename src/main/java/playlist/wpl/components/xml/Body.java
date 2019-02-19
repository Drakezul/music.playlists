package playlist.wpl.components.xml;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

public class Body {
		
	@JacksonXmlElementWrapper(localName="seq", useWrapping=true)
	public List<Media> media;

	public Body() {}

	public Body(List<Media> media) {
		this.media = media;
	}

	public List<Media> getMedia() {
		return this.media;
	}

	public void setMedia(List<Media> media) {
		this.media = media;
	}
}