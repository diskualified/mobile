package hu.ait.minesweeper

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class MinesweeperView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val MSModel = MinesweeperModel()
    private val paintBackGround = Paint()
    private val paintLines = Paint()
    private val emptyBackground = Paint()
    private val paintText = Paint()
    private var flagImg = BitmapFactory.decodeResource(resources, R.drawable.marioflag)
    private var mineImg = BitmapFactory.decodeResource(resources, R.drawable.potatomine)

    private var gameOver = false
    init {
        paintBackGround.color = Color.LTGRAY
        paintBackGround.strokeWidth = 5f
        paintBackGround.style = Paint.Style.FILL

        emptyBackground.color = Color.GRAY
        emptyBackground.strokeWidth = 5f
        emptyBackground.style = Paint.Style.FILL

        paintLines.color = Color.WHITE
        paintLines.style = Paint.Style.STROKE
        paintLines.strokeWidth = 5f

        paintText.color = Color.BLUE
        paintText.strokeWidth = 100f
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        paintText.textSize = height / 5f
        flagImg = Bitmap.createScaledBitmap(flagImg, width/5, height/5, false)
        mineImg = Bitmap.createScaledBitmap(mineImg, width/5, height/5, false)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintBackGround)
        drawGameArea(canvas)
        drawPlayers(canvas)
    }

    private fun drawGameArea(canvas: Canvas) {
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintLines)
        for (i in 1 .. 4) {
            canvas.drawLine(
                0f, (i * height / 5).toFloat(), width.toFloat(),
                (i * height / 5).toFloat(), paintLines
            )
            canvas.drawLine(
                (i * width / 5).toFloat(), 0f,
                (i * width / 5).toFloat(), height.toFloat(), paintLines
            )
        }
    }

    private fun drawPlayers(canvas: Canvas) {
        for (i in 0.. 4) {
            for (j in 0..4) {
                if (MSModel.getFieldContent(i, j) == MSModel.MINE) {
                    canvas.drawRect(i * width / 5f + 1f, j * height / 5f +  1f, (i+1) * width / 5f -  1f, (j+1) * height / 5f -  1f, emptyBackground)
                    canvas.drawBitmap(mineImg, i * width / 5f, j * height / 5f, null)
                } else if (MSModel.getFieldContent(i, j) == MSModel.EMPTY) {
                    canvas.drawRect(i * width / 5f + 1f, j * height / 5f +  1f, (i+1) * width / 5f -  1f, (j+1) * height / 5f -  1f, emptyBackground)
                    if (MSModel.calcNeighbors(i, j) > 0) {
                        canvas.drawText(
                            MSModel.calcNeighbors(i, j).toString(),
                            i * width / 5f + 45f,
                            (j + 1) * height / 5f - 30f,
                            paintText
                        )
                    }
                } else if (MSModel.getFieldContent(i, j) == MSModel.FLAG) {
                    canvas.drawBitmap(flagImg, i * width / 5f, j * height / 5f, null)
                }
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN && !gameOver) {
            val tX = event.x.toInt() / (width / 5)
            val tY = event.y.toInt() / (height / 5)
            if (tX < 5 && tY < 5) {
                calcClickOutcome(tX, tY)
            }
            invalidate()
        }
        return true
    }

    private fun calcClickOutcome(tX: Int, tY: Int) {
        if (MSModel.currMode == MSModel.FLAG) {
            calcFlagOutcome(tX, tY)
        } else if (MSModel.getFieldContent(tX, tY) == MSModel.MINE_COVER) {
            // lose
            gameOver = true
            revealBoard()
            (context as MainActivity).showMessage(context.getString(R.string.lossmsg))
        } else if (MSModel.getFieldContent(tX, tY) == MSModel.EMPTY_COVER) {
            MSModel.setFieldContent(tX, tY, MSModel.currMode)
        }
    }

    private fun calcFlagOutcome(tX: Int, tY: Int) {
        var ec = MSModel.getFieldContent(tX, tY) == MSModel.EMPTY_COVER
        var remainingMines = MSModel.setFieldContent(tX, tY, MSModel.currMode)
        if (remainingMines <= 0) {
            // win
            gameOver = true
            revealBoard()
            (context as MainActivity).showMessage(context.getString(R.string.winmsg))
        } else if (remainingMines > 0 && ec) {
            // lose
            gameOver = true
            revealBoard()
            (context as MainActivity).showMessage(context.getString(R.string.lossmsg))
        }
    }

    private fun revealBoard() {
        for (i in 0..4) {
            for (j in 0..4) {
                if (MSModel.getFieldContent(i,j) == MSModel.EMPTY_COVER || MSModel.getFieldContent(i, j) == MSModel.MINE_COVER) {
                    MSModel.setFieldContent(i,j, MSModel.REVEAL)
                }
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val w = View.MeasureSpec.getSize(widthMeasureSpec)
        val h = View.MeasureSpec.getSize(heightMeasureSpec)
        val d = if (w == 0) h else if (h == 0) w else if (w < h) w else h
        setMeasuredDimension(d, d)
    }

    public fun reset() {
        MSModel.resetModel()
        gameOver = false
        invalidate()
        (context as MainActivity).resetButton()
    }

    fun switchMode() {
        MSModel.switchMode()
    }
}