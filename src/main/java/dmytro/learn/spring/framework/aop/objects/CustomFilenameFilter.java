package dmytro.learn.spring.framework.aop.objects;

import java.io.File;
import java.io.FilenameFilter;

public class CustomFilenameFilter implements FilenameFilter {

	private String extension;

	public CustomFilenameFilter(String extension) {
		super();
		this.extension = extension;
	}

	@Override
	public boolean accept(File dir, String name) {
		return name.toUpperCase().endsWith("." + extension.toUpperCase());
	}

}
