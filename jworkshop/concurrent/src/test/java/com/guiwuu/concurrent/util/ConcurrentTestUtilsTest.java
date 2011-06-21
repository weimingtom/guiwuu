package com.guiwuu.concurrent.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author guiwuu
 */
public class ConcurrentTestUtilsTest {
    
    private static final Logger logger = Logger.getLogger(ConcurrentTestUtilsTest.class.getName()); 
    
    @Test
    public void testRun() throws Exception {
        int concurrent = 10;
        logger.log(Level.CONFIG, "%s threads running concurrently...", concurrent);
        final ThreadWrapper[] threads = new ThreadWrapper[concurrent];
        for(int i=0;i<concurrent;i++){
            threads[i] = new ThreadWrapper("test run thread"){

                @Override
                protected boolean runTask() throws Exception {
                    return true;
                }
                
            };
        }
        int result = ConcurrentTestUtils.run(threads);
        assertEquals(concurrent, result);
    }
}