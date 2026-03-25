package com.example.tabs_fragmentos_menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.tabs_fragmentos_menu.databinding.FragmentBisiestoBinding
import com.example.tabs_fragmentos_menu.viewmodel.MainViewModel

class FragmentBisiesto : Fragment() {


    //binding asi:
    private var _binding: FragmentBisiestoBinding? = null
    private val binding get() = _binding!!


    private val miViewModel: MainViewModel by viewModels({ requireActivity() })
    private var respuestaSeleccionada: Boolean? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBisiestoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        miViewModel.datos.observe(viewLifecycleOwner, Observer { datos ->
            if (datos.numGenerado != 0) {
                when (datos.estado) {
                    1 -> {
                        binding.txtResultadoBisiesto.text = "Pendiente "
                        binding.txtResultadoBisiesto.setTextColor(resources.getColor(android.R.color.holo_blue_bright, null))
                    }
                    0 -> {
                        binding.txtResultadoBisiesto.text = "Correcto"
                        binding.txtResultadoBisiesto.setTextColor(resources.getColor(android.R.color.holo_green_light, null))
                    }
                    -1 -> {
                        binding.txtResultadoBisiesto.text = "Incorrecto"
                        binding.txtResultadoBisiesto.setTextColor(resources.getColor(android.R.color.holo_red_light, null))
                    }
                }
            }
        })

        binding.radioGroupBisiesto.setOnCheckedChangeListener { _, checkedId ->
            respuestaSeleccionada = when (checkedId) {
                R.id.sRadioBisiesto -> true
                R.id.noRadioBisiesto -> false
                else -> null
            }
        }

        binding.btnValidar.setOnClickListener {
            val numActual = miViewModel.datos.value?.numGenerado ?: return@setOnClickListener

            if (numActual == 0) {
                Toast.makeText(requireContext(), "Genera un número primero", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (respuestaSeleccionada == null) {
                Toast.makeText(requireContext(), "Selecciona una opción", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            miViewModel.validarBisiesto(respuestaSeleccionada!!)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}