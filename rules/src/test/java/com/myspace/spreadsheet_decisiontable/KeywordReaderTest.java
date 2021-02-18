package com.myspace.spreadsheet_decisiontable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;


import org.junit.Test;

public class KeywordReaderTest {
    
    @Test
    public void testGetKWList(){
        KeywordReader kwReader = new KeywordReader("10kTable.xls");
        List result = kwReader.getKwList();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(10000, result.size());
        Map element1 =  (Map) result.get(0);
        assertNotNull(element1);
        assertEquals("DangerObject1.*", element1.get("1"));
        assertEquals("false", element1.get("2"));
        
    }
}
