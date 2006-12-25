package net.lojjic.xul.impl;

public interface AttributeHandler<T> {
	
	String get(T target);
	
	void set(T target, String value);
	
}
