package edu.tamu.aser.rvtest.buffer;
/* from http://www.doc.ic.ac.uk/~jnm/book/ */
/* Concurrency: State Models & Java Programs - Jeff Magee & Jeff Kramer */
/* has a deadlock */

/*********************BUFFER*****************************/

public class BufferImpl{

    final protected Object[] buf;
    protected int in = 0;
    protected int out= 0;
    protected int count= 0;
    final protected int size;

    public BufferImpl(int size) {
        this.size = size;
        buf = new Object[size];
    }

    public synchronized void put(Object o) throws InterruptedException {
        while (count==size)
           wait();
//	buf[in] = o;
        ++count;
//        in=(in+1) % size;
        notify(); // should be notify all                                                                                                                                                                    
    }

    public synchronized void get() throws InterruptedException {
	while (count==0)
           wait();
//	Object o =null;
//			buf[out];
//	buf[out]=null;
	--count;
//	out=(out+1) % size;
	notify(); // should be notify all                                                                                                                                                                    
//	return (o);
    }
}