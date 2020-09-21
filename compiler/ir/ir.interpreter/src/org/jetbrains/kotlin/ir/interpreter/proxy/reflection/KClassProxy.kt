/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.interpreter.proxy.reflection

import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.ir.interpreter.IrInterpreter
import org.jetbrains.kotlin.ir.interpreter.internalName
import org.jetbrains.kotlin.ir.interpreter.proxy.Proxy
import org.jetbrains.kotlin.ir.interpreter.state.reflection.KClassState
import kotlin.reflect.*

internal class KClassProxy(
    override val state: KClassState, override val interpreter: IrInterpreter
) : ReflectionProxy, KClass<Proxy> {
    override val simpleName: String?
        get() = state.classReference.name.takeIf { !it.isSpecial }?.asString()
    override val qualifiedName: String?
        get() = if (!state.classReference.name.isSpecial) state.classReference.internalName() else null

    override val members: Collection<KCallable<*>>
        get() = state.getMembers(interpreter)
    override val constructors: Collection<KFunction<Proxy>>
        get() = state.getConstructors(interpreter)
    override val nestedClasses: Collection<KClass<*>>
        get() = TODO("Not yet implemented")
    override val objectInstance: Proxy?
        get() = TODO("Not yet implemented")
    override val typeParameters: List<KTypeParameter>
        get() = state.getTypeParameters(interpreter)
    override val supertypes: List<KType>
        get() = state.getSupertypes(interpreter)
    override val sealedSubclasses: List<KClass<out Proxy>>
        get() = TODO("Not yet implemented")
    override val annotations: List<Annotation>
        get() = TODO("Not yet implemented")

    override val visibility: KVisibility?
        get() = state.classReference.visibility.toKVisibility()
    override val isFinal: Boolean
        get() = state.classReference.modality == Modality.FINAL
    override val isOpen: Boolean
        get() = state.classReference.modality == Modality.OPEN
    override val isAbstract: Boolean
        get() = state.classReference.modality == Modality.ABSTRACT
    override val isSealed: Boolean
        get() = state.classReference.modality == Modality.SEALED
    override val isData: Boolean
        get() = state.classReference.isData
    override val isInner: Boolean
        get() = state.classReference.isInner
    override val isCompanion: Boolean
        get() = state.classReference.isCompanion
    override val isFun: Boolean
        get() = state.classReference.isFun

    override fun isInstance(value: Any?): Boolean {
        TODO("Not yet implemented")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is KClassProxy) return false

        return state == other.state
    }

    override fun hashCode(): Int {
        return state.hashCode()
    }

    override fun toString(): String {
        return state.toString()
    }
}