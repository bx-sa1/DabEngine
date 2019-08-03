package DabEngine.Cache;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import DabEngine.Cache.InMemoryCache;

public class CacheTest {
    
    public void checkIfObjectCanBeAddedAndRetrived(){
        String obj = "lol";
        InMemoryCache.INSTANCE.add("string", obj, 3000);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String newObj = InMemoryCache.INSTANCE.get("string");
        assertSame(obj, newObj);
    }

    public void checkIfCleaningWorks(){
        String obj = "lol";
        InMemoryCache.INSTANCE.add("string", obj, 3000);
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String newObj = InMemoryCache.INSTANCE.get("string");
        assertNotSame(obj, newObj);
    }
}