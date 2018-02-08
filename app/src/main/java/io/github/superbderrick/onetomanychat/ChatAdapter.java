package io.github.superbderrick.onetomanychat;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by derrick on 08/02/2018.
 */

public class ChatAdapter extends ArrayAdapter<ChatData> {
    private final SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("a h:mm", Locale.getDefault());
    private final static int TYPE_ANOTHER = 1;
    private String mMyEmail;

    public ChatAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void setEmail(String email) {
        mMyEmail = email;
    }

    private View setAnotherView(LayoutInflater inflater) {
        View convertView = inflater.inflate(R.layout.listitem_chat, null);
        ViewHolderAnother holder = new ViewHolderAnother();
        holder.bindView(convertView);
        convertView.setTag(holder);
        return convertView;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int viewType = getItemViewType(position);
        LayoutInflater inflater = LayoutInflater.from(getContext());

        if (convertView == null) {
            convertView = setAnotherView(inflater);
        }
        convertView = setAnotherView(inflater);
        ((ViewHolderAnother) convertView.getTag()).setData(position);

        return convertView;
    }


    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_ANOTHER;
    }

    private class ViewHolderAnother {
        private ImageView mImgProfile;
        private TextView mTxtUserName;
        private TextView mTxtMessage;
        private TextView mTxtTime;

        private void bindView(View convertView) {
            mImgProfile = (ImageView) convertView.findViewById(R.id.img_profile);
            mTxtUserName = (TextView) convertView.findViewById(R.id.txt_userName);
            mTxtMessage = (TextView) convertView.findViewById(R.id.txt_message);
            mTxtTime = (TextView) convertView.findViewById(R.id.txt_time);
        }

        private void setData(int position) {
            ChatData chatData = getItem(position);
            Picasso.with(getContext()).load(chatData.userPhotoUrl).into(mImgProfile);
            mTxtUserName.setText(chatData.userName);
            mTxtMessage.setText(chatData.message);
            mTxtTime.setText(mSimpleDateFormat.format(chatData.time));
        }
    }

}
