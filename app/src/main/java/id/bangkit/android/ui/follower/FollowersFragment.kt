package id.bangkit.android.ui.follower

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.bangkit.android.adapter.UserAdapter
import id.bangkit.android.databinding.FragmentFollowersBinding
import id.bangkit.android.model.ItemUser
import id.bangkit.android.ui.detail.DetailUserActivity


class FollowersFragment : Fragment() {

    private lateinit var binding: FragmentFollowersBinding

    companion object {
        private lateinit var followerViewModel: FollowerViewModel
        private lateinit var adapter: UserAdapter
        private lateinit var username: String
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val activity: DetailUserActivity = activity as DetailUserActivity
         username = activity.getUsername().toString()

        initListFollowers()
        initObserveData()
    }

    private fun initObserveData() {
        followerViewModel = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()).get(FollowerViewModel::class.java)
        followerViewModel.getFollowerQuery(username)
        followerViewModel.getFollowers().observe(requireActivity(), userFollowers)
    }

    private val userFollowers = Observer<MutableList<ItemUser>> { result ->
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

    private fun initListFollowers() {
        binding.rvUser.layoutManager = LinearLayoutManager(requireContext())
        adapter = UserAdapter()
        binding.rvUser.adapter = adapter
    }

}