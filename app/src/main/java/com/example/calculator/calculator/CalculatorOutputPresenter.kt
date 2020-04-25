package com.example.calculator.calculator

import bsh.Interpreter
import java.lang.Exception

object CalculatorOutputPresenter {

    //current attached view
    private var mmView: CalculatorOutputInterfaceView? = null

    //current equation
    private var mmCurrentEquation: String = ""

    //current outcome
    private var mmCurrentOutcome: String = ""

    //interpreter
    private val mmInterpreter = Interpreter()

    //引数に与えられたviewをmmViewに結びつける
    fun attach(view: CalculatorOutputInterfaceView) {
        mmView = view
        updateEquation()
        updateOutcome()
    }

    //mmViewを空にする
    fun detach() {
        mmView = null
    }

    fun add(item: String) {
        mmCurrentEquation = mmCurrentEquation.plus(item)//文字列の追加
        updateEquation()
        calculateOutcome()
        updateOutcome()
    }

    fun remove() {
        mmCurrentEquation = if (mmCurrentEquation.length > 1) {
            //substringは文字の切り出し(切り出しを始める開始位置, 切り出しの終了位置 )
            //つまり空文字にする
            mmCurrentEquation.substring(0, mmCurrentEquation.length - 1)
        } else {
            ""
        }
        updateEquation()
        calculateOutcome()
        updateOutcome()
    }

    fun solve() {
        if (mmCurrentOutcome.isNotEmpty()) {
            mmCurrentEquation = mmCurrentOutcome
            mmCurrentOutcome = ""
        }
        updateEquation()
        updateOutcome()
    }

    fun clear() {
        mmCurrentEquation = ""
        mmCurrentOutcome = ""
        updateEquation()
        updateOutcome()
    }

    private fun calculateOutcome() {
        if (mmCurrentEquation.isNotEmpty()) {
            try {
                //文字列を式として評価
                mmInterpreter.eval("result = $mmCurrentEquation")
                val result = mmInterpreter.get("result")

                if (result != null && result is Int) {
                    //結果を文字列に変換して代入
                    mmCurrentOutcome = result.toString()
                }

            } catch (e: Exception) {
                mmCurrentOutcome = ""
            }
        } else {
            mmCurrentOutcome = ""
        }
    }

    //viewのsetEquationを実行
    private fun updateEquation() {
        mmView?.setEquation(mmCurrentEquation)
    }

    //viewのsetOutcomeを実行
    private fun updateOutcome() {
        mmView?.setOutcome(mmCurrentOutcome)
    }
}