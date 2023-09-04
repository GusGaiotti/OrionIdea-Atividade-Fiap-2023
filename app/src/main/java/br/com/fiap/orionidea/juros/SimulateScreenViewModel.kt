package br.com.fiap.orionidea.juros

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.orionidea.calculate.calcularJuros
import br.com.fiap.orionidea.calculate.calcularMontante

class SimulateScreenViewModel: ViewModel() {

    private val _value = MutableLiveData<String>()
    val valueState: LiveData<String> = _value

    private val _rate = MutableLiveData<String>()
    val rateState: LiveData<String> = _rate

    private val _period = MutableLiveData<String>()
    val periodState: LiveData<String> = _period

    private val _interest = MutableLiveData<Double>()
    val interestState: LiveData<Double> = _interest

    private val _amount = MutableLiveData<Double>()
    val amountState: LiveData<Double> = _amount

    fun onValueChanged(novoCapital: String){
        _value.value = novoCapital
    }

    fun onRateChanged(novaTaxa: String){
        _rate.value = novaTaxa
    }

    fun onPeriodChanged(novoTempo: String) {
        _period.value = novoTempo
    }

    fun calculateInvestmentInterest(){
        _interest.value = calcularJuros(
            capital = _value.value!!.toDouble(),
            taxa = _rate.value!!.toDouble(),
            tempo = _period.value!!.toDouble()
        )
    }

    fun calculateAmountInvestment(){
        _amount.value = calcularMontante(
            _value.value!!.toDouble(),
            _interest.value!!.toDouble()
        )
    }
}