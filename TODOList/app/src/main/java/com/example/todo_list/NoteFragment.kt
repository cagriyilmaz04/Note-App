package com.example.todo_list

import android.os.Bundle
import android.view.Display
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todo_list.databinding.FragmentNoteBinding
import com.example.todo_list.viewmodel.NoteViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*


class NoteFragment : Fragment(R.layout.fragment_note) {
    private var _binding:FragmentNoteBinding?=null
    private val binding get()=_binding!!
    private lateinit var mNoteView:NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding= FragmentNoteBinding.inflate(inflater,container,false)
        mNoteView=ViewModelProvider(this).get(NoteViewModel::class.java)
        val view=binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonKaydet.setOnClickListener {
            val baslik=binding.editTextBaslik.text.toString()
            val metin=binding.editTextNotunuz.text.toString()
            if(baslik.isNotEmpty()&&metin.isNotEmpty()){
                val sdf: SimpleDateFormat = SimpleDateFormat("yyyy:MM:dd_HH:ss", Locale.getDefault())
                val new= Date()
                val date=sdf.format(new)
                val model=Model(baslik,metin,date,0,0)

                insertNoteToDatabase(model)
            }else{
                Toast.makeText(requireContext(),"Lütfen Gerekli Yerleri Doldurunuz",Toast.LENGTH_LONG).show()
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
 private fun insertNoteToDatabase(note:Model){
            if(note!=null){
                mNoteView.addUser(note)
                Toast.makeText(requireContext(),"Succesful",Toast.LENGTH_LONG).show()
               Thread.sleep(1000L)
                findNavController().navigate(R.id.action_noteFragment_to_menuFragment)

            }
    }
}