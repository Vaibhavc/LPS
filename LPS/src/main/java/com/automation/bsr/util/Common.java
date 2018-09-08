package com.automation.bsr.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.automation.Base;

public class Common extends Base {

	private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final int RANDOM_STRING_LENGTH = 5;
	
	public static String getCurrentTimeStamp() {
		logger.info("getting current time stamp in yyyy-MM-dd HH:mm:ss format ");
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// dd/MM/yyyy
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;

	}
	
	/**
	 * This method generates random string
	 * 
	 * @return
	 */
	public static String generateRandomString() {
		logger.info("getting random string ");
		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
			int number = getRandomNumber();
			char ch = CHAR_LIST.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}

	/**
	 * This method generates random numbers
	 * 
	 * @return int
	 */
	private static int getRandomNumber() {
		int randomInt = 0;
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(CHAR_LIST.length());
		if (randomInt - 1 == -1) {
			return randomInt;
		} else {
			return randomInt - 1;
		}
	}
	public List<String> getRowRecordsFromWebtable(String fName) {
		logger.info("getting row having first name as " + fName);
		// To locate table.
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WebElement mytable = getDriver().findElement(By.xpath("/html/body/div/table"));
		// To locate rows of table.
		List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
		// To calculate no of rows In table.
		int rows_count = rows_table.size();
		// Loop will execute till the last row of table.
		for (int row = 1; row < rows_count; row++) {
			// To locate columns(cells) of that specific row.
			List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
			// To calculate no of columns(cells) In that specific row.
			int columns_count = Columns_row.size();
			// Loop will execute till the last cell of that specific row.
			for (int column = 0; column < columns_count; column++) {
				// To retrieve text from that specific cell.
				String celtext = Columns_row.get(column).getText();
				if (celtext.equals(fName)) {
					logger.info("Row is...." + row);
					List<String> rowRecords = new ArrayList<String>();
					for (WebElement e : Columns_row) {
						rowRecords.add(e.getText());
					}
					return rowRecords;
				}
			}
		}
		return null;
	}
	
	public void handleHomePagePopup(){
		try {
			logger.info("-----------waiting for pop up on Homepage-------------");
			if (getDriver().findElement(By.xpath("//img[@usemap='#IPEMap']")).isDisplayed())
			{
				logger.info("-----------handling pop up on Homepage-------------");
				getDriver().navigate().refresh();
			}
		} catch (Exception e) {
			logger.info("----------pop up does not appear--------------");
		}
		
	}
}
