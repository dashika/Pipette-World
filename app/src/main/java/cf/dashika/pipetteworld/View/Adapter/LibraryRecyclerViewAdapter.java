package cf.dashika.pipetteworld.View.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import cf.dashika.pipetteworld.DisplayUtil;
import cf.dashika.pipetteworld.Model.Adobe.Element;
import cf.dashika.pipetteworld.Model.Adobe.Representation;
import cf.dashika.pipetteworld.Model.Adobe.Swatch;
import cf.dashika.pipetteworld.R;

public class LibraryRecyclerViewAdapter extends RecyclerView.Adapter<LibraryRecyclerViewAdapter.ViewHolder> {

    private final List<Element> mValues;
    Context context;
    ViewGroup.LayoutParams params;
    Point p;

    public LibraryRecyclerViewAdapter(List<Element> items, Context context) {
        mValues = items;
        p = DisplayUtil.getScreenMetrics(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_librarycolor, parent, false);
        params = view.getLayoutParams();
        params.width = p.x;

        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.init();
    }

    public void animateTo(List<Element> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<Element> newModels) {
        for (int i = mValues.size() - 1; i >= 0; i--) {
            final Element model = mValues.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<Element> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final Element model = newModels.get(i);
            if (!mValues.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<Element> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final Element model = newModels.get(toPosition);
            final int fromPosition = mValues.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }


    public Element removeItem(int position) {
        final Element model = mValues.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, Element model) {
        mValues.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final Element model = mValues.remove(fromPosition);
        mValues.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final LinearLayout layoutPallete;
        final LinearLayout layoutParam;
        Element mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            layoutPallete = (LinearLayout) view.findViewById(R.id.layout_pallete);
            layoutParam = (LinearLayout) view.findViewById(R.id.example);
        }

        void init() {
            if (mItem == null) return;
            layoutPallete.removeAllViews();
            try {
                for (Representation representation : mItem.getRepresentations()) {
                    List<List<Swatch>> swatch = representation.getColorthemeData().getSwatches();
                    int count = swatch.size() + 1;

                    for (List<Swatch> swatch1 : swatch) {
                        View viewChild = LayoutInflater.from(context).inflate(R.layout.item_color, null, false);
                        params = layoutParam.getLayoutParams();
                        params.width = p.x / count;
                        ImageView img = (ImageView) viewChild.findViewById(R.id.imgColor);
                        int color = Color.rgb(
                                swatch1.get(0).getValue().getR(),
                                swatch1.get(0).getValue().getG(),
                                swatch1.get(0).getValue().getB());
                        img.setBackgroundColor(color);
                        viewChild.setLayoutParams(params);
                        TextView textView = (TextView) viewChild.findViewById(R.id.tvColor);
                        textView.setText("#" + Integer.toHexString(color));
                        layoutPallete.addView(viewChild);
                    }
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }

    }
}
