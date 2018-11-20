package ca.ualberta.cs.personal_condition_tracker;

import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.util.ArrayList;
import java.util.List;

import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

public class UserAccountListController {
    private static UserAccountList userAccountList = null;

    // TODO we need a function which adds tweets to elastic search
    static public UserAccountList getUserAccountList() {
        if ((userAccountList) == null) {
            userAccountList = new UserAccountList();
        }
        return userAccountList;
    }

    public void addUserAccount(UserAccount userAccount){ getUserAccountList().addUserAccount(userAccount);}

//    static public void saveAccountList(){
//
//    }

}
