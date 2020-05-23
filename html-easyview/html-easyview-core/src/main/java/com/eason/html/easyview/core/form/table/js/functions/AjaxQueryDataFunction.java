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
		script.add(Text.of("function queryData(url,params,needCreadsearchForm,tableData) {"));
		script.add(Text.of("    tableQueryView(url,params,tableData,needCreadsearchForm);"));
		script.add(Text.of("}"));

		script.add(Text.of("//ajax 远程查询"));
		script.add(Text.of("function showMessage(url,params) {"));
		script.add(Text.of("  $.ajax({"));
		script.add(Text.of("      url: url,"));
		script.add(Text.of("      method: 'post',"));
		script.add(Text.of("      contentType: \"application/x-www-form-urlencoded\","));
		script.add(Text.of("      //阻止深度序列化，向后台传送数组"));
		script.add(Text.of("      traditional: true,"));
		script.add(Text.of("      data: params,"));
		script.add(Text.of("      success: function (msg) {"));
		script.add(Text.of("          if (msg.status==0||msg.status==200) {"));
		script.add(Text.of("          	  if (msg.type==4) {"));
		script.add(Text.of("              	 layer.msg(msg.msg, {icon: 1, time: 1500});"));
		script.add(Text.of("              }else{"));
		script.add(Text.of("              	 layer.open({type: msg.type,title: msg.title,shadeClose: true,shade: 0.3,"));
		script.add(Text.of("              	       area: msg.area,maxmin: true,content: msg.msg}); "));
		script.add(Text.of("              }"));
		script.add(Text.of("          } else {"));
		script.add(Text.of("              layer.msg(msg.msg, {icon: 2, time: 1500});"));
		script.add(Text.of("          }"));
		script.add(Text.of("       }"));
		script.add(Text.of("  })"));
		script.add(Text.of("}"));
		
		script.add(Text.of("//ajax 远程查询"));
		script.add(Text.of("function formQuery(url,params,callback) {"));
		script.add(Text.of("  $.ajax({"));
		script.add(Text.of("      url: url,"));
		script.add(Text.of("      method: 'post',"));
		script.add(Text.of("      contentType: \"application/x-www-form-urlencoded\","));
		script.add(Text.of("      //阻止深度序列化，向后台传送数组"));
		script.add(Text.of("      traditional: true,"));
		script.add(Text.of("      data: params,"));
		script.add(Text.of("      success: function (msg) {"));
		script.add(Text.of("          if (msg.status==0||msg.status==200) {"));
		script.add(Text.of("          	  if (msg.type==4) {"));
		script.add(Text.of("              	 layer.msg(msg.msg, {icon: 1, time: 1500});"));
		script.add(Text.of("              }else{"));
		script.add(Text.of("              	 layer.open({type: msg.type,title: msg.title,shadeClose: true,shade: 0.3,"));
		script.add(Text.of("              	       area: msg.area,maxmin: true,content: msg.msg}); "));
		script.add(Text.of("              }"));
		script.add(Text.of("          } else {"));
		script.add(Text.of("              layer.msg(msg.msg, {icon: 2, time: 1500});"));
		script.add(Text.of("          }"));
		script.add(Text.of("          callback();"));
		script.add(Text.of("       }"));
		script.add(Text.of("  })"));
		script.add(Text.of("}"));
		
		script.add(Text.of("//ajax 远程查询"));
		script.add(Text.of("function showJsonQueryForm(url,querydata,callback) {"));
		script.add(Text.of("  $.ajax({"));
		script.add(Text.of("      url: url,"));
		script.add(Text.of("      method: 'post',"));
		script.add(Text.of("      contentType: \"application/json\","));
		script.add(Text.of("      //阻止深度序列化，向后台传送数组"));
		script.add(Text.of("      traditional: true,"));
		script.add(Text.of("      data: JSON.stringify(querydata),"));
		script.add(Text.of("      success: function (msg) {"));
		script.add(Text.of("          if (msg.status==0||msg.status==200) {"));
		script.add(Text.of("          	  if (msg.type==4) {"));
		script.add(Text.of("              	 layer.msg(msg.msg, {icon: 1, time: 1500});"));
		script.add(Text.of("          		 callback();"));
		script.add(Text.of("              }else{"));
		script.add(Text.of("      			 var msgtype = msg.type ==5?0:msg.type;"));
		script.add(Text.of("              	 layer.open({type: msgtype,title: msg.title,shadeClose: true,shade: 0.3,"));
		script.add(Text.of("              	       area: msg.area,maxmin: true,content: msg.msg}); "));
		script.add(Text.of("              }"));
		script.add(Text.of("          } else {"));
		script.add(Text.of("              layer.msg(msg.msg, {icon: 2, time: 1500});"));
		script.add(Text.of("          }"));
		script.add(Text.of("       }"));
		script.add(Text.of("  })"));
		script.add(Text.of("}"));

		return script.toString();
	}

}
