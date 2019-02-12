//package com.hdd.androidreview;
//
//import android.os.Message;
//import android.util.Log;
//
//public class TestCode {
//
//
//    boolean enqueueMessage(Message msg, long when) {
//        // msg 必须有target也就是必须有handler
//        if (msg.target == null) {
//            throw new IllegalArgumentException("Message must have a target.");
//        }
//        if (msg.isInUse()) {
//            throw new IllegalStateException(msg + " This message is already in use.");
//        }
//        //插入消息队列的时候需要做同步，因为会有多个线程同时做往这个队列插入消息
//        synchronized (this) {
//            ...
//            msg.markInUse();
//            //when 表示这个消息执行的时间，队列是按照消息执行时间排序的
//            //如果handler 调用的是postDelay 那么when=SystemClock.uptimeMillis()+delayMillis
//            msg.when = when;
//            Message p = mMessages;
//            boolean needWake;
//            if (p == null || when == 0 || when < p.when) {
//                // p==null 表示当前消息队列没有消息
//                msg.next = p;
//                mMessages = msg;
//                //需要唤醒主线程，如果队列没有元素，主线程会堵塞在管道的读端，这时
//                //候队列突然有消息了，就会往管道写入字符，唤醒主线程
//                needWake = mBlocked;
//            } else {
//                needWake = mBlocked && p.target == null && msg.isAsynchronous();
//                Message prev;
//                //将消息放到队列的确切位置，队列是按照msg的when 排序的，链表操作自己看咯
//                for (; ; ) {
//                    prev = p;
//                    p = p.next;
//                    if (p == null || when < p.when) {
//                        break;
//                    }
//                    if (needWake && p.isAsynchronous()) {
//                        needWake = false;
//                    }
//                }
//                msg.next = p; // invariant: p == prev.next
//                prev.next = msg;
//            }
//
//            // 如果需要唤醒Looper线程，这里调用native的方法实现epoll机制唤醒线程，我们就不在深入探讨了
//            if (needWake) {
//                nativeWake(mPtr);
//            }
//        }
//        return true;
//    }
//}
