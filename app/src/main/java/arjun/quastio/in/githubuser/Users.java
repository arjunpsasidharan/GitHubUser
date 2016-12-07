package arjun.quastio.in.githubuser;


public class Users {
    String username;
    String repoUrl;
    String imageUrl;
    String gitScore;


    public Users(String username, String repoUrl, String imageUrl, String gitScore) {
        this.username = username;
        this.repoUrl = repoUrl;
        this.imageUrl = imageUrl;
        this.gitScore = gitScore;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRepoUrl() {
        return repoUrl;
    }

    public void setRepoUrl(String repoUrl) {
        this.repoUrl = repoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGitScore() {
        return gitScore;
    }

    public void setGitScore(String gitScore) {
        this.gitScore = gitScore;
    }
}
