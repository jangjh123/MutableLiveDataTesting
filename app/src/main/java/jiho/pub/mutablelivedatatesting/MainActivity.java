package jiho.pub.mutablelivedatatesting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MutableLiveData<Pack> m_data = new MutableLiveData<>();
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (m_data.getValue() != null) {
            Log.d("MutableLiveData", String.valueOf(m_data.getValue().getId()));
            Log.d("MutableLiveData", m_data.getValue().getName());
        }

        Pack pack = new Pack(1, "json");

        Log.d("Data", String.valueOf(pack.getId()));
        Log.d("Data", pack.getName());

        // 콜백은 메소드. 리스너는 콜백 메소드를 가진 인터페이스. 즉,
        // public interface CallbackListener { = 리스너. 리스너는 콜백메소드의 결과를 핸들러로 전달하는 역할
        //     public void onSetSomething(); = 콜백 메소드

        // 콜백이란? 피호출 메소드가 호출 메소드를 호출하는 것. 여기서는 m_data.observe (호출자) 에 new Observer<Pack> (피호출자) 이 인자.
        // m_data.observe 를 호출한 뒤, Observer<Pack> 에 구현된 이벤트 리스너가 이벤트를 받아들이고 observe 를 역으로 호출, observe 는 onChanged 를 implements 하여 호출.
        m_data.observe(MainActivity.this, new Observer<Pack>() { // new Observer<Pack> 는 인터페이스. onChanged 는 피호출자
            @Override
            public void onChanged(Pack pack) { // onChanged = 콜백 메소드
                Log.d("ChangedMutableLiveData", String.valueOf(m_data.getValue().getId()));
                Log.d("ChangedMutableLiveData", m_data.getValue().getName());
                myIf(pack.getId());


            }
        });

        m_data.setValue(pack); // setValue 든 postValue 든, 애초에 값 자체를 MutableLiveData "<Pack>" 으로 받는다. 값 하나만 따로 바꿀 순 없고,
        // 값을 바꾸는 행위에는 setValue 나 postValue 메소드 사용이 필요하기 때문에 무조건 onChanged 콜백을 호출하게 되어있다.

        textView = findViewById(R.id.textView);
        Pack pack1 = new Pack(21, "jiho");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (pack1.getId() == 1) {
                   pack1.setId(21);
                   m_data.setValue(pack1);
               } else if (pack1.getId() == 21) {
                   pack1.setId(30);
                   m_data.setValue(pack1);
               }
            }
        });
    }
    public void myIf(int age) {
        switch (age) {
            case 1:
                break;
            case 21:
                Toast.makeText(this, "21", Toast.LENGTH_SHORT).show();
                break;
            case 30:
                Toast.makeText(this, "30", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}