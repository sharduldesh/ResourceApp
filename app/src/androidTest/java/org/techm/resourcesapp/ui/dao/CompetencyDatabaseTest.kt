package org.techm.resourcesapp.ui.dao

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.techm.resourcesapp.data.local.database.ResourcesDatabase
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
abstract class CompetencyDatabaseTest {
    protected lateinit var appDatabase: ResourcesDatabase
    private var appContext = InstrumentationRegistry.getInstrumentation().targetContext
    @Before
    fun initDb() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            appContext,
            ResourcesDatabase::class.java)
            .build()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        appDatabase.close()
    }
}