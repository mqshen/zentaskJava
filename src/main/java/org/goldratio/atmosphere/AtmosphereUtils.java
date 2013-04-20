package org.goldratio.atmosphere;

import java.util.concurrent.CountDownLatch;
import javax.servlet.http.HttpServletRequest;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.atmosphere.cpr.AtmosphereResourceEventListenerAdapter;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterFactory;
import org.atmosphere.cpr.Meteor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * ClassName: AtmosphereUtils <br/>
 * Function: <br/>
 * Reason: <br/>
 * date: Apr 11, 2013 11:48:32 PM <br/>
 * 
 * @author GoldRatio
 * @version 1.0
 */

public class AtmosphereUtils {
	public static final Logger LOG = LoggerFactory.getLogger(AtmosphereUtils.class);

	public static AtmosphereResource getAtmosphereResource(
			HttpServletRequest request) {
		return getMeteor(request).getAtmosphereResource();
	}

	public static Meteor getMeteor(HttpServletRequest request) {
		return Meteor.build(request);
	}

	public static void suspend(final AtmosphereResource resource) {

		final CountDownLatch countDownLatch = new CountDownLatch(1);
		resource.addEventListener(new AtmosphereResourceEventListenerAdapter() {
			@Override
			public void onSuspend(AtmosphereResourceEvent event) {
				countDownLatch.countDown();
				resource.removeEventListener(this);
			}

			@Override
			public void onDisconnect(AtmosphereResourceEvent event) {
				super.onDisconnect(event);
			}

			@Override
			public void onBroadcast(AtmosphereResourceEvent event) {
				super.onBroadcast(event);
			}

		});
		AtmosphereUtils.lookupBroadcaster().addAtmosphereResource(resource);

		if (AtmosphereResource.TRANSPORT.LONG_POLLING.equals(resource
				.transport())) {
			resource.resumeOnBroadcast(true).suspend(-1);
		} else {
			resource.suspend(-1);
		}

		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			LOG.error("Interrupted while trying to suspend resource {}",
					resource);
		}
	}

	public static Broadcaster lookupBroadcaster() {
		Broadcaster b = BroadcasterFactory.getDefault().get();
		return b;
	}

}
