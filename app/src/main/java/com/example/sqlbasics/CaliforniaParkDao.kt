/*
 * Copyright (C) 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.sqlbasics

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CaliforniaParkDao {
    @Insert
    suspend fun insertAll(parks: List<CaliforniaPark>)
    @Query("SELECT * FROM park")
    suspend fun getAll(): List<CaliforniaPark>
    @Query("SELECT name FROM park WHERE park_visitors < 1000000")
    suspend fun getNamesLessMillionVisitors(): List<String>
    @Query("SELECT SUM(park_visitors) FROM park\n" +
            "WHERE city = \"San Francisco\"")
    suspend fun getSunFranciscoParkVisitorsSum(): Int
    @Query("SELECT name, park_visitors FROM park\n" +
            "ORDER BY park_visitors DESC\n" +
            "LIMIT 5")
    suspend fun getOrderedNamesWithParkVisitors(): List<CaliforniaPark>
}