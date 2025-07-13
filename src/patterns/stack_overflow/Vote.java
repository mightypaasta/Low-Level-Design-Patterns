package patterns.stack_overflow;

enum VoteType {
    UPVOTE,
    DOWNVOTE
}

record Vote(User voter, VoteType type){
    public Vote {
        if (voter == null || type == null) {
            throw new IllegalArgumentException("Voter and type cannot be null");
        }
    }

    public int getVoteValue() {
        return type == VoteType.UPVOTE ? 1 : -1;
    }
}