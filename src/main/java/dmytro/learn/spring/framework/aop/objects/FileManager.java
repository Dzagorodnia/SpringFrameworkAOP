package dmytro.learn.spring.framework.aop.objects;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

import dmytro.learn.spring.framework.aop.annotations.ShowResult;
import dmytro.learn.spring.framework.aop.annotations.ShowTime;
import dmytro.learn.spring.framework.aop.interfaces.Manager;

@Component
public class FileManager implements Manager {

	@Override
	@ShowResult
	public Set<String> getExtensionList(String folder) {
		File dir = new File(folder);

		Set<String> extensionList = new TreeSet<>();

		for (String fileName : dir.list()) {

			File file = new File(dir.getAbsolutePath() + "\\" + fileName);

			int i = fileName.lastIndexOf(".");
			if (file.isFile() && i != -1) {
				extensionList.add(fileName.substring(i + 1, fileName.length()).toLowerCase());
			}

		}

		return extensionList;

	}

	@Override
	@ShowResult
	@ShowTime
	public Map<String, Integer> getExtensionCount(String folder) {

		File dir = new File(folder);

		Map<String, Integer> extensionCount = new HashMap<>();

		for (String extension : getExtensionList(folder)) {
			FilenameFilter filenameFilter = new CustomFilenameFilter(extension);
			extensionCount.put(extension, dir.listFiles(filenameFilter).length);
		}

		return extensionCount;

	}

}
