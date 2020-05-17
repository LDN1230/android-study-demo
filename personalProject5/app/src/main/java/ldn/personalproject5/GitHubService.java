package ldn.personalproject5;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author: LDN
 * @date: 2020/5/13
 */
public interface GitHubService {
    // 这里的List<Repo>即为最终返回的类型，需要保持一致才可解析
    // 之所以使用一个List包裹是因为该接口返回的最外层是一个数组
    @GET("/users/{user_name}/repos")
    Observable<List<GithubRepoObj>> getRepo(@Path("user_name") String user_name);

    @GET("/repos/{user_name}/{repo}/issues")
    Observable<List<GithubIssueObj>> getIssue(@Path("user_name") String user_name, @Path("repo") String repo);

    @Headers("Authorization: token 7126250495152fcc06a7a5a008805c3a18eb258c")
    //@FormUrlEncoded
    @POST("/repos/{user_name}/{repo}/issues")
    Observable<GithubPostIssueObj> postIssue(@Path("user_name") String user_name,
                                             @Path("repo") String repo,
                                             @Body GithubIssueJsonObj githubIssueObj);
}
