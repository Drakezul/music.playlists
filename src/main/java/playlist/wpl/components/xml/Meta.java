package playlist.wpl.components.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Meta {

	@JacksonXmlProperty(isAttribute=true)
	public String name;
	@JacksonXmlProperty(isAttribute=true)
	public String content;

	public Meta() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/*- TODO remove if automated (de-)serialization works
	public static List<Meta> parse(NodeList rawMetas) {
		List<Meta> metas = new ArrayList<>(rawMetas.getLength());
		for (Node rawMeta : IterableNodeList.iterate(rawMetas)) {
			metas.add(new Meta(rawMeta.getAttributes().getNamedItem("name").getNodeValue(),
					rawMeta.getAttributes().getNamedItem("content").getNodeValue()));
		}
		return metas;
	}
	*/
}