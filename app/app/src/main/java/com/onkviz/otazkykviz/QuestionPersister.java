package com.onkviz.otazkykviz;


import android.content.Context;
import android.content.res.AssetManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by LHE on 1/27/2018.
 */

public class QuestionPersister {

    private List<Question> dispqst = new ArrayList<Question>();
    private List<Question> questionList;
    public static String AssetJSONFile (String filename, Context context) throws IOException {
        AssetManager manager = context.getAssets();
        InputStream file = manager.open(filename);
        byte[] formArray = new byte[file.available()];
        file.read(formArray);
        file.close();

        return new String(formArray);
    }
    /*
        { questions: [ {},{} ]}
     */
    public List<Question> getAllQuestions(String jsonstring) {
        List<Question> quesList = new ArrayList<Question>();

        try {

            JSONObject json = new JSONObject(jsonstring);
            JSONArray qsts = json.getJSONArray("questions");
            for (int i = 0; i < qsts.length(); i++)
            {
                JSONObject q = qsts.getJSONObject(i);
                Question quest = new Question();
                quest.setID(q.getInt("id"));
                quest.setQUESTION(q.getString("question"));
                quest.setANSWER(q.getString("answer"));
                quest.setOPT1(q.getString("opt1"));
                quest.setOPT2(q.getString("opt2"));
                quest.setOPT3(q.getString("opt3"));
                quest.setOPT4(q.getString("opt4"));
                quesList.add(quest);

            }
            //return quesList;
        } catch (JSONException e) {
            e.printStackTrace();
            //throw e;

        }
        this.questionList = quesList;
        return quesList;

    }

    public String getAnswr(Question q, List<String> dispanswr) {

        Random r = new Random();
        List<String> as = new ArrayList<>();
        as.add(q.getOPT1());
        as.add(q.getOPT2());
        as.add(q.getOPT3());
        as.add(q.getOPT4());

        for (String s: dispanswr
                ) {
            as.remove(s);
        }

        int a = r.nextInt(4 - dispanswr.size());

        return as.get(a);
    }

    public Question getQuestion() {

        Random r = new Random();

        Question res = null;
        do {
            res = this.questionList.get(r.nextInt(this.questionList.size()));

        } while (this.dispqst.contains(res));

        this.dispqst.add(res);
        return res;


    }

}
