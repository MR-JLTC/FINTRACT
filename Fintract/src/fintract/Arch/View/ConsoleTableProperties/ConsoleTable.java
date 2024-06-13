package fintract.Arch.View.ConsoleTableProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import fintract.Arch.View.ConsoleTableProperties.Style.Row;
import fintract.Arch.View.ConsoleTableProperties.Style.Column;
import fintract.Arch.View.ConsoleTableProperties.ColumnFormat.Aligned;


/**
 * A {@code ConsoleTable} represents a table of rows and columns to be formatted into a 
 * single {@code String}, which can be further printed on the console in a monospaced font.
 * <p>
 * For example:
 * <pre>
 * Pet Age
 * --- ---
 * Cat   5
 * Dog  10
 * </pre>
 * 
 * I started with <a href="https://www.logicbig.com/how-to/code-snippets/jcode-java-cmd-command-line-table.html">CommandLineTable</a> and added null handling, fluency, ColumnFormats and customisable Styles and tests.
 * 
 * @author Copyright (c) John C Sinclair 2021
 */
public final class ConsoleTable {
	
	private static final Object[] NULL_OBJECT_ARRAY = (Object[])null;
	private static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];	
    private Style style = Styles.LIGHT;

    private Object[] headers;
    private final List<Object[]> rows = new ArrayList<>();
    
    private Aligned alignment = Aligned.RIGHT;
    private boolean showVerticalLines = true;
    private boolean withRowLines = false;
    private String  leftColumnPadding  = " ";
    private String  rightColumnPadding = " ";
    
    private int rowWidth;

    /**
     * Constructor for empty table. The column headers can be set with setHeaders and data rows can be added with addRow.
     */
    public ConsoleTable() {
        withVerticalLines(true);
    }

    /**
     * Constructor for table of List<Object[]> with no headers. 
     * @param data List of Object[] representing rows of columns.
     */
    public ConsoleTable(final List<? extends Object[]> data) {
        this();
        final Object[] emptyObjectArray = {};
        setHeaders(emptyObjectArray);
        rows.addAll(data);
    }

    /**
     * Constructor for List<Object[]> table with headers
     * @param headers array of Object representing table headers
     * @param data
     */
    public ConsoleTable(final Object[] headers, final List<? extends Object[]> data) {
        this(data);
        setHeaders(headers);
    }

    /**
     * Constructor for Object[][] table with no headers
     * @param data
     */
   public ConsoleTable(final Object[][] data) {
        this();
        rows.addAll(Arrays.asList(data));
    }
    
   /**
    * Constructor for Object[][] table with no headers
     * @param headers
     * @param data
    */
    public ConsoleTable(final Object[] headers, final Object[][] data) {
        this(data);
        setHeaders(headers);
    }
    
    //TODO test this
    /**
     * Constructor for Object[][] table with no headers
     * @param <T>
     * @param <U>
     * @param headers
     * @param data
     */
    public <T,U> ConsoleTable(final Iterable<T> headers, final Iterable<Iterable<U>> data) {
    	
    	for(T t : headers) {
    		System.out.println(t);
    	}
    	
    	for(Iterable<U> row : data) {
    		for( U column : row ) {
    			System.out.printf("%s ", column);
    		}
     		System.out.println();
    	}
    	
    	
        setHeaders(headers);
        throw new UnsupportedOperationException("TODO");
    }
    
    /**
     * Constructor for Object[][] table with no headers
     * @param <T>
     * @param <U>
     * @param headerList
     * @param rowList
     */
     public <T,U> ConsoleTable(List<T> headerList, List<List<U>> rowList) {
    	 this();
    	 
    	 setHeaders(headerList.toArray(Object[]::new) );

    	 for(List<U> row : rowList) {
    		Object[] newRow = row.toArray();
     		rows.add(newRow);
    	 }
    	 
	}

     /**
      * Constructor for table with headers
      * @param headers Strings representing table headers
      */
	public ConsoleTable(final String ... headers) {
		this();
        setHeaders(headers);
	}

	
	
	public ConsoleTable withStyle(Style style) {
        this.style = style;
    	this.leftColumnPadding  = style.getPadding(Column.LEFT); 
    	this.rightColumnPadding = style.getPadding(Column.RIGHT);
        return this;
    }

    public ConsoleTable withAlignment(Aligned aligned) {
        this.alignment = aligned;
        return this;
    }

    public ConsoleTable withVerticalLines(boolean showVerticalLines) {
        this.showVerticalLines = showVerticalLines;
        return this;
    }

    public ConsoleTable withColumnPadding(String leftColumnPadding, String rightColumnPadding) {
    	this.leftColumnPadding  = leftColumnPadding; 
    	this.rightColumnPadding = rightColumnPadding;
        return this;
    }
    
	public ConsoleTable withColumnPadding(String padding) {
		return this.withColumnPadding(padding, padding);
	}
   
	public ConsoleTable withRowLines(boolean showRowLines) {
		this.withRowLines = showRowLines;
		return this;
	}
	
	public ConsoleTable withRowLines() {
		this.withRowLines = true;
		return this;
	}

	/**
	 * display a row of column headings at the top of the table
	 * 
	 * @param headers The headings for the columns. By default a column will be right aligned, <br>if it starts with <code>-</code> the column will be left aligned, <br>if it starts with <code>'</code> the column will be centred.
     * @return 
	 */
	public ConsoleTable setHeaders(Object... headers) {
    	
    	// if a <code>String</code> starts with "-", make it left aligned, like <code>String.format("%-s")</code>
    	// replace any <code>String</code> headers starting with "-" with a Left aligned format, like <code>String.format("%-9s")</code>
    	// have to copy the array to avoid an ArrayStoreException
    	
		if( headers == null) {
			headers = EMPTY_OBJECT_ARRAY;
		}
		
    	Object[] newHeaders = new Object[headers.length];
    	
    	for(int i = 0; i < headers.length; i++) {
    		Object columnHeading = headers[i];
    		if( columnHeading != null && columnHeading instanceof String && ((String) columnHeading).length() > 0 ) {
    			char firstChar = ((String) columnHeading).charAt(0);
    			if(firstChar == '-' || firstChar == '\'') {
        			ColumnFormat columnFormat = new ColumnFormat( (String)columnHeading );
        			columnHeading = columnFormat;
    			}
    		}
			newHeaders[i] = columnHeading;
		} 	
    	this.headers = (newHeaders.length == 0) ? null : newHeaders;
    	
        return this;
    }
	
	/**
	 * display a row of column headings at the top of the table
	 * 
	 * @param headers The headings for the columns. By default a column will be right aligned, <br>if it starts with <code>-</code> the column will be left aligned, <br>if it starts with <code>'</code> the column will be centred.
	 * @return
	 */
	public ConsoleTable setHeaders(String... headers) {
		Object[] objects = headers;
		setHeaders(objects);
		return this;
	}

	
    /**
     * Add a row of data
     * @param cells
     */
    public void addRow(Object... cells) {
    	if(cells == null) {
    		addRow();
    		return;
    	}
        rows.add(cells);
    }

    /**
     * Add a row of data
     * @param cells
     */
    public void addRow(String[] cells) {
    	addRow( (Object[])cells );
    }

    /**
     * Add a row of data
     * @param <T>
     * @param cells
     */
    public <T> void addRow(List<T> cells) {
    	if( cells == null ) {
    		addRow();
    	}
    	else {
    		addRow( cells.toArray() );
    	}
    }

    /**
     * Add an empty row, all columns in the row will be empty.
     */
    public void addRow() {
        rows.add(NULL_OBJECT_ARRAY );
    }

    /**
     * Appends all of the elements in <code>moreRows</code> to the end of the rows in this ConsoleTable. 
     * @param moreRows
     */
    public void addAll(Iterable<? extends Object[]> moreRows) {
        for(Object[] row : moreRows) {
        	if( row != null ) {
        		rows.add( row );
        	}
        	else {
        		rows.add( NULL_OBJECT_ARRAY );
        	}
        }
    }
    
    
    /**
     * Returns a multi-line <code>String</code> containing the formatted rows and columns of the table.
     */
    @Override
    public String toString() {
    	return render();
    }
    
    private String render() {
    	
    	int[] maxWidths = calculateMaxWidths();

    	rowWidth = calculateRowWidth(maxWidths);
    	
    	int renderedLineCount = rows.size();
    	if(withRowLines) {
    		renderedLineCount = renderedLineCount * 2;
    	}
    	if (headers != null) {
    		renderedLineCount++;
    	}
		StringBuilder buf = new StringBuilder(rowWidth * renderedLineCount); // TODO tableWidth()+1);

    	buf.append(renderRow(Row.TOP, maxWidths, null));

    	if (headers != null) {
            buf.append(renderRow(Row.HDRDATA, maxWidths, headers));
            buf.append(renderRow(Row.HDRLINE, maxWidths, null));
        }
        
    	for (int i = 0; i < rows.size(); i++) {
        	Object[] row = rows.get(i);
        	buf.append(renderRow(Row.ROWDATA, maxWidths, row));
        	if( i != renderedLineCount - 1 && withRowLines ) {
        		buf.append(renderRow(Row.ROWLINE, maxWidths, null));
        	}
        }
        
       	buf.append(renderRow(Row.BOTTOM, maxWidths, null));

        return buf.toString();
    }

    private int calculateRowWidth(int[] columnWidths) {
    	
    	int rwWidth = 0;
    	Row rowType = Row.ROWDATA;
    	
    	if(showVerticalLines) {
    		rwWidth += style.getPattern(rowType, Column.LEFT).length();
    	}
        for (int i = 0; i < columnWidths.length; i++) {
        	
			String joinSep = showVerticalLines ? style.getPattern(rowType, Column.COLLINE) : " ";
			boolean isLastCell = i == columnWidths.length - 1;
        	
			if(showVerticalLines)
				rwWidth += leftColumnPadding.length();
 
			rwWidth += columnWidths[i];

			if(showVerticalLines)
				rwWidth += rightColumnPadding.length();
  	   		if(!isLastCell) {
				rwWidth += joinSep.length();
    		}
         }
    	if(showVerticalLines) {
    		rwWidth += style.getPattern(rowType, Column.RIGHT).length();
    	}
		rwWidth += "\n".length();
		
        return rwWidth;
    }
    
	public int[] calculateMaxWidths() {
     
		//  instead of throwing exception, be permissive, and if header and data widths are not equal then display blanks at the end of header or data.
		
		List<Integer> maxWidths = new ArrayList<>();

    	
        if(headers != null) {
	    	for (int i = 0; i < headers.length; i++) {
	    		if( i > maxWidths.size() - 1 ) {
	    			maxWidths.add(0);
	    		}
				maxWidths.set(i, Math.max(maxWidths.get(i), headers[i] == null ? 0 : headers[i].toString().codePointCount(0, headers[i].toString().length()) ));
	        }
        }

        for (Object[] cells : rows) {
	        if(cells != null) {
	            for (int i = 0; i < cells.length; i++) {
	 	    		if( i > maxWidths.size() - 1 ) {
		    			maxWidths.add(0);
		    		}
		    		maxWidths.set(i, Math.max(maxWidths.get(i), cells[i] == null ? 0 :  cells[i].toString().codePointCount(0, cells[i].toString().length()) ));
	           }
        	}
        }
		return maxWidths.stream().mapToInt(Integer::intValue).toArray();
	}

    private String renderRow(Row rowType, int[] columnWidths, Object[] cells ) {
    	if(style.getPattern(rowType, Column.LEFT) == null) {
    		return "";
    	}

    	StringBuilder buf = new StringBuilder(rowWidth);
    	if(showVerticalLines) {
    		buf.append(style.getPattern(rowType, Column.LEFT));
    	}
        for (int i = 0; i < columnWidths.length; i++) {
        	
        	int columnWidth = columnWidths[i];
        	String cellString = null;
        	String leftCellPadding  = leftColumnPadding; 
        	String rightCellPadding = rightColumnPadding;
        	
    		Aligned columnAlign = alignment;
    		
        	if(rowType == Row.HDRDATA || rowType == Row.ROWDATA ) {
        		String cell = (cells == null || i > cells.length -1 || cells[i] == null) ? "" : cells[i].toString();
        		
        		if(headers != null && i < headers.length && headers[i] != null && headers[i] instanceof ColumnFormat) {
        			columnAlign = ((ColumnFormat)headers[i]).getAlignment();
            		if(columnAlign == Aligned.CENTRE) {
            			// left pad <code>cell</code> so that it is centred
            			int cellWidth = cell.codePointCount(0, cell.length());
            			int leftPadWidth = (columnWidth - cellWidth+1) / 2;
            			String padFormat = "%" + (leftPadWidth+cellWidth) + "s";
						cell = String.format(padFormat, cell);
            		}
        		}

				String formatString = "%"+(columnAlign == Aligned.RIGHT ? "" : "-")+columnWidth+"s";
				cellString = (columnWidth == 0) ? "" : String.format(formatString, cell);
        	}
        	else {	// this is a rule line between the rows of the table
        		
               	String ruleString = style.getPattern(rowType, Column.COLDATA);
               	columnWidth = leftCellPadding.length() + columnWidth + rightCellPadding.length();
    			cellString = String.join("", Collections.nCopies(columnWidth, ruleString)).substring(0,columnWidth);

    			leftCellPadding  = "";
    			rightCellPadding = "";
        	}

			String joinSep = showVerticalLines ? style.getPattern(rowType, Column.COLLINE) : " ";
			boolean isLastCell = i == columnWidths.length - 1;
        	
			buf.append(leftCellPadding);
   			buf.append(cellString);
			buf.append(rightCellPadding);
			
  	   		if(!isLastCell) {
     			buf.append(joinSep);
    		}
         }
    	if(showVerticalLines) {
    		buf.append(style.getPattern(rowType, Column.RIGHT));
    	}
        buf.append("\n");

        return buf.toString();
    }
}