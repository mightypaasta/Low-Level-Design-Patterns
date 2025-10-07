package patterns.linkedin.services;

import patterns.linkedin.entities.Connection;
import patterns.linkedin.entities.Member;
import patterns.linkedin.entities.NewsFeed;
import patterns.linkedin.entities.Post;
import patterns.linkedin.strategies.FeedSortingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NewsFeedService {
    private final Map<String, List<Post>> allPosts;

    public NewsFeedService(){
        this.allPosts = new ConcurrentHashMap<>();
    }

    public void addPost(Member member, Post post){
        String memberId = member.getId();
        if(!allPosts.containsKey(memberId)){
            allPosts.put(memberId,new ArrayList<>());
        }
        allPosts.get(memberId).add(post);
    }

    public List<Post> getMemberPosts(String memberId){
        return allPosts.get(memberId);
    }

    public void displayFeedForMember(Member member, FeedSortingStrategy strategy){
        List<Post> feedPosts = new ArrayList<>();

        for(Member connection: member.getConnections()){
            List<Post> connectionPosts = allPosts.get(connection.getId());
            if(connectionPosts!=null){
                feedPosts.addAll(connectionPosts);
            }
        }

        NewsFeed feed = new NewsFeed(feedPosts,strategy);
        feed.displayAllPosts();
    }
}
