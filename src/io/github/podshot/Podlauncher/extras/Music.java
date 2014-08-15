package io.github.podshot.Podlauncher.extras;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.TYPE})
public @interface Music {
	
	String songArtist() default "Darude";
	String songName() default "Sandstorm";
	String songUrl() default "";

}
