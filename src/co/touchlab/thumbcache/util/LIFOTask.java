package co.touchlab.thumbcache.util;

import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicLong;

/**
 * User: William Sanville
 * Date: 7/17/12
 * Time: 6:27 PM
 * A class for use with a priority queue that increases the priority as items are added.
 */
public class LIFOTask extends FutureTask<Object> implements Comparable<LIFOTask>
{
    private static long counter = 0;
    private final long priority;

    public LIFOTask(Runnable runnable)
    {
        super(runnable, new Object());
        priority = counter++;
    }

    public long getPriority()
    {
        return priority;
    }

    @Override
    public int compareTo(LIFOTask other)
    {
        return priority > other.getPriority() ? -1 : 1;
    }
}
