package br.com.fiap.orionidea.rate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.orionidea.calculate.calculateInterest
import br.com.fiap.orionidea.calculate.calculateAmount

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

    fun onValueChanged(newCapital: String){
        _value.value = newCapital
    }

    fun onRateChanged(newRate: String){
        _rate.value = newRate
    }

    fun onPeriodChanged(newTime: String) {
        _period.value = newTime
    }

    fun calculateInvestmentInterest(){
        _interest.value = calculateInterest(
            capital = _value.value!!.toDouble(),
            rate = _rate.value!!.toDouble(),
            time = _period.value!!.toDouble()
        )
    }

    fun calculateAmountInvestment(){
        _amount.value = calculateAmount(
            _value.value!!.toDouble(),
            _interest.value!!.toDouble()
        )
    }
}