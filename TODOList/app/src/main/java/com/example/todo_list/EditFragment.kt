package com.example.todo_list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list.databinding.FragmentEditBinding
import com.example.todo_list.viewmodel.NoteViewModel
import java.text.SimpleDateFormat
import java.util.*


class EditFragment : Fragment(R.layout.fragment_edit) {

    private var _binding:FragmentEditBinding?=null
    private val binding get()=_binding!!
     val args:EditFragmentArgs by navArgs()
    var dizi=ArrayList<Model>()
    private lateinit var nModel:NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentEditBinding.inflate(inflater,container,false)
        val view=binding.root
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbarEditFragment)
        setHasOptionsMenu(true)
        nModel=ViewModelProvider(this).get(NoteViewModel::class.java)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editTextBaslikEdit.setText(args.datas.baslik)
        binding.editTextNotunuzEdit.setText(args.datas.metin)
        binding.buttonKaydetEdit.setOnClickListener {
            val sdf: SimpleDateFormat = SimpleDateFormat("yyyy:MM:dd_HH:ss", Locale.getDefault())
            val new= Date()
            val date=sdf.format(new)
            val baslik=binding.editTextBaslikEdit.text.toString()
            val not=binding.editTextNotunuzEdit.text.toString()
            if(baslik.isNotEmpty()&&not.isNotEmpty()){
                val new_model=Model(baslik,not,date,1,args.datas.id)
                nModel.updateNote(new_model)
                findNavController().navigate(R.id.action_editFragment_to_menuFragment)
                Toast.makeText(requireContext(),"Başarılı",Toast.LENGTH_LONG).show()
            }
        }
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
                deleteUser()
                Thread.sleep(1000L)
                findNavController().navigate(R.id.action_editFragment_to_menuFragment)
                return true
            }

        }
        return false
    }
    private fun deleteUser(){
        val build= AlertDialog.Builder(requireContext())
        build.setMessage(R.string.tekli_silme)
        build.setTitle(R.string.uyari)
        build.setPositiveButton(R.string.pozitif_uyari){dialog,i->
          nModel.deleteNote(args.datas)
            Toast.makeText(requireContext(),"Silindi",Toast.LENGTH_LONG).show()
        }
        build.setNegativeButton(R.string.negatif_uyari){dialog,i->
        }
        build.create().show()
    }
}