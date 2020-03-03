package com.eason.html.easyview.core.page;

import java.util.List;

import com.eason.html.easyview.core.WidgetStyle;
import com.eason.html.easyview.core.form.dialog.BeanDialog;
import com.eason.html.easyview.core.form.table.BeanTableView;
import com.eason.html.easyview.core.form.table.CustomQueryBeanTable;
import com.eason.html.easyview.core.form.table.TableItemLink;
import com.eason.html.easyview.core.form.table.formatter.TableColMappingFormatterManager;
import com.eason.html.easyview.core.form.table.js.TableJsScript;
import com.eason.html.easyview.core.form.table.model.TableData;
import com.eason.html.easyview.core.form.table.model.UploadWidgetInfo;
import com.eason.html.easyview.core.widget.css.BaseStyle;

/**
 * 
 * @author dingluofeng
 *
 */
public class SingleTableViewPage extends BasePage {
	
	private TableColMappingFormatterManager colMappingFormatterManager;

	private boolean onlineResource = true;

	public static void main(String[] args) {
		new SingleTableViewPage("用户信息表").html("user", Object.class,Object.class);
	}

	public SingleTableViewPage(String title) {
		super();
		// set Title
		setPageTitle(title);
	}

	public void build(String baseUrl, Class<?> coClass,Class<?> beanClass) {
        // build header
        buildHeader();
        // BeanTableView
        TableData tableData = new TableData(coClass,beanClass, colMappingFormatterManager);
        tableData.baseUrl = baseUrl;
        
        UploadWidgetInfo uploadWidgetInfo = null;
        if ((WidgetStyle.IMPORT & getToolbarStyle()) != 0) {
            // upload
            uploadWidgetInfo = new UploadWidgetInfo();
            uploadWidgetInfo.setUploadId(tableData.btnPrefix + "_btn_upload");
            uploadWidgetInfo.setUploadUrl(baseUrl + "/fileimport");
            uploadWidgetInfo.setAcceptType("file");
            uploadWidgetInfo.setFileExts("txt|xlsx"); 
            uploadWidgetInfo.setLimitSize(10240);
        }
        
        tableData.customItemLinks.addAll(tableItemsLinks);
        // .add(TableItemLink.of("send", "发送", "glyphicon glyphicon-send", baseUrl +
        // "/send/upgrade"));
        
        BeanTableView tableView = BeanTableView.build(tableData, getToolbarStyle());
        addBody(tableView);
        // BeanDialog
        BeanDialog dialog = BeanDialog.open(tableData);
        addBody(dialog);
        //customContainer
        CustomQueryBeanTable customContainer = CustomQueryBeanTable.build(tableData,customButtons);
        if (customContainer != null) {
            addBody(customContainer);
        }
        // script
        addBodyScript(TableJsScript.of(tableData,uploadWidgetInfo, customButtons, colMappingFormatterManager == null ? null
                : colMappingFormatterManager.getTableColMappingFormatters()));
    }

	private void buildHeader() {
		if (onlineResource) {
			buildOnlineResourcesHeader();
		} else {
			buildLocalResourcesHeader();
		}
		// 全局base.css
		addStyle(BaseStyle.of());
	}

	 private void buildOnlineResourcesHeader() {
	        // jquery
			addHeadScript(script().setType("text/javascript").setSrc("https://unpkg.com/jquery@3.4.1/dist/jquery.min.js"));
			addHeadScript(script().setType("text/javascript").setSrc("https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"));

			//jquery-confirm
	        // addLink(link().stylesheet().setType("text/css").setHref("https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.4/jquery-confirm.min.css"));
	        // addHeadScript(script().setType("text/javascript").setSrc("https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.4/jquery-confirm.min.js"));

	        // layui
	        addLink(link().stylesheet().setType("text/css").setHref("https://www.layuicdn.com/layui-v2.5.6/css/layui.css"));
	        addHeadScript(script().setType("text/javascript").setSrc("https://www.layuicdn.com/layui-v2.5.6/layui.js"));

			//toastr
	        // addLink(link().stylesheet().setType("text/css").setHref("https://cdn.bootcss.com/toastr.js/2.1.4/toastr.min.css"));
	        // addHeadScript(script().setType("text/javascript").setSrc("https://cdn.bootcss.com/toastr.js/2.1.4/toastr.min.js"));

	        // bootstrap
			addLink(link().stylesheet().setType("text/css").setHref("https://unpkg.com/bootstrap@3.4.1/dist/css/bootstrap.min.css"));
			addHeadScript(script().setType("text/javascript").setSrc("https://unpkg.com/bootstrap@3.4.1/dist/js/bootstrap.min.js"));

	        // bootstrap table
			addHeadScript(script().setType("text/javascript").setSrc("https://unpkg.com/bootstrap-table@1.15.5/dist/bootstrap-table.min.js"));
			addHeadScript(script().setType("text/javascript").setSrc("https://unpkg.com/bootstrap-table@1.15.5/dist/bootstrap-table-locale-all.min.js"));
			addLink(link().stylesheet().setType("text/css").setHref("https://unpkg.com/bootstrap-table@1.15.5/dist/bootstrap-table.min.css"));

	        // ie 8 兼容
	        addHeadText("<!--[if lt IE 9]>");
	        addHeadScript(script().setSrc("https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"));
	        addHeadScript(script().setSrc("https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"));
	        addHeadText("<![endif]-->");
	    }
	    
	   private void buildLocalResourcesHeader() {
	        // jquery
			addHeadScript(script().setType("text/javascript").setSrc("../dependents/jquery/jquery.min.js"));
			addHeadScript(script().setType("text/javascript").setSrc("../dependents/popper/popper.min.js"));

	        // layui
	        addLink(link().stylesheet().setType("text/css").setHref("../dependents/layui/css/layui.css"));
	        addHeadScript(script().setType("text/javascript").setSrc("../dependents/layui/layui.js"));

	        // bootstrap
			addLink(link().stylesheet().setType("text/css").setHref("../dependents/bootstrap3/css/bootstrap.min.css"));
			addHeadScript(script().setType("text/javascript").setSrc("../dependents/bootstrap3/js/bootstrap.min.js"));

	        // bootstrap table
			addHeadScript(script().setType("text/javascript").setSrc("../dependents/bootstrap-table/bootstrap-table.min.js"));
			addHeadScript(script().setType("text/javascript").setSrc("../dependents/bootstrap-table/bootstrap-table-locale-all.min.js"));
			addLink(link().stylesheet().setType("text/css").setHref("../dependents/bootstrap-table/bootstrap-table.min.css"));

	        // ie 8 兼容
	        addHeadText("<!--[if lt IE 9]>");
	        addHeadScript(script().setSrc("../dependents/htm5-ie8/html5shiv.min.js"));
	        addHeadScript(script().setSrc("../dependents/htm5-ie8/respond.min.js"));
	        addHeadText("<![endif]-->");
	    }

	public String html(String baseUrl,Class<?> coClass, Class<?> beanClass) {
		build(baseUrl,coClass, beanClass);
		return super.html();
	}
	
	public void setColMappingFormatterManager(TableColMappingFormatterManager colMappingFormatterManager) {
        this.colMappingFormatterManager = colMappingFormatterManager;
    }

	public boolean isOnlineResource() {
		return onlineResource;
	}

	public void setOnlineResource(boolean onlineResource) {
		this.onlineResource = onlineResource;
	}

    public void seTableItemsLinks(List<TableItemLink> tableItemsLinks) {
        // TODO Auto-generated method stub

    }

}
