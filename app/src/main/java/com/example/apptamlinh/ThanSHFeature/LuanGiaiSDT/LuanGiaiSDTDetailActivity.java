package com.example.apptamlinh.ThanSHFeature.LuanGiaiSDT;

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

public class LuanGiaiSDTDetailActivity extends AppCompatActivity {
    Button btnBack;
    TextView txtNumber, txtHeader, txtDetail;


    private final String[] result = new String[]{"Trong Thần số học, số 1 biểu tượng của sự tự tin, quyền lực và hành động. Con số đại diện này cho thấy bạn là người biết nhìn vào hoàn cảnh hiện tại của mình và nhận ra rằng bản thân có khả năng biến ước mơ thành hiện thực. Ngay cả khi đối diện khó khăn, bạn cũng sẽ tìm lại được bình tĩnh, khả năng kiểm soát và bắt đầu những khởi đầu mới. Trong thời gian tới cũng như cuộc đời sau này, bạn không chờ cơ hội đến mà tự thân mình vận động để tìm mọi cơ hội lấp đầy con đường thành công. Bạn có khả năng làm việc độc lập tốt và bạn nghĩ rằng làm việc một mình giúp bản thân linh hoạt và hiệu quả hơn. Bạn sẽ cần đến sự tự tin và tinh thần sáng tạo bất tận của mình để gặt hai được nhiều thành công phía trước. Bạn không ngại đổi mới, nên những biến động xung quanh cuộc sống có thể là cơ hội giúp bạn tìm được quả ngọt từ khó khăn. Bài học của bạn là trở nên thực sự tự tin vào khả năng của mình và thực hành tin tưởng người khác."
            , "Số 2 là con số phù hợp cho những ai đang mong muốn tìm kiếm một nửa yêu thương. Số điện thoại này tượng trưng cho sự tốt bụng, khéo léo và khả năng giao thiệp với mọi người. Nếu bạn đang làm kinh doanh, buôn bán, con số này phù trợ cho bạn rất tốt. Ngoài ra, số 2 tượng trưng cho những người có ảnh hưởng thầm lặng nhưng mạnh mẽ. Những người mang số 2 có tính cách bình tĩnh, trực giác nhạy bén và dễ nhận biết được nỗi sợ hãi của đối phương. Khi bạn sở hữu mật mã số 2, dãy số điện thoại này sẽ hỗ trợ cho bạn khả năng hợp tác và làm việc tập thể. Bên cạnh đó, bạn dễ bị phân vân giữa hai quyết định và không quyết đoán. Điều này sẽ làm ảnh hưởng đến khả năng thành công của bạn trong mọi chuyện.",
            "Dãy số điện thoại mang mật mã 3 thể hiện chủ nhân sở hữu là người sáng tạo và thú vị, cá tính. Nếu bạn là người trẻ tuổi, số điện thoại này rất phù hợp để bạn thực hiện ước mơ của mình. Trái tim và tâm hồn vô tư, người mang số 3 không bao giờ bỏ lỡ cơ hội để vui chơi, gắn kết với những người khác. Phong thái vui vẻ và khả năng giao tiếp tốt của người số 3 kết hợp với nhau để tạo nên một sức hút tự nhiên thu hút sự chú ý của người khác. Thậm chí không cần cố gắng, người mang số 3 cũng luôn hấp dẫn trong mắt mọi người. Số 3 là con số truyền cảm hứng, mang đến sự độc đáo và danh tiếng. Tuy nhiên, nếu bạn có tham vọng và luôn quyết tâm cạnh tranh để đạt được mục tiêu, đây không phải là con số dành cho bạn.",
            "Số 4 là con số tượng trưng cho sự ổn định và đáng tin cậy. Nếu bạn đang làm việc liên quan đến các ngành nghề ngân hàng, luật,... và những công việc cần tạo dựng uy tín thì con số này rất phù hợp. Dãy số điện thoại có tổng 4 tượng trưng cho người có cái nhìn thực tế về cuộc sống và không dễ bị lung lay về ý kiến khách quan của người khác. Ngoài ra, người sở hữu mật mã này là người đáng tin cậy và làm việc chăm chỉ để thể hiện uy tin của mình. Mặc dù vậy, sự bảo thủ và kiên định với niềm tin của mình thái quá sẽ tạo ra rào cản khi bạn muốn tiến gần đến thành công. Thật không may, lối suy nghĩ giáo điều của bạn không cho các suy nghĩ mới mẻ tiếp cận cuộc sống của bạn và khiến giới hạn bản thân bạn bị bó hẹp. Bài học giúp bạn thành công hơn trên đường đời là nới lỏng tâm trí để tận hưởng cuộc sống và cho phép những điều thú vị có thể xuất hiện làm mới mẻ cuộc sống của mình.",
            "Con số 5 cho thấy cuộc sống của người sở hữu số điện thoại này có nhiều cuộc phiêu lưu, sự thay đổi và thiếu ổn định. Nhiều chuyện bất ngờ có thể xảy ra trong cuộc sống của họ. Số điện thoại này thích hợp với những người ưa trải nghiệm khám phá và mong muốn có một cuộc sống tự do, đam mê xê dịch. Người sở hữu số điện thoại này có tinh thần học hỏi cao, khả năng thích nghi tốt và biết cách tương tác với người khác để đạt được mục tiêu của mình. Nhưng đây không phải là số điện thoại dành cho những người tâm lý yếu và phải gồng gánh nhiều trách nhiệm.",
            "Nếu bạn là người mong muốn trở thành trụ cột của gia đình hoặc là người quan trọng nắm giữ hạnh phúc hôn nhân thì số điện thoại này rất phù hợp với bạn. Trong Thần số học, số 6 biểu trưng cho sự ấm áp, quan tâm và bảo vệ. Vì vậy, con số này có thể mang lại may mắn cho bạn trong việc vun đắp tình cảm gia đình và tình bạn tri kỷ. Đây cũng là con số phù hợp với người làm kinh doanh.",
            "Số 7 là số luôn đi tìm kiếm câu trả lời. Số 7 không phải là con số tuyệt vời cho khả năng hiển thị và kết nối. Nếu công việc của bạn là một nhà nghiên cứu, học thuật, nhà văn hoặc nhà phân tích dữ liệu, con số 7 là con số lý tưởng dành cho bạn. Nếu không, điện thoại của bạn sẽ liên tục đổ chuông và nhiều người sẽ tìm đến bạn để đặt ra câu hỏi và tìm kiếm câu trả lời từ bạn. Bởi những người gọi đến biết rằng bạn có câu trả lời mà họ đang tìm kiếm.",
            "Số 8 mang năng lượng mạnh mẽ, đặc biệt là đối với những người làm kinh doanh. Số 8 gắn liền với sự thịnh vượng và dồi dào. Nó thu hút tài vận nhưng cũng mang đến những thách thức lớn. Những người có số điện thoại là 88 thường nhận được nhiều lời đề nghị và cơ hội về việc làm cũng như tài vận. Tuy nhiên, số 8 cũng ám chỉ những gì bạn gặt hái được chính là những gì bạn đem gieo, vì vậy, hãy làm việc một cách khôn ngoan, trung thực và khéo léo. Đây là con số hoàn hảo dành cho doanh nhân, giám đốc điều hành, chính trị gia, chủ ngân hàng, môi giới và thương nhân.",
            "Số 9 tượng trưng cho lòng trắc ẩn và những lý tưởng. Nếu bạn làm việc liên quan đến tổ chức xã hội, cộng đồng, y tế, từ thiện, bạn nên tiếp tục sử dụng con số này. Đây là một trong những con số mang đến năng lượng hạnh phúc và may mắn, an lành cho chủ sở hữu. Tuy nhiên, nó không phù hợp với những người đang cần cải thiện tình hình tài chính. Con số này tượng trưng cho tấm lòng nhân đạo. Đó là lòng nhân ái, tốt bụng và có ý định nỗ lực để tạo ra điều tốt đẹp nhất. Số 9 trong Thần số học đã trải qua rất nhiều khó khăn để đạt được kết quả khôn ngoan hơn, mạnh mẽ hơn."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_luan_giai_sdtdetail);
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
                Intent intent = new Intent(LuanGiaiSDTDetailActivity.this, ThanSoHocActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        Bundle extras = getIntent().getExtras();
        String dataSDT = extras.getString("dataSDT");
        String number = sumDigitsToOne(dataSDT);
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