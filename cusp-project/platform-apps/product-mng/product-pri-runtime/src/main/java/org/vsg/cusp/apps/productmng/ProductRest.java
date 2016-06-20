package org.vsg.cusp.apps.productmng;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 
 * @author vison ruan
 *
 */

@Path("/products")
public class ProductRest {
	
	
	
	@GET
	@Path("/{id}")
	public void getProduct(
			@Suspended AsyncResponse asyncResponse , 
			@PathParam("id") String productId) {

		
		// --- call response handle ---
        Response jaxrs = Response.ok("hello world, VISON").type(MediaType.TEXT_PLAIN).build();
        asyncResponse.resume(jaxrs);	
		
        /*		
		CountDownLatch latch = new CountDownLatch(2);
		vertx.eventBus().localConsumer("moduleStarted").handler(message -> {
			System.out.println(message.body());
			latch.countDown();
		});

		
		// --- call service verticle ---
		vertx.deployVerticle("service:vertx.mods.vertx-services" , res -> {
			
			System.out.println("product : " + productId + " , " + res.succeeded() );

			// --- call response handle ---
            Response jaxrs = Response.ok("hello world, VISON").type(MediaType.TEXT_PLAIN).build();
            asyncResponse.resume(jaxrs);	
			
		});
		*/


		
	}

	
}
