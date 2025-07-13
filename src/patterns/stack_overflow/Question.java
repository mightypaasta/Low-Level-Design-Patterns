package patterns.stack_overflow;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

class Question implements  Commentable, Votable {
    private final String id;
    private final String title;
    private final String content;
    private final User author;
    private final LocalDateTime creationDateTime;
    private final ArrayList<Answer> answers;
    private final ArrayList<Comment> comments;
    private final ArrayList<Vote> votes;
    private final ArrayList<Tag> tags;
    private Answer acceptedAnswer;

    public Question( String title, String content, User author, ArrayList<Tag> tags) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
        this.author = author;
        this.creationDateTime = LocalDateTime.now();
        this.answers = new ArrayList<Answer>();
        this.comments = new ArrayList<Comment>();
        this.votes = new ArrayList<Vote>();
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
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

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public ArrayList<Vote> getVotes() {
        return votes;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public Answer getAcceptedAnswer() {
        return acceptedAnswer;
    }

    public void acceptAnswer(Answer answer){
        if (answers.contains(answer)) {
            acceptedAnswer = answer;
            answer.markAsAccepted();
            answer.getAuthor().updateReputation(15); // Assuming reputation points for accepting an answer
        } else {
            throw new IllegalArgumentException("Answer not found in the question's answers.");
        }
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void addTag(Tag tag) {
        if (!tags.contains(tag)) {
            this.tags.add(tag);
        } else {
            throw new IllegalArgumentException("Tag already exists in the question.");
        }
    }

    public void vote(User voter, VoteType voteType) {
        if (voter == null || voteType == null) {
            throw new IllegalArgumentException("Voter and vote type cannot be null");
        }
        Vote vote = new Vote(voter, voteType);
        votes.add(vote);
        author.updateReputation(vote.getVoteValue());
    }

    public int getVoteCount() {
        return votes.size();
    }
}