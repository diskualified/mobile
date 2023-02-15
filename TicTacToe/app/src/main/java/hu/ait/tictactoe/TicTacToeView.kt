package hu.ait.tictactoe

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class TicTacToeView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paintBackground = Paint()
    private val paintLines = Paint()

    init {
        paintBackground.color = Color.BLACK
        paintBackground.strokeWidth = 5f
        paintBackground.style = Paint.Style.FILL

        paintLines.color = Color.WHITE
        paintLines.strokeWidth = 5f
        paintLines.style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintBackground)
        canvas?.drawLine(0f, height.toFloat()/3, width.toFloat(), height.toFloat()/3, paintLines)
        canvas?.drawLine(0f, height.toFloat()*2/3, width.toFloat(), height.toFloat()*2/3, paintLines)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }
}