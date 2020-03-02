package com.eason.html.easyview.core.page;

import java.util.ArrayList;
import java.util.List;

import com.eason.html.easyview.core.WidgetStyle;
import com.eason.html.easyview.core.form.CustomButton;
import com.eason.html.easyview.core.form.table.TableItemLink;
import com.eason.html.easyview.core.utils.StringUtils;
import com.eason.html.easyview.core.widget.Attribute;
import com.eason.html.easyview.core.widget.Body;
import com.eason.html.easyview.core.widget.Head;
import com.eason.html.easyview.core.widget.Html;
import com.eason.html.easyview.core.widget.Link;
import com.eason.html.easyview.core.widget.Meta;
import com.eason.html.easyview.core.widget.Node;
import com.eason.html.easyview.core.widget.Script;
import com.eason.html.easyview.core.widget.Text;
import com.eason.html.easyview.core.widget.Title;
import com.eason.html.easyview.core.widget.css.CssStyle;

/**
 * <p>
 * </p>
 * 
 * @author DingLuoFeng 2020年1月21日 上午11:38:48
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020年1月21日
 * @modify by reason:{方法名}:{原因}
 */
public abstract class BasePage {

	protected Html html;

	protected Head head;

	protected Body body;

	private int toolbarStyle = WidgetStyle.ADD | WidgetStyle.REFLUSH | WidgetStyle.DEL;

    protected final List<CustomButton> customButtons = new ArrayList<>();

    protected final List<TableItemLink> tableItemsLinks = new ArrayList<>();

	public BasePage() {
		this("../icon/favicon.ico");
	}

	public BasePage(String iconPath) {
		super();
		head = Head.of();
		body = Body.of();
		html = Html.of().add(head).add(body);
		// set meta
		addMeta(meta().addAttribute(Attribute.of("charset", "utf-8")));
		addMeta(meta().setName("renderer").setContent("webkit|ie-comp|ie-stand").setRequiresEndTag(false));
		addMeta(meta().setName("viewport").setContent(
				"width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"));
		addMeta(meta().setName("format-detection").setContent("telephone=no").setRequiresEndTag(false));
		addMeta(meta().setHttpEquiv("X-UA-Compatible").setContent("IE=edge").setRequiresEndTag(false));

		// set iocn
		addLink(link().setType("image/x-icon").setRel("icon").setHref(iconPath));
		addLink(link().setType("image/x-icon").setRel("shortcut icon").setHref(iconPath));
	}

	protected final Link link() {
		return Link.of();
	}

	protected final Meta meta() {
		return Meta.of();
	}

	protected final Script script(Text... texts) {
		return Script.of(texts);
	}

	public Head addMeta(Meta meta) {
		head.add(meta);
		return head;
	}

	public Head addStyle(CssStyle cssStyle) {
		head.add(cssStyle);
		return head;
	}

	public Head addLink(Link link) {
		head.add(link);
		return head;
	}

	public Head addHeadScript(Script script) {
		head.add(script);
		return head;
	}

	public Body addBody(Node<?> container) {
		body.add(container);
		return body;
	}

	public Head addBodyScript(Script script) {
		body.add(script);
		return head;
	}

	public Head addHeadText(String text) {
		head.add(Text.of(text));
		return head;
	}

	public Head setPageTitle(String title) {
		head.add(Title.of().add(Text.of(title)));
		return head;
	}

	protected String html() {
		return "<!DOCTYPE html>" + System.lineSeparator() + html.html();
	}

	public int getToolbarStyle() {
		return toolbarStyle;
	}

	public void setToolbarStyle(int toolbarStyle) {
		this.toolbarStyle = toolbarStyle;
	}

	public void addCustomButton(CustomButton button) {
		if (!checkedUniqId(button)) {
			throw new IllegalArgumentException("button id " + button.getId() + " is already exsit!");
		}
		this.customButtons.add(button);
	}

	private final boolean checkedUniqId(CustomButton button) {
		for (CustomButton customButton : customButtons) {
			if (StringUtils.equalsIgnoreCase(customButton.getId(), button.getId())) {
				return false;
			}
		}
		return true;
	}

    public void addTableItemsLink(TableItemLink tableItemLink) {
        if (!checkedItemUniqId(tableItemLink)) {
            throw new IllegalArgumentException("TableItemLink id " + tableItemLink.id() + " is already exsit!");
        }
        this.tableItemsLinks.add(tableItemLink);
    }

    private final boolean checkedItemUniqId(TableItemLink tableItemLink) {
        for (TableItemLink itemLink : tableItemsLinks) {
            if (StringUtils.equalsIgnoreCase(itemLink.id(), tableItemLink.id())) {
                return false;
            }
        }
        return true;
    }

}
