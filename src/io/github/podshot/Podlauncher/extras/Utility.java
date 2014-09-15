package io.github.podshot.Podlauncher.extras;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
public @interface Utility {
	
	UtilityType value();
	
	public enum UtilityType {
		COMPATIBILITY, DEVELOPMENT, QUICK_FIX, OTHER;
	}

}
