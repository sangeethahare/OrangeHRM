package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders1 {
	@DataProvider(name="login")
	public String[][] getData() throws IOException
	{
		String path=".\\testdata\\orangelogin.xlsx ";
		ExcelUtility ex=new ExcelUtility(path);
		int totalrow = ex.getRowCount("login");
		int totalcolumn = ex.getCellCount("login",1);
		String [][]data=new String[totalrow][totalcolumn];
		for(int i=1;i<=totalrow;i++)
		{
			for(int j=0;j<totalcolumn;j++)
			{
				data[i-1][j]=ex.getCellData("login", i, j);
			}
		}
		return data;
	}
	

}
