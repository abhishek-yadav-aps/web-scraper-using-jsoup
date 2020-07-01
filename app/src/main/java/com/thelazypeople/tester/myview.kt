package com.thelazypeople.tester

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View

class myview(context: Context?) : View(context) {


    override fun onDraw(canvas: Canvas?) {

        var paint= Paint()
        var line:android.graphics.Path=android.graphics.Path()
        line.moveTo(50f,300f)
        line.lineTo(160f,280f)
        line.lineTo(300f,380f)
        line.lineTo(380f,370f)
        line.lineTo(280f,450f)
        line.lineTo(100f,390f)
        line.lineTo(160f,380f)
        line.lineTo(50f,300f)
        paint.setARGB(256,256,0,0)
        canvas?.drawPath(line,paint)
        super.onDraw(canvas)
    }
    fun draw(){
        invalidate()
        requestLayout()
    }
}