package org.techm.resourcesapp.ui.associate.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.techm.resourcesapp.R
import org.techm.resourcesapp.data.local.entity.AssociateEntity

/**
 * @class{EmployeeAdapter} -> adapter for associate
 */

class EmployeeAdapter(
    private var associates: MutableList<AssociateEntity>,
    listener: OnItemClickListener
) : RecyclerView.Adapter<EmployeeViewHolder>() {

    private var associateList: MutableList<AssociateEntity> = associates
    private var listenerAssociate: OnItemClickListener = listener

    interface OnItemClickListener {
        fun onItemClick(associateEntity: AssociateEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {

        return EmployeeViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_item_associate, parent, false)
        )
    }

    override fun getItemCount() = associateList.size


    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val associate: AssociateEntity = associateList[position]


        val name = associate.employeeName
        val id = associate.employeeId
        val band = "(${associate.employeeBand})"
        val competency = associate.employeeCompetency
        val designation = associate.employeeDesignation
        val currentProject = associate.employeeCurrentProject

        holder.mName.text = name
        holder.mBand.text = band
        holder.mID.text = id
        holder.mCompetency.text = competency
        holder.mDesignation.text = designation
        holder.mCurrentProject.text = currentProject


        if (holder.mCompetency.text == "Android")
            holder.mLogo.setImageResource(R.drawable.android)
        else if (holder.mCompetency.text == "iOS")
            holder.mLogo.setImageResource(R.drawable.ios)
        else if (holder.mCompetency.text == "UX")
            holder.mLogo.setImageResource(R.drawable.ux)
        else if (holder.mCompetency.text == "Tester")
            holder.mLogo.setImageResource(R.drawable.tester)

        //holder.mLogo.setImageResource(R.drawable.ios)

        holder.bind(associate, listenerAssociate)

    }

    fun addAssociates(list: MutableList<AssociateEntity>) {
        this.associateList = list
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        associateList.removeAt(position)
        notifyItemRemoved(position)
        /*dataModelList.removeAt(position)
        notifyItemRemoved(position)*/
    }

}