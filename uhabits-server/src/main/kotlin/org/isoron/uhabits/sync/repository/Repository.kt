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

package org.isoron.uhabits.sync.repository

import org.isoron.uhabits.sync.KeyNotFoundException
import org.isoron.uhabits.sync.SyncData

/**
 * A class that knows how to store and retrieve a large number of [SyncData] items.
 */
interface Repository {
    /**
     * Stores a data item, under the provided key. The item can be later retrieved with [get].
     * Replaces existing items silently.
     */
    suspend fun put(key: String, data: SyncData)

    /**
     * Retrieves a data item that was previously stored using [put].
     * @throws KeyNotFoundException If no such key exists.
     */
    suspend fun get(key: String): SyncData

    /**
     * Returns true if the repository contains a given key.
     */
    suspend fun contains(key: String): Boolean
}

