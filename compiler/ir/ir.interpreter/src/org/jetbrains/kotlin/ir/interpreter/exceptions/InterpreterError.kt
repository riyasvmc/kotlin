/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.interpreter.exceptions

open class InterpreterError(override val message: String) : InterpreterException()

class InterpreterMethodNotFoundError(override val message: String) : InterpreterError(message)

class InterpreterTimeOutError : InterpreterError("Exceeded execution limit of constexpr expression")

class InterpreterEmptyReturnStackError : InterpreterError("Return values stack is empty")