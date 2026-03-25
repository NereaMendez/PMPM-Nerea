package com.example.tabs_fragmentos_menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.tabs_fragmentos_menu.databinding.FragmentDivisibleBinding
import com.example.tabs_fragmentos_menu.viewmodel.MainViewModel

class Fragment_Divisible : Fragment() {

    private var _binding: FragmentDivisibleBinding? = null
    private val binding get() = _binding!!


    private val miViewModel: MainViewModel by viewModels({ requireActivity() })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDivisibleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        miViewModel.datos.observe(viewLifecycleOwner, Observer { datos ->
            if (datos.numGenerado != 0) {
                when (datos.estado) {
                    1 -> {
                        binding.txtResultadoDivisible.text = "Pendiente"
                        binding.txtResultadoDivisible.setTextColor(resources.getColor(android.R.color.holo_blue_bright, null))
                    }
                    0 -> {
                        binding.txtResultadoDivisible.text = "Correcto"
                        binding.txtResultadoDivisible.setTextColor(resources.getColor(android.R.color.holo_green_light, null))
                    }
                    -1 -> {
                        binding.txtResultadoDivisible.text = "Incorrecto"
                        binding.txtResultadoDivisible.setTextColor(resources.getColor(android.R.color.holo_red_light, null))
                    }
                }
            }
        })

        binding.btnValidarDivisible.setOnClickListener {
            val numActual = miViewModel.datos.value?.numGenerado ?: return@setOnClickListener

            if (numActual == 0) {
                Toast.makeText(requireContext(), "Genera un número primero", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val div2 = binding.radioDiv2.isChecked
            val div3 = binding.radioDiv3.isChecked
            val div5 = binding.radioDiv5.isChecked
            val div10 = binding.radioDiv10.isChecked
            val ninguno = binding.radioNinguno.isChecked

            val seleccionadas = listOf(div2, div3, div5, div10, ninguno).count { it }
            if (seleccionadas != 1) {
                Toast.makeText(requireContext(), "Selecciona solo una opción", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            miViewModel.validarDivisible(div2, div3, div5, div10, ninguno)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}