//package com.hans.demo.mc;
//
//import android.content.Context;
//import android.graphics.Color;
//import android.support.v7.widget.RecyclerView;
//import android.text.TextUtils;
//import android.util.Log;
//import android.util.SparseArray;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.yiche.autoeasy.R;
//import com.yiche.autoeasy.module.cartype.data.carparam.Cell;
//import com.yiche.autoeasy.module.cartype.data.carparam.Line;
//import com.yiche.autoeasy.module.cartype.data.carparam.LineChoosePac;
//import com.yiche.autoeasy.module.cartype.data.carparam.LineGroup;
//import com.yiche.autoeasy.module.cartype.data.carparam.LineParams;
//import com.yiche.autoeasy.tool.CollectionsWrapper;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
///**
// * Created by sudi on 2017/10/23.
// * Email：sudi@yiche.com
// */
//
//public class LinesAdapter2 extends RecyclerView.Adapter {
//
//    private static final int GROUP_TYPE = 0;
//    private static final int LINE_TYPE = 1;
//    private static final String TAG = "LinesAdapter";
//    private List<McParamsModel.McLineBean> mLines;
//    private Context mContext;
//    private CellsScrollHandler mScrollhandler;
//    private SparseArray<String> groupPosition;
//    private boolean isCarCompare;
//
//    public LinesAdapter2(Context context, CellsScrollHandler scrollhandler) {
//        mLines = new ArrayList<>();
//        this.mContext = context;
//        groupPosition = new SparseArray<>();
//        mScrollhandler = scrollhandler;
//    }
//
//    //-----------------section部分----start-------------
////    @Override
////    public long getHeaderId(int position) {
////        try {
////            return mLines.get(position).groupId;
////        } catch (Exception e) {
////            return -1;
////        }
////    }
////
////    @Override
////    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
////        View view = LayoutInflater.from(mContext).inflate(R.layout.pinned_header_listview_header, parent, false);
////        LineGroupTitleHolder holder = new LineGroupTitleHolder(view);
////        return holder;
////    }
////
////    @Override
////    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
////        if (holder instanceof LineGroupTitleHolder) {
////            ((LineGroupTitleHolder) holder).bindData(position, mLines.get(position));
////        }
////    }
//    //-----------------section部分----end---------------
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        RecyclerView.ViewHolder viewHolder;
//        View lineView;
//        if (viewType == Line.LINE_TYPE_CHOOSE_PAC) {
//            lineView = LayoutInflater.from(mContext).inflate(R.layout.view_item_line_choose_pac, parent, false);
//            viewHolder = new LineChoosePacViewHolder(mContext, lineView);
//        } else if (viewType == Line.LINE_TYPE_GROUP) {
//            View view = LayoutInflater.from(mContext).inflate(R.layout.pinned_header_listview_header, parent, false);
//            viewHolder = new LineGroupTitleHolder(view);
//        } else if (viewType == Line.LINE_TYPE_BOTTOM) {
//            View view = LayoutInflater.from(mContext).inflate(R.layout.view_item_line_params_bottom, parent, false);
//            viewHolder = new LineGroupBottomHolder(view);
//        } else {
//            lineView = LayoutInflater.from(mContext).inflate(R.layout.view_item_line_params, parent, false);
//            viewHolder = new LineParamsViewHolder(mContext, lineView, isCarCompare);
//        }
//        if (viewHolder instanceof LineViewHolder) {
//            mScrollhandler.regist(((LineViewHolder) viewHolder).mCellsView);
//        }
//
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        if (holder instanceof LineViewHolder) {
//            ((LineViewHolder) holder).bindData(position, mLines.get(position));
//        } else if (holder instanceof LineGroupTitleHolder) {
//            ((LineGroupTitleHolder) holder).bindData(position, mLines.get(position));
//        }
//    }
//
//
//    @Override
//    public int getItemViewType(int position) {
//        if (!CollectionsWrapper.isEmpty(mLines)) {
//            return mLines.get(position).lineType;
//        }
//        return super.getItemViewType(position);
//    }
//
//    @Override
//    public int getItemCount() {
//        return mLines.size();
//    }
//
//    public void setData(List<Line> lines) {
//        mLines.clear();
//        if (lines != null) {
//            processGroupName(lines);
//        }
//        Log.d("sudi", TAG + "bindData: " + mLines.size());
//        notifyDataSetChanged();
//    }
//
//
//    private void processGroupName(List<Line> lines) {
//        int groupId = -1;
//        groupPosition.clear();
//
//        for (int i = 0; i < lines.size(); i++) {
//            Line line = lines.get(i);
//            if (groupId != line.groupId) {
//                LineGroup lineGroup = new LineGroup(Line.LINE_TYPE_GROUP, "", line.groupId, line.groupName);
//                mLines.add(lineGroup);
//                groupPosition.put(i + groupPosition.size(), line.groupName);
//                groupId = line.groupId;
//            }
//            mLines.add(line);
//        }
//        mLines.add(new LineGroup(Line.LINE_TYPE_BOTTOM, "", 0, ""));
//    }
//
//    private void setGroupNames() {
//        int lastid = -1;
//        groupPosition.clear();
//        for (int i = 0, l = mLines.size(); i < l; i++) {
//            Line line = mLines.get(i);
//            if (lastid != line.groupId) {
//                groupPosition.put(i, line.groupName);
//                lastid = line.groupId;
//            }
//        }
//    }
//
//    public SparseArray<String> getGroupNames() {
//        return groupPosition;
//    }
//
//    public int getPositionForSection(int index) {
//        return groupPosition.keyAt(index);
//    }
//
//    public void setIsCarCompare(boolean isCarCompare) {
//        this.isCarCompare = isCarCompare;
//    }
//
//    /**
//     * 行group
//     */
//    private static class LineGroupTitleHolder extends RecyclerView.ViewHolder {
//        private TextView mGroupTitleView;
//        private ImageView imageView;
//
//        public LineGroupTitleHolder(View itemView) {
//            super(itemView);
//            mGroupTitleView = (TextView) itemView.findViewById(R.id.header_text);
//            imageView = (ImageView) itemView.findViewById(R.id.header_image);
//        }
//
//        public void bindData(int position, Line title) {
//            mGroupTitleView.setText(title.groupName);
//            imageView.setVisibility(TextUtils.equals(title.groupName, "基本信息") ? View.INVISIBLE : View.VISIBLE);//通过基本信息来是否显示右边的头部标识
//        }
//    }
//
//    private static class LineGroupBottomHolder extends RecyclerView.ViewHolder {
//
//        public LineGroupBottomHolder(View itemView) {
//            super(itemView);
//        }
//
//        public void bindData(int position, Line title) {
//
//        }
//    }
//
//    /**
//     * 行ViewHolder
//     */
//    private static class LineViewHolder extends RecyclerView.ViewHolder {
//
//        McCarParamsLine mLineView;
//
//        public LineViewHolder(Context context) {
//            super(McCarParamsLine.createForRecyclerView(context));
//            mLineView = (McCarParamsLine) itemView;
//
//        }
//
//        public void bindData(int position, McParamsModel.McLineBean lineData) {
//            mLineView.setData(lineData);
//        }
//
//    }
//
//    private static class LineParamsViewHolder extends LineViewHolder<LineParams> {
//        private boolean isCarCompare;
//
//        public LineParamsViewHolder(Context context, View lineView, boolean isCarCompare) {
//            super(context, lineView);
//            this.isCarCompare = isCarCompare;
//        }
//
//        @Override
//        public void bindData(int position, LineParams lineData) {
//            super.bindData(position, lineData);
//            if (lineData.isSameData() && (isCarCompare ? lineData.getCells().size() >= 3 : lineData.getCells().size() >= 2)) {
//                mCellsView.setVisibility(View.GONE);
//                mRlCellContent.setVisibility(View.VISIBLE);
//                mTvCellContent.setVisibility(View.VISIBLE);
//                mCVCellContent.setVisibility(View.GONE);
//                List<Cell> cells = lineData.getCells();
//                Cell cell = cells.get(0);
//                if (cell.getCellType() == Cell.CELL_TYPE_TXT) {
//                    Cell.DefaultCell defaultCell = (Cell.DefaultCell) cell;
//                    StringBuilder stringBuilder = new StringBuilder();
//                    for (int i = 0, l = defaultCell.mCellItems.size(); i < l; i++) {
//                        Cell.CellItem paramsDetail = defaultCell.mCellItems.get(i);
//                        if (paramsDetail != null) {
//                            if (!TextUtils.isEmpty(paramsDetail.mFront)) {
//                                if (stringBuilder.toString().length() != 0) {
//                                    stringBuilder.append("\n");
//                                }
//                                stringBuilder.append(paramsDetail.mFront);
//                            }
//                            if (!TextUtils.isEmpty(paramsDetail.mPrice)) {
//                                if (stringBuilder.toString().length() != 0) {
//                                    stringBuilder.append("\n     ");
//                                }
//                                stringBuilder.append(paramsDetail.mPrice);
//                            }
//                        }
//                    }
//                    mTvCellContent.setText(stringBuilder.toString().trim());
//                    itemView.setMinimumHeight(mTvCellContent.getMeasuredHeight() > LineParams.CELL_MIN_HEIGHT ?
//                            mTvCellContent.getMeasuredHeight() : LineParams.CELL_MIN_HEIGHT);
//                } else if (cell.getCellType() == Cell.CELL_TYPE_COLOR) {
//                    mCellsView.setVisibility(View.GONE);
//                    mRlCellContent.setVisibility(View.VISIBLE);
//                    mTvCellContent.setVisibility(View.GONE);
//                    mCVCellContent.setVisibility(View.VISIBLE);
//                    Cell.ColorCell colorCell = (Cell.ColorCell) cell;
//                    mCVCellContent.setColors(colorCell);
//                    itemView.setMinimumHeight(mCVCellContent.getMeasuredHeight() > LineParams.CELL_MIN_HEIGHT ?
//                            mTvCellContent.getMeasuredHeight() : LineParams.CELL_MIN_HEIGHT);
//                } else {
//                    mCellsView.setVisibility(View.VISIBLE);
//                    mRlCellContent.setVisibility(View.GONE);
//                }
//
//            } else {
//                mCellsView.setVisibility(View.VISIBLE);
//                mRlCellContent.setVisibility(View.GONE);
//            }
//        }
//
//
//        @Override
//        protected void drawCellDifBG(LineParams lineData) {
//            mCellsView.setBackgroundColor(!lineData.isSameData() ? difBgColor : Color.TRANSPARENT);
//            itemView.setMinimumHeight(lineData.minHeight);
//        }
//
//    }
//
//    private static class LineChoosePacViewHolder extends LineViewHolder<LineChoosePac> {
//        private TextView linePacDesc;
//
//        public LineChoosePacViewHolder(Context context, View lineView) {
//            super(context, lineView);
//            linePacDesc = (TextView) itemView.findViewById(R.id.line_pac_desc);
//        }
//
//        @Override
//        public void bindData(int position, LineChoosePac lineData) {
//            super.bindData(position, lineData);
//            linePacDesc.setText(lineData.desc);
//        }
//
//        @Override
//        protected void drawCellDifBG(LineChoosePac lineData) {
//
//        }
//    }
//}
