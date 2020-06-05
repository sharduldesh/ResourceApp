package org.techm.resourcesapp.ui.associate.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_associate.*
import org.techm.resourcesapp.R
import org.techm.resourcesapp.data.local.entity.AssociateEntity
import org.techm.resourcesapp.ui.associate.adapter.EmployeeAdapter
import org.techm.resourcesapp.ui.associate.viewmodel.AssociateViewModel
import org.techm.resourcesapp.util.SwipeToDeleteCallback


/**
 * @fragment{ActivityAddAssociate} -> view for associate
 */
class FragmentAssociate : Fragment(), EmployeeAdapter.OnItemClickListener {

    private var displayList: MutableList<AssociateEntity> = ArrayList()
    private var associateSearchList: MutableList<AssociateEntity> = ArrayList()
    private lateinit var associateViewModel: AssociateViewModel
    private var recyclerViewAdapter: EmployeeAdapter? = null




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_associate, container, false)




        val toolbar: Toolbar = view.findViewById(R.id.toolBarAssociate)
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                /**
                 * add to next activity
                 */
                R.id.actionAddAssociate -> {
                    //Toast.makeText(activity, "add associate", Toast.LENGTH_SHORT).show()
                    requireContext().let {
                        val intent = Intent(it, ActivityAddAssociate::class.java)
                        intent.putExtra("addOrUpdate", "add")
                        startActivity(intent)
                    }
                }
                /**
                 * search associate entry
                 */
                R.id.actionSearchAssociate -> {
                    val searchView = item.actionView as SearchView
                    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            return true
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {

                            if (newText!!.isNotEmpty()){

                                displayList.clear()
                                val search = newText.toLowerCase()
                                associateSearchList.forEach {
                                    if (it.employeeName.contains(search)) {
                                        displayList.add(it)
                                    }
                                }

                            }else {
                                displayList.clear()
                                displayList.addAll(associateSearchList)
                                recyclerViewAssociate.adapter?.notifyDataSetChanged()

                            }
                            return true
                        }

                    })
                }
                else -> Log.e("else ", "clause")
            }
            true
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpUIViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setUpUIViewModel()
        /**
         * swipe to delete
         */
        val swipeHandler = object : SwipeToDeleteCallback(activity as AppCompatActivity) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = recyclerViewAssociate.adapter as EmployeeAdapter
                adapter.removeItem(viewHolder.adapterPosition)
                Log.e("POS ->" , "${viewHolder.adapterPosition}")
                deleteEmployeeById(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerViewAssociate)

        recyclerViewAssociate.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }

        recyclerViewAdapter = EmployeeAdapter(arrayListOf(), this)
        recyclerViewAssociate.adapter = recyclerViewAdapter
    }

    fun deleteEmployeeById(index: Int) {
        associateViewModel.delete(index.toLong())
    }


    /**
     * setup view model and factory for associate
     */
    private fun setUpUIViewModel() {

        associateViewModel = ViewModelProvider(activity as AppCompatActivity).get(AssociateViewModel::class.java)

        associateViewModel.allAssociates.observe(activity as AppCompatActivity, Observer {
            if (it.isEmpty()) {
                Log.e("TAG EMPTY", "ITEMS: $it")

            } else {
                displayList.addAll(it)
                associateSearchList = it
                recyclerViewAdapter!!.addAssociates(it)
                recyclerViewAdapter!!.notifyDataSetChanged()
            }
            Log.e("TAG", "ITEMS: $it")
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_associate, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.actionAddAssociate -> {
                //Toast.makeText(activity , "add on click" , Toast.LENGTH_SHORT).show()
                Log.e("add", "add Associate")
                true
            }
            R.id.actionSearchAssociate -> {
                Log.e("search", "search Associate")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * on item click of associate
     */
    override fun onItemClick(associateEntity: AssociateEntity) {
        val intent = Intent(context, ActivityAddAssociate::class.java)

        intent.putExtra("addOrUpdate", "update")
        intent.putExtra("id", associateEntity.employeeId)
        intent.putExtra("name", associateEntity.employeeName)
        intent.putExtra("band", associateEntity.employeeBand)
        intent.putExtra("designation", associateEntity.employeeDesignation)
        intent.putExtra("competency", associateEntity.employeeCompetency)
        intent.putExtra("currentProject", associateEntity.employeeCurrentProject)
        startActivity(intent)
    }

}
