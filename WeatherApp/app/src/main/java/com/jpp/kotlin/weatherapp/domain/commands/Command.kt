package com.jpp.kotlin.weatherapp.domain.commands

public interface Command<T> {
    fun execute(): T
}