import blog.Post;
import blog.PostNotFoundException;
import blog.PostStorage;

public class PostStorageImpl implements PostStorage {
    private Post[] posts = new Post[10];
    private int size = 0;

    @Override
    public void add(Post post) {
        if (size == posts.length) {
            extend();
        }
        posts[size++] = post;
    }

    private void extend() {
        Post[] temp = new Post[posts.length + 10];
        System.arraycopy(posts, 0, temp, 0, posts.length);
        posts = temp;
    }

    @Override
    public Post getPostByTitle(String title) throws PostNotFoundException {
        PostNotFoundException e = new PostNotFoundException();
        for (int i = 0; i < size; i++) {
            if (posts[i].getTitle().equals(title)) {
                return posts[i];
            }
        }
        throw e;
    }

    @Override
    public void searchPostsByKeyword(String keyword){
        for (int i = 0; i < size; i++) {
            if (posts[i].getTitle().contains(keyword)
                    || posts[i].getText().contains(keyword)) {
                System.out.println(posts[i]);
            }
        }
    }

    @Override
    public void printPostsByCategory(String category) {
        for (int i = 0; i < size; i++) {
            if (posts[i].getCategory().equals(category))
                System.out.println(posts[i]);
        }
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(posts[i]);
        }
    }
}