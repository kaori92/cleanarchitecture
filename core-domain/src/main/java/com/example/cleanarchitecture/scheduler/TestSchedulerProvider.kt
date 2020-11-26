package com.example.cleanarchitecture.scheduler

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

object TestSchedulerProvider : SchedulerProvider {
    override fun main(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun io(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun computation(): Scheduler {
        return Schedulers.trampoline()
    }
}