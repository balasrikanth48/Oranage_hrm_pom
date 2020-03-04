package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excelutils {
Workbook wb;
	
	
	public Excelutils() throws Exception
	{
	FileInputStream fi=new FileInputStream("E:\\Srikanth_82\\Orangehrm_POM\\TestInput\\TestDataInput.xlsx");
	wb=new XSSFWorkbook(fi);
	}
	public int getrowcount(String Sname)
	{
		 return wb.getSheet(Sname).getLastRowNum();
		
	}
	public  int getcolcount(String Sname)
	{
		 return wb.getSheet(Sname).getRow(0).getLastCellNum();
	}
	@SuppressWarnings("deprecation")
	public String  getData(String Sname,int row,int coloumn)
	{
		String data=" ";
		
		if (wb.getSheet(Sname).getRow(row).getCell(coloumn).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			int celldata=(int) wb.getSheet(Sname).getRow(row).getCell(coloumn).getNumericCellValue();
			data=String.valueOf(celldata);
		}else
		{
			data=wb.getSheet(Sname).getRow(row).getCell(coloumn).getStringCellValue();
			
		}return data;
	
	}
	public void SetCellData(String Sname,int row,int coloumn,String Status) throws Exception
	{
		Cell cell  =wb.getSheet(Sname).getRow(row).getCell(coloumn);
		cell.setCellValue(Status);
		if (Status.equalsIgnoreCase("PASS"))
		{
			
			CellStyle style=wb.createCellStyle();
			org.apache.poi.ss.usermodel.Font font=wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			cell.setCellStyle(style);
			
		}else if (Status.equalsIgnoreCase("fail"))
		{
			CellStyle style=wb.createCellStyle();
			org.apache.poi.ss.usermodel.Font font=wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			cell.setCellStyle(style);
			
		}else if  (Status.equalsIgnoreCase("NotExcuted"))
        {
			CellStyle style=wb.createCellStyle();
			org.apache.poi.ss.usermodel.Font font=wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			style.setFont(font);
			cell.setCellStyle(style);
		}
		FileOutputStream fo=new FileOutputStream("E:\\Srikanth_82\\Orangehrm_POM\\TestOutput\\TestData.xlsx");
		wb.write(fo);
		fo.close();
		
	}
	public int getTestCaseRow(String tcid)
	{
		
		int testcaserow=-1;
		int rc=getrowcount("TestData");
		for (int i =1; i <=rc; i++) {
			
			if (getData("TestData", i, 0).equalsIgnoreCase(tcid)) {
				testcaserow=i;
				break;
				
			}
		}
//		System.out.println("test case rows:"+testcaserow);
		return testcaserow;
	}

}
