package com.example.interesesapp.ui.theme.viewmodels

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.interesapp.components.ShowInfoCards
import com.example.interesesapp.components.Alert
import com.example.interesesapp.components.MainButton
import com.example.interesesapp.components.MainTextField
import com.example.interesesapp.components.SpaceH

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun HomeView(viewModel: PrestamoViewModel){
    Scaffold(topBar = {
            CenterAlignedTopAppBar(
                title = {Text(text = "Calculadora Prestamos", color = Color.White)},
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }){
     ContentHomeView(it , viewModel)
    }
}
@Composable
fun ContentHomeView(paddingValues: PaddingValues, viewModel: PrestamoViewModel){
    val state = viewModel.state
    ShowInfoCards(
        titleInteres = "Total: ",
        montoInteres = state.montoInteres,
        titleMonto = "Cuota: ",
        monto = state.montoCuota
    )
    MainTextField(value = state.montoPrestamo,
        onValueChange = {viewModel.onValue(value = it, campo = "montoPrestamo")}, label = "Prestamo")
    SpaceH()
    MainTextField(value = state.cantCuotas,
        onValueChange = {viewModel.onValue(value = it, campo = "cuotas")}, label = "Cuotas")
    SpaceH(10.dp)
    MainTextField(value = state.tasa,
        onValueChange = {viewModel.onValue(value = it, campo = "tasa")}, label = "Tasa")
    SpaceH(10.dp)
    MainButton(text = "Calcular") {
        viewModel.calcular()
    }
    SpaceH()
    MainButton(text = "Limpiar", color = Color.Red) {
        viewModel.limpiar()
    }
    if (viewModel.state.showAlert){
        Alert(title = "Alerta",
            message = "Ingresa los datos",
            confirmText = "Aceptar",
            onConfirmClick = {viewModel.confirmDialog()}) {
        }
    }
}