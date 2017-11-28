package com.minor2k17.nimble;

import android.app.DownloadManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import static android.content.Context.DOWNLOAD_SERVICE;

/**
 * Created by Hopeless on 13-Nov-17.
 */

public class StorageAdapter extends RecyclerView.Adapter<StorageAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private List<Upload> uploads;
    private String link="", name="";

    public StorageAdapter(Context context, List<Upload> uploads) {
        this.uploads = uploads;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_files, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Upload upload = uploads.get(position);
        name = upload.getName();
        String prefix = name.substring(0, 3);
        link = upload.getUrl();
        holder.textViewName.setText(name.substring(4));
        if (prefix.equals("img")) {
            Glide.with(context).load(upload.getUrl()).into(holder.imageView);
        } else if (prefix.equals("pdf")) {
            Glide.with(context).load(R.drawable.pdficon).into(holder.imageView);
        } else if (prefix.equals("txt")) {
            Glide.with(context).load(R.drawable.texticon).into(holder.imageView);
        }
        holder.downloadFile.setOnClickListener(StorageAdapter.this);
    }

    @Override
    public int getItemCount() {
        return uploads.size();
    }

    @Override
    public void onClick(View view) {
            new DownloadTask(view.getContext(), name, link);
    }

    /*//Check if internet is present or not
    private boolean isConnectingToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager
                .getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }*/

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;
        public ImageView imageView;
        public FloatingActionButton downloadFile;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.textView4);
            imageView = (ImageView) itemView.findViewById(R.id.imageView4);
            downloadFile = (FloatingActionButton) itemView.findViewById(R.id.downloadButton);
        }
    }
}
