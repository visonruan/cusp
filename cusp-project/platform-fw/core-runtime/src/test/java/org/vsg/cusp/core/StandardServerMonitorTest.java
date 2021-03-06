/**
 * 
 */
package org.vsg.cusp.core;

import java.net.URL;

import org.vsg.cusp.core.runtime.CustomUnifiedServicePlatform;

/**
 * @author Vicente Yuen
 *
 */
public class StandardServerMonitorTest {

	/**
	 * 
	 */
	public StandardServerMonitorTest() {
		// TODO Auto-generated constructor stub
	}
	

	public void loadConfigurationForEngine() throws Exception {
		URL url = this.getClass().getResource("/test/conf.json");

		String[] args = new String[]{"start","-conf_file="+url.getFile()};
		
		CustomUnifiedServicePlatform ssm = new CustomUnifiedServicePlatform();
		ssm.setParentClassLoader( Thread.currentThread().getContextClassLoader() );
		ssm.load(args);
		
		// --- check load event ---
		ssm.start();

	}
	
	public static void main(String[] args) {
		StandardServerMonitorTest testCase = new StandardServerMonitorTest();
		
		try {
			testCase.loadConfigurationForEngine();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
