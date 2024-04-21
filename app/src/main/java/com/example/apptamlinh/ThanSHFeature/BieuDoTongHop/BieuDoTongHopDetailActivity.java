package com.example.apptamlinh.ThanSHFeature.BieuDoTongHop;

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

public class BieuDoTongHopDetailActivity extends AppCompatActivity {
    private Button btnBack;
    TextView[] txtViews = new TextView[9];
    TextView[] txtDetails = new TextView[9];
    TextView[] txtHeaders = new TextView[9];

    private final String[] arrBD = {
            "Con số 1 trong biểu đồ tổng hợp là con số thể hiện khả năng trình bày, bộc lộ cảm xúc và diễn đạt nội tâm của bản thân cho người khác. Do đó, nếu thiếu con số này, bạn sẽ bị mất khả năng diễn đạt cảm xúc bản thân, khó khăn trong việc truyền tải những gì nội tâm mong muốn đến mọi người. Để có thể bổ sung con số này, bạn cần luyện tập khả năng diễn đạt và thể hiện bản thân một cách rõ ràng, sâu sắc để người khác có thể nhận biết. Bạn có thể luyện tập bằng cách sáng tác, tham gia các cuộc tranh luận, rèn luyện tư duy phản biện và nâng cao khả năng tập trung. Thường xuyên tham gia các hoạt động cộng đồng, xã hội cũng là một trong những cách thôi thúc con người phát triển, được phép tự do diễn đạt, bộc lộ cảm xúc và để tự tin nói lên những quan điểm, chính kiến của mình. Những kỹ năng trên sẽ giúp bạn bổ sung được sự thiếu vắng của con số một và nâng cao khả năng kết nối, khả năng tư duy của mình.",
            "Trong biểu đồ tổng hợp thần số học, số 2 là con số đại diện cho khả năng trực giác khả năng khám phá, thấu cảm, tính cách tinh tế của con người. Do đó, nếu trong biểu đồ bạn thiếu đi con số này, bạn sẽ trở nên kém tinh tế, chu toàn và trực giác kém nhạy bén. Để có thể bổ sung những đặc điểm bị trống do thiếu số 2, bạn cần phải học tập và nâng cao độ nhạy cảm của bản thân. Thông qua việc lắng nghe câu chuyện, cảm xúc, thấu hiểu bản thân và cả những người khác, bình tĩnh và tập trung quan sát kỹ càng các vấn đề có thể là một phương pháp hiệu quả để bạn có thể giúp trực giác trở nên nhạy vén hơn. Bên cạnh đó, học cách tập trung xử lý công việc và thực hành các bài tập tư duy tăng khả năng nhạy bén cũng là một trong những cách hỗ trợ bạn bổ sung số thiếu 2 trong biểu đồ tổng hợp thần số học.",
            "Con số 3 là con số đại diện cho khả năng xử lý tình huống, cho trí nhớ nhạy vén và những thế giới tưởng tượng bên trong con người. Nếu biểu đồ tổng hợp thần số học của bạn thiếu đi con số này thì khả năng hoạt động trí não chưa phát triển mạnh, trí nhớ không tốt và thường có xu hướng lười biếng. Trong biểu đồ tổng hợp nếu trống con số 3, bạn có thể tập trung nghiên cứu và khám phá vào một lĩnh vực cụ thể mà bản thân yêu thích để nâng cao năng lực ghi nhớ và phát triển về khả năng tư duy, củng cố ghi nhớ kiến thức. Bên cạnh đó, kết hợp với chế độ làm việc, nghỉ ngơi đều đặn để đảm bảo có một sức khỏe tốt cũng góp phần tạo sự cân bằng cả về trí lực lẫn thể lực.",
            "Con số 4 là con số nằm ở trục thể chất và là đại diện cho khả năng sắp, kỷ luật, tổ chức trong một tập hoặc trong cuộc sống. Những người có con số 4 trong biểu đồ tổng hợp thường có có thể quản trị cuộc sống và điều tiết công việc một cách rõ ràng, thích hợp và thực tế. Do đó, nếu biểu đồ của bạn thiếu đi con số này thì bạn sẽ bị trống khả năng lên kế hoạch, sắp xếp đồng thời thiếu kiên nhẫn, hay mơ mộng và khá bừa bộn. Để có thể bổ sung chỗ trống của con số 4 trong biểu đồ, bạn có thể tập cho bản thân khả năng cân bằng cảm xúc, bình tĩnh nhìn nhận và xử lý khéo léo các vấn đề gặp phải. Mặt khác, hãy xây dựng cho bản thân những quy tắc và hình phạt riêng để bản thân có khả năng nghiêm túc thực hiện, nâng cao tính kỷ luật. Bên cạnh đó, hãy tự giác và chủ động trong mọi việc, trước khi làm hãy lên kế hoạch và tìm được lối giải quyết đúng đắn. Bạn cũng có thể tham gia những khóa học và tìm những người có cùng chí hướng để thể hiện bản thân, học tập điểm tốt thích hợp của họ.",
            "Số 5 trong biểu đồ tổng hợp thần số học là con số nằm ở vị trí trung tâm. Đây là con số khá quan trọng vì chúng đại diện cho tình yêu thương, sự tự do và khả năng diễn đạt, thể hiện bản thân. Những người sở hữu con số này trong biểu đồ ngày sinh là những người có khả năng kiểm soát và cân bằng rất tốt giữa trực giác – lý trí. Tuy nhiên, nếu số 5 xuất hiện quá nhiều trong biểu đồ tổng hợp, bạn sẽ gặp phải rất nhiều rắc rối tới từ sự ảnh hưởng về mặt cảm xúc. Nhưng nếu không có bất kỳ một số 5 nào, bạn lại thiếu đi khả năng kết nối và diễn cảm xúc một cách tự do, khiến bạn khó khắn trong việc giao tiếp với những người xung quanh. Để bổ sung chỗ trống của con số 5 trong biểu đồ tổng hợp thần số học, hãy chủ động tham gia các hoạt động xã hội, chủ động kết nối đến mọi người xung quanh và rèn luyện những kỹ năng mềm. Không ngại học hỏi và diễn đạt những ý kiến cá nhân trước đám đông để thể hiện bản thên, nêu lên tiếng nói của chính mình. ",
            "Số 6 trong biểu đồ tổng hợp là con số nằm tại trục ngang – trục về trí óc. Con số này đại diện cho sự kết hợp và cân bằng của não trái – não phải. Bên cạnh đó, được mệnh danh là con số của gia đình, số 6 sống rất có trách nhiệm và giàu tình yêu thương cho người thân. Họ là những con người lý tưởng của gia đình. Do đó, nếu thiếu đi số 6 trong biểu đồ, bạn sẽ bị kém về mặt thể hiện tình cảm gia đình, kém tư duy và thiếu tính sáng tạo khi không có khả năng cân bằng não trái – não phải. Để bổ sung vị trí của con số 6, bạn có thể tập trung và phát triển những thế mạnh mà bản thân sở hữu để nâng cao khả năng tư duy, sáng tạo. Đặc biệt là trong những lĩnh vực về nghệ thuật như biểu diễn, điêu khắc hoặc hội họa… Bên cạnh đó, hãy gắn kết và thường xuyên ở gần những người thân trong gia đình để sưởi ấm tình cảm, nâng cao sự thấu hiểu giữa các thành viên trong gia đình.",
            "Trong biểu đồ tổng hợp thần số học, con số 7 là con số biểu tượng cho sự nghiệm thực tế và những bài học được đúc kết qua kinh nghiệm mà bản thân đã trau đồi được trong hành trình cuộc sống của họ. Do đó, nếu thiếu đi con số này, bạn có thể rơi vào hai trường hợp sau đây: một là người dày dặn kinh nghiệm, đã thấu hiểu nhiều bài học trong cuộc đời; hai là người non trẻ và chưa có nhiều trải nghiệm và không chủ động để thấu hiểu được những giá trị trong cuộc sống. Để có thể bổ sung số thiếu 7, bạn cần phải tập trung và tự đưa ra những trải nghiệm thực tế, hãy chủ động để có thể khám phá những điều mới mẻ, tiếp thu những bài học trong cuộc sống của mình. Cách này có thể mất một khoảng thời gian dài tuy nhiên giúp bạn có nhiều kinh nghiệm và thấu hiểu cuộc sống hơn.",
            "Số 8 trong biểu đồ tổng hợp thần só học là con số nằm tại trục ngang tinh thần. Con số này đại biểu cho trí tuệ, tính cách độc lập và sự chủ động trong cuộc sống. Những người sở hữu một số 8 trong biểu đồ là những người tỉ mỉ, quan tâm, chu đáo, có khả năng tổ chức và sắp xếp bài bản, logic. Do đó, nếu thiếu đi con số này, bạn sẽ gặp nhiều khó khăn trong việc xây dựng cuộc sống tự lập, kém trong khả năng sáng tạo và khá hấp tấp. Để bổ sung sự thiếu vắng con số 8 trong biểu đồ tổng hợp thần số học, bạn nên chú trọng vào việc phát triển khả năng chủ động, quyết đoán, nâng cao tinh thần tự lập và chịu trách nhiệm với những gì bản thân đã lựa chọn. Thường xuyên tham gia vào các hoạt động cộng đồng, các câu lạc bộ hoặc các tổ chức để học cách tôn trọng nguyên tắc, tỉ mỉ và nâng cao trí tuệ. ",
            "Trong biểu đồ tổng hợp thần số học, số 9 là con số nằm ở đầu trục ngang của trục trí óc. Con số này đại diện cho tham vọng và những hoài bão lớn. Mặt khác, số 9 cũng thể hiện tinh thần trách nhiệm và sự lý tưởng hóa cao. Do đó, nếu thiếu đi con số này trong biểu đồ tổng hợp, bạn sẽ là người ít tham vọng, không có nhiều hoài bão để phấn đấu và thiếu đi lý tưởng sống để định hướng cho lối đi của bản thân. Để bổ sung con số 9 trong biểu đồ tổng hợp thần số học, bạn cần nỗ lực, cố gắng và hãy đặt ra cho bản thân những mục tiêu hoàn thành trước khi thực hiện một kế hoạch nào đó. Mặt khác, không ngừng trau dồi những kỹ năng lập và tổ chức kế hoạch, nâng cao khả năng giao tiếp, thấu hiểu được sức nặng của những công việc được giao từ đó để có tinh thần trách nhiệm hơn."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bieu_do_tong_hop_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        for (int i = 0; i < 9; i++) {
            int resId = getResources().getIdentifier("txtView" + (i + 1), "id", getPackageName());
            txtViews[i] = findViewById(resId);
        }

        for (int i = 0; i < 9; i++) {
            int resId = getResources().getIdentifier("txt" + (i + 1) + "Header", "id", getPackageName());
            txtHeaders[i] = findViewById(resId);
        }

        for (int i = 0; i < 9; i++) {
            int resId = getResources().getIdentifier("txt" + (i + 1) + "Detail", "id", getPackageName());
            txtDetails[i] = findViewById(resId);
        }
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Bundle extras = getIntent().getExtras();
        String dataDate = extras.getString("dataDate");
        String dataName = extras.getString("dataName");

        String[] mangChuoi = taoMangChuoi(dataDate);
        String[] mangChuoi2 = taoMangChuoi2(dataName);

        String[] chuoiFinal = new String[mangChuoi.length];
        for (int i = 0; i < mangChuoi.length; i++) {
            chuoiFinal[i] = (mangChuoi[i] != null ? mangChuoi[i] : "") + (mangChuoi2[i] != null ? mangChuoi2[i] : "");
        }

        for (int i = 0; i < txtViews.length; i++) {
            txtViews[i].setText(chuoiFinal[i]);
            if (chuoiFinal[i] != "") {
                txtDetails[i].setVisibility(View.GONE);
                txtHeaders[i].setVisibility(View.GONE);
            } else {
                String txtHeader = "Cách bổ sung số " + (i + 1) + " trong biểu đồ";
                txtHeaders[i].setText(txtHeader);
                txtDetails[i].setText(arrBD[i]);
            }
        }
    }

