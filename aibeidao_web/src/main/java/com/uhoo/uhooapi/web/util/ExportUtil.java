package com.uhoo.uhooapi.web.util;




/**
 * 数字格式化帮助类
 * @author yang.yun
 *
 */
public class ExportUtil {

//	public static void generateIncomeExcelToTempDir(Pagination page,String tmpDirPath,String tmpZipFilePath) {
//		System.out.println("---------------tmpDirPath:"+tmpDirPath);
//		System.out.println("---------------tmpZipFilePath:"+tmpZipFilePath);
//		File tmpDir = new File(tmpDirPath);
//		if(!tmpDir.exists()){
//			tmpDir.mkdirs();
//		}
//		
//		HSSFWorkbook workbook = new HSSFWorkbook();// 创建一个Excel文件
//		HSSFSheet sheet = workbook.createSheet();// 创建一个Excel的Sheet
//		//标题加粗样式
//		HSSFFont titlefont = workbook.createFont();   
//		titlefont.setFontName("黑体");
//		titlefont.setFontHeightInPoints((short) 10);// 字体大小
//		titlefont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗 
//		HSSFCellStyle titlestyle = workbook.createCellStyle();
//		titlestyle.setFont(titlefont);
//		
//		HSSFRow crow = null;
//		
//		//创建title显示
//		crow = sheet.createRow(0);
//		
//		crow.createCell(0).setCellStyle(titlestyle);
//		crow.getCell(0).setCellValue(new HSSFRichTextString("编号"));
//		
//		crow.createCell(1).setCellStyle(titlestyle);
//		crow.getCell(1).setCellValue(new HSSFRichTextString("订单编号"));
//		
//		crow.createCell(2).setCellStyle(titlestyle);
//		crow.getCell(2).setCellValue(new HSSFRichTextString("年份"));
//		
//		crow.createCell(3).setCellStyle(titlestyle);
//		crow.getCell(3).setCellValue(new HSSFRichTextString("月份"));
//		
//		crow.createCell(4).setCellStyle(titlestyle);
//		crow.getCell(4).setCellValue(new HSSFRichTextString("服务人员"));
//		
//		crow.createCell(5).setCellStyle(titlestyle);
//		crow.getCell(5).setCellValue(new HSSFRichTextString("联系电话"));
//		
//		crow.createCell(6).setCellStyle(titlestyle);
//		crow.getCell(6).setCellValue(new HSSFRichTextString("订单金额"));
//		
//		crow.createCell(7).setCellStyle(titlestyle);
//		crow.getCell(7).setCellValue(new HSSFRichTextString("成交金额"));
//		
//		crow.createCell(8).setCellStyle(titlestyle);
//		crow.getCell(8).setCellValue(new HSSFRichTextString("支付方式"));
//		
//		crow.createCell(9).setCellStyle(titlestyle);
//		crow.getCell(9).setCellValue(new HSSFRichTextString(" 完成时间"));
//		if(page != null && page.getDatas() != null && page.getDatas().size() > 0){
////			@SuppressWarnings("unchecked")
////			List<UhIncomeBill> incomebills = (List<UhIncomeBill>) page.getDatas();
////			for(int i = 0 ; i < incomebills.size() ; i++){
////				UhIncomeBill incomebill=incomebills.get(i);
////				crow = sheet.createRow(i+1);
////				crow.createCell(0).setCellStyle(titlestyle);
////				crow.getCell(0).setCellValue(new HSSFRichTextString((i+1)+""));
////				
////				crow.createCell(1).setCellStyle(titlestyle);
////				if(incomebill.getOrder()!=null){
////					crow.getCell(1).setCellValue(new HSSFRichTextString(incomebill.getOrder().getOrderid()));
////				}else{
////					crow.getCell(1).setCellValue(new HSSFRichTextString(incomebill.getOrder_number()));
////				}
////				
////				crow.createCell(2).setCellStyle(titlestyle);
////				crow.getCell(2).setCellValue(new HSSFRichTextString(incomebill.getByear()+"年"));
////				
////				crow.createCell(3).setCellStyle(titlestyle);
////				crow.getCell(3).setCellValue(new HSSFRichTextString(incomebill.getBmonth()+"月"));
////				
////				crow.createCell(4).setCellStyle(titlestyle);
////				if(incomebill.getUser()!=null){
////					crow.getCell(4).setCellValue(new HSSFRichTextString(incomebill.getUser().getUsername()));
////				}
////				
////				crow.createCell(5).setCellStyle(titlestyle);
////				if(incomebill.getUser()!=null){
////					crow.getCell(5).setCellValue(new HSSFRichTextString(incomebill.getUser().getPhone()));
////				}
////				
////				crow.createCell(6).setCellStyle(titlestyle);
////				if(incomebill.getOrder()!=null){
////					crow.getCell(6).setCellValue(new HSSFRichTextString(DecimalUtil.formatToString(incomebill.getOrder().getPrice())));
////				}
////				
////				crow.createCell(7).setCellStyle(titlestyle);
////				crow.getCell(7).setCellValue(new HSSFRichTextString(DecimalUtil.formatToString(incomebill.getIncome())));
////				
////				crow.createCell(8).setCellStyle(titlestyle);
////				if(incomebill.getPay_type().equals(Options.ORDER_PAYM_ALIPAY)){
////					crow.getCell(8).setCellValue(new HSSFRichTextString("支付宝"));
////				}else if(incomebill.getPay_type().equals(Options.ORDER_PAYM_WEIXIN)){
////					crow.getCell(8).setCellValue(new HSSFRichTextString("微信"));
////				}else if(incomebill.getPay_type().equals(Options.ORDER_PAYM_UCOIN)){
////					crow.getCell(8).setCellValue(new HSSFRichTextString("U币"));
////				}else if(incomebill.getPay_type().equals(Options.ORDER_PAYM_CASH)){
////					crow.getCell(8).setCellValue(new HSSFRichTextString("现金"));
////				}
////				
////				crow.createCell(9).setCellStyle(titlestyle);
////				crow.getCell(9).setCellValue(new HSSFRichTextString(DateFormatUtil.date2Str(incomebill.getIncome_time())));
//			}
//		}
//		
//		String xlsFileName = "incomelist_"+System.currentTimeMillis();
//		//生成订单文件
//		File orderFile = new File(tmpDir.getAbsolutePath() + File.separator + xlsFileName + ".xls");
//		OutputStream os = null;
//		try {
//			orderFile.createNewFile();
//			os = new FileOutputStream(orderFile);
//			workbook.write(os);
//			os.flush();
//		} catch (IOException e) {
//			System.out.println("create xls:{} file fail");
//		} finally{
//			if(os != null){
//				try {
//					os.close();
//				} catch (IOException e) {
//					System.out.println("close xls:{} file fail");
//				}
//			}
//		}
//		//打包所有订单文件为zip文件
//		String zippath = tmpZipFilePath;
//		File zipFile = new File(zippath);
////		if(!zipFile.exists()){
////			try {
////				zipFile.createNewFile();
////			} catch (IOException e) {
////				e.printStackTrace();
////			}
////		}
//		ZipOutputStream zos = null;
//		try {
//			zipFile.createNewFile();
//			zos = new ZipOutputStream(new FileOutputStream(zipFile));
//			//找到要打包的文件
//			File[] orderFiles = tmpDir.listFiles();
//			for(int i = 0; i < orderFiles.length ; i ++){
//				FileInputStream fis = new FileInputStream(orderFiles[i]);  
//				zos.putNextEntry(new ZipEntry(orderFiles[i].getName()));
//				int len;
//				byte[] buffer = new byte[1024];
//				//读入需要下载的文件的内容，打包到zip文件
//				while((len = fis.read(buffer)) > 0) {
//					zos.write(buffer, 0, len);
//				}
//				zos.closeEntry();
//				fis.close();
//			}
//			zos.flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//			System.out.println("create zip file fail");
//		} finally{
//			if(zos != null){
//				try {
//					zos.close();
//				} catch (IOException e) {
//					System.out.println("close zip file fail");
//				}
//			}
//		}
//		//删除临时目录
//		try {
//			FileUtils.deleteDirectory(tmpDir);
//		} catch (IOException e) {
//			System.out.println("delete tempdir:{} fail");
//		}
//	}
//
//	public static void generateUserbillExcelToTempDir(Pagination page,String tmpDirPath,String tmpZipFilePath) {
//		File tmpDir = new File(tmpDirPath);
//		if(!tmpDir.exists()){
//			tmpDir.mkdirs();
//		}
//		
//		HSSFWorkbook workbook = new HSSFWorkbook();// 创建一个Excel文件
//		HSSFSheet sheet = workbook.createSheet();// 创建一个Excel的Sheet
//		//标题加粗样式
//		HSSFFont titlefont = workbook.createFont();   
//		titlefont.setFontName("黑体");
//		titlefont.setFontHeightInPoints((short) 10);// 字体大小
//		titlefont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗 
//		HSSFCellStyle titlestyle = workbook.createCellStyle();
//		titlestyle.setFont(titlefont);
//		
//		HSSFRow crow = null;
//		
//		//创建title显示
//		crow = sheet.createRow(0);
//		
//		crow.createCell(0).setCellStyle(titlestyle);
//		crow.getCell(0).setCellValue(new HSSFRichTextString("编号"));
//		
//		crow.createCell(1).setCellStyle(titlestyle);
//		crow.getCell(1).setCellValue(new HSSFRichTextString("用户"));
//		
//		crow.createCell(2).setCellStyle(titlestyle);
//		crow.getCell(2).setCellValue(new HSSFRichTextString("账单类型"));
//		
//		crow.createCell(3).setCellStyle(titlestyle);
//		crow.getCell(3).setCellValue(new HSSFRichTextString("成交金额"));
//		
//		crow.createCell(4).setCellStyle(titlestyle);
//		crow.getCell(4).setCellValue(new HSSFRichTextString("联系电话"));
//		
//		crow.createCell(5).setCellStyle(titlestyle);
//		crow.getCell(5).setCellValue(new HSSFRichTextString("年份"));
//		
//		crow.createCell(6).setCellStyle(titlestyle);
//		crow.getCell(6).setCellValue(new HSSFRichTextString("月份"));
//		
//		crow.createCell(7).setCellStyle(titlestyle);
//		crow.getCell(7).setCellValue(new HSSFRichTextString("订单编号"));
//		
//		crow.createCell(8).setCellStyle(titlestyle);
//		crow.getCell(8).setCellValue(new HSSFRichTextString("预约服务"));
//		
//		crow.createCell(9).setCellStyle(titlestyle);
//		crow.getCell(9).setCellValue(new HSSFRichTextString("支付方式"));
//		
//		crow.createCell(10).setCellStyle(titlestyle);
//		crow.getCell(10).setCellValue(new HSSFRichTextString("完成时间"));
//		if(page != null && page.getDatas() != null && page.getDatas().size() > 0){
//			@SuppressWarnings("unchecked")
//			List<UhUserBill> bills = (List<UhUserBill>) page.getDatas();
//			for(int i = 0 ; i < bills.size() ; i++){
//				UhUserBill incomebill=bills.get(i);
//				crow = sheet.createRow(i+1);
//				crow.createCell(0).setCellStyle(titlestyle);
//				crow.getCell(0).setCellValue(new HSSFRichTextString((i+1)+""));
//				
//				crow.createCell(1).setCellStyle(titlestyle);
//				if(incomebill.getUser()!=null){
//					crow.getCell(1).setCellValue(new HSSFRichTextString(incomebill.getUser().getUsername()));
//				}else{
//					crow.getCell(1).setCellValue(new HSSFRichTextString(""));
//				}
//				
//				crow.createCell(2).setCellStyle(titlestyle);
//				if(incomebill.getBill_type().equals(Options.BILL_TYPE_INCASH)){
//					crow.getCell(2).setCellValue(new HSSFRichTextString("充值"));
//				}else if(incomebill.getBill_type().equals(Options.BILL_TYPE_PAYORDER)){
//					crow.getCell(2).setCellValue(new HSSFRichTextString("消费"));
//				}
//				
//				crow.createCell(3).setCellStyle(titlestyle);
//				crow.getCell(3).setCellValue(new HSSFRichTextString(DecimalUtil.formatToString(incomebill.getPay_price())));
//				
//				crow.createCell(4).setCellStyle(titlestyle);
//				if(incomebill.getUser()!=null){
//					crow.getCell(4).setCellValue(new HSSFRichTextString(incomebill.getUser().getPhone()));
//				}else{
//					crow.getCell(4).setCellValue(new HSSFRichTextString(""));
//				}
//				
//				crow.createCell(5).setCellStyle(titlestyle);
//				crow.getCell(5).setCellValue(new HSSFRichTextString(incomebill.getByear()+"年"));
//				
//				crow.createCell(6).setCellStyle(titlestyle);
//				crow.getCell(6).setCellValue(new HSSFRichTextString(incomebill.getBmonth()+"月"));
//				
//				crow.createCell(7).setCellStyle(titlestyle);
//				if(incomebill.getOrder()!=null){
//					crow.getCell(7).setCellValue(new HSSFRichTextString(incomebill.getOrder().getOrderid()));
//				}else{
//					crow.getCell(7).setCellValue(new HSSFRichTextString(incomebill.getOrder_number()));
//				}
//				
//				crow.createCell(8).setCellStyle(titlestyle);
//				crow.getCell(8).setCellValue(new HSSFRichTextString(incomebill.getService_name()));
//				
//				crow.createCell(9).setCellStyle(titlestyle);
//				if(incomebill.getPay_type().equals(Options.ORDER_PAYM_ALIPAY)){
//					crow.getCell(9).setCellValue(new HSSFRichTextString("支付宝"));
//				}else if(incomebill.getPay_type().equals(Options.ORDER_PAYM_WEIXIN)){
//					crow.getCell(9).setCellValue(new HSSFRichTextString("微信"));
//				}else if(incomebill.getPay_type().equals(Options.ORDER_PAYM_UCOIN)){
//					crow.getCell(9).setCellValue(new HSSFRichTextString("U币"));
//				}else if(incomebill.getPay_type().equals(Options.ORDER_PAYM_CASH)){
//					crow.getCell(9).setCellValue(new HSSFRichTextString("现金"));
//				}
//				
//				crow.createCell(10).setCellStyle(titlestyle);
//				crow.getCell(10).setCellValue(new HSSFRichTextString(DateFormatUtil.date2Str(incomebill.getCreated_at())));
//			}
//		}
//		
//		String xlsFileName = "userbilllist_"+System.currentTimeMillis();
//		//生成订单文件
//		File orderFile = new File(tmpDir.getAbsolutePath() + File.separator + xlsFileName + ".xls");
//		OutputStream os = null;
//		try {
//			orderFile.createNewFile();
//			os = new FileOutputStream(orderFile);
//			workbook.write(os);
//			os.flush();
//		} catch (IOException e) {
//			System.out.println("create xls:{} file fail");
//		} finally{
//			if(os != null){
//				try {
//					os.close();
//				} catch (IOException e) {
//					System.out.println("close xls:{} file fail");
//				}
//			}
//		}
//		//打包所有订单文件为zip文件
//		String zippath = tmpZipFilePath;
//		File zipFile = new File(zippath);
//		if(!zipFile.exists()){
//			try {
//				zipFile.createNewFile();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		ZipOutputStream zos = null;
//		try {
//			zipFile.createNewFile();
//			zos = new ZipOutputStream(new FileOutputStream(zipFile));
//			//找到要打包的文件
//			File[] orderFiles = tmpDir.listFiles();
//			for(int i = 0; i < orderFiles.length ; i ++){
//				FileInputStream fis = new FileInputStream(orderFiles[i]);  
//				zos.putNextEntry(new ZipEntry(orderFiles[i].getName()));
//				int len;
//				byte[] buffer = new byte[1024];
//				//读入需要下载的文件的内容，打包到zip文件
//				while((len = fis.read(buffer)) > 0) {
//					zos.write(buffer, 0, len);
//				}
//				zos.closeEntry();
//				fis.close();
//			}
//			zos.flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//			System.out.println("create zip file fail");
//		} finally{
//			if(zos != null){
//				try {
//					zos.close();
//				} catch (IOException e) {
//					System.out.println("close zip file fail");
//				}
//			}
//		}
//		//删除临时目录
//		try {
//			FileUtils.deleteDirectory(tmpDir);
//		} catch (IOException e) {
//			System.out.println("delete tempdir:{} fail");
//		}
//	}
	
	
}
