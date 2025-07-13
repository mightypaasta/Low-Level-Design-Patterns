package patterns.stack_overflow;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

class Answer implements Commentable, Votable {
    private final String id;
    private final String content;
    private final User author;
    private final LocalDateTime  creationDateTime;
    private final Question question;
    private boolean isAccepted;
    private final ArrayList<Comment> comments;
    private final ArrayList<Vote> votes;

    public Answer(String content, User author, Question question) {
        this.id = UUID.randomUUID().toString();
        this.content = content;
        this.author = author;
        this.creationDateTime = LocalDateTime.now();
        this.question = question;
        this.isAccepted = false;
        this.comments = new ArrayList<Comment>();
        this.votes = new ArrayList<Vote>();
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public User getAuthor() {
        return author;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public Question getQuestion() {
        return question;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public ArrayList<Vote> getVotes() {
        return votes;
    }

    public int getVoteCount() {
        return votes.size();
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void markAsAccepted() {
        if (this.isAccepted) {
            throw new IllegalStateException("This answer is already accepted.");
        }
        this.isAccepted = true;
        // Optionally, update the question to reflect the accepted answer
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void vote(User voter, VoteType voteType){
        if (voter == null || voteType == null) {
            throw new IllegalArgumentException("Voter and vote type cannot be null");
        }
        Vote vote = new Vote(voter, voteType);
        this.votes.add(vote);
        // Update author's reputation based on the vote
        int reputationChange = vote.getVoteValue();
        author.updateReputation(reputationChange);
    }

}