package com.openclassrooms.mareu.ui.meeting_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.openclassrooms.mareu.R;
import com.openclassrooms.mareu.model.Meeting;

import java.text.SimpleDateFormat;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Recycler View Adapter for the list of meetings
 */
public class ListMeetingAdapter extends RecyclerView.Adapter<ListMeetingAdapter.ViewHolder> {

    private Context context;
    private List<Meeting> meetings;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("H");

    public ListMeetingAdapter(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(context == null) { context = parent.getContext(); }
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_meeting, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meeting meeting = meetings.get(position);

        String meetingTitle = context.getString(R.string.meeting_title_text,
                meeting.getSubject(), Integer.valueOf(simpleDateFormat.format(meeting.getSlot().getStartTime()))
                , meeting.getPlace().getName());

        holder.meetingTitle.setText(meetingTitle);

        StringBuilder participantText = new StringBuilder();

        for(int i = 0; i < meeting.getParticipants().size(); i++) {
            if(i != meeting.getParticipants().size() - 1) {
               participantText.append(meeting.getParticipants().get(i).getEmail() + ", ");
            } else {
                participantText.append(meeting.getParticipants().get(i).getEmail());
            }
        }
        holder.participantMails.setText(participantText.toString());
    }

    @Override
    public int getItemCount() {
        return meetings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.meeting_title)
        public TextView meetingTitle;
        @BindView(R.id.participantMails)
        public TextView participantMails;
        @BindView(R.id.delete_button)
        public ImageView deleteButton;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
