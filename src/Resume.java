/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    String uuid;

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public int compareTo(Resume resume) {
        return uuid.compareTo(resume.uuid);
    }
}
