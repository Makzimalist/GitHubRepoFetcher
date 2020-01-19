package com.makzimalist.githubrepofetcher.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.makzimalist.githubrepofetcher.R
import com.makzimalist.githubrepofetcher.ui.list.viewmodel.ListViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.list_fragment.*

class ListFragment : Fragment() {

    companion object {
        fun newInstance() = ListFragment()
    }

    private lateinit var viewModel: ListViewModel
    private lateinit var adapter: RepositoryListAdapter

    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

        initViews()
        initBindings()

        viewModel.requestRepositories()
    }

    private fun initBindings() {
        viewModel.dataSubject
            .subscribe {
                adapter.data = it
                adapter.notifyDataSetChanged()
            }.addTo(compositeDisposable)
    }

    private fun initViews() {
        adapter = RepositoryListAdapter()

        repo_list.adapter = adapter
        repo_list.layoutManager = LinearLayoutManager(context)
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.dispose()
    }

}
