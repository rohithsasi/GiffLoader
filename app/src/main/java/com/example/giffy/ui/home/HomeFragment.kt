package com.example.giffy.ui.home

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.giffy.R
import com.example.giffy.presentation.BlockChainViewModel
import com.example.giffy.repository.GiffyResult
import com.example.giffy.repository.OnFailurGiffyResult
import com.example.giffy.repository.OnSuccessGiffyResult
import com.example.giffy.utils.NetworkConnectionUtil
import com.example.giffy.utils.getString
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.recycle_view_gif

class HomeFragment : Fragment() {
    private lateinit var searchViewModel: BlockChainViewModel
    private lateinit var myAdapter: GiffySearchAdapter
    private var textQuery: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false).apply {
            setHasOptionsMenu(true)

            myAdapter = GiffySearchAdapter(context)
            recycle_view_gif?.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            }

            searchViewModel = ViewModelProviders.of(this@HomeFragment).get(BlockChainViewModel::class.java)
            startObserving(searchViewModel)
            searchViewModel.getGifs()

        }
    }

    private fun startObserving(vm: BlockChainViewModel) {

        val gifsObserver = Observer<GiffyResult> { data ->
            recycle_view_gif.adapter = myAdapter

            when (data) {

                is OnSuccessGiffyResult -> {
                    recycle_view_gif.visibility = View.VISIBLE
                    error_text.visibility = View.GONE
                    data.result?.urlList?.let { myAdapter.update(it) }
                }

                is OnFailurGiffyResult -> {
                    //error state
                    recycle_view_gif.visibility = View.GONE
                    error_text.visibility = View.VISIBLE
                }

            }
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        vm.searchResult.observe(this, gifsObserver)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu_scrolling, menu)
        val menuItem = menu!!.findItem(R.id.app_bar_search)
        val searchView = menuItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                textQuery = query

                if (!NetworkConnectionUtil.isOnline(activity as Context)) {
                    throwErrorDialog(activity as Context)
                } else {
                    query?.let { searchViewModel.getGifs(it) }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun throwErrorDialog(context: Context, search: String? = null) {
        MaterialAlertDialogBuilder(context)
            .setTitle(R.string.network_dialog_title.getString())
            .setMessage(R.string.network_dialog_text.getString())
            .setPositiveButton(R.string.network_dialog_retry.getString()) { dialog, which ->
                if (!NetworkConnectionUtil.isOnline(context)) {
                    throwErrorDialog(context)
                } else {
                    textQuery?.let { searchViewModel.getGifs(it) }
                }
            }
            .setNegativeButton(android.R.string.cancel) { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }
}