package patterns.stack_overflow;

import java.util.UUID;

class Tag{
    private final String id;
    private final String name;

    public Tag(String name) {
        if ( name == null || name.isEmpty()) {
            throw new IllegalArgumentException("ID and name cannot be null or empty");
        }
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}