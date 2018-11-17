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
    //private static JestDroidClient client;
    private static UserAccountList userAccountList = null;
    // TODO we need a function which adds tweets to elastic search
    static public UserAccountList getUserAccountList() {
        if ((userAccountList) == null) {
            userAccountList = new UserAccountList();
        }
        return userAccountList;
    }

//    static public void saveAccountList(){
//
//    }
//    public static class AddUserAccountsTask extends AsyncTask<UserAccount, Void, Void> {
//
//        @Override
//        protected Void doInBackground(UserAccount... userAccounts) {
//            verifySettings();
//
//            for (UserAccount userAccount : userAccounts) {
//                Index index = new Index.Builder(userAccount).index("cmput301f18t28test").type("userAccount").build();
//
//                try {
//                    DocumentResult result = client.execute(index);
//                    if (result.isSucceeded()) {
//                       userAccount.setId(result.getId());
//                        Log.e("Error", "Success.");
//                    }
//                    else {
//                        Log.e("Error", "Elastic search was not able to add the account.");
//                    }
//                }
//                catch (Exception e) {
//                    Log.i("Error", "The application failed to build and send the user accounts.");
//                }
//
//            }
//            return null;
//        }
//    }
//
//    // TODO we need a function which gets tweets from elastic search
//    public static class GetUserAccountsTask extends AsyncTask<String, Void, ArrayList<UserAccount>> {
//        @Override
//        protected ArrayList<UserAccount> doInBackground(String... search_parameters) {
//            verifySettings();
//
//            ArrayList<UserAccount> userAccounts = new ArrayList<UserAccount>();
//
//            Search search = new Search.Builder( search_parameters[0])
//                    //Search search = new Search.Builder( search_parameters[0] )
//                    .addIndex("cmput301f18t28test")
//                    .addType("userAccount")
//                    .build();
//
//            try {
//                SearchResult result = client.execute(search);
//                if (result.isSucceeded()) {
//                    List<UserAccount> foundAccounts = result.getSourceAsObjectList(UserAccount.class);
//                    userAccounts.addAll(foundAccounts);
//                    Log.e("Error", "Success.");
//                }
//                else {
//                    Log.e("Error", "The search query failed to find any user accounts that matched.");
//                }
//            }
//            catch (Exception e) {
//                Log.e("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
//            }
//            return userAccounts;
//        }
//    }
//
//
//
//
//    public static void verifySettings() {
//        if (client == null) {
//            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080/");
//            DroidClientConfig config = builder.build();
//
//            JestClientFactory factory = new JestClientFactory();
//            factory.setDroidClientConfig(config);
//            client = (JestDroidClient) factory.getObject();
//        }
//    }

    public void addUserAccount(UserAccount userAccount){ getUserAccountList().addUserAccount(userAccount);}

}
