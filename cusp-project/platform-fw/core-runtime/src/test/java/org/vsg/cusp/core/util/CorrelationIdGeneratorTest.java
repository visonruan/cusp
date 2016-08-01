/**
 * 
 */
package org.vsg.cusp.core.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vsg.cusp.core.utils.CorrelationIdGenerator;

/**
 * @author Vicente Yuen
 *
 */
public class CorrelationIdGeneratorTest {
	
	
	public static Logger log = LoggerFactory.getLogger(CorrelationIdGeneratorTest.class);

	byte[] mac = null;
	
	@Before
	public void setUp() throws Exception {
		
		InetAddress  inetAdd = InetAddress.getLocalHost();
		mac = NetworkInterface.getByInetAddress(inetAdd).getHardwareAddress(); 		
	}
	
	
	private Collection<Long> existedValues = new ArrayList<Long>();
	
	@Test
	public void test_case01() throws Exception {
		/*
		CorrelationIdGenerator inst = CorrelationIdGenerator.genInstance( mac );
		
		for (int i = 0 ; i < 999999999 ; i++) {
			long value = inst.generate( 0 );
			
			if (existedValues.contains( value )) {
				log.info("exist value : " + value);				
				break;
			}
			existedValues.add(value);
		}
		*/
	}
	
}
