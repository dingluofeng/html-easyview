package com.eason.html.easyview.core.widget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Node<T extends Node> {

	public static final String INDENT = "    ";

	private List<Node> childNodeList = new ArrayList<>();
	private List<Attribute> attributeList = new ArrayList<>();
	private final String tagName;
	private Node parentNode;
	private boolean requiresEndTag = true;

	private String id;

	public Node(String tagName) {
		super();
		this.tagName = tagName;
	}

	public boolean getRequiresEndTag() {
		return this.requiresEndTag;
	}

	public T setRequiresEndTag(boolean requiresEndTag) {
		this.requiresEndTag = requiresEndTag;
		return (T) this;
	}

	public List<Node> getChildNodeList() {
		return childNodeList;
	}

	public Node getParentNode() {
		return this.parentNode;
	}

	public Node setParentNode(Node parentNode) {
		this.parentNode = parentNode;
		return this;
	}

	public T add(Node node) {
		if (node == null) {
			return (T) this;
		}

		node.setParentNode(this);
		this.childNodeList.add(node);
		return (T) this;
	}

	public T add(Node... node) {
		return add(Arrays.asList(node));
	}

	public T add(List<Node> nodeList) {
		for (Node node : nodeList) {
			add(node);
		}
		return (T) this;
	}

	public String html() {
		return Utils.joiningWithLineSeparator(this.contentList());
	}

	public List<String> contentList() {
		List<String> contentList = new ArrayList<>();
		contentList.add(Utils.startTag(this.tagName + this.attribute()));
		contentList.addAll(this.getChildContentList());

		if (this.requiresEndTag) {
			contentList.add(Utils.endTag(this.tagName));
		}

		return this.shrink(contentList);
	}

	private String attribute() {
		if (this.attributeList.size() == 0) {
			return "";
		}

		List<String> parts = Lists.transform(attributeList, new Function<Attribute, String>() {
			@Override
			public String apply(Attribute input) {
				return input.toString();
			}
		});

		StringBuilder builder = new StringBuilder();
		builder.append(" ");
		return Joiner.on(" ").appendTo(builder, parts).toString();
	}

	private List<String> getChildContentList() {

		List<List<String>> processIndent = Lists.transform(childNodeList, new Function<Node, List<String>>() {
			@Override
			public List<String> apply(Node input) {
				return processIndent(shrink(input.contentList()));
			}
		});

		List<String> ret = Lists.newArrayList();
		for (List<String> list : processIndent) {
			ret.addAll(list);
		}
		return ret;
		// return
		// listStream.stream().map(this::shrink).map(this::processIndent).flatMap(List::stream).collect(Collectors.toList());
	}

	private List<String> processIndent(List<String> list) {
		if (this.childNodeList.size() <= 1 && list.size() < 3) {
			return list;
		}

		return prependIndent(list);
	}

	private List<String> prependIndent(List<String> list) {
		return Lists.transform(list, new Function<String, String>() {
			@Override
			public String apply(String input) {
				return prependIndent(input);
			}
		});
		// return list.stream().map(this::prependIndent).collect(Collectors.toList());
	}

	private String prependIndent(String content) {
		return INDENT + content;
	}

	private String joining(List<String> list) {
		return Joiner.on("").join(list);
		// return list.stream().collect(Collectors.joining());
	}

	private List<String> joiningAndAsList(List<String> list) {
		return Arrays.asList(this.joining(list));
	}

	public List<String> shrink(List<String> list) {
		if (list.size() <= 3) {
			return this.joiningAndAsList(list);
		}

		return list;
	}

	public T addAttribute(Attribute newAttribute) {
		boolean found = false;
		for (Attribute attribute : attributeList) {
			if (attribute.getName().equals(newAttribute.getName())) {
				attribute.getValueSet().addAll(newAttribute.getValueSet());
				found = true;
				break;
			}
		}
		if (!found) {
			attributeList.add(newAttribute);
		}
//		this.attributeList.stream().filter(attribute -> attribute.getName().equals(newAttribute.getName())).findFirst()
//				.map(attribute -> attribute.getValueSet().addAll(newAttribute.getValueSet()))
//				.orElseGet(() -> this.attributeList.add(newAttribute));

		return (T) this;
	}

	public T addAttribute(String name, String value) {
		return addAttribute(Attribute.of(name, value));
	}

	public T setId(String id) {
		this.id = id;
		this.attributeList.add(Attribute.of("id", id));
		return (T) this;
	}

	public String getId() {
		return id;
	}

	public T addClass(String className) {
		return this.addAttribute(Attribute.of("class", className));
	}

	public T addClass(String... className) {
		for (String name : className) {
			this.addClass(name);
		}

		return (T) this;
	}

	public T addStyle(String style) {
		return this.addAttribute(Attribute.of("style", style));
	}

	public T setType(String type) {
		this.attributeList.add(Attribute.of("type", type));
		return (T) this;
	}

	public T setPlaceholder(String placeholder) {
		this.attributeList.add(Attribute.of("placeholder", placeholder));
		return (T) this;
	}

	public T setName(String name) {
		this.attributeList.add(Attribute.of("name", name));
		return (T) this;
	}

	public T setContent(String content) {
		this.attributeList.add(Attribute.of("content", content));
		return (T) this;
	}

	public T setHttpEquiv(String httpEquiv) {
		this.attributeList.add(Attribute.of("http-equiv", httpEquiv));
		return (T) this;
	}

	public T setValue(String value) {
		this.attributeList.add(Attribute.of("value", value));
		return (T) this;
	}

	public T setHref(String href) {
		this.attributeList.add(Attribute.of("href", href));
		return (T) this;
	}

	public T setRel(String rel) {
		this.attributeList.add(Attribute.of("rel", rel));
		return (T) this;
	}

	public T setFor(String value) {
		this.attributeList.add(Attribute.of("for", value));
		return (T) this;
	}

	public T stylesheet() {
		return this.setRel("stylesheet");
	}

	public T password() {
		return this.setType("password");
	}

	public T text() {
		return this.setType("text");
	}

	public T submit() {
		return this.setType("submit");
	}

	public T hidden() {
		return this.setType("hidden");
	}

	public Node setDisabled(boolean disabled) {
		if (disabled) {
			this.attributeList.add(Attribute.of("disabled"));
		}

		return (T) this;
	}

	public T setSrc(String src) {
		this.attributeList.add(Attribute.of("src", src));
		return (T) this;
	}
}
