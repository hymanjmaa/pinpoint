package com.navercorp.pinpoint.common.util;

/**
 * @author emeroad
 */
public class NullNextElementAction<E> implements NextElementAction<E> {

	@Override
	public E nextElement() {
		return null;
	}
}