    private static String[] taoMangChuoi(String ngaystring) {
        String[] mangChuoi = new String[9];
        for (int i = 0; i < ngaystring.length(); i++) {
            if (Character.isDigit(ngaystring.charAt(i))) {
                switch (ngaystring.charAt(i)) {
                    case '1':
                        mangChuoi[0] = (mangChuoi[0] != null ? mangChuoi[0] : "") + ngaystring.charAt(i);
                        break;
                    case '2':
                        mangChuoi[1] = (mangChuoi[1] != null ? mangChuoi[1] : "") + ngaystring.charAt(i);
                        break;
                    case '3':
                        mangChuoi[2] = (mangChuoi[2] != null ? mangChuoi[2] : "") + ngaystring.charAt(i);
                        break;
                    case '4':
                        mangChuoi[3] = (mangChuoi[3] != null ? mangChuoi[3] : "") + ngaystring.charAt(i);
                        break;
                    case '5':
                        mangChuoi[4] = (mangChuoi[4] != null ? mangChuoi[4] : "") + ngaystring.charAt(i);
                        break;
                    case '6':
                        mangChuoi[5] = (mangChuoi[5] != null ? mangChuoi[5] : "") + ngaystring.charAt(i);
                        break;
                    case '7':
                        mangChuoi[6] = (mangChuoi[6] != null ? mangChuoi[6] : "") + ngaystring.charAt(i);
                        break;
                    case '8':
                        mangChuoi[7] = (mangChuoi[7] != null ? mangChuoi[7] : "") + ngaystring.charAt(i);
                        break;
                    case '9':
                        mangChuoi[8] = (mangChuoi[8] != null ? mangChuoi[8] : "") + ngaystring.charAt(i);
                        break;
                    default:
                        break;
                }
            }
        }

        // Trả về mảng đã tạo
        return mangChuoi;
    }

