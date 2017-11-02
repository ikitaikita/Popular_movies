package popularmovies.vicmar.com.popularmovies.ui.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * A general RecyclerViewAdapter which supports OnItemClickListener and OnItemLongClickListener.
 *
 */
public abstract class BaseRecyclerViewAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private RecyclerViewListener.OnItemClickListener itemClickListener;
  private RecyclerViewListener.OnItemLongClickListener itemLongClickListener;

  public void setOnItemClickListener(
          RecyclerViewListener.OnItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
  }

  public void setOnItemLongClickListener(
          RecyclerViewListener.OnItemLongClickListener itemLongClickListener) {
            this.itemLongClickListener = itemLongClickListener;
  }

  /**
   * Override onBindViewHolder to support OnItemClick and OnItemLongClick listener.
   */
  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
      final int position = i;
      if (itemClickListener != null) {
          viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
              @Override
              public void onClick(View v) {

                  itemClickListener.OnItemClick(v, position);
              }
             });
      }
      if (itemLongClickListener != null) {
          viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener(){

              @Override
              public boolean onLongClick(View v) {
                  itemLongClickListener.OnItemLongClick(v, position);
                  return true;
              }
          });
      }
  }
}
