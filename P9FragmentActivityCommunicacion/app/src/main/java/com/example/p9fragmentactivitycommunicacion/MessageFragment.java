package com.example.p9fragmentactivitycommunicacion;


import android.app.Activity;
import android.content.Context;
import android.location.OnNmeaMessageListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {
    private EditText editText;
    private Button button;

    OnMessageReadListener messageReadListener;

    public interface OnMessageReadListener{
        public void onMessageRead(String message);
    }

    public MessageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        editText = view.findViewById(R.id.editText);
        button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editText.getText().toString();
                messageReadListener.onMessageRead(message);
            }
        });
        return view;

        //es lo mismo a return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        Activity activity=(Activity)context;
        try{
            messageReadListener=(OnMessageReadListener)activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString()+
                    " debes de sobreescribir on MessageRead...");
        }
    }
}
