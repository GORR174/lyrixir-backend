package net.catstack.lyrixir.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory

fun Any.injectLogger(): Logger = LoggerFactory.getLogger(this::class.java)