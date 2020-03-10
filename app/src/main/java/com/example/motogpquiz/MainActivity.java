package com.example.motogpquiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;



public class MainActivity extends AppCompatActivity {

    private TextView countLabel;
    private ImageView questionImage;
    private Button answerBtn1, answerBtn2, answerBtn3, answerBtn4;

    private AdView mAdView;

    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    String quizData[][] = {
            // {"画像名", "正解", "選択肢１", "選択肢２", "選択肢３"}
            {"a1", "ホルヘ・マルティン", "ルカ・マリーニ", "ジャウマ・マシア", "この中にはいない"},
            {"a2", "マルコス・ラミレス", "鳥羽　海渡", "エネア・バスティアニーニ", "フィリップ・サラック"},
            {"a3", "ヴァレンティーノ・ロッシ", "カル・クラッチロウ", "ホルヘ・ロレンソ", "マルク・マルケス"},
            {"a4", "ジョアン・ミル", "アレイシ・エスパルガロ", "ブラッド・ビンダー", "アレックス・リンス"},
            {"a5", "アンドレア・ドヴィツィオーゾ", "ダニロ・ペトルッチ", "フランコ・モルビデリ", "この中にはいない"},
            {"a6", "この中にはいない", "ティト・ラバット", "フランコ・モルビデリ", "フランセスコ・バニャイア"},
            {"a7", "カル・クラッチロウ", "ダニロ・ペトルッチ", "アレックス・リンス", "ミゲール・オリベイラ"},
            {"a8", "アレックス・マルケス", "ダニー・ケント", "フランコ・モルビデリ", "ボ・ベンスナイダー"},
            {"a9", "アレックス・リンス", "ズルファミ・カイルディン", "ステファノ・マンジ", "イサック・ビニャーレス"},
            {"a10", "イサック・ビニャーレス", "サム・ロウズ", "スティーブン・オデンダル", "ミゲール・オリベイラ"},
            {"a11", "ブラッドリー・スミス", "ポル・エスパルガロ", "カル・クラッチロウ", "ミゲール・オリベイラ"},
            {"a12", "ダリン・ビンダー", "ニッコロ・アントネッリ", "ジョン・マクフィー", "この中にはいない"},
            {"a13", "中上　貴晶", "鳥羽　海渡", "佐々木　歩夢", "長島　哲太"},
            {"a14", "フランコ・モルビデリ", "フランセスコ・バニャイア", "アレックス・リンス", "この中にはいない"},
            {"a15", "ロレンソ・ダラ・ポルタ", "アロン・カネト", "アロンソ・ロペス", "ヤコブ・コンフェイル"},
            {"a16", "岡崎　静夏", "佐々木 歩夢", "鈴木 竜生", "真崎 一輝"},
            {"a17", "アンドレア・ドヴィツィオーゾ", "フランコ・モルビデリ", "ダニロ・ペトルッチ", "マーベリック・ビニャーレス"},
            {"a18", "アルバロ・バウティスタ", "この中にはいない", "ジャック・ミラー", "マルク・マルケス"},
            {"a19", "鈴木 竜生", "ニッコロ・アントネッリ", "アロンソ・ロペス", "アンドレア・ミーニョ"},
            {"a20", "ファビオ・クアルタラロ", "この中にはいない", "ホルヘ・ナバーロ", "ニッコロ・ブレガ"},
            {"a21", "シルバン・ギュントーリ", "アレックス・リンス", "ジョアン・ミル", "アレイシ・エスパルガロ"},
            {"a22", "ヨハン・ザルコ", "チャビエル・シメオン", "ファビオ・クアルタラロ", "トーマス・ルティ"},
            {"a23", "マティア・パッシーニ", "この中にはいない", "エリック・グラナド", "ドミニク・エガター"},
            {"a24", "ドミニク・エガーター", "チャビ・ビエルゲ", "フランセスコ・バグナイア", "ロマーノ・フェナティ"},
            {"a25", "ドミニク・エガーター", "フェデリコ・フリーニ", "ルカ・マリーニ", "アンドレア・ロカテッリ"},
            {"a26", "マルク・マルケス", "ホルヘ・ロレンソ", "ステファン・ブラドル", "アレックス・マルケス"},
            {"a27", "イェスコ・ラフィン", "ボ・ベンスナイダー", "エドガー・ポンス", "ソムキャット・チャントラ"},
            {"a28", "ダニ・ペドロサ", "ホルヘ・ロレンソ", "ステファン・ブラドル", "マルク・マルケス"},
            {"a29", "長島　哲太", "中上　貴晶", "水野　涼", "鳥羽　海渡"},
            {"a30", "ニッコロ・ブレガ", "ルカ・マリーニ", "マルコ・ベッツェッキ", "エネア・バスティアニーニ"},
            {"a31", "マーベリック・ビニャーレス", "バレンティーノ・ロッシ", "ホルヘ・ロレンソ", "アンドレア・イアンノーネ"},
            {"a32", "アンドレア・イアンノーネ", "アレイシ・エスパルガロ", "ブラドリー・スミス", "スコット・レディング"},
            {"a33", "この中にはいない", "スティーブン・オデンダル", "ジョー・ロバーツ", "エネア・バスティアニーニ"},
            {"a34", "ポル・エスパルガロ", "アレイシ・エスパルガロ", "ブラッド・ビンダー", "フランセスコ・バニャイア"},
            {"a35", "ミカ・カリオ", "ポル・エスパルガロ", "この中にはいない", "ブラッド・ビンダー"},
            {"a36", "ホルヘ・ロレンソ", "マルク・マルケス", "ダニ・ペドロサ", "ステファン・ブラドル"},
            {"a37", "ミゲール・オリベイラ", "この中にはいない", "ポル・エスパルガロ", "イケル・レクオーナ"},
            {"a38", "ロレンソ・バルダッサーリ", "ルカ・マリーニ", "ホルヘ・ナバーロ", "イェスコ・ラフィン"},
            {"a39", "この中にはいない", "ディマス・エッキー・プラタマ", "マルコ・ベッツェッキ", "マーセル・シュローター"},
            {"a40", "イケル・レクオーナ", "フィリップ・エッテル", "カイルール・イダム・パウィ", "チャビ・ビエルヘ"},
            {"a41", "佐々木 歩夢", "フィリップ・エッテル", "カイルール・イダム・パウィ", "鳥羽　海渡"},
            {"a42", "ジョアン・ミル", "アレックス・マルケス", "フランコ・モルビデリ", "チャビ・ビエルヘ"},
            {"a43", "ファビオ・ディ・ジャンアントニオ", "ホルヘ・ナバーロ", "トーマス・ルティ", "この中にはいない"},
            {"a44", "ルカ・マリーニ", "ニッコロ・ブレガ", "マルコ・ベッツェッキ", "レミー・ガードナー"},
            {"a45", "マルコ・ベッツェッキ", "ルカ・マリーニ", "ニッコロ・アントネッリ", "アンドレア・ミーニョ"},
            {"a46", "ヴァレンティーノ・ロッシ", "マックス・ビアッジ", "セテ・ジベルノウ", "阿部　典史"},
            {"a47", "カレル・アブラハム", "ジャック・ミラー", "ミゲール・オリベイラ", "ティト・ラバト"},
            {"a48", "トニー・アルボリーノ", "ジョン・マクフィー", "ラウル・フェルナンデス", "アロンソ・ロペス"},
            {"a49", "マルセル・シュロッター", "トーマス・ルティ", "アウグスト・フェルナンデス", "チャビ・ビエルヘ"},
            {"a50", "デニス・フォッジア", "ロレンソ・ダラ・ポルタ", "セレスティーノ・ビエッティ", "アンドレア・ミーニョ"}

    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        countLabel = findViewById(R.id.countLabel);
        questionImage = findViewById(R.id.questionImage);
        answerBtn1 = findViewById(R.id.answerBtn1);
        answerBtn2 = findViewById(R.id.answerBtn2);
        answerBtn3 = findViewById(R.id.answerBtn3);
        answerBtn4 = findViewById(R.id.answerBtn4);

        // クイズデータquizDataからクイズ出題用のquizArrayを作成する
        for (int i = 0; i < quizData.length; i++) {
            // 新しいArrayListを用意
            ArrayList<String> tmpArray = new ArrayList<>();

            // クイズデータを追加
            tmpArray.add(quizData[i][0]); // 画像名
            tmpArray.add(quizData[i][1]); // 正解
            tmpArray.add(quizData[i][2]); // 選択肢１
            tmpArray.add(quizData[i][3]); // 選択肢２
            tmpArray.add(quizData[i][4]); // 選択肢３

            // tmpArrayをquizArrayに追加
            quizArray.add(tmpArray);
        }

        showNextQuiz();
    }

