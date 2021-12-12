package org.wit.myassignment.ui.swipe

import android.content.Context
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ValueEventListener
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import org.wit.myassignment.R


abstract class SwipeGesture(context: Context) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {



    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {

        return false
    }

    override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean)
    {


        RecyclerViewSwipeDecorator.Builder(c,recyclerView, viewHolder, dX, dY, actionState,isCurrentlyActive)
            .addSwipeLeftBackgroundColor(R.color.deletecolor)
            .addSwipeLeftActionIcon(R.drawable.ic_remove)
            .create()
            .decorate()

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }


}