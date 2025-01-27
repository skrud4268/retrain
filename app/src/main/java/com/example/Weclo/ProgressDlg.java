package com.example.Weclo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class ProgressDlg extends AsyncTask<Integer, String, Integer> {

    private ProgressDialog mDlg;
    private Context mCtx;

    //생성자
    public ProgressDlg(Context ctx) {
        mCtx = ctx;
    }

    @Override
    protected void onPreExecute() {
        //ProgressDialog 세팅
        mDlg = new ProgressDialog(mCtx);
        //스타일 설정
        //커스텀은 R.style.***
        mDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //프로그래스 다이얼로그 나올 때 메시지 설정.
        mDlg.setMessage("로딩중...");
        //세팅된 다이얼로그를 보여줌.
        mDlg.show();

        super.onPreExecute();
    }

    @Override
    protected Integer doInBackground(Integer... params) {
        //프로그래스바 최대치가 몇인지 설정하는 변수
        final int taskCnt = params[0];
        //프로그래스바 최대치 설정
        publishProgress("max", Integer.toString(taskCnt));

        for (int i = 0; i < taskCnt; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //프로그래스바 현재 진행상황 설정
            publishProgress("progress", Integer.toString(i),
                    "Task " + Integer.toString(i) + " number");
        }
        //PostExecute로 리턴
        return taskCnt;
    }

//    //프로그래스가 업데이트 될때 호출
//    @Override
//    protected void onProgressUpdate(String... values) {
//        if (values[0].equals("progress")) {
//            mDlg.setProgress(Integer.parseInt(values[1]));
//            mDlg.setMessage(values[2]);
//        } else if (values[0].equals("max")) {
//            mDlg.setMax(Integer.parseInt(values[1]));
//        }
//    }

    //Background에서 처리가 완료되면 호출
    @Override
    protected void onPostExecute(Integer integer) {
        //다이얼로그를 없앰
        mDlg.dismiss();
//        Toast.makeText(mCtx, Integer.toString(integer) + " total sum",
//                Toast.LENGTH_SHORT).show();
    }
}
