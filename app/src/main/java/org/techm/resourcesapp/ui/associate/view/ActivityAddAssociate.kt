package org.techm.resourcesapp.ui.associate.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_add_associate.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.techm.resourcesapp.R
import org.techm.resourcesapp.data.local.entity.AssociateEntity
import org.techm.resourcesapp.ui.associate.viewmodel.AssociateViewModel
import org.techm.resourcesapp.ui.project.viewmodel.ProjectViewModel


/**
 * @activity{ActivityAddAssociate} -> view for associate
 */

class ActivityAddAssociate : AppCompatActivity() {


    private lateinit var associateViewModel: AssociateViewModel
    private lateinit var projectViewModel: ProjectViewModel
    private lateinit var associateEntity: AssociateEntity
    private var projectList: MutableList<String> = ArrayList()
    private var isComingFrom: String? = null

    var id: String? = null
    var name:String? = null
    var band:String? = null
    var designation:String? = null
    var currentProject: String? = null
    var competency: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_associate)
        val intent = intent

        isComingFrom = intent.getStringExtra("addOrUpdate") //if add or update

        associateViewModel = ViewModelProviders.of(this).get(AssociateViewModel::class.java)
        projectViewModel = ViewModelProviders.of(this).get(ProjectViewModel::class.java)

        initUI(intent)
    }

    /**
     * initializing ui
     */
    private fun initUI(intent: Intent) {

        projectList.clear()
        progressBarAssociate.visibility = View.GONE
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)
        val spinner = findViewById<Spinner>(R.id.spinnerCurrentProjects)

         id = inputAssociateID.text?.toString()
         name = inputAssociateName.text.toString()
         band = inputAssociateBand.text.toString()
         designation = inputAssociateDesignation.text.toString()


        if (isComingFrom.equals("update")) {
            val updateID = intent.getStringExtra("id")
            val updateName = intent.getStringExtra("name")
            val updateBand = intent.getStringExtra("band")
            val updateDesignation = intent.getStringExtra("designation")
            val updateCompetency = intent.getStringExtra("competency")
            val updateCurrentProject = intent.getStringExtra("currentProject")

            inputAssociateID.setText(updateID)
            inputAssociateName.setText(updateName)
            inputAssociateBand.setText(updateBand)
            inputAssociateDesignation.setText(updateDesignation)
            if (updateCompetency.toString().equals("Android")) {
                competency = "Android"
                radioGroup.check(R.id.radioButtonAndroid)
            } else if (updateCompetency.toString().equals("iOS")) {
                competency = "iOS"
                radioGroup.check(R.id.radioButtoniOS)
            } else if (updateCompetency.toString().equals("UX")) {
                competency = "UX"
                radioGroup.check(R.id.radioButtonUX)
            } else if (updateCompetency.toString().equals("Tester")) {
                competency = "Tester"
                radioGroup.check(R.id.radioButtonTester)
            }

            val index = getIndex(spinner, updateCurrentProject)
            Log.e("INDEX POS = ", "$index")
            Log.e("INDEX VAL = ", "$updateCurrentProject")
            spinner.setSelection(index)

        }

        radioGroup.setOnCheckedChangeListener { group, checkID ->

            competency = when {
                R.id.radioButtonAndroid == checkID -> {
                    "Android"
                }
                R.id.radioButtoniOS == checkID -> {
                    "iOS"
                }
                R.id.radioButtonUX == checkID -> {
                    "UX"
                }
                R.id.radioButtonTester == checkID -> {
                    "Tester"
                }
                else -> {
                    null
                }
            }
            //Toast.makeText(applicationContext, currentProject, Toast.LENGTH_SHORT).show()
        }
        projectViewModel.allProjects.observe(this, Observer {


            it.forEach { item ->
                projectList.add(item.projectName)
            }
            Log.e("IT SIZE", "${it.size}")
            Log.e("Project SIZE", "${projectList.size}")
            Log.e("Project SIZE", "${projectList.size}")
            //Log.e("Project array", "$projectArray")

        })


        val spinnerArray: Array<String> = arrayOf(
            "ATT FirstNet",
            "Sawari Cab App",
            "Resource Management App",
            "ATT",
            "TMobile",
            "Bell Canada"
        )
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArray)
        //spinner.adapter = ArrayAdapter(this@ActivityAddAssociate, android.R.layout.simple_spinner_dropdown_item, projectArray)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {

                currentProject = spinnerArray[position]
                Log.e("position : ", spinnerArray[position])
                Log.e("position : ", currentProject)
                Log.e("position ", parent.getItemAtPosition(position).toString())

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Code to perform some action when nothing is selected
            }
        }


        /**
         * add associate entry to database
         */
        buttonSubmitAssociate.setOnClickListener {

            if (!id.isNullOrBlank() && !name.isNullOrBlank() && !designation.isNullOrBlank() && !competency.isNullOrBlank() && !currentProject.isNullOrBlank()) {
                progressBarAssociate.visibility = View.VISIBLE
                runBlocking {
                    launch {
                        delay(2000)
                    }
                }


                associateViewModel.insert(
                    AssociateEntity(
                        id.toString(),
                        name.toString(),
                        band.toString(),
                        designation.toString(),
                        competency.toString(),
                        currentProject.toString()
                    )
                )

                progressBarAssociate.visibility = View.GONE
                finish()

            } else {
                Toast.makeText(this, "Please fill up all details", Toast.LENGTH_SHORT).show()

            }
        }
    }


    private fun getIndex(spinner: Spinner, myString: String): Int {
        var index = 0
        for (i in 0 until spinner.count) {
            if (spinner.getItemAtPosition(i) == myString) {
                index = i
            }
        }
        return index
    }


}