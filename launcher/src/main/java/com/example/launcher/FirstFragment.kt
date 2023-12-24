package com.example.launcher

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.pm.ResolveInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.launcher.databinding.FragmentFirstBinding
import com.example.launcher.databinding.GridviewItemBinding


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var mApps: List<ResolveInfo>? = null
    var mGrid: GridView? = null
    private val listener: AdapterView.OnItemClickListener =
        AdapterView.OnItemClickListener { parent, view, position, id ->
            val info: ResolveInfo = mApps!![position]

            //该应用的包名
            val pkg: String = info.activityInfo.packageName
            //应用的主activity类
            val cls: String = info.activityInfo.name
            val component = ComponentName(pkg, cls)
            val i = Intent()
            i.setComponent(component)
            startActivity(i)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        mGrid = _binding!!.appsList
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController(it).navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        loadApps()
        mGrid!!.adapter = activity?.let { mApps?.let { it1 -> AppsAdapter(it, it1) } }

        mGrid!!.onItemClickListener = listener
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadApps() {
        val mainIntent = Intent(Intent.ACTION_MAIN, null)
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
        mApps = requireActivity().packageManager.queryIntentActivities(mainIntent, 0)
    }

    class AppsAdapter(val activity: Activity, val mApps: List<ResolveInfo>) : BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            var viewHolder: MyViewHolder
            var rootView: View
            if (convertView == null) {
//                rootView = LayoutInflater.from(activity).inflate(R.layout.gridview_item, null)
                rootView = GridviewItemBinding.inflate(LayoutInflater.from(activity)).root
                viewHolder = MyViewHolder()
                viewHolder.icon = rootView.findViewById(R.id.icon_iv)
                viewHolder.name = rootView.findViewById(R.id.name_tv)
                rootView.tag = viewHolder
            } else {
                rootView = convertView
                viewHolder = rootView.tag as MyViewHolder
            }
            val info: ResolveInfo = mApps.get(position)
            viewHolder.icon?.apply {
                setImageDrawable(info.loadIcon(activity.packageManager))
            }
            viewHolder.name?.apply {
                text = info.loadLabel(activity.packageManager)
            }
            return rootView
        }


        override fun getCount() = mApps!!.size

        override fun getItem(position: Int): Any {
            return mApps[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }


    }
}

class MyViewHolder {
    var icon: ImageView? = null
    var name: TextView? = null
}