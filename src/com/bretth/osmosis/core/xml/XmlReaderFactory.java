package com.bretth.osmosis.core.xml;

import java.io.File;
import java.util.Map;

import com.bretth.osmosis.core.pipeline.RunnableSourceManager;
import com.bretth.osmosis.core.pipeline.TaskManager;


/**
 * The task manager factory for an xml reader.
 * 
 * @author Brett Henderson
 */
public class XmlReaderFactory extends XmlTaskManagerFactory {
	private static final String ARG_FILE_NAME = "file";
	private static final String DEFAULT_FILE_NAME = "dump.osm";
	private static final String ARG_ENABLE_DATE_PARSING = "enableDateParsing";
	private static final boolean DEFAULT_ENABLE_DATE_PARSING = true;
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TaskManager createTaskManagerImpl(String taskId, Map<String, String> taskArgs, Map<String, String> pipeArgs) {
		String fileName;
		File file;
		boolean enableDateParsing;
		CompressionMethod compressionMethod;
		XmlReader task;
		
		// Get the task arguments.
		fileName = getStringArgument(taskId, taskArgs, ARG_FILE_NAME, DEFAULT_FILE_NAME);
		enableDateParsing = getBooleanArgument(taskId, taskArgs, ARG_ENABLE_DATE_PARSING, DEFAULT_ENABLE_DATE_PARSING);
		compressionMethod = getCompressionMethodArgument(taskId, taskArgs);
		
		// Create a file object from the file name provided.
		file = new File(fileName);
		
		// Build the task object.
		task = new XmlReader(file, enableDateParsing, compressionMethod);
		
		return new RunnableSourceManager(taskId, task, pipeArgs);
	}
}