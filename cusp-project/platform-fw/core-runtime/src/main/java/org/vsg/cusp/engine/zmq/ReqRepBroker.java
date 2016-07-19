/**
 * 
 */
package org.vsg.cusp.engine.zmq;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zeromq.ZFrame;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;
import org.zeromq.ZMsg;

/**
 * @author Vicente Yuen
 *
 */
public class ReqRepBroker implements RunnableFuture {
	
	
	private static Logger logger = LoggerFactory.getLogger( ReqRepBroker.class );
	
	
	public ReqRepBroker() {
		
	}
	
	public ReqRepBroker(int brokerPort) {
		this.brokerPort = brokerPort;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.concurrent.Future#cancel(boolean)
	 */
	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.concurrent.Future#isCancelled()
	 */
	@Override
	public boolean isCancelled() {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.concurrent.Future#isDone()
	 */
	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.concurrent.Future#get()
	 */
	@Override
	public Object get() throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.concurrent.Future#get(long, java.util.concurrent.TimeUnit)
	 */
	@Override
	public Object get(long timeout, TimeUnit unit) throws InterruptedException,
			ExecutionException, TimeoutException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private int brokerPort = 8701;
	

	public int getBrokerPort() {
		return brokerPort;
	}

	public void setBrokerPort(int brokerPort) {
		this.brokerPort = brokerPort;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.concurrent.RunnableFuture#run()
	 */
	@Override
	public void run() {
		Context context = ZMQ.context(1);

		Socket frontend = context.socket(ZMQ.REP);
		String frontendAddress = "tcp://*:"+brokerPort;
		frontend.bind(frontendAddress);
		
		Socket taskSocket = context.socket(ZMQ.PUSH);
		taskSocket.bind("tcp://*:5560");

		Socket taskReceive = context.socket(ZMQ.PULL);
		String taskReceiveAddress = "tcp://*:5561";
		taskReceive.bind(taskReceiveAddress);		
		
		logger.info("launch ReqRepBroker at port: " + brokerPort);

		// Initialize poll set
		//Poller items = new Poller(1);
		//items.register(frontend, Poller.POLLIN);
		//items.register(backend, Poller.POLLIN);

		boolean more = false;
		byte[] message;
		
		// Switch messages between sockets
		while (!Thread.currentThread().isInterrupted()) {
			// poll and memorize multipart detection
			//items.poll();
			
			// --- reply content to another server ---
			byte[] reply = frontend.recv(0);
			/*
			StringBuilder output = new StringBuilder("reveive : ");
			for (byte con : reply) {
				output.append(con).append(" ");
			}
			System.out.println(output);			
			*/
			// --- assign to another push task ---
			taskSocket.send(reply , 0);
			
			
			byte[] receiveTask = taskReceive.recv(0);
			
            try {
				String request = new String(receiveTask, "UTF-8") ;
				// Send the message
				frontend.send(request.getBytes (ZMQ.CHARSET), 0);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
						
			//System.out.println("Received " + ": [" + new String(reply, ZMQ.CHARSET) + "]");

			/*

			if (items.pollin(0)) {

				while (true) {
					// receive message
					message = frontend.recv(0);
					more = frontend.hasReceiveMore();
					
					System.out.println("message -> " + message);

					// Broker it
					//backend.send(message, more ? ZMQ.SNDMORE : 0);
					if (!more) {
						break;
					}
				}
			}
			*/
			/*
			if (items.pollin(1)) {
				while (true) {
					// receive message
					message = backend.recv(0);
					more = backend.hasReceiveMore();
					// Broker it
					frontend.send(message, more ? ZMQ.SNDMORE : 0);
					if (!more) {
						break;
					}
				}
			}*/
		}
		
		// We never get here but clean up anyhow
		frontend.close();
		taskSocket.close();
		context.term();
	}

}
