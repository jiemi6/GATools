import com.minkey.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.security.Provider;
import java.security.Security;
import java.util.TreeSet;

@Slf4j
public class T {

    @Test
    public void testIp(){
        log.error(""+StringUtil.isIp("10.10.10.4"));
        log.error(""+StringUtil.isIp("255.10.10.4"));
    }
}
