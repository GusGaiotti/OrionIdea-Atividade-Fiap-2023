package br.com.fiap.orionidea.calculate

fun calculateInterest(capital: Double, rate: Double, time: Double): Double {
    return capital * rate / 100 * time
}

fun calculateAmount(capital: Double, rate: Double): Double {
    return capital + rate
}