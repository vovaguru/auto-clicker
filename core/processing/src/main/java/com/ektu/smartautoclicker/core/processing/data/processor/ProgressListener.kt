/*
 * Copyright (C) 2023 Kevin Buzeau
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ektu.smartautoclicker.core.processing.data.processor

import android.content.Context

import com.ektu.smartautoclicker.core.detection.DetectionResult
import com.ektu.smartautoclicker.core.domain.model.condition.Condition
import com.ektu.smartautoclicker.core.domain.model.event.Event
import com.ektu.smartautoclicker.core.domain.model.scenario.Scenario

interface ProgressListener {

    suspend fun onSessionStarted(context: Context, scenario: Scenario, events: List<Event>)

    suspend fun onImageProcessingStarted()

    suspend fun onEventProcessingStarted(event: Event)

    suspend fun onConditionProcessingStarted(condition: Condition)

    suspend fun onConditionProcessingCompleted(detectionResult: DetectionResult)

    suspend fun onEventProcessingCompleted(
        isEventMatched: Boolean,
        event: Event?,
        condition: Condition?,
        result: DetectionResult?,
    )

    suspend fun onImageProcessingCompleted()

    suspend fun onSessionEnded()

    suspend fun cancelCurrentProcessing()
}

internal suspend fun ProgressListener.onEventProcessingCompleted(result: ProcessorResult) =
    onEventProcessingCompleted(result.eventMatched, result.event, result.condition, result.detectionResult)