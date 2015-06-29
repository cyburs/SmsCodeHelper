package me.drakeet.inmessage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import me.drakeet.inmessage.R;
import me.drakeet.inmessage.api.OnItemClickListener;
import me.drakeet.inmessage.model.Message;
import me.drakeet.inmessage.utils.StringUtils;

/**
 * Created by shengkun on 15/6/5.
 */
public class MainMessageAdapter extends RecyclerView.Adapter<MainMessageAdapter.ViewHolder> {

    public enum ITEM_TYPE {
        ITEM_TYPE_DATE,
        ITEM_TYPE_MESSAGE
    }

    private List<Message> mList;
    private Boolean mShowResult = false;

    private OnItemClickListener listener;


    public MainMessageAdapter(List<Message> messageList) {
        mList = messageList;
    }

    @Override
    public MainMessageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        if (viewType == ITEM_TYPE.ITEM_TYPE_DATE.ordinal()) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_separation, parent, false);
            viewHolder = new ViewHolder(v);
            viewHolder.date = (TextView) v.findViewById(R.id.date_message_tv);
        }
        if (viewType == ITEM_TYPE.ITEM_TYPE_MESSAGE.ordinal()) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
            viewHolder = new ViewHolder(v);
            viewHolder.author = (TextView) v.findViewById(R.id.author_message_tv);
            viewHolder.content = (TextView) v.findViewById(R.id.content_message_tv);
            viewHolder.avatar = (TextView) v.findViewById(R.id.avatar_tv);
            viewHolder.date = (TextView) v.findViewById(R.id.message_date_tv);
            viewHolder.item = (FrameLayout) v.findViewById(R.id.item_message);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MainMessageAdapter.ViewHolder holder, final int position) {
        if (holder.getItemViewType() == ITEM_TYPE.ITEM_TYPE_MESSAGE.ordinal()) {
            holder.author.setText(mList.get(position).getSender());
            if(mShowResult && mList.get(position).getResultContent() != null) {
                holder.content.setText(mList.get(position).getResultContent());
            }
            else {
                holder.content.setText(mList.get(position).getContent());
            }
            if (mList.get(position).getReceiveDate() != null) {
                holder.date.setText(mList.get(position).getReceiveDate());
            }
            holder.author.setText(mList.get(position).getSender());
            if(mList.get(position).getCompanyName() != null) {
                String showCompanyName = mList.get(position).getCompanyName();
                // 中文四个字的名字特别换行
                if(StringUtils.isContainsChinese(showCompanyName) && showCompanyName.length() == 4) {
                    String fourCharsName = "";
                    for(int u = 0; u < showCompanyName.length();u ++) {
                        if(u == 2) {
                            fourCharsName += "\n";
                        }
                        fourCharsName += showCompanyName.charAt(u);
                    }
                    showCompanyName = fourCharsName;
                }
                holder.avatar.setText(showCompanyName);
            }
            else {
                holder.avatar.setText("?");
            }
            if (listener != null) {
                holder.item.setOnClickListener(
                        new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                listener.onItemClick(v, position);
                            }
                        }
                );
            }
        }
        else {
            holder.date.setText(mList.get(position).getReceiveDate());
        }
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getIsMessage() ? ITEM_TYPE.ITEM_TYPE_MESSAGE.ordinal() : ITEM_TYPE.ITEM_TYPE_DATE.ordinal();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }

        TextView author;
        TextView content;
        TextView avatar;
        TextView date;
        FrameLayout item;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setShowResult(boolean showResult) {
        this.mShowResult = showResult;
    }

}
