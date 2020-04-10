package com.eason.html.easyview.core.form.table.formatter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.eason.html.easyview.core.logging.Log;
import com.eason.html.easyview.core.logging.LogFactory;
import com.eason.html.easyview.core.utils.ServiceFinder;
import com.eason.html.easyview.core.utils.StringUtils;

/**
 * <p>
 * </p>
 * 
 * @author DingLuoFeng 2020年2月1日 下午7:18:20
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020年2月1日
 * @modify by reason:{方法名}:{原因}
 */
public final class TableColMappingFormatterManager {

    private static final String FORMATTER = "Formatter";

    private Log logger = LogFactory.getLog(TableColMappingFormatterManager.class);

	private final List<TableColMappingFormatter> tableColMappingFormatters = new ArrayList<>();

	private ServiceFinder serviceFinder;

	private AtomicBoolean isInjectExtension = new AtomicBoolean();

    public String addMappingFormatter(String field, String jsonMapping) {
        String functionName = field + FORMATTER;
        CustomMappingFormatter tableColMappingFormatter = new CustomMappingFormatter(functionName, jsonMapping);
        addTableColMappingFormatter(tableColMappingFormatter);
        return tableColMappingFormatter.functionName();
    }

	public String addMappingFormatter(Class<? extends TableColMappingFormatter> formatterClass) {
		TableColMappingFormatter tableColMappingFormatter;
		try {
			tableColMappingFormatter = formatterClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		addTableColMappingFormatter(tableColMappingFormatter);
		return tableColMappingFormatter.functionName();
	}

	public void addTableColMappingFormatter(TableColMappingFormatter mappingFormatter) {
		if (!checkUniqFormatter(mappingFormatter)) {
			logger.warnf(mappingFormatter.functionName() + " is already exsit! please config another functionName ");
			return;
		}
		tableColMappingFormatters.add(mappingFormatter);
	}

	private final boolean checkUniqFormatter(TableColMappingFormatter mappingFormatter) {
		for (TableColMappingFormatter formatter : tableColMappingFormatters) {
			if (StringUtils.equalsIgnoreCase(formatter.functionName(), mappingFormatter.functionName())) {
				return false;
			}
		}
		return true;
	}

	public List<TableColMappingFormatter> getTableColMappingFormatters() {
		return tableColMappingFormatters;
	}

	public ServiceFinder getServiceFinder() {
		return serviceFinder;
	}

	public void setServiceFinder(ServiceFinder serviceFinder) {
		this.serviceFinder = serviceFinder;
	}

	public void initInjectExtension() {
		if (isInjectExtension.compareAndSet(false, true)) {
			if (serviceFinder != null) {
				for (TableColMappingFormatter mappingFormatter : tableColMappingFormatters) {
					serviceFinder.injectExtension(mappingFormatter);
				}
			}
		}
	}
}
