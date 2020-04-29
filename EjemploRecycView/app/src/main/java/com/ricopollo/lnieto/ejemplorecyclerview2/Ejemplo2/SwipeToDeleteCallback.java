package com.ricopollo.lnieto.ejemplorecyclerview2.Ejemplo2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.ricopollo.lnieto.ejemplorecyclerview2.R;

abstract public class SwipeToDeleteCallback extends ItemTouchHelper.Callback {
    Context context;
    private Paint clearPaint;
    private ColorDrawable background;
    private int backgroundColor;
    private Drawable deleteDrawable;
    private int intrinsicWidth;
    private int intrinsicHeight;

    SwipeToDeleteCallback(Context context){
        this.context = context;
        this.background = new ColorDrawable();
        this.backgroundColor = Color.parseColor("#b80f0a");
        this.clearPaint = new Paint();
        this.clearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        this.deleteDrawable = ContextCompat.getDrawable(this.context, R.drawable.ic_delete_white_24dp);
        this.intrinsicWidth = this.deleteDrawable.getIntrinsicWidth();
        this.intrinsicHeight = this.deleteDrawable.getIntrinsicHeight();
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        View itemView = viewHolder.itemView;
        int itemHeight = itemView.getHeight();
        boolean isCancelled = dX == 0 && !isCurrentlyActive;

        if(isCancelled){
            clearCanvas(c, itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            return;
        }

        this.background.setColor(this.backgroundColor);
        this.background.setBounds(itemView.getRight() + (int) dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
        this.background.draw(c);

        int deleteIconTop = itemView.getTop() + (itemHeight - this.intrinsicHeight) / 2;
        int deleteIconMargin = (itemHeight - this.intrinsicHeight) / 2;
        int deleteIconLeft = itemView.getRight() - deleteIconMargin - this.intrinsicWidth;
        int deleteIconRight = itemView.getRight() - deleteIconMargin;
        int deleteIconBottom = deleteIconTop + this.intrinsicHeight;

        this.deleteDrawable.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom);
        this.deleteDrawable.draw(c);

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }


    @Override
    public float getSwipeThreshold(RecyclerView.ViewHolder viewHolder) {
        return 0.5f;
    }

    private void clearCanvas(Canvas c, Float left, Float top, Float right, Float bottom){
        c.drawRect(left, top, right, bottom, this.clearPaint);
    }

}
