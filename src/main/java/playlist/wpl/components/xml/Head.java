package playlist.wpl.components.xml;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties(value= {
		"author"
})
public class Head {

	@JacksonXmlProperty(localName = "title")
	public String title;

	@JacksonXmlElementWrapper(useWrapping = false)
	public List<Meta> meta;

	public Head() {}

	public String getTitle() {
		return title;
	}

	public List<Meta> getMeta() {
		return meta;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setMeta(List<Meta> meta) {
		this.meta = meta;
	}
}