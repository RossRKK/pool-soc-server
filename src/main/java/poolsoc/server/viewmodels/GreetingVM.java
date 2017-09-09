package poolsoc.server.viewmodels;

public class GreetingVM {

    private final long id;
    private final String content;

    public GreetingVM(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}