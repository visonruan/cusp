/**
 * 
 */
package org.vsg.cusp.event.register;

import java.util.Set;

import org.vsg.cusp.event.EventMethodDescription;
import org.vsg.cusp.event.EventMethodRegister;

/**
 * @author ruanweibiao
 *
 */
public class EhcacheEventMethodRegister implements EventMethodRegister {
	
	
	

	/* (non-Javadoc)
	 * @see org.vsg.cusp.event.EventMethodRegister#registerEvent(java.lang.String, org.vsg.cusp.event.EventMethodDescription)
	 */
	@Override
	public void registerEvent(String eventName,
			EventMethodDescription methodDescription) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.vsg.cusp.event.EventMethodRegister#findAllRegisterEventsById(java.lang.String)
	 */
	@Override
	public Set<EventMethodDescription> findAllRegisterEventsByName(String eventName) {
		
		System.out.println("event name : " + eventName);

		return null;
	}

	/* (non-Javadoc)
	 * @see org.vsg.cusp.event.EventMethodRegister#unRegisterEvent(java.lang.String)
	 */
	@Override
	public void unRegisterEvent(String eventName) {
		// TODO Auto-generated method stub

	}

}
