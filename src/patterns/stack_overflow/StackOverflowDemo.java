package patterns.stack_overflow;

import java.util.Arrays;

public class StackOverflowDemo{
    public static void runDemo(){

        StackOveflow instance = StackOveflow.getInstance();

        // Create users
        User Alice = instance.addUser("Alice", "alice@yopmail.com");
        User Bob = instance.addUser("Bob", "bob@yopmail.com");
        User Charlie = instance.addUser("Charlie", "charlie@yopmail.com");

        // Post a question
        Question question = instance.postQuestion(
            "How to implement a Singleton pattern in Java?",
            "I am trying to implement a Singleton pattern in Java. Can anyone help me with the best practices?",
            Alice.getId(),
            Arrays.asList("Java", "Design Patterns")
        );

        // Post an answer
        Answer answer = instance.postAnswer(
            "You can implement a Singleton pattern by making the constructor private and providing a static method to get the instance.",
            Bob.getId(),
            question.getId()
        );

        // Post a comment on the answer
        instance.addComment(
            "This is a great answer! Thanks for sharing.",
            Charlie.getId(),
            answer
        );

        instance.addComment(
            "This is a great question and a very common design pattern in Java.",
            Charlie.getId(),
            question
        );

        instance.vote(Charlie.getId(), VoteType.UPVOTE, question);
        instance.vote(Charlie.getId(),VoteType.UPVOTE, answer);
        instance.vote(Alice.getId(), VoteType.UPVOTE, answer);

        // Accept the answer
        instance.acceptAnswer(Alice.getId(), answer.getId());

        System.out.println("Question: " + question.getTitle());
        System.out.println("Posted by: " + question.getAuthor().getName());
        System.out.println("Content: " + question.getContent());
        System.out.println("Tags: " + question.getTags().stream().map(Tag::getName).toList());
        System.out.println("Answers: ");
        for (Answer ans : question.getAnswers()) {
            System.out.println(" - " + ans.getContent() + " (by " + ans.getAuthor().getName() + ")");
            System.out.println("   Accepted: " + ans.isAccepted());
            System.out.println("   Votes: " + ans.getVoteCount());
            System.out.println("   Comments: ");
            for (Comment comment : ans.getComments()) {
                System.out.println("     - " + comment.getContent() + " (by " + comment.getAuthor().getName() + ")");
            }
        }
        System.out.println("Comments on the question: ");
        for (Comment comment : question.getComments()) {
            System.out.println(" - " + comment.getContent() + " (by " + comment.getAuthor().getName() + ")");
        }
        System.out.println("Votes on the question: " + question.getVoteCount());

        // User's reputation
        System.out.println("Alice's reputation: " + Alice.getReputation());
        System.out.println("Bob's reputation: " + Bob.getReputation());
        System.out.println("Charlie's reputation: " + Charlie.getReputation());

        // Search questions by keyword
        String keyword = "Singleton";
        System.out.println("Searching questions with keyword '" + keyword + "':");
        instance.searchQuestionsByKeyword(keyword).forEach(q ->
            System.out.println(" - " + q.getTitle() + " (by " + q.getAuthor().getName() + ")")
        );

        // Search questions by tag
        String tagName = "Design Patterns";
        System.out.println("Searching questions with tag '" + tagName + "':");
        instance.searchQuestionsByTag(tagName).forEach(q ->
            System.out.println(" - " + q.getTitle() + " (by " + q.getAuthor().getName() + ")")
        );

        // Search questions by author
        System.out.println("Searching questions by author '" + Alice.getName() + "':");
        instance.searchQuestionsByAuthor(Alice.getId()).forEach(q ->
            System.out.println(" - " + q.getTitle() + " (by " + q.getAuthor().getName() + ")")
        );

    }
}