package com.example.calculator

import com.example.calculator.calculator.CalculatorOutputInterfaceView
import com.example.calculator.calculator.CalculatorOutputPresenter
import org.junit.Test
import org.mockito.BDDMockito.then
import org.mockito.Mockito

class CalculatorOutputTest {

    private val mmPresenter = CalculatorOutputPresenter
    private val mmMockView = Mockito.mock(CalculatorOutputInterfaceView::class.java)

    @Test
    fun `1 plus 1 is 2`() {

        //given that the view is attached
        mmPresenter.attach(mmMockView)

        //when a number is added
        mmPresenter.add("1")

        //then the correct equation should be set
        then(mmMockView).should().setEquation("1")

        //when an operator is added
        mmPresenter.add("+")

        //then the correct equation should be set
        then(mmMockView).should().setEquation("1+")

        //when a number is added
        mmPresenter.add("1")

        //then the correct equation should be set
        then(mmMockView).should().setEquation("1+1")

        //the correct outcome should be set
        then(mmMockView).should().setOutcome("2")

        //clear presenter
        mmPresenter.clear()
    }

    @Test
    fun `2 plus 2 minus 1 is 3`() {

        //given that the view is attached
        mmPresenter.attach(mmMockView)

        //when a number is added
        mmPresenter.add("2")

        //then the correct equation should be set
        then(mmMockView).should().setEquation("2")

        //when an operator is added
        mmPresenter.add("+")

        //then the correct equation should be set
        then(mmMockView).should().setEquation("2+")

        //when a number is added
        mmPresenter.add("2")

        //then the correct equation should be set
        then(mmMockView).should().setEquation("2+2")

        //when an operator is added
        mmPresenter.add("-")

        //then the correct equation should be set
        then(mmMockView).should().setEquation("2+2-")

        //when a number is added
        mmPresenter.add("1")

        //then the correct equation should be set
        then(mmMockView).should().setEquation("2+2-1")

        //the correct outcome should be set
        then(mmMockView).should().setOutcome("3")

        //clear presenter
        mmPresenter.clear()
    }
}