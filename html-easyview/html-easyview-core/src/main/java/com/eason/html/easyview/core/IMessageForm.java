package com.eason.html.easyview.core;

/**
 * 0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（msg）
 * 
 * @author dingluofeng
 *
 */
public interface IMessageForm {

	int INFO_FORM = 0;

	int PAGE_FORM = 1;

	int IFRAME_FORM = 2;

	int LOADING_FORM = 3;

	int MSG_FORM = 4;

}