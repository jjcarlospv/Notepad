package pe.area51.notepad;

import android.app.Application;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by alumno on 9/12/15.
 */
public class MyApplication extends Application {

    private static final String PARSE_APLICATION_ID = "LMpi1U1WedfG7EcxuYG85obVEXW1iHZSBaic3bzJ";
    private static final String PARSE_CLIENT_ID = "jcAXQ8tKY6oskLICEfBODZMtNHBmfStrWrLZA6z9";

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, PARSE_APLICATION_ID, PARSE_CLIENT_ID);

        //Test
        final ParseObject parseObject = new ParseObject("TestClass");
        parseObject.add("message","hello world!");
        parseObject.add("newColumn", "test");
        parseObject.saveInBackground();

        final ParseQuery parseQuery =  ParseQuery.getQuery("TestClass");
        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                for(ParseObject parseObject1 : list){
                    Log.d("Parse Server response",parseObject1.toString());
                }

            }
        });

    }
}
