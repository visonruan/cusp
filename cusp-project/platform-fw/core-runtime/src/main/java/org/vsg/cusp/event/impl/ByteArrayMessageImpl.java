package org.vsg.cusp.event.impl;

import org.vsg.cusp.event.Message;
import org.vsg.cusp.eventbus.AsyncResult;
import org.vsg.cusp.eventbus.DeliveryOptions;
import org.vsg.cusp.eventbus.Handler;


public class ByteArrayMessageImpl extends AbstractMessage<byte[]> {

	@Override
	public String replyAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reply(Object message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> void reply(Object message,
			Handler<AsyncResult<Message<R>>> replyHandler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reply(Object message, DeliveryOptions options) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> void reply(Object message, DeliveryOptions options,
			Handler<AsyncResult<Message<R>>> replyHandler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fail(int failureCode, String message) {
		// TODO Auto-generated method stub
		
	}



}
