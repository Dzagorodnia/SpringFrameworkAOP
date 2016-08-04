package dmytro.learn.spring.framework.aop.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dmytro.learn.spring.framework.aop.objects.FileManager;
import dmytro.learn.spring.framework.aop.objects.Numeric;

public class AOPMain {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

		Numeric numeric = (Numeric) context.getBean("numeric");
		numeric.getDoubleValue();
		numeric.getIntValue();

		FileManager fileManager = (FileManager) context.getBean("fileManager");

		String folder = "C:\\Users\\Dmytro\\Downloads";

		fileManager.getExtensionCount(folder);
		fileManager.getExtensionList(folder);

	}

}
