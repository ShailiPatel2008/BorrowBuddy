package com.borrowbuddy.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.borrowbuddy.app.R;
import com.borrowbuddy.app.models.RequestModel;

import java.util.List;


public class RequestStatusAdapter extends RecyclerView.Adapter<RequestStatusAdapter.ViewHolder> {


    private Context context;
    private List<RequestModel> requestList;


    public RequestStatusAdapter(Context context, List<RequestModel> requestList) {

        this.context = context;
        this.requestList = requestList;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_request_status, parent, false);


        return new ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        RequestModel request = requestList.get(position);


        holder.txtItemName.setText(request.getItemName());

        holder.txtCustomerName.setText(
                "Requested by: " + request.getCustomerName()
        );


        holder.txtCustomerMobile.setText(
                "Mobile: " + request.getCustomerMobile()
        );


        holder.txtRentalPeriod.setText(
                "Rental Period: " + request.getRentalPeriod()
        );


        holder.txtRent.setText(
                "Rent: ₹" + request.getRent() + "/day"
        );


        holder.txtStatus.setText(
                "Status: " + request.getStatus()
        );


        // Temporary image
        holder.imgItem.setImageResource(
                android.R.drawable.ic_menu_gallery
        );


        holder.btnAccept.setOnClickListener(v -> {


            holder.txtStatus.setText("Status: Approved");


            Toast.makeText(
                    context,
                    "Request Accepted",
                    Toast.LENGTH_SHORT
            ).show();


        });



        holder.btnReject.setOnClickListener(v -> {


            holder.txtStatus.setText("Status: Rejected");


            Toast.makeText(
                    context,
                    "Request Rejected",
                    Toast.LENGTH_SHORT
            ).show();


        });


    }


    @Override
    public int getItemCount() {

        return requestList.size();

    }



    public static class ViewHolder extends RecyclerView.ViewHolder {


        ImageView imgItem;


        TextView txtItemName,
                txtCustomerName,
                txtCustomerMobile,
                txtRentalPeriod,
                txtRent,
                txtStatus;


        Button btnAccept,
                btnReject;



        public ViewHolder(@NonNull View itemView) {

            super(itemView);


            imgItem = itemView.findViewById(R.id.imgRequestItem);


            txtItemName = itemView.findViewById(R.id.txtRequestItemName);

            txtCustomerName = itemView.findViewById(R.id.txtCustomerName);

            txtCustomerMobile = itemView.findViewById(R.id.txtCustomerMobile);

            txtRentalPeriod = itemView.findViewById(R.id.txtRentalPeriod);

            txtRent = itemView.findViewById(R.id.txtRequestRent);

            txtStatus = itemView.findViewById(R.id.txtRequestStatus);



            btnAccept = itemView.findViewById(R.id.btnAccept);

            btnReject = itemView.findViewById(R.id.btnReject);


        }
    }
}