    public void showNextQuiz() {
        // クイズカウントラベルを更新
        countLabel.setText("Q" + quizCount);

        // ランダムな数字を取得
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        // randomNumを使って、quizArrayからクイズを一つ取り出す
        ArrayList<String> quiz = quizArray.get(randomNum);

        // 問題画像をセット
        questionImage.setImageResource(
                getResources().getIdentifier(quiz.get(0), "drawable", getPackageName())
        );

        // 正解をrightAnswerをセット
        rightAnswer = quiz.get(1);

        // クイズ配列から画像名を削除
        quiz.remove(0);

        // 正解と選択肢３つをシャッフル
        Collections.shuffle(quiz);

        // 回答ボタンに正解と選択肢３つをセット
        answerBtn1.setText(quiz.get(0));
        answerBtn2.setText(quiz.get(1));
        answerBtn3.setText(quiz.get(2));
        answerBtn4.setText(quiz.get(3));

        // このクイズをquizArrayから削除
        quizArray.remove(randomNum);
    }

    public void checkAnswer(View view) {

        // どの回答ボタンが押されたか
        Button answerBtn = findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        String alertTitle;
        if (btnText.equals(rightAnswer)) {
            // 正解
            alertTitle = "正解！";
            rightAnswerCount++;
        } else {
            // 不正解
            alertTitle = "不正解...";
        }

        // ダイアログ作成
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("答え：" + rightAnswer);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (quizCount >= 5) {
                    // ５問出題したら結果を表示
                    showResult();
                } else {
                    quizCount++;
                    // 次の問題を表示
                    showNextQuiz();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    public void showResult() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("結果");
        builder.setMessage(quizCount + "問中" + rightAnswerCount + "問正解！！");
        builder.setPositiveButton("もう一度", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                recreate();
            }
        });
        builder.setNegativeButton("終了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNeutralButton("シェアする", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String url = "http://twitter.com/share?text=MotoGPクイズ！私は" + quizCount + "問中" + rightAnswerCount +
                        "問正解でした！！ http://play.google.com/store/apps/details?id=com.example.motogpquiz";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        builder.show();
    }
}
