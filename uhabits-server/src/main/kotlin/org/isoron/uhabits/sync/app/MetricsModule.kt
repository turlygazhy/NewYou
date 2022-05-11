/*
 * Copyright (C) 2016-2021 Álinson Santos Xavier <git@axavier.org>
 *
 * This file is part of Loop Habit Tracker.
 *
 * Loop Habit Tracker is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * Loop Habit Tracker is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.isoron.uhabits.sync.app

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.prometheus.client.*
import io.prometheus.client.exporter.common.*
import io.prometheus.client.hotspot.*
import java.io.*


fun Routing.metrics(app: SyncApplication) {
    // Register JVM metrics
    DefaultExports.initialize()

    get("/metrics") {
        val writer = StringWriter()
        TextFormat.write004(
            writer,
            CollectorRegistry.defaultRegistry.filteredMetricFamilySamples(setOf<String>()),
        )
        call.respond(HttpStatusCode.OK, writer.toString())
    }
}
