package patterns.linkedin;

import patterns.linkedin.entities.Member;
import patterns.linkedin.entities.Post;
import patterns.linkedin.services.ConnectionService;
import patterns.linkedin.services.NewsFeedService;
import patterns.linkedin.services.NotificationService;
import patterns.linkedin.services.SearchService;
import patterns.linkedin.strategies.ChronologicalSortStrategy;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LinkedInSystem {
    private static LinkedInSystem instance;
    private final Map<String,Member> allMembers = new ConcurrentHashMap<>();

    private final ConnectionService connectionService;
    private final NewsFeedService newsFeedService;
    private final SearchService searchService;

    private LinkedInSystem(){
        connectionService = new ConnectionService(new NotificationService());
        newsFeedService = new NewsFeedService();
        searchService = new SearchService(allMembers.values());
    }

    public static LinkedInSystem getInstance(){
        if(instance==null){
            synchronized (LinkedInSystem.class){
                instance = new LinkedInSystem();
            }
        }
        return instance;
    }

    public void registerMember(Member member){
        allMembers.put(member.getId(),member);
    }

    public Member getMember(String name){
        return allMembers.values().stream().filter(member -> member.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public String sendRequest(Member from, Member to){
        return connectionService.requestConnection(from,to);
    }

    public void acceptRequest(String requestId){
        connectionService.acceptRequest(requestId);
    }

    public void createPost(String memberId, String content){
        Member author = allMembers.get(memberId);
        Post post = new Post(content,author);
        newsFeedService.addPost(author,post);
        System.out.printf("%s created a new post. %n",author.getName());
    }

    public Post getLatestPostByMember(String memberId){
        List<Post> memberPosts = newsFeedService.getMemberPosts(memberId);
        return memberPosts.getLast();
    }

    public void viewNewsFeed(String memberId){
        Member member = allMembers.get(memberId);
        System.out.printf("-----News Feed for %s----- %n",member.getName());
        newsFeedService.displayFeedForMember(member,new ChronologicalSortStrategy());
    }

    public List<Member> searchMemberByName(String name){
        return searchService.searchByName(name);
    }
}
