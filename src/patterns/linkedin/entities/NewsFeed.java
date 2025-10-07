package patterns.linkedin.entities;

import patterns.linkedin.strategies.FeedSortingStrategy;

import java.util.List;

public class NewsFeed {
    private final List<Post> allPosts;
    private final FeedSortingStrategy sortingStrategy;

    public NewsFeed(List<Post> posts, FeedSortingStrategy strategy){
        this.allPosts = posts;
        this.sortingStrategy = strategy;
    }

    public void displayAllPosts(){
        List<Post> sortedList = sortingStrategy.sort(allPosts);
        if(sortedList.isEmpty()){
            System.out.println("Your news feed is empty.");
            return;
        }
        for(Post post: sortedList){
            System.out.printf("Post by %s at %s %n",post.getAuthor().getName(),post.getCreatedAt().toLocalDate());
            System.out.printf("Content: %s %n",post.getContent());
            System.out.printf("total likes - %d and total comments - %d %n",post.likesCount(),post.commentsCount());
            System.out.println("-----------------");
        }
    }
}
