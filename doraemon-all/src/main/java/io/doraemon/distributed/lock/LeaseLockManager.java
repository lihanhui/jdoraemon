package io.doraemon.distributed.lock;

public interface LeaseLockManager {
	public LeaseLock getLeaseLock(String name);
}
