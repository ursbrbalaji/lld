package classes;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    private final String name;
    private final List<Partition> partitonList;

    public Topic(String name, int partitionCount) {
        this.name = name;
        this.partitonList = new ArrayList<>();
        for (int i = 0; i < partitionCount; i++) {
            this.partitonList.add(new Partition(i));
        }
    }

    public Partition getPartitionById(int id) {
        return this.partitonList.get(id);
    }

    public List<Partition> getPartitonList() {
        return partitonList;
    }

    public String getName() {
        return name;
    }
}
