package com.makzimalist.githubrepofetcher.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.makzimalist.githubrepofetcher.R
import com.makzimalist.githubrepofetcher.data.api.model.Repository
import com.makzimalist.githubrepofetcher.extension.hide
import com.makzimalist.githubrepofetcher.extension.visible
import com.makzimalist.githubrepofetcher.ui.detail.viewmodel.DetailsViewModel
import kotlinx.android.synthetic.main.repository_details_fragment.*

class DetailsFragment : BottomSheetDialogFragment() {

    lateinit var viewModel: DetailsViewModel

    companion object {
        val REPOSITORY_KEY = "REPOSITORY_KEY"

        fun newInstance(repository: Repository): DetailsFragment {
            return DetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(REPOSITORY_KEY, repository)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.repository_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)

        val repository: Repository = arguments?.getParcelable(REPOSITORY_KEY) ?: return

        initView(repository)
    }

    private fun initView(repository: Repository) {
        title.text = repository.name
        owner.text = repository.owner.name
        stars.text = repository.stars.toString()
        forks.text = repository.forks.toString()
        watchers.text = repository.watchers.toString()
        description.text = repository.description

        if (repository.language.isNullOrEmpty()) {
            language.hide()
        } else {
            language.visible()
            language.text = repository.language
        }

        github_button.setOnClickListener { viewModel.openBrowser(context, repository.html_url) }
    }
}