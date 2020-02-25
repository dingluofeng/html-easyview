
package com.eason.html.easyview.core.utils;

import java.util.Collection;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class CollectionUtils {

	/**
	 * 检查集合是否为空: <code>null</code>或为空集合。
	 * 
	 * <pre>
	 * CollectionUtil.isEmpty(null)      = true
	 * CollectionUtil.isEmpty(new ArrayList<?>())       = true
	 * List<String> a = new ArrayList<String>(); a.add("1");CollectionUtil.isEmpty(a) = false
	 * </pre>
	 * 
	 * @param collection 要检查的集合
	 * @return 如果为空, 则返回<code>true</code>
	 */
	public static boolean isEmpty(Collection collection) {
		return (collection == null || collection.isEmpty());
	}

	/**
	 * 检查集合是否为非空: <code>null</code>或为空集合。
	 * 
	 * <pre>
	 * CollectionUtil.isNotEmpty(null)      = false
	 * CollectionUtil.isNotEmpty(new ArrayList<?>())       = false
	 * List<String> a = new ArrayList<String>(); a.add("1");CollectionUtil.isNotEmpty(a) = true
	 * </pre>
	 * 
	 * @param collection 要检查的集合
	 * @return 如果为非空, 则返回<code>true</code>
	 */
	public static boolean isNotEmpty(Collection collection) {
		return !isEmpty(collection);
	}

	/**
	 * 检查Map是否为空: <code>null</code>或为空Map。
	 * 
	 * <pre>
	 * CollectionUtil.isEmpty(null)      = ture
	 * CollectionUtil.isEmpty(new HashMap<?,?>())       = true
	 *  Map<String, String> a = new HashMap<String, String>(); a.put("a", "a"); CollectionUtil.isEmpty(a) = false
	 * </pre>
	 * 
	 * @param map 要检查的map
	 * @return 如果为空, 则返回<code>true</code>
	 */
	public static boolean isEmpty(Map map) {
		return (map == null || map.isEmpty());
	}

	/**
	 * 检查Map是否为非空: <code>null</code>或为空Map。
	 * 
	 * <pre>
	 * CollectionUtil.isNotEmpty(null)      = false
	 * CollectionUtil.isNotEmpty(new HashMap<?,?>())       = false
	 *  Map<String, String> a = new HashMap<String, String>(); a.put("a", "a"); CollectionUtil.isNotEmpty(a) = true
	 * </pre>
	 * 
	 * @param map 要检查的map
	 * @return 如果为非空, 则返回<code>true</code>
	 */
	public static boolean isNotEmpty(Map map) {
		return !isEmpty(map);
	}

}
