package playlist.wpl.components.xml;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JsonIgnoreProperties(value= {
		"tid",
		"cid"
})
@JacksonXmlRootElement(localName="media")
public class Media {
	
	@JacksonXmlProperty(isAttribute=true)
	public String src;
	
	public Media() {}

	public Media(String src) {
		this.src = src;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}
}