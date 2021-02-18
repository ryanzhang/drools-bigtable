package com.myspace.spreadsheet_decisiontable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class KeywordReader {
    private String excelFile;
    private List<Map<String, String>> kwList;

    public KeywordReader(String excelFile) {
        this.excelFile = excelFile;
        kwList= new ArrayList<Map<String, String>>();
    }

    public String getExcelFile() {
        return excelFile;
    }

    public void setExcelFile(String excelFile) {
        this.excelFile = excelFile;
    }

    public List<Map<String,String>> getKwList() {
        if(this.excelFile.isEmpty())return null;
        parseFile(getClass().getClassLoader().getResourceAsStream(excelFile));
        return kwList;
    }

    //load From Excel file
    private void loadKwList() {
        this.kwList = kwList;
    }
    
    //Parse Excel file; reference from: https://github.com/kiegroup/drools/blob/master/drools-decisiontables/src/main/java/org/drools/decisiontable/parser/xls/ExcelParser.java
    public void parseFile( InputStream inStream ) {
        try {
            parseWorkbook( WorkbookFactory.create( inStream ) );
        } catch ( IOException e ) {
            throw new RuntimeException( "Failed to open Excel stream, " + "please check that the content is xls97 format.",
                                                   e );
        }
    }

    public void parseFile( File file ) {
        try {
            parseWorkbook( WorkbookFactory.create( file, (String)null, true ) );
        } catch ( IOException e ) {
            throw new RuntimeException( "Failed to open Excel stream, " + "please check that the content is xls97 format.",
                                                   e );
        }
    }

    public void parseWorkbook( Workbook workbook ) {
        try {
            try {
                    Sheet sheet = workbook.getSheetAt( 0 );
                    processSheet( sheet, 1, 1 );
            } finally {
                workbook.close();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    private void processSheet( Sheet sheet, int c, int r) {
        int maxRows = sheet.getLastRowNum();

        // CellRangeAddress[] mergedRanges = getMergedCells( sheet );
        DataFormatter formatter = new DataFormatter( Locale.ENGLISH );
        FormulaEvaluator formulaEvaluator = sheet.getWorkbook().getCreationHelper().createFormulaEvaluator();

        for ( int i = r; i <= maxRows; i++ ) {
            Row row = sheet.getRow( i );
            int lastCellNum = row != null ? row.getLastCellNum() : 0;
            // newRow( listeners, i, lastCellNum );
            
            Map<String, String> rowData= new HashMap<String, String>();
            int index =0;
            for ( int cellNum = c; cellNum < lastCellNum; cellNum++ ) {
                Cell cell = row.getCell( cellNum );
                if ( cell == null ) {
                    continue;
                }
                double num = 0;
                index++;
                rowData.put(String.valueOf(index), cell.getStringCellValue());

                // // CellRangeAddress merged = getRangeIfMerged( cell,
                // //                                             mergedRanges );
                // // int mergedColStart = DataListener.NON_MERGED;
                // switch ( cell.getCellTypeEnum() ) {
                //     case BOOLEAN:
                //     case FORMULA:
                //         try {
                //             newCell(listeners,
                //                     i,
                //                     cellNum,
                //                     getFormulaValue( formatter, formulaEvaluator, cell ),
                //                     mergedColStart);
                //         } catch (RuntimeException e) {
                //             // This is thrown if an external link cannot be resolved, so try the cached value
                //             log.warn("Cannot resolve externally linked value: " + formatter.formatCellValue(cell));
                //             String cachedValue = tryToReadCachedValue(cell);
                //             newCell(listeners,
                //                     i,
                //                     cellNum,
                //                     cachedValue,
                //                     mergedColStart);
                //         }
                //         break;
                //     case NUMERIC:
                //         num = cell.getNumericCellValue();
                //     default:
                //         if (num - Math.round(num) != 0) {
                //             newCell(listeners,
                //                     i,
                //                     cellNum,
                //                     String.valueOf(num),
                //                     mergedColStart);
                //         } else {
                //             newCell(listeners,
                //                     i,
                //                     cellNum,
                //                     formatter.formatCellValue(cell),
                //                     mergedColStart);
                //         }
                // }
            }
            kwList.add(rowData);
        }
    }
}
