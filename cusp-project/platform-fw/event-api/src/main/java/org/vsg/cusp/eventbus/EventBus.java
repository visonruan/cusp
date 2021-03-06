package org.vsg.cusp.eventbus;

import org.vsg.cusp.concurrent.AsyncResult;
import org.vsg.cusp.core.Handler;
import org.vsg.cusp.event.DeliveryOptions;
import org.vsg.cusp.event.Message;
import org.vsg.cusp.event.MessageCodec;

/**
 * 
 * @author Vicente Yuen
 *
 */
public interface EventBus {

	/**
	 * Sends a message.
	 * <p>
	 * The message will be delivered to at most one of the handlers registered
	 * to the address.
	 *
	 * @param address
	 *            the address to send it to
	 * @param message
	 *            the message, may be {@code null}
	 * @return a reference to this, so the API can be used fluently
	 */
	EventBus send(String address, Object message);

	/**
	 * Like {@link #send(String, Object)} but specifying a {@code replyHandler}
	 * that will be called if the recipient subsequently replies to the message.
	 *
	 * @param address
	 *            the address to send it to
	 * @param message
	 *            the message, may be {@code null}
	 * @param replyHandler
	 *            reply handler will be called when any reply from the recipient
	 *            is received, may be {@code null}
	 * @return a reference to this, so the API can be used fluently
	 */
	<T> EventBus send(String address, Object message,
			Handler<AsyncResult<Message<T>>> replyHandler);

	/**
	 * Like {@link #send(String, Object)} but specifying {@code options} that
	 * can be used to configure the delivery.
	 *
	 * @param address
	 *            the address to send it to
	 * @param message
	 *            the message, may be {@code null}
	 * @param options
	 *            delivery options
	 * @return a reference to this, so the API can be used fluently
	 */
	EventBus send(String address, Object message, DeliveryOptions options);

	/**
	 * Like {@link #send(String, Object, DeliveryOptions)} but specifying a
	 * {@code replyHandler} that will be called if the recipient subsequently
	 * replies to the message.
	 *
	 * @param address
	 *            the address to send it to
	 * @param message
	 *            the message, may be {@code null}
	 * @param options
	 *            delivery options
	 * @param replyHandler
	 *            reply handler will be called when any reply from the recipient
	 *            is received, may be {@code null}
	 * @return a reference to this, so the API can be used fluently
	 */
	<T> EventBus send(String address, Object message, DeliveryOptions options,
			Handler<AsyncResult<Message<T>>> replyHandler);

	/**
	 * Publish a message.
	 * <p>
	 * The message will be delivered to all handlers registered to the address.
	 *
	 * @param address
	 *            the address to publish it to
	 * @param message
	 *            the message, may be {@code null}
	 * @return a reference to this, so the API can be used fluently
	 *
	 */
	EventBus publish(String address, Object message);

	/**
	 * Like {@link #publish(String, Object)} but specifying {@code options} that
	 * can be used to configure the delivery.
	 *
	 * @param address
	 *            the address to publish it to
	 * @param message
	 *            the message, may be {@code null}
	 * @param options
	 *            the delivery options
	 * @return a reference to this, so the API can be used fluently
	 */
	EventBus publish(String address, Object message, DeliveryOptions options);

	/**
	 * Create a message consumer against the specified address.
	 * <p>
	 * The returned consumer is not yet registered at the address, registration
	 * will be effective when
	 * {@link MessageConsumer#handler(io.vertx.core.Handler)} is called.
	 *
	 * @param address
	 *            the address that it will register it at
	 * @return the event bus message consumer
	 */
	<T> MessageConsumer<T> consumer(String address);

	/**
	 * Create a consumer and register it against the specified address.
	 *
	 * @param address
	 *            the address that will register it at
	 * @param handler
	 *            the handler that will process the received messages
	 *
	 * @return the event bus message consumer
	 */
	<T> MessageConsumer<T> consumer(String address, Handler<Message<T>> handler);


	/**
	 * Create a message sender against the specified address.
	 * <p>
	 * The returned sender will invoke the {@link #send(String, Object)} method
	 * when the stream {@link io.vertx.core.streams.WriteStream#write(Object)}
	 * method is called with the sender address and the provided data.
	 *
	 * @param address
	 *            the address to send it to
	 * @return The sender
	 */
	<T> MessageProducer<T> sender(String address);

	/**
	 * Like {@link #sender(String)} but specifying delivery options that will be
	 * used for configuring the delivery of the message.
	 *
	 * @param address
	 *            the address to send it to
	 * @param options
	 *            the delivery options
	 * @return The sender
	 */
	<T> MessageProducer<T> sender(String address, DeliveryOptions options);

	/**
	 * Create a message publisher against the specified address.
	 * <p>
	 * The returned publisher will invoke the {@link #publish(String, Object)}
	 * method when the stream
	 * {@link io.vertx.core.streams.WriteStream#write(Object)} method is called
	 * with the publisher address and the provided data.
	 *
	 * @param address
	 *            The address to publish it to
	 * @return The publisher
	 */
	<T> MessageProducer<T> publisher(String address);

	/**
	 * Like {@link #publisher(String)} but specifying delivery options that will
	 * be used for configuring the delivery of the message.
	 *
	 * @param address
	 *            the address to publish it to
	 * @param options
	 *            the delivery options
	 * @return The publisher
	 */
	<T> MessageProducer<T> publisher(String address, DeliveryOptions options);

	/**
	 * Register a message codec.
	 * <p>
	 * You can register a message codec if you want to send any non standard
	 * message across the event bus. E.g. you might want to send POJOs directly
	 * across the event bus.
	 * <p>
	 * To use a message codec for a send, you should specify it in the delivery
	 * options.
	 *
	 * @param codec
	 *            the message codec to register
	 * @return a reference to this, so the API can be used fluently
	 */
	EventBus registerCodec(MessageCodec codec);

	/**
	 * Unregister a message codec.
	 * <p>
	 * 
	 * @param name
	 *            the name of the codec
	 * @return a reference to this, so the API can be used fluently
	 */
	EventBus unregisterCodec(String name);

	/**
	 * Register a default message codec.
	 * <p>
	 * You can register a message codec if you want to send any non standard
	 * message across the event bus. E.g. you might want to send POJOs directly
	 * across the event bus.
	 * <p>
	 * Default message codecs will be used to serialise any messages of the
	 * specified type on the event bus without the codec having to be specified
	 * in the delivery options.
	 *
	 * @param clazz
	 *            the class for which to use this codec
	 * @param codec
	 *            the message codec to register
	 * @return a reference to this, so the API can be used fluently
	 */
	<T> EventBus registerDefaultCodec(Class<T> clazz, MessageCodec<T, ?> codec);

	/**
	 * Unregister a default message codec.
	 * <p>
	 * 
	 * @param clazz
	 *            the class for which the codec was registered
	 * @return a reference to this, so the API can be used fluently
	 */
	EventBus unregisterDefaultCodec(Class clazz);

	/**
	 * Start the event bus. This would not normally be called in user code
	 *
	 * @param completionHandler
	 *            handler will be called when event bus is started
	 */
	void start(Handler<AsyncResult<Void>> completionHandler);

	/**
	 * Close the event bus and release any resources held. This would not
	 * normally be called in user code
	 *
	 * @param completionHandler
	 *            may be {@code null}
	 */
	void close(Handler<AsyncResult<Void>> completionHandler);

}
