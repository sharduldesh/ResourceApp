package org.techm.resourcesapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import org.techm.resourcesapp.data.local.dao.AssociateDAO
import org.techm.resourcesapp.data.local.database.ResourcesDatabase
import org.techm.resourcesapp.data.local.entity.AssociateEntity

/**
 * @class{AssociateRepository} -> repository for associate
 */

class AssociateRepository(application: Application) {


    private val associateDAO: AssociateDAO
    private var associateList: LiveData<MutableList<AssociateEntity>>

    init {
        val db = ResourcesDatabase.getInstance(application)
        associateDAO = db?.associateDao()!!
        associateList = associateDAO.fetchAllAssociates()
    }

    init {
        associateList = associateDAO.fetchAllAssociates()
    }

    fun fetchListOfAssociates(): LiveData<MutableList<AssociateEntity>> {
        return associateList
    }

    suspend fun insertAssociate(associateEntity: AssociateEntity) {
        associateDAO.createAssociate(associateEntity)
    }

    suspend fun updateAssociate(associateEntity: AssociateEntity) {
        associateDAO.updateAssociate(associateEntity)
    }

    suspend fun deleteAssociate(id:Long) {
        associateDAO.deleteAssociateById(id)
    }

}