package com.minor2k17.nimble;

/**
 * Created by shaur on 04-11-2017.
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * RecyclerView.Adapter
 * Recycler.ViewHolder
 */
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {


    private Context mCtx;
    private List<Books_getter_function> bookList;

    public BookAdapter(Context mCtx, List<Books_getter_function> bookList) {
        this.mCtx = mCtx;
        this.bookList = bookList;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card_view_layout,null);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        Books_getter_function book = bookList.get(position);

        holder.mbook_name.setText(book.getBook_name());
        holder.mbook_price.setText(String.valueOf(book.getBook_price()));
        holder.mbook_contact.setText(String.valueOf(book.getContact_number()));
        holder.mbook_email.setText(book.getemail());
        holder.mbook_desc.setText(book.getDescription());

        holder.mbook_image.setImageDrawable(mCtx.getResources().getDrawable(book.getImageId(),null));
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder{

        ImageView mbook_image;
        TextView mbook_name,mbook_price,mbook_contact,mbook_email,mbook_desc;

        public BookViewHolder(View itemView) {
            super(itemView);

            mbook_image = itemView.findViewById(R.id.book_image);
            mbook_name = itemView.findViewById(R.id.book_name);
            mbook_price = itemView.findViewById(R.id.book_price);
            mbook_email = itemView.findViewById(R.id.book_contact_email);
            mbook_contact = itemView.findViewById(R.id.book_contact_number);
            mbook_desc = itemView.findViewById(R.id.book_description_display);




        }
    }

}
