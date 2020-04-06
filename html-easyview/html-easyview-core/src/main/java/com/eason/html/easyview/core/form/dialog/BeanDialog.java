package com.eason.html.easyview.core.form.dialog;

import java.util.List;

import com.eason.html.easyview.core.form.FormInput;
import com.eason.html.easyview.core.form.table.model.TableData;
import com.eason.html.easyview.core.widget.Attribute;
import com.eason.html.easyview.core.widget.Button;
import com.eason.html.easyview.core.widget.Div;
import com.eason.html.easyview.core.widget.HiddenInput;
import com.eason.html.easyview.core.widget.Hn;
import com.eason.html.easyview.core.widget.Node;
import com.eason.html.easyview.core.widget.Span;
import com.eason.html.easyview.core.widget.Text;

/**
 * <p>
 * </p>
 * 
 * @author DingLuoFeng 2020年1月22日 下午6:01:40
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020年1月22日
 * @modify by reason:{方法名}:{原因}
 */
public class BeanDialog extends Node<BeanDialog> {

	private BeanDialog() {
		super("div");
		addClass("modal fade");
	}

	public static BeanDialog open(TableData tableData) {
		String id = tableData.btnPrefix + "_addAndUpdate";
		String labelledby = id + "Label";
		BeanDialog beanDialog = new BeanDialog().setId(id);
		beanDialog.addAttribute(Attribute.of("tabindex", "-1"));
		beanDialog.addAttribute(Attribute.of("role", "dialog"));
		beanDialog.addAttribute(Attribute.of("aria-labelledby", labelledby));
		// <div class="modal-dialog" role="document">
		Div modalDialog = Div.of().addClass("modal-dialog");
		modalDialog.addAttribute(Attribute.of("role", "document"));
		beanDialog.add(modalDialog);
		// <div class="modal-content">
		Div modalContent = Div.of().addClass("modal-content");
		modalDialog.add(modalContent);
		// <div class="modal-header">
		DialogHeader header = DialogHeader.header(labelledby, "信息");
		modalContent.add(header);

		List<FormInput<?>> formInputs = tableData.formInputs;
		// TODO
		DialogBody body = DialogBody.body(formInputs);
		body.add(HiddenInput.of(tableData.btnPrefix + "_opt_type", "add"));
		modalContent.add(body);
		// <div class="modal-footer">
		DialogFooter footer = DialogFooter.footer(tableData.btnPrefix + "_btn_add_update_submit");
		modalContent.add(footer);
		return beanDialog;
	}

	private static class DialogBody extends Node<DialogBody> {

		private DialogBody() {
			super("div");
			addClass("modal-body");// 标准弹框
			// addClass("modal-body modal-lg");
			// addClass("modal-body modal-sm");
		}

		private static DialogBody body(List<FormInput<?>> inputs) {
			DialogBody dialogBody = new DialogBody();
			for (FormInput<?> formInput : inputs) {
				dialogBody.add(formInput);
			}
			return dialogBody;
		}
	}

	private static class DialogHeader extends Node<DialogHeader> {
		private DialogHeader() {
			super("div");
			addClass("modal-header").addStyle(
					"-moz-border-radius: 5px;-webkit-border-radius: 5px;border-top-left-radius:5px;border-top-right-radius: 5px;background-color: #f5f5f5;");
		}

		private static DialogHeader header(String labelId, String title) {
			DialogHeader dialogHeader = new DialogHeader();
			dialogHeader.add(
					Button.of().setType("button").addClass("close").addAttribute(Attribute.of("data-dismiss", "modal"))
							.addAttribute(Attribute.of("aria-label", "Close"))
							.add(Span.of().addAttribute(Attribute.of("aria-hidden", "true")).add(Text.of("x"))));
			dialogHeader.add(Hn.of(4).addClass("modal-title").setId(labelId).add(Text.of(title)));
			return dialogHeader;
		}
	}

	private static class DialogFooter extends Node<DialogFooter> {

		private DialogFooter() {
			super("div");
			addClass("modal-footer");
		}

		private static DialogFooter footer(String saveBtnId) {
			DialogFooter dialogFooter = new DialogFooter();

			Button closeBtn = Button.of().setType("button").addClass("btn btn-default btn-sm")
					.addAttribute(Attribute.of("data-dismiss", "modal"));
			closeBtn.add(
					Span.of().addClass("glyphicon glyphicon-remove").addAttribute(Attribute.of("aria-hidden", "true")));
			closeBtn.add(Text.of("关闭"));

			Button saveBtn = Button.of().setType("button").setId(saveBtnId).addClass("btn btn-primary btn-sm")
					.addAttribute(Attribute.of("data-dismiss", "modal"));
			saveBtn.add(Span.of().addClass("glyphicon glyphicon-floppy-disk")
					.addAttribute(Attribute.of("aria-hidden", "true")));
			saveBtn.add(Text.of("保存"));

			dialogFooter.add(closeBtn);
			dialogFooter.add(saveBtn);
			return dialogFooter;
		}
	}
}
