package org.techm.resourcesapp.data.local.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.techm.resourcesapp.data.local.dao.AssociateDAO
import org.techm.resourcesapp.data.local.dao.ProjectDAO
import org.techm.resourcesapp.data.local.entity.AssociateEntity
import org.techm.resourcesapp.data.local.entity.ProjectEntity

/**
 * @class{ResourcesDatabase} -> database
 */

@Database(entities = [AssociateEntity::class , ProjectEntity::class] , version = 1 , exportSchema = false)
abstract class ResourcesDatabase: RoomDatabase() {

    abstract fun associateDao(): AssociateDAO
    abstract fun projectDao(): ProjectDAO

    companion object {
        @Volatile
        private var instance: ResourcesDatabase? = null
        fun getInstance(context: Context): ResourcesDatabase? {
            if (instance == null) {
                synchronized(ResourcesDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ResourcesDatabase::class.java, "resource_db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance
        }
    }


}