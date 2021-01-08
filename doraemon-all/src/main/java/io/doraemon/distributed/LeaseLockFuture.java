package io.doraemon.distributed;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

public interface LeaseLockFuture<T> extends java.util.concurrent.Future<T>, CompletionStage<T>{
	/**
     * Returns {@code true} if and only if the I/O operation was completed
     * successfully.
     * 
     * @return {@code true} if future was completed successfully
     */
    boolean isSuccess();

    /**
     * Returns the cause of the failed I/O operation if the I/O operation has
     * failed.
     *
     * @return the cause of the failure.
     *         {@code null} if succeeded or this future is not
     *         completed yet.
     */
    Throwable cause();
    
    /**
     * Return the result without blocking. If the future is not done yet this will return {@code null}.
     *
     * As it is possible that a {@code null} value is used to mark the future as successful you also need to check
     * if the future is really done with {@link #isDone()} and not relay on the returned {@code null} value.
     * 
     * @return object
     */
    T getNow();
    
    /**
     * Returns the result value when complete, or throws an
     * (unchecked) exception if completed exceptionally. To better
     * conform with the use of common functional forms, if a
     * computation involved in the completion of this
     * CompletableFuture threw an exception.
     *
     * @return the result value
     */
    T join();
    
    /**
     * Waits for this future to be completed within the
     * specified time limit.
     *
     * @param timeout - wait timeout
     * @param unit - time unit
     * @return {@code true} if and only if the future was completed within
     *         the specified time limit
     *
     * @throws InterruptedException
     *         if the current thread was interrupted
     */
    boolean await(long timeout, TimeUnit unit) throws InterruptedException;

    /**
     * Waits for this future to be completed within the
     * specified time limit.
     *
     * @param timeoutMillis - timeout value
     * @return {@code true} if and only if the future was completed within
     *         the specified time limit
     *
     * @throws InterruptedException
     *         if the current thread was interrupted
     */
    boolean await(long timeoutMillis) throws InterruptedException;
    
    /**
     * Waits for this future until it is done, and rethrows the cause of the failure if this future
     * failed.
     *
     * @throws InterruptedException
     *         if the current thread was interrupted
     * @return Future object
     */
    LeaseLockFuture<T> sync() throws InterruptedException;

    /**
     * Waits for this future until it is done, and rethrows the cause of the failure if this future
     * failed.
     * 
     * @return Future object
     */
    LeaseLockFuture<T> syncUninterruptibly();

    /**
     * Waits for this future to be completed.
     *
     * @throws InterruptedException
     *         if the current thread was interrupted
     * @return Future object
     */
    LeaseLockFuture<T> await() throws InterruptedException;

    /**
     * Waits for this future to be completed without
     * interruption.  This method catches an {@link InterruptedException} and
     * discards it silently.
     * 
     * @return Future object
     */
    LeaseLockFuture<T> awaitUninterruptibly();

    /**
     * Waits for this future to be completed within the
     * specified time limit without interruption.  This method catches an
     * {@link InterruptedException} and discards it silently.
     *
     * @param timeout - timeout value
     * @param unit - timeout unit value
     * @return {@code true} if and only if the future was completed within
     *         the specified time limit
     */
    boolean awaitUninterruptibly(long timeout, TimeUnit unit);

    /**
     * Waits for this future to be completed within the
     * specified time limit without interruption.  This method catches an
     * {@link InterruptedException} and discards it silently.
     * 
     * @param timeoutMillis - timeout value
     * @return {@code true} if and only if the future was completed within
     *         the specified time limit
     */
    boolean awaitUninterruptibly(long timeoutMillis);

    void onComplete(BiConsumer<? super T, ? super Throwable> action);
}
