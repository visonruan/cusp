/**
 * 
 */
package org.vsg.cusp.platform.utils;

import io.vertx.core.Vertx;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.inject.Inject;

import org.vsg.common.model.json.Result;


/**
 * @author vison
 *
 */
public class ServiceInvokerManagerImpl implements ServiceInvokerManager {
	
	// --- get the proxy ---
	@Inject
	private Vertx vertx;	

	/* (non-Javadoc)
	 * @see utils.ServiceInvokerManager#invokeService(java.lang.String, utils.ServiceInvokerHandler)
	 */
	@Override
	public void invokeService(String serviceName, Map<?,?> params,ServiceInvokerHandler handler)
			throws ServiceInvokerException {
		
		if (serviceName.startsWith("service:")){
			// call service method
			handleByVertx(serviceName , handler);
		}
		
		else if (serviceName.startsWith("class:")) {
			StringTokenizer st = new StringTokenizer(serviceName , ":");
			int tokens = st.countTokens();
			
			int index = 0 ;
			
			String className = null;
			String method = null;
			while ( st.hasMoreElements()) {
				String value = st.nextToken();

				if (index == 2) {
					method = value; 
				}
				else if (index == 1) {
					className = value;
				}

				if (index == tokens - 1) {
					break;
				}
				
				index++;

			}

			
			Result result = new Result();
			// --- invoke class ---
			try {
				Class proxyCls = Thread.currentThread().getContextClassLoader().loadClass(className);
				
				Method meth = proxyCls.getMethod(method, Map.class);
				
				Object inst =  proxyCls.newInstance();
				
				Object retrn = meth.invoke(inst ,new HashMap());
				
				// --- handle ok ---
				result.setSuccess( true );
				result.setData( retrn );
				
			} catch (ClassNotFoundException | InstantiationException | IllegalArgumentException e) {
				// TODO Auto-generated catch block
				result.setErrorMessage( e.getLocalizedMessage() );
			}  catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  finally {
				
				// --- calll handle value ---
				handler.handle( result );				
			}
			

			
		}
		


	}
	
	
	private void handleByVertx(String serviceName, ServiceInvokerHandler handler) {
		vertx.deployVerticle(serviceName , res -> {
			
			Result result = new Result();
			result.setSuccess( res.succeeded() );
			// --- calll handle value ---
			handler.handle( result );
		
			
			
		});		
	}

}
