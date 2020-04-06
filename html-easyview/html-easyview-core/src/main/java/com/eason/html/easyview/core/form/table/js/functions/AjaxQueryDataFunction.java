/**
 * 
 */
package com.eason.html.easyview.core.form.table.js.functions;

import com.eason.html.easyview.core.widget.Script;
import com.eason.html.easyview.core.widget.Text;

/**
 * @author dingluofeng
 *
 */
public class AjaxQueryDataFunction {

	public static String queryData(Script script) {
		script.add(Text.of("//ajax 远程查询表格展示"));
		script.add(Text.of("function queryData(url,params,needCreadsearchForm) {"));
		script.add(Text.of("  $.ajax({"));
		script.add(Text.of("      url: url,"));
		script.add(Text.of("      method: 'post',"));
		script.add(Text.of(" contentType: \"application/x-www-form-urlencoded\","));
		script.add(Text.of("      //阻止深度序列化，向后台传送数组"));
		script.add(Text.of("      traditional: true,"));
		script.add(Text.of("      data: params,"));
		script.add(Text.of("      success: function (msg) {"));
		script.add(Text.of("          if (msg.status==0||msg.status==200) {"));
		script.add(Text.of("              layer.msg(msg.msg, {icon: 1, time: 1500});"));
		script.add(Text.of("          } else {"));
		script.add(Text.of("              layer.msg(msg.msg, {icon: 2, time: 1500});"));
		script.add(Text.of("          }"));
		script.add(Text.of("          showTable(msg.data,needCreadsearchForm);"));
		script.add(Text.of("       }"));
		script.add(Text.of("  })"));
		script.add(Text.of("}"));

		script.add(Text.of("//ajax 远程查询"));
		script.add(Text.of("function showMessage(url,params) {"));
		script.add(Text.of("  $.ajax({"));
		script.add(Text.of("      url: url,"));
		script.add(Text.of("      method: 'post',"));
		script.add(Text.of(" contentType: \"application/x-www-form-urlencoded\","));
		script.add(Text.of("      //阻止深度序列化，向后台传送数组"));
		script.add(Text.of("      traditional: true,"));
		script.add(Text.of("      data: params,"));
		script.add(Text.of("      success: function (msg) {"));
		script.add(Text.of("          if (msg.status==0||msg.status==200) {"));
		script.add(Text.of("              layer.msg(msg.msg, {icon: 1, time: 1500});"));
		script.add(Text.of("          } else {"));
		script.add(Text.of("              layer.msg(msg.msg, {icon: 2, time: 1500});"));
		script.add(Text.of("          }"));
		script.add(Text.of("       }"));
		script.add(Text.of("  })"));
		script.add(Text.of("}"));

		return script.toString();
	}

}
