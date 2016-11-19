package com.android.learnit;

/**
 * Created by sarthakalang on 15/09/15.
 */
public class WordsContract {
    public class WordEntry{
        public static final String Table_name="Words";
        public static final String Column_Id="id";
        public static final String Column_Setname="setname";
        public static final String Column_Word="word";
        public static final String Column_Description="description";
        public static final String Column_Check="dcheck";
    }

    public class NewWords{
        public static final String Table_name="new_Words";
        public static final String Column_Id="new_id";
        public static final String Column_Setname="new_setname";
        public static final String Column_Word="new_word";
        public static final String Column_Description="new_description";
    }
}