    private static String[] taoMangChuoi2(String stringName) {
        String[] mangChuoi = new String[9];
        for (int i = 0; i < stringName.length(); i++) {
            switch (stringName.charAt(i)) {
                case 'A':
                case 'J':
                case 'S':
                    mangChuoi[0] = (mangChuoi[0] != null ? mangChuoi[0] : "") + 1;
                    break;
                case 'B':
                case 'K':
                case 'T':
                    mangChuoi[1] = (mangChuoi[1] != null ? mangChuoi[1] : "") + 2;
                    break;
                case 'C':
                case 'L':
                case 'U':
                    mangChuoi[2] = (mangChuoi[2] != null ? mangChuoi[2] : "") + 3;
                    break;
                case 'D':
                case 'M':
                case 'V':
                    mangChuoi[3] = (mangChuoi[3] != null ? mangChuoi[3] : "") + 4;
                    break;
                case 'E':
                case 'N':
                case 'W':
                    mangChuoi[4] = (mangChuoi[4] != null ? mangChuoi[4] : "") + 5;
                    break;
                case 'F':
                case 'O':
                case 'X':
                    mangChuoi[5] = (mangChuoi[5] != null ? mangChuoi[5] : "") + 6;
                    break;
                case 'G':
                case 'P':
                case 'Y':
                    mangChuoi[6] = (mangChuoi[6] != null ? mangChuoi[6] : "") + 7;
                    break;
                case 'H':
                case 'Q':
                case 'Z':
                    mangChuoi[7] = (mangChuoi[7] != null ? mangChuoi[7] : "") + 8;
                    break;
                case 'I':
                case 'R':
                    mangChuoi[8] = (mangChuoi[8] != null ? mangChuoi[8] : "") + 9;
                    break;
                default:
                    break;
            }
        }

        // Trả về mảng đã tạo
        return mangChuoi;
    }
}