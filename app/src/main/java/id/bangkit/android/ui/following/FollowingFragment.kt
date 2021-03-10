package id.bangkit.android.ui.following

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.bangkit.android.adapter.UserAdapter
import id.bangkit.android.databinding.FragmentFollowingBinding
import id.bangkit.android.model.ItemUser
import id.bangkit.android.ui.detail.DetailUserActivity


class FollowingFragment : Fragment() {


    companion object {
        private lateinit var followingViewModel: FollowingViewModel
        private lateinit var adapter: UserAdapter
        private lateinit var username: String
        private lateinit var binding: FragmentFollowingBinding
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val activity: DetailUserActivity = activity as DetailUserActivity
        username = activity.getUsername().toString()

        initListFollowing()
        initObserveData()

    }

    private fun initObserveData() {
        followingViewModel = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()).get(FollowingViewModel::class.java)
        followingViewModel.getFollowingQuery(username)
        followingViewModel.getFollowing().observe(requireActivity(), userFollowing)
    }

    private val userFollowing = Observer<MutableList<ItemUser>> { result ->
        binding.progress.visibility = View.GONE
        if (result.size >0){
            adapter.setData(result)
            binding.rvUser.visibility = View.VISIBLE
            binding.lvEmptyData.visibility = View.GONE
        } else {
            binding.rvUser.visibility = View.GONE
            binding.lvEmptyData.visibility = View.VISIBLE
        }
    }

    private fun initListFollowing() {
        binding.rvUser.layoutManager = LinearLayoutManager(requireContext())
        adapter = UserAdapter()
        binding.rvUser.adapter = adapter
    }

}