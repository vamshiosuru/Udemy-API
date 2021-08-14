import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven {
	
	public ArrayList getTestdata(String testcasename) throws IOException
	{
		ArrayList a= new ArrayList();
		FileInputStream file=new  FileInputStream("C:\\Users\\pc\\Documents\\TestData.xlsx");
		XSSFWorkbook workbook =new XSSFWorkbook(file);
		
		int totalsheets=workbook.getNumberOfSheets();
		for(int i=0;i<totalsheets ;i++)
		{
			if(workbook.getSheetName(i).equalsIgnoreCase("Sheet2"))
			{
				XSSFSheet sheet=workbook.getSheetAt(i);
				
				//identify test case column by scanning entire first row
				//once column is identified , scan  the entire column to identify purchase test case row
				//once purchase testcase row is identified ,  pull all the data of that row and feed that in to test case
				Iterator<Row> rows=sheet.iterator();  //sheet is the collection of rows
				Row firstrow=rows.next();
				Iterator<Cell> cells=firstrow.cellIterator(); //row is the collection of cells
				
				int k=0;
				int column=0;
				while(cells.hasNext())
				{
					Cell columnvalue=cells.next();
					if(columnvalue.getStringCellValue().equalsIgnoreCase("TestCases"))
					{
						column=k;
					}
					k ++ ;
				}
				System.out.println(column);
				//once column is identified ,scan the entire Testcases column to identify purchase test case row
				
				while(rows.hasNext())
				{
					Row r=rows.next();
					if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testcasename))
					{
						Iterator<Cell> cv =r.iterator();
						while(cv.hasNext())
						{
							Cell cell=cv.next();
							if(cell.getCellType()==CellType.STRING)
							{
								a.add(cell.getStringCellValue());
							}
							else
							{
								a.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
							}
							
						}
					}
					
				}
			}
		}
		return a;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
DataDriven d=new DataDriven();
ArrayList ad=d.getTestdata("Login");
for(int i=1;i<ad.size();i++)
{
	System.out.println(ad.get(i));
}

	   
	}

}
