package patterns.linkedin.strategies;

import patterns.linkedin.entities.Post;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ChronologicalSortStrategy implements FeedSortingStrategy{

    @Override
    public List<Post> sort(List<Post> posts) {
        List<Post> results;
        results = posts.stream()
                .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
                .toList();
        return results;
    }
}
