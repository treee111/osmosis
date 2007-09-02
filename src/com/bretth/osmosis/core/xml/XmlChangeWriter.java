package com.bretth.osmosis.core.xml;

import java.io.BufferedWriter;
import java.io.File;

import com.bretth.osmosis.core.container.ChangeContainer;
import com.bretth.osmosis.core.task.ChangeSink;
import com.bretth.osmosis.core.xml.impl.OsmChangeWriter;


/**
 * An OSM change sink for storing all data to an xml file.
 * 
 * @author Brett Henderson
 */
public class XmlChangeWriter extends BaseXmlWriter implements ChangeSink {
	
	private OsmChangeWriter osmChangeWriter;
	
	
	/**
	 * Creates a new instance.
	 * 
	 * @param file
	 *            The file to write.
	 * @param compressionMethod
	 *            Specifies the compression method to employ.
	 */
	public XmlChangeWriter(File file, CompressionMethod compressionMethod) {
		super(file, compressionMethod);
		
		osmChangeWriter = new OsmChangeWriter("osmChange", 0);
	}
	

	/**
	 * {@inheritDoc}
	 */
	public void process(ChangeContainer changeContainer) {
		initialize();
		
		osmChangeWriter.process(changeContainer);
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void beginElementWriter() {
		osmChangeWriter.begin();
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void endElementWriter() {
		osmChangeWriter.end();
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void setWriterOnElementWriter(BufferedWriter writer) {
		osmChangeWriter.setWriter(writer);
	}
}