package popularmovies.vicmar.com.popularmovies.ui.base;

import android.view.View;

public interface RecyclerViewListener {


  interface OnItemClickListener {
    void OnItemClick(View view, int position);
  }


  interface OnItemLongClickListener {
    void OnItemLongClick(View view, int position);
  }
}
