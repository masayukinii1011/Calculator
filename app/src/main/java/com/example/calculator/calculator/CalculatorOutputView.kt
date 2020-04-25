package com.example.calculator.calculator

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.calculator.R
import kotlinx.android.synthetic.main.view_calculator_output.view.*

class CalculatorOutputView(context: Context, attributeSet: AttributeSet?) :
    LinearLayout(context, attributeSet), CalculatorOutputInterfaceView {

    //ビジネスロジックはpresenterに持たせる
    //viewは描画、イベントハンドルのみ

    init {

        //set orientation
        orientation = VERTICAL

        //set gravity
        gravity = Gravity.CENTER_VERTICAL

        //inflate layout
        LayoutInflater.from(context)
            .inflate(R.layout.view_calculator_output, this, true)
    }

    fun addItem(item: String) {
        CalculatorOutputPresenter.add(item)
    }

    fun removeItem() {
        CalculatorOutputPresenter.remove()
    }

    fun solve() {
        CalculatorOutputPresenter.solve()
    }

    fun clear() {
        CalculatorOutputPresenter.clear()
    }

    //viewのライフサイクルメソッド
    //viewがアクティビティに追加されたら
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        CalculatorOutputPresenter.attach(this)
    }

    //viewがアクティビティから削除されたら
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        CalculatorOutputPresenter.detach()
    }

    //引数の文字列をviewのテキストにセット
    override fun setEquation(equation: String) {
        calculator_input_equation.text = equation
    }

    //引数の文字列をviewのテキストにセット
    override fun setOutcome(outcome: String) {
        calculator_input_outcome.text = outcome
    }
}