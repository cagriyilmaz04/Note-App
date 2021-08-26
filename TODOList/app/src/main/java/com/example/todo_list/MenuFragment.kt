package com.example.todo_list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list.databinding.FragmentMenuBinding
import com.example.todo_list.viewmodel.NoteViewModel
import java.text.SimpleDateFormat
import java.util.*


class MenuFragment : Fragment(R.layout.fragment_menu) {
    private var _binding:FragmentMenuBinding?=null
    private val binding get()=_binding!!
    private var dizi=ArrayList<Model>()
    val adapter=Adapter()
    private lateinit var menuView:NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentMenuBinding.inflate(inflater,container,false)
        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_noteFragment)
        }
        binding.recclerViewMenu.layoutManager=LinearLayoutManager(requireContext())
        menuView=ViewModelProvider(this).get(NoteViewModel::class.java)
        menuView.readAllData.observe(viewLifecycleOwner, androidx.lifecycle.Observer { note->
            if(note.size>0){
                dizi= note as ArrayList<Model>
                binding.textViewUyarimesaji.visibility=View.INVISIBLE
                adapter.setData(note)
                binding.recclerViewMenu.adapter=adapter
            }

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_for_menu_fragment,menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete_all->{
                Toast.makeText(requireContext(),"Basıldı",Toast.LENGTH_LONG).show()
                deleteAllUser(dizi)
                return true
            }

        }
        return false
    }

    private fun deleteAllUser(ornek:ArrayList<Model>){
            val build=AlertDialog.Builder(requireContext())
                build.setMessage(R.string.silme)
                build.setTitle(R.string.uyari)
                build.setPositiveButton(R.string.pozitif_uyari){dialog,i->
                    if(ornek.size>0){
                        menuView.deleteAllNotes()
                        Toast.makeText(requireContext(),"Her şey silindi",Toast.LENGTH_LONG).show()
                        Thread.sleep(1000L)
                        binding.textViewUyarimesaji.visibility=View.VISIBLE
                    }else{
                        Toast.makeText(requireContext(),"Not Bulunmamaktadır",Toast.LENGTH_LONG).show()
                    }

                }
                build.setNegativeButton(R.string.negatif_uyari){dialog,i->
                }
        build.create().show()
    }



}