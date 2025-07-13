package patterns.stack_overflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

class StackOveflow{
    private static StackOveflow instance;
    private final HashMap<String, User> users;
    private final HashMap<String, Question> questions;
    private final HashMap<String, Answer> answers;
    private final HashMap<String, Tag> tags;

    public StackOveflow() {
        this.users = new HashMap<>();
        this.questions = new HashMap<>();
        this.answers = new HashMap<>();
        this.tags = new HashMap<>();
    }

    public static synchronized StackOveflow getInstance(){
        if(instance==null){
            instance = new StackOveflow();
        }
        return instance;
    }

    public User addUser(String name, String email){
        User user = new User(name,email);
        users.put(user.getId(), user);
        return user;
    }

    public Question postQuestion(String title, String content, String userId, List<String> questionTags) {
        User author = users.get(userId);

        if(author==null){
            throw new IllegalArgumentException("User not found");
        }
        if(title == null || title.isEmpty() || content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Title and content cannot be null or empty");
        }

        ArrayList<Tag> tagList = new ArrayList<>();
        for(String qTag: questionTags){
            Tag tag = tags.getOrDefault(qTag, new Tag(qTag));
            tags.put(qTag, tag);
            tagList.add(tags.get(qTag));
        }

        Question question = new Question(title,content,author,tagList);

        questions.put(question.getId(), question);
        return question;
    }

    public Answer postAnswer(String content, String userId, String questionId) {
        User author = users.get(userId);
        Question question = questions.get(questionId);

        if(author==null){
            throw new IllegalArgumentException("User not found");
        }
        if(content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or empty");
        }
        if(question == null) {
            throw new IllegalArgumentException("Question not found");
        }

        Answer answer = new Answer(content, author, question);
        answers.put(answer.getId(), answer);
        question.getAnswers().add(answer);
        return answer;
    }

    public Comment addComment(String content, String userId, Commentable commentable) {
        User author = users.get(userId);

        if(author==null){
            throw new IllegalArgumentException("User not found");
        }
        if(content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or empty");
        }

        Comment comment = new Comment(content, author);
        commentable.addComment(comment);
        return comment;
    }

    public void vote(String userId, VoteType voteType, Votable votable) {
        User voter = users.get(userId);

        if(voter==null){
            throw new IllegalArgumentException("User not found");
        }
        if(voteType == null) {
            throw new IllegalArgumentException("Vote type cannot be null");
        }

        Vote vote = new Vote(voter, voteType);
        votable.vote(voter, voteType);
    }

    public void acceptAnswer(String userId, String answerId){
        User author = users.get(userId);
        Answer answer = answers.get(answerId);

        if(author == null) {
            throw new IllegalArgumentException("User not found");
        }
        if(answer == null) {
            throw new IllegalArgumentException("Answer not found");
        }
        if(!answer.getQuestion().getAuthor().equals(author)) {
            throw new IllegalArgumentException("Only the question author can accept an answer");
        }

        answer.getQuestion().acceptAnswer(answer);
    }

    public ArrayList<Question> searchQuestionsByKeyword(String query){

        if (query == null || query.isEmpty()) {
            throw new IllegalArgumentException("Keyword cannot be null or empty");
        }

        return questions.values().stream()
                .filter(question -> question.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                        question.getContent().toLowerCase().contains(query.toLowerCase()) ||
                        question.getTags().stream().anyMatch(tag -> tag.getName().equalsIgnoreCase(query)))
                .collect(Collectors.toCollection(ArrayList::new));

    }

    public ArrayList<Question> searchQuestionsByTag(String tagName){

        if (tagName == null || tagName.isEmpty()) {
            throw new IllegalArgumentException("Tag name cannot be null or empty");
        }

        Tag tag = tags.get(tagName);
        if (tag == null) {
            throw new IllegalArgumentException("Tag not found: " + tagName);
        }

        ArrayList<Question> taggedQuestions = new ArrayList<>();
        for (Question question : questions.values()) {
            if (question.getTags().contains(tag)) {
                taggedQuestions.add(question);
            }
        }

        return taggedQuestions;
    }

    public ArrayList<Question> searchQuestionsByAuthor(String userId){

        if(userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }

        User author = users.get(userId);
        if (author == null) {
            throw new IllegalArgumentException("User not found");
        }

        ArrayList<Question> authoredQuestions = new ArrayList<>();
        for (Question question : questions.values()) {
            if (question.getAuthor().equals(author)) {
                authoredQuestions.add(question);
            }
        }

        return authoredQuestions;
    }

}