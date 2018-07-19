/**
 * 
 */
package com.duyi.commons;

import org.springframework.context.SmartLifecycle;

/**
 * @author wangyan
 *
 */
public abstract class AbstractSmartLifecycle implements SmartLifecycle {

	private boolean running=false;

	/* (non-Javadoc)
	 * @see org.springframework.context.Lifecycle#start()
	 */
	@Override
	public void start() {
		doStart();
		running=true;
	}

	protected abstract void doStart();
	/* (non-Javadoc)
	 * @see org.springframework.context.Lifecycle#stop()
	 */
	@Override
	public void stop() {
		doStop();
		running=false;
	}
	protected abstract void doStop();
	/* (non-Javadoc)
	 * @see org.springframework.context.Lifecycle#isRunning()
	 */
	@Override
	public boolean isRunning() {
		return running;
	}

	/* (non-Javadoc)
	 * @see org.springframework.context.Phased#getPhase()
	 */
	@Override
	public int getPhase() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.springframework.context.SmartLifecycle#isAutoStartup()
	 */
	@Override
	public boolean isAutoStartup() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.context.SmartLifecycle#stop(java.lang.Runnable)
	 */
	@Override
	public void stop(Runnable callback) {
		callback.run();
		stop();
	}

}
