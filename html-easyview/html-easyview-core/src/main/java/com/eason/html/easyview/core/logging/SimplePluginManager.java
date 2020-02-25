package com.eason.html.easyview.core.logging;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class SimplePluginManager<T> {

	private List<T> list = new ArrayList<T>();

	public SimplePluginManager(String... classNames) throws RuntimeException {
		if (classNames != null)
			for (String className : classNames)
				loadT(className);
	}

	public SimplePluginManager(Class<? extends T>... classNames) throws RuntimeException {
		if (classNames != null)
			for (Class<? extends T> TClass : classNames)
				loadT(TClass);
	}

	public T get() {
		for (T T : list)
			return (T) T;
		return null;
	}

	public List<T> gets() {
		List<T> aList = new ArrayList<T>(list.size());
		for (T T : list)
			aList.add((T) T);
		return aList;
	}

	protected void loadT(Class<? extends T> TClass) throws RuntimeException {
		if (TClass != null)
			try {
				list.add((T) TClass.newInstance());
			} catch (Throwable e) {
				throw new RuntimeException(TClass.getName(), e);
			}
	}

	private void loadT(String TClassName) throws RuntimeException {
		try {
			if (TClassName != null)
				loadT((Class<? extends T>) Class.forName(TClassName));
		} catch (Throwable e) {
		}
	}
}
