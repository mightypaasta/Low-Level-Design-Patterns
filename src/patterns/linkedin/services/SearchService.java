package patterns.linkedin.services;

import patterns.linkedin.entities.Member;

import java.util.Collection;
import java.util.List;

public class SearchService {
    public final Collection<Member> allMembers;

    public SearchService(Collection<Member> members){
        this.allMembers = members;
    }

    public List<Member> searchByName(String name){
        List<Member> results;
        results = allMembers.stream()
                .filter(member -> member.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
        return results;
    }
}
