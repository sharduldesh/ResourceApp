package org.techm.resourcesapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import org.techm.resourcesapp.data.local.entity.AssociateEntity

/**
 * @DAO{AssociateDAO}
 */

@Dao
interface AssociateDAO {

    @Query("SELECT * FROM employee")
    fun fetchAllAssociates(): LiveData<MutableList<AssociateEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createAssociate(associateEntity: AssociateEntity): Long

    @Query("DELETE FROM employee WHERE employeeId = :id")
    suspend fun deleteAssociateById(id: Long)

    @Update
    suspend fun updateAssociate(associateEntity: AssociateEntity)
}