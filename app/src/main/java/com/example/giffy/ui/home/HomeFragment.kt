package com.example.giffy.ui.home

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.giffy.R
import com.example.giffy.presentation.BlockChainViewModel
import com.example.giffy.ui.GiffySearchAdapter
import com.example.myapplication.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.giffy.repository.GiffyResult
import com.example.giffy.repository.OnFailurGiffyResult
import com.example.giffy.repository.OnSuccessGiffyResult
import com.example.giffy.utils.NetworkConnectionUtil
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_home.recycle_view_gif
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var vm :BlockChainViewModel
    private lateinit var myAdapter: GiffySearchAdapter
    private  var textQuery: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false).apply {
           // val textView: TextView = findViewById(R.id.text_home)
            setHasOptionsMenu(true)


            homeViewModel.text.observe(this@HomeFragment, Observer {
                //textView.text = it
            })

            //imageViewTestHome.visibility =View.GONE
            myAdapter = GiffySearchAdapter(context)
            recycle_view_gif?.apply {
                setHasFixedSize(true)
                layoutManager =GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            }


           vm = ViewModelProviders.of(this@HomeFragment).get(BlockChainViewModel::class.java)
            startObserving(vm)
            vm.getData()

//            uiScope.launch(Dispatchers.Default) {
//                BlockChainViewModel().getGraphData()
//            }
        }
    }

    private fun startObserving(vm:BlockChainViewModel) {

        val nameObserver = Observer<GiffyResult> { data ->

            // Update the UI, in this case, a TextView.
            recycle_view_gif.adapter =myAdapter

            when(data){

                is OnSuccessGiffyResult ->{
                    recycle_view_gif.visibility = View.VISIBLE
                    error_text.visibility =View.GONE
                    data.result?.urlList?.let { myAdapter.update(it) }
                }

                is OnFailurGiffyResult ->{
                    //error state
                    recycle_view_gif.visibility = View.GONE
                    error_text.visibility =View.VISIBLE
                }

            }
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        vm .uiData.observe(this, nameObserver)
    }

    //TODO
//    fun updateImage(url: String = "https://media2.giphy.com/media/BlVnrxJgTGsUw/giphy-preview.gif") {
//        Glide.with(this)
//            .asGif()
//            .load(url)
//            .into(imageViewTestHome);
//    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu_scrolling, menu)
        val menuItem = menu!!.findItem(R.id.app_bar_search)
        val searchView = menuItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                textQuery =query

                if(!NetworkConnectionUtil.isOnline(context!!)) {
                    throwErrorDialog(context!!)
                }
                else{
                    query?.let { vm.getData(it) }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun throwErrorDialog(context: Context, search:String?= null) {
            MaterialAlertDialogBuilder(context)
                .setTitle("Network Error")
                .setMessage("Please check your WiFi or cellular connection and try again.")
                .setPositiveButton("Retry") { dialog, which ->
                    if(!NetworkConnectionUtil.isOnline(context!!)) {
                        throwErrorDialog(context!!)
                    }
                    else{
                        textQuery?.let { vm.getData(it) }
                    }
                }
                .setNegativeButton(android.R.string.cancel) { dialog, which ->
                    dialog.dismiss()
                }
                .show()
    }
}