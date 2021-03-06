package org.vsg.cusp.event;

import org.vsg.cusp.concurrent.AsyncResult;
import org.vsg.cusp.core.Handler;
import org.vsg.cusp.eventbus.MessageConsumer;


/**
 * Define message bus for system communication
 * @author ruanweibiao
 *
 */
public interface MessageBus {
	
	public static final String EVENT_METHOD_CHANNEL = "EVT_METHOD_CHANNEL";
	
	
	/**
	 * 
	 * @param address the address which is told by sending.
	 * @param message message object defind
	 * @param options
	 * @param replyHandler
	 * @return
	 */
	
	<T> MessageBus send(String address, Object message, DeliveryOptions options,Handler<AsyncResult<Message<T>>> replyHandler);	

	
	/**
	 * 
	 * @param address
	 * @return
	 */
	<T> MessageConsumer<T> consumer(String address);	
	
}

