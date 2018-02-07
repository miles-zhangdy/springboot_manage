package com.zdy.util.excel;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import com.zdy.exception.MyException;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;

public class ExcelUtil {

	public static void main(String[] args) throws Exception {
		String[] a = { "编号", "姓名", "年龄", "身高", "体重", "地址", "婚姻状况" };
		List<LinkedHashMap<String, String>> excelContent = new ArrayList<LinkedHashMap<String, String>>();
		LinkedHashMap<String, String> map = null;
		for (int i = 1; i < 10; i++) {
			map = new LinkedHashMap<String, String>();
			map.put("编号", "" + i);
			map.put("姓名", "张三" + i);
			map.put("年龄", "23");
			map.put("身高", "175");
			map.put("体重", "85");
			map.put("地址", "上海");
			map.put("婚姻状况", "未婚");
			excelContent.add(map);
		}

		ExcelUtil.createExcel("婚姻状况", "d:\\", "test1", new Date(), a, excelContent);
	}

	public static void createExcel(String titleName, String filePath, String fileName, Date fileDate, String[] titles,
			List<LinkedHashMap<String, String>> excelContent) throws Exception {
		File tempFile = new File(filePath + System.getProperties().getProperty("file.separator") + "source");
		if (!tempFile.exists())
			tempFile.mkdirs();
		File targetFile = null;
		BufferedOutputStream bos = null;
		jxl.write.WritableWorkbook wwb = null;
		jxl.write.WritableSheet ws = null;
		jxl.write.WritableCellFormat titleFormat = null;
		jxl.write.WritableCellFormat pageHeaderFormat = null;
		jxl.write.WritableCellFormat contentFormat = null;
		jxl.write.WritableCellFormat contentFormatLe = null;
		jxl.write.WritableCellFormat wcfN = null;
		jxl.write.WritableCellFormat wcfF = null;
		jxl.write.WritableCellFormat wcfFbfh = null;
		jxl.write.NumberFormat dis = null;
		jxl.write.NumberFormat bfhdis = null;
		int count = 0;
		fileName = filePath + System.getProperties().getProperty("file.separator") + "source"
				+ System.getProperties().getProperty("file.separator") + fileName + ".xls";
		targetFile = new File(fileName);
		bos = new BufferedOutputStream(new FileOutputStream(targetFile));
		wwb = Workbook.createWorkbook(bos);// 创建工作簿
		ws = wwb.createSheet(titleName, 0);// 创建一个工作sheet
		ws.getSettings().setShowGridLines(false); // 去掉excel的网格
		try {

			// 标题
			jxl.write.WritableFont title = new jxl.write.WritableFont(WritableFont.createFont("宋体"), 18,
					WritableFont.BOLD, false);// 标题字体格式
			title.setColour(Colour.GREEN);
			title.setUnderlineStyle(UnderlineStyle.SINGLE);
			titleFormat = new jxl.write.WritableCellFormat(title);// 标题单元格格式
			titleFormat.setAlignment(Alignment.CENTRE);
			titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

			// 表头单元格格式
			jxl.write.WritableFont pageHeader = new jxl.write.WritableFont(WritableFont.createFont("宋体"), 9,
					WritableFont.BOLD, false);
			pageHeaderFormat = new jxl.write.WritableCellFormat(pageHeader);
			pageHeaderFormat.setAlignment(Alignment.CENTRE);
			pageHeaderFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			pageHeaderFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.GREEN);

			// 数据格式
			jxl.write.WritableFont content = new jxl.write.WritableFont(WritableFont.createFont("宋体"), 12,
					WritableFont.NO_BOLD, false);
			contentFormat = new jxl.write.WritableCellFormat(content);// 正文单元格格式
																		// 居右
			contentFormat.setAlignment(Alignment.RIGHT);
			contentFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			contentFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.GREEN);

			contentFormatLe = new jxl.write.WritableCellFormat(content);// 非数字居左
			contentFormatLe.setAlignment(Alignment.LEFT);
			contentFormatLe.setVerticalAlignment(VerticalAlignment.CENTRE);
			contentFormatLe.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.GREEN);

			/*
			 * contentFormatBold = new jxl.write.WritableCellFormat( new
			 * jxl.write.WritableFont(WritableFont.createFont("宋体"), 12,
			 * WritableFont.BOLD, false));
			 * contentFormatBold.setAlignment(Alignment.RIGHT);
			 * contentFormatBold.setVerticalAlignment(VerticalAlignment.CENTRE);
			 * contentFormatBold.setBorder(Border.ALL, BorderLineStyle.THIN,
			 * Colour.GREEN);
			 * 
			 * contentFormatBold1 = new jxl.write.WritableCellFormat( new
			 * jxl.write.WritableFont(WritableFont.createFont("宋体"), 12,
			 * WritableFont.BOLD, false));
			 * contentFormatBold1.setAlignment(Alignment.LEFT);
			 * contentFormatBold1.setVerticalAlignment(VerticalAlignment.CENTRE)
			 * ; contentFormatBold1.setBorder(Border.ALL, BorderLineStyle.THIN,
			 * Colour.GREEN);
			 */

			jxl.write.NumberFormat nf = new jxl.write.NumberFormat("0.00####");// 数值格式化
			wcfN = new jxl.write.WritableCellFormat(nf);
			wcfN.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.GREEN);

			bfhdis = new jxl.write.NumberFormat("0.0000%");// 百分比格式化
			wcfFbfh = new jxl.write.WritableCellFormat(bfhdis);
			wcfFbfh.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.GREEN);

			dis = new jxl.write.NumberFormat("0.0000");// 百分比格式化
			wcfF = new jxl.write.WritableCellFormat(dis);
			wcfF.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.GREEN);
			// 合并标题单元格
			ws.mergeCells(0, 0, titles.length - 1, 0);
			ws.mergeCells(0, 1, titles.length - 1, 1);
			ws.mergeCells(0, 2, titles.length - 1, 2);

			// 标题高度格式
			ws.setRowView(0, 600);
			// 添加标题
			ws.addCell(new jxl.write.Label(0, 0, titleName, titleFormat));

			// 创建空白单元行
			ws.addCell(new Label(0, 1, ""));

			// 创建日期单元行
			jxl.write.WritableFont date = new jxl.write.WritableFont(WritableFont.createFont("宋体"), 12,
					WritableFont.NO_BOLD, false);// 字体格式
			WritableCellFormat dateFormat = new WritableCellFormat(date);
			ws.addCell(new Label(0, 2, new SimpleDateFormat("yyyy-MM-dd").format(fileDate), dateFormat));
			// 列标题
			for (int k = 0; k < titles.length; k++) {
				ws.addCell(new Label(k, 3, titles[k], pageHeaderFormat));
				ws.addCell(new Label(k, 4, titles[k], pageHeaderFormat));
				ws.setColumnView(k, 13);
				ws.mergeCells(k, 3, k, 4);
			}
			count = 4;
			// excel内容
			if (excelContent != null) {
				for (LinkedHashMap<String, String> map : excelContent) {
					int k = 0;
					Set<String> set = map.keySet();
					++count;
					for (String key : set) {
						ws.addCell(new Label(k++, count, map.get(key), contentFormatLe));
					}
				}
			}

		} catch (Exception e) {
			throw new MyException("生成EXCEL文件出错", e);
		} finally {
			wwb.write();
			wwb.close();
			bos.close();
		}

	}

}
