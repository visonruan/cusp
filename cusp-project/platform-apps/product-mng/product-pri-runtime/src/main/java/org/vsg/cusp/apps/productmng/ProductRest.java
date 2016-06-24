package org.vsg.cusp.apps.productmng;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.vsg.cusp.eventbus.EventBus;



/**
 * 
 * @author vison ruan
 *
 */

@Path("/products")
public class ProductRest {
	
	@Inject
	private EventBus eventBus;
	
	@GET
	@Path("/{id}")
	public void getProduct(AsyncResponse asyncResponse , @PathParam("id") String productId , int test) throws InterruptedException  {
		
		try {
			// --- call response handle ---
			ResponseBuilder rb = Response.ok("hello world, VISON , my dear");
			rb.type( MediaType.TEXT_PLAIN );
			
			System.out.println("show event bus : " + eventBus);

			Response jaxrs = rb.build();
			asyncResponse.resume(jaxrs);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
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
