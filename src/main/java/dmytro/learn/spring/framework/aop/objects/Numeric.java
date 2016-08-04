package dmytro.learn.spring.framework.aop.objects;

import org.springframework.stereotype.Component;

import dmytro.learn.spring.framework.aop.annotations.ShowResult;

@Component
public class Numeric {

	@ShowResult
	public int getIntValue() {
		return 9;
	}

	public double getDoubleValue() {
		return 9.9;
	}

}
