/**
 * 
 */
package org.vsg.deferred.impl;

import org.vsg.deferred.Promise;

/**
 * @author ruanweibiao
 *
 */
public class DeferredObject<D, F extends Throwable , P> extends AbstractPromise<D, F, P> {
	
	public Promise<D, F, P> promise() {
		return this;
	}	
	
}
