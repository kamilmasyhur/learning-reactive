package com.bootcamp.learningreactive.executors

import android.os.Handler
import android.os.Looper
import androidx.annotation.NonNull
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppExecutors private constructor(
    diskIO: Executor = Executors.newSingleThreadExecutor(),
    networkIO: Executor = Executors.newFixedThreadPool(3),
    mainThread: Executor = MainThreadExecutor()
) {
    private val mDiskIO: Executor = diskIO
    private val mNetworkIO: Executor = networkIO
    private val mMainThread: Executor = mainThread

    fun diskIO(): Executor {
        return mDiskIO
    }

    fun networkIO(): Executor {
        return mNetworkIO
    }

    fun mainThread(): Executor {
        return mMainThread
    }

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler: Handler = Handler(Looper.getMainLooper())
        override fun execute(@NonNull command: Runnable?) {
            mainThreadHandler.post(command)
        }
    }

    companion object {
        @Volatile
        private var mInstance: AppExecutors? = null
        val instance: AppExecutors?
            get() {
                if (mInstance == null) {
                    synchronized(
                        AppExecutors::class.java
                    ) { mInstance = AppExecutors() }
                }
                return mInstance
            }

        fun xDisk(@NonNull command: Runnable?) {
            instance!!.diskIO().execute(command)
        }

        fun xNet(@NonNull command: Runnable?) {
            instance!!.networkIO().execute(command)
        }

        fun xMain(@NonNull command: Runnable?) {
            instance!!.mainThread().execute(command)
        }
    }

}
