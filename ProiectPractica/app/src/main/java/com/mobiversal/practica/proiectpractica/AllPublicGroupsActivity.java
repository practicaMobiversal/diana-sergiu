//package com.mobiversal.practica.proiectpractica;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.Toolbar;
//import android.view.View;
//import android.widget.TextView;
//
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//
//public class AllPublicGroupsActivity extends AppCompatActivity {
//
//    private Toolbar mToolbar;
//    private RecyclerView mGroupsList;
//    private DatabaseReference mPublicGroupsDatabase;
//    private RecyclerView.Adapter adapter;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_grup);
//
//        mPublicGroupsDatabase = FirebaseDatabase.getInstance().getReference().child("Groups");
//        mGroupsList = (RecyclerView) findViewById(R.id.my_recycler_view);
//        mGroupsList.setHasFixedSize(true);
//        mGroupsList.setLayoutManager(new LinearLayoutManager(this));
//
//
//
//    }
//
//    @Override
//   // protected void onStart() {
//        super.onStart();
//        FirebaseRecyclerAdapter<PublicGroup, GroupsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PublicGroup, GroupsViewHolder>(
//
//                PublicGroup.class,
//                R.layout.group_list_row,
//                GroupsViewHolder.class,
//                mPublicGroupsDatabase
//
//        ) {
//            @Override
//            protected void populateViewHolder(GroupsViewHolder viewHolder, PublicGroup model, int position) {
//
//               // GroupsViewHolder gwh = new GroupsViewHolder();
//
//                viewHolder.setName(model.getPublicGroupName());
//
//            }
//        };
//
//        mGroupsList.setAdapter(firebaseRecyclerAdapter);
//
//    }
//
//    //public static class GroupsViewHolder extends RecyclerView.ViewHolder {
//
//        View mView;
//
//        public GroupsViewHolder(View itemView){
//            super(itemView);
//
//            mView = itemView;
//        }
//        public void setName(String publicGroupName){
//
//                TextView groupNameView = (TextView) mView.findViewById(R.id.title);
//                groupNameView.setText(publicGroupName);
//        }
//
//    }
//
//
//}
