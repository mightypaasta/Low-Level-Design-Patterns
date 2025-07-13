package patterns.stack_overflow;

import java.util.ArrayList;

interface Commentable{
    void addComment(Comment comment);
    ArrayList<Comment> getComments();
}

interface Votable {
    void vote(User voter, VoteType type);
    ArrayList<Vote> getVotes();
    int getVoteCount();
}