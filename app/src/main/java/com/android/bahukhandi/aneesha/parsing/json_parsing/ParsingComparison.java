package com.android.bahukhandi.aneesha.parsing.json_parsing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.android.bahukhandi.aneesha.parsing.json_parsing.models.ComplexJavaObject;
import com.android.bahukhandi.aneesha.parsing.json_parsing.models.SimpleJavaObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ParsingComparison extends AppCompatActivity implements View.OnClickListener{

    private RelativeLayout mOptionsLayout;
    private AppCompatButton mMoshiParser, mGsonParser;
    private AppCompatTextView mMoshiHeader, mGsonHeader;
    private AppCompatRadioButton mSmallJson, mBigJson;
    private Gson gsonParser;
    private Moshi moshiParser;

    private static final String MOSHI_TAG = "moshi_tag";
    private static final String GSON_TAG = "gson_tag";

    private static final int SMALL_JSON_OPTION = 0;
    private static final int BIG_JSON_OPTION = 1;
    private static final int MAX_PERMISSIBLE_VAL = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parsing_comparison);

        initData();
        initView();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.tv_parse_gson_section_header:
                openOptionsView(v, GSON_TAG, mOptionsLayout, mGsonParser, mMoshiParser, mMoshiHeader);
                break;
            case R.id.tv_parse_moshi_section_header:
                openOptionsView(v, MOSHI_TAG, mOptionsLayout, mMoshiParser, mGsonParser, mGsonHeader);
                break;
            case R.id.btn_gson:
                parseWithGson(getSelectedOption());
                break;
            case R.id.btn_moshi:
                parseWithMoshi(getSelectedOption());
                break;
        }
    }

    private void initData(){
        gsonParser = new Gson();
        moshiParser = new Moshi.Builder().build();
    }

    private void initView(){
        mMoshiHeader = (AppCompatTextView)findViewById(R.id.tv_parse_moshi_section_header);
        mMoshiHeader.setOnClickListener(this);
        mGsonHeader = (AppCompatTextView)findViewById(R.id.tv_parse_gson_section_header);
        mGsonHeader.setOnClickListener(this);
        mOptionsLayout = (RelativeLayout)findViewById(R.id.rl_parser_options);
        mMoshiParser = (AppCompatButton)findViewById(R.id.btn_moshi);
        mMoshiParser.setOnClickListener(this);
        mGsonParser = (AppCompatButton)findViewById(R.id.btn_gson);
        mGsonParser.setOnClickListener(this);

        mSmallJson = (AppCompatRadioButton) findViewById(R.id.rb_small_json);
        mBigJson = (AppCompatRadioButton)findViewById(R.id.rb_big_json);
    }

    private void openOptionsView(View v, String tag, View optionsLayout, View optionsBtn, View otherOptionBtn, View otherHeader){
        v.setTag(tag);
        v.setVisibility(View.GONE);
        optionsLayout.setVisibility(View.VISIBLE);
        optionsBtn.setVisibility(View.VISIBLE);
        otherOptionBtn.setVisibility(View.GONE);
        otherHeader.setVisibility(View.VISIBLE);
    }

    private int getSelectedOption(){
        if (mSmallJson.isChecked()){
            return SMALL_JSON_OPTION;
        } else{
            return BIG_JSON_OPTION;
        }
    }

    private void parseWithGson(int option){
        String fileName;
        int numOfExp = MAX_PERMISSIBLE_VAL;
        Log.e("number of exp :: ", String.valueOf(numOfExp));
        switch (option){
            case BIG_JSON_OPTION:
                fileName = "big.json";
                while (numOfExp-- > 0) {
                    pojoToJsonGson(jsonToPojoGSON(fileName, option));
                }
                break;
            case SMALL_JSON_OPTION:
            default:
                fileName = "small.json";
                while (numOfExp-- > 0) {
                    pojoToJsonGson(jsonToPojoGSON(fileName, option));
                }
                break;
        }
    }

    private Object jsonToPojoGSON(String fileName, int option){
        List<SimpleJavaObject> simpleList = new ArrayList<>();
        List<ComplexJavaObject> complexList = new ArrayList<>();
        try {
            long start, end;
            start = System.currentTimeMillis();
            if (option == SMALL_JSON_OPTION) {
                simpleList = gsonParser.fromJson(new InputStreamReader(getAssets().open(fileName)),
                        new TypeToken<List<SimpleJavaObject>>() {
                        }.getType());
            } else {
                complexList = gsonParser.fromJson(new InputStreamReader(getAssets().open(fileName)),
                        new TypeToken<List<ComplexJavaObject>>(){}.getType());
            }
            end = System.currentTimeMillis();
            Log.e("Time taken to parse :: ", fileName + " :: time (millis) :: " + (end - start));
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return option == SMALL_JSON_OPTION ? simpleList : complexList;
    }

    private void pojoToJsonGson(Object javaObject){
        long start = System.currentTimeMillis();
        gsonParser.toJson(javaObject);
        long end = System.currentTimeMillis();
        Log.e("Time taken to reparse ", javaObject.getClass().getCanonicalName() + " :: time (millis) :: " + (end - start));
    }

    private void parseWithMoshi(int option){
        String fileName;
        int numOfExp = MAX_PERMISSIBLE_VAL;
        Log.e("number of exp :: ", String.valueOf(numOfExp));
        switch (option){
            case BIG_JSON_OPTION:
                fileName = "big.json";
                while (numOfExp-- > 0) {
                    pojoToJsonMoshi(jsonToPojoMoshi(fileName, option));
                }
                break;
            case SMALL_JSON_OPTION:
            default:
                fileName = "small.json";
                while (numOfExp-- > 0) {
                    pojoToJsonMoshi(jsonToPojoMoshi(fileName, option));
                }
                break;
        }
    }

    private Object jsonToPojoMoshi(String fileName, int option){
        List<SimpleJavaObject> simpleList = new ArrayList<>();
        List<ComplexJavaObject> complexList = new ArrayList<>();
        try {
            long start, end;
            start = System.currentTimeMillis();
            if (option == SMALL_JSON_OPTION) {
                JsonAdapter<List<SimpleJavaObject>> simpleAdapter = moshiParser.adapter(Types.newParameterizedType(List.class, SimpleJavaObject.class));
                simpleList = simpleAdapter.fromJson(loadJSONFromAsset(fileName));
            } else {
                JsonAdapter<List<ComplexJavaObject>> complexAdapter = moshiParser.adapter(Types.newParameterizedType(List.class, ComplexJavaObject.class));
                complexList = complexAdapter.fromJson(loadJSONFromAsset(fileName));
            }
            end = System.currentTimeMillis();
            Log.e("Time taken to parse :: ", fileName + " :: time (millis) :: " + (end - start));
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return option == SMALL_JSON_OPTION ? simpleList : complexList;
    }

    private void pojoToJsonMoshi(Object javaObject){
        long start = System.currentTimeMillis();
        if (javaObject instanceof List && ((List) javaObject).get(0) instanceof SimpleJavaObject) {
            JsonAdapter<List<SimpleJavaObject>> simpleAdapter = moshiParser.adapter(Types.newParameterizedType(List.class, SimpleJavaObject.class));
            simpleAdapter.toJson((List<SimpleJavaObject>) javaObject);
        } else if (javaObject instanceof List && ((List) javaObject).get(0) instanceof ComplexJavaObject) {
            JsonAdapter<List<ComplexJavaObject>> complexAdapter = moshiParser.adapter(Types.newParameterizedType(List.class, ComplexJavaObject.class));
            complexAdapter.toJson((List<ComplexJavaObject>) javaObject);
        }
        long end = System.currentTimeMillis();
        Log.e("Time taken to reparse ", javaObject.getClass().getCanonicalName() + " :: time (millis) :: " + (end - start));
    }

    private String loadJSONFromAsset(String fileName) throws IOException {
        String json = null;
        InputStream is = getAssets().open(fileName);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        json = new String(buffer, "UTF-8");
        return json;
    }
}
