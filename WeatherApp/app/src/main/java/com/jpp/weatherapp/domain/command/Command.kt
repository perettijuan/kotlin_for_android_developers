package com.jpp.weatherapp.domain.command


/**
 * Interface that defines commands to be executed.
 *
 * Will perform a task and return a T type.
 *
 */
public interface Command<T> {

    /**
     * Executes the task
     */
    fun execute(): T
}