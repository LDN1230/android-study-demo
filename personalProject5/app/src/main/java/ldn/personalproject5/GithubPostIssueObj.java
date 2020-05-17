package ldn.personalproject5;

import com.google.gson.annotations.SerializedName;

/**
 * @author: LDN
 * @date: 2020/5/13
 */
public class GithubPostIssueObj {
    @SerializedName("id")
    private int id;

    public int getTitle() {
        return id;
    }
}
