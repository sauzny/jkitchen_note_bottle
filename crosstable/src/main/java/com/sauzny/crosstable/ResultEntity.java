package com.sauzny.crosstable;

import java.util.List;

public class ResultEntity {

	/**
	 * columns。列名
	 */
	private List<String> columns;
	/**
	 * data。记录
	 */
	private Object[][] data;
	/**
	 * @return 
	 */
	public List<String> getColumns() {
		return columns;
	}
	/**
	 * @param List<Column> columns
	 */
	public void setColumns(List<String> columns) {
		this.columns = columns;
	}
	/**
	 * @return 
	 */
	public Object[][] getData() {
		return data;
	}
	/**
	 * @param Object[][] data
	 */
	public void setData(Object[][] data) {
		this.data = data;
	}
	
	/** 
	 * <b>toString。</b>  
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder stb = new StringBuilder();
		stb.append("|");
		for (int i = 0; i < columns.size(); i++) {
			stb.append(columns.get(i)).append("|");
		}
		int lCount = stb.length();
		stb.append("\r\n");
		for (int i = 0; i < lCount; i++) {
			stb.append("-");
		}
		stb.append("\r\n");
		for (int i = 0; i < 20 && i<data.length; i++) {
			Object[] obj = data[i];
			stb.append("|");
			for (int j = 0; j < obj.length; j++) {
				stb.append(obj[j]).append("|");
			}
			stb.append("\r\n");
		}
		
		return stb.toString();
	}
}