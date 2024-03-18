package com.example.customclockview.customView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import com.example.customclockview.R
import java.util.Calendar
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

class ClockCustom @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        //dial settings
        private const val DEFAULT_CLOCK_RADIUS = 200f
        private const val DEFAULT_SIZE_IN_DP = 200
        private const val DEFAULT_CLOCK_COLOR = Color.WHITE

        //border settings
        private const val DEFAULT_CLOCK_BORDER_COLOR = Color.BLACK
        private const val DEFAULT_CLOCK_BORDER_WIDTH = 12f

        //arrow settings
        private const val DEFAULT_SECOND_HAND_COLOR = Color.BLACK
        private const val DEFAULT_SECOND_HAND_SIZE = 200f

        private const val DEFAULT_MINUTE_HAND_COLOR = Color.BLACK
        private const val DEFAULT_MINUTE_HAND_SIZE = 200f

        private const val DEFAULT_HOUR_HAND_COLOR = Color.BLACK
        private const val DEFAULT_HOUR_HAND_SIZE = 200f

        //minute point settings
        private const val DEFAULT_MINUTE_DOTS_COLOR = Color.BLACK
        private const val DEFAULT_MINUTE_DOTS_SIZE = 3f


        //minute point settings
        private const val DEFAULT_DIGIT_COLOR = Color.BLACK
        private const val DEFAULT_DIGIT_SIZE = 50f


        private const val START_ANGLE = -Math.PI / 2
    }


    var clockRadius: Float = 0f
    var clockColor: Int = 0

    var clockBorderWidth: Float = 0f
    var clockBorderColor: Int = 0

    var secondHandColor: Int = 0
    var secondHandSize: Float = 0f

    var minuteHandColor: Int = 0
    var minuteHandSize: Float = 0f

    var hourHandColor: Int = 0
    var hourHandSize: Float = 0f

    var minuteDotsColor: Int = 0
    var minuteDotsSize: Float = 0f

    var digitColor: Int = 0
    var digitSize: Float = 0f

    var centerX = 0.0f
    var centerY = 0.0f

    private val position: PointF = PointF(0.0f, 0.0f)


    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)


    init {
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.ClockCustom, defStyleAttr, 0)
        try {

            clockRadius =
                typedArray.getDimension(R.styleable.ClockCustom_clockRadius, DEFAULT_CLOCK_RADIUS)

            clockColor =
                typedArray.getColor(R.styleable.ClockCustom_clockColor, DEFAULT_CLOCK_COLOR)

            clockBorderWidth = typedArray.getDimension(
                R.styleable.ClockCustom_clockBorderWidth,
                DEFAULT_CLOCK_BORDER_WIDTH
            )

            clockBorderWidth = typedArray.getDimension(
                R.styleable.ClockCustom_clockBorderWidth,
                DEFAULT_CLOCK_BORDER_WIDTH
            )

            clockBorderColor = typedArray.getColor(
                R.styleable.ClockCustom_clockBorderColor,
                DEFAULT_CLOCK_BORDER_COLOR
            )

            secondHandColor = typedArray.getColor(
                R.styleable.ClockCustom_secondHandColor,
                DEFAULT_SECOND_HAND_COLOR
            )

            secondHandSize =
                typedArray.getDimension(R.styleable.ClockCustom_secondHandSize, DEFAULT_SECOND_HAND_SIZE)

            minuteHandColor = typedArray.getColor(
                R.styleable.ClockCustom_minuteHandColor,
                DEFAULT_MINUTE_HAND_COLOR
            )

            minuteHandSize =
                typedArray.getDimension(R.styleable.ClockCustom_minuteHandSize, DEFAULT_MINUTE_HAND_SIZE)

            hourHandColor =
                typedArray.getColor(R.styleable.ClockCustom_hourHandColor, DEFAULT_HOUR_HAND_COLOR)

            hourHandSize =
                typedArray.getDimension(R.styleable.ClockCustom_hourHandSize, DEFAULT_HOUR_HAND_SIZE)

            minuteDotsColor = typedArray.getColor(
                R.styleable.ClockCustom_minuteDotsColor,
                DEFAULT_MINUTE_DOTS_COLOR
            )

            minuteDotsSize =
                typedArray.getDimension(R.styleable.ClockCustom_minuteDotsSize, DEFAULT_MINUTE_DOTS_SIZE)

            digitColor =
                typedArray.getColor(R.styleable.ClockCustom_digitColor, DEFAULT_DIGIT_COLOR)

            digitSize =
                typedArray.getDimension(R.styleable.ClockCustom_digitSize, DEFAULT_DIGIT_SIZE)
        } finally {
            typedArray.recycle()
        }
    }


    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        clockRadius = min(width, height) / 2f
        centerX = width / 2f
        centerY = height / 2f
    }


    private fun PointF.computeXYForPoints(pos: Int, radius: Float) {
        val angle = (pos * (Math.PI / 30)).toFloat()
        x = radius * cos(angle) + centerX
        y = radius * sin(angle) + centerY
    }


    private fun PointF.computeXYForHourLabels(hour: Int, radius: Float) {
        val angle = (START_ANGLE + hour * (Math.PI / 6)).toFloat()
        x = radius * cos(angle) + centerX
        y = radius * sin(angle) + centerY
    }


    //Draws a dial
    private fun drawClockDial(canvas: Canvas) {
        paint.color = clockColor
        paint.style = Paint.Style.FILL
        canvas.drawCircle(centerX, centerY, clockRadius, paint)
    }


    //Draws the border of the dial.
    // Метод для рисования рамки циферблата
    private fun drawClockBorder(canvas: Canvas) {
        paint.color = clockBorderColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = clockBorderWidth

        // Учитываем ширину рамки при вычислении радиуса
        val boundaryRadius = clockRadius - paint.strokeWidth / 2

        // Рисуем окружность рамки циферблата
        canvas.drawCircle(centerX, centerY, boundaryRadius, paint)

        // Устанавливаем толщину рамки в 0 для дальнейшей отрисовки
        paint.strokeWidth = 0f
    }


    //Draw minute markers on the dial
    private fun drawDots(canvas: Canvas) {
        paint.color = minuteDotsColor
        paint.style = Paint.Style.FILL
        val dotsDrawLineRadius = (clockRadius - clockBorderWidth / 2) * 11/12
        for (i in 0 until 60) {
            position.computeXYForPoints(i, dotsDrawLineRadius)
            val dotRadius = if (i % 5 == 0) minuteDotsSize * 1.5f else minuteDotsSize
            canvas.drawCircle(position.x, position.y, dotRadius, paint)
        }
    }


    //Draws the hour numbers on the dial
    private fun drawHourLabels(canvas: Canvas) {
        paint.textSize = digitSize
        paint.strokeWidth = 0f
        paint.color = digitColor

        // Учитываем ширину рамки для расположения меток
        val labelsDrawLineRadius = (clockRadius - clockBorderWidth / 2)  * 25 / 32
        val textHeight = (paint.descent() - paint.ascent())

        for (i in 1..12) {
            position.computeXYForHourLabels(i, labelsDrawLineRadius)
            val label = i.toString()
            val textWidth = paint.measureText(label)
            canvas.drawText(label, position.x - textWidth / 2, position.y + textHeight / 2, paint)
        }
    }


    //Gets the current time and draws the hour,
    // minute and second hands on the canvas.
    private fun drawClockHands(canvas: Canvas) {
        val calendar: Calendar = Calendar.getInstance()
        var hour = calendar.get(Calendar.HOUR_OF_DAY)
        hour = if (hour > 12) hour - 12 else hour
        val minute = calendar.get(Calendar.MINUTE)
        val second = calendar.get(Calendar.SECOND)
        paint.style = Paint.Style.STROKE
        drawHourHand(canvas, hour + minute / 60f)
        drawMinuteHand(canvas, minute)
        drawSecondHand(canvas, second)
    }


    //Draws the hour hand
    private fun drawHourHand(canvas: Canvas, hourWithMinutes: Float) {
        paint.color = hourHandColor
        paint.strokeWidth = clockRadius / 15
        val angle = (Math.PI * hourWithMinutes / 6 + START_ANGLE).toFloat()

        if (hourHandSize == DEFAULT_HOUR_HAND_SIZE){
            canvas.drawLine(
                centerX - cos(angle) * clockRadius * 3 / 14,
                centerY - sin(angle) * clockRadius * 3 / 14,
                centerX + cos(angle) * clockRadius * 7 / 14,
                centerY + sin(angle) * clockRadius * 7 / 14,
                paint
            )
        }
        else {
            canvas.drawLine(
                centerX - cos(angle) * hourHandSize * 3 / 14,
                centerY - sin(angle) * hourHandSize * 3 / 14,
                centerX + cos(angle) * hourHandSize * 7 / 14,
                centerY + sin(angle) * hourHandSize * 7 / 14,
                paint
            )
        }

    }


    //Draws the minute hand
    private fun drawMinuteHand(canvas: Canvas, minute: Int) {
        paint.color = minuteHandColor
        paint.strokeWidth = clockRadius / 40
        val angle = (Math.PI * minute / 30 + START_ANGLE).toFloat()

        if (minuteHandSize == DEFAULT_MINUTE_HAND_SIZE){
            canvas.drawLine(
                centerX - cos(angle) * clockRadius * 2 / 7,
                centerY - sin(angle) * clockRadius * 2 / 7,
                centerX + cos(angle) * clockRadius * 5 / 7,
                centerY + sin(angle) * clockRadius * 5 / 7,
                paint
            )
        }
        else {
            canvas.drawLine(
                centerX - cos(angle) * minuteHandSize * 2 / 7,
                centerY - sin(angle) * minuteHandSize * 2 / 7,
                centerX + cos(angle) * minuteHandSize * 5 / 7,
                centerY + sin(angle) * minuteHandSize * 5 / 7,
                paint
            )
        }
    }


    //Draws the second hand
    private fun drawSecondHand(canvas: Canvas, second: Int) {
        paint.color = secondHandColor
        val angle = (Math.PI * second / 30 + START_ANGLE).toFloat()
        paint.strokeWidth = clockRadius / 80



        if (secondHandSize == DEFAULT_SECOND_HAND_SIZE){
            canvas.drawLine(
                centerX - cos(angle) * clockRadius * 1 / 14,
                centerY - sin(angle) * clockRadius * 1 / 14,
                centerX + cos(angle) * clockRadius * 5 / 7,
                centerY + sin(angle) * clockRadius * 5 / 7,
                paint
            )

            paint.strokeWidth = clockRadius / 50
            canvas.drawLine(
                centerX - cos(angle) * clockRadius * 2 / 7,
                centerY - sin(angle) * clockRadius * 2 / 7,
                centerX - cos(angle) * clockRadius * 1 / 14,
                centerY - sin(angle) * clockRadius * 1 / 14,
                paint
            )
        }
        else {
            canvas.drawLine(
                centerX - cos(angle) * secondHandSize * 1 / 14,
                centerY - sin(angle) * secondHandSize * 1 / 14,
                centerX + cos(angle) * secondHandSize * 5 / 7,
                centerY + sin(angle) * secondHandSize * 5 / 7,
                paint
            )

            paint.strokeWidth = clockRadius / 50
            canvas.drawLine(
                centerX - cos(angle) * secondHandSize * 2 / 7,
                centerY - sin(angle) * secondHandSize * 2 / 7,
                centerX - cos(angle) * secondHandSize * 1 / 14,
                centerY - sin(angle) * secondHandSize * 1 / 14,
                paint
            )
        }
    }


    //Drawing hour markers, hands, dial, etc.
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawClockDial(canvas)

        drawClockBorder(canvas)

        drawDots(canvas)

        drawHourLabels(canvas)

        drawClockHands(canvas)

        postInvalidateDelayed(180L)
    }


    //Determining the dimensions of the representation in accordance
   // with the transmitted measurement specifications.
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val defaultWidth = (DEFAULT_SIZE_IN_DP * resources.displayMetrics.density).toInt()
        val defaultHeight = (DEFAULT_SIZE_IN_DP * resources.displayMetrics.density).toInt()

        val widthToSet = resolveSize(defaultWidth, widthMeasureSpec)
        val heightToSet = resolveSize(defaultHeight, heightMeasureSpec)

        setMeasuredDimension(widthToSet, heightToSet)
    }


    // Saves the color state of the clock elements.
    override fun onSaveInstanceState(): Parcelable {
        val bundle = Bundle()
        bundle.putParcelable("superState", super.onSaveInstanceState())

        bundle.putInt("clockColor", clockColor)

        bundle.putInt("digitColor", digitColor)
        bundle.putFloat("digitSize", digitSize)


        bundle.putInt("clockBorderColor", clockBorderColor)
        bundle.putFloat("clockBorderWidth", clockBorderWidth)


        bundle.putInt("minuteDotsColor", minuteDotsColor)
        bundle.putFloat("minuteDotsSize", minuteDotsSize)


        bundle.putInt("hourHandColor", hourHandColor)
        bundle.putFloat("hourHandSize", hourHandSize)

        bundle.putInt("minuteHandColor", minuteHandColor)
        bundle.putFloat("minuteHandSize", minuteHandSize)

        bundle.putInt("secondHandColor", secondHandColor)
        bundle.putFloat("secondHandSize", secondHandSize)



        return bundle
    }


    // Restores the saved state of the colors of the clock elements.
    override fun onRestoreInstanceState(state: Parcelable?) {
        var superState: Parcelable? = null
        if (state is Bundle) {
            clockColor = state.getInt("clockColor")

            digitColor = state.getInt("digitColor")
            digitSize = state.getFloat("digitSize")

            clockBorderColor = state.getInt("clockBorderColor")
            clockBorderWidth = state.getFloat("clockBorderColor")

            minuteDotsColor = state.getInt("minuteDotsColor")
            minuteDotsSize = state.getFloat("minuteDotsSize")

            hourHandColor = state.getInt("hourHandColor")
            hourHandSize = state.getFloat("hourHandSize")

            minuteHandColor = state.getInt("minuteHandColor")
            minuteHandSize = state.getFloat("minuteHandSize")

            secondHandColor = state.getInt("secondHandColor")
            secondHandSize = state.getFloat("secondHandSize")

            superState =
                if (Build.VERSION.SDK_INT >= 33) state.getParcelable(
                    "superState",
                    Parcelable::class.java
                )
                else @Suppress("DEPRECATION") state.getParcelable("superState")
        }
        super.onRestoreInstanceState(superState)
    }
}