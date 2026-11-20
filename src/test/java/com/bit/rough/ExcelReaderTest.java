package com.bit.rough;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import static org.apache.poi.ss.usermodel.CellType.*;


public class ExcelReaderTest {
	

	public FileOutputStream fos;
    public FileInputStream fis = null;
    private XSSFWorkbook workbook  = null;
    private XSSFSheet sheet  = null;
    private XSSFRow row  = null;
    private XSSFCell cell  = null;

    public String path  = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	String userDir = System.getProperty("user.dir");	
	String excelPath = userDir + "/src/test/resources/testdata/Testdata.xlsx";
	ExcelReaderTest excel = new ExcelReaderTest(excelPath);
	int rows = excel.getRowCount("TestSuite");
	int cols = excel.getColumnCount("TestSuite");
	System.out.println("Row count is: "+ rows);
	System.out.println("Column count is: "+cols);
	for(int i=0;i<rows;i++)
	{
		for(int j=0;j<cols;j++) {
			System.out.print(excel.getCellData("TestSuite", j, i) + "   ");
		}
		System.out.println();
	}

	}
	

    public ExcelReaderTest(String path) {
        this.path = path;
        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0);
            fis.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    public int getRowCount(String sheetName)
    {
        sheet = workbook.getSheet(sheetName);
        return sheet.getLastRowNum()+1;
    }

    public int getColumnCount(String sheetName)
    {
        sheet = workbook.getSheet(sheetName);
        return sheet.getRow(1).getPhysicalNumberOfCells();
    }

    public String getCellValues(String sheetName,int rows,int cols)
    {
        System.out.println("Rows: "+rows+" and Cols: "+cols);  //For debugging
        String cellValue = null;
        try{
            sheet = workbook.getSheet(sheetName);
            for(int i=1;i<rows;i++)
            {
                row = sheet.getRow(i);
                for(int j=1;j<=cols;j++)
                {
                    cell = row.getCell(j);
                    switch (cell.getCellType())
                    {
                        case STRING:    //field that represents string cell type
                            cellValue = cell.getStringCellValue();
                            break;
                        case NUMERIC:    //field that represents number cell type
                            cellValue = String.valueOf(cell.getNumericCellValue());
                            break;
                        default:
                            cellValue = "";
                    }
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return cellValue;
    }

    public String getCellData(String sheetName, int colNum, int rowNum) {
        try {
            if (rowNum <= 0)
                return "";

            int index = workbook.getSheetIndex(sheetName);

            if (index == -1)
                return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                return "";
            cell = row.getCell(colNum);
            if (cell == null)
                return "";

            if (cell.getCellType() == STRING)
                return cell.getStringCellValue();
            else if (cell.getCellType() == NUMERIC || cell.getCellType() == FORMULA)
            {
                String cellText = String.valueOf(cell.getNumericCellValue());
                return cellText;
            }
            else if (cell.getCellType() == BLANK)
                return "";
            else if (cell.getCellType() == BOOLEAN)
                return String.valueOf(cell.getBooleanCellValue());
            else
                return "";
        } catch (Exception e) {

            e.printStackTrace();
            return "row " + rowNum + " or column " + colNum + " does not exist  in xls";
        }
    }
    
  // returns the data from a cell
  public String getCellData(String sheetName, String colName, int rowNum) {
      try {
          if (rowNum <= 0)
              return "";

          int index = workbook.getSheetIndex(sheetName);
          int col_Num = -1;
          if (index == -1)
              return "";

          sheet = workbook.getSheetAt(index);
          row = sheet.getRow(0);
          for (int i = 0; i < row.getLastCellNum(); i++) {
              //System.out.println(row.getCell(i).getStringCellValue().trim());
              if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
                  col_Num = i;
          }
          if (col_Num == -1)
              return "";

          sheet = workbook.getSheetAt(index);
          row = sheet.getRow(rowNum - 1);
          if (row == null)
              return "";
          cell = row.getCell(col_Num);

          if (cell == null)
              return "";

          if (cell.getCellType() == STRING)
              return cell.getStringCellValue();
          else if (cell.getCellType() == NUMERIC || cell.getCellType() == FORMULA) {

              String cellText = String.valueOf(cell.getNumericCellValue());
//              if (HSSFDateUtil.isCellDateFormatted(cell)) {
//
//                  double d = cell.getNumericCellValue();
//
//                  Calendar cal = Calendar.getInstance();
//                  cal.setTime(HSSFDateUtil.getJavaDate(d));
//                  cellText =
//                          (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
//                  cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" +
//                          cal.get(Calendar.MONTH) + 1 + "/" +
//                          cellText;
//
//
//              }


              return cellText;
          } else if (cell.getCellType() == BLANK)
              return "";
          else
              return String.valueOf(cell.getBooleanCellValue());

      } catch (Exception e) {

          e.printStackTrace();
          return "row " + rowNum + " or column " + colName + " does not exist in xls";
      }
  }
}
