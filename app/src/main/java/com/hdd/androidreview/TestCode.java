//package com.hdd.androidreview;
//
//
//import android.graphics.PixelFormat;
//import android.os.Looper;
//import android.os.RemoteException;
//import android.util.Log;
//import android.view.InputQueue;
//import android.view.View;
//import android.view.WindowManager;
//
//public class TestCode {
//    private void performTraversals() {
//        {
//
//            if (!mStopped) {
//               ...
//
//                // Ask host how big it wants to be
//                performMeasure(childWidthMeasureSpec, childHeightMeasureSpec);
//
//                  ...
//            }
//            ...
//
//            if (didLayout) {
//                performLayout(lp, desiredWindowWidth, desiredWindowHeight);
//
//          ...
//            }
//
//            if (!cancelDraw && !newSurface) {
//           ...
//
//                performDraw();
//
//            }
//
//        }
//    }
//
//
//    public void addView(View view, ViewGroup.LayoutParams params,
//                        Display display, Window parentWindow) {
//        ...
//        synchronized (mLock) {
//            ...
//
//            root = new ViewRootImpl(view.getContext(), display);
//          ...
//        }
//        try {
//            root.setView(view, wparams, panelParentView);
//        } catch (RuntimeException e) {
//            ...
//            throw e;
//        }
//    }
//
//
//    public void setView(View view, WindowManager.LayoutParams attrs, View panelParentView) {
//        synchronized (this) {
//                ...
//            requestLayout();
//                ...
//            try {
//                    ...
//                res = mWindowSession.addToDisplay(mWindow, mSeq, mWindowAttributes,
//                        getHostVisibility(), mDisplay.getDisplayId(),
//                        mAttachInfo.mContentInsets, mInputChannel);
//            } catch (RemoteException e) {
//                   ...
//            }
//        }
//    }
//
//
//    @Override
//    public void requestLayout() {
//        if (!mHandlingLayoutInLayoutRequest) {
//            checkThread();
//            mLayoutRequested = true;
//            scheduleTraversals();
//        }
//    }
//
//
//    @Override
//    public int addToDisplay(IWindow window, int seq, WindowManager.LayoutParams attrs,
//                            int viewVisibility, int displayId, Rect outContentInsets,
//                            InputChannel outInputChannel) {
//        return mService.addWindow(this, window, seq, attrs, viewVisibility, displayId,
//                outContentInsets, outInputChannel);
//    }
//
//
//    void doTraversal() {
//        if (mTraversalScheduled) {
//           ...
//            try {
//                performTraversals();
//            } finally {
//                Trace.traceEnd(Trace.TRACE_TAG_VIEW);
//            }
//            ...
//        }
//    }
//
//
//    private void performLayout(WindowManager.LayoutParams lp, int desiredWindowWidth,
//                               int desiredWindowHeight) {
//        mLayoutRequested = false;
//        mScrollMayChange = true;
//        mInLayout = true;
//
//        final View host = mView;
//        if (DEBUG_ORIENTATION || DEBUG_LAYOUT) {
//            Log.v(TAG, "Laying out " + host + " to (" +
//                    host.getMeasuredWidth() + ", " + host.getMeasuredHeight() + ")");
//        }
//
//        Trace.traceBegin(Trace.TRACE_TAG_VIEW, "layout");
//        try {
//            host.layout(0, 0, host.getMeasuredWidth(), host.getMeasuredHeight());
//
//            mInLayout = false;
//            int numViewsRequestingLayout = mLayoutRequesters.size();
//            if (numViewsRequestingLayout > 0) {
//                // requestLayout() was called during layout.
//                // If no layout-request flags are set on the requesting views, there is no problem.
//                // If some requests are still pending, then we need to clear those flags and do
//                // a full request/measure/layout pass to handle this situation.
//                ArrayList<View> validLayoutRequesters = getValidLayoutRequesters(mLayoutRequesters,
//                        false);
//                if (validLayoutRequesters != null) {
//                    // Set this flag to indicate that any further requests are happening during
//                    // the second pass, which may result in posting those requests to the next
//                    // frame instead
//                    mHandlingLayoutInLayoutRequest = true;
//
//                    // Process fresh layout requests, then measure and layout
//                    int numValidRequests = validLayoutRequesters.size();
//                    for (int i = 0; i < numValidRequests; ++i) {
//                        final View view = validLayoutRequesters.get(i);
//                        Log.w("View", "requestLayout() improperly called by " + view +
//                                " during layout: running second layout pass");
//                        view.requestLayout();
//                    }
//                    measureHierarchy(host, lp, mView.getContext().getResources(),
//                            desiredWindowWidth, desiredWindowHeight);
//                    mInLayout = true;
//                    host.layout(0, 0, host.getMeasuredWidth(), host.getMeasuredHeight());
//
//                    mHandlingLayoutInLayoutRequest = false;
//
//                    // Check the valid requests again, this time without checking/clearing the
//                    // layout flags, since requests happening during the second pass get noop'd
//                    validLayoutRequesters = getValidLayoutRequesters(mLayoutRequesters, true);
//                    if (validLayoutRequesters != null) {
//                        final ArrayList<View> finalRequesters = validLayoutRequesters;
//                        // Post second-pass requests to the next frame
//                        getRunQueue().post(new Runnable() {
//                            @Override
//                            public void run() {
//                                int numValidRequests = finalRequesters.size();
//                                for (int i = 0; i < numValidRequests; ++i) {
//                                    final View view = finalRequesters.get(i);
//                                    Log.w("View", "requestLayout() improperly called by " + view +
//                                            " during second layout pass: posting in next frame");
//                                    view.requestLayout();
//                                }
//                            }
//                        });
//                    }
//                }
//
//            }
//        } finally {
//            Trace.traceEnd(Trace.TRACE_TAG_VIEW);
//        }
//        mInLayout = false;
//    }
//
