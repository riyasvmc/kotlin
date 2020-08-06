/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.gradle.targets.js.npm

import kotlin.test.Test
import kotlin.test.assertTrue


class NpmRangeTest {
    @Test
    fun maxStartTest() {
        val nullRange1 = npmRange()
        val nullRange2 = npmRange()
        val maxStart1 = maxStart(
            nullRange1,
            nullRange2
        )
        assertTrue("Max start should be ${nullRange2.startVersion} but $maxStart1 found") {
            maxStart1 == nullRange2.startVersion
        }

        val startRange1 = npmRange(
            startMajor = 1
        )
        val maxStart2 = maxStart(
            startRange1,
            npmRange()
        )
        assertTrue("Max start should be ${startRange1.startVersion} but $maxStart2 found") {
            maxStart2 == startRange1.startVersion
        }

        val startRange2 = npmRange(startMajor = 2)
        val maxStart3 = maxStart(
            startRange1,
            startRange2
        )
        assertTrue("Max start should be ${startRange2.startVersion} but $maxStart3 found") {
            maxStart3 == startRange2.startVersion
        }
    }

    @Test
    fun minStartTest() {
        val nullRange1 = npmRange()
        val nullRange2 = npmRange()
        val minStart1 = minStart(
            nullRange1,
            nullRange2
        )
        assertTrue("Min start should be ${null} but $minStart1 found") {
            minStart1 == null
        }

        val startRange1 = npmRange(
            startMajor = 1
        )
        val minStart2 = minStart(
            startRange1,
            npmRange()
        )
        assertTrue("Min start should be ${null} but $minStart2 found") {
            minStart2 == null
        }

        val startRange2 = npmRange(startMajor = 2)
        val minStart3 = minStart(
            startRange1,
            startRange2
        )
        assertTrue("Min start should be ${startRange1.startVersion} but $minStart3 found") {
            minStart3 == startRange1.startVersion
        }
    }

    @Test
    fun maxEndTest() {
        val nullRange1 = npmRange()
        val nullRange2 = npmRange()
        val maxEnd1 = maxEnd(
            nullRange1,
            nullRange2
        )
        assertTrue("Max end should be ${null} but $maxEnd1 found") {
            maxEnd1 == null
        }

        val endRange1 = npmRange(
            endMajor = 1
        )
        val maxEnd2 = maxEnd(
            endRange1,
            npmRange()
        )
        assertTrue("Max end should be ${null} but $maxEnd2 found") {
            maxEnd2 == null
        }

        val endRange2 = npmRange(endMajor = 2)
        val maxEnd3 = maxEnd(
            endRange1,
            endRange2
        )
        assertTrue("Max end should be ${endRange2.endVersion} but $maxEnd3 found") {
            maxEnd3 == endRange2.endVersion
        }
    }

    @Test
    fun minEndTest() {
        val nullRange1 = npmRange()
        val nullRange2 = npmRange()
        val minEnd1 = minEnd(
            nullRange1,
            nullRange2
        )
        assertTrue("Min end should be ${nullRange1.endVersion} but $minEnd1 found") {
            minEnd1 == nullRange1.endVersion
        }

        val endRange1 = npmRange(
            endMajor = 1
        )
        val minEnd2 = minEnd(
            endRange1,
            npmRange()
        )
        assertTrue("Min end should be ${endRange1.endVersion} but $minEnd2 found") {
            minEnd2 == endRange1.endVersion
        }

        val endRange2 = npmRange(endMajor = 2)
        val minEnd3 = minEnd(
            endRange1,
            endRange2
        )
        assertTrue("Min end should be ${endRange1.endVersion} but $minEnd3 found") {
            minEnd3 == endRange1.endVersion
        }
    }
}

private fun npmRange(
    startMajor: Int? = null,
    startMinor: Int? = null,
    startPatch: Int? = null,
    endMajor: Int? = null,
    endMinor: Int? = null,
    endPatch: Int? = null,
    startInclusive: Boolean = false,
    endInclusive: Boolean = false
): NpmRange =
    NpmRange(
        startVersion = semVer(startMajor, startMinor, startPatch),
        endVersion = semVer(endMajor, endMinor, endPatch),
        startInclusive = startInclusive,
        endInclusive = endInclusive
    )

private fun semVer(
    major: Int? = null,
    minor: Int? = null,
    patch: Int? = null
): SemVer? =
    if (major == null && minor == null && patch == null)
        null
    else {
        SemVer(
            (major ?: 0).toBigInteger(),
            (minor ?: 0).toBigInteger(),
            (patch ?: 0).toBigInteger()
        )
    }