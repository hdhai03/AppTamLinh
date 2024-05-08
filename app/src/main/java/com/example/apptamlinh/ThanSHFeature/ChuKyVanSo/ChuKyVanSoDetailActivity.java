package com.example.apptamlinh.ThanSHFeature.ChuKyVanSo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.apptamlinh.R;
import com.example.apptamlinh.ThanSHFeature.ThanSoHocActivity;

import java.util.Calendar;

public class ChuKyVanSoDetailActivity extends AppCompatActivity {
    Button btnBack;
    TextView txtNumber, txtHeader, txtDetail;

    private final String[] result = new String[]{"Năm cá nhân số 1 được xem là một con số thể hiện một sự phát triển cá nhân rất mạnh mẽ. Sức mạnh này chắc chắn sẽ thúc đẩy bạn trở nên đặc biệt và có thể phát triển được lòng tự tin của chính mình trong năm nay. Cũng trong năm nay, bạn nên bỏ đi những thói quen cũ xây dựng một lối sống khác với những đòi hỏi khắc khe hơn. Năm cá nhân là số 1 chắc chắn sẽ là năm có tác động không hề nhỏ đến những con người mang số chủ đạo là số 10. Lý do là vì mọi việc đều sẽ diễn ra vô cùng thuận lợi tới mức mà bạn dễ dàng rơi vào tình trạng thiếu cảnh giác, phần lớn là liên quan đến tài chính. Nếu năm cá nhân của bạn là số này cần phải lưu ý, không nên ngủ quên trên chiến thắng, phải tự chủ cuộc sống để có những phát triển vượt bậc cả về vật chất lẫn tinh thần khi luôn nhận được sự ủng hộ và quan tâm từ những người xung quanh.",
            "Mặc dù số 2 không có được sức mạnh như số 1 những cũng là một con số rất ấn tượng, những người có năm cá nhân rơi vào số này sẽ luôn có được sự bình an. Đây cũng chính là thời điểm phát triển về yếu tố tâm linh rõ rệt vì chính những người mang số 2 trong năm nay đã cảm nhận được cuộc sống một cách rất tinh tế. Đây là thời điểm vàng để bạn ngồi lại và kiểm soát cảm xúc cá nhân của bản thân, nâng cao nhân thức về tâm linh. Những người có năm cá nhân là số 2 sẽ có sức ảnh hướng lớn lên những người có số chủ đạo là số 2 và số 11.",
            "Khi bước vào năm cá nhân số 3 trong chu kỳ 9 năm thần số học, bạn nên mở rộng tầm mắt và trí não của mình để phát triển bản thân lên một tầm cao mới. Đồng thời, đây cũng là thời điểm tuyệt vời để bạn thực hiện việc truyền cảm hứng cho những người xung quanh. Năm cá nhân số 3 là năm bạn nên cân bằng lại cuộc sống, dành thời gian cho những thú vui của bản thân hoặc để tụ tập bạn bè. Ngoài ra, bạn có thể tham gia một khóa học hoặc đến một vùng đất mới. Tuy nhiên, bạn cần lưu ý rằng đây là thời điểm nhạy cảm vì bạn sẽ thường xuyên phải đối mặt với những tổn thương và nỗi nghi ngờ luôn luôn xuất hiện.   Năm cá nhân số 3 của chu kỳ 9 năm thần số học sẽ có tác động và ảnh hưởng khá mạnh mẽ đến những người có số chủ đạo 3 và 33/6. ",
            "Trong chu kỳ 9 năm thần số học, năm số 4 là điểm trũng đầu tiên. Trong thời gian này bạn cần nghỉ ngơi và duy trì sự ổn định sẽ là điều kiện tuyệt vời giúp bạn củng cố năng lượng cho 5 năm tiếp theo. Các yếu tố như sức khỏe, thể chất và tài chính được xem là chạm đáy trong năm cá nhân số 4. Năm số 4 được xem là năm để bạn nghiêm túc trau dồi những kỹ năng và củng cố nội lực để tạo nền tảng vững chắc cho các mối quan hệ, công việc, sức khỏe hoặc gia đình. Đồng thời, bạn đừng quên chú trọng tới các mối quan hệ xung quanh bởi không ai có thể tồn tại khi chỉ sống cho riêng mình. ",
            "Năm cá nhân số 5 trong chu kỳ 9 năm thần số học sẽ thể hiện sự tự do và phiêu lưu. Đây là thời điểm bạn bắt đầu sự bùng nổ sau một thời gian đã ghìm mình. Năm nay năng lượng tự do được kích hoạt rõ nét nhất nên bạn sẽ có nhiều trải nghiệm mới. Năm số 5 sẽ có nhiều cuộc vui diễn ra, tuy nhiên mục đích cuối cùng là để bạn rèn luyện tính kỷ luật. Bạn không nên quá chìm đắm trong những cuộc vui cùng bạn bè mà phải đặt ra nguyên tắc, khuôn khổ nhất định cho bản thân. Khi bước vào năm cá nhân số 5, bạn không nên lựa chọn những cam kết lâu dài bởi còn rất nhiều thứ thú vị để bạn trải nghiệm. Đây là năm không có kế hoạch, không có gì chắc chắn và luôn có cảm giác hồi hộp nhất định. Trong chu kỳ 9 năm thần số học, năm số 5 mang đến nhiều cơ hội để bạn đối mặt với nỗi sợ của bản thân. Đồng thời, điều đó giúp bạn loại bỏ những giới hạn, khơi dậy cảm giác phiêu lưu và bước ra khỏi vùng an toàn để chinh phục những chân trời mới.  ",
            "Khi bước vào năm số 6 trong chu kỳ 9 năm thần số học, bạn cần tập trung vào năng lượng tình yêu thương. Hãy dành thời gian để quan tâm, chăm sóc gia đình và các mối quan hệ cá nhân nhiều hơn. Đây là thời điểm bạn quay về thể hiện tình cảm với những người thân yêu, học cách cân bằng giữa sự nghiệp và trách nhiệm trong gia đình. Đồng thời, năm cá nhân số 6 cũng là thời điểm lý tưởng để những người đang độc thân tìm kiếm sự lãng mạn vì tình yêu sẽ sớm đến với bạn. Bạn hãy thực hiện một cam kết với đối tác của mình, chẳng hạn như kết hôn hoặc sinh con. ",
            "Giống như năm số 4, năm cá nhân số 7 là điểm trũng thứ hai trong chu kỳ 9 năm thần số học. Đây là giai đoạn gần kết thúc một chu kỳ của đời người. Vì vậy, thời gian này bạn không thể tập trung vào sự nghiệp hay những thành tựu về vấn đề tài chính. Năm cá nhân số 7 là thời điểm thích hợp để bạn phát triển tâm linh và chiêm nghiệm cuộc sống. Có thể, bạn sẽ cảm thấy đây là thời gian mà sự tin tưởng và niềm tin của mình đang được thử thách. Vào năm cá nhân số 7, để hạn chế tối đa những mất mát, bạn hãy học cách phát triển bản thân. Không nên hướng ra ngoài và có những thay đổi quá lớn, vì như vậy bạn sẽ phải đối mặt với nhiều điều bất lợi. Bên cạnh đó, bạn hãy chuẩn bị tâm lý để đối mặt với sự buông bỏ và mất mát trong năm số 7 trong chu kỳ 9 năm thần số học. Sức khỏe và tình cảm là những vấn đề có thể ảnh hưởng sâu sắc đến bạn trong năm này. Đồng thời, giai đoạn này sẽ đặc biệt tác động đến những người mang số chủ đạo 7 nên bạn cần lưu ý. ",
            "Năm số 8 trong chu kỳ 9 năm thần số học là thời điểm để bạn bứt phá. Đây là năm mang lại cơ hội cho những người mong muốn gặt hái thành quả trong 7 năm đã qua. Sau khoảng thời gian phát triển bản thân, đối mặt với những thách thức thì đã đến lúc bạn hái những quả ngọt. Năm cá nhân số 8 rất thuận lợi để bạn phát triển các hoạt động kinh doanh cũng như tài chính cá nhân của bạn. Năm nay có thể bạn sẽ đạt được đỉnh cao của sự nghiệp với những lời mời gọi và cơ hội hấp dẫn. Có thể nói, đây là năm bù đắp cho những khó khăn mà bạn đã trải qua. ",
            "Năm cá nhân số 9 là năm kết thúc chu kỳ 9 năm thần số học và mở ra một chu kỳ mới. Thời điểm này bạn có thể đưa ra một quyết định nào đó liên quan đến công việc hoặc các mối quan hệ. Đồng thời, bạn cũng phải đối mặt với một số thay đổi như chuyển nhà, chuyển môi trường sống, môi trường làm việc,…Tuy nhiên, bạn không cần phải lo lắng bởi không phải kết thúc nào cũng buồn. Đôi khi việc kết thúc một điều gì đó sẽ là cơ hội để bạn hướng tới những điều mới mẻ và tốt đẹp hơn. Bạn hãy giữ cho mình tâm thế đón nhận tất cả vì mọi thứ đến với mình đều có lý do. Năm số 9 trong chu kỳ 9 năm thần số học là năm của sự hoàn tất mọi vấn đề. Đây là khoảng thời gian để bạn nhìn lại và thấu hiểu rằng mọi thứ đều có điểm bắt đầu và kết thúc. Và khi mọi thứ kết thúc thì sẽ bắt đầu một chu kỳ mới. Bạn không nên níu giữ những thứ không còn phù hợp, hãy để nó qua đi. Đón nhận chu kỳ mới và biết ơn những cơ hội là điều bạn nên làm. "
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chu_ky_van_so_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtNumber = findViewById(R.id.txtNumber);
        txtHeader = findViewById(R.id.txtHeader);
        txtDetail = findViewById(R.id.txtDetail);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChuKyVanSoDetailActivity.this, ThanSoHocActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        Bundle extras = getIntent().getExtras();
        String dataDate = extras.getString("dataDate");
        String number = sumDigitsToOne(dataDate);
        txtNumber.setText(number);
        Calendar calendar = Calendar.getInstance();
        int iYear = calendar.get(Calendar.YEAR);
        String sHeader = "Số chủ đạo của bạn trong năm " + iYear + " là con số " + number;
        txtHeader.setText(sHeader);

        int iID = Integer.valueOf(number) - 1;
        txtDetail.setText(result[iID]);

    }

    public static String sumDigitsToOne(String str) {
        while (str.length() > 1) {
            int sum = 0;
            for (int i = 0; i < str.length(); i++) {
                sum += Character.getNumericValue(str.charAt(i));
            }
            str = Integer.toString(sum);
        }
        return str;
    }
